package com.theodoro.supermarket_service.api.rest.endpoints;

import com.theodoro.supermarket_service.ApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.theodoro.supermarket_service.api.rest.endpoints.CartEndpoint.CART_SELF_PATH;
import static com.theodoro.supermarket_service.domains.enumerations.ExceptionMessagesEnum.CART_ID_NOT_FOUND;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.util.UriComponentsBuilder.fromPath;

public class CartEndpointTest extends ApplicationTests<ProductEndpointTest> {

    @Test
    @DisplayName("Should return ok when get cart with exist id")
    void shouldReturnOkWhenGetCartWithExistId() throws Exception {

        final String uri = fromPath(CART_SELF_PATH).buildAndExpand("ID_CART_WITHOUT_CART_ITEM").toUriString();
        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("ID_CART_WITHOUT_CART_ITEM"))
                .andExpect(jsonPath("$.totalPrice").value(0))
                .andExpect(jsonPath("$.discount").value(0))
                .andExpect(jsonPath("$.finalPrice").value(0))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(uri)));
    }

    @Test
    @DisplayName("Should return Not Found when get product with not exist id")
    void shouldReturnNotFoundWhenGetCartWithNotExistId() throws Exception {

        final String uri = fromPath(CART_SELF_PATH).buildAndExpand("NOT_EXIST_ID").toUriString();
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors.messages").exists())
                .andExpect(jsonPath("$.errors.messages[0].code").value(CART_ID_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.errors.messages[0].message").value(CART_ID_NOT_FOUND.getMessage()))
                .andExpect(jsonPath("$._links['self'].href").value(containsString(uri)));
    }
}
