package tech.ffs.urlshortner.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ffs.urlshortner.dto.UrlRequestDTO;
import tech.ffs.urlshortner.dto.UrlResponseDTO;
import tech.ffs.urlshortner.entities.UrlEntity;
import tech.ffs.urlshortner.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlShortnerController {

    private final UrlRepository urlRepository;

    public UrlShortnerController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<UrlResponseDTO> urlShortner(@RequestBody UrlRequestDTO request,
                                            HttpServletRequest servletRequest){

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new UrlResponseDTO(redirectUrl));

    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        var url = urlRepository.findById(id);

        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
