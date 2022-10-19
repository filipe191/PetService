package br.fai.lds.api.controller;

import br.fai.lds.api.service.CategoryRestService;
import br.fai.lds.api.service.TypeServRestService;
import br.fai.lds.model.entities.CategoryModel;
import br.fai.lds.model.entities.TypeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryRestController {

    @Autowired
    private CategoryRestService<CategoryModel> categoryRestService;

    @GetMapping("/find")
    public ResponseEntity<List<CategoryModel>> find(){

        List<CategoryModel> users = categoryRestService.find();

        if (users == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<CategoryModel> findById(@PathVariable("id") final int id) {
        CategoryModel user = categoryRestService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final CategoryModel user) {

        int userId = categoryRestService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final CategoryModel entity) {
        boolean response = categoryRestService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = categoryRestService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
}
