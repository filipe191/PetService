package br.fai.lds.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/playground")
@CrossOrigin(origins = "*")
//Este é para liberar acesso
public class PlaygroungRestController {

    //webflux
    //Monoflux

    @GetMapping("/test")
    public ResponseEntity<String> getPlaygroungData() {
        return ResponseEntity.ok("boa noite =D");
    }
}
