package com.vkale.ENotes_Api_Services.Services;

import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.CategoryReponse;
import com.vkale.ENotes_Api_Services.Entity.Category;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(CategoryDto category);

    public List<CategoryDto> getAllCategory();

    public List<CategoryReponse> getActiveCategory();
}
