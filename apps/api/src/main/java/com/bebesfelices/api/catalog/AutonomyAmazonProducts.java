package com.bebesfelices.api.catalog;

import java.time.LocalDate;
import java.util.List;

/**
 * Productos contrastados en Amazon España para autonomía distinta de
 * torres de aprendizaje y vajilla: cubiertos y aprender a vestirse.
 */
final class AutonomyAmazonProducts {

    private static final String MARKETPLACE = "www.amazon.es";
    private static final LocalDate REVIEWED_AT = LocalDate.of(2026, 9, 2);

    private AutonomyAmazonProducts() {
    }

    static List<Product> all() {
        return List.of(
                product("cubiertos-twistshake-acero", "B0799JDZJY",
                        "Twistshake cubiertos de aprendizaje de acero",
                        "Tenedor, cuchillo y cuchara de acero inoxidable, sin BPA, a partir de 12 meses.",
                        1, 6, "Cubiertos infantiles"),
                product("cubiertos-mam-aprendizaje", "B0CMQSWWXR",
                        "MAM cubiertos de aprendizaje",
                        "Tenedor, cuchillo y cuchara con asas antideslizantes, para zurdos y diestros, a partir de 6 meses.",
                        1, 5, "Cubiertos infantiles"),
                product("cubiertos-wmf-animales", "B000XG3HIS",
                        "WMF Animales cubertería 4 piezas",
                        "Tenedor, cuchillo, cuchara y cuchara pequeña de acero Cromargan, aptos para lavavajillas.",
                        3, 10, "Cubiertos infantiles"),
                product("cubiertos-exzact-safari", "B09HQZ8T55",
                        "EXZACT cubertería infantil Safari",
                        "Seis piezas de acero: dos tenedores, dos cuchillos de seguridad y dos cucharas, a partir de 24 meses.",
                        2, 8, "Cubiertos infantiles"),
                product("cubiertos-lehoo-vehiculos", "B08ZK8P99H",
                        "Lehoo Castle cubiertos vehículos",
                        "Seis piezas de acero inoxidable 304 con mangos de vehículos, dos de cada: tenedor, cuchillo y cuchara, a partir de 3 años.",
                        3, 8, "Cubiertos infantiles"),

                product("vestir-melissa-habilidades", "B0015XWTW8",
                        "Melissa & Doug tablero de habilidades básicas",
                        "Oso de madera con seis prendas para practicar botones, cremallera, hebilla y cordones, a partir de 3 años.",
                        3, 6, "Aprender a vestirse"),
                product("vestir-melissa-cordones", "B002665T20",
                        "Melissa & Doug paneles de cordones",
                        "Cinco paneles de madera con forma de mascotas y cordones de colores para enhebrar, a partir de 3 años.",
                        3, 6, "Aprender a vestirse"),
                product("vestir-small-foot-cubo", "B000L7C7P8",
                        "Small Foot cubo de cierres",
                        "Cubo de tela con cremallera, botones, velcro, cordones, broches y correas, a partir de 12 meses.",
                        1, 5, "Aprender a vestirse"),
                product("vestir-melissa-disfraces", "B07P8NSTPG",
                        "Melissa & Doug disfraces magnéticos Mejores Amigos",
                        "Tablero para vestir figuras con prendas magnéticas, a partir de 3 años.",
                        3, 6, "Aprender a vestirse"),
                product("vestir-melissa-pestillos", "B0026ZPTYY",
                        "Melissa & Doug tablero de pestillos",
                        "Tablero de madera con pestillos, cierres y ventanas para practicar el gesto de abrir y cerrar, a partir de 3 años.",
                        3, 6, "Aprender a vestirse")
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
                List.of("Autonomía", subcategory),
                ProductStatus.ACTIVE,
                null,
                REVIEWED_AT
        );
    }
}
