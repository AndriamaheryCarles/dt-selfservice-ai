package mg.telma.data.llm;

import mg.telma.data.llm.utils.PromptUtils;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqlGenerationService {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    public String generateSqlQueryFromText(String question) {
        Prompt prompt = new PromptTemplate(PromptUtils.getDatabasePromptWithQuestion(question)).create();
        ChatResponse chatResponse = ollamaChatModel.call(prompt);
        String sqlQuery = chatResponse.getResult().getOutput().getContent();
        return sqlQuery;
    }
}
