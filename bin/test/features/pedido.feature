Feature: Gestión Digital de Pedidos y Preparación en Cocina

Scenario Outline: Ciclo de vida completo del pedido desde la mesa hasta la confirmación de entrega
  Given que un cliente se encuentra en la "<mesa>" y selecciona productos disponibles en la carta digital
  When confirma el envío del pedido para que la cocina inicie la preparación de forma inmediata
  And el personal de cocina gestiona la orden en el monitor hasta marcarla como terminada
  Then el cliente monitorea el progreso en tiempo real hasta confirmar que su pedido está "<estado_final>"

Examples:
  | mesa   | estado_final |
  | Mesa 5 | Listo        |