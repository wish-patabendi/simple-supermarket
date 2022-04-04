package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public class TwoForOnePoundDiscount implements Discount {

    private final BigDecimal itemAmount;

    public TwoForOnePoundDiscount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Override
    public BigDecimal discount(List<Item> items) {
        // eligible items for discounts
        int noOfItemsForDiscount = items.size() / 2;

        // discount for 2 = 2 items price - £1
        BigDecimal discountForTwoItems = itemAmount.multiply(BigDecimal.valueOf(2)).subtract(BigDecimal.valueOf(1.00));

        //discount for all eligible items
        return discountForTwoItems.multiply(BigDecimal.valueOf(noOfItemsForDiscount));
    }
}
