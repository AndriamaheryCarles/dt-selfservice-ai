package mg.telma.data.llm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlQueryRepository extends JpaRepository<SqlQuery, Long> {
}

