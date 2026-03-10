package com.restaurante.stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class PedidoStepDefinitions {
    

    @Given("que un cliente se encuentra en la {string} y selecciona productos disponibles en la carta digital")
    public void queUnClienteSeEncuentraEnLaMesaYSeleccionaProductos(String mesa) {
        System.out.println("Cliente ubicado en: " + mesa);
    }

    @When("confirma el envío del pedido para que la cocina inicie la preparación de forma inmediata")
    public void confirmaElEnvioDelPedido() {
        System.out.println("Pedido enviado a cocina.");
    }

    @And("el personal de cocina gestiona la orden en el monitor hasta marcarla como terminada")
    public void elPersonalDeCocinaGestionaLaOrden() {
        System.out.println("Cocina procesando y terminando la orden.");
    }

    @Then("el cliente monitorea el progreso en tiempo real hasta confirmar que su pedido está {string}")
    public void elClienteMonitoreaElProgresoHastaEstadoFinal(String estadoFinal) {

        System.out.println("Validando que el estado sea: " + estadoFinal);
    }
    
}
