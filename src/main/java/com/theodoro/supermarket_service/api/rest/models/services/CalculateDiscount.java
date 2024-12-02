package com.theodoro.supermarket_service.api.rest.models.services;

import com.theodoro.supermarket_service.api.rest.models.repositories.PromotionRepository;
import com.theodoro.supermarket_service.domains.entities.Cart;
import com.theodoro.supermarket_service.domains.entities.CartItem;
import com.theodoro.supermarket_service.domains.entities.Promotion;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculateDiscount {

    private final PromotionRepository promotionRepository;

    public CalculateDiscount(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public void calculatePromotionDiscount(Cart cart) {
        float totalDiscount = 0f;
        int totalPrice = 0;

        for (CartItem item : cart.getItems()) {
            Optional<Promotion> promotionOpt = promotionRepository.findFirstByIdProductAndActive(item.getIdProduct(), true);
            totalPrice += item.getUnitPrice() * item.getQuantity();
            if (promotionOpt.isPresent()) {
                totalDiscount += this.getDiscount(item, promotionOpt.get(), totalPrice);
            }
        }
        cart.setDiscount((int) totalDiscount);
        cart.setTotalPrice(totalPrice);
        cart.setFinalPrice((int) (totalPrice - totalDiscount));
    }

    private float getDiscount(CartItem item, Promotion promotion, int totalPrice) {
        if (promotion.getAmount() != null) {
            return this.calculatePercentageDiscount(totalPrice, promotion.getAmount());
        }
        if (promotion.getFreeQuantity() != null && promotion.getRequiredQuantity() != null){
            return this.calculateFreeQuantityDiscount(item.getQuantity(), promotion.getAmount(), promotion.getFreeQuantity());
        }
        if (promotion.getPrice() != null && promotion.getRequiredQuantity() != null){
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
