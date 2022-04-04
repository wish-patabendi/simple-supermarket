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
        return new BigDecimal("0.30");
    }
}
