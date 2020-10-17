package del.ac.id.demo.repository;

import del.ac.id.demo.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<Store,String> {
}
