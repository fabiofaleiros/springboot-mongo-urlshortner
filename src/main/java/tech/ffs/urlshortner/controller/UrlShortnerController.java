package tech.ffs.urlshortner.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ffs.urlshortner.dto.UrlRequestDTO;
import tech.ffs.urlshortner.dto.UrlResponseDTO;
import tech.ffs.urlshortner.service.UrlService;

import java.net.URI;

@RestController
public class UrlShortnerController {

    private final UrlService urlService;

    public UrlShortnerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<UrlResponseDTO> urlShortner(@RequestBody UrlRequestDTO request,
                                            HttpServletRequest servletRequest){

        var id = urlService.getHashUrlShortened(request);

        var urlShortened = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new UrlResponseDTO(urlShortened));

    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        var url = urlService.getFullUrl(id);

        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
