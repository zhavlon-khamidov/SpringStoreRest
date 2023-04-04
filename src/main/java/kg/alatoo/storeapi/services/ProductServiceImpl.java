package kg.alatoo.storeapi.services;

import kg.alatoo.storeapi.domains.Product;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<UUID, Product> productData = new HashMap<>();

    public ProductServiceImpl() {
        Product prod1 = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 1")
                .description("Product 1 Description")
                .price(2.52)
                .build();
        Product prod2 = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 2")
                .description("Product 2 Description")
                .price(12.2)
                .build();
        Product prod3 = Product.builder()
                .id(UUID.randomUUID())
                .name("Product 3")
                .description("Product 3 Description")
                .price(2.52)
                .build();

        productData.put(prod1.getId(), prod1);
        productData.put(prod2.getId(), prod2);
        productData.put(prod3.getId(), prod3);

    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productData.values());
    }

    @Override
    public Product saveProduct(Product product) {
        Product saved = Product.builder()
                .id(UUID.randomUUID())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        productData.put(saved.getId(), saved);
        return saved;
    }

    @Override
    public void updateProductById(UUID productId, Product product) {
        Product existed = productData.get(productId);
        existed.setName(product.getName());
        existed.setDescription(product.getDescription());
        existed.setPrice(product.getPrice());
    }

    @Override
    public Product findById(UUID productId) {
        return productData.get(productId);
    }

    @Override
    public void deleteById(UUID productId) {
        productData.remove(productId);
    }

    @Override
    public void patchProductById(UUID productId, Product product) {
        Product existed = productData.get(productId);
        if (StringUtils.hasText(product.getName())) {
            existed.setName(product.getName());
        }
        if (StringUtils.hasText(product.getDescription())) {
            existed.setDescription(product.getDescription());
        }
        if (Objects.nonNull(product.getPrice())) {
            existed.setPrice(product.getPrice());
        }
    }

}
