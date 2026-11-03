package com.abrahamdev.restaurantesmexico.data

object ProductoRepository {

    private val productos = listOf(

        // ==========================================
        // Restaurante 1: Cocina económica Lupita
        // ==========================================
        // Comida (5 productos)
        Producto(1, 1, "Molletes", 65.0, "Orden de 2 molletes con salsa picante.", "Comida"),
        Producto(2, 1, "Enchiladas verdes", 95.0, "Enchiladas con salsa verde y crema.", "Comida"),
        Producto(3, 1, "Milanesa de res", 110.0, "Milanesa acompañada de ensalada y papas.", "Comida"),
        Producto(4, 1, "Torta ahogada", 75.0, "Torta tradicional bañada en salsa de chile de árbol.", "Comida"),
        Producto(5, 1, "Caldo de gallina", 90.0, "Caldo tradicional con verduras y pieza de pollo.", "Comida"),

        // Bebida (5 productos)
        Producto(6, 1, "Agua de horchata", 35.0, "Agua fresca de horchata natural.", "Bebida"),
        Producto(7, 1, "Agua de jamaica", 35.0, "Agua fresca de flor de jamaica.", "Bebida"),
        Producto(8, 1, "Agua de limón con chía", 35.0, "Agua fresca de limón con semillas de chía.", "Bebida"),
        Producto(9, 1, "Café de olla", 30.0, "Café tradicional con canela y piloncillo.", "Bebida"),
        Producto(10, 1, "Refresco de lata", 30.0, "Refresco de 355 ml.", "Bebida"),

        // Complemento (5 productos)
        Producto(11, 1, "Arroz mexicano", 25.0, "Porción de arroz rojo.", "Complemento"),
        Producto(12, 1, "Frijoles refritos", 25.0, "Porción de frijoles con queso.", "Complemento"),
        Producto(13, 1, "Orden de tortillas", 15.0, "Media docena de tortillas de maíz.", "Complemento"),
        Producto(14, 1, "Nopales asados", 30.0, "Orden de nopales con queso panela.", "Complemento"),
        Producto(15, 1, "Salsa extra", 10.0, "Porción adicional de salsa molcajeteada.", "Complemento"),

        // ==========================================
        // Restaurante 2: Casa Sabores (Cafetería / Desayunos)
        // ==========================================
        // Comida (5 productos)
        Producto(16, 2, "Sandwich panela", 95.0, "Delicioso sandwich de panela con verduras.", "Comida"),
        Producto(17, 2, "Chilaquiles verdes con pollo", 115.0, "Orden de chilaquiles con salsa verde, pollo y crema.", "Comida"),
        Producto(18, 2, "Hot cakes tradicionales", 85.0, "Torre de 3 hot cakes con mantequilla y miel.", "Comida"),
        Producto(19, 2, "Huevos rancheros", 90.0, "Dos huevos estrellados sobre tortilla con salsa roja.", "Comida"),
        Producto(20, 2, "Omelette de champiñones", 105.0, "Omelette relleno de champiñones y queso gouda.", "Comida"),

        // Bebida (5 productos)
        Producto(21, 2, "Jugo de naranja", 40.0, "Jugo de naranja natural recién exprimido.", "Bebida"),
        Producto(22, 2, "Café americano", 35.0, "Café negro de grano.", "Bebida"),
        Producto(23, 2, "Capuchino", 55.0, "Café espresso con leche vaporizada y espuma.", "Bebida"),
        Producto(24, 2, "Te chai latte", 50.0, "Bebida especiada con té negro y leche.", "Bebida"),
        Producto(25, 2, "Malteada de fresa", 65.0, "Malteada cremosa con helado de fresa.", "Bebida"),

        // Complemento (5 productos)
        Producto(26, 2, "Fruta picada", 45.0, "Plato con mezcla de fruta de temporada.", "Complemento"),
        Producto(27, 2, "Yogurt con granola", 50.0, "Yogurt natural con granola y miel.", "Complemento"),
        Producto(28, 2, "Pan tostado con mermelada", 30.0, "2 piezas de pan tostado con mantequilla.", "Complemento"),
        Producto(29, 2, "Churro azucarado", 15.0, "Churro tradicional cubierto de azúcar y canela.", "Complemento"),
        Producto(30, 2, "Galleta de avena", 25.0, "Galleta casera de avena con pasas.", "Complemento"),

        // ==========================================
        // Restaurante 3: Pizza Los Hermanos
        // ==========================================
        // Comida (5 productos)
        Producto(31, 3, "Pizza Margarita", 120.0, "Pizza clásica con mozzarella, tomate y albahaca.", "Comida"),
        Producto(32, 3, "Pizza Pepperoni", 145.0, "Pizza con abundante pepperoni y queso.", "Comida"),
        Producto(33, 3, "Pizza Hawaiana", 140.0, "Pizza con jamón y piña.", "Comida"),
        Producto(34, 3, "Pizza Carnes Frías", 180.0, "Pizza con pepperoni, salchicha italiana y jamón.", "Comida"),
        Producto(35, 3, "Lasaña boloñesa", 135.0, "Lasaña horneada con carne y queso gratinado.", "Comida"),

        // Bebida (5 productos)
        Producto(36, 3, "Refresco chico", 30.0, "Refresco de 355 ml.", "Bebida"),
        Producto(37, 3, "Refresco grande", 60.0, "Refresco de 1.5 Litros para compartir.", "Bebida"),
        Producto(38, 3, "Agua embotellada", 25.0, "Botella de agua natural de 600 ml.", "Bebida"),
        Producto(39, 3, "Te frío", 35.0, "Té negro helado sabor limón.", "Bebida"),
        Producto(40, 3, "Cerveza nacional", 45.0, "Cerveza de botella de 355 ml.", "Bebida"),

        // Complemento (5 productos)
        Producto(41, 3, "Pan de ajo", 40.0, "Porción de pan crujiente con mantequilla de ajo.", "Complemento"),
        Producto(42, 3, "Papas a la francesa", 50.0, "250g de papas fritas sazonadas.", "Complemento"),
        Producto(43, 3, "Alitas BBQ (6 pzas)", 95.0, "Alitas de pollo bañadas en salsa BBQ.", "Complemento"),
        Producto(44, 3, "Boneless de pollo", 105.0, "Trozo de pechuga empanizada con aderezo ranch.", "Complemento"),
        Producto(45, 3, "Aros de cebolla", 55.0, "Orden de aros de cebolla empanizados y crujientes.", "Complemento"),

        // ==========================================
        // Restaurante 4: Antojitos Reyna (Comida rápida)
        // ==========================================
        // Comida (5 productos)
        Producto(46, 4, "Hot dog clásico", 65.0, "Salchicha de res con jitomate, cebolla y aderezos.", "Comida"),
        Producto(47, 4, "Hot dog con papas", 85.0, "Salchicha de pavo con 150g de papas fritas.", "Comida"),
        Producto(48, 4, "Hamburguesa sencilla", 75.0, "Carne de res, queso amarillo y vegetales frescos.", "Comida"),
        Producto(49, 4, "Hamburguesa con papas", 90.0, "Carne de res con 150g de papas fritas.", "Comida"),
        Producto(50, 4, "Salchipulgas", 70.0, "Orden de salchichas fritas cortadas con papas.", "Comida"),

        // Bebida (5 productos)
        Producto(51, 4, "Agua del día (Litro)", 35.0, "Un litro de agua fresca de sabor de la temporada.", "Bebida"),
        Producto(52, 4, "Refresco de lata", 35.0, "Variedad de sabores de 355 ml.", "Bebida"),
        Producto(53, 4, "Malteada de chocolate", 55.0, "Bebida fría batida con jarabe de chocolate.", "Bebida"),
        Producto(54, 4, "Licuado de plátano", 45.0, "Licuado de leche con plátano y un toque de canela.", "Bebida"),
        Producto(55, 4, "Agua mineralizada", 30.0, "Botella de agua mineral de 600 ml.", "Bebida"),

        // Complemento (5 productos)
        Producto(56, 4, "Rebanada de pastel", 55.0, "Pastel de chocolate amargo con relleno de crema.", "Complemento"),
        Producto(57, 4, "Pay de queso", 55.0, "Delicioso pay de queso horneado.", "Complemento"),
        Producto(58, 4, "Orden de papas extra", 45.0, "Porción adicional de papas fritas crujientes.", "Complemento"),
        Producto(59, 4, "Nuggets de pollo (6 pzas)", 65.0, "Nuggets doraditos con salsa catsup o agridulce.", "Complemento"),
        Producto(60, 4, "Helado sencillo", 30.0, "Vainilla, chocolate o fresa en vaso.", "Complemento")

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