package com.restaurante.tasks;

import com.restaurante.ui.TableSelectionPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class SeleccionarMesa implements Task {

    private final int numeroMesa;

    public SeleccionarMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public static SeleccionarMesa numero(int numeroMesa) {
        return new SeleccionarMesa(numeroMesa);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(TableSelectionPage.tableButton(numeroMesa))
        );
    }
}
