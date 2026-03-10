package com.restaurante.tasks;

import com.restaurante.ui.OrderConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class ConfirmarPedido implements Task {

    public static ConfirmarPedido ahora() {
        return new ConfirmarPedido();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(OrderConfirmationPage.CONFIRM_ORDER_BUTTON)
        );
    }
}
