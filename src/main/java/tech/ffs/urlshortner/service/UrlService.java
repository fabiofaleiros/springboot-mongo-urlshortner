package tech.ffs.urlshortner.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ffs.urlshortner.dto.UrlRequestDTO;
import tech.ffs.urlshortner.entities.UrlEntity;
import tech.ffs.urlshortner.repository.UrlRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String getHashUrlShortened(UrlRequestDTO request) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        return id;
    }

    public Optional<UrlEntity> getFullUrl(String id) {

        return urlRepository.findById(id);

    }

}
