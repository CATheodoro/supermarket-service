package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.ApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_RESOURCE_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.ProductEndpoint.PRODUCT_SELF_PATH;
import static com.theodoro.supermarket_service.api.rest.endpoints.PromotionEndpoint.*;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PRODUCT_ID_NOT_FOUND;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.PROMOTION_ID_NOT_FOUND;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    @Test
    @DisplayName("Should return Not Found when get promotion with not exist id")
    void shouldReturnNotFoundWhenGetPromotionWithNotExistId() throws Exception {

        final String uri = fromPath(PROMOTION_SELF_PATH).buildAndExpand("NOT_EXIST_ID").toUriString();
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.messages").exists())
                .andExpect(jsonPath("$.errors.messages[0].code").value(PROMOTION_ID_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.errors.messages[0].message").value(PROMOTION_ID_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(uri)));
    }

    @Test
    @DisplayName("Should return ok get all promotion")
    void shouldReturnOkWhenGetAllPromotion() throws Exception {

        final String uri = fromPath(PROMOTION_RESOURCE_PATH).toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("ID_PROMOTION_BANANA"))
                .andExpect(jsonPath("$[0].code").value("BUY_X_GET_Y_FREE"))
                .andExpect(jsonPath("$[0].description").value("Promotion for banana BUY_X_GET_Y_FREE"))
                .andExpect(jsonPath("$[0].idProduct").value("ID_PRODUCT_BANANA"))
                .andExpect(jsonPath("$[0].active").value(true))
                .andExpect(jsonPath("$[0].requiredQuantity").value(2))
                .andExpect(jsonPath("$[0].price").doesNotExist())
                .andExpect(jsonPath("$[0].amount").doesNotExist())
                .andExpect(jsonPath("$[0].freeQuantity").value(1))
                .andExpect(jsonPath("$[0].creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$[0].links[0].href").value(containsString(PRODUCT_RESOURCE_PATH)))

                .andExpect(jsonPath("$[1].id").value("ID_PROMOTION_SODA"))
                .andExpect(jsonPath("$[1].code").value("QTY_BASED_PRICE_OVERRIDE"))
                .andExpect(jsonPath("$[1].description").value("Promotion for soda QTY_BASED_PRICE_OVERRIDE"))
                .andExpect(jsonPath("$[1].idProduct").value("ID_PRODUCT_SODA"))
                .andExpect(jsonPath("$[1].active").value(true))
                .andExpect(jsonPath("$[1].requiredQuantity").value(2))
                .andExpect(jsonPath("$[1].price").value(100))
                .andExpect(jsonPath("$[1].amount").doesNotExist())
                .andExpect(jsonPath("$[1].freeQuantity").doesNotExist())
                .andExpect(jsonPath("$[1].creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$[1].links[0].href").value(containsString(PRODUCT_RESOURCE_PATH)))

                .andExpect(jsonPath("$[2].id").value("ID_PROMOTION_RICE"))
                .andExpect(jsonPath("$[2].code").value("FLAT_PERCENT"))
                .andExpect(jsonPath("$[2].description").value("Promotion for soda FLAT_PERCENT"))
                .andExpect(jsonPath("$[2].idProduct").value("ID_PRODUCT_RICE"))
                .andExpect(jsonPath("$[2].active").value(true))
                .andExpect(jsonPath("$[2].requiredQuantity").doesNotExist())
                .andExpect(jsonPath("$[2].price").doesNotExist())
                .andExpect(jsonPath("$[2].amount").value(10))
                .andExpect(jsonPath("$[2].freeQuantity").doesNotExist())
                .andExpect(jsonPath("$[2].creationDate").value("2025-01-11T11:00:00-03:00"))
                .andExpect(jsonPath("$[2].links[0].href").value(containsString(PRODUCT_RESOURCE_PATH)));
    }

    @Test
    @DisplayName("Should return created when post promotion")
    void shouldReturnCreatedWhenPostPromotion() throws Exception {

        final String uri = fromPath(PROMOTION_RESOURCE_PATH).toUriString();
        String content = super.getScenarioBody("shouldReturnCreatedWhenPostPromotion");
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
    @DisplayName("Should return Internal Server Error when post promotion")
    void shouldReturnInternalServerErrorWhenPostPromotion() throws Exception {

        final String uri = fromPath(PRODUCT_RESOURCE_PATH).toUriString();
        String content = super.getScenarioBody("shouldReturnInternalServerErrorWhenPostPromotion");
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
