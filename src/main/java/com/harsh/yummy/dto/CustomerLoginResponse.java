package com.harsh.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerLoginResponse(

        @JsonProperty("token")
        String token,

        @JsonProperty("message")
        String message
) {
}
