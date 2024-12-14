package com.vkale.ENotes_Api_Services.Exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private Map<String, Object> error;

    public ValidationException(Map<String, Object> error) {
        super("Validation failed");
        this.error = error;
    }

    public Map<String, Object> getErrors() {
        return error;
    }

}