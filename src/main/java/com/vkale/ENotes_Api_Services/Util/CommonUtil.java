package com.vkale.ENotes_Api_Services.Util;

import com.vkale.ENotes_Api_Services.Handler.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {

    public static ResponseEntity<?> createBuildResponse(Object data, HttpStatus status) {

        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("succes")
                .message("succes")
                .data(data)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createBuildResponseMessage(String message, HttpStatus status) {

        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("succes")
                .message(message)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createErrorResponse(Object data, HttpStatus status) {

        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("failed")
                .message("failed")
                .data(data)
                .build();
        return response.create();
    }

    public static ResponseEntity<?> createErrorResponseMessage(String message, HttpStatus status) {

        GenericResponse response = GenericResponse.builder()
                .responseStatus(status)
                .status("failed")
                .message(message)
                .build();
        return response.create();
    }

}