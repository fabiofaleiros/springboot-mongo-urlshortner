package controller;

import dto.ShortUrlRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UrlShortnerController {


    @PostMapping("/shorten-url")
    public ResponseEntity<Void> urlShortner(@RequestBody ShortUrlRequestDTO request){

        return ResponseEntity.ok().build();

    }

}
