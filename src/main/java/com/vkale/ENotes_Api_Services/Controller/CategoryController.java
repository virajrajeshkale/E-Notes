package com.vkale.ENotes_Api_Services.Controller;

import com.vkale.ENotes_Api_Services.Dto.CategoryDto;
import com.vkale.ENotes_Api_Services.Dto.CategoryReponse;
import com.vkale.ENotes_Api_Services.Exception.ResourceNotFoundException;
import com.vkale.ENotes_Api_Services.Services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {

        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) {
            return new ResponseEntity<>("saved success", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
//		String nm=null;
//		nm.toUpperCase();
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }

    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategory() {

        List<CategoryReponse> allCategory = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(allCategory)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(allCategory, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategortDetailsById(@PathVariable Integer id) throws Exception {

        //## TRADITIONAL WAY TO HANDLE EXCEPTION
        /*
        try {
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            if (ObjectUtils.isEmpty(categoryDto)) {
                return new ResponseEntity<>("Category not found with Id=" + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }catch (ResourceNotFoundException e)
        {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

         */

        CategoryDto categoryDto = categoryService.getCategoryById(id);
        if (ObjectUtils.isEmpty(categoryDto)) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
        Boolean deleted = categoryService.deleteCategory(id);
        if (deleted) {
            return new ResponseEntity<>("Category deleted success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}