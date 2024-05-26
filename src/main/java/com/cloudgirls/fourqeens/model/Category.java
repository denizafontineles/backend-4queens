package com.cloudgirls.fourqeens.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "the name field is required!")
    @Size(min = 2, max = 100, message = "minimum size: 2, maximum size: 100")
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("category")
    private List<Subcategory> subcategory;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Subcategory> getSubcategory() {
        return subcategory;
    }
    public void setSubcategory(List<Subcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
