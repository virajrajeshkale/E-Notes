package com.vkale.ENotes_Api_Services.Handler;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class GenericResponse {

    private HttpStatus responseStatus;

    private String status;

    private String message;

    private Object data;

    public ResponseEntity<?>create()
    {
        Map<String,Object> map = new LinkedHashMap<>();

        map.put("Status",status);
        map.put("Message",message);

        if(!ObjectUtils.isEmpty(data))
        {
            map.put("data",data);
        }
        return  new ResponseEntity<>(map,responseStatus);
    }

}
