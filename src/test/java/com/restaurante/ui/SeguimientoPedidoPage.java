package com.restaurante.ui;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SeguimientoPedidoPage {
    public static final Target CAMPO_NUMERO_PEDIDO =
        Target.the("campo para ingresar número de pedido")
        .located(By.id("numeroPedido"));

    public static final Target BOTON_CONSULTAR_ESTADO =
            Target.the("botón consultar estado")
            .located(By.id("consultarEstado"));

    public static final Target ESTADO_PEDIDO =
            Target.the("estado actual del pedido")
            .located(By.id("estadoPedido"));

    
}
