Feature: Registro de usuario en el restaurante

  Scenario: Registro exitoso de un nuevo usuario
    Given el usuario se encuentra en la página de registro del restaurante
    When el usuario diligencia el formulario con datos válidos
    Then el sistema confirma que el registro fue exitoso
