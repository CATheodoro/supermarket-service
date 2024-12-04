package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.PromotionRepository;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.theodoro.supermarket_service.domains.enumerations.PromotionEnum.*;

@Service
public class CalculateDiscountService {

    private final PromotionRepository promotionRepository;
    private final PromotionService promotionService;

    @Autowired
    public CalculateDiscountService(PromotionRepository promotionRepository, PromotionService promotionService) {
        this.promotionRepository = promotionRepository;
        this.promotionService = promotionService;
    }

    public void calculatePromotionDiscount(Cart cart, CartItem cartItem) {
        float totalDiscount = 0f;
        int totalPrice = 0;

        cart.getItems().add(cartItem);
        for (CartItem item : cart.getItems()) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
            Optional<Promotion> promotionOpt = promotionRepository.findFirstByIdProductAndActive(item.getIdProduct(), true);
            //Optional<Promotion> promotionOpt = promotionService.findFindByIdWithWiremock(item.getIdProduct()); //Using Wiremock if you Want
            if (promotionOpt.isPresent()) {
                cartItem.setIdPromotion(promotionOpt.get().getId());
                totalDiscount += this.getDiscount(item, promotionOpt.get(), totalPrice);
            }
        }
        cart.setDiscount((int) totalDiscount);
        cart.setTotalPrice(totalPrice);
        cart.setFinalPrice((int) (totalPrice - totalDiscount));
    }

    private float getDiscount(CartItem item, Promotion promotion, int totalPrice) {
        if (promotion.getCode().equals(FLAT_PERCENT)) {
            return this.calculatePercentageDiscount(totalPrice, promotion.getAmount());
        }
        if (promotion.getCode().equals(BUY_X_GET_Y_FREE)){
            return this.calculateFreeQuantityDiscount(item.getQuantity(), promotion.getAmount(), promotion.getFreeQuantity());
        }
        if (promotion.getCode().equals(QTY_BASED_PRICE_OVERRIDE)){
            return this.calculateQuantityBasedPriceOverride(item.getQuantity(), promotion.getRequiredQuantity(), promotion.getPrice(), item.getUnitPrice());
        }
        return 0;
    }

    private Float calculatePercentageDiscount(Integer totalPrice, Float amount){
        return totalPrice * (amount / 100);
    }

    private Float calculateFreeQuantityDiscount(Integer quantity, Float amount, Integer freeQuantity){
        return (quantity / (amount + freeQuantity)) * freeQuantity;
    }

    private Integer calculateQuantityBasedPriceOverride(Integer quantity, Integer requiredQuantity, Integer price, Integer unitPrice){
        int eligibleSets = quantity / requiredQuantity;
        int remainingItems = quantity % requiredQuantity;
        return eligibleSets * price + remainingItems * unitPrice;
    }
}
