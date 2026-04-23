package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Tutor;
import group1.tutoringcenter.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
public class TutorRestController {

    private final TutorService tutorService;

    @Autowired
    public TutorRestController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    // GET /api/tutors - Get all records
    @GetMapping
    public List<Tutor> getAllTutors() {
        return tutorService.findAll();
    }

    // GET /api/tutors/{id} - Get one record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutorById(@PathVariable int id) {
        Tutor tutor = tutorService.getTutorById(id);
        if (tutor != null) {
            return ResponseEntity.ok(tutor);
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/tutors/search?name=xxx - Search by field
    @GetMapping("/search")
    public List<Tutor> searchTutors(@RequestParam("name") String name) {
        return tutorService.searchByName(name);
    }

    // POST /api/tutors - Create new record
    @PostMapping
    public Tutor createTutor(@RequestBody Tutor tutor) {
        return tutorService.addTutor(tutor);
    }

    // PUT /api/tutors/{id} - Full update
    @PutMapping("/{id}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable int id, @RequestBody Tutor tutor) {
        Tutor updated = tutorService.updateTutor(id, tutor);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/tutors/{id} - Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable int id) {
        tutorService.deleteTutor(id);
        return ResponseEntity.noContent().build();
    }
}
