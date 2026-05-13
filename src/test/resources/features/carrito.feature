Feature: Validar carrito de compras en SauceLabs

  Scenario Outline: Agregar productos al carrito
    Given estoy en la aplicacion de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido el carrito de compra actualice correctamente

    Examples:
      | PRODUCTO                | UNIDADES |
      | Sauce Labs Backpack     | 1        |
      | Sauce Labs Bolt - T-Shirt | 1      |
      | Sauce Labs Bike Light   | 2        |