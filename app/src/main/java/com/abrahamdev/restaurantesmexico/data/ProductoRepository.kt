package com.abrahamdev.restaurantesmexico.data

object ProductoRepository {

    private val productos = listOf(

        // Restaurante 1: Cocina economica Lupita
        Producto(1, 1, "Molletes", 65.0, "Orden de 2 molletes con salsa picante.", "Comida"),
        Producto(2, 1, "Enchiladas verdes", 95.0, "Enchiladas con salsa verde.", "Comida"),
        Producto(3, 1, "Agua de horchata", 35.0, "Agua fresca de horchata.", "Bebida"),
        Producto(4, 1, "Refresco", 30.0, "Refresco de 355 ml.", "Bebida"),
        Producto(5, 1, "Arroz mexicano", 25.0, "Porción de arroz.", "Complemento"),
        Producto(6, 1, "Frijoles refritos", 25.0, "Porción de frijoles", "Complemento"),

        // Restaurante 2 "Casa Sabores"
        Producto(7, 2, "Sandwich panela", 95.0, "Delicioso sandwich de panela con verduras.", "Comida"),
        Producto(8, 2, "Chilaquiles pollo", 45.0, "Orden de chilaquiles con salsa verde y pollo", "Comida"),
        Producto(9, 2, "Jugo naranja", 30.0, "Jugo de naranja natural", "Bebida"),
        Producto(10, 2, "Refresco", 30.0, "Refresco de 355 ml.", "Bebida"),
        Producto(11, 2, "Arroz frito", 25.0, "Porción de arroz frito.", "Complemento"),
        Producto(12, 2, "Churro", 8.0, "Churro azucarado", "Complemento"),
        // Restaurante 3: Pizza Los Hermanos
        Producto(13, 3, "Pizza Margarita", 120.0, "Pizza de queso y tomate.", "Comida"),
        Producto(14, 3, "Pizza Pepperoni", 145.0, "Pizza con pepperoni.", "Comida"),
        Producto(15, 3, "Refresco chico", 30.0, "Refresco de 355 ml.", "Bebida"),
        Producto(15, 3, "Refresco grande", 60.0, "Refresco de 1 Litro.", "Bebida"),
        Producto(16, 3, "Pan de ajo", 40.0, "Porción de pan con ajo.", "Complemento"),
        Producto(17, 3, "Papas a la francesa", 50.0, "250g de papas fritas", "Complemento"),

        // Restaurante 4: Antojitos Reyna
        Producto(18, 4, "Hot dog con papas", 85.0, "Salchica de pavo con 150g de papas fritas.", "Comida"),
        Producto(19, 4, "Hamburguesa con papas", 90.0, "Carne de res con 150g de papas fritas.", "Comida"),
        Producto(20, 4, "Agua del dia", 35.0, "Un litro de agua del dia", "Bebida"),
        Producto(21, 4, "Refresco", 35.0, "Variedad de sabores de 355ml", "Bebida"),
        Producto(22, 4, "Rebanada de pastel", 55.0, "Pastel de chocolate amargo con relleno de crema", "Complemento"),
        Producto(23, 4, "Pay", 55.0, "Delicioso pay de queso.", "Complemento")

    )

    fun obtenerProductos(
        restauranteId: Int,
        categoria: String
    ): List<Producto> {

        return productos.filter { producto ->
            producto.restauranteId == restauranteId &&
                    producto.categoria == categoria
        }
    }

    fun obtenerProductoPorId(productoId: Int): Producto? {

        return productos.find { producto ->
            producto.id == productoId
        }
    }
}