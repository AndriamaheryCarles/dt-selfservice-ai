package mg.telma.data.llm;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mg.telma.data.llm.utils.PromptUtils;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/llm")
@Tag(name = "LLM Extraction")
public class OllamaController {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Autowired
    private SqlQueryRepository sqlQueryRepository;

    @PostMapping("/user/text-to-sql")
    public ResponseEntity<List<Generation>> getSqlFromText(@RequestBody String question) {
        log.info("user sent : [question={}]", question);
        Prompt prompt = new PromptTemplate(PromptUtils.getDatabasePromptWithQuestion(question)).create();
        ChatResponse chatResponse = ollamaChatModel.call(prompt);
        log.info("ollama replied : [response={}]", chatResponse.getResult().getOutput().getContent());

        String sqlQuery = chatResponse.getResult().getOutput().getContent();
        log.info("ollama replied : [response={}]", sqlQuery);
        SqlQuery queryEntity = new SqlQuery(null, question, sqlQuery, LocalDateTime.now().toString());
        sqlQueryRepository.save(queryEntity);
        return ResponseEntity.ok().body(chatResponse.getResults());
    }
}