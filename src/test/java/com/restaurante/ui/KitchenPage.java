package com.restaurante.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UI locators for the kitchen pages:
 * - Kitchen Login (/kitchen): PIN-based authentication
 * - Kitchen Board (/kitchen/board): Kanban-style order management
 */
public class KitchenPage {

    // ==================== KITCHEN LOGIN (/kitchen) ====================

    public static final Target LOGIN_TITLE =
        Target.the("kitchen login title 'Panel de cocina'")
        .located(By.xpath("//h1[text()='Panel de cocina']"));

    public static final Target PIN_INPUT =
        Target.the("kitchen PIN input field")
        .located(By.cssSelector("input#kitchen-pass"));

    public static final Target LOGIN_BUTTON =
        Target.the("kitchen login submit button")
        .located(By.xpath("//button[@type='submit' and (contains(.,'Ingresar') or contains(.,'Verificando'))]"));

    public static final Target PIN_HINT =
        Target.the("demo PIN hint card")
        .located(By.xpath("//div[contains(@class,'bg-muted') and contains(text(),'Demo')]"));

    // ==================== KITCHEN BOARD (/kitchen/board) ====================

    // --- Header ---
    public static final Target BOARD_TITLE =
        Target.the("kitchen board title 'Bandeja de cocina'")
        .located(By.xpath("//h1[text()='Bandeja de cocina']"));

    public static final Target ACTIVE_ORDERS_COUNT =
        Target.the("active orders count subtitle")
        // El <p> está dentro del mismo contenedor que el <h1>, por eso se usa following::p[1]
        .located(By.xpath("//h1[text()='Bandeja de cocina']/following::p[1]"));

    public static final Target CLEAR_ALL_BUTTON =
        Target.the("'Limpiar todo' button to remove all orders")
        .located(By.xpath("//button[contains(text(),'Limpiar todo') or contains(text(),'Limpiando')]"));

    public static final Target REPORT_LINK =
        Target.the("link to orders report")
        // Selecciona el <a> directamente
        .located(By.cssSelector("a[href='/reports/orders']"));

    public static final Target CLIENT_LINK =
        Target.the("link to client view from kitchen")
        // Selecciona el botón dentro del <a>
        .located(By.cssSelector("a[href='/client/table'] > button"));

    public static final Target LOGOUT_BUTTON =
        Target.the("kitchen logout button")
        // Selecciona el botón que contiene el svg del logout y evita el botón de tema
        .located(By.xpath("//header//button[.//*[local-name()='svg' and contains(@class,'lucide-log-out')]]"));

    // --- Kanban columns ---
    public static final Target COLUMN_PENDING_TITLE =
        Target.the("'Pendiente' column header")
        .located(By.xpath("//h2[text()='Pendiente']"));

    public static final Target COLUMN_PENDING_COUNT =
        Target.the("pending column order count badge")
        .located(By.xpath("//h2[text()='Pendiente']/following-sibling::span"));

    public static final Target COLUMN_IN_PREPARATION_TITLE =
        Target.the("'En preparacion' column header")
        .located(By.xpath("//h2[text()='En preparacion']"));

    public static final Target COLUMN_IN_PREPARATION_COUNT =
        Target.the("in-preparation column order count badge")
        .located(By.xpath("//h2[text()='En preparacion']/following-sibling::span"));

    public static final Target COLUMN_READY_TITLE =
        Target.the("'Listo' column header")
        .located(By.xpath("//h2[text()='Listo']"));

    public static final Target COLUMN_READY_COUNT =
        Target.the("ready column order count badge")
        .located(By.xpath("//h2[text()='Listo']/following-sibling::span"));

    // --- Order cards (generic) ---
    public static final Target ALL_ORDER_CARDS =
        Target.the("all order cards on the kitchen board")
        .located(By.xpath("//main//div[contains(@class,'cursor-pointer') and contains(@class,'p-4')]"));

    /**
     * Locates an order card by its table number.
     */
      public static Target orderCardByTable(int numeroMesa) {
        return Target.the("order card for table " + numeroMesa)
                     .located(By.xpath("//h3[normalize-space()='Mesa " + numeroMesa + "']"));
    }

