package com.example.backend.entities.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BookDto {

    @NotNull
    private int categoryId;

    @NotNull
    private List<Integer> authorIdList;

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Title must contains at least 3 characters.")
    private String title;

    @NotNull 
    private Double price;

    @NotNull
    @Size(min = 20, message = "Description must contains at least 3 characters.")
    private String description;
}
