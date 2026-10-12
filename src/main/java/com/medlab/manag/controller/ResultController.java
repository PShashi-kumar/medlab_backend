package com.medlab.manag.controller;

import com.medlab.manag.entity.Result;
import com.medlab.manag.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping
    public Result add(@RequestBody Result result) {
        return resultService.add(result);
    }

    @GetMapping
    public List<Result> getAll() {
        return resultService.getAll();
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getByBooking(@PathVariable Integer bookingId) {
        Result result = resultService.getByBooking(bookingId);
        if (result == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Result not available yet"));
        }
        return ResponseEntity.ok(result);
    }
}