package com.restaurante.tasks;

import com.restaurante.ui.OrderConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class ConfirmarPedido implements Task {

    public static ConfirmarPedido ahora() {
        return new ConfirmarPedido();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(OrderConfirmationPage.CONFIRM_ORDER_BUTTON),
            // Esperar a que la página de confirmación cargue antes de que se intente leer el orderId
            WaitUntil.the(OrderConfirmationPage.CONFIRMATION_TITLE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(15).seconds()
        );
    }
}
