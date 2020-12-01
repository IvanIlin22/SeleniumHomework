Feature: Product operation

  Scenario: Add product to basket
    When we login to page and add product to basket
    Then products are in the basket
    And delete product from basket and quit
