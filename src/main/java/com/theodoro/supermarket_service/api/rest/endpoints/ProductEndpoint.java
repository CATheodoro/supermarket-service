package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.api.rest.assemblers.ProductAssembler;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.domains.exceptions.NotFoundException;
import com.theodoro.supermarket_service.models.requests.ProductRequest;
import com.theodoro.supermarket_service.models.responses.ProductResponse;
import com.theodoro.supermarket_service.models.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @PostMapping(PRODUCT_RESOURCE_PATH)
    public ResponseEntity<URI> create(@RequestBody @Valid ProductRequest productRequest){
        Product product = this.productService.create(productAssembler.toEntity(productRequest));
        return ResponseEntity.created(productAssembler.buildSelfLink(product.getId()).toUri()).build();
    }

    @GetMapping(PRODUCT_RESOURCE_PATH)
    public ResponseEntity<Page<ProductResponse>> findAll(@PageableDefault(size = 20, page = 0, sort = "name", direction = Sort.Direction.DESC) Pageable page){
        Page<Product> products = this.productService.findAll(page);
        return ResponseEntity.ok(productAssembler.toPageModel(products));
    }


    @GetMapping(PRODUCT_SELF_PATH)
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") final String id) {
        Product product = this.productService.findById(id).orElseThrow(() -> new NotFoundException(PRODUCT_ID_NOT_FOUND));
        return ResponseEntity.ok(productAssembler.toModel(product));
    }

}
