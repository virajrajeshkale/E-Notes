package com.vkale.ENotes_Api_Services.Services;

import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.CategoryResponse;
import com.vkale.ENotes_Api_Services.Exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategory();

    public List<CategoryResponse> getActiveCategory();

    public CategoryDto getCategoryById(Integer id) throws ResourceNotFoundException;

    public Boolean deleteCategory(Integer id);
}
