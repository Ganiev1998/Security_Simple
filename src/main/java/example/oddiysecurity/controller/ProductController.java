package example.oddiysecurity.controller;

import example.oddiysecurity.entity.Product;
import example.oddiysecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService service;
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id){
        return service.getById(id);
    }
    @GetMapping
    public List<Product> getALl(){
        return service.getAll();
    }
    @PostMapping
    public Product create(@RequestBody Product product){
        return service.create(product);
    }
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product product){
        return service.update(id,product);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
