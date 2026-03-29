package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category){
        service.save(category);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        service.upadte(id, category);
        return ResponseEntity.ok().body(category);
    }
}