    /**
     * Locates an order card by its short ID prefix (first 8 chars shown as #xxxxxxxx).
     */
    public static Target orderCardByShortId(String shortId) {
        return Target.the("order card with short ID '#" + shortId + "'")
            .located(By.xpath(
                "//p[contains(.,'#" + shortId + "')]/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]"
            ));
    }

    public static Target startButtonByShortId(String shortId) {
        return Target.the("'Iniciar' button for order #" + shortId)
            .located(By.xpath(
                "//p[contains(.,'#" + shortId + "')]/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Iniciar']"
            ));
    }

    public static Target markReadyButtonByShortId(String shortId) {
        return Target.the("'Marcar listo' button for order #" + shortId)
            .located(By.xpath(
                "//p[contains(.,'#" + shortId + "')]/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Marcar listo']"
            ));
    }

    public static Target orderCardInReadyColumn(int numeroMesa) {
        return Target.the("Mesa " + numeroMesa + " in Listo column")
                     .located(By.xpath(
                         "//h2[normalize-space()='Listo']/parent::div/parent::div//h3[normalize-space()='Mesa " + numeroMesa + "']"
                     ));
    }

    // --- Order card action buttons ---

    /**
     * Locates the 'Iniciar' button (PENDING → IN_PREPARATION) on an order card for a given table.
     */
    public static Target startButtonByTable(int numeroMesa) {
        return Target.the("Iniciar button for table " + numeroMesa)
                     .located(By.xpath(
                         "//h3[normalize-space()='Mesa " + numeroMesa + "']/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Iniciar']"
                     ));
    }

    /**
     * Locates the 'Marcar listo' button (IN_PREPARATION → READY) on an order card for a given table.
     */
    public static Target markReadyButtonByTable(int numeroMesa) {
        return Target.the("Marcar listo button for table " + numeroMesa)
                     .located(By.xpath(
                         "//h3[normalize-space()='Mesa " + numeroMesa + "']/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Marcar listo']"
                     ));
    }

    /**
     * Locates the 'Volver' button (revert to previous status) on an order card for a given table.
     */
    public static Target revertButtonByTable(int tableNumber) {
        return Target.the("'Volver' button for table " + tableNumber)
            .located(By.xpath(
                "//h3[normalize-space()='Mesa " + tableNumber + "']/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Volver']"
            ));
    }

    /**
     * Locates the 'Eliminar' button on an order card for a given table.
     */
    public static Target deleteButtonByTable(int tableNumber) {
        return Target.the("'Eliminar' button for table " + tableNumber)
            .located(By.xpath(
                "//h3[normalize-space()='Mesa " + tableNumber + "']/ancestor::div[contains(@class,'rounded-2xl') and contains(@class,'p-4')]//button[normalize-space()='Eliminar']"
            ));
    }

    // --- Order detail dialog ---
    public static final Target DETAIL_DIALOG =
        Target.the("order detail dialog")
        .located(By.xpath("//div[@role='dialog']"));

    public static final Target DETAIL_DIALOG_TITLE =
        Target.the("order detail dialog title (Mesa X · Status)")
        .located(By.xpath("//div[@role='dialog']//h2"));

    public static final Target DETAIL_ORDER_ID =
        Target.the("full order ID in detail dialog")
        .located(By.xpath("//div[@role='dialog']//span[contains(text(),'ID completo')]/following-sibling::*"));

    public static final Target DETAIL_TABLE_NUMBER =
        Target.the("table number in detail dialog")
        .located(By.xpath("//div[@role='dialog']//p[span[text()='Mesa:']]"));

    public static final Target DETAIL_STATUS =
        Target.the("status text in detail dialog")
        .located(By.xpath("//div[@role='dialog']//p[span[text()='Estado:']]"));

    public static final Target DETAIL_ITEMS =
        Target.the("item rows in detail dialog")
        .located(By.xpath("//div[@role='dialog']//h4[text()='Items del pedido']/following-sibling::div//div[contains(@class,'rounded-xl')]"));

    // --- Empty board state ---
    public static final Target EMPTY_BOARD_MESSAGE =
        Target.the("empty board message 'No hay pedidos activos'")
        .located(By.xpath("//div[contains(text(),'No hay pedidos activos')]"));

    public static Target orderStatusByTable(int numeroMesa) {
        return Target.the("badge de estado de la mesa " + numeroMesa)
                     .located(By.xpath("//div[@data-table='" + numeroMesa + "']//span[contains(@class,'status-badge')]"));
    }
}
