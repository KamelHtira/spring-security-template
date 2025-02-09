package com.SpringBootApplicationTemplate.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Boolean success;
    private String message;
    private HttpStatusCode httpStatusCode;
    private T data;
}
