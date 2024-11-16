package com.harsh.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
        @NotNull(message = "Product name should be present")
        @NotEmpty(message = "Product name should be present")
        @NotBlank(message = "Product name should be present")
        @JsonProperty("name")
        String name,
        @NotNull(message = "Product price should be present")
        @JsonProperty("price")
        Float price
) {
}
