package br.fai.lds.api.controller;



import br.fai.lds.api.service.AnnouncementRestService;
import br.fai.lds.model.entities.AnnouncementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@CrossOrigin(origins = "*")
public class AnnouncementRestController {

    @Autowired
    private AnnouncementRestService<AnnouncementModel> announcementService;

    @GetMapping("/find")
    public ResponseEntity<List<AnnouncementModel>> find(){

        List<AnnouncementModel> users = announcementService.find();

        if (users == null){
        return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(users);

    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<AnnouncementModel> findById(@PathVariable("id") final int id) {
        AnnouncementModel user = announcementService.findById(id);

        //vou retorna not found manda
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final int id) {
        boolean result = announcementService.deleteById(id);

        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/craete")
    public ResponseEntity<Integer> create(@RequestBody final AnnouncementModel user) {

        int userId = announcementService.create(user);
        if (userId == - 1 ) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userId);
//
//        return userId == -1 ? ResponseEntity.badRequest().build() : ResponseEntity.ok(userId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") final int id, @RequestBody final AnnouncementModel entity) {
        boolean response = announcementService.update(id, entity);

        return response ? ResponseEntity.ok(response) : ResponseEntity.badRequest().build();
    }


}



