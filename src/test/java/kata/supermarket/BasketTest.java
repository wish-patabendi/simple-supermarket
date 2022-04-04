package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    @DisplayName("basket buy two for one pound")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketBuyTwoForOnePoundTotal(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        basket.addDiscount(new TwoForOnePoundDiscount(new BigDecimal("0.65")));
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketBuyTwoForOnePoundTotal() {
        return Stream.of(
                twoForOnePoundTwoItems(),
                twoForOnePoundThreeItems(),
                twoForOnePoundFiveItems()
        );
    }

    @DisplayName("buy one get one free")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketBuyOneGetOneFreeTotal(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        basket.addDiscount(new BuyOneGetOneFreeDiscount(new BigDecimal("0.65")));
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketBuyOneGetOneFreeTotal() {
        return Stream.of(
                buyOneGetOneFreeTwoItems(),
                buyOneGetOneFreeFiveItems()
        );
    }

    @DisplayName("buy three for price of two")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketBuyThreeForPriceOfTwoTotal(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        basket.addDiscount(new BuyThreeForPriceOfTwoDiscount());
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketBuyThreeForPriceOfTwoTotal() {
        return Stream.of(
                threeForPriceOfTwoThreeItems(),
                threeForPriceOfTwoThreeMultipleItems()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments twoForOnePoundTwoItems() {
        return Arguments.of("two for one pound two items", "1.00",
                Arrays.asList(aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments twoForOnePoundThreeItems() {
        return Arguments.of("two for one pound two items three items", "1.65",
                Arrays.asList(aCanOfBeans(), aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments twoForOnePoundFiveItems() {
        return Arguments.of("two for one pound two items five items", "2.65",
                Arrays.asList(aCanOfBeans(), aCanOfBeans(), aCanOfBeans(), aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments buyOneGetOneFreeTwoItems() {
        return Arguments.of("buy one get one free two items", "0.65",
                Arrays.asList(aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments buyOneGetOneFreeFiveItems() {
        return Arguments.of("buy one get one free five items", "1.95",
                Arrays.asList(aCanOfBeans(), aCanOfBeans(), aCanOfBeans(), aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments threeForPriceOfTwoThreeItems() {
        return Arguments.of("buy three for two items, single product", "1.30",
                Arrays.asList(aCanOfBeans(), aCanOfBeans(), aCanOfBeans()));
    }

    private static Arguments threeForPriceOfTwoThreeMultipleItems() {
        return Arguments.of("buy three for two items, multiple products", "1.15",
                Arrays.asList(aCanOfBeans(), aCanOfCoke(), aCanOfCoke()));
    }

    private static Item aPintOfMilk() {
        return new Product("Milk", new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product("Digestive", new BigDecimal("1.55")).oneOf();
    }

    private static Item aCanOfBeans() {
        return new Product("Beans", new BigDecimal("0.65")).oneOf();
    }

    private static Item aCanOfCoke() {
        return new Product("Coke", new BigDecimal("0.50")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}