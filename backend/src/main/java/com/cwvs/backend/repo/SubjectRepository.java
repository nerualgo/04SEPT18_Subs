package com.cwvs.backend.repo;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.cwvs.backend.model.Subject;

public interface SubjectRepository extends MongoRepository<Subject, String>{
	List<Subject> findBySsn(int ssn);

}
