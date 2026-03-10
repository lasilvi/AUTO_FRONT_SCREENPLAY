package com.restaurante.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import io.cucumber.java.en.Then;

import com.restaurante.tasks.AgregarProducto;
import com.restaurante.tasks.ConfirmarPedido;
import com.restaurante.tasks.IrAlCarrito;
import com.restaurante.tasks.PrepararPedido;
import com.restaurante.tasks.SeleccionarMesa;

import static org.hamcrest.Matchers.containsString;
import com.restaurante.questions.EstadoDelPedido;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import io.cucumber.java.en.And;

public class PedidoStepDefinitions {
    

    @Given("que un cliente se encuentra en la {string} y selecciona productos disponibles en la carta digital")
    public void queUnClienteSeEncuentraEnLaMesaYSeleccionaProductos(String mesa) {
        int numeroMesa = Integer.parseInt(mesa);

        OnStage.theActorInTheSpotlight().attemptsTo(
                SeleccionarMesa.numero(numeroMesa),
                AgregarProducto.llamado("Hamburguesa")
        );
    }

    @When("confirma el envío del pedido para que la cocina inicie la preparación de forma inmediata")
    public void confirmaElEnvioDelPedido() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IrAlCarrito.ahora(),
                ConfirmarPedido.ahora()
        );
    }

    @And("el personal de cocina gestiona la orden en el monitor hasta marcarla como terminada")
    public void elPersonalDeCocinaGestionaLaOrden() {
        
         OnStage.theActorInTheSpotlight().attemptsTo(
                PrepararPedido.delaMesa(1)
        );
    }

    @Then("el cliente monitorea el progreso en tiempo real hasta confirmar que su pedido está {string}")
    public void elClienteMonitoreaElProgresoHastaEstadoFinal(String estadoFinal) {

        OnStage.theActorInTheSpotlight().should(seeThat(EstadoDelPedido.actual(), containsString(estadoFinal)));
    }
    
}
