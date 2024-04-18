package com.spring.ems.controller;

import com.spring.ems.entity.Color;
import com.spring.ems.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
public class ColorController {
    private ColorService colorService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color) {
        Color newColor = colorService.createColor(color);
        return new ResponseEntity<>(newColor, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Color>> getAll() {
        List<Color> colors = colorService.getALl();
        return ResponseEntity.ok(colors);
    }
}
