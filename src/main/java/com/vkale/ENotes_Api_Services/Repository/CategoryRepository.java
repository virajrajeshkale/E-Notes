package com.vkale.ENotes_Api_Services.Repository;

import com.vkale.ENotes_Api_Services.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}