package del.ac.id.demo.repository;

import del.ac.id.demo.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {

}
