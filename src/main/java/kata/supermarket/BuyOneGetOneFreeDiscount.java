package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class BuyOneGetOneFreeDiscount implements Discount {

    private final BigDecimal itemAmount;

    public BuyOneGetOneFreeDiscount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    public BigDecimal discount(List<Item> items) {
        // eligible items for discounts
        int noOfItemsForDiscount = items.size() / 2;

        // discount for 2 = 1 item price
        BigDecimal discountForTwoItems = itemAmount;

        // calculate discount for all eligible items
        return discountForTwoItems.multiply(BigDecimal.valueOf(noOfItemsForDiscount));
    }
}
