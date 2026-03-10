package com.restaurante.questions;

import com.restaurante.ui.OrderStatusPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class EstadoDelPedido implements Question<String> {

    public static EstadoDelPedido actual() {
        return new EstadoDelPedido();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrderStatusPage.STATUS_BADGE).answeredBy(actor);
    }
}
