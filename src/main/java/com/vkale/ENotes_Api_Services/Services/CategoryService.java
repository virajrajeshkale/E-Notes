package com.vkale.ENotes_Api_Services.Services;

import com.vkale.ENotes_Api_Services.Entity.Category;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(Category category);

    public List<Category> getAllCategory();

}
