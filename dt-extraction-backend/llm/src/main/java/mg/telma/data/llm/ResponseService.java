package mg.telma.data.llm;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ResponseService {

    public ResponseEntity<List<String>> createResponse(String sqlQuery) {
        return ResponseEntity.ok().body(Collections.singletonList(sqlQuery));
    }
}
