package com.toptechsol.ipc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("SELECT item from Item item "
			+ "WHERE "
			+ "item.id =:id "
			+ "AND item.category.id =:categoryId ")
	Item findByIdAndCategoryId(@Param("id") Long id, @Param("categoryId") String categoryId);
	
	@Query("SELECT item from Item item "
			+ "WHERE "
			+ "item.category.id =:categoryId ")
	List<Item> findByCategoryId(@Param("categoryId") String categoryId);
	
}
