package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.ProductAssembler;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import com.theodoro.supermarket_service.models.responses.ProductResponse;
import com.theodoro.supermarket_service.models.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;

@RestController
public class ProductEndpoint {
    public static final String PRODUCT_RESOURCE_PATH = "/products";
    public static final String PRODUCT_SELF_PATH = PRODUCT_RESOURCE_PATH + "/{id}";

    private final ProductService productService;
    private final ProductAssembler productAssembler;

    @Autowired
    public ProductEndpoint(ProductService productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }


    @GetMapping(PRODUCT_SELF_PATH)
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") final String id) {
        final Product product = productService.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));

        return ResponseEntity.ok(productAssembler.toModel(product));
    }

}
