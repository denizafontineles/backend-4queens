package com.cloudgirls.fourqeens.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="tb_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "the name field is required!")
    @Size(min = 2, max = 100, message = "minimum size: 2, maximum size: 100")
    private String name;
    @NotBlank(message = "the description field is required!")
    @Size(min = 10, max = 5000, message = "minimum size: 10, maximum size: 5000")
    private String description;
    @NotNull(message = "the price field is required!")
    @Column(precision = 12, scale = 2)
    private BigDecimal price;
    private Integer stock;
    @NotNull(message = "it is mandatory to insert a Product Media!")
    private String media;
    @UpdateTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JsonIgnoreProperties("product")
    private Subcategory subcategory;

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
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getMedia() {
        return media;
    }
    public void setMedia(String media) {
        this.media = media;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
