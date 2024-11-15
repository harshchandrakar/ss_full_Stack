package com.harsh.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("first_name")
        String name,

        @JsonProperty("email")
        String email
) {
}
