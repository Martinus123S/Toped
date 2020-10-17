package del.ac.id.demo.repository;

import del.ac.id.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
