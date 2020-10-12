package ru.mydesignstudio.config.webui.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.PropertyDocument;

@Repository
public interface PropertyMongoRepository extends MongoRepository<PropertyDocument, String> {
}
