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

  # Casos Web Tables
  Scenario: Validar que permita registrar y que los datos registrados sean los correctos
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Web Tables
    Then debo dar clic en el botón Add
    And debe mostrarse un modal con campos para completar
    But verificar que los datos que se muestran en la fila de tabla sean los mismos que fueron enviados

  Scenario: Validar que permita buscar por Nombre y se extraigan todos los datos del registro
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Web Tables
    Then en el buscador escribo el nombre a buscar
    And extraigo todos los datos del registro para mostrarlo en consola

  Scenario: Validar que permita editar los datos del segundo registro y los datos nuevos sean los correctos
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Web Tables
    Then doy clic en el botón Edit del segundo registro para que me muestre el modal con los datos y edito todos los datos del registro
    And valido que los nuevos datos sean los correctos

  # Casos Links
  Scenario: Validar que al dar clic en el textlink Home redireccione al home de la web Demo QA
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then debo seleccionar el textlink Home para que me redireccione al home de Demo QA
    And debe mostrarse el logo de Demo QA

  Scenario: Validar que al dar clic en el textlink Created muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Created
    And me mostrará el mensaje correspondiente a Created

  Scenario: Validar que al dar clic en el textlink No Content muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink No Content
    And me mostrará el mensaje correspondiente a No Content

  Scenario: Validar que al dar clic en el textlink Moved muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Moved
    And me mostrará el mensaje correspondiente a Moved

  Scenario: Validar que al dar clic en el textlink Bad Request muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Bad Request
    And me mostrará el mensaje correspondiente a Bad Request

  Scenario: Validar que al dar clic en el textlink Unauthorized muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Unauthorized
    And me mostrará el mensaje correspondiente a Unauthorized

  Scenario: Validar que al dar clic en el textlink Forbidden muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Forbidden
    And me mostrará el mensaje correspondiente a Forbidden

  @CurrentTest
  Scenario: Validar que al dar clic en el textlink Not Found muestre mensaje según corresponda
    Given me encuentro en la página Demo QA Elements
    When selecciono la opción Links
    Then doy clic en el textlink Not Found
    And me mostrará el mensaje correspondiente a Not Found