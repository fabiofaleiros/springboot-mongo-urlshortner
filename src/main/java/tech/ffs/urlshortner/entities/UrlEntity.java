package tech.ffs.urlshortner.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;

    private String fullUrl;

    //Using native TTL MongoDB
    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expireAt;

    public UrlEntity() {
    }

    public UrlEntity(String id, String fullUrl, LocalDateTime expireAt) {
        this.expireAt = expireAt;
        this.id = id;
        this.fullUrl = fullUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}
