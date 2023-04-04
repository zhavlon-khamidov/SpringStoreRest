package kg.alatoo.storeapi.controllers;

import kg.alatoo.storeapi.domains.Product;
import kg.alatoo.storeapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PatchMapping("/api/v1/product/{productId}")
    public ResponseEntity updatePatchById(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        log.info("Patch product with id " + productId + ": "  + product);
        productService.patchProductById(productId,product);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/v1/product")
    public List<Product> allProducts() {
        return productService.findAll();
    }

    @GetMapping("/api/v1/product/{productId}")
    public Product getProductById(@PathVariable("productId") UUID productId) {
        return productService.findById(productId);
    }

    @PostMapping("/api/v1/product")
    public ResponseEntity createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location","/api/v1/product/" + savedProduct.getId());
        return new ResponseEntity(responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping("/api/v1/product/{productId}")
    public ResponseEntity updateProduct(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        productService.updateProductById(productId,product);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/api/v1/product/{productId}")
    public ResponseEntity deleteProduct(@PathVariable("productId") UUID productId) {
        productService.deleteById(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
