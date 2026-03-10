package com.restaurante.tasks;

import com.restaurante.ui.MenuPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class AgregarProducto implements Task {

    private final String nombreProducto;

    public AgregarProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public static AgregarProducto llamado(String nombreProducto) {
        return new AgregarProducto(nombreProducto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(MenuPage.addButtonByProductName(nombreProducto))
        );
    }
}
