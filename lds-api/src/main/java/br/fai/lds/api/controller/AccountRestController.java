package br.fai.lds.api.controller;

import br.fai.lds.api.service.UserRestService;
import br.fai.lds.model.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountRestController {

    @Autowired
    private UserRestService userRestService;

    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestParam("username") final String username, @RequestParam ("password") final String password){

        UserModel userModel = userRestService.validateLogin(username, password);

        if (userModel == null) {
            return  ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(userModel);


    }

}
