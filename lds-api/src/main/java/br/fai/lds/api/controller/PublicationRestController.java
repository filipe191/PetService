package br.fai.lds.api.controller;

import br.fai.lds.api.service.PublicationRestService;
import br.fai.lds.api.service.TypeServRestService;
import br.fai.lds.model.entities.TypeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publication")
@CrossOrigin(origins = "*")
public class PublicationRestController {

    @Autowired
    private PublicationRestService publicationRestService;

    @Autowired
    private TypeServRestService<TypeServiceModel> typeServService;

    @GetMapping("/find")
    public ResponseEntity<List<TypeServiceModel>> find(){

        List<TypeServiceModel> users = typeServService.find();

        if (users == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<TypeServiceModel> findById(@PathVariable("id") final int id) {
        TypeServiceModel user = typeServService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final TypeServiceModel user) {

        int userId = typeServService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final TypeServiceModel entity) {
        boolean response = typeServService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = typeServService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
}
