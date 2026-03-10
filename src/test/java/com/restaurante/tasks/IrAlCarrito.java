package com.restaurante.tasks;

import com.restaurante.ui.MenuPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class IrAlCarrito implements Task {

    public static IrAlCarrito ahora() {
        return new IrAlCarrito();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(MenuPage.CART_BUTTON)
        );
    }
}
