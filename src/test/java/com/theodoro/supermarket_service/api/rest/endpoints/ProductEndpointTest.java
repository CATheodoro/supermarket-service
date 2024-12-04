package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.ApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_RESOURCE_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_SELF_PATH;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

public class ProductEndpointTest extends ApplicationTests<ProductEndpointTest> {

    @Test
    @DisplayName("Should return ok when get product with exist id")
    void shouldReturnOkWhenGetProductWithExistId() throws Exception {

        final String uri = fromPath(PRODUCT_SELF_PATH).buildAndExpand("ID_PRODUCT_WATERMELON").toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ID_PRODUCT_WATERMELON"))
                .andExpect(jsonPath("$.name").value("Watermelon"))
                .andExpect(jsonPath("$.price").value(1000))
                .andExpect(jsonPath("$.stockQuantity").value(100))
                .andExpect(jsonPath("$.creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(PRODUCT_RESOURCE_PATH)));
    }

    @Test
    @DisplayName("Should return Not Found when get product with not exist id")
    void shouldReturnNotFoundWhenGetProductWithNotExistId() throws Exception {

        final String uri = fromPath(PRODUCT_SELF_PATH).buildAndExpand("NOT_EXIST_ID").toUriString();
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.messages").exists())
                .andExpect(jsonPath("$.errors.messages[0].code").value(PRODUCT_ID_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.errors.messages[0].message").value(PRODUCT_ID_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(uri)));
    }

    @Test
    @DisplayName("Should return ok get all product")
    void shouldReturnOkWhenGetAllProduct() throws Exception {

        final String uri = fromPath(PRODUCT_RESOURCE_PATH).toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value("ID_PRODUCT_WATERMELON"))
                .andExpect(jsonPath("$.content[0].name").value("Watermelon"))
                .andExpect(jsonPath("$.content[0].price").value(1000))
                .andExpect(jsonPath("$.content[0].stockQuantity").value(100))
                .andExpect(jsonPath("$.content[0].creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$.content[0].links[0].href").value(containsString(PRODUCT_RESOURCE_PATH)))

                .andExpect(jsonPath("$.content[1].id").value("ID_PRODUCT_BANANA"))
                .andExpect(jsonPath("$.content[1].name").value("Banana"))
                .andExpect(jsonPath("$.content[1].price").value(5999))
                .andExpect(jsonPath("$.content[1].stockQuantity").value(50))
                .andExpect(jsonPath("$.content[1].creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$.content[1].links[0].href").value(containsString(PRODUCT_RESOURCE_PATH)));
    }

    @Test
    @DisplayName("Should return created when post product")
    void shouldReturnCreatedWhenPostProduct() throws Exception {

        final String uri = fromPath(PRODUCT_RESOURCE_PATH).toUriString();
        String content = super.getScenarioBody("shouldReturnCreatedWhenPostProduct");
        MvcResult result = mockMvc.perform(post(uri)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(LOCATION, containsString(uri)))
                .andReturn();

        mockMvc.perform(get(Objects.requireNonNull(result.getResponse().getHeader(LOCATION))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DisplayName("Should return Internal Server Error when post product")
    void shouldReturnInternalServerErrorWhenPostProduct() throws Exception {

        final String uri = fromPath(PRODUCT_RESOURCE_PATH).toUriString();
        String content = super.getScenarioBody("shouldReturnInternalServerErrorWhenPostProduct");
        mockMvc.perform(post(uri)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errors.messages").exists())
                .andExpect(jsonPath("$.errors.messages[0].code").value(500))
                .andExpect(jsonPath("$.errors.messages[0].message").exists())
                .andExpect(jsonPath("$._links['self'].href").value(containsString(uri)));
    }
}
