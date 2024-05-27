package controller;

import dto.UrlRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UrlShortnerController {


    @PostMapping("/shorten-url")
    public ResponseEntity<Void> urlShortner(@RequestBody UrlRequestDTO request){

        return ResponseEntity.ok().build();

    }

}
