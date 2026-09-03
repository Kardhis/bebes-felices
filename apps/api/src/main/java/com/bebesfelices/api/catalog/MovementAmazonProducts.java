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
                product("trepar-costway-plegable-3-14", "B0D22RL2C7",
                        "COSTWAY triángulo plegable 7 en 1",
                        "Escalera plegable con rampa reversible y arco-balancín, de 3 a 14 años y 60 kg por pieza.",
                        3, 14, "Estructuras de trepar"),
                product("trepar-relax4life-5en1", "B0CP91PGCH",
                        "RELAX4LIFE triángulo de escalada 5 en 1",
                        "Triángulo, arco y rampa reversible de haya, 50 kg y edad declarada de 1 a 5 años.",
                        1, 5, "Estructuras de trepar"),
                product("trepar-costway-gimnasio-6en1", "B0BXKTWCTL",
                        "COSTWAY gimnasio interior 6 en 1",
                        "Parque de haya con red, anillas, tobogán reversible y tienda, 115 × 108 × 127 cm y 120 kg en total.",
                        1, 6, "Estructuras de trepar"),
                product("trepar-feber-slide-plus", "B0C8B9SKXK",
                        "FEBER tobogán Slide Plus",
                        "Tobogán de jardín de 161 × 71 × 103 cm, rampa de 152 cm, de 2 a 7 años y 30 kg.",
                        2, 7, "Estructuras de trepar"),
                product("trepar-mamoi-muro", "B08K91RSXP",
                        "MAMOI muro de escalada interior",
                        "Cuatro paneles de contrachapado con 16 presas, de 3 a 7 años y carga de 120 kg, para fijar a la pared.",
                        3, 7, "Estructuras de trepar"),

                product("corre-injusa-winner-repsol", "B0F49D74P5",
                        "INJUSA moto Winner Repsol XL",
                        "Correpasillos XL de 99 × 39 × 61 cm, asiento a 41 cm y 50 kg, ficha desde 36 meses.",
                        3, 7, "Correpasillos"),
                product("corre-injusa-tundra-tornado", "B084WPF328",
                        "INJUSA moto Tundra Tornado",
                        "Moto correpasillos de 18 meses a 3 años, 30 kg, asiento a 33 cm y ruedas anchas.",
                        1, 3, "Correpasillos"),
                product("corre-feber-dream", "B0D8475K4D",
                        "FEBER Motofeber Dream",
                        "Moto correpasillos de 18 meses a 3 años, ruedas anchas, 62 cm de largo, interior o patio.",
                        1, 3, "Correpasillos"),
                product("corre-molto-cross-premium", "B0D817QCB4",
                        "MOLTO Cross Premium",
                        "Moto correpasillos de 18 meses a 5 años, 30 kg, asiento a 33 cm, para varios terrenos.",
                        1, 5, "Correpasillos"),
                product("corre-little-tikes-cozy-coupe", "B01LY451EC",
                        "Little Tikes Cozy Coupe",
                        "Coche correpasillos con techo y claxon, de 18 meses a 5 años, pies al suelo.",
                        1, 5, "Correpasillos"),
                product("corre-feber-motofeber-2", "B009E5WTCY",
                        "FEBER Motofeber 2 Racing",
                        "Moto correpasillos de 70 × 36 × 54 cm, asiento a 33,5 cm, de 3 a 5 años, casco incluido.",
                        3, 5, "Correpasillos"),
                product("corre-jamara-fiat-500", "B07DQS3WNF",
                        "Jamara Fiat 500",
                        "Coche correpasillos con licencia Fiat, 60 × 27,5 × 38 cm, 23 kg, asa de adulto y claxon.",
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
