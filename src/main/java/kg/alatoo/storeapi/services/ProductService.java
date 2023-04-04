package kg.alatoo.storeapi.services;

import kg.alatoo.storeapi.domains.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();

    Product saveProduct(Product product);


    void updateProductById(UUID productId, Product product);

    Product findById(UUID productId);

    void deleteById(UUID productId);

    void patchProductById(UUID productId, Product product);
}
