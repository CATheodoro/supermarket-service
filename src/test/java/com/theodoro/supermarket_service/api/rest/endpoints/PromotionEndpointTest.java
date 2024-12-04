package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.ApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_RESOURCE_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.PromotionEndpoint.PROMOTION_RESOURCE_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.PromotionEndpoint.PROMOTION_SELF_PATH;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

public class PromotionEndpointTest extends ApplicationTests<PromotionEndpointTest> {

    @Test
    @DisplayName("Should return ok when get promotion with exist id")
    void shouldReturnOkWhenGetPromotionWithExistId() throws Exception {

        final String uri = fromPath(PROMOTION_SELF_PATH).buildAndExpand("ID_PROMOTION_BANANA").toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ID_PROMOTION_BANANA"))
                .andExpect(jsonPath("$.code").value("BUY_X_GET_Y_FREE"))
                .andExpect(jsonPath("$.description").value("Promotion for banana BUY_X_GET_Y_FREE"))
                .andExpect(jsonPath("$.idProduct").value("ID_PRODUCT_BANANA"))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.requiredQuantity").value(2))
                .andExpect(jsonPath("$.price").doesNotExist())
                .andExpect(jsonPath("$.amount").doesNotExist())
                .andExpect(jsonPath("$.freeQuantity").value(1))
                .andExpect(jsonPath("$.creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(PRODUCT_RESOURCE_PATH)));
    }
}
