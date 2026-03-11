package com.restaurante.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.core.Serenity;
import com.restaurante.util.Urls;

import com.restaurante.questions.EstadoDelPedido;
import com.restaurante.tasks.AgregarProducto;
import com.restaurante.tasks.ConfirmarPedido;
import com.restaurante.tasks.ConsultarEstadoPedido;
import com.restaurante.tasks.IngresarALaCocina;
import com.restaurante.tasks.IrAlCarrito;
import com.restaurante.tasks.PrepararPedido;
import com.restaurante.tasks.SeleccionarMesa;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.containsString;

public class PedidoStepDefinitions {

    private int numeroMesa;
    private String orderId;

    @Given("que un cliente se encuentra en la {string} y selecciona productos disponibles en la carta digital")
    public void queUnClienteSeEncuentraEnLaMesaYSeleccionaProductos(String mesa) {
        numeroMesa = Integer.parseInt(mesa.replace("Mesa ", "").trim());

        OnStage.theActorInTheSpotlight().attemptsTo(
                Open.url(Urls.MESA),
                SeleccionarMesa.numero(numeroMesa),
                AgregarProducto.llamado("Pizza Margherita")
        );
    }

    @When("confirma el envío del pedido para que la cocina inicie la preparación de forma inmediata")
    public void confirmaElEnvioDelPedido() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IrAlCarrito.ahora(),
                ConfirmarPedido.ahora()
        );
        // Extraer orderId directamente de la URL: /client/confirm/{orderId}
        // Más fiable que leer el DOM (evita problemas de animación en la ConfirmationPage)
        String currentUrl = Serenity.getDriver().getCurrentUrl();
        orderId = currentUrl.substring(currentUrl.lastIndexOf('/') + 1);
    }

    @And("el personal de cocina gestiona la orden en el monitor hasta marcarla como terminada")
    public void elPersonalDeCocinaGestionaLaOrden() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IngresarALaCocina.conPin("cocina123")
        );
        OnStage.theActorInTheSpotlight().attemptsTo(
                PrepararPedido.laOrden(orderId)
        );
    }

    @Then("el cliente monitorea el progreso en tiempo real hasta confirmar que su pedido está {string}")
    public void elClienteMonitoreaElProgresoHastaEstadoFinal(String estadoFinal) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsultarEstadoPedido.conId(orderId)
        );
        OnStage.theActorInTheSpotlight().should(seeThat(EstadoDelPedido.actual(), containsString(estadoFinal)));
    }
}
