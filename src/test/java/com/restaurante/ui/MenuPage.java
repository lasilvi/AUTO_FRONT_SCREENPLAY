package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI locators for the menu page (/client/menu).
 * Displays the product catalog with categories, search,
 * and add-to-cart actions. Contains the floating cart button.
 */
public class MenuPage {

    // --- Header ---
    public static final Target PAGE_TITLE =
        Target.the("menu page title")
        .located(By.xpath("//h1[text()='Menú']"));

    public static final Target TABLE_NUMBER_LABEL =
        Target.the("current table number label")
        .located(By.xpath("//h1[text()='Menú']/following-sibling::p"));

    public static final Target BACK_TO_TABLES_BUTTON =
        Target.the("back arrow to table selection")
        .located(By.cssSelector("a[href='/client/table'] button"));

    public static final Target STATUS_LINK =
        Target.the("link to order status from menu")
        .located(By.cssSelector("a[href='/client/status'] button"));

    // --- Search ---
    public static final Target SEARCH_INPUT =
        Target.the("search input for dishes")
        .located(By.cssSelector("input[type='search'][placeholder='Buscar platos...']"));

    // --- Category tabs ---
    public static final Target TAB_ALL =
        Target.the("tab 'Todos' to show all products")
        .located(By.xpath("//button[@role='tab' and text()='Todos']"));

    public static final Target TAB_ENTRADAS =
        Target.the("tab 'Entradas'")
        .located(By.xpath("//button[@role='tab' and text()='Entradas']"));

    public static final Target TAB_PRINCIPALES =
        Target.the("tab 'Principales'")
        .located(By.xpath("//button[@role='tab' and text()='Principales']"));

    public static final Target TAB_POSTRES =
        Target.the("tab 'Postres'")
        .located(By.xpath("//button[@role='tab' and text()='Postres']"));

    public static final Target TAB_BEBIDAS =
        Target.the("tab 'Bebidas'")
        .located(By.xpath("//button[@role='tab' and text()='Bebidas']"));

    // --- Product cards ---
    public static final Target PRODUCT_CARDS =
        Target.the("all product cards in the menu")
        .located(By.cssSelector("[role='tabpanel'] .grid > div"));

    /**
     * Locates a product card by its visible name (h3 text).
     */
    public static Target productCardByName(String productName) {
        return Target.the("product card for '" + productName + "'")
            .located(By.xpath(
                "//div[@role='tabpanel']//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'rounded-2xl')]"
            ));
    }

    /**
     * Locates the 'Agregar' button for a specific product.
     */
    public static Target addButtonByProductName(String productName) {
        return Target.the("add button for product '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'flex items-center gap-3')]//button[contains(.,'Agregar')]"
            ));
    }

    /**
     * Locates the increase (+) button for a product already in the cart.
     */
    public static Target increaseButtonByProductName(String productName) {
        return Target.the("increase quantity button for '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'flex items-center gap-3')]//button[@aria-label='Agregar una unidad de " + productName + "']"
            ));
    }

    /**
     * Locates the decrease (-) button for a product already in the cart.
     */
    public static Target decreaseButtonByProductName(String productName) {
        return Target.the("decrease quantity button for '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'flex items-center gap-3')]//button[@aria-label='Quitar una unidad de " + productName + "']"
            ));
    }

    /**
     * Locates the displayed quantity for a product already in the cart.
     */
    public static Target quantityByProductName(String productName) {
        return Target.the("quantity display for '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'flex items-center gap-3')]//span[contains(@class,'min-w-6')]"
            ));
    }

    // --- Floating cart button ---
    public static final Target CART_BUTTON =
        Target.the("floating 'Comprar' cart button")
        .located(By.cssSelector("a[aria-label='Ir al carrito para comprar']"));

    public static final Target CART_BUTTON_LABEL =
        Target.the("cart button 'Comprar' text")
        .located(By.xpath("//a[@aria-label='Ir al carrito para comprar']//span[text()='Comprar']"));

    public static final Target CART_ITEM_COUNT_BADGE =
        Target.the("cart item count badge")
        .located(By.xpath("//a[@aria-label='Ir al carrito para comprar']//span[contains(@class,'bg-success')]"));

    // --- Empty state ---
    public static final Target EMPTY_RESULTS_MESSAGE =
        Target.the("no results found message")
        .located(By.xpath("//div[contains(@class,'p-6') and contains(text(),'No se encontraron platos')]"));
}
