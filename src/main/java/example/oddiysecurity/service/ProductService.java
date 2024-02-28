package example.oddiysecurity.service;

import example.oddiysecurity.entity.Product;
import example.oddiysecurity.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    public Product getById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public List<Product> getAll(){
        return repository.findAll();
    }
    public Product create(Product product){
        return repository.save(product);
    }
    public Product update(Long id,Product product){
        Product product1 = repository.getReferenceById(id);
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());
        return repository.save(product1);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
