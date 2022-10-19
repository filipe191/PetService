package br.fai.lds.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {


    @GetMapping("/roupa")
    public String getRoupaPage(){

        return "/product/roupa";
    }

    @GetMapping("/chale")
    public String getChalePage(){

        return "/product/chale";
    }

    @GetMapping("/brinquedo")
    public String getBrinquedoPage(){

        return "/product/brinquedo";
    }

    @GetMapping("/cama")
    public String getCamaPage(){

        return "/product/cama";
    }
}
