package br.fai.lds.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class ShopController {



    @GetMapping("/situation")
    public String getSituacao() {
        return "situation"; }
}
