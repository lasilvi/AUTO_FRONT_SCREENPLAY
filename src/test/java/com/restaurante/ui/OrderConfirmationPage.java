package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI locators for the order confirmation page (/client/confirm/:orderId).
 * Displayed after successfully placing an order.
 * Also covers the Cart page (/client/cart) where the order is reviewed
 * and submitted, since it is part of the confirmation flow.
 */
public class OrderConfirmationPage {

    // ==================== CART PAGE (/client/cart) ====================

    public static final Target CART_PAGE_TITLE =
        Target.the("cart page title 'Carrito'")
        .located(By.xpath("//h1[text()='Carrito']"));

    public static final Target CART_TABLE_LABEL =
        Target.the("cart page table number label")
        .located(By.xpath("//h1[text()='Carrito']/following-sibling::p"));

    public static final Target BACK_TO_MENU_BUTTON =
        Target.the("back arrow to menu from cart")
        .located(By.cssSelector("a[href='/client/menu'] button"));

    // --- Cart items ---
    public static final Target CART_ITEMS =
        Target.the("all cart item cards")
        .located(By.xpath("//main//div[contains(@class,'space-y-4')]//div[contains(@class,'p-4')]"));

    /**
     * Locates a cart item card by product name.
     */
    public static Target cartItemByName(String productName) {
        return Target.the("cart item for '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'p-4')]"
            ));
    }

    /**
     * Locates the remove (trash) button for a specific product in the cart.
     */
    public static Target removeItemButton(String productName) {
        return Target.the("remove button for '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/parent::div//button[@aria-label='Eliminar " + productName + "']"
            ));
    }

    /**
     * Locates the per-item notes textarea for a specific product.
     */
    public static Target itemNoteTextarea(String productName) {
        return Target.the("note textarea for product '" + productName + "'")
            .located(By.xpath(
                "//h3[normalize-space(text())='" + productName + "']/ancestor::div[contains(@class,'p-4')]//textarea"
            ));
    }

    // --- Order note ---
    public static final Target ORDER_NOTE_TEXTAREA =
        Target.the("general order notes textarea")
        .located(By.xpath(
            "//p[text()='Notas adicionales del pedido']/following-sibling::textarea"
        ));

    // --- Total and submit ---
    public static final Target TOTAL_AMOUNT =
        Target.the("order total amount")
        .located(By.xpath("//div[contains(@class,'fixed')]//span[contains(@class,'text-accent') and contains(@class,'text-3xl')]"));

    public static final Target CONFIRM_ORDER_BUTTON =
        Target.the("'Confirmar pedido' submit button")
        .located(By.xpath("//button[contains(.,'Confirmar pedido') or contains(.,'Enviando')]"));

    public static final Target CART_ERROR_MESSAGE =
        Target.the("cart error message")
        .located(By.xpath("//div[contains(@class,'text-danger')]"));

    public static final Target EMPTY_CART_MESSAGE =
        Target.the("empty cart message")
        .located(By.xpath("//div[contains(text(),'No hay productos en tu carrito')]"));

    // ==================== CONFIRMATION PAGE (/client/confirm/:orderId) ====================

    public static final Target SUCCESS_ICON =
        Target.the("success check circle icon")
        .located(By.cssSelector(".text-success svg"));

    public static final Target CONFIRMATION_TITLE =
        Target.the("'Pedido confirmado' title")
        .located(By.xpath("//h1[text()='Pedido confirmado']"));

    public static final Target CONFIRMATION_SUBTITLE =
        Target.the("confirmation subtitle with order id")
        .located(By.xpath("//h1[text()='Pedido confirmado']/following-sibling::p"));

    public static final Target ORDER_ID_FULL =
        Target.the("full order ID text")
        .located(By.xpath("//p[text()='ID completo del pedido']/following-sibling::p"));

    public static final Target VIEW_STATUS_BUTTON =
        Target.the("'Ver estado' button on confirmation page")
        .located(By.xpath("//button[contains(.,'Ver estado')]"));

    public static final Target BACK_TO_MENU_FROM_CONFIRMATION =
        Target.the("'Volver al menu' button on confirmation page")
        .located(By.xpath("//button[contains(.,'Volver al menu')]"));
}
