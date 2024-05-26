package com.cloudgirls.fourqeens.controller;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.cloudgirls.fourqeens.model.Product;
import com.cloudgirls.fourqeens.model.Subcategory;
import com.cloudgirls.fourqeens.repository.ProductRepository;
import com.cloudgirls.fourqeens.repository.SubcategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product){
        if (subcategoryRepository.existsById(product.getSubcategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "subcategory does not exist!", null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        if(productRepository.existsById(product.getId())){
            if(subcategoryRepository.existsById((product.getSubcategory().getId())))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productRepository.save(product));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "subcategory does not exist!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){

        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productRepository.deleteById(id);
    }
}
