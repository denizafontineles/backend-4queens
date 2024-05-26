package com.cloudgirls.fourqeens.controller;

import com.cloudgirls.fourqeens.model.Category;
import com.cloudgirls.fourqeens.model.Product;
import com.cloudgirls.fourqeens.model.Subcategory;
import com.cloudgirls.fourqeens.repository.CategoryRepository;
import com.cloudgirls.fourqeens.repository.SubcategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subcategories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubcategoryController {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Subcategory> post(@Valid @RequestBody Subcategory subcategory){
        if (categoryRepository.existsById(subcategory.getCategory().getId())){
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(subcategoryRepository.save(subcategory));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category does not exist!", null);
    }

    @GetMapping
    public ResponseEntity<List<Subcategory>> getAll(){
        return ResponseEntity.ok(subcategoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getById(@PathVariable Long id){
        return subcategoryRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Subcategory> put(@Valid @RequestBody Subcategory subcategory){
        if(subcategoryRepository.existsById(subcategory.getId())){
            if (categoryRepository.existsById(subcategory.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK).body(subcategoryRepository.save(subcategory));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category does not exist!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        Optional<Subcategory> subcategory = subcategoryRepository.findById(id);
        if(subcategory.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        subcategoryRepository.deleteById(id);
    }
}
