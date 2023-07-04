package com.example.zalik_rsd.controller;

import com.example.zalik_rsd.modal.FarmEntity;
import com.example.zalik_rsd.repository.FarmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/api")
@CrossOrigin(origins = "*",
        allowedHeaders = "*")
@RestController
public class FarmEntityRestController {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/getAllFarmEntity")
    public ResponseEntity<String> getAllFarmEntity() {
        try {

            List<FarmEntity> farmEntities = farmRepository.findAll();
            String json = objectMapper.writeValueAsString(farmEntities);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(json);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/addFarmEntity")
    public ResponseEntity<String> createFarmEntity(@Valid @RequestBody FarmEntity farm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

        farmRepository.save(farm);

        String responseJson = null;
        try {
            responseJson = objectMapper.writeValueAsString("create new Farm Entity");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseJson);
    }

    @DeleteMapping("/removeFarmEntity/{id}")
    public ResponseEntity<String> deleteEntityById(@PathVariable("id") Long id) {
        Optional<FarmEntity> entityOptional = farmRepository.findById(id);

        if (entityOptional.isPresent()) {
            farmRepository.deleteById(id);
            return ResponseEntity.ok("Farm entity with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}