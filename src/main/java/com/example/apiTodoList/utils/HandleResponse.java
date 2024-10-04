package com.example.apiTodoList.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class HandleResponse {
    private Map<String, Object> response;
    private HttpStatus httpStatus;

    public HandleResponse(Object data,HttpStatus httpStatus) {
        this.response = new HashMap<>();
        response.put("timestamp", Instant.now());
        response.put("data", data);
        this.httpStatus = httpStatus;
    }


    public ResponseEntity<Map<String, Object>> run() {
        return new ResponseEntity<Map<String, Object>>(response, httpStatus);
    }

}

