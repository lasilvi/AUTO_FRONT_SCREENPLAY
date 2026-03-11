package com.restaurante.tasks;

import com.restaurante.ui.KitchenPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;

public class PrepararPedido implements Task {

    private final String shortId;

    public PrepararPedido(String shortId) {
        this.shortId = shortId;
    }

    /**
     * @param orderId UUID completo (e.g. "a63dfa87-729c-44b5-a7f3-6331aaccb9cb")
     *                Se usan los primeros 8 caracteres que aparecen en la card del board.
     */
    public static PrepararPedido laOrden(String orderId) {
        return new PrepararPedido(orderId.substring(0, 8));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // Espera a que la card específica de este pedido aparezca en el board
        actor.attemptsTo(
            WaitUntil.the(KitchenPage.orderCardByShortId(shortId), WebElementStateMatchers.isVisible())
                    .forNoMoreThan(20).seconds()
        );

        // Click "Iniciar": scroll al centro para evitar que el header fijo intercepte el click
        actor.attemptsTo(
            WaitUntil.the(KitchenPage.startButtonByShortId(shortId), WebElementStateMatchers.isClickable())
                    .forNoMoreThan(10).seconds()
        );
        scrollToCenter(actor, KitchenPage.startButtonByShortId(shortId));
        actor.attemptsTo(Click.on(KitchenPage.startButtonByShortId(shortId)));

        // Click "Marcar listo": ídem scroll antes del click
        actor.attemptsTo(
            WaitUntil.the(KitchenPage.markReadyButtonByShortId(shortId), WebElementStateMatchers.isClickable())
                    .forNoMoreThan(10).seconds()
        );
        scrollToCenter(actor, KitchenPage.markReadyButtonByShortId(shortId));
        actor.attemptsTo(Click.on(KitchenPage.markReadyButtonByShortId(shortId)));
    }

    /** Centra el elemento verticalmente en el viewport vía JS para esquivar el header fijo. */
    private <T extends Actor> void scrollToCenter(T actor, Target target) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", target.resolveFor(actor));
    }
}

