package com.carlosserrano.apirestfulservice.repositories;

import com.carlosserrano.apirestfulservice.model.Item;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
/**
 * JpaRepository has several methods like save, delete, count , findOne etc. 
 * These methods are implemented by 
 * the SimpleJpaRepository class so we do not need to implement these methods.
 * @author carlosserrano
 */ 

@Repository
public interface ItemRepository
        extends JpaRepository<Item, Long> {

    @Query(
    value="SELECT * FROM items AS i WHERE i.title LIKE %?1%",
            nativeQuery=true)
    public List<Item> getByTitle(String title);
 
}

