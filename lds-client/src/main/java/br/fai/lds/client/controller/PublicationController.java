package br.fai.lds.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publication")

public class PublicationController {
    @GetMapping("/anuncio")
    public  String getAnuncioPage(){

        return "/publication/anuncio";
    }



}
