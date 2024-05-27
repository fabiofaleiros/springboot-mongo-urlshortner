package tech.ffs.urlshortner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.ffs.urlshortner.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
