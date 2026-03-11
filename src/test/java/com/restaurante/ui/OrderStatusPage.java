package com.restaurante.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI locators for the order status page (/client/status and /client/status/:orderId).
 * Allows looking up an order by ID and displays its current status
 * with a progress stepper (PENDING → IN_PREPARATION → READY).
 */
public class OrderStatusPage {

    // --- Header ---
    public static final Target PAGE_TITLE =
        Target.the("order status page title")
        .located(By.xpath("//h1[text()='Estado del pedido']"));

    public static final Target BACK_TO_TABLES_BUTTON =
        Target.the("back arrow to table selection")
        .located(By.cssSelector("a[href='/client/table'] button"));

    // --- Search form ---
    public static final Target ORDER_ID_INPUT =
        Target.the("order ID input field")
        .located(By.cssSelector("input[placeholder='Ingresa tu orderId']"));

    public static final Target SEARCH_BUTTON =
        Target.the("'Consultar' search button")
        .located(By.xpath("//button[text()='Consultar']"));

    // --- Order info badges ---
    public static final Target ORDER_ID_BADGE =
        Target.the("order ID badge (short)")
        .located(By.xpath("//span[contains(@class,'badge') and contains(text(),'Pedido')]"));

    public static final Target TABLE_ID_BADGE =
        Target.the("table ID badge")
        .located(By.xpath("//span[contains(text(),'Mesa')]"));

    public static final Target STATUS_BADGE =
        Target.the("current status badge")
        .located(By.xpath(
            "//span[contains(.,'Pendiente') or contains(.,'preparaci') or contains(.,'Listo')]"
        ));

    // --- Progress stepper ---
    public static final Target STEP_PENDING =
        Target.the("stepper step 'Pendiente'")
        .located(By.xpath("//p[text()='Pendiente']"));

    public static final Target STEP_IN_PREPARATION =
        Target.the("stepper step 'En preparacion'")
        .located(By.xpath("//p[text()='En preparacion']"));

    public static final Target STEP_READY =
        Target.the("stepper step 'Listo'")
        .located(By.xpath("//p[text()='Listo']"));

    /**
     * Locates the circular icon container for a given step by index (0-based).
     * When completed, it has class 'bg-accent'; otherwise 'bg-muted'.
     */
    public static Target stepIcon(int stepIndex) {
        return Target.the("step icon at index " + stepIndex)
            .located(By.xpath(
                "(//div[contains(@class,'flex') and contains(@class,'h-10') and contains(@class,'w-10') and contains(@class,'rounded-full')])[" + (stepIndex + 1) + "]"
            ));
    }

    // --- Progress bar ---
    public static final Target PROGRESS_BAR =
        Target.the("progress bar indicator")
        .located(By.xpath("//div[contains(@class,'bg-accent') and contains(@class,'h-0.5') and contains(@style,'width')]"));

    // --- Order detail items ---
    public static final Target ORDER_DETAIL_SECTION =
        Target.the("order detail section title")
        .located(By.xpath("//h3[text()='Detalle']"));

    public static final Target ORDER_ITEMS =
        Target.the("order item rows in detail section")
        .located(By.xpath("//h3[text()='Detalle']/following-sibling::div[contains(@class,'rounded-xl')]"));

    // --- States ---
    public static final Target EMPTY_STATE_MESSAGE =
        Target.the("empty state message when no orderId")
        .located(By.xpath("//div[contains(text(),'Ingresa un orderId para consultar')]"));

    public static final Target LOADING_STATE =
        Target.the("loading indicator while fetching order")
        .located(By.xpath("//*[contains(text(),'Consultando estado')]"));

    public static final Target ERROR_STATE =
        Target.the("error state when fetch fails")
        .located(By.xpath("//*[contains(text(),'No pudimos consultar el pedido')]"));
}
