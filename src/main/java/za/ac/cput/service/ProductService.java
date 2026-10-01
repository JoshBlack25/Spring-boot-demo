package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Product;
import za.ac.cput.repository.ProductRepository;
import za.ac.cput.service.impl.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }


    @Override
    public List<Product> findByCustomerId(int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }
}
