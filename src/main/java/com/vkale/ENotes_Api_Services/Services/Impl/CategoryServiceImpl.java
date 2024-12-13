package com.vkale.ENotes_Api_Services.Services.Impl;


import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.CategoryReponse;
import com.vkale.ENotes_Api_Services.Entity.Category;
import com.vkale.ENotes_Api_Services.Repository.CategoryRepository;
import com.vkale.ENotes_Api_Services.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {


        //Traditional method for convert Categorydto to category
//        Category category = new Category();
//        category.setName(categoryDto.getName());
//        category.setDescription(categoryDto.getDescription());
//        category.setIsActive(categoryDto.getIsActive());

        Category category = modelMapper.map(categoryDto, Category.class);

        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedOn(new Date());
        Category saveCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveCategory)) {
            return false;
        }
        return true;


    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

        List<CategoryDto> categorylist = categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();

        return categorylist;
    }

    @Override
    public List<CategoryReponse> getActiveCategory() {

        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryReponse> categoryList = categories.stream().map(cat -> modelMapper.map(cat, CategoryReponse.class))
                .toList();
        return categoryList;
    }



}