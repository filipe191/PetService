package br.fai.lds.api.controller;

import br.fai.lds.api.service.AddressRestService;
import br.fai.lds.api.service.UserRestService;
import br.fai.lds.model.entities.AddressModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressRestController {
    @Autowired
    private AddressRestService<AddressModel> addresService;

    @GetMapping("/find")
    public ResponseEntity<List<AddressModel>> find(){

        List<AddressModel> users = addresService.find();

        if (users == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<AddressModel> findById(@PathVariable("id") final int id) {
        AddressModel user = addresService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = addresService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final AddressModel user) {

        int userId = addresService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final AddressModel entity) {
        boolean response = addresService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

}
