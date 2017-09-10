package com.toptechsol.ipc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Category;

@Repository("noderRepository")
public interface NodeRepository extends JpaRepository<Category, Integer> {
	 Category findById(Integer id);
}
