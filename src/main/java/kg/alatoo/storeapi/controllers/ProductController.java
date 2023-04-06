package kg.alatoo.storeapi.controllers;

import kg.alatoo.storeapi.domains.Product;
import kg.alatoo.storeapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PatchMapping( "{productId}")
    public ResponseEntity<?> updatePatchById(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        log.debug("Patch product with id " + productId + ": "  + product);
        productService.patchProductById(productId,product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Product> allProducts() {
        System.out.println("Getting all product"); // don't use like this
        log.debug("Getting all product"); // use like this
        return productService.findAll();
    }

    @GetMapping( "{productId}")
    public Product getProductById(@PathVariable("productId") UUID productId) {
        return productService.findById(productId);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location","/api/v1/product/" + savedProduct.getId());
        return new ResponseEntity<>(responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        productService.updateProductById(productId,product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") UUID productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
