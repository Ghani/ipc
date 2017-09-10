package com.toptechsol.ipc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.toptechsol.ipc.model.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, String> {

	@Query("SELECT item from Item item "
			+ "WHERE "
			+ "item.serialNumber =:serialNumber "
			+ "AND item.category.id =:categoryId ")
	Item findBySerialNumberAndCategoryId(@Param("serialNumber") String serialNumber, @Param("categoryId") Integer nodeId);
}
