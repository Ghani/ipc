package com.toptechsol.ipc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, String> {
}
