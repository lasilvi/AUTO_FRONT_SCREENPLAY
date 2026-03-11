package com.restaurante.tasks;

import com.restaurante.ui.KitchenPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class PrepararPedido implements Task {

    private final String shortId;

    public PrepararPedido(String shortId) {
        this.shortId = shortId;
    }

    /**
     * @param orderId UUID completo del pedido (e.g. "d264b7be-5bb7-43d7-b4b3-c53b534e9a38")
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

        // Click "Iniciar" en la card correcta
        actor.attemptsTo(
            WaitUntil.the(KitchenPage.startButtonByShortId(shortId), WebElementStateMatchers.isClickable())
                    .forNoMoreThan(10).seconds(),
            Click.on(KitchenPage.startButtonByShortId(shortId))
        );

        // Click "Marcar listo" en la card correcta
        actor.attemptsTo(
            WaitUntil.the(KitchenPage.markReadyButtonByShortId(shortId), WebElementStateMatchers.isClickable())
                    .forNoMoreThan(10).seconds(),
            Click.on(KitchenPage.markReadyButtonByShortId(shortId))
        );
    }
}
