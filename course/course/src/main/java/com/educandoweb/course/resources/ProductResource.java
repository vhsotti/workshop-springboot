package com.educandoweb.course.resources;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.projection.ProductNameProjection;
import com.educandoweb.course.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/pag")
    public ResponseEntity<Page<Product>> listar(
            @RequestParam(defaultValue = "0") int pag,
            @RequestParam(defaultValue = "2") int tam
    )
    {
        Page<Product> list = service.listarPaginado(pag, tam);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/names")
    public ResponseEntity<List<ProductNameProjection>> findNames() {
        List<ProductNameProjection> list = service.namesProducts();
        return ResponseEntity.ok().body(list);
    }
}
