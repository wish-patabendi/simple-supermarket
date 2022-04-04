package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Comparator.naturalOrder;

public class BuyThreeForPriceOfTwoDiscount implements Discount {

    @Override
    public BigDecimal discount(List<Item> items) {
        // eligible items for discounts
        int noOfItemsForDiscount = items.size() / 3;

        // discount for 3 = lowest price item
        BigDecimal discountForTwoItems = items.stream().map(Item::price).min(naturalOrder()).get();

        // calculate discount for all eligible items
        return discountForTwoItems.multiply(BigDecimal.valueOf(noOfItemsForDiscount));
    }
}
