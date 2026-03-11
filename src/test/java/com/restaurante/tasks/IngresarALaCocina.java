package com.restaurante.tasks;

import com.restaurante.ui.KitchenPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import com.restaurante.util.Urls;

public class IngresarALaCocina implements Task {

    private final String pin;

    public IngresarALaCocina(String pin) {
        this.pin = pin;
    }

    public static IngresarALaCocina conPin(String pin) {
        return new IngresarALaCocina(pin);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Open.url(Urls.COCINA),
            Enter.theValue(pin).into(KitchenPage.PIN_INPUT),
            Click.on(KitchenPage.LOGIN_BUTTON),
            WaitUntil.the(KitchenPage.BOARD_TITLE, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
            WaitUntil.the(KitchenPage.ALL_ORDER_CARDS, WebElementStateMatchers.isPresent()).forNoMoreThan(20).seconds()
        );
    }
}
