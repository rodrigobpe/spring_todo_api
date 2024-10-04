package com.example.apiTodoList.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class HandleResponseError {
    private Map<String, Object> response;
    private HttpStatus httpStatus;

    public HandleResponseError(Object data,HttpStatus httpStatus) {
        this.response = new HashMap<>();
        response.put("timestamp", Instant.now());
        response.put("error", data);
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<Map<String, Object>> run() {
        return new ResponseEntity<Map<String, Object>>(response, httpStatus);
    }
}
