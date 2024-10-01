package mg.telma.data.llm;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/llm")
public class OllamaController {

    @Autowired
    private LoggingService loggingService;

    @Autowired
    private SqlGenerationService sqlGenerationService;

    @Autowired
    private SqlQueryService sqlQueryService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private QuestionValidationService questionValidationService;

    @PostMapping("/user/text-to-sql")
    public ResponseEntity<List<String>> getSqlFromText(@RequestBody String question) {
        loggingService.logRequest(question);

        if (!questionValidationService.isValidQuestion(question)) {
            return ResponseEntity.badRequest().body(List.of("Invalid or meaningless question. Please ask a coherent question."));
        }

        String sqlQuery = sqlGenerationService.generateSqlQueryFromText(question);

        loggingService.logResponse(sqlQuery);

        sqlQueryService.saveSqlQuery(question, sqlQuery);

        return responseService.createResponse(sqlQuery);
    }
}
