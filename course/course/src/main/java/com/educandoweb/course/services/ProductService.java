package com.educandoweb.course.services;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.projection.ProductNameProjection;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.services.exceptions.DataIntegrityViolation;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;

    public Page<Product> listarPaginado(int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return repository.findAll(pageable);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<ProductNameProjection> namesProducts(){
        return repository.findAllProjectedBy();
    }

    public Product save(Product product){
       return repository.save(product);
    }

//    public Product update(Long id, Product obj){
//        try{
//            Product entity = repository.getReferenceById(id);
//            updateData(entity, obj);
//            return repository.save(entity);
//        } catch (ResourceNotFoundException e){
//           throw new ResourceNotFoundException(id);
//        }
//    }

    public Product update(Long id, Product obj){
        return repository.findById(id)
                .map(productFound -> {
                    updateData(productFound, obj);
                    Product updated = repository.save(productFound);
                    return updated;
                }).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateData(Product entity, Product obj){
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setImgUrl(obj.getImgUrl());
        entity.setStatus(obj.isStatus());
    }


    public void delete (Long id){
        try {
            repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            repository.deleteById(id);
            repository.flush();

        } catch (DataIntegrityViolationException e){
          inativar(id);
        }

    }

   
    public void inativar(Long id){
        Product product =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        product.setStatus(false);
        repository.save(product);
    }

}
