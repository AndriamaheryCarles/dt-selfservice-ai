package mg.telma.data.llm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SqlQueryService {

    @Autowired
    private SqlQueryRepository sqlQueryRepository;

    public void saveSqlQuery(String question, String sqlQuery) {
        SqlQuery queryEntity = new SqlQuery(null, question, sqlQuery, LocalDateTime.now().toString());
        sqlQueryRepository.save(queryEntity);
    }
}
