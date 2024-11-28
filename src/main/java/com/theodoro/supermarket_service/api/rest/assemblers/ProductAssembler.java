package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.models.requests.ProductRequest;
import com.theodoro.supermarket_service.models.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler extends RepresentationModelAssemblerSupport<Product, ProductResponse> {

    public ProductAssembler(){
        super(ProductEndpoint.class, ProductResponse.class);
    }

    public Link buildSelfLink(String id) {
        return linkTo(methodOn(ProductEndpoint.class).findById(id)).withSelfRel();
    }

    @Override
    @NonNull
    public ProductResponse toModel(@NonNull Product entity) {
        final ProductResponse productResponse = new ProductResponse(entity);
        productResponse.add(this.buildSelfLink(entity.getId()));
        return productResponse;
    }

    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return product;
    }

    public Page<ProductResponse> toPageModel(Page<Product> products) {
        return products.map(this::toModel);
    }
}
