package com.example.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle,Integer> {
    List<Rectangle> findByName(String name);
    List<Rectangle> findByRid(int rid);
    List<Rectangle> deleteByRid(int rid);
    List<Rectangle> findByColor(String color);
    
}