package pages.actions;

import pages.screen.InventoryPageScreen;

import static base.SeleniumBuilder.Selenium;

public class InventoryPageActions extends InventoryPageScreen {

    private final String URL_INVENTARIO_SWAGLABS = "https://www.saucedemo.com/inventory.html";

    public void validarUrlInventario() {
        Selenium()
                .checkUrl(URL_INVENTARIO_SWAGLABS)
                .perform();
    }

    public void validarCargaTituloProductos() {
        Selenium()
                .waitForElementToAppear(lblTituloProductos, 30)
                .assertText(lblTituloProductos, "Products")
                .perform();
    }

    public void validarCargaListadoProductos() {
        Selenium()
                .waitForElementToAppear(lstProductos, 30)
                .waitForElementToBeEnabled(lstProductos, 30)
                .perform();
    }

    public void clickearAgregarCarrito(int cantidadProductos){
        for (int i = 0; i < cantidadProductos; i++) {
            Selenium()
                    .waitForElementToAppear(btnAgregarCarrito, 30)
                    .waitClick(btnAgregarCarrito, 15)
                    .perform();

            validarBotonRemoveCarrito(i + 1); // i + 1 porque el índice de la lista de productos comienza en 1.
        }
    }

    public void validarBotonRemoveCarrito(int indice) {
        Selenium()
                .waitForElementToAppear(btnQuitarCarrito(indice), 30)
                .waitForElementToBeEnabled(btnQuitarCarrito(indice), 30)
                .perform();
    }

    public void validarCantidadProductosCarrito(int cantidadProductos) {
        Selenium()
                .sleepSeconds(5)
                .perform();

        Selenium()
                .waitForElementToAppear(lblCantidadProductosCarrito, 30)
                .assertText(lblCantidadProductosCarrito, String.valueOf(cantidadProductos))
                .perform();
    }

    public void clickearBotonQuitarCarrito(int cantidadProductos) {
        for (int i = 0; i < cantidadProductos; i++) {
            Selenium()
                    .waitForElementToAppear(btnQuitarCarrito(i+1), 10)
                    .waitForElementToBeEnabled(btnQuitarCarrito(i+1), 10)
                    .click(btnQuitarCarrito(i+1))
                    .perform();
        }

    }


}
