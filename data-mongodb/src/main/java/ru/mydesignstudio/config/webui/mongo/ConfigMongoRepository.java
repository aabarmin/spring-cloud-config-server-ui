package ru.mydesignstudio.config.webui.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.ConfigDocument;

@Repository
public interface ConfigMongoRepository extends MongoRepository<ConfigDocument, String> {
}
