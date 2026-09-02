package com.bebesfelices.api.catalog;

import java.time.LocalDate;
import java.util.List;

/**
 * Productos contrastados en Amazon España para movimiento distinto de
 * bicicletas sin pedales y patinetes: trepar y correpasillos.
 */
final class MovementAmazonProducts {

    private static final String MARKETPLACE = "www.amazon.es";
    private static final LocalDate REVIEWED_AT = LocalDate.of(2026, 9, 2);

    private MovementAmazonProducts() {
    }

    static List<Product> all() {
        return List.of(
                product("trepar-mamoi-triangulo-blanco", "B09FFCDJ64",
                        "MAMOI triángulo de escalada con tobogán",
                        "Triángulo de madera con rampa reversible, altura regulable de 20 a 57 cm y carga de 50 kg.",
                        1, 6, "Estructuras de trepar"),
                product("trepar-aiyaplay-3en1", "B0FPFGSZ76",
                        "AIYAPLAY triángulo de escalada 3 en 1",
                        "Triángulo plegable de pino con rampa reversible, 50 kg y edad declarada de 18 a 48 meses.",
                        1, 4, "Estructuras de trepar"),
                product("trepar-little-tikes-gimnasio", "B000XRY40W",
                        "Little Tikes gimnasio de actividades Junior",
                        "Estructura de plástico para trepar, gatear y deslizarse, de 18 meses a 5 años, interior o jardín.",
                        1, 5, "Estructuras de trepar"),
                product("trepar-smoby-xs", "B00ERK3PVA",
                        "Smoby tobogán XS verde y rojo",
                        "Tobogán de 90 cm con peldaños antideslizantes, de 2 a 5 años, se guarda en dos piezas.",
                        2, 5, "Estructuras de trepar"),
                product("trepar-costway-7en1", "B0C5RFDNKT",
                        "COSTWAY set de escalada 7 en 1",
                        "Triángulo, arco y rampa de haya para trepar, deslizarse o balancearse, a partir de 1 año.",
                        1, 6, "Estructuras de trepar"),

                product("corre-injusa-winner-honda", "B09XRC63FZ",
                        "INJUSA moto Winner Honda",
                        "Correpasillos XL de 99 × 39 × 61 cm, a partir de 3 años, con ruedas anchas y asa de transporte.",
                        3, 7, "Correpasillos"),
                product("corre-injusa-tundra-tornado", "B084WPF328",
                        "INJUSA moto Tundra Tornado",
                        "Moto correpasillos de 18 meses a 3 años, 30 kg, asiento a 33 cm y ruedas anchas.",
                        1, 3, "Correpasillos"),
                product("corre-feber-bluey", "B0BXXXSXTG",
                        "FEBER Motofeber Bluey",
                        "Moto correpasillos de 18 meses a 3 años, ruedas anchas, interior o patio.",
                        1, 3, "Correpasillos"),
                product("corre-smoby-rookie", "B07PNJHWFC",
                        "Smoby Rookie de cuatro ruedas",
                        "Correpasillos de metal con cuatro ruedas silenciosas y maletero, a partir de 12 meses.",
                        1, 3, "Correpasillos"),
                product("corre-little-tikes-cozy-coupe", "B01LY451EC",
                        "Little Tikes Cozy Coupe",
                        "Coche correpasillos con techo y claxon, de 18 meses a 5 años, pies al suelo.",
                        1, 5, "Correpasillos")
        );
    }

    private static Product product(
            String id,
            String asin,
            String title,
            String description,
            int minAge,
            int maxAge,
            String subcategory
    ) {
        return new Product(
                id,
                ProductSource.MANUAL,
                asin,
                MARKETPLACE,
                title,
                description,
                minAge,
                maxAge,
                List.of("Movimiento", subcategory),
                ProductStatus.ACTIVE,
                null,
                REVIEWED_AT
        );
    }
}
