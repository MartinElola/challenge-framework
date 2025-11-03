# language: es
@AgregadoProductos
@AgregadoProductos_5
Característica: Agregar productos al carrito y eliminarlos
    Se agregan productos al carrito y se eliminan productos del mismo.


  Antecedentes: Login con 'STANDARD_USER'
    Dada la pagina de login
    Cuando el usuario 'STANDARD_USER' ingresa sus datos de ingreso
    Entonces el usuario hace click en el boton de login

    Escenario: Agregar productos al carrito y eliminarlos
        Dada la pagina de inventario de productos
        Cuando el usuario agrega 4 productos al carrito
          Y el usuario elimina 1 productos del carrito
        Entonces el usuario debe tener 3 productos en el carrito