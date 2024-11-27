package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.ApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_RESOURCE_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_SELF_PATH;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

public class ProductEndpointTest extends ApplicationTests<ProductEndpointTest> {

    @Test
    @DisplayName("Should return ok When get product with exist id")
    void shouldReturnOkWhenGetProductWithExistId() throws Exception {

        final String uri = fromPath(PRODUCT_SELF_PATH).buildAndExpand("ID_PRODUCT_WATERMELON").toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ID_PRODUCT_WATERMELON"))
                .andExpect(jsonPath("$.name").value("Watermelon"))
                .andExpect(jsonPath("$.price").value(1000))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(PRODUCT_RESOURCE_PATH)));
    }
}
