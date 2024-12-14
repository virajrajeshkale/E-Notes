package com.vkale.ENotes_Api_Services.Exception;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

public class ResourceNotFoundException extends  Exception {

    public  ResourceNotFoundException(String message) {
        super(message);
    }
}
