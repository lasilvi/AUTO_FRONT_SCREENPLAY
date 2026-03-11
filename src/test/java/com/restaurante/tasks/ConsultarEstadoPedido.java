package com.restaurante.tasks;

import com.restaurante.ui.OrderStatusPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import com.restaurante.util.Urls;

public class ConsultarEstadoPedido implements Task {

    private final String orderId;

    public ConsultarEstadoPedido(String orderId) {
        this.orderId = orderId;
    }

    public static ConsultarEstadoPedido conId(String orderId) {
        return new ConsultarEstadoPedido(orderId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Navegar directamente a /client/status/:orderId evita depender del formulario de búsqueda
        actor.attemptsTo(
            Open.url(Urls.ESTADO + "/" + orderId),
            WaitUntil.the(OrderStatusPage.STATUS_BADGE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(15).seconds()
        );
    }
}
