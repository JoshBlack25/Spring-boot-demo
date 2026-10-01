package za.ac.cput.service.impl;

import za.ac.cput.domain.Product;

import java.util.List;

public interface IProductService extends IService<Product, Integer>{
    List<Product> findByCustomerId(int customerId);
}
