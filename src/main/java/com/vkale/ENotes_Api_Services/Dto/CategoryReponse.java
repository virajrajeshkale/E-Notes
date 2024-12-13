package com.vkale.ENotes_Api_Services.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReponse {

    private Integer id;

    private String name;

    private String description;

}