package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI locators for the table selection page (/client/table).
 * Allows selecting a table to start an order, navigating to order status,
 * or accessing the kitchen panel.
 */
public class TableSelectionPage {

    // --- Page header ---
    public static final Target PAGE_TITLE =
        Target.the("page title 'Selecciona tu mesa'")
        .located(By.xpath("//h2[text()='Selecciona tu mesa']"));

    // --- Table grid ---
    public static final Target TABLE_GRID =
        Target.the("table selection grid")
        .located(By.cssSelector(".grid.grid-cols-3"));

    /**
     * Selects a specific table button by its number (1–12).
     * Each table is a motion.button rendered inside the grid.
     * The table number appears as text content inside a div with class 'text-4xl'.
     */
    public static Target tableButton(int tableNumber) {
        return Target.the("table button number " + tableNumber)
            .located(By.xpath(
                "//div[contains(@class,'grid')]//button[.//div[contains(@class,'text-4xl') and normalize-space(text())='" + tableNumber + "']]"
            ));
    }

    // --- Table status badges ---
    public static Target tableStatusBadge(int tableNumber) {
        return Target.the("status badge for table " + tableNumber)
            .located(By.xpath(
                "//div[contains(@class,'grid')]//button[.//div[normalize-space(text())='" + tableNumber + "']]//span[contains(@class,'status-dot')]/parent::span"
            ));
    }

    // --- Legend badges ---
    public static final Target BADGE_EMPTY =
        Target.the("legend badge 'Verde: vacia'")
        .located(By.xpath("//span[contains(text(),'Verde: vacia')]"));

    public static final Target BADGE_OCCUPIED =
        Target.the("legend badge 'Rojo: ocupada'")
        .located(By.xpath("//span[contains(text(),'Rojo: ocupada')]"));

    // --- Navigation links ---
    public static final Target LINK_ORDER_STATUS =
        Target.the("link to order status page")
        .located(By.cssSelector("a[href='/client/status']"));

    public static final Target BUTTON_ORDER_STATUS =
        Target.the("'Consultar pedido' button")
        .located(By.xpath("//button[contains(.,'Consultar pedido')]"));

    public static final Target LINK_KITCHEN =
        Target.the("link to kitchen login")
        .located(By.cssSelector("a[href='/kitchen']"));

    public static final Target BUTTON_KITCHEN_ACCESS =
        Target.the("'Acceso a cocina' button")
        .located(By.xpath("//button[contains(.,'Acceso a cocina')]"));
}
