package com.medlab.manag.controller;

import com.medlab.manag.entity.Test;
import com.medlab.manag.service.TestService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getAll() {
        return testService.getAll();
    }

    @PostMapping
    public Test add(@RequestBody Test test) {
        return testService.save(test);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Test test) {
        Test updated = testService.update(id, test);
        if (updated == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Test not found"));
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            testService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Test deleted"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Cannot delete: this test has bookings"));
        }
    }
}