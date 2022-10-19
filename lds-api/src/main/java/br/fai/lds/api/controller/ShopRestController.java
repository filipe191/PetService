package br.fai.lds.api.controller;

import br.fai.lds.api.service.ShopRestService;
import br.fai.lds.api.service.TypeServRestService;
import br.fai.lds.model.entities.ShopModel;
import br.fai.lds.model.entities.TypeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "*")
public class ShopRestController {

    @Autowired
    private ShopRestService<ShopModel> shopRestService;

    @GetMapping("/find")
    public ResponseEntity<List<ShopModel>> find(){

        List<ShopModel> users = shopRestService.find();

        if (users == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<ShopModel> findById(@PathVariable("id") final int id) {
        ShopModel user = shopRestService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final ShopModel user) {

        int userId = shopRestService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final ShopModel entity) {
        boolean response = shopRestService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = shopRestService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
}
