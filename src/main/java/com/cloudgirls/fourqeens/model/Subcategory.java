package com.cloudgirls.fourqeens.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "the name field is required!")
    @Size(min = 2, max = 100, message = "minimum size: 2, maximum size: 100")
    private String name;
    private String description;
    @ManyToOne
    @JsonIgnoreProperties("subcategory")
    private Category category;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("subcategory")
    private List<Product> product;

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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
