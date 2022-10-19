package br.fai.lds.api.controller;

import br.fai.lds.api.service.CityRestService;
import br.fai.lds.api.service.TypeServRestService;
import br.fai.lds.model.entities.CityModel;
import br.fai.lds.model.entities.TypeServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
@CrossOrigin(origins = "*")
public class CityRestController {

    @Autowired
    private CityRestService<CityModel> cityRestService;

    @GetMapping("/find")
    public ResponseEntity<List<CityModel>> find(){

        List<CityModel> users = cityRestService.find();

        if (users == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<CityModel> findById(@PathVariable("id") final int id) {
        CityModel user = cityRestService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }


    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final CityModel user) {

        int userId = cityRestService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final CityModel entity) {
        boolean response = cityRestService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = cityRestService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }
}
