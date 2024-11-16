package com.harsh.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("pid")
        String pid,

        @JsonProperty("name")
        String name,
        @JsonProperty("price")
        Float price

) {
}
