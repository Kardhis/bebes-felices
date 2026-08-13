package com.bebesfelices.api.catalog;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Catálogo en memoria con productos introducidos editorialmente.
 * <p>
 * Sirve como implementación MVP de {@link ProductCatalog} mientras no haya
 * acceso a la Creators API de Amazon (ver
 * {@link com.bebesfelices.api.catalog.amazon.AmazonCatalogClient}). Ningún
 * producto de este catálogo incluye un enlace de afiliado: todavía no se ha
 * generado ni validado ningún enlace real de Amazon.
 */
@Component
public class ManualProductCatalog implements ProductCatalog {

    private final Map<String, Product> products = new LinkedHashMap<>();

    public ManualProductCatalog() {
        register(new Product(
                "juego-montessori-formas",
                ProductSource.MANUAL,
                null,
                null,
                "Juego Montessori de formas y encajes",
                "Piezas de madera para clasificar formas, colores y tamaños de forma autónoma.",
                3, 4,
                List.of("Juguetes educativos", "Montessori"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 20)
        ));
        register(new Product(
                "puzle-madera-animales",
                ProductSource.MANUAL,
                null,
                null,
                "Puzle de madera de animales",
                "Piezas grandes y resistentes pensadas para manos pequeñas.",
                3, 5,
                List.of("Juguetes educativos", "Puzles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 20)
        ));
        register(new Product(
                "bici-sin-pedales-basica",
                ProductSource.MANUAL,
                null,
                null,
                "Bicicleta sin pedales básica",
                "Cuadro ligero y sillín regulable para iniciarse en el equilibrio.",
                3, 5,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 22)
        ));
        register(new Product(
                "bici-chicco-red-bullet",
                ProductSource.MANUAL,
                null,
                null,
                "Chicco Red Bullet",
                "Bicicleta sin pedales para 2 a 5 años, con ruedas antipinchazos de 10 pulgadas, sillín y manillar ajustables y carga máxima de 25 kg.",
                2, 5,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "bici-kinderkraft-tove",
                ProductSource.MANUAL,
                null,
                null,
                "Kinderkraft TOVE",
                "Bicicleta sin pedales desde 18 meses, de 2 kg, con sillín regulable de 31 a 34,5 cm, ruedas EVA, límite de giro y carga máxima de 25 kg.",
                2, Integer.MAX_VALUE,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "bici-kinderkraft-fly-plus-2",
                ProductSource.MANUAL,
                null,
                null,
                "Kinderkraft FLY PLUS 2",
                "Bicicleta sin pedales desde 2 años, de 2,7-2,8 kg, con sillín regulable de 34 a 42 cm, ruedas de espuma de 30 cm, límite de giro y carga máxima de 35 kg.",
                2, Integer.MAX_VALUE,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "bici-kinderkraft-goswift",
                ProductSource.MANUAL,
                null,
                null,
                "Kinderkraft GOSWIFT",
                "Bicicleta sin pedales para 3 a 6 años, de 3,8 kg, con sillín regulable de 34 a 42 cm, ruedas inflables de 30 cm y cuadro de magnesio.",
                3, 6,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "bici-puky-lr-m",
                ProductSource.MANUAL,
                null,
                null,
                "PUKY LR M",
                "Bicicleta sin pedales desde 2 años, de 3,5 kg, para entrepiernas de 30 a 43 cm, con sillín y manillar ajustables, ruedas EVA sin aire y reposapiés.",
                2, Integer.MAX_VALUE,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "patinete-3-ruedas",
                ProductSource.MANUAL,
                null,
                null,
                "Patinete de 3 ruedas",
                "Base estable de tres ruedas pensada para el equilibrio inicial al aire libre.",
                3, 4,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 22)
        ));
        register(new Product(
                "torre-aprendizaje-madera",
                ProductSource.MANUAL,
                null,
                null,
                "Torre de aprendizaje de madera",
                "Plataforma segura y regulable en altura para participar en tareas de cocina.",
                3, 5,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 21)
        ));
        register(new Product(
                "set-vajilla-infantil",
                ProductSource.MANUAL,
                null,
                null,
                "Set de vajilla infantil irrompible",
                "Plato, cuenco y vaso de tamaño adaptado para practicar comer sin ayuda.",
                3, 5,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 21)
        ));
        register(new Product(
                "set-construccion-magnetico",
                ProductSource.MANUAL,
                null,
                null,
                "Set de construcción magnético",
                "Piezas magnéticas para construir estructuras y practicar lógica espacial.",
                4, 5,
                List.of("Juguetes educativos", "STEM"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 23)
        ));
        register(new Product(
                "juego-mesa-cooperativo",
                ProductSource.MANUAL,
                null,
                null,
                "Juego de mesa cooperativo",
                "Partidas cortas en las que todos los jugadores ganan o pierden juntos.",
                4, 5,
                List.of("Juguetes educativos", "Juegos de mesa"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 23)
        ));
        register(new Product(
                "kit-manualidades-natural",
                ProductSource.MANUAL,
                null,
                null,
                "Kit de manualidades con materiales naturales",
                "Piezas de madera, fieltro y cartón para crear sin depender de pantallas.",
                3, 5,
                List.of("Regalos", "Creatividad"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 24)
        ));
    }

    private void register(Product product) {
        products.put(product.id(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findByIds(List<String> ids) {
        return ids.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .toList();
    }
}
