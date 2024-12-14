package com.vkale.ENotes_Api_Services.Services.Impl;


import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.CategoryReponse;
import com.vkale.ENotes_Api_Services.Entity.Category;
import com.vkale.ENotes_Api_Services.Exception.ResourceNotFoundException;
import com.vkale.ENotes_Api_Services.Repository.CategoryRepository;
import com.vkale.ENotes_Api_Services.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());

        Category category = mapper.map(categoryDto, Category.class);

        if (ObjectUtils.isEmpty(category.getId())) {
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
        } else {
            updateCategory(category);
        }

        Category saveCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveCategory)) {
            return false;
        }
        return true;
    }

    private void updateCategory(Category category) {
        Optional<Category> findById = categoryRepo.findById(category.getId());
        if (findById.isPresent()) {
            Category existCategory = findById.get();
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedOn(existCategory.getCreatedOn());
            category.setIsDeleted(existCategory.getIsDeleted());

            category.setUpdatedBy(1);
            category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();

        return categoryDtoList;
    }

    @Override
    public List<CategoryReponse> getActiveCategory() {

        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryReponse> categoryList = categories.stream().map(cat -> mapper.map(cat, CategoryReponse.class))
                .toList();
        return categoryList;
    }

    @Override
    public CategoryDto getCategoryById(Integer id ) throws ResourceNotFoundException  {

      Category findByCategory = categoryRepo.findByIdAndIsDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Category Not Found for ID :"+id));

        if (!ObjectUtils.isEmpty(findByCategory))
        {
            return mapper.map(findByCategory, CategoryDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCatgeory = categoryRepo.findById(id);

        if (findByCatgeory.isPresent()) {
            Category category = findByCatgeory.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }

}