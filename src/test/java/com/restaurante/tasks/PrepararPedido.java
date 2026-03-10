package com.restaurante.tasks;

import com.restaurante.ui.KitchenPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class PrepararPedido implements Task {

    private final int numeroMesa;

    public PrepararPedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public static PrepararPedido delaMesa(int numeroMesa) {
        return new PrepararPedido(numeroMesa);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(KitchenPage.startButtonByTable(numeroMesa)),
            Click.on(KitchenPage.markReadyButtonByTable(numeroMesa))
        );
    }
}
