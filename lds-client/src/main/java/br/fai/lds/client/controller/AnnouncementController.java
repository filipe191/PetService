package br.fai.lds.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagina de anúncios")
public class AnnouncementController {

    @GetMapping("/adicionou anúncio")
    public String getHomePage(){

        return "announcement";

    }
}
