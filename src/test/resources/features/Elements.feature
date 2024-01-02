Feature: Automatización Demo QA Elements

  # Casos Text Box
  Scenario: Validar envío de datos mediante textbox en Demo QA
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Text Box
    Then debo completar los campos y dar clic en el botón Submit
    But los datos enviamos deben ser los mismos que muestran como resultado

  # Casos Check Box
  Scenario: Validar que cuando se seleccione el checkbox Home muestre todas las carpetas existentes
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Check Box
    Then debo darle clic en el checkbox Home
    And mostrará todas las carpetas existentes

  Scenario: Validar que cuando se seleccione el botón desplegable de Home, permita seleccionar el checkbox Desktop
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Check Box
    And actualizo la página
    Then debo dar clic en el botón desplegable de Home
    And seleccionaré el checkbox Desktop
    But pero validaré que se muestre las carpetas solamente de Desktop

  Scenario: Validar que cuando se seleccione el botón desplegable de Home, permita seleccionar el checkbox Documents
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Check Box
    And actualizo la página
    Then debo dar clic en el botón desplegable de Home
    And seleccionaré el checkbox Documents
    But pero validaré que se muestre las carpetas solamente de Documents

  Scenario: Validar que cuando se seleccione el botón desplegable de Home, permita seleccionar el checkbox Downloads
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Check Box
    And actualizo la página
    Then debo dar clic en el botón desplegable de Home
    And seleccionaré el checkbox Downloads
    But pero validaré que se muestre las carpetas solamente de Downloads

  # Casos Radio Button
  Scenario: Validar que cuando seleccione el radiobutton Yes muestre el mensaje del radiobutton seleccionado
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Radio Button
    Then debo seleccionar el radiobutton Yes
    And debe mostrarse un mensaje indicando el radiobutton Yes seleccionado

  Scenario: Validar que cuando seleccione el radiobutton Impressive muestre el mensaje del radiobutton seleccionado
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Radio Button
    Then debo seleccionar el radiobutton Impressive
    And debe mostrarse un mensaje indicando el radiobutton Impressive seleccionado