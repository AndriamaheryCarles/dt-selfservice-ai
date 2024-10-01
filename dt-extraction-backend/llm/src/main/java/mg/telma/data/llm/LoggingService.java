package mg.telma.data.llm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggingService {

    public void logRequest(String question) {
        log.info("user sent : [question={}]", question);
    }

    public void logResponse(String sqlQuery) {
        log.info("SQL query generated: [sqlQuery={}]", sqlQuery);
    }
}
