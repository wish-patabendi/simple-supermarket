package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;

public interface Discount {

    /**
     * List of discount items
     * @param items list of Item
     * @return none
     */
    BigDecimal discount(List<Item> items);
}
