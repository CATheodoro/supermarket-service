package com.theodoro.supermarket_service.api.rest.assemblers;

import com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint;
import com.theodoro.supermarket_service.domains.entities.Product;
import com.theodoro.supermarket_service.models.responses.ProductResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public ProductResponse toModel(@NonNull Product product) {
        final ProductResponse productResponse = new ProductResponse(product);
        productResponse.add(this.buildSelfLink(product.getId()));
        return productResponse;
    }

    public List<ProductResponse> toListModel(List<Product> products) {
        return products.stream()
                .map(this::toModel)
                .toList();
    }
}
