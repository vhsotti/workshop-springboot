package com.educandoweb.course.services;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public Category upadte(Long id, Category category){
        return repository.findById(id)
                .map(categoryFound -> {
                    this.updateData(categoryFound, category);
                    Category updated = repository.save(categoryFound);
                    return updated;
                })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private Category updateData(Category entity, Category obj){
        entity.setName(obj.getName());
        return entity;
    }
}
