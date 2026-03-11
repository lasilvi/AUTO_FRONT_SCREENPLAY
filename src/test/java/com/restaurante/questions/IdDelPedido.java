package com.restaurante.questions;

import com.restaurante.ui.OrderConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class IdDelPedido implements Question<String> {

    public static IdDelPedido enPantalla() {
        return new IdDelPedido();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(OrderConfirmationPage.ORDER_ID_FULL).answeredBy(actor);
    }
}
