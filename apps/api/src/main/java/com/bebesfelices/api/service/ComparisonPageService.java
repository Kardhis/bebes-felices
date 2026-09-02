package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.dto.ComparisonPageResponse;
import com.bebesfelices.api.dto.PageStatus;
import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ComparisonPageService {

    public static final String BALANCE_BIKES_SLUG = "mejores-bicicletas-sin-pedales-3-anos";
    public static final String BOARD_GAMES_SLUG = "mejores-juegos-de-mesa-4-anos";
    public static final String SCOOTERS_SLUG = "mejores-patinetes-4-anos";
    public static final String TOWERS_SLUG = "mejores-torres-aprendizaje-4-anos";
    public static final String TABLEWARE_SLUG = "mejores-vajillas-infantiles-4-anos";
    public static final String SUSTAINABLE_SLUG = "mejores-regalos-sostenibles-4-anos";
    public static final String STEM_5_SLUG = "mejores-juguetes-stem-5-anos";
    public static final String MONTESSORI_3_SLUG = "mejores-juegos-montessori-3-anos";
    public static final String PUZZLES_3_SLUG = "mejores-puzles-3-anos";
    public static final String SCOOTERS_3_SLUG = "mejores-patinetes-3-anos";
    public static final String TOWERS_3_SLUG = "mejores-torres-aprendizaje-3-anos";
    public static final String TABLEWARE_3_SLUG = "mejores-vajillas-infantiles-3-anos";
    public static final String GIFTS_3_SLUG = "mejores-ideas-regalo-3-anos";
    public static final String SUSTAINABLE_3_SLUG = "mejores-regalos-sostenibles-3-anos";
    public static final String DURABLE_3_SLUG = "mejores-regalos-duraderos-3-anos";
    public static final String ARTS_NATURAL_3_SLUG = "mejores-manualidades-materiales-naturales-3-anos";
    public static final String MONTESSORI_WOOD_3_SLUG = "mejores-juegos-montessori-madera-3-anos";
    public static final String SYMBOLIC_3_SLUG = "mejores-juegos-simbolicos-3-anos";
    public static final String SENSORY_3_SLUG = "mejores-juguetes-sensoriales-3-anos";
    public static final String BALANCE_GUIDE_3_SLUG = "mejores-bicicletas-equilibrio-3-anos";
    public static final String SCOOTERS_TRIKES_3_SLUG = "mejores-patinetes-triciclos-3-anos";
    public static final String PIKLER_3_SLUG = "mejores-triangulos-pikler-3-anos";
    public static final String RIDE_ON_3_SLUG = "mejores-correpasillos-3-anos";
    public static final String CUTLERY_3_SLUG = "mejores-cubiertos-infantiles-3-anos";
    public static final String DRESSING_3_SLUG = "mejores-aprender-vestirse-3-anos";
    public static final String TOWERS_KITCHEN_3_SLUG = "mejores-torres-cocina-3-anos";
    public static final String TABLEWARE_DAILY_3_SLUG = "mejores-vajillas-mesa-diaria-3-anos";
    public static final String GIFT_SELECTION_3_SLUG = "mejores-seleccion-regalos-3-anos";
    public static final String CHOOSE_GIFT_3_SLUG = "mejores-elegir-regalo-edad-3-anos";
    public static final String DURABLE_4_SLUG = "mejores-regalos-duraderos-4-anos";
    public static final String DURABLE_5_SLUG = "mejores-regalos-duraderos-5-anos";
    public static final String ARTS_NATURAL_4_SLUG = "mejores-manualidades-materiales-naturales-4-anos";
    public static final String ARTS_NATURAL_5_SLUG = "mejores-manualidades-materiales-naturales-5-anos";
    public static final String MONTESSORI_WOOD_4_SLUG = "mejores-juegos-montessori-madera-4-anos";
    public static final String MONTESSORI_WOOD_5_SLUG = "mejores-juegos-montessori-madera-5-anos";
    public static final String MONTESSORI_4_SLUG = "mejores-juegos-montessori-4-anos";
    public static final String MONTESSORI_5_SLUG = "mejores-juegos-montessori-5-anos";
    public static final String PUZZLES_4_SLUG = "mejores-puzles-4-anos";
    public static final String PUZZLES_5_SLUG = "mejores-puzles-5-anos";
    public static final String SYMBOLIC_4_SLUG = "mejores-juegos-simbolicos-4-anos";
    public static final String SYMBOLIC_5_SLUG = "mejores-juegos-simbolicos-5-anos";
    public static final String SENSORY_4_SLUG = "mejores-juguetes-sensoriales-4-anos";
    public static final String SENSORY_5_SLUG = "mejores-juguetes-sensoriales-5-anos";
    public static final String BALANCE_GUIDE_4_SLUG = "mejores-bicicletas-equilibrio-4-anos";
    public static final String BALANCE_GUIDE_5_SLUG = "mejores-bicicletas-equilibrio-5-anos";
    public static final String SCOOTERS_TRIKES_4_SLUG = "mejores-patinetes-triciclos-4-anos";
    public static final String SCOOTERS_TRIKES_5_SLUG = "mejores-patinetes-triciclos-5-anos";
    public static final String TOWERS_KITCHEN_4_SLUG = "mejores-torres-cocina-4-anos";
    public static final String TOWERS_KITCHEN_5_SLUG = "mejores-torres-cocina-5-anos";
    public static final String TABLEWARE_DAILY_4_SLUG = "mejores-vajillas-mesa-diaria-4-anos";
    public static final String TABLEWARE_DAILY_5_SLUG = "mejores-vajillas-mesa-diaria-5-anos";
    public static final String GIFTS_4_SLUG = "mejores-ideas-regalo-4-anos";
    public static final String GIFTS_5_SLUG = "mejores-ideas-regalo-5-anos";
    public static final String GIFT_SELECTION_4_SLUG = "mejores-seleccion-regalos-4-anos";
    public static final String GIFT_SELECTION_5_SLUG = "mejores-seleccion-regalos-5-anos";
    public static final String CHOOSE_GIFT_4_SLUG = "mejores-elegir-regalo-edad-4-anos";
    public static final String CHOOSE_GIFT_5_SLUG = "mejores-elegir-regalo-edad-5-anos";
    public static final String BALANCE_BIKES_4_SLUG = "mejores-bicicletas-sin-pedales-4-anos";
    public static final String BALANCE_BIKES_5_SLUG = "mejores-bicicletas-sin-pedales-5-anos";
    public static final String STEM_4_SLUG = "mejores-juegos-stem-4-anos";
    public static final String BOARD_GAMES_5_SLUG = "mejores-juegos-de-mesa-5-anos";
    public static final String SCOOTERS_5_SLUG = "mejores-patinetes-5-anos";
    public static final String TOWERS_5_SLUG = "mejores-torres-aprendizaje-5-anos";
    public static final String TABLEWARE_5_SLUG = "mejores-vajillas-infantiles-5-anos";
    public static final String SUSTAINABLE_5_SLUG = "mejores-regalos-sostenibles-5-anos";
    private static final int BALANCE_BIKES_AGE = 3;
    private static final int BOARD_GAMES_AGE = 4;
    private static final int SCOOTERS_AGE = 4;
    private static final int TOWERS_AGE = 4;
    private static final int TABLEWARE_AGE = 4;
    private static final int SUSTAINABLE_AGE = 4;
    private static final int STEM_5_AGE = 5;
    private static final int AGE_3 = 3;
    private static final int AGE_4 = 4;
    private static final int AGE_5 = 5;
    private static final String BIKES_PUBLISHED_AT = "2026-08-13";
    private static final String BIKES_UPDATED_AT = "2026-08-13";
    private static final String BOARD_GAMES_PUBLISHED_AT = "2026-08-14";
    private static final String BOARD_GAMES_UPDATED_AT = "2026-08-14";
    private static final String SCOOTERS_PUBLISHED_AT = "2026-08-14";
    private static final String SCOOTERS_UPDATED_AT = "2026-08-14";
    private static final String FOUR_YEAR_PUBLISHED_AT = "2026-08-14";
    private static final String FOUR_YEAR_UPDATED_AT = "2026-08-14";
    private static final String STEM_5_PUBLISHED_AT = "2026-08-17";
    private static final String STEM_5_UPDATED_AT = "2026-08-17";
    private static final String THREE_YEAR_PUBLISHED_AT = "2026-08-26";
    private static final String THREE_YEAR_UPDATED_AT = "2026-08-26";
    private static final String FIVE_YEAR_PUBLISHED_AT = "2026-08-17";
    private static final String FIVE_YEAR_UPDATED_AT = "2026-08-17";
    private static final String BALANCE_BIKES_CANONICAL = "https://bebesfelices.es/comparativas/"
            + BALANCE_BIKES_SLUG + "/";
    private static final String BOARD_GAMES_CANONICAL = "https://bebesfelices.es/comparativas/"
            + BOARD_GAMES_SLUG + "/";
    private static final String SCOOTERS_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SCOOTERS_SLUG + "/";
    private static final String TOWERS_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TOWERS_SLUG + "/";
    private static final String TABLEWARE_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TABLEWARE_SLUG + "/";
    private static final String SUSTAINABLE_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SUSTAINABLE_SLUG + "/";
    private static final String STEM_5_CANONICAL = "https://bebesfelices.es/comparativas/"
            + STEM_5_SLUG + "/";
    private static final String MONTESSORI_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + MONTESSORI_3_SLUG + "/";
    private static final String PUZZLES_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + PUZZLES_3_SLUG + "/";
    private static final String SCOOTERS_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SCOOTERS_3_SLUG + "/";
    private static final String TOWERS_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TOWERS_3_SLUG + "/";
    private static final String TABLEWARE_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TABLEWARE_3_SLUG + "/";
    private static final String GIFTS_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + GIFTS_3_SLUG + "/";
    private static final String SUSTAINABLE_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SUSTAINABLE_3_SLUG + "/";
    private static final String DURABLE_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + DURABLE_3_SLUG + "/";
    private static final String ARTS_NATURAL_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + ARTS_NATURAL_3_SLUG + "/";
    private static final String MONTESSORI_WOOD_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + MONTESSORI_WOOD_3_SLUG + "/";
    private static final String SYMBOLIC_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SYMBOLIC_3_SLUG + "/";
    private static final String SENSORY_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SENSORY_3_SLUG + "/";
    private static final String BALANCE_GUIDE_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + BALANCE_GUIDE_3_SLUG + "/";
    private static final String SCOOTERS_TRIKES_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + SCOOTERS_TRIKES_3_SLUG + "/";
    private static final String PIKLER_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + PIKLER_3_SLUG + "/";
    private static final String RIDE_ON_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + RIDE_ON_3_SLUG + "/";
    private static final String CUTLERY_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + CUTLERY_3_SLUG + "/";
    private static final String DRESSING_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + DRESSING_3_SLUG + "/";
    private static final String TOWERS_KITCHEN_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TOWERS_KITCHEN_3_SLUG + "/";
    private static final String TABLEWARE_DAILY_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + TABLEWARE_DAILY_3_SLUG + "/";
    private static final String GIFT_SELECTION_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + GIFT_SELECTION_3_SLUG + "/";
    private static final String CHOOSE_GIFT_3_CANONICAL = "https://bebesfelices.es/comparativas/"
            + CHOOSE_GIFT_3_SLUG + "/";
    private static final String DURABLE_4_CANONICAL = "https://bebesfelices.es/comparativas/" + DURABLE_4_SLUG + "/";
    private static final String DURABLE_5_CANONICAL = "https://bebesfelices.es/comparativas/" + DURABLE_5_SLUG + "/";
    private static final String ARTS_NATURAL_4_CANONICAL = "https://bebesfelices.es/comparativas/" + ARTS_NATURAL_4_SLUG + "/";
    private static final String ARTS_NATURAL_5_CANONICAL = "https://bebesfelices.es/comparativas/" + ARTS_NATURAL_5_SLUG + "/";
    private static final String MONTESSORI_WOOD_4_CANONICAL = "https://bebesfelices.es/comparativas/" + MONTESSORI_WOOD_4_SLUG + "/";
    private static final String MONTESSORI_WOOD_5_CANONICAL = "https://bebesfelices.es/comparativas/" + MONTESSORI_WOOD_5_SLUG + "/";
    private static final String MONTESSORI_4_CANONICAL = "https://bebesfelices.es/comparativas/" + MONTESSORI_4_SLUG + "/";
    private static final String MONTESSORI_5_CANONICAL = "https://bebesfelices.es/comparativas/" + MONTESSORI_5_SLUG + "/";
    private static final String PUZZLES_4_CANONICAL = "https://bebesfelices.es/comparativas/" + PUZZLES_4_SLUG + "/";
    private static final String PUZZLES_5_CANONICAL = "https://bebesfelices.es/comparativas/" + PUZZLES_5_SLUG + "/";
    private static final String SYMBOLIC_4_CANONICAL = "https://bebesfelices.es/comparativas/" + SYMBOLIC_4_SLUG + "/";
    private static final String SYMBOLIC_5_CANONICAL = "https://bebesfelices.es/comparativas/" + SYMBOLIC_5_SLUG + "/";
    private static final String SENSORY_4_CANONICAL = "https://bebesfelices.es/comparativas/" + SENSORY_4_SLUG + "/";
    private static final String SENSORY_5_CANONICAL = "https://bebesfelices.es/comparativas/" + SENSORY_5_SLUG + "/";
    private static final String BALANCE_GUIDE_4_CANONICAL = "https://bebesfelices.es/comparativas/" + BALANCE_GUIDE_4_SLUG + "/";
    private static final String BALANCE_GUIDE_5_CANONICAL = "https://bebesfelices.es/comparativas/" + BALANCE_GUIDE_5_SLUG + "/";
    private static final String SCOOTERS_TRIKES_4_CANONICAL = "https://bebesfelices.es/comparativas/" + SCOOTERS_TRIKES_4_SLUG + "/";
    private static final String SCOOTERS_TRIKES_5_CANONICAL = "https://bebesfelices.es/comparativas/" + SCOOTERS_TRIKES_5_SLUG + "/";
    private static final String TOWERS_KITCHEN_4_CANONICAL = "https://bebesfelices.es/comparativas/" + TOWERS_KITCHEN_4_SLUG + "/";
    private static final String TOWERS_KITCHEN_5_CANONICAL = "https://bebesfelices.es/comparativas/" + TOWERS_KITCHEN_5_SLUG + "/";
    private static final String TABLEWARE_DAILY_4_CANONICAL = "https://bebesfelices.es/comparativas/" + TABLEWARE_DAILY_4_SLUG + "/";
    private static final String TABLEWARE_DAILY_5_CANONICAL = "https://bebesfelices.es/comparativas/" + TABLEWARE_DAILY_5_SLUG + "/";
    private static final String GIFTS_4_CANONICAL = "https://bebesfelices.es/comparativas/" + GIFTS_4_SLUG + "/";
    private static final String GIFTS_5_CANONICAL = "https://bebesfelices.es/comparativas/" + GIFTS_5_SLUG + "/";
    private static final String GIFT_SELECTION_4_CANONICAL = "https://bebesfelices.es/comparativas/" + GIFT_SELECTION_4_SLUG + "/";
    private static final String GIFT_SELECTION_5_CANONICAL = "https://bebesfelices.es/comparativas/" + GIFT_SELECTION_5_SLUG + "/";
    private static final String CHOOSE_GIFT_4_CANONICAL = "https://bebesfelices.es/comparativas/" + CHOOSE_GIFT_4_SLUG + "/";
    private static final String CHOOSE_GIFT_5_CANONICAL = "https://bebesfelices.es/comparativas/" + CHOOSE_GIFT_5_SLUG + "/";
    private static final String BALANCE_BIKES_4_CANONICAL = "https://bebesfelices.es/comparativas/" + BALANCE_BIKES_4_SLUG + "/";
    private static final String BALANCE_BIKES_5_CANONICAL = "https://bebesfelices.es/comparativas/" + BALANCE_BIKES_5_SLUG + "/";
    private static final String STEM_4_CANONICAL = "https://bebesfelices.es/comparativas/" + STEM_4_SLUG + "/";
    private static final String BOARD_GAMES_5_CANONICAL = "https://bebesfelices.es/comparativas/" + BOARD_GAMES_5_SLUG + "/";
    private static final String SCOOTERS_5_CANONICAL = "https://bebesfelices.es/comparativas/" + SCOOTERS_5_SLUG + "/";
    private static final String TOWERS_5_CANONICAL = "https://bebesfelices.es/comparativas/" + TOWERS_5_SLUG + "/";
    private static final String TABLEWARE_5_CANONICAL = "https://bebesfelices.es/comparativas/" + TABLEWARE_5_SLUG + "/";
    private static final String SUSTAINABLE_5_CANONICAL = "https://bebesfelices.es/comparativas/" + SUSTAINABLE_5_SLUG + "/";

    private static final List<EditorialEntry> BALANCE_BIKES = List.of(
            new EditorialEntry(
                    "bici-chicco-red-bullet",
                    "Chicco Red Bullet",
                    "Una primera bicicleta sencilla y ajustable",
                    "Una opción directa para iniciarse a los 3 años: combina un peso declarado de 2,7 kg con ruedas antipinchazos y ajustes en sillín y manillar.",
                    List.of(
                            "Ruedas antipinchazos de 10 pulgadas, sin mantenimiento de presión.",
                            "Sillín y manillar ajustables para acompañar el crecimiento.",
                            "Carga máxima declarada de 25 kg."
                    ),
                    List.of(
                            "Las ruedas de 10 pulgadas son menores que las de 30 cm de otros modelos.",
                            "No incorpora ruedas inflables."
                    ),
                    "2-5 años",
                    List.of(
                            note("Ajuste", "Sillín y manillar ajustables."),
                            note("Manejo", "Peso declarado de 2,7 kg."),
                            note("Ruedas", "10 pulgadas y diseño antipinchazos.")
                    )
            ),
            new EditorialEntry(
                    "bici-kinderkraft-tove",
                    "Kinderkraft TOVE",
                    "Niños que necesitan un modelo especialmente ligero",
                    "Con 2 kg declarados y sillín entre 31 y 34,5 cm, facilita que un niño pequeño pueda manejarla y recuperar el equilibrio.",
                    List.of(
                            "Peso declarado de 2 kg.",
                            "Sillín regulable entre 31 y 34,5 cm.",
                            "Límite de giro y ruedas EVA.",
                            "Carga máxima declarada de 25 kg."
                    ),
                    List.of(
                            "El recorrido de ajuste del sillín es más corto que en los modelos de 34 a 42 cm.",
                            "Las ruedas EVA priorizan el bajo mantenimiento frente a la absorción de una rueda inflable."
                    ),
                    "Desde 18 meses",
                    List.of(
                            note("Ajuste", "Sillín regulable de 31 a 34,5 cm."),
                            note("Manejo", "Peso declarado de 2 kg y límite de giro."),
                            note("Ruedas", "Ruedas EVA sin necesidad de inflado.")
                    )
            ),
            new EditorialEntry(
                    "bici-kinderkraft-fly-plus-2",
                    "Kinderkraft FLY PLUS 2",
                    "Un ajuste amplio y mayor margen de carga",
                    "Su sillín de 34 a 42 cm ofrece un recorrido amplio, mientras las ruedas de espuma de 30 cm evitan pinchazos y el límite de giro ayuda en el aprendizaje.",
                    List.of(
                            "Sillín regulable entre 34 y 42 cm.",
                            "Ruedas de espuma de 30 cm.",
                            "Límite de giro.",
                            "Carga máxima declarada de 35 kg."
                    ),
                    List.of(
                            "Su peso declarado varía entre 2,7 y 2,8 kg según la configuración.",
                            "Las ruedas de espuma no ofrecen el mismo comportamiento que unas inflables sobre firme irregular."
                    ),
                    "Desde 2 años",
                    List.of(
                            note("Ajuste", "Sillín regulable de 34 a 42 cm."),
                            note("Manejo", "Peso declarado de 2,7-2,8 kg y límite de giro."),
                            note("Ruedas", "Espuma de 30 cm, sin inflado.")
                    )
            ),
            new EditorialEntry(
                    "bici-kinderkraft-goswift",
                    "Kinderkraft GOSWIFT",
                    "Paseos sobre superficies variadas",
                    "Las ruedas inflables de 30 cm y el cuadro de magnesio la orientan a quien prioriza el comportamiento sobre firmes variados y acepta más peso y mantenimiento.",
                    List.of(
                            "Ruedas inflables de 30 cm.",
                            "Sillín regulable entre 34 y 42 cm.",
                            "Cuadro de magnesio.",
                            "Rango declarado de 3 a 6 años."
                    ),
                    List.of(
                            "Con 3,8 kg declarados, es la más pesada de esta selección.",
                            "Las ruedas inflables requieren revisar la presión y pueden pincharse."
                    ),
                    "3-6 años",
                    List.of(
                            note("Ajuste", "Sillín regulable de 34 a 42 cm."),
                            note("Manejo", "Peso declarado de 3,8 kg y cuadro de magnesio."),
                            note("Ruedas", "Inflables de 30 cm.")
                    )
            ),
            new EditorialEntry(
                    "bici-puky-lr-m",
                    "PUKY LR M",
                    "Una postura ajustable con amplio margen de talla",
                    "Combina sillín y manillar ajustables, 3,5 kg declarados, ruedas EVA sin aire y reposapiés. La ficha indica una entrepierna de 30 a 43 cm y una altura de 85 a 110 cm.",
                    List.of(
                            "Adecuada para entrepiernas de 30 a 43 cm según la ficha.",
                            "Peso declarado de 3,5 kg.",
                            "Ruedas EVA sin aire de 8,8 pulgadas.",
                            "Reposapiés integrado.",
                            "Indicada desde 2 años."
                    ),
                    List.of(
                            "Es más pesada que las opciones de 2 a 2,8 kg de la selección.",
                            "Sus ruedas EVA priorizan el bajo mantenimiento frente a la absorción de una rueda inflable."
                    ),
                    "Desde 2 años",
                    List.of(
                            note("Ajuste", "Entrepierna de 30 a 43 cm y altura de 85 a 110 cm."),
                            note("Manejo", "Peso declarado de 3,5 kg y reposapiés integrado."),
                            note("Ruedas", "EVA sin aire de 8,8 pulgadas.")
                    )
            )
    );

    private static final List<EditorialEntry> BOARD_GAMES = List.of(
            new EditorialEntry(
                    "juego-mesa-el-frutal-mini",
                    "HABA El Frutal Mini",
                    "Empezar a cooperar con reglas mínimas",
                    "Versión mini del clásico cooperativo de HABA: se recoge la fruta de madera antes de que el cuervo Teo llegue a los árboles. A los 4 años las reglas caben en un turno corto y no hace falta leer.",
                    List.of(
                            "Cooperativo: se gana o se pierde en equipo.",
                            "Edad declarada a partir de 3 años.",
                            "De 1 a 4 jugadores, con dado de colores y símbolos.",
                            "Piezas de madera de haya y caja de lata para guardar o llevar."
                    ),
                    List.of(
                            "Es el formato mini: menos fruta y hasta 4 jugadores, no la caja grande de hasta 8.",
                            "El dado introduce azar; un adulto debe explicar el objetivo la primera vez."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "Reglas cortas y edad declarada desde 3 años."),
                            note("Cooperación", "Todos recogen fruta frente al cuervo; no hay un ganador individual."),
                            note("Lectura", "No requiere leer; el dado usa colores y símbolos."),
                            note("Componentes", "Madera de haya, pintura al agua y aviso de piezas pequeñas menores de 3 años.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-unicornio-tesoro",
                    "HABA Unicornio Destello El Tesoro de las Nubes",
                    "Practicar colores y primeros conteos",
                    "Los unicornios recorren el camino hacia la nube del sol recogiendo cristales. El tablero a doble cara está pensado para acercarse a los primeros conteos con dados, a partir de 3 años.",
                    List.of(
                            "Edad declarada a partir de 3 años.",
                            "Tablero a doble cara para favorecer los primeros conteos.",
                            "Cuatro unicornios de madera y 60 cristales de nube.",
                            "Dados y figuras de haya con pintura al agua, fabricados en Alemania."
                    ),
                    List.of(
                            "Es un juego de acumulación y carrera, no cooperativo: hay un resultado individual.",
                            "Con 60 cristales conviene vigilar que no se pierdan ni se lleven a la boca."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "Dados, colores y un recuento sencillo encajan cuando ya aguanta un turno."),
                            note("Cooperación", "Competitivo: gana quien llega y recoge más cristales."),
                            note("Lectura", "No exige leer; el adulto explica el recorrido y el conteo."),
                            note("Componentes", "Madera de haya y cristales pequeños; no apto para menores de 3 años.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-animal-sobre-animal",
                    "HABA Animal sobre Animal",
                    "Trabajar pulso y motricidad fina",
                    "Se apilan 29 figuras de madera según el dado de símbolos. A los 4 años el reto es de pulso y planificación espacial, con reglas que HABA declara comprensibles para niños pequeños.",
                    List.of(
                            "Edad declarada de 4 a 99 años.",
                            "29 animales de madera y un dado de símbolos.",
                            "Reglas cortas centradas en apilar sin derribar la torre.",
                            "Entrena coordinación ojo-mano y pensamiento tridimensional."
                    ),
                    List.of(
                            "Es competitivo: gana quien coloca primero todas sus figuras.",
                            "Si el pulso aún es inestable, las caídas pueden frustrar; conviene partidas cortas y un adulto cerca."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Encaje a los 4 años", "La edad mínima del fabricante coincide con esta etapa."),
                            note("Cooperación", "Competitivo de habilidad; se puede jugar con ayuda, no es un modo cooperativo declarado."),
                            note("Lectura", "El dado usa símbolos; no hace falta leer el reglamento en cada turno."),
                            note("Componentes", "Figuras de madera de tamaño manejable; usar con supervisión.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-dobble-kids",
                    "Dobble Kids",
                    "Observación rápida en familia",
                    "Edición infantil en español (DOKI01ES): cada dos cartas comparten un animal y hay que encontrarlo. Menos iconos que el Dobble adulto, partidas de unos 15 minutos y hasta 8 jugadores.",
                    List.of(
                            "Edad declarada a partir de 4 años en la ficha de Amazon.es.",
                            "De 2 a 8 jugadores y unos 15 minutos por partida.",
                            "30 cartas con ilustraciones más reconocibles que el Dobble estándar.",
                            "Cinco minijuegos; no hace falta leer para señalar el animal."
                    ),
                    List.of(
                            "Es un juego de rapidez: puede resultar intenso si el niño aún no tolera perder un envite corto.",
                            "No es cooperativo; el adulto puede suavizar el ritmo para que no sea una carrera."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Encaje a los 4 años", "Menos símbolos por carta y partidas breves, pensado para esta edad."),
                            note("Cooperación", "Competitivo de observación; el valor está en nombrar y mirar, no en un objetivo común."),
                            note("Lectura", "Reglamento en español; el turno se resuelve señalando un dibujo."),
                            note("Componentes", "Cartas; no recomendado para menores de 3 años por piezas pequeñas.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-unicornio-fiesta-rosalie",
                    "HABA Unicornio Destello Una Fiesta para Rosalie",
                    "Un reto cooperativo un poco más estructurado",
                    "HABA lo presenta como un cooperativo de recolección y movimiento a partir de 4 años: dados y ruleta para reunir cristales e invitados antes de que llegue Rosalie.",
                    List.of(
                            "Edad declarada a partir de 4 años.",
                            "De 2 a 4 jugadores, con dados y ruleta.",
                            "Cinco unicornios de madera de haya y cristales de nube extragrandes.",
                            "Un paso más de estructura que El Frutal Mini, sin exigir lectura."
                    ),
                    List.of(
                            "Hay más elementos que en un primer cooperativo; la primera partida necesita un adulto que lleve el ritmo.",
                            "Si el niño aún se cansa a los pocos minutos, empieza por El Frutal Mini."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Encaje a los 4 años", "La edad mínima del fabricante es 4 años, alineada con esta página."),
                            note("Cooperación", "HABA lo describe como cooperativo de recolección y movimiento."),
                            note("Lectura", "Dados y ruleta; el adulto explica el objetivo común."),
                            note("Componentes", "Madera y cristales grandes; usar bajo supervisión.")
                    )
            )
    );

    private static final List<EditorialEntry> SCOOTERS = List.of(
            new EditorialEntry(
                    "patinete-micro-mini-deluxe",
                    "Micro Mini Deluxe LED",
                    "Empezar de pie con tres ruedas ligeras",
                    "Patinete de tres ruedas para 2 a 5 años: 1,95 kg declarados, giro por inclinación, manillar ajustable y luces LED sin pilas. A los 4 años cubre un uso de pie con una base estable y poco peso que levantar.",
                    List.of(
                            "Tres ruedas, giro por inclinación y edad declarada de 2 a 5 años.",
                            "Peso declarado de 1,95 kg y carga máxima de 50 kg.",
                            "Manillar ajustable y desmontable; las fichas del Mini Deluxe LED indican 48-68 cm.",
                            "Luces LED en las ruedas delanteras sin pilas y freno trasero."
                    ),
                    List.of(
                            "No declara sistema de plegado en esta ficha; el manillar sí se desmonta para guardar o llevar.",
                            "Sigue siendo un patinete de tres ruedas: no sustituye una bicicleta sin pedales ni un modelo de dos ruedas."
                    ),
                    "2-5 años",
                    List.of(
                            note("Encaje a los 4 años", "Edad declarada de 2 a 5 años y manillar en el rango 48-68 cm."),
                            note("Estabilidad", "Tres ruedas y giro por inclinación; se usa de pie."),
                            note("Manejo", "Peso declarado de 1,95 kg y carga máxima de 50 kg."),
                            note("Seguridad", "Freno trasero; casco, calzado cerrado y supervisión siguen siendo necesarios.")
                    )
            ),
            new EditorialEntry(
                    "patinete-molto-maxi",
                    "MOLTO Maxi Scooter",
                    "Una primera opción sencilla de tres ruedas",
                    "Patinete de tres ruedas indicado para 3 a 5 años, con luces LED en las ruedas, manillar de 57 a 67 cm, freno trasero y montaje sin herramientas.",
                    List.of(
                            "Edad declarada de 3 a 5 años.",
                            "Manillar regulable entre 57 y 67 cm.",
                            "Luces LED en las ruedas, plataforma antideslizante y freno trasero.",
                            "Montaje sin herramientas según la ficha."
                    ),
                    List.of(
                            "El recorrido del manillar es de 10 cm y parte de 57 cm, por encima de los 48 cm del Mini Deluxe LED.",
                            "La ficha consultada no declara plegado ni un peso del patinete."
                    ),
                    "3-5 años",
                    List.of(
                            note("Encaje a los 4 años", "Edad declarada de 3 a 5 años y manillar de 57 a 67 cm."),
                            note("Estabilidad", "Tres ruedas y plataforma antideslizante."),
                            note("Manejo", "Freno trasero y montaje sin herramientas; no hay peso declarado en la ficha."),
                            note("Seguridad", "Freno trasero; usar casco y supervisión. La ficha recomienda no usarlo en tráfico.")
                    )
            ),
            new EditorialEntry(
                    "patinete-globber-junior-foldable",
                    "Globber Junior Foldable Lights",
                    "Plegar y llevar el patinete",
                    "Patinete de tres ruedas plegable desde 2 años, con luces LED por dinamo, bloqueo de dirección, manillar de tres alturas (54, 61 y 68 cm) y carga máxima de 50 kg.",
                    List.of(
                            "Plegado con botón y modo carrito para guardar o transportar.",
                            "Edad declarada a partir de 2 años y carga máxima de 50 kg.",
                            "Manillar de 3 alturas: 54, 61 y 68 cm.",
                            "Luces LED sin batería y bloqueo de dirección para circular en línea recta."
                    ),
                    List.of(
                            "El manillar llega hasta 68 cm, por debajo de los 74-94 cm del Master Lights.",
                            "El plegado y el bloqueo de dirección piden que un adulto los configure; no sustituyen la supervisión."
                    ),
                    "Desde 2 años",
                    List.of(
                            note("Encaje a los 4 años", "Indicada desde 2 años; el manillar cubre 54, 61 y 68 cm."),
                            note("Estabilidad", "Tres ruedas y bloqueo de dirección opcional."),
                            note("Manejo", "Carga máxima de 50 kg; plegado y modo carrito."),
                            note("Seguridad", "Freno trasero ancho; casco y zona sin tráfico.")
                    )
            ),
            new EditorialEntry(
                    "patinete-globber-master-lights",
                    "Globber Master Lights",
                    "Más altura a partir de 4 años",
                    "Patinete de tres ruedas plegable indicado desde 4 años, con manillar de cinco alturas entre 74 y 94 cm, luces LED por dinamo, bloqueo de dirección y carga máxima de 50 kg.",
                    List.of(
                            "Edad declarada a partir de 4 años.",
                            "Manillar de 5 alturas: 74, 79, 84, 89 y 94 cm.",
                            "Plegado con botón y modo carrito.",
                            "Carga máxima de 50 kg, luces LED por dinamo y bloqueo de dirección."
                    ),
                    List.of(
                            "El manillar parte de 74 cm, por encima de los 48-68 cm de Mini Deluxe y Junior: encaja mejor si el niño ya es alto.",
                            "Sigue siendo de tres ruedas; no es el paso a un patinete de dos ruedas."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Encaje a los 4 años", "Indicada desde 4 años; el manillar mínimo es 74 cm."),
                            note("Estabilidad", "Tres ruedas y bloqueo de dirección opcional."),
                            note("Manejo", "Cinco alturas de manillar y plegado; carga máxima de 50 kg."),
                            note("Seguridad", "Freno trasero ancho; casco y supervisión.")
                    )
            ),
            new EditorialEntry(
                    "triciclo-chicco-u-go",
                    "Chicco U-GO 2en1",
                    "Pedalear sentado con mango de adulto",
                    "Triciclo de 18 meses a 5 años y hasta 20 kg, con dos modos: el adulto guía con mango telescópico (pedales y dirección bloqueables) o se retira el mango para el pedaleo libre. No es un patinete.",
                    List.of(
                            "Edad declarada de 18 meses a 5 años.",
                            "Dos modos: empuje con mango telescópico en tres posiciones o pedaleo libre sin mango.",
                            "Estructura metálica, cinturón en el asiento y cesta.",
                            "Bloqueo de pedales y manillar en el modo guiado por el adulto."
                    ),
                    List.of(
                            "La carga máxima declarada es 20 kg, por debajo de los 50 kg de los patinetes de esta lista; comprueba el peso real del niño.",
                            "Se usa sentado, con pedales: no practica el equilibrio de pie del patinete."
                    ),
                    "18 meses-5 años",
                    List.of(
                            note("Encaje a los 4 años", "Hasta 5 años y 20 kg; a esta edad el modo habitual es el pedaleo libre."),
                            note("Estabilidad", "Tres ruedas y uso sentado, distinto del patinete de pie."),
                            note("Manejo", "Mango telescópico extraíble; ruedas de goma antipinchazos según la ficha."),
                            note("Seguridad", "Cinturón y bloqueo de pedales o dirección en el modo guiado; supervisión igualmente necesaria.")
                    )
            )
    );

    private static final List<EditorialEntry> TOWERS = List.of(
            new EditorialEntry(
                    "torre-yoleo-transformer",
                    "YOLEO Transformer",
                    "Plegar y convertir en mesa de trabajo",
                    "Torre de madera de nogal de 42 x 45 x 86 cm, plegable, con bloqueo y convertible en silla y escritorio sin herramientas. Incluye pizarra magnética de doble cara (blanca y negra).",
                    List.of(
                            "Se pliega y se convierte en silla y escritorio sin herramientas.",
                            "Pizarra magnética de doble cara para dibujar.",
                            "Ángulos redondeados y madera de nogal según la ficha.",
                            "Pensada para tareas de cocina, lavabo o dientes con un adulto."
                    ),
                    List.of(
                            "Ocupa 42 x 45 cm desplegada; hay que comprobar huecos y holguras tras el montaje.",
                            "La pizarra no sustituye la barandilla ni la supervisión junto al fuego o al agua hirviendo."
                    ),
                    "Uso infantil con adulto",
                    List.of(
                            note("Encaje a los 4 años", "A esta edad ya puede subir y bajar si la altura encaja; el escritorio alarga el uso."),
                            note("Estabilidad", "La ficha insiste en estructura estable y montaje con kit incluido."),
                            note("Regulación", "Modo torre o mesa; confirma la altura respecto a tu encimera."),
                            note("Seguridad", "Nunca sola junto a fogones, cuchillos o agua hirviendo.")
                    )
            ),
            new EditorialEntry(
                    "torre-hauck-learn-n-explore",
                    "hauck Learn N Explore",
                    "Tres alturas en madera de haya FSC",
                    "Torre de madera de haya con certificación FSC, de 1 a 6 años, con plataforma regulable en 3 alturas (33 a 45 cm) y 90,5 cm de alto. Carga máxima declarada de 40 kg.",
                    List.of(
                            "Tres alturas de plataforma, de 33 a 45 cm.",
                            "Madera de haya con certificación FSC según la ficha.",
                            "Edad declarada de 1 a 6 años y carga de 40 kg.",
                            "Uso en cocina, mesa o baño con un adulto."
                    ),
                    List.of(
                            "La ficha no declara plegado ni conversión a escritorio.",
                            "A los 4 años usa una altura que llegue a la encimera sin trepar por fuera."
                    ),
                    "1-6 años",
                    List.of(
                            note("Encaje a los 4 años", "Las tres alturas cubren el crecimiento; mide tu encimera."),
                            note("Estabilidad", "Estructura de madera de haya; el fabricante la describe como estable y robusta."),
                            note("Regulación", "Tres posiciones de plataforma, de 33 a 45 cm."),
                            note("Seguridad", "Uso solo con adulto; no es un juguete de trepa.")
                    )
            ),
            new EditorialEntry(
                    "torre-costway-plegable",
                    "COSTWAY Plegable 3 en 1",
                    "Plegar y convertir en mesa",
                    "Torre plegable de madera indicada a partir de 3 años, con estructura en A, patas antivuelco, barra de seguridad y pizarra. Se convierte en mesa y silla sin herramientas. Altura 91 cm y carga máxima de 60 kg.",
                    List.of(
                            "Indicada a partir de 3 años en la ficha.",
                            "Se pliega y se convierte en mesa bloqueando la hebilla.",
                            "Estructura en A, patas antivuelco y barra de seguridad.",
                            "Pizarra integrada; carga máxima declarada de 60 kg."
                    ),
                    List.of(
                            "Plegada sigue midiendo 91 cm de alto.",
                            "La hebilla debe quedar bloqueada; la pizarra no sustituye la barandilla."
                    ),
                    "Recomendada desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "La ficha la recomienda a partir de 3 años."),
                            note("Estabilidad", "Estructura en A y patas antivuelco."),
                            note("Regulación", "Plegado y modo mesa; confirma la altura de plataforma en tu unidad."),
                            note("Seguridad", "Supervisión directa; no es un juguete de trepa.")
                    )
            ),
            new EditorialEntry(
                    "torre-bey-co",
                    "BEY & CO Torre de aprendizaje",
                    "Tres alturas con certificación EN-71",
                    "Torre de madera con plataforma en 3 alturas, superficie antideslizante, patas anticaída y certificación EN-71. Indicada desde que el niño se mantiene de pie.",
                    List.of(
                            "Tres alturas de peldaño para acompañar el crecimiento.",
                            "Certificación EN-71, patas anticaída y gomas antideslizantes.",
                            "Madera barnizada, fácil de limpiar según la ficha.",
                            "Uso en cocina o lavabo con un adulto."
                    ),
                    List.of(
                            "La ficha no declara plegado ni conversión a escritorio.",
                            "Indicada desde los 12 meses o cuando se mantiene de pie: a los 4 años hay que ajustar la altura más alta."
                    ),
                    "Desde que se mantiene de pie",
                    List.of(
                            note("Encaje a los 4 años", "Usa la posición más alta y comprueba que llega a la encimera sin trepar."),
                            note("Estabilidad", "Patas anticaída y superficie antideslizante."),
                            note("Regulación", "Tres alturas de escalón."),
                            note("Seguridad", "EN-71 no sustituye a un adulto al lado.")
                    )
            ),
            new EditorialEntry(
                    "torre-maxi-cosi-toucan",
                    "Maxi-Cosi Toucan 3 en 1",
                    "Convertir la torre en mesa, silla o taburete",
                    "Torre de madera FSC que pasa a mesa y silla o a taburete, con 3 alturas (29,5 a 41,4 cm), 46 x 91 x 43,3 cm y 8,7 kg. El modo torre cubre de 1,5 a 6 años (10-30 kg); el taburete declara hasta 150 kg.",
                    List.of(
                            "Se transforma en mesa y silla, o en taburete al retirar la tapa.",
                            "Tres posiciones de altura, de 29,5 a 41,4 cm.",
                            "Madera FSC 100 % y pizarra integrada.",
                            "8,7 kg declarados; normas de seguridad europeas según la ficha."
                    ),
                    List.of(
                            "El modo taburete (hasta 150 kg) no es el uso infantil en torre.",
                            "El modo escritorio no sustituye la supervisión junto al fuego."
                    ),
                    "Uso infantil con adulto",
                    List.of(
                            note("Encaje a los 4 años", "El escritorio alarga el uso cuando la torre de cocina se queda corta."),
                            note("Estabilidad", "Base de 46 x 43,3 cm; monta según el manual."),
                            note("Regulación", "Tres alturas y modos torre, mesa o taburete."),
                            note("Seguridad", "Supervisión constante; no dejarla junto al fuego.")
                    )
            )
    );

    private static final List<EditorialEntry> TABLEWARE = List.of(
            new EditorialEntry(
                    "vajilla-twistshake-dividido",
                    "Twistshake plato con compartimentos",
                    "Separar la comida sin que vuele el plato",
                    "Plato de 20 cm y 4,5 cm de alto, con 3 compartimentos, tapa, base antideslizante y plástico PP y TPE libre de BPA. Apto para microondas y lavavajillas, desde 6 meses.",
                    List.of(
                            "Tres compartimentos para no mezclar alimentos.",
                            "Tapa incluida; se puede apilar con TwistClick.",
                            "Base antideslizante; compatible con Click-Mat (se vende aparte).",
                            "Libre de BPA, apto para microondas y cubeta superior del lavavajillas."
                    ),
                    List.of(
                            "Es un plato, no un set: no incluye vaso ni cubiertos.",
                            "El Click-Mat que lo deja casi inmóvil se compra aparte."
                    ),
                    "Desde 6 meses",
                    List.of(
                            note("Encaje a los 4 años", "Los compartimentos siguen siendo útiles si no quiere mezclar."),
                            note("Estabilidad", "Base antideslizante; Click-Mat opcional."),
                            note("Piezas", "Plato y tapa; sin vaso ni cubiertos."),
                            note("Cuidado", "Microondas y lavavajillas (cubeta superior).")
                    )
            ),
            new EditorialEntry(
                    "vajilla-stor-mickey",
                    "Stor vajilla 3 piezas Mickey Mouse",
                    "Llevar plato, cuenco y vaso a la mesa",
                    "Set de plato, cuenco y vaso de 260 ml, plástico libre de BPA, base antideslizante y apto para microondas. Licencia Mickey Mouse.",
                    List.of(
                            "Tres piezas de mesa: plato, cuenco y vaso de 260 ml.",
                            "Base antideslizante en superficie lisa.",
                            "Libre de BPA y apto para microondas."
                    ),
                    List.of(
                            "El diseño es de personaje: si el interés pasa, el set sigue siendo plástico de mesa.",
                            "No incluye cubiertos."
                    ),
                    "3-6 años",
                    List.of(
                            note("Encaje a los 4 años", "Tamaño de mesa diaria; el vaso es abierto de 260 ml."),
                            note("Estabilidad", "Base antideslizante en superficie lisa."),
                            note("Piezas", "Plato, cuenco y vaso; sin cubiertos."),
                            note("Cuidado", "Apto para microondas; revisa el marcado de alimento.")
                    )
            ),
            new EditorialEntry(
                    "vaso-munchkin-miracle-360",
                    "Munchkin Miracle 360 con asas",
                    "Beber sin tetina y con menos derrames",
                    "Lote de 2 vasos de 207 ml con borde 360°, válvula antigoteo, asas y plástico libre de BPA. Apto para lavavajillas. No es un set de plato.",
                    List.of(
                            "Borde 360° para beber por cualquier lado, sin tetina.",
                            "Válvula que se cierra al dejar de beber.",
                            "Dos unidades de 207 ml con asas.",
                            "Libre de BPA y apto para lavavajillas."
                    ),
                    List.of(
                            "No incluye plato ni cubiertos.",
                            "Hay que limpiar la válvula; no es un vaso de cristal de adulto."
                    ),
                    "Desde 6 meses",
                    List.of(
                            note("Encaje a los 4 años", "El borde abierto ayuda a pasar del biberón al vaso."),
                            note("Estabilidad", "Asas; la válvula reduce derrames, no los elimina si se agita."),
                            note("Piezas", "Solo vasos."),
                            note("Cuidado", "Lavavajillas; desmonta la válvula según el manual.")
                    )
            ),
            new EditorialEntry(
                    "vajilla-fun-house",
                    "Fun House vajilla 3 piezas",
                    "Un set reutilizable de plato, cuenco y vaso",
                    "Juego de polipropileno con plato de 22 cm, cuenco de 16 cm y vaso de 220 ml, reutilizable y apto para microondas.",
                    List.of(
                            "Tres piezas con medidas declaradas: 22 cm, 16 cm y 220 ml.",
                            "Apto para microondas.",
                            "Reutilizable para el uso diario."
                    ),
                    List.of(
                            "El motivo ilustrado es un reclamo; no añade función.",
                            "La ficha no declara base antideslizante ni cubiertos."
                    ),
                    "Uso infantil",
                    List.of(
                            note("Encaje a los 4 años", "El plato de 22 cm es de tamaño mesa, no de aprendizaje de bebé."),
                            note("Estabilidad", "Sin base antideslizante declarada."),
                            note("Piezas", "Plato, cuenco y vaso."),
                            note("Cuidado", "Microondas; confirma lavavajillas en la ficha vigente.")
                    )
            ),
            new EditorialEntry(
                    "cuenco-twistshake-tapa",
                    "Twistshake cuenco con tapa",
                    "Guardar o llevar lo que no se termina",
                    "Cuenco de PP y silicona con tapa, libre de BPA, desde 6 meses. Complementa el plato; no sustituye un set completo.",
                    List.of(
                            "Tapa para guardar o transportar.",
                            "Libre de BPA.",
                            "Indicada desde 6 meses."
                    ),
                    List.of(
                            "Es un cuenco, no un set de mesa.",
                            "La ficha de este ASIN no detalla el volumen."
                    ),
                    "Desde 6 meses",
                    List.of(
                            note("Encaje a los 4 años", "Útil para yogur, fruta o llevar al parque."),
                            note("Estabilidad", "Silicona y PP; confirma antideslizante en tu color."),
                            note("Piezas", "Cuenco y tapa."),
                            note("Cuidado", "Revisa microondas y lavavajillas en la ficha.")
                    )
            )
    );

    private static final List<EditorialEntry> SUSTAINABLE = List.of(
            new EditorialEntry(
                    "cuentas-melissa-doug",
                    "Melissa & Doug cuentas de madera",
                    "Ensartar sin pantallas",
                    "27 cuentas de madera con formas, números del 1 al 10 y 2 cordones, a partir de 3 años. Motricidad fina y secuencias, sin electrónica.",
                    List.of(
                            "Madera, 27 piezas y 2 cordones.",
                            "Números del 1 al 10 y formas para clasificar.",
                            "Edad declarada a partir de 3 años.",
                            "Juego sin pantallas."
                    ),
                    List.of(
                            "Las cuentas y los cordones piden supervisión: riesgo de asfixia si se usan mal.",
                            "No es un kit de pintar o recortar; es ensartar y contar."
                    ),
                    "3-6 años",
                    List.of(
                            note("Encaje a los 4 años", "Edad declarada desde 3 años; a los 4 ya puede seguir secuencias cortas."),
                            note("Materiales", "Cuentas de madera y cordones."),
                            note("Uso", "Ensartar, clasificar y contar; no es un juguete de dejar solo con hermanos pequeños."),
                            note("Duración", "Se guarda y se saca en sesiones cortas.")
                    )
            ),
            new EditorialEntry(
                    "plantoys-ata-zapato",
                    "PlanToys Ata el zapato",
                    "Practicar nudos de verdad",
                    "Juguete de madera de caucho para 3 a 8 años, fabricado en Tailandia con pegamento sin formaldehído y tintes al agua. Sirve para ensayar nudos, no sustituye el calzado real.",
                    List.of(
                            "Edad declarada de 3 a 8 años.",
                            "Madera de caucho, pigmentos orgánicos y tintes al agua.",
                            "Pegamento sin formaldehído según la ficha.",
                            "Formato portable para practicar fuera de la entrada."
                    ),
                    List.of(
                            "No ata el zapato del niño: es un material de ensayo.",
                            "Un adulto debe mostrar el nudo las primeras veces."
                    ),
                    "3-8 años",
                    List.of(
                            note("Encaje a los 4 años", "Rango 3-8 años; a los 4 el nudo todavía pide ayuda."),
                            note("Materiales", "Madera de caucho y tintes al agua."),
                            note("Uso", "Autonomía de vestirse, no juego libre de larga duración."),
                            note("Duración", "Se usa en ratos cortos, cuando hay un zapato de verdad que atar después.")
                    )
            ),
            new EditorialEntry(
                    "haba-puzles-cuatro-estaciones",
                    "HABA Puzzles Las Cuatro Estaciones",
                    "Cartón resistente y madera de haya",
                    "Cuatro rompecabezas de 15 piezas a partir de 3 años, con figuras de madera de haya sostenible. Clasificar estaciones y jugar sobre el puzle ya montado.",
                    List.of(
                            "Cuatro puzles de 15 piezas, edad desde 3 años.",
                            "Figuras de madera de haya sostenible.",
                            "Cartón resistente para manos pequeñas.",
                            "Motivos de las cuatro estaciones."
                    ),
                    List.of(
                            "15 piezas por puzle puede quedarse corto si ya monta de 24 o más.",
                            "Las figuras de madera son pequeñas: no las dejes con menores de 3 años."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "Desde 3 años; a los 4 las figuras alargan el juego."),
                            note("Materiales", "Cartón y haya sostenible."),
                            note("Uso", "Encajar y juego simbólico con las figuras."),
                            note("Duración", "Cuatro motivos distintos para repetir.")
                    )
            ),
            new EditorialEntry(
                    "small-foot-grua",
                    "Small Foot grúa de construcción",
                    "Madera FSC para el juego de obra",
                    "Grúa de madera FSC 100 % a partir de 3 años, giratoria 360°, con manivela, escalera interior y accesorios de obra.",
                    List.of(
                            "Madera con certificado FSC 100 %.",
                            "Edad declarada a partir de 3 años.",
                            "Pluma móvil con manivela y giro 360°.",
                            "Incluye accesorios de obra según la ficha."
                    ),
                    List.of(
                            "Hay piezas sueltas y cuerda: supervisión en cada sesión.",
                            "No es un juguete de exterior ni de agua."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "Desde 3 años; a los 4 la manivela ya se entiende con ayuda."),
                            note("Materiales", "Madera FSC 100 %."),
                            note("Uso", "Juego simbólico de obra, no construcción magnética."),
                            note("Duración", "Se puede combinar con otros vehículos de madera.")
                    )
            ),
            new EditorialEntry(
                    "green-toys-construccion",
                    "Green Toys vehículos de construcción",
                    "Plástico reciclado que se puede lavar",
                    "Tres vehículos (pala, hormigonera y volquete) de plástico 100 % reciclado, fabricados en EE. UU., sin BPA, ftalatos ni PVC. Lavables en lavavajillas. Edad 24 a 72 meses.",
                    List.of(
                            "Plástico 100 % reciclado, sin BPA, ftalatos ni PVC.",
                            "Tres vehículos y figuras de perros de obra intercambiables.",
                            "Se pueden lavar en lavavajillas.",
                            "Rango declarado de 24 a 72 meses."
                    ),
                    List.of(
                            "No es madera: la sostenibilidad aquí es el plástico reciclado.",
                            "A los 4 años (48 meses) está dentro del rango; a los 6 se acaba el declarado."
                    ),
                    "24-72 meses",
                    List.of(
                            note("Encaje a los 4 años", "48 meses cae en el rango 24-72."),
                            note("Materiales", "Plástico reciclado; tinta de soja según la ficha."),
                            note("Uso", "Arena, bañera o suelo; se lava entero."),
                            note("Duración", "Tres vehículos para rotar; sin electrónica.")
                    )
            )
    );

    private static final List<EditorialEntry> STEM_5 = List.of(
            new EditorialEntry(
                    "set-construccion-magnetico",
                    "Set de construcción magnético",
                    "Construir estructuras y explorar geometría",
                    "Las piezas magnéticas permiten probar formas, simetrías y estabilidad con un reto que puede crecer añadiendo componentes.",
                    List.of(
                            "Permite construir en tres dimensiones.",
                            "La dificultad se adapta al número de piezas.",
                            "Practica lógica espacial y planificación."
                    ),
                    List.of(
                            "Los imanes deben permanecer encapsulados.",
                            "Un set demasiado grande puede dificultar terminar un proyecto."
                    ),
                    "4-5 años",
                    List.of(
                            note("Reto STEM", "Construcción, geometría y estabilidad."),
                            note("Uso evolutivo", "Se puede aumentar la dificultad añadiendo piezas."),
                            note("Seguridad", "Requiere revisar que ningún imán se desprenda.")
                    )
            ),
            new EditorialEntry(
                    "small-foot-grua",
                    "Small Foot grúa de construcción",
                    "Comprender poleas, giro y causa-efecto",
                    "La grúa incorpora giro de 360 grados y manivela para observar cómo un mecanismo sencillo eleva y desplaza una carga.",
                    List.of(
                            "Manivela y grúa giratoria para experimentar con movimiento.",
                            "Juego abierto con accesorios de obra.",
                            "Madera FSC 100 % declarada en la ficha."
                    ),
                    List.of(
                            "El aprendizaje depende de proponer retos, no solo de mover accesorios.",
                            "Las piezas se deben recoger si hay hermanos pequeños."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Reto STEM", "Mecanismos simples y relación causa-efecto."),
                            note("Uso evolutivo", "Admite juego libre y pequeños retos de transporte."),
                            note("Seguridad", "Supervisión por sus accesorios sueltos.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-animal-sobre-animal",
                    "HABA Animal sobre Animal",
                    "Experimentar equilibrio y centro de gravedad",
                    "Apilar figuras según el dado convierte el equilibrio en un problema visible: probar una posición, observar por qué cae y corregirla.",
                    List.of(
                            "Reto directo de equilibrio y planificación espacial.",
                            "29 figuras de madera para configuraciones variadas.",
                            "Reglas breves, sin lectura durante el turno."
                    ),
                    List.of(
                            "Es competitivo y exige tolerar que una torre se derrumbe.",
                            "No desarrolla construcción estructural al nivel de un set de piezas."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Reto STEM", "Equilibrio, apoyo y distribución del peso."),
                            note("Uso evolutivo", "La dificultad cambia con cada apilado."),
                            note("Seguridad", "No apto para menores de 3 años por piezas pequeñas.")
                    )
            ),
            new EditorialEntry(
                    "haba-puzles-cuatro-estaciones",
                    "HABA Puzzles Las Cuatro Estaciones",
                    "Practicar análisis visual y orientación espacial",
                    "Cuatro puzles de 15 piezas permiten comparar patrones, rotar piezas y completar una tarea con una dificultad acotada.",
                    List.of(
                            "Cuatro escenas para alternar el reto.",
                            "Quince piezas por puzle, asumibles en una sesión.",
                            "Cartón resistente y figuras de madera declaradas."
                    ),
                    List.of(
                            "El reto puede quedarse corto si ya completa puzles mayores.",
                            "No incluye un mecanismo ni experimentos científicos."
                    ),
                    "Desde 3 años",
                    List.of(
                            note("Reto STEM", "Patrones, rotación mental y resolución de problemas."),
                            note("Uso evolutivo", "Cuatro puzles permiten progresar sin mezclar todas las piezas."),
                            note("Seguridad", "Conviene guardar cada conjunto por separado.")
                    )
            ),
            new EditorialEntry(
                    "juego-mesa-dobble-kids",
                    "Dobble Kids",
                    "Reconocer patrones con rapidez",
                    "Buscar el símbolo común entre cartas entrena comparación visual, atención selectiva y clasificación bajo una regla sencilla.",
                    List.of(
                            "Partidas declaradas de unos 15 minutos.",
                            "Regla visual sin necesidad de leer.",
                            "De 2 a 8 jugadores."
                    ),
                    List.of(
                            "La rapidez puede frustrar a quien necesita más tiempo.",
                            "Es un juego de observación, no un kit de experimentación."
                    ),
                    "Desde 4 años",
                    List.of(
                            note("Reto STEM", "Reconocimiento de patrones y clasificación visual."),
                            note("Uso evolutivo", "La velocidad aumenta con la práctica."),
                            note("Seguridad", "Las cartas deben mantenerse lejos de menores que puedan morderlas.")
                    )
            )
    );

    private static final List<EditorialEntry> MONTESSORI_3 = List.of(
            entry(
                    "montessori-janod-animales",
                    "Janod Magneti'Book Animales",
                    "Formar animales con modelos magnéticos",
                    "Libro magnético con treinta imanes y diez fichas para recrear animales, de 3 a 8 años. A los 3 años la consigna es mirar la ficha, coger la pieza y colocarla; un adulto elige primero los modelos más sencillos.",
                    List.of(
                            "Diez modelos para formar animales con dificultad creciente.",
                            "Treinta piezas magnéticas en formato libro.",
                            "Rango declarado de 3 a 8 años."
                    ),
                    List.of(
                            "Las fichas piden un adulto que elija el modelo inicial.",
                            "Las piezas magnéticas se pierden si se abre el libro en el suelo."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; empieza por los modelos más sencillos."),
                    note("Actividad", "Formar animales siguiendo un modelo, no solo meter en un hueco."),
                    note("Piezas", "Treinta imanes y diez fichas modelo."),
                    note("Seguridad", "Supervisión cercana; recuenta las piezas al guardar.")
            ),
            entry(
                    "montessori-melissa-tres-puzzles",
                    "Melissa & Doug pack de 3 puzzles de encaje",
                    "Tres escenas de madera en una sesión",
                    "Pack con tres tableros de encaje de granja, safari y vehículos, a partir de 3 años. Cada uno se termina en una sesión corta; se puede rotar el tema sin mezclar piezas.",
                    List.of(
                            "Tres tableros con ilustraciones a todo color.",
                            "Piezas gruesas de madera fáciles de agarrar.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "Tres cajas distintas: conviene guardar cada puzle por separado.",
                            "No sustituye un material con dificultad progresiva en la misma caja."
                    ),
                    "Desde 3 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; pocas piezas por tablero."),
                    note("Actividad", "Encajar una escena reconocible, no clasificar un cubo."),
                    note("Piezas", "Tres tableros de madera con piezas gruesas."),
                    note("Seguridad", "Piezas grandes; supervisión si hay hermanos más pequeños.")
            ),
            entry(
                    "montessori-janod-ballenas",
                    "Janod clasificar ballenas por colores",
                    "Clasificar animales marinos por color",
                    "Cuatro ballenas de madera, doce animales marinos y pinzas para introducir cada pieza en la ballena del color correspondiente, de 2 a 5 años. A los 3 años el gesto es coger con la pinza, nombrar el color y completar cada ballena.",
                    List.of(
                            "Cuatro colores: cada ballena recibe tres animales.",
                            "Doce piezas de madera y pinzas para motricidad fina.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Las pinzas piden práctica; se puede empezar con la mano.",
                            "Doce piezas sueltas: recuenta al terminar."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Cabe en 3 años y admite crecer hasta 5."),
                    note("Actividad", "Clasificar por color, no encajar un cubo."),
                    note("Piezas", "Cuatro ballenas, doce animales y pinzas de madera."),
                    note("Seguridad", "Piezas de tamaño medio y pinzas; supervisión cercana.")
            ),
            entry(
                    "montessori-janod-tropik",
                    "Janod Mis primeras formas Tropik",
                    "Encajar nueve formas en orden",
                    "Soporte de madera FSC con seis formas y tres pájaros para encajar en el orden correcto, de 1 a 3 años. A los 3 años la consigna sigue siendo una: coger la pieza, nombrar forma y color, y meterla en su hueco.",
                    List.of(
                            "Nueve piezas de madera FSC con pintura al agua.",
                            "Cada pieza encaja en un hueco concreto.",
                            "Rango declarado de 1 a 3 años."
                    ),
                    List.of(
                            "El máximo declarado es 3 años: si ya encaja con soltura, Magneti'Book Animales da más margen.",
                            "Nueve piezas se acaban rápido si ya clasifica sin ayuda."
                    ),
                    "1-3 años",
                    note("Encaje a los 3 años", "Tope de edad 3 años; buen puente si aún necesita pocos elementos."),
                    note("Actividad", "Encajar formas y colores en un soporte, no un tablero de escena."),
                    note("Piezas", "Seis formas y tres pájaros de madera."),
                    note("Seguridad", "Supervisión; piezas sueltas al terminar.")
            ),
            entry(
                    "puzle-melissa-granja-peg",
                    "Melissa & Doug puzle de granja con agarres",
                    "Encajar ocho animales con pomos",
                    "Tablero de madera con ocho piezas con pomos para encajar animales de granja, de 2 a 4 años. A los 3 años añade vocabulario de animales al encaje y el tablero sujeta las piezas mientras se completa.",
                    List.of(
                            "Ocho piezas con pomos de madera.",
                            "Escena de granja reconocible.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "Ocho piezas pueden agotarse rápido si ya encaja con soltura.",
                            "No es el puzle con sonidos; es encaje con agarres."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Pocas piezas gruesas, fáciles de completar."),
                    note("Actividad", "Encajar animales y nombrarlos."),
                    note("Piezas", "Ocho piezas de madera con pomos en un tablero."),
                    note("Seguridad", "Piezas grandes; supervisión si hay hermanos más pequeños.")
            )
    );

    private static final List<EditorialEntry> MONTESSORI_4 = List.of(
            entry(
                    "juego-montessori-formas",
                    "Melissa & Doug cubo de formas",
                    "Clasificar formas y colores con piezas grandes",
                    "Cubo de madera con 12 piezas grandes para encajar por la forma. A los 4 años sigue siendo útil si aún clasifica con ayuda; si ya lo domina, Goula o el arca dan más margen.",
                    List.of(
                            "Doce piezas grandes, pensadas para manos pequeñas.",
                            "Clasificación de formas y colores en un solo objeto.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "El máximo declarado es 4 años: si ya clasifica sin esfuerzo, prioriza Goula o el arca.",
                            "No incluye un segundo nivel de dificultad en la misma caja."
                    ),
                    "2-4 años",
                    note("Encaje a los 4 años", "Edad declarada hasta 4 años; piezas grandes y consigna clara."),
                    note("Actividad", "Encajar y nombrar; una sola consigna."),
                    note("Piezas", "12 piezas grandes de madera."),
                    note("Seguridad", "Supervisión cercana; no es un juguete para dejar solo.")
            ),
            entry(
                    "montessori-goula-baby-shapes",
                    "Goula Baby Shapes",
                    "Aumentar la dificultad sin cambiar de juguete",
                    "Láminas y piezas de madera para encajar formas y colores con dificultad progresiva. A los 4 años permite empezar por láminas medias y añadir las más exigentes.",
                    List.of(
                            "Dificultad progresiva con varias láminas.",
                            "Piezas de madera para encajar formas y colores.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Hay que guardar las láminas que no se usan para no mezclar el reto.",
                            "Un adulto debe proponer el siguiente nivel; no se explica solo."
                    ),
                    "2-5 años",
                    note("Encaje a los 4 años", "Cabe en 4 años y admite crecer hasta 5."),
                    note("Actividad", "Encaje con niveles, no un cubo único."),
                    note("Piezas", "Láminas y piezas de madera."),
                    note("Seguridad", "Revisa que no falten piezas pequeñas sueltas.")
            ),
            entry(
                    "montessori-formas-geometricas",
                    "Melissa & Doug puzzle de formas geométricas",
                    "Reconocer ocho formas gruesas",
                    "Ocho piezas gruesas de madera para reconocer formas y colores. Se termina en una sesión corta, ideal como calentamiento antes de puzles más largos.",
                    List.of(
                            "Ocho piezas gruesas de madera.",
                            "Formas y colores reconocibles.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Ocho piezas pueden agotarse rápido si ya encaja con soltura.",
                            "No es un puzle de imagen; es un tablero de formas."
                    ),
                    "2-5 años",
                    note("Encaje a los 4 años", "Pocas piezas gruesas, fáciles de completar."),
                    note("Actividad", "Encajar formas, no montar una escena."),
                    note("Piezas", "Ocho piezas de madera."),
                    note("Seguridad", "Piezas gruesas; supervisión si hay hermanos más pequeños.")
            ),
            entry(
                    "montessori-noah-ark",
                    "Melissa & Doug Arca de Noé clasificadora",
                    "Clasificar animales por la forma",
                    "Arca de madera con 26 piezas de animales para clasificar por forma. A los 4 años conviene sacar un subconjunto al empezar y nombrar los animales.",
                    List.of(
                            "Veintiséis piezas de animales para clasificar.",
                            "El arca guarda las piezas al terminar.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "26 piezas son más que un cubo de 12: conviene sacar solo unas pocas al empezar.",
                            "Si hay menores de 3 años, revisa el tamaño de cada figura."
                    ),
                    "2-5 años",
                    note("Encaje a los 4 años", "Útil para nombrar animales y clasificar por silueta."),
                    note("Actividad", "Clasificar por silueta, no solo encajar un cubo."),
                    note("Piezas", "26 figuras; saca un subconjunto al inicio."),
                    note("Seguridad", "Comprueba que ninguna figura sea demasiado pequeña.")
            ),
            entry(
                    "plantoys-ata-zapato",
                    "PlanToys Ata el zapato",
                    "Practicar nudos en madera de caucho",
                    "Juguete de madera de caucho para 3 a 8 años, con tintes al agua. No es encaje de formas, pero encaja en materiales Montessori de autonomía y motricidad fina.",
                    List.of(
                            "Edad declarada de 3 a 8 años.",
                            "Madera de caucho y tintes al agua según la ficha.",
                            "Formato portable para practicar nudos."
                    ),
                    List.of(
                            "No ata el zapato del niño: es un material de ensayo.",
                            "Un adulto debe mostrar el nudo las primeras veces."
                    ),
                    "3-8 años",
                    note("Encaje a los 4 años", "Rango 3-8 años; motricidad fina y autonomía."),
                    note("Actividad", "Practicar nudos, no clasificar formas."),
                    note("Madera", "Caucho y tintes al agua."),
                    note("Seguridad", "Cordones bajo supervisión.")
            )
    );

    private static final List<EditorialEntry> MONTESSORI_5 = List.of(
            entry(
                    "montessori-goula-baby-shapes",
                    "Goula Baby Shapes",
                    "Aumentar la dificultad sin cambiar de juguete",
                    "Láminas y piezas de madera para encajar formas y colores con dificultad progresiva. A los 5 años conviene usar las láminas más exigentes y proponer retos de clasificación.",
                    List.of(
                            "Dificultad progresiva con varias láminas.",
                            "Piezas de madera para encajar formas y colores.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Hay que guardar las láminas que no se usan para no mezclar el reto.",
                            "Un adulto debe proponer el siguiente nivel; no se explica solo."
                    ),
                    "2-5 años",
                    note("Encaje a los 5 años", "Está en el tope del rango; usa las láminas más exigentes."),
                    note("Actividad", "Encaje con niveles, no un cubo único."),
                    note("Piezas", "Láminas y piezas de madera."),
                    note("Seguridad", "Revisa que no falten piezas pequeñas sueltas.")
            ),
            entry(
                    "montessori-formas-geometricas",
                    "Melissa & Doug puzzle de formas geométricas",
                    "Reconocer ocho formas gruesas",
                    "Ocho piezas gruesas de madera para reconocer formas y colores. A los 5 años puede quedarse corto si ya monta puzles de 15 piezas; sirve como calentamiento.",
                    List.of(
                            "Ocho piezas gruesas de madera.",
                            "Formas y colores reconocibles.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Ocho piezas pueden agotarse rápido si ya encaja con soltura.",
                            "No es un puzle de imagen; es un tablero de formas."
                    ),
                    "2-5 años",
                    note("Encaje a los 5 años", "Pocas piezas; combínalo con Goula o el arca."),
                    note("Actividad", "Encajar formas, no montar una escena."),
                    note("Piezas", "Ocho piezas de madera."),
                    note("Seguridad", "Piezas gruesas; supervisión si hay hermanos más pequeños.")
            ),
            entry(
                    "montessori-noah-ark",
                    "Melissa & Doug Arca de Noé clasificadora",
                    "Clasificar animales por la forma",
                    "Arca de madera con 26 piezas de animales para clasificar por forma. A los 5 años puede usar más figuras a la vez y nombrar grupos de animales.",
                    List.of(
                            "Veintiséis piezas de animales para clasificar.",
                            "El arca guarda las piezas al terminar.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "26 piezas piden recogida al terminar.",
                            "Si hay menores de 3 años, revisa el tamaño de cada figura."
                    ),
                    "2-5 años",
                    note("Encaje a los 5 años", "Clasificar por silueta y vocabulario de animales."),
                    note("Actividad", "Clasificar por silueta, no solo encajar un cubo."),
                    note("Piezas", "26 figuras; saca un subconjunto si abruma."),
                    note("Seguridad", "Comprueba que ninguna figura sea demasiado pequeña.")
            ),
            entry(
                    "plantoys-ata-zapato",
                    "PlanToys Ata el zapato",
                    "Practicar nudos en madera de caucho",
                    "Juguete de madera de caucho para 3 a 8 años. Practica nudos y autonomía de vestirse; encaja como material Montessori de motricidad fina.",
                    List.of(
                            "Edad declarada de 3 a 8 años.",
                            "Madera de caucho y tintes al agua según la ficha.",
                            "Formato portable para practicar nudos."
                    ),
                    List.of(
                            "No ata el zapato del niño: es un material de ensayo.",
                            "Un adulto debe mostrar el nudo las primeras veces."
                    ),
                    "3-8 años",
                    note("Encaje a los 5 años", "Rango 3-8; autonomía y motricidad fina."),
                    note("Actividad", "Practicar nudos, no clasificar formas."),
                    note("Madera", "Caucho y tintes al agua."),
                    note("Seguridad", "Cordones bajo supervisión.")
            ),
            entry(
                    "cuentas-melissa-doug",
                    "Melissa & Doug cuentas de madera",
                    "Ensartar y contar con madera",
                    "27 cuentas de madera, números del 1 al 10 y 2 cordones, a partir de 3 años. Motricidad fina y conteo; cordones y cuentas piden supervisión.",
                    List.of(
                            "Madera, 27 piezas y 2 cordones.",
                            "Edad declarada a partir de 3 años.",
                            "Juego sin pantallas."
                    ),
                    List.of(
                            "Riesgo de asfixia si se usan mal: no se deja solo.",
                            "No es un cubo de encaje."
                    ),
                    "3-6 años",
                    note("Encaje a los 5 años", "Edad mínima 3 años; ensartar y contar."),
                    note("Actividad", "Ensartar y clasificar, no encajar formas."),
                    note("Madera", "Cuentas de madera."),
                    note("Seguridad", "Cordones bajo supervisión.")
            )
    );

    private static final List<EditorialEntry> PUZZLES_3 = List.of(
            entry(
                    "puzle-madera-animales",
                    "Melissa & Doug puzle de animales del safari",
                    "Piezas grandes de animales reconocibles",
                    "Puzle de madera de animales del safari con piezas grandes para manos pequeñas. A los 3 años el objetivo es terminar una imagen, no un recuento alto de piezas.",
                    List.of(
                            "Piezas grandes de madera.",
                            "Motivo de animales reconocible.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Si ya monta puzles de 16 piezas, este puede quedarse corto.",
                            "No incluye pomos si el modelo de tu ficha es de pieza plana; confirma el agarre."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Piezas grandes y un motivo conocido."),
                    note("Dificultad", "Una escena, no un pack progresivo."),
                    note("Soporte", "Madera; revisa si el tablero sujeta las piezas."),
                    note("Seguridad", "Sin recuento alto de piezas sueltas diminutas.")
            ),
            entry(
                    "puzle-melissa-mascotas",
                    "Melissa & Doug puzle de mascotas",
                    "Ocho piezas gruesas que también se sostienen de pie",
                    "Puzle de madera de mascotas con ocho piezas gruesas y un tablero con pistas visuales debajo. A los 3 años el objetivo es terminar el tablero; las piezas se sostienen de pie para jugar después.",
                    List.of(
                            "Ocho piezas gruesas de madera fáciles de agarrar.",
                            "Mascotas reconocibles; las piezas se sostienen de pie.",
                            "Imágenes a color bajo cada pieza."
                    ),
                    List.of(
                            "Ocho piezas pueden quedarse cortas si ya monta 15 o 16.",
                            "Es un tablero de siluetas, no un puzle de escena de muchas piezas."
                    ),
                    "Desde 2 años",
                    note("Encaje a los 3 años", "Piezas grandes y un motivo de mascotas conocido."),
                    note("Dificultad", "Encaje guiado por silueta, no un puzle de 16 piezas sueltas."),
                    note("Soporte", "Tablero de madera que sujeta las piezas."),
                    note("Seguridad", "Piezas grandes; supervisión si se usan como figuras de pie.")
            ),
            entry(
                    "puzle-educa-selva",
                    "Educa My First animales de la selva",
                    "Empezar con 5 piezas y subir a 8",
                    "Cuatro puzles progresivos de 5 a 8 piezas para empezar a encajar. A los 3 años permite acertar pronto y cambiar de escena sin mezclar 50 piezas.",
                    List.of(
                            "Cuatro puzles de 5 a 8 piezas.",
                            "Progresión de dificultad dentro de la misma caja.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "8 piezas máximas: si ya monta 15 o 16, pasa a HABA o Educa Disney.",
                            "Cartón: confirma que es grueso en la ficha vigente."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Empieza por 5 piezas y sube cuando termine sin ayuda."),
                    note("Dificultad", "Progresiva y acotada, no un único puzle largo."),
                    note("Soporte", "Cuatro escenas para no mezclar todas las piezas."),
                    note("Seguridad", "Guarda cada puzle aparte para no perder piezas.")
            ),
            entry(
                    "haba-puzles-cuatro-estaciones",
                    "HABA Puzzles Las Cuatro Estaciones",
                    "15 piezas y figuras de madera",
                    "Cuatro rompecabezas de 15 piezas a partir de 3 años, con figuras de madera de haya. Un paso más que 5-8 piezas, todavía asumible en una sesión.",
                    List.of(
                            "Cuatro puzles de 15 piezas, edad desde 3 años.",
                            "Figuras de madera de haya sostenible.",
                            "Cartón resistente para manos pequeñas."
                    ),
                    List.of(
                            "15 piezas pueden frustrar si aún no termina puzles de 8.",
                            "Las figuras de madera son pequeñas: no las dejes con menores de 3 años."
                    ),
                    "Desde 3 años",
                    note("Encaje a los 3 años", "Edad mínima declarada 3 años; 15 piezas piden un adulto al lado al principio."),
                    note("Dificultad", "Más pieza que My First, menos que un puzle de 24."),
                    note("Soporte", "Cuatro motivos; las figuras alargan el juego una vez montado."),
                    note("Seguridad", "Separa figuras si hay hermanos más pequeños.")
            ),
            entry(
                    "puzle-educa-disney-madera",
                    "Educa Disney Animals, 2 puzles de madera",
                    "16 piezas de madera a partir de 3 años",
                    "Dos puzles de 16 piezas de madera, recomendados a partir de 3 años. Sirven cuando 8 piezas ya se resuelven en un minuto.",
                    List.of(
                            "Dos puzles de 16 piezas de madera.",
                            "Edad declarada a partir de 3 años.",
                            "Motivos de animales Disney."
                    ),
                    List.of(
                            "16 piezas piden más paciencia que un puzle de silueta con tablero.",
                            "El motivo de personaje puede caducar; la madera no."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Indicada desde 3 años; empieza junto y retira ayuda al final."),
                    note("Dificultad", "16 piezas por escena, el recuento más alto de esta lista."),
                    note("Soporte", "Madera; confirma si hay marco en tu edición."),
                    note("Seguridad", "No mezcles las dos cajas en la misma mesa al empezar.")
            )
    );

    private static final List<EditorialEntry> SCOOTERS_3 = List.of(
            entry(
                    "patinete-micro-mini-deluxe",
                    "Micro Mini Deluxe LED",
                    "Empezar de pie con tres ruedas ligeras",
                    "Patinete de tres ruedas para 2 a 5 años: 1,95 kg declarados, giro por inclinación, manillar ajustable y luces LED sin pilas. A los 3 años cubre el primer uso de pie con poco peso que levantar.",
                    List.of(
                            "Tres ruedas, giro por inclinación y edad declarada de 2 a 5 años.",
                            "Peso declarado de 1,95 kg y carga máxima de 50 kg.",
                            "Manillar ajustable; las fichas del Mini Deluxe LED indican 48-68 cm.",
                            "Luces LED en las ruedas delanteras sin pilas y freno trasero."
                    ),
                    List.of(
                            "No declara sistema de plegado en esta ficha; el manillar sí se desmonta.",
                            "No sustituye una bicicleta sin pedales ni un modelo de dos ruedas."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Edad 2-5 años y manillar 48-68 cm, usable con un niño bajo."),
                    note("Estabilidad", "Tres ruedas y giro por inclinación; se usa de pie."),
                    note("Manejo", "Peso declarado de 1,95 kg y carga máxima de 50 kg."),
                    note("Seguridad", "Freno trasero; casco, calzado cerrado y supervisión.")
            ),
            entry(
                    "patinete-molto-maxi",
                    "MOLTO Maxi Scooter",
                    "Una primera opción sencilla de 3 a 5 años",
                    "Patinete de tres ruedas indicado para 3 a 5 años, con luces LED, manillar de 57 a 67 cm, freno trasero y montaje sin herramientas.",
                    List.of(
                            "Edad declarada de 3 a 5 años.",
                            "Manillar regulable entre 57 y 67 cm.",
                            "Luces LED, plataforma antideslizante y freno trasero.",
                            "Montaje sin herramientas según la ficha."
                    ),
                    List.of(
                            "El manillar parte de 57 cm, por encima de los 48 cm del Mini Deluxe LED.",
                            "La ficha consultada no declara plegado ni el peso del patinete; la carga máxima es 50 kg."
                    ),
                    "3-5 años",
                    note("Encaje a los 3 años", "La edad mínima declarada es 3 años."),
                    note("Estabilidad", "Tres ruedas y plataforma antideslizante."),
                    note("Manejo", "Freno trasero y carga máxima de 50 kg; no hay peso del patinete en la ficha."),
                    note("Seguridad", "Casco y zona sin tráfico. La ficha recomienda no usarlo en circulación.")
            ),
            entry(
                    "patinete-globber-junior-foldable",
                    "Globber Junior Foldable Lights",
                    "Plegar y llevar el patinete",
                    "Patinete de tres ruedas plegable desde 2 años, con luces LED por dinamo, bloqueo de dirección, manillar de 54, 61 y 68 cm y carga máxima de 50 kg.",
                    List.of(
                            "Plegado con botón y modo carrito.",
                            "Edad declarada a partir de 2 años y carga máxima de 50 kg.",
                            "Manillar de 3 alturas: 54, 61 y 68 cm.",
                            "Luces LED sin batería y bloqueo de dirección."
                    ),
                    List.of(
                            "El manillar llega hasta 68 cm; no cubre a un niño ya alto que necesite 74 cm.",
                            "El plegado y el bloqueo piden que un adulto los configure."
                    ),
                    "Desde 2 años",
                    note("Encaje a los 3 años", "Indicada desde 2 años; 54 cm de manillar mínimo ayuda a los más bajos."),
                    note("Estabilidad", "Tres ruedas y bloqueo de dirección opcional."),
                    note("Manejo", "Plegado y modo carrito; carga máxima de 50 kg."),
                    note("Seguridad", "Freno trasero ancho; casco y zona sin tráfico.")
            ),
            entry(
                    "patinete-micro-mini-3en1",
                    "Micro Mini 3en1 Deluxe Plus",
                    "Empezar sentado y pasar a pie",
                    "Patinete evolutivo de tres ruedas para 1 a 5 años: asiento, reposapiés y bastón de empuje extraíbles. A los 3 años cubre el paso de ir sentado a ir de pie con el mismo chasis de 1,95 kg y manillar 48-68 cm.",
                    List.of(
                            "Tres etapas: empuje con adulto, correpasillos sentado y patinete de pie.",
                            "Edad declarada de 1 a 5 años; manillar 48-68 cm.",
                            "Peso declarado de 1,95 kg y carga máxima de 50 kg (asiento 20 kg).",
                            "Asiento, reposapiés y bastón de empuje se retiran cuando ya se sostiene de pie."
                    ),
                    List.of(
                            "El asiento declara 20 kg: comprueba el peso real si aún lo usa sentado.",
                            "Hay más piezas que en un Mini Deluxe de pie; un adulto monta y retira los accesorios."
                    ),
                    "1-5 años",
                    note("Encaje a los 3 años", "Rango 1-5 años y manillar 48-68 cm; a esta edad suele usarse ya de pie o como correpasillos."),
                    note("Estabilidad", "Tres ruedas; el asiento añade un uso sentado distinto del triciclo con pedales."),
                    note("Manejo", "1,95 kg; asiento 20 kg y patinete 50 kg."),
                    note("Seguridad", "Freno trasero; casco y supervisión en las tres etapas.")
            ),
            entry(
                    "triciclo-chicco-u-go",
                    "Chicco U-GO 2en1",
                    "Pedalear sentado con mango de adulto",
                    "Triciclo de 18 meses a 5 años y hasta 20 kg, con mango telescópico o pedaleo libre. A los 3 años el mango sigue siendo útil en paseos largos. No es un patinete.",
                    List.of(
                            "Edad declarada de 18 meses a 5 años.",
                            "Mango telescópico extraíble y pedaleo libre.",
                            "Estructura metálica, cinturón y cesta.",
                            "Bloqueo de pedales y manillar en el modo guiado."
                    ),
                    List.of(
                            "Carga máxima declarada de 20 kg; comprueba el peso real del niño.",
                            "Se usa sentado: no practica el equilibrio de pie del patinete."
                    ),
                    "18 meses-5 años",
                    note("Encaje a los 3 años", "Hasta 5 años y 20 kg; el mango de adulto encaja en esta etapa."),
                    note("Estabilidad", "Tres ruedas y uso sentado, distinto del patinete."),
                    note("Manejo", "Mango extraíble; ruedas de goma antipinchazos según la ficha."),
                    note("Seguridad", "Cinturón en el modo guiado; supervisión igualmente necesaria.")
            )
    );

    private static final List<EditorialEntry> TOWERS_3 = List.of(
            entry(
                    "torre-costway-plegable",
                    "COSTWAY Plegable 3 en 1",
                    "Plegar y guardar en una cocina pequeña",
                    "Torre plegable de madera indicada a partir de 3 años, con estructura en A, barra de seguridad y pizarra. Encaje directo con esta página: la ficha parte de los 3 años.",
                    List.of(
                            "Recomendada a partir de 3 años en la ficha.",
                            "Se pliega y se convierte en mesa sin herramientas.",
                            "Estructura en A, patas antivuelco y barra de seguridad.",
                            "Carga máxima declarada de 60 kg y 91 cm de alto."
                    ),
                    List.of(
                            "Plegada sigue midiendo 91 cm de alto.",
                            "La hebilla debe quedar bloqueada; no es un juguete de trepa."
                    ),
                    "Recomendada desde 3 años",
                    note("Encaje a los 3 años", "La ficha la recomienda a partir de 3 años."),
                    note("Estabilidad", "Estructura en A y patas antivuelco."),
                    note("Regulación", "Plegado y modo mesa; confirma la altura de plataforma."),
                    note("Seguridad", "Adulto presente; nunca junto a fuego o agua hirviendo.")
            ),
            entry(
                    "torre-yoleo-transformer",
                    "YOLEO Transformer",
                    "Plegar y convertir en mesa más adelante",
                    "Torre de madera de nogal de 42 x 45 x 86 cm, plegable y convertible en silla y escritorio. A los 3 años se usa como torre; el escritorio alarga la vida útil.",
                    List.of(
                            "Se pliega y se convierte en silla y escritorio sin herramientas.",
                            "Pizarra magnética de doble cara.",
                            "Ángulos redondeados y madera de nogal según la ficha."
                    ),
                    List.of(
                            "Ocupa 42 x 45 cm desplegada; revisa huecos tras el montaje.",
                            "La pizarra no sustituye la barandilla ni la supervisión."
                    ),
                    "Uso infantil con adulto",
                    note("Encaje a los 3 años", "La torre sirve si llega a la encimera; el niño no sube solo."),
                    note("Estabilidad", "La ficha insiste en montaje con kit incluido."),
                    note("Regulación", "Modo torre o mesa; mide tu encimera."),
                    note("Seguridad", "Supervisión constante a esta edad.")
            ),
            entry(
                    "torre-hauck-learn-n-explore",
                    "hauck Learn N Explore",
                    "Tres alturas en madera de haya FSC",
                    "Torre de madera de haya FSC, de 1 a 6 años, con plataforma en 3 alturas (33 a 45 cm) y 90,5 cm de alto. A los 3 años suele usarse una altura intermedia, no la más alta desde el primer día.",
                    List.of(
                            "Tres alturas de plataforma, de 33 a 45 cm.",
                            "Madera de haya con certificación FSC.",
                            "Carga máxima declarada de 40 kg."
                    ),
                    List.of(
                            "La ficha no declara plegado.",
                            "Comprueba que la altura llegue a tu encimera sin asomarse."
                    ),
                    "1-6 años",
                    note("Encaje a los 3 años", "Empieza por 33 o una altura intermedia; no dejes la más alta si aún trepa por fuera."),
                    note("Estabilidad", "Estructura de haya; el fabricante la describe como estable."),
                    note("Regulación", "Tres posiciones de plataforma."),
                    note("Seguridad", "Uso solo con adulto.")
            ),
            entry(
                    "torre-bey-co",
                    "BEY & CO Torre de aprendizaje",
                    "Tres alturas con patas anticaída",
                    "Torre de madera con 3 alturas, superficie antideslizante, patas anticaída y certificación EN-71. Indicada desde que se mantiene de pie; a los 3 años hay que fijar una altura que no invite a trepar.",
                    List.of(
                            "Tres alturas de peldaño.",
                            "Certificación EN-71, patas anticaída y gomas antideslizantes.",
                            "Madera barnizada, fácil de limpiar según la ficha."
                    ),
                    List.of(
                            "La ficha no declara plegado ni conversión a escritorio.",
                            "Indicada desde los 12 meses: a los 3 años elige la altura que llegue a la encimera sin asomarse."
                    ),
                    "Desde que se mantiene de pie",
                    note("Encaje a los 3 años", "Ajusta la altura para que los brazos queden sobre la encimera."),
                    note("Estabilidad", "Patas anticaída y superficie antideslizante."),
                    note("Regulación", "Tres alturas, sin plegado declarado."),
                    note("Seguridad", "Un solo niño; lejos del fuego.")
            ),
            entry(
                    "torre-maxi-cosi-toucan",
                    "Maxi-Cosi Toucan 3 en 1",
                    "Torre convertible en mesa y silla",
                    "Torre de madera FSC que se transforma en mesa y silla o en taburete, de 46 x 91 x 43,3 cm y 8,7 kg, con 3 alturas (29,5 a 41,4 cm). A los 3 años el modo torre cubre 10-30 kg.",
                    List.of(
                            "Conversión a mesa y silla, o a taburete.",
                            "Tres alturas, de 29,5 a 41,4 cm.",
                            "Madera FSC; 8,7 kg declarados."
                    ),
                    List.of(
                            "Con 8,7 kg pesa más que desplazar que una torre plegable de cocina.",
                            "El modo escritorio no se usa a los 3 años: es margen futuro."
                    ),
                    "Uso infantil con adulto",
                    note("Encaje a los 3 años", "Sirve si la altura de torre llega a tu encimera."),
                    note("Estabilidad", "Confirma bloqueos al convertir a mesa."),
                    note("Regulación", "Torre, mesa y taburete."),
                    note("Seguridad", "No la uses como juguete de trepa.")
            )
    );

    private static final List<EditorialEntry> TABLEWARE_3 = List.of(
            entry(
                    "vajilla-stor-mickey",
                    "Stor vajilla 3 piezas Mickey Mouse",
                    "Llevar plato, cuenco y vaso a la mesa",
                    "Set de plato, cuenco y vaso de 260 ml, plástico libre de BPA, base antideslizante y apto para microondas. El rango 3-6 años coincide con esta página.",
                    List.of(
                            "Tres piezas de mesa: plato, cuenco y vaso de 260 ml.",
                            "Base antideslizante en superficie lisa.",
                            "Libre de BPA y apto para microondas.",
                            "Edad declarada de 3 a 6 años."
                    ),
                    List.of(
                            "El diseño de personaje puede caducar; el set sigue siendo plástico de mesa.",
                            "No incluye cubiertos."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Rango 3-6 años y vaso abierto de 260 ml."),
                    note("Estabilidad", "Base antideslizante en superficie lisa."),
                    note("Piezas", "Plato, cuenco y vaso; sin cubiertos."),
                    note("Cuidado", "Apto para microondas; revisa el marcado de alimento.")
            ),
            entry(
                    "vajilla-fun-house",
                    "Fun House vajilla 3 piezas",
                    "Un set reutilizable de tamaño mesa",
                    "Juego de polipropileno con plato de 22 cm, cuenco de 16 cm y vaso de 220 ml, reutilizable y apto para microondas. El plato es de mesa, no de aprendizaje de bebé.",
                    List.of(
                            "Plato de 22 cm, cuenco de 16 cm y vaso de 220 ml.",
                            "Apto para microondas.",
                            "Rango declarado de 3 a 8 años."
                    ),
                    List.of(
                            "La ficha no declara base antideslizante ni cubiertos.",
                            "El motivo ilustrado no añade función."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Tamaño de mesa diaria desde 3 años."),
                    note("Estabilidad", "Sin base antideslizante declarada."),
                    note("Piezas", "Plato, cuenco y vaso."),
                    note("Cuidado", "Microondas; confirma lavavajillas en la ficha vigente.")
            ),
            entry(
                    "vajilla-twistshake-dividido",
                    "Twistshake plato con compartimentos",
                    "Separar la comida sin que vuele el plato",
                    "Plato de 20 cm con 3 compartimentos, tapa y base antideslizante, libre de BPA. Apto para microondas y lavavajillas. A los 3 años sigue siendo útil si no quiere mezclar.",
                    List.of(
                            "Tres compartimentos y tapa.",
                            "Base antideslizante; Click-Mat se vende aparte.",
                            "Libre de BPA, microondas y cubeta superior del lavavajillas."
                    ),
                    List.of(
                            "Es un plato, no un set: no incluye vaso ni cubiertos.",
                            "Indicada desde 6 meses: a los 3 años no es un plato de bebé, pero tampoco un set completo."
                    ),
                    "Desde 6 meses",
                    note("Encaje a los 3 años", "Los compartimentos ayudan si aún rechaza mezclar alimentos."),
                    note("Estabilidad", "Base antideslizante; Click-Mat opcional."),
                    note("Piezas", "Plato y tapa."),
                    note("Cuidado", "Microondas y lavavajillas (cubeta superior).")
            ),
            entry(
                    "vaso-munchkin-miracle-360",
                    "Munchkin Miracle 360 con asas",
                    "Beber sin tetina y con menos derrames",
                    "Lote de 2 vasos de 207 ml con borde 360°, válvula antigoteo y asas. A los 3 años cubre el paso del biberón al vaso abierto, no sustituye el vaso de 260 ml del set Stor.",
                    List.of(
                            "Borde 360° sin tetina.",
                            "Dos unidades de 207 ml con asas.",
                            "Libre de BPA y apto para lavavajillas."
                    ),
                    List.of(
                            "No incluye plato ni cubiertos.",
                            "Hay que limpiar la válvula."
                    ),
                    "Desde 6 meses",
                    note("Encaje a los 3 años", "Útil si aún derrama un vaso abierto; no es obligatorio si ya bebe del vaso de mesa."),
                    note("Estabilidad", "Asas; la válvula reduce derrames, no los elimina si se agita."),
                    note("Piezas", "Solo vasos."),
                    note("Cuidado", "Lavavajillas; desmonta la válvula según el manual.")
            ),
            entry(
                    "cuenco-twistshake-tapa",
                    "Twistshake cuenco con tapa",
                    "Guardar lo que no se termina",
                    "Cuenco de PP y silicona con tapa, libre de BPA. Complementa el plato; no sustituye un set de 3 piezas.",
                    List.of(
                            "Tapa para guardar o llevar.",
                            "Libre de BPA.",
                            "Indicada desde 6 meses."
                    ),
                    List.of(
                            "Es un cuenco, no un set de mesa.",
                            "La ficha de este ASIN no detalla el volumen."
                    ),
                    "Desde 6 meses",
                    note("Encaje a los 3 años", "Útil para yogur, fruta o merienda fuera de casa."),
                    note("Estabilidad", "Silicona y PP; confirma antideslizante en tu color."),
                    note("Piezas", "Cuenco y tapa."),
                    note("Cuidado", "Revisa microondas y lavavajillas en la ficha.")
            )
    );

    private static final List<EditorialEntry> GIFTS_3 = List.of(
            entry(
                    "juego-montessori-formas",
                    "Melissa & Doug cubo de formas",
                    "Un regalo para aprender encajando",
                    "Doce piezas grandes en un cubo de madera: se usa el mismo día y se puede repetir. Encaja como primer regalo educativo si aún no tiene un clasificador de formas.",
                    List.of(
                            "Uso inmediato, sin reglas largas.",
                            "Piezas grandes y un solo objeto que guardar.",
                            "Rango 2-4 años."
                    ),
                    List.of(
                            "Si ya tiene un cubo similar, elige puzle, movimiento o autonomía.",
                            "No cubre juego al aire libre."
                    ),
                    "2-4 años",
                    note("Necesidad", "Aprender jugando: formas y colores."),
                    note("Uso en casa", "Sesiones cortas en el suelo o la mesa."),
                    note("Regalo", "Ocasión amplia: cumpleaños o «porque sí»."),
                    note("Límite", "No sustituye movimiento ni autonomía.")
            ),
            entry(
                    "puzle-madera-animales",
                    "Melissa & Doug puzle de animales del safari",
                    "Un regalo breve que se puede terminar",
                    "Puzle de madera de piezas grandes. A los 3 años un regalo útil es el que se completa; no un recuento de piezas pensado para adultos.",
                    List.of(
                            "Se puede terminar en una sesión.",
                            "Motivo de animales reconocible.",
                            "Madera y piezas grandes."
                    ),
                    List.of(
                            "Si ya monta 16 piezas, este puzle puede ser poco reto.",
                            "No es un juguete de movimiento."
                    ),
                    "2-5 años",
                    note("Necesidad", "Motricidad fina y completar una tarea."),
                    note("Uso en casa", "Mesa o suelo, con un adulto al principio."),
                    note("Regalo", "Fácil de acertar si no conoces otros juguetes de la casa."),
                    note("Límite", "Una escena; no un pack para meses.")
            ),
            entry(
                    "bici-chicco-red-bullet",
                    "Chicco Red Bullet",
                    "Un regalo para moverse con seguridad",
                    "Bicicleta sin pedales de 2,7 kg declarados, ruedas antipinchazos de 10 pulgadas y sillín y manillar ajustables. El detalle está en la comparativa de bicicletas; aquí cuenta como idea de regalo de movimiento.",
                    List.of(
                            "Sillín y manillar ajustables.",
                            "Ruedas antipinchazos de 10 pulgadas.",
                            "Carga máxima declarada de 25 kg."
                    ),
                    List.of(
                            "Las ruedas de 10 pulgadas son menores que las de 30 cm de otros modelos.",
                            "Requiere casco, talla correcta y un espacio sin tráfico."
                    ),
                    "2-5 años",
                    note("Necesidad", "Movimiento y equilibrio sentado."),
                    note("Uso", "Exterior; no es un juguete de salón."),
                    note("Regalo", "Comprueba la entrepierna antes de comprarlo."),
                    note("Límite", "No es un patinete ni un triciclo.")
            ),
            entry(
                    "torre-yoleo-transformer",
                    "YOLEO Transformer",
                    "Un regalo de autonomía para la cocina",
                    "Torre plegable de madera de nogal, convertible en silla y escritorio, con pizarra magnética de doble cara. Tiene sentido si la familia cocina con el niño y hay un adulto dispuesto a estar al lado.",
                    List.of(
                            "Se pliega y se convierte en silla y escritorio.",
                            "Pizarra magnética de doble cara.",
                            "Uso en cocina o lavabo con un adulto."
                    ),
                    List.of(
                            "No es un juguete: sin supervisión no se regala.",
                            "Ocupa sitio aunque se pliegue."
                    ),
                    "Uso infantil con adulto",
                    note("Necesidad", "Participar en tareas reales de casa."),
                    note("Uso", "Cocina o lavabo, con adulto."),
                    note("Regalo", "Pregunta antes si ya hay torre o espacio."),
                    note("Límite", "No sustituye un juguete de juego libre.")
            ),
            entry(
                    "kit-manualidades-natural",
                    "Kit de manualidades con materiales naturales",
                    "Un regalo para crear sin pantallas",
                    "SES Creative Eco: plastilina de materias primas naturales y herramientas de madera, a partir de 3 años. Las sesiones son cortas; el valor está en acompañar, no en dejar el kit solo.",
                    List.of(
                            "Materias primas naturales y herramientas de madera según la ficha.",
                            "Uso sin pantalla.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "Requiere un adulto dispuesto a sentarse un rato.",
                            "La plastilina se gasta; no es un objeto de madera permanente."
                    ),
                    "Desde 3 años",
                    note("Necesidad", "Crear y manipular materiales."),
                    note("Uso", "Mesa, con un adulto."),
                    note("Regalo", "Encaja si la familia ya modela o pinta un poco."),
                    note("Límite", "No publicamos certificaciones que no figuren en ficha.")
            )
    );

    private static final List<EditorialEntry> GIFTS_5 = List.of(
            entry(
                    "set-construccion-magnetico",
                    "Set de construcción magnético",
                    "Un regalo para construir y razonar",
                    "Piezas magnéticas para construir estructuras tridimensionales. A los 5 años plantea retos de lógica espacial que pueden crecer añadiendo piezas.",
                    List.of(
                            "Construcción tridimensional con piezas magnéticas.",
                            "La dificultad se adapta al número de piezas.",
                            "Rango declarado de 4 a 5 años."
                    ),
                    List.of(
                            "Los imanes deben permanecer encapsulados.",
                            "Requiere revisar piezas sueltas si hay hermanos pequeños."
                    ),
                    "4-5 años",
                    note("Necesidad", "Aprender: construcción y lógica espacial."),
                    note("Uso", "Mesa o suelo, con supervisión al principio."),
                    note("Regalo", "Encaja si le gusta construir estructuras."),
                    note("Límite", "No sustituye movimiento ni autonomía.")
            ),
            entry(
                    "puzle-madera-animales",
                    "Melissa & Doug puzle de animales del safari",
                    "Un regalo breve que se puede terminar",
                    "Puzle de madera de piezas grandes. A los 5 años puede quedarse corto si ya monta 15 piezas; sirve como regalo seguro si no conoces otros juguetes.",
                    List.of(
                            "Se puede terminar en una sesión.",
                            "Motivo de animales reconocible.",
                            "Madera y piezas grandes."
                    ),
                    List.of(
                            "Si ya monta puzles más complejos, elige construcción o juego de mesa.",
                            "No es un juguete de movimiento."
                    ),
                    "2-5 años",
                    note("Necesidad", "Motricidad fina y completar una tarea."),
                    note("Uso", "Mesa o suelo."),
                    note("Regalo", "Fácil de acertar si no conoces la casa."),
                    note("Límite", "Una escena; no un pack para meses.")
            ),
            entry(
                    "bici-sin-pedales-basica",
                    "Bicicleta sin pedales básica",
                    "Un regalo para moverse con seguridad",
                    "Bicicleta sin pedales de cuadro ligero y sillín regulable. El detalle está en la comparativa de bicicletas; aquí cuenta como idea de regalo de movimiento.",
                    List.of(
                            "Cuadro ligero y sillín regulable.",
                            "Practica equilibrio sentado.",
                            "Rango declarado de 3 a 5 años."
                    ),
                    List.of(
                            "Requiere casco, talla correcta y un espacio sin tráfico.",
                            "Comprueba la entrepierna antes de regalar."
                    ),
                    "3-5 años",
                    note("Necesidad", "Movimiento y equilibrio sentado."),
                    note("Uso", "Exterior; no es un juguete de salón."),
                    note("Regalo", "Comprueba la talla antes de comprarlo."),
                    note("Límite", "No es un patinete ni un triciclo.")
            ),
            entry(
                    "juego-mesa-cooperativo",
                    "Juego de mesa cooperativo",
                    "Un regalo para jugar en familia",
                    "Partidas cortas en las que se gana o se pierde en equipo. A los 5 años introduce turnos y reglas sencillas con más paciencia que a los 4.",
                    List.of(
                            "Cooperativo: se gana o se pierde en equipo.",
                            "Partidas declaradas de duración breve.",
                            "Edad declarada a partir de 4 años."
                    ),
                    List.of(
                            "Requiere explicar las reglas la primera vez.",
                            "No sustituye juguetes de movimiento."
                    ),
                    "Desde 4 años",
                    note("Necesidad", "Juego social y reglas sencillas."),
                    note("Uso", "Mesa, con adulto la primera partida."),
                    note("Regalo", "Clásico de cumpleaños si ya juega en familia."),
                    note("Límite", "No es construcción ni movimiento.")
            ),
            entry(
                    "kit-manualidades-natural",
                    "Kit de manualidades con materiales naturales",
                    "Un regalo para crear sin pantallas",
                    "SES Creative Eco: plastilina de materias primas naturales y herramientas de madera. Las sesiones son más largas a los 5 años, pero sigue haciendo falta un adulto al principio.",
                    List.of(
                            "Materias primas naturales y herramientas de madera según la ficha.",
                            "Uso sin pantalla.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "Requiere un adulto dispuesto a sentarse un rato.",
                            "La plastilina se gasta; no es un objeto permanente."
                    ),
                    "Desde 3 años",
                    note("Necesidad", "Crear y manipular materiales."),
                    note("Uso", "Mesa, con un adulto."),
                    note("Regalo", "Encaja si la familia ya modela o pinta un poco."),
                    note("Límite", "No publicamos certificaciones que no figuren en ficha.")
            )
    );

    private static final List<EditorialEntry> SUSTAINABLE_3 = List.of(
            entry(
                    "cuentas-melissa-doug",
                    "Melissa & Doug cuentas de madera",
                    "Ensartar y contar sin pantallas",
                    "27 cuentas de madera, números del 1 al 10 y 2 cordones, a partir de 3 años. Motricidad fina; las cuentas y los cordones piden supervisión.",
                    List.of(
                            "Madera, 27 piezas y 2 cordones.",
                            "Edad declarada a partir de 3 años.",
                            "Juego sin pantallas."
                    ),
                    List.of(
                            "Riesgo de asfixia si se usan mal: no se deja solo.",
                            "No es un kit de pintar; es ensartar y contar."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; un adulto enseña el gesto."),
                    note("Materiales", "Cuentas de madera y cordones."),
                    note("Uso", "Ensartar y clasificar en sesiones cortas."),
                    note("Duración", "Se guarda y se saca; no es de un solo uso.")
            ),
            entry(
                    "small-foot-grua",
                    "Small Foot grúa de construcción",
                    "Madera FSC para el juego de obra",
                    "Grúa de madera FSC 100 % a partir de 3 años, giratoria 360°, con manivela, escalera interior y accesorios de obra. A los 3 años el gesto es empujar, girar y cargar; no pide nudos ni calzado real.",
                    List.of(
                            "Madera con certificado FSC 100 %.",
                            "Edad declarada a partir de 3 años.",
                            "Pluma móvil con manivela y giro 360°."
                    ),
                    List.of(
                            "Hay piezas sueltas y cuerda: supervisión en cada sesión.",
                            "No es un juguete de exterior ni de agua."
                    ),
                    "Desde 3 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; la manivela pide un adulto las primeras veces."),
                    note("Materiales", "Madera FSC 100 %."),
                    note("Uso", "Juego simbólico de obra, sesiones cortas."),
                    note("Duración", "Se puede combinar con otros vehículos de madera.")
            ),
            entry(
                    "green-toys-construccion",
                    "Green Toys vehículos de construcción",
                    "Plástico reciclado que se puede lavar",
                    "Tres vehículos de plástico 100 % reciclado, sin BPA, ftalatos ni PVC, lavables en lavavajillas. Rango 24 a 72 meses: los 3 años (36 meses) caen dentro.",
                    List.of(
                            "Plástico 100 % reciclado, sin BPA, ftalatos ni PVC.",
                            "Tres vehículos lavables en lavavajillas.",
                            "Rango declarado de 24 a 72 meses."
                    ),
                    List.of(
                            "No es madera: la sostenibilidad aquí es el plástico reciclado.",
                            "Piezas de obra y figuras: supervisión si hay menores de 3 años."
                    ),
                    "24-72 meses",
                    note("Encaje a los 3 años", "36 meses está dentro de 24-72."),
                    note("Materiales", "Plástico reciclado; tinta de soja según la ficha."),
                    note("Uso", "Suelo, arena o bañera; se lava entero."),
                    note("Duración", "Tres vehículos para rotar; sin electrónica.")
            ),
            entry(
                    "haba-puzles-cuatro-estaciones",
                    "HABA Puzzles Las Cuatro Estaciones",
                    "Cartón resistente y haya sostenible",
                    "Cuatro puzles de 15 piezas a partir de 3 años, con figuras de madera de haya. Encajar y luego jugar sobre la escena montada.",
                    List.of(
                            "Cuatro puzles de 15 piezas desde 3 años.",
                            "Figuras de madera de haya sostenible.",
                            "Cartón resistente."
                    ),
                    List.of(
                            "15 piezas pueden ser muchas si aún encaja siluetas de 5 piezas.",
                            "Las figuras son pequeñas."
                    ),
                    "Desde 3 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; empieza con un adulto."),
                    note("Materiales", "Cartón y haya sostenible."),
                    note("Uso", "Encajar y juego simbólico con las figuras."),
                    note("Duración", "Cuatro motivos para repetir.")
            ),
            entry(
                    "plantoys-ata-zapato",
                    "PlanToys Ata el zapato",
                    "Madera de caucho con tintes al agua",
                    "Juguete de madera de caucho para 3 a 8 años, con pegamento sin formaldehído y tintes al agua según la ficha. Sostenible, aquí, es material declarado y un objeto que se guarda, no un kit que se gasta en una tarde.",
                    List.of(
                            "Madera de caucho y tintes al agua según la ficha.",
                            "Edad declarada de 3 a 8 años.",
                            "Formato portable para practicar nudos."
                    ),
                    List.of(
                            "No ata el zapato del niño: es un material de ensayo.",
                            "Un adulto debe mostrar el nudo las primeras veces."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Rango 3-8 años; a los 3 el nudo pide ayuda."),
                    note("Materiales", "Caucho y tintes al agua; pegamento sin formaldehído en ficha."),
                    note("Uso", "Autonomía de vestirse en ratos cortos."),
                    note("Duración", "El mismo objeto acompaña varios cursos.")
            )
    );

    private static final List<EditorialEntry> DURABLE_3 = List.of(
            entry(
                    "puzle-madera-animales",
                    "Melissa & Doug puzle de animales del safari",
                    "Madera que se monta y se vuelve a guardar",
                    "Puzle de madera de animales del safari con piezas grandes. A los 3 años encaja como objeto que se saca, se termina y se guarda en caja: no es un kit de un solo uso ni un juguete con pilas.",
                    List.of(
                            "Piezas grandes de madera.",
                            "Motivo de animales reconocible.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Si ya monta puzles de 16 piezas, este puede quedarse corto.",
                            "No incluye pomos si el modelo de tu ficha es de pieza plana; confirma el agarre."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Piezas grandes y un motivo conocido."),
                    note("Duración", "Se guarda en caja y se saca de nuevo; no se consume."),
                    note("Uso", "Mesa o suelo, con un adulto al principio."),
                    note("Resistencia", "Madera; aguanta el juego intenso si se recogen las piezas.")
            ),
            entry(
                    "puzle-melissa-mascotas",
                    "Melissa & Doug puzle de mascotas",
                    "Tablero de madera que sujeta las piezas",
                    "Puzle de madera de mascotas con ocho piezas gruesas. El tablero evita perder piezas sueltas: a los 3 años eso alarga el uso real del objeto; las piezas se sostienen de pie para jugar después.",
                    List.of(
                            "Ocho piezas gruesas de madera fáciles de agarrar.",
                            "Mascotas reconocibles; las piezas se sostienen de pie.",
                            "Imágenes a color bajo cada pieza."
                    ),
                    List.of(
                            "Ocho piezas pueden quedarse cortas si ya monta 15 o 16.",
                            "Es un tablero de siluetas, no un puzle de escena de muchas piezas."
                    ),
                    "Desde 2 años",
                    note("Encaje a los 3 años", "Piezas grandes y un motivo de mascotas conocido."),
                    note("Duración", "El tablero sujeta las piezas; se guarda entero."),
                    note("Uso", "Encaje guiado por silueta, no un puzle suelto de muchas piezas."),
                    note("Resistencia", "Madera; las piezas gruesas aguantan el juego intenso si se recogen.")
            ),
            entry(
                    "puzle-educa-selva",
                    "Educa My First animales de la selva",
                    "Cuatro escenas para rotar sin mezclar 50 piezas",
                    "Cuatro puzles progresivos de 5 a 8 piezas para empezar a encajar. A los 3 años permite acertar pronto y cambiar de escena: el mismo estuche dura meses si guardas cada puzle aparte.",
                    List.of(
                            "Cuatro puzles de 5 a 8 piezas.",
                            "Progresión de dificultad dentro de la misma caja.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "8 piezas máximas: si ya monta 15 o 16, pasa a Educa Disney o a otro formato.",
                            "Cartón: confirma que es grueso en la ficha vigente."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Empieza por 5 piezas y sube cuando termine sin ayuda."),
                    note("Duración", "Cuatro escenas para rotar; no se agota en una tarde."),
                    note("Uso", "Progresivo y acotado, no un único puzle largo."),
                    note("Resistencia", "Cartón grueso; guarda cada puzle aparte para no perder piezas.")
            ),
            entry(
                    "puzle-educa-disney-madera",
                    "Educa Disney Animals, 2 puzles de madera",
                    "Dos escenas de madera a partir de 3 años",
                    "Dos puzles de 16 piezas de madera, recomendados a partir de 3 años. Sirven cuando 8 piezas ya se resuelven en un minuto: el mismo par de cajas se repite durante varias semanas.",
                    List.of(
                            "Dos puzles de 16 piezas de madera.",
                            "Edad declarada a partir de 3 años.",
                            "Motivos de animales Disney."
                    ),
                    List.of(
                            "16 piezas piden más paciencia que un puzle de silueta con tablero.",
                            "El motivo de personaje puede caducar; la madera no."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Indicada desde 3 años; empieza junto y retira ayuda al final."),
                    note("Duración", "Dos escenas para alternar; no es un solo montaje."),
                    note("Uso", "16 piezas por escena, el recuento más alto de esta lista."),
                    note("Resistencia", "Madera; confirma si hay marco en tu edición.")
            ),
            entry(
                    "simbolico-theo-klein-miele",
                    "Theo Klein Cocina Miele 7199",
                    "Cocina de madera para años de juego simbólico",
                    "Cocina de madera con placa, horno, fregadero y accesorios, de 3 a 8 años. A los 3 años el gesto es abrir y «cocinar»; el mismo mueble aguanta varios cursos si hay sitio en casa.",
                    List.of(
                            "Placa, horno, fregadero y accesorios.",
                            "Madera según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Ocupa sitio en casa; mide antes de comprar.",
                            "Los accesorios sueltos se pierden si no hay un cajón."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; el niño imita gestos cortos."),
                    note("Duración", "El mismo escenario se repite durante años, no una tarde."),
                    note("Uso", "Juego simbólico con un adulto cerca al principio."),
                    note("Resistencia", "Madera; revisa estabilidad contra la pared si la ficha lo indica.")
            )
    );

    private static final List<EditorialEntry> ARTS_NATURAL_3 = List.of(
            entry(
                    "arte-ses-eco-mega-7",
                    "SES Creative Eco mega set de modelado",
                    "Modelar con plastilina ecológica y rodillo de madera",
                    "Set de siete colores de plastilina ecológica con rodillo de madera y cortadores de plástico reciclado, a partir de 2 años. A los 3 años el gesto es aplastar, enrollar y cortar formas con un adulto al lado.",
                    List.of(
                            "Siete colores en envases reutilizables según la ficha.",
                            "Rodillo de madera y cortadores de plástico reciclado.",
                            "Línea Eco con materiales declarados por el fabricante."
                    ),
                    List.of(
                            "La plastilina se gasta; no es un objeto permanente.",
                            "Los cortadores son piezas pequeñas: supervisión en cada sesión."
                    ),
                    "2-12 años",
                    note("Encaje a los 3 años", "Edad mínima 2 años; sesiones cortas con un adulto."),
                    note("Materiales", "Plastilina ecológica, madera y plástico reciclado en cortadores."),
                    note("Uso", "Modelar en mesa, no juego de exterior."),
                    note("Cuidado", "Superficie fácil de limpiar; cerrar los botes al terminar.")
            ),
            entry(
                    "arte-jovi-pintura-dedos-6",
                    "Jovi pintura de dedos lavable, 6 colores",
                    "Pintar con los dedos e ingredientes naturales",
                    "Seis botes de pintura de dedos lavable a base de ingredientes naturales, sin gluten, a partir de 2 años. A los 3 años el gesto es untar con el dedo, no dosificar témpera con pincel fino.",
                    List.of(
                            "Pintura de dedos 100 % lavable según la ficha.",
                            "Seis colores mezclables.",
                            "Sin gluten y libre de los principales alérgenos declarados."
                    ),
                    List.of(
                            "Mancha mesa y ropa si no hay babero o mantel.",
                            "No es plastilina: se usa en papel o cartón, no para modelar."
                    ),
                    "2-10 años",
                    note("Encaje a los 3 años", "Edad mínima 2 años; un adulto dosifica la cantidad."),
                    note("Materiales", "Base de ingredientes naturales, según la ficha."),
                    note("Uso", "Papel o cartón con los dedos; no paredes."),
                    note("Cuidado", "Se lava de piel y, según la ficha, de la mayoría de tejidos.")
            ),
            entry(
                    "arte-crayola-effects",
                    "Crayola témperas de efectos especiales",
                    "Añadir brillo cuando ya pinta sin miedo",
                    "Diez colores lavables con acabados neón, brillo y metal, a partir de 3 años. Encaja si ya aguanta una sesión de pintar y quiere un resultado distinto, no como primer bote.",
                    List.of(
                            "Diez colores lavables.",
                            "Efectos neón, brillo y metal según la ficha.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "Más botes que los 6 de témpera básica: hay que recoger.",
                            "El efecto visual no enseña a pintar; pide el mismo acompañamiento."
                    ),
                    "3-10 años",
                    note("Encaje a los 3 años", "Usable a los 3 si ya ha pintado con témpera simple."),
                    note("Materiales", "Témpera lavable con acabados especiales."),
                    note("Uso", "Papel; el brillo no sustituye la supervisión."),
                    note("Cuidado", "Lavable; cierra los botes al terminar.")
            ),
            entry(
                    "arte-jovi-plastilina-vegetal-12",
                    "Jovi plastilina vegetal, 12 pastillas",
                    "Modelar con base vegetal y muchos colores",
                    "Doce pastillas de plastilina de base vegetal, no tóxica y sin gluten, a partir de 3 años. Ocho colores básicos y cuatro fluorescentes para mezclar sin abrir botes de pintura.",
                    List.of(
                            "Doce pastillas de 50 g en colores básicos y fluorescentes.",
                            "Base vegetal, sin gluten según la ficha.",
                            "No se seca: se puede guardar y volver a usar."
                    ),
                    List.of(
                            "No incluye rodillo ni cortadores; el gesto es manual.",
                            "Las pastillas pequeñas piden recoger al terminar."
                    ),
                    "3-10 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; sesiones cortas con un adulto."),
                    note("Materiales", "Plastilina vegetal, no tóxica."),
                    note("Uso", "Modelar en mesa; no comer ni mezclar con comida."),
                    note("Cuidado", "Guardar las pastillas en su estuche; limpiar la mesa al terminar.")
            ),
            entry(
                    "arte-crayola-paw-patrol",
                    "Crayola maletín Patrulla Canina",
                    "Colorear con un maletín que se guarda",
                    "Maletín con material para colorear y hojas temáticas, de 3 a 10 años. A los 3 años el valor es poder recoger ceras y hojas en una caja, no el personaje.",
                    List.of(
                            "Maletín para guardar y transportar.",
                            "Hojas y material para colorear.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "El motivo de personaje puede cansarse; las ceras siguen sirviendo.",
                            "No es pintura ni plastilina: es colorear."
                    ),
                    "3-10 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; un adulto recorta o sujeta la hoja si hace falta."),
                    note("Materiales", "Ceras y papel según la ficha del maletín."),
                    note("Uso", "Mesa; se guarda en el maletín."),
                    note("Cuidado", "Revisa que no haya piezas pequeñas sueltas de adorno.")
            )
    );

    private static final List<EditorialEntry> MONTESSORI_WOOD_3 = List.of(
            entry(
                    "juego-montessori-formas",
                    "Melissa & Doug cubo de formas",
                    "Encajar formas y colores en madera",
                    "Cubo de madera con 12 piezas grandes para encajar por la forma, de 2 a 4 años. A los 3 años la actividad es evidente: coger, nombrar y meter. Es un objeto de madera que se guarda y se vuelve a sacar.",
                    List.of(
                            "Doce piezas grandes de madera.",
                            "Clasificación de formas y colores en un solo objeto.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "A los 4 años el cubo puede quedarse corto si ya clasifica sin esfuerzo.",
                            "No incluye un segundo nivel de dificultad en la misma caja."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Edad declarada hasta 4 años; las piezas grandes evitan frustración inicial."),
                    note("Madera", "Cubo y piezas de madera."),
                    note("Uso", "Encajar y nombrar en sesiones cortas."),
                    note("Duración", "Se guarda entero; no es de un solo uso.")
            ),
            entry(
                    "montessori-goula-baby-shapes",
                    "Goula Baby Shapes",
                    "Madera con láminas que se pueden subir",
                    "Láminas y piezas de madera para encajar formas y colores con dificultad progresiva, de 2 a 5 años. La madera se queda; cambian las láminas.",
                    List.of(
                            "Piezas de madera y varias láminas.",
                            "Dificultad progresiva sin cambiar de juguete.",
                            "Rango declarado de 2 a 5 años."
                    ),
                    List.of(
                            "Hay que guardar las láminas que no se usan.",
                            "Un adulto propone el siguiente nivel."
                    ),
                    "2-5 años",
                    note("Encaje a los 3 años", "Cabe en 3 años y admite crecer hasta 5."),
                    note("Madera", "Piezas y láminas de madera."),
                    note("Uso", "Encaje con niveles, no un cubo único."),
                    note("Duración", "Las láminas alargan el uso más de una temporada.")
            ),
            entry(
                    "simbolico-sundaymot-33",
                    "Sundaymot maletín médico de madera",
                    "Imitar la consulta con piezas de madera",
                    "Maletín de médico y dentista con bata, estetoscopio y accesorios de madera, de 3 a 8 años. A los 3 años el gesto es abrir, «auscultar» y recoger; saca pocos accesorios en cada sesión.",
                    List.of(
                            "Maletín con bata y accesorios de consulta.",
                            "33 piezas de madera según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Tantos accesorios abruman si se vuelcan de golpe.",
                            "No sustituye un cubo de encaje ni las láminas de Goula."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; limita los accesorios en cada sesión."),
                    note("Madera", "Maletín y accesorios de madera según la ficha del lote vigente."),
                    note("Uso", "Juego simbólico; recuento al guardar."),
                    note("Duración", "Se guarda en el maletín; no es de un solo uso.")
            ),
            entry(
                    "puzle-melissa-granja-peg",
                    "Melissa & Doug puzle de granja con agarres",
                    "Ocho piezas de madera con pomos",
                    "Tablero de madera con ocho piezas con pomos para encajar animales de granja, de 2 a 4 años. Menos piezas que el cubo de 12: se termina en una sesión corta y el tablero sujeta las piezas.",
                    List.of(
                            "Ocho piezas con pomos de madera.",
                            "Escena de granja reconocible.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "Ocho piezas pueden agotarse rápido si ya encaja con soltura.",
                            "No es un puzle de escena suelta: es encaje de siluetas con pomos."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Pocas piezas gruesas, fáciles de completar."),
                    note("Madera", "Tablero y piezas de madera."),
                    note("Uso", "Encajar animales en una sesión corta."),
                    note("Duración", "Se guarda entero; el tablero evita perder piezas.")
            ),
            entry(
                    "lectura-three-pigs",
                    "Goula Los 3 Cerditos",
                    "Llegar juntos a casa con piezas de madera",
                    "Juego de mesa de Goula basado en el cuento: turnos, vocabulario y una modalidad cooperativa para llegar juntos a casa, de 3 a 7 años. A los 3 años pide nombrar personajes y mover piezas; las partidas son cortas.",
                    List.of(
                            "Relato conocido con turnos y vocabulario.",
                            "Edad declarada de 3 a 7 años.",
                            "Modalidad cooperativa para llegar juntos a casa."
                    ),
                    List.of(
                            "El dado introduce azar; un adulto explica el objetivo la primera vez.",
                            "Piezas pequeñas: supervisión y recogida al terminar."
                    ),
                    "3-7 años",
                    note("Encaje a los 3 años", "Reglas cortas y edad declarada desde 3 años."),
                    note("Madera", "Piezas de madera según la ficha."),
                    note("Uso", "Turnos y cooperación, no ensartar cordones."),
                    note("Duración", "Partidas cortas; se guarda en la caja.")
            )
    );

    private static final List<EditorialEntry> SYMBOLIC_3 = List.of(
            entry(
                    "simbolico-theo-klein-miele",
                    "Theo Klein Cocina Miele 7199",
                    "Imitar la cocina con placa y fregadero",
                    "Cocina de madera con placa, horno, fregadero y accesorios, de 3 a 8 años. A los 3 años el juego es abrir, cerrar y «cocinar» con un adulto cerca; no un electrodoméstico real.",
                    List.of(
                            "Placa, horno, fregadero y accesorios.",
                            "Madera según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Ocupa sitio en casa; mide antes de comprar.",
                            "Los accesorios sueltos se pierden si no hay un cajón."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; el niño imita gestos cortos."),
                    note("Escenario", "Cocina completa, no un maletín."),
                    note("Espacio", "Revisa hueco contra la pared y estabilidad."),
                    note("Seguridad", "No es una cocina de verdad; lejos del fuego real.")
            ),
            entry(
                    "simbolico-kidkraft-vintage",
                    "KidKraft cocina vintage blanca",
                    "Nevera, horno y teléfono de juguete",
                    "Cocina de madera con nevera, horno, microondas y teléfono, de 3 a 8 años. Más zonas que una cocina compacta: a los 3 años conviene no llenarla de accesorios el primer día.",
                    List.of(
                            "Nevera, horno, microondas y teléfono de juguete.",
                            "Madera según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Más volumen que la cocina compacta de Small Foot.",
                            "El teléfono es imitación, no un juguete de reglas."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; abre y cierra con supervisión."),
                    note("Escenario", "Varias zonas de cocina para inventar historias."),
                    note("Espacio", "Mide el hueco; no es un set de viaje."),
                    note("Seguridad", "Estabilidad contra la pared si la ficha lo indica.")
            ),
            entry(
                    "simbolico-small-foot-compacta",
                    "Small Foot cocina compacta",
                    "Cocinita de madera que se monta y se guarda",
                    "Cocina compacta de madera FSC con horno, olla, sartén y utensilios, a partir de 3 años. Se monta y desmonta sin herramientas: si no hay pared libre, cabe en una estantería o se recoge.",
                    List.of(
                            "Madera FSC 100 % según la ficha.",
                            "Montaje y desmontaje sin herramientas.",
                            "Formato compacto para poco espacio."
                    ),
                    List.of(
                            "No sustituye una cocina de pie con fregadero grande.",
                            "Los utensilios son pequeños: no los dejes con menores de 3 años."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; se usa en mesa o suelo."),
                    note("Escenario", "Imitación de cocina sin ocupar una pared."),
                    note("Espacio", "Se guarda; no pide un rincón fijo."),
                    note("Seguridad", "Utensilios sueltos; supervisión.")
            ),
            entry(
                    "simbolico-janod-macaron",
                    "Janod cocina Macaron",
                    "Cocina de madera compacta con cinco accesorios",
                    "Cocina de madera con horno, fregadero de acero, placas con sonido y cinco accesorios, de 3 a 8 años. Mide 53 × 30 × 78 cm: más compacta que las cocinas de pared de esta lista; a los 3 años saca pocos utensilios de cada vez.",
                    List.of(
                            "Horno, fregadero de acero y placas con sonido.",
                            "Cinco accesorios según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Sigue pidiendo un hueco en el suelo; no es un set de viaje.",
                            "Cinco accesorios no llenan una cocina grande."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; el niño imita gestos cortos."),
                    note("Escenario", "Cocina compacta de pie, no un maletín de médico."),
                    note("Espacio", "Más pequeña que Theo Klein y KidKraft."),
                    note("Seguridad", "Piezas sueltas; recuento al guardar.")
            ),
            entry(
                    "simbolico-janod-veterinario",
                    "Janod maletín de veterinario",
                    "Imitar la consulta, no la cocina",
                    "Maletín de tela con dieciséis accesorios de madera FSC para auscultar, vendar y cuidar peluches, de 3 a 8 años. El juego simbólico aquí es cuidar, no cocinar: otro rol, el mismo gesto de imitar la vida diaria.",
                    List.of(
                            "Dieciséis accesorios según la ficha, con estetoscopio y termómetro.",
                            "Madera FSC, tela y cartón.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Piezas pequeñas de accesorios: supervisión.",
                            "No es un juguete de cocina."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; un adulto nombra los objetos."),
                    note("Escenario", "Consulta veterinaria, no cocina."),
                    note("Espacio", "Se guarda en el maletín."),
                    note("Seguridad", "No sustituye material real ni se lleva a la boca.")
            )
    );

    private static final List<EditorialEntry> SENSORY_3 = List.of(
            entry(
                    "sensorial-emotion-bottles",
                    "Learning Resources botellas sensoriales de emociones",
                    "Mirar e inclinar sin abrir",
                    "Cuatro botellas selladas con movimientos y expresiones, de 3 a 7 años. A los 3 años el gesto es observar e inclinar; no se abren ni se prueban.",
                    List.of(
                            "Cuatro botellas selladas.",
                            "Movimiento visual distinto en cada una.",
                            "Edad declarada de 3 a 7 años."
                    ),
                    List.of(
                            "Si el sello falla, se retiran: no se improvisan con líquidos de casa.",
                            "No enseñan emociones solas; un adulto nombra lo que se ve."
                    ),
                    "3-7 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; se usan enteras, sin abrir."),
                    note("Acción", "Observar e inclinar."),
                    note("Material", "Selladas; revisa que no goteen."),
                    note("Supervisión", "Retira cualquier botella dañada.")
            ),
            entry(
                    "sensorial-playfoam",
                    "Learning Resources Playfoam, 6 bloques",
                    "Apretar y modelar sin que se seque",
                    "Espuma moldeable que no se seca ni se pega, de 3 a 8 años. Textura para manos: no es plastilina de secar ni pintura.",
                    List.of(
                            "Seis bloques de espuma moldeable.",
                            "No se seca ni se pega según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Se pierde en la alfombra si no hay bandeja.",
                            "No sustituye un kit de pintar."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; bandeja o mantel."),
                    note("Acción", "Apretar, estirar y volver a juntar."),
                    note("Material", "Espuma que no se seca; no se come."),
                    note("Supervisión", "Fuera del alcance de menores que se lleven cosas a la boca.")
            ),
            entry(
                    "sensorial-fidget-tubes",
                    "Learning Resources Sensory Trio Fidget Tubes",
                    "Arena, brillo y cuentas que no se derraman",
                    "Tres tubos sellados con arena, brillo y cuentas, de 3 a 8 años. Inclinar y mirar: el contenido no se saca.",
                    List.of(
                            "Tres tubos sellados.",
                            "Arena, brillo y cuentas según la ficha.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Si se raja un tubo, se tira; no se rellena en casa.",
                            "No es un juguete de transferir con palas."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; tubos enteros."),
                    note("Acción", "Inclinar y seguir el movimiento."),
                    note("Material", "Sellados; revisa grietas."),
                    note("Supervisión", "Cuentas dentro: no se abren.")
            ),
            entry(
                    "sensorial-scoops",
                    "Learning Resources palas sensoriales Helping Hands",
                    "Verter y tamizar con manos pequeñas",
                    "Cuatro palas para verter, tamizar y transferir, de 3 a 8 años. El juego es mover arroz, agua o arena de mesa, no mirar un tubo cerrado.",
                    List.of(
                            "Cuatro palas de distinto tipo.",
                            "Pensadas para manos pequeñas.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Hace falta un material que verter y una bandeja.",
                            "El arroz no se deja con menores que lo puedan tragar a puñados."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; un adulto prepara la bandeja."),
                    note("Acción", "Verter, tamizar y transferir."),
                    note("Material", "Palas; el contenido lo pones tú y se supervisa."),
                    note("Supervisión", "Nada de agua profunda ni granos con bebés cerca.")
            ),
            entry(
                    "sensorial-pinzas-jumbo",
                    "Learning Resources pinzas jumbo",
                    "Recoger y soltar para el agarre",
                    "Pinzas grandes para recoger, transferir y fortalecer el agarre, de 3 a 8 años. Un gesto claro: abrir, coger, soltar. Sirve en bandeja, no como juguete de sala.",
                    List.of(
                            "Tamaño jumbo para manos de 3 años.",
                            "Recoger y transferir.",
                            "Edad declarada de 3 a 8 años."
                    ),
                    List.of(
                            "Sin objetos que recoger no hay juego.",
                            "No son tijeras ni cubiertos de comida."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; pompones o cubos grandes, no cuentas sueltas."),
                    note("Acción", "Pinzar y soltar."),
                    note("Material", "Pinzas jumbo; elige objetos que no se traguen."),
                    note("Supervisión", "Fuera de la boca; recuento al guardar.")
            )
    );

    private static final List<EditorialEntry> PIKLER_3 = List.of(
            entry(
                    "trepar-mamoi-triangulo-blanco",
                    "MAMOI triángulo de escalada con tobogán",
                    "Trepar en madera con rampa reversible",
                    "Triángulo de madera con rampa suave por un lado y barras por el otro. Altura regulable de 20 a 57 cm y carga de 50 kg. A los 3 años el gesto es subir, bajar o deslizarse en interior, con un adulto al lado.",
                    List.of(
                            "Rampa reversible: tobogán o escalada.",
                            "Altura regulable de 20 a 57 cm.",
                            "Marcado EN y CE según la ficha; carga 50 kg."
                    ),
                    List.of(
                            "Pide un hueco en el suelo; no es un juguete de viaje.",
                            "Uso en interior seco; no sustituye casco ni patio."
                    ),
                    "1-6 años",
                    note("Encaje a los 3 años", "Altura regulable; el niño sube y baja con supervisión."),
                    note("Gesto", "Trepar o deslizarse, no empujar ruedas."),
                    note("Espacio", "Interior; mide el hueco antes."),
                    note("Seguridad", "Suelo nivelado, adulto presente, sin saltar desde lo alto.")
            ),
            entry(
                    "trepar-mamoi-triangulo-natural",
                    "MAMOI triángulo de escalada natural",
                    "Madera cruda a partir de 36 meses",
                    "Triángulo de pino con tobogán de dos caras y carga de 60 kg. El fabricante lo indica a partir de 36 meses: encaja cuando el niño ya trepa con control, no como primer escalón de bebé.",
                    List.of(
                            "Edad declarada a partir de 36 meses.",
                            "Carga de 60 kg según la ficha.",
                            "Acabado de madera natural sin pinturas extra."
                    ),
                    List.of(
                            "Más volumen que el modelo compacto de 50 kg.",
                            "Montaje por el adulto; no se usa a medias."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Edad mínima 36 meses; no adelantar si aún gatea inestable."),
                    note("Gesto", "Trepar en un marco mayor."),
                    note("Espacio", "185 cm de largo según ficha; mide el rincón."),
                    note("Seguridad", "Tornillos bien apretados; supervisión constante.")
            ),
            entry(
                    "trepar-little-tikes-gimnasio",
                    "Little Tikes gimnasio de actividades Junior",
                    "Trepar, gatear y deslizarse en plástico",
                    "Estructura de plástico para interior o jardín, de 18 meses a 5 años. Combina rampa, tobogán y huecos para esconderse. No es un triángulo Pikler de madera: el gesto es el mismo tipo de movimiento grueso.",
                    List.of(
                            "Trepar, gatear y deslizarse en un solo volumen.",
                            "Uso interior o exterior según la ficha.",
                            "Edad declarada de 18 meses a 5 años."
                    ),
                    List.of(
                            "Ocupa 130 × 76 cm; no cabe en un pasillo.",
                            "Carga 23 kg: comprueba el peso real."
                    ),
                    "1-5 años",
                    note("Encaje a los 3 años", "Rango 18 meses-5 años; ya puede trepar y deslizarse."),
                    note("Gesto", "Circuito corto: subir, esconderse, bajar."),
                    note("Espacio", "Ancho 130 cm; interior en invierno, jardín si hay césped."),
                    note("Seguridad", "Base estable; un niño a la vez en el tobogán.")
            ),
            entry(
                    "trepar-little-tikes-tobogan",
                    "Little Tikes tobogán azul y verde",
                    "Solo deslizarse, sin marco de escalada",
                    "Tobogán de 110 cm de recorrido, 5 kg, de 18 meses a 5 años. Escalones y pasamanos. Si no hay sitio para un triángulo, cubre el gesto de subir y bajar sin ruedas.",
                    List.of(
                            "Recorrido de 110 cm y poco peso declarado.",
                            "Escalones y pasamanos.",
                            "Interior o exterior, 18 meses a 5 años."
                    ),
                    List.of(
                            "No enseña a trepar un marco de barras.",
                            "Carga 27,2 kg; pendiente suave, no un tobogán de parque."
                    ),
                    "1-5 años",
                    note("Encaje a los 3 años", "Altura baja; el niño sube los escalones con apoyo si hace falta."),
                    note("Gesto", "Deslizarse, no empujar un correpasillos."),
                    note("Espacio", "Fondo 122 cm; se desmonta para guardar."),
                    note("Seguridad", "Base ancha; no lo coloques junto a un desnivel.")
            ),
            entry(
                    "trepar-costway-7en1",
                    "COSTWAY set de escalada 7 en 1",
                    "Triángulo, arco y rampa en un set",
                    "Set de haya con triángulo, arco (también balancín al girarlo) y rampa de dos caras, a partir de 1 año. A los 3 años permite cambiar el circuito sin comprar tres juguetes distintos.",
                    List.of(
                            "Tres piezas combinables: triángulo, arco y rampa.",
                            "Arco reversible como balancín.",
                            "Madera de haya y bordes pulidos según la ficha."
                    ),
                    List.of(
                            "Tres piezas piden más suelo que un solo triángulo.",
                            "El balancín no se usa cerca de bordes ni de otros niños."
                    ),
                    "1-6 años",
                    note("Encaje a los 3 años", "A partir de 1 año; a los 3 se combinan las tres piezas."),
                    note("Gesto", "Trepar, deslizarse o balancearse, no ruedas."),
                    note("Espacio", "75 × 55 × 64 cm el triángulo; el set entero pide más."),
                    note("Seguridad", "Una pieza a la vez si el espacio es justo; adulto presente.")
            )
    );

    private static final List<EditorialEntry> RIDE_ON_3 = List.of(
            entry(
                    "corre-injusa-africa-twin",
                    "INJUSA moto Honda África Twin",
                    "Empujar sentado a los 3 y 4 años",
                    "Moto correpasillos de ruedas anchas con asa de transporte, indicada para 2 a 4 años. El gesto es sentarse y empujar con los pies: no es bici sin pedales ni patinete de pie.",
                    List.of(
                            "Rango declarado que incluye 3 y 4 años.",
                            "Ruedas anchas y asa de transporte.",
                            "Fabricada en España según la ficha."
                    ),
                    List.of(
                            "No enseña el equilibrio de dos ruedas.",
                            "Pregunta si ya tiene moto o coche de empuje."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Pensada para 2 a 4 años, no solo para bebés."),
                    note("Gesto", "Sentado, pies al suelo, sin pedales."),
                    note("Espacio", "Interior liso o patio sin tráfico."),
                    note("Seguridad", "Supervisión; no es un vehículo eléctrico.")
            ),
            entry(
                    "corre-injusa-neox-kawasaki",
                    "INJUSA moto Neox Kawasaki",
                    "Moto baja de 18 meses a 3 años",
                    "Correpasillos de 18 meses a 3 años, 30 kg, asiento a 37 cm y manillar a 49 cm. Sirve si el niño aún cabe cómodo a los 3; si ya va justo de rodillas, mira la África Twin.",
                    List.of(
                            "Asiento a 37 cm y ruedas anchas.",
                            "Carga 30 kg y asa de transporte.",
                            "Edad declarada 18 meses-3 años."
                    ),
                    List.of(
                            "El tope son 3 años: puede quedarse pequeño a mitad de curso.",
                            "Decoración de marca; el gesto es el mismo que otras Neox."
                    ),
                    "1-3 años",
                    note("Encaje a los 3 años", "Tope 3 años y 30 kg; mide rodillas y asiento."),
                    note("Gesto", "Empuje sentado, ruedas anchas."),
                    note("Espacio", "69 × 27,5 × 49 cm según ficha."),
                    note("Seguridad", "No para menores de 18 meses; adulto cerca.")
            ),
            entry(
                    "corre-feber-motofeber-casual",
                    "FEBER Motofeber Casual",
                    "Moto de empuje hasta los 3 años",
                    "Moto correpasillos de 18 meses a 3 años. Formato relajado para interior o patio: pies al suelo, sin motor ni pedales. Si buscas llegar a los 4 años, no es esta ficha.",
                    List.of(
                            "Edad declarada de 18 meses a 3 años.",
                            "Diseño estable para empujar con los pies.",
                            "Uso interior o exterior según la ficha."
                    ),
                    List.of(
                            "No cubre los 4 años.",
                            "No es un correpasillos con forma de coche."
                    ),
                    "1-3 años",
                    note("Encaje a los 3 años", "Tope 3 años; comprueba que las piernas empujen sin encogerse."),
                    note("Gesto", "Moto sentada, no patinete."),
                    note("Espacio", "Pasillo o terraza lisa."),
                    note("Seguridad", "Sin tráfico; no bajar bordillos.")
            ),
            entry(
                    "corre-molto-cross-race",
                    "MOLTO Cross Race Silver",
                    "Moto para superficies variadas",
                    "Moto correpasillos a partir de 18 meses, pensada para distintos suelos. A los 3 años cubre el empuje sentado si el asiento aún llega; no sustituye una bici sin pedales.",
                    List.of(
                            "A partir de 18 meses según la ficha.",
                            "Uso en varios terrenos.",
                            "Empuje con los pies, sin pedales."
                    ),
                    List.of(
                            "Confirma altura de asiento en tu lote: MOLTO no detalla cm en todas las fichas.",
                            "No es un todoterreno eléctrico."
                    ),
                    "1-4 años",
                    note("Encaje a los 3 años", "Desde 18 meses; a los 3 sigue si las piernas empujan sueltas."),
                    note("Gesto", "Moto de empuje en suelo irregular suave."),
                    note("Espacio", "Patio o interior; evita grava profunda."),
                    note("Seguridad", "Calzado cerrado; adulto a la vista.")
            ),
            entry(
                    "corre-smoby-coche",
                    "Smoby Little Smoby correpasillos",
                    "Coche con antivuelco, no moto",
                    "Coche correpasillos con dispositivos antivuelco y hueco bajo el asiento, a partir de 10 meses, fabricado en Francia. A los 3 años sirve si aún cabe: el gesto es empujar un coche, distinto de la moto y de la bici sin pedales.",
                    List.of(
                            "Antivuelco delantero y trasero.",
                            "Hueco bajo el asiento.",
                            "54 × 27 × 40 cm según la ficha."
                    ),
                    List.of(
                            "Formato compacto: a los 3 años puede quedar pequeño de asiento.",
                            "No es un Cozy Coupe con puerta."
                    ),
                    "1-3 años",
                    note("Encaje a los 3 años", "Desde 10 meses; a los 3 solo si las rodillas no van encogidas."),
                    note("Gesto", "Coche sentado con volante, no moto."),
                    note("Espacio", "Interior; ruedas para suelo liso."),
                    note("Seguridad", "Antivuelco no sustituye supervisión.")
            )
    );

    private static final List<EditorialEntry> CUTLERY_3 = List.of(
            entry(
                    "cubiertos-twistshake-acero",
                    "Twistshake cubiertos de aprendizaje de acero",
                    "Tres piezas cortas para la mesa diaria",
                    "Tenedor, cuchillo y cuchara de acero inoxidable, sin BPA, a partir de 12 meses. A los 3 años cubre el gesto de pinchar y recoger sin pedir cubiertos de adulto. El mango corto cabe en una mano pequeña.",
                    List.of(
                            "Set de tres: tenedor, cuchillo y cuchara.",
                            "Acero y mango de PP y TPE, sin BPA.",
                            "Edad declarada a partir de 12 meses."
                    ),
                    List.of(
                            "No incluye plato ni vaso: es cubierto, no vajilla.",
                            "El cuchillo corta blando; no sustituye un cuchillo de mesa de adulto."
                    ),
                    "1-6 años",
                    note("Encaje a los 3 años", "Desde 12 meses; a los 3 el mango corto sigue siendo el punto."),
                    note("Piezas", "Tres cubiertos; sin plato."),
                    note("Cuidado", "Lavavajillas según ficha; confirma cubeta."),
                    note("Seguridad", "Cuchillo de aprendizaje; supervisión en la mesa.")
            ),
            entry(
                    "cubiertos-mam-aprendizaje",
                    "MAM cubiertos de aprendizaje",
                    "Asas antideslizantes para zurdos y diestros",
                    "Tenedor, cuchillo y cuchara con asas curvadas y antideslizantes, a partir de 6 meses. A los 3 años sirve si aún se le escapa el cubierto de acero liso: el agarre es el criterio, no el motivo.",
                    List.of(
                            "Asas antideslizantes para ambas manos.",
                            "Tres piezas; tenedor y cuchillo de filo seguro según la ficha.",
                            "Libre de BPA."
                    ),
                    List.of(
                            "Pensado también para bebés: a los 3 puede verse pequeño si ya maneja acero de mesa.",
                            "No es un set de cuatro con cucharilla de postre."
                    ),
                    "1-5 años",
                    note("Encaje a los 3 años", "Desde 6 meses; a los 3 si el mango de adulto aún se le va."),
                    note("Piezas", "Tenedor, cuchillo y cuchara."),
                    note("Cuidado", "Revisa lavavajillas en la ficha vigente."),
                    note("Seguridad", "Filo de aprendizaje; no dejar solo con comida dura.")
            ),
            entry(
                    "cubiertos-wmf-animales",
                    "WMF Animales cubertería 4 piezas",
                    "Acero de mesa con cucharilla extra",
                    "Tenedor, cuchillo, cuchara y cuchara pequeña de Cromargan, aptos para lavavajillas. A los 3 años es el paso hacia cubiertos que se parecen a los de la mesa familiar, con tamaño de niño.",
                    List.of(
                            "Cuatro piezas, incluida cucharilla.",
                            "Acero Cromargan y lavavajillas según ficha.",
                            "Marca de cubertería de mesa, no de juguete."
                    ),
                    List.of(
                            "El mango de acero puede resbalar más que uno de goma.",
                            "Motivo de animales: pregunta si ya hay otro set WMF."
                    ),
                    "3-10 años",
                    note("Encaje a los 3 años", "Tamaño infantil; el acero pide más control que el mango de goma."),
                    note("Piezas", "Cuatro, con cucharilla."),
                    note("Cuidado", "Lavavajillas declarado."),
                    note("Seguridad", "Cuchillo de mesa infantil; adulto en la comida.")
            ),
            entry(
                    "cubiertos-exzact-safari",
                    "EXZACT cubertería infantil Safari",
                    "Dos de cada pieza a partir de 24 meses",
                    "Seis piezas de acero: dos tenedores, dos cuchillos de seguridad y dos cucharas, a partir de 24 meses. Útil si hay recambio en el cajón o si come fuera y en casa con el mismo formato.",
                    List.of(
                            "Seis piezas: recambio sin comprar otro set.",
                            "Cuchillos dentados de seguridad según la ficha.",
                            "Edad declarada a partir de 24 meses."
                    ),
                    List.of(
                            "Motivo safari: el diseño se cansa antes que el acero.",
                            "No dejes el acero en remojo: la ficha lo desaconseja."
                    ),
                    "2-8 años",
                    note("Encaje a los 3 años", "Desde 24 meses; a los 3 el recambio cubre visitas o guardería."),
                    note("Piezas", "Dos tenedores, dos cuchillos, dos cucharas."),
                    note("Cuidado", "Lavavajillas; secar, no remojar."),
                    note("Seguridad", "Cuchillo de seguridad; no es un cuchillo de cocina.")
            ),
            entry(
                    "cubiertos-lehoo-vehiculos",
                    "Lehoo Castle cubiertos vehículos",
                    "Seis piezas con mango corto a partir de 3 años",
                    "Dos tenedores, dos cuchillos y dos cucharas de acero 304 con mango de vehículos de construcción. La ficha lo indica a partir de 3 años: encaja cuando ya pincha y recoge, no como primer cubierto de bebé.",
                    List.of(
                            "Edad declarada a partir de 3 años.",
                            "Seis piezas y mango corto.",
                            "Acero inoxidable 304 según la ficha."
                    ),
                    List.of(
                            "El dibujo del mango puede desgastarse en lavavajillas; la ficha recomienda mano.",
                            "No es un set de aprendizaje de 12 meses."
                    ),
                    "3-8 años",
                    note("Encaje a los 3 años", "Mínimo 3 años; no adelantar si aún usa solo cuchara de plástico."),
                    note("Piezas", "Seis, dos de cada."),
                    note("Cuidado", "Lavado a mano si quieres conservar el dibujo."),
                    note("Seguridad", "Mango corto; supervisión con el cuchillo.")
            )
    );

    private static final List<EditorialEntry> DRESSING_3 = List.of(
            entry(
                    "vestir-melissa-habilidades",
                    "Melissa & Doug tablero de habilidades básicas",
                    "Botones, cremallera, hebilla y cordones en un oso",
                    "Oso de madera con seis prendas que se quitan y se abrochan: botones, cremallera, hebilla y cordones. A los 3 años el gesto está aislado en la mesa, no en el abrigo puesto. Edad declarada a partir de 3 años.",
                    List.of(
                            "Seis cierres de vestir en un solo tablero.",
                            "Piezas que se separan para practicar una a una.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "No es la chaqueta real: después hay que pasar el gesto a la prenda.",
                            "39 × 29 cm: pide un sitio en la mesa, no en el bolso."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; un cierre cada vez."),
                    note("Gesto", "Abrochar prendas del oso, no trepar ni comer."),
                    note("Material", "Madera y tela; recuento de piezas al guardar."),
                    note("Supervisión", "Cordones y hebillas fuera de la boca.")
            ),
            entry(
                    "vestir-melissa-cordones",
                    "Melissa & Doug paneles de cordones",
                    "Enhebrar y atar sin el zapato del pie",
                    "Cinco paneles de madera con forma de mascotas y cordones de colores, a partir de 3 años. Cubre el enhebrado y el nudo en la mesa, distinto del zapato PlanToys y de atarse el calzado puesto.",
                    List.of(
                            "Cinco paneles y cinco cordones a juego.",
                            "Edad declarada a partir de 3 años.",
                            "Se puede practicar sentado, sin calzado."
                    ),
                    List.of(
                            "No enseña a meter el pie en el zapato.",
                            "Los cordones se enredan; recuento al guardar."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Desde 3 años; empieza por pasar el cordón, no por el lazo doble."),
                    note("Gesto", "Enhebrar y tirar, no abotonar."),
                    note("Material", "Madera y cordón; lejos de cuellos."),
                    note("Supervisión", "Cordones no son para el cuello ni para morder.")
            ),
            entry(
                    "vestir-small-foot-cubo",
                    "Small Foot cubo de cierres",
                    "Cremallera, botones y velcro en un cubo de tela",
                    "Cubo textil con un cierre distinto en cada cara: cordones, botones, cremallera, broches, velcro y correas. A partir de 12 meses; a los 3 años sigue siendo útil si el abrigo real aún se atasca. Se puede llevar.",
                    List.of(
                            "Seis tipos de cierre en un objeto compacto.",
                            "Tela suave; 16 cm de lado según la ficha.",
                            "Se transporta sin un tablero de 39 cm."
                    ),
                    List.of(
                            "Formato bebé: a los 3 años puede quedarse corto si ya abrocha el tablero del oso.",
                            "No aísla un solo gesto: hay que elegir una cara."
                    ),
                    "1-5 años",
                    note("Encaje a los 3 años", "Desde 12 meses; a los 3 si el abrigo aún pide práctica."),
                    note("Gesto", "Cierres de ropa en tela, no pestillos de puerta."),
                    note("Material", "Felpa y herrajes textiles."),
                    note("Supervisión", "Cremallera y cordones; no dejar solo a morder.")
            ),
            entry(
                    "vestir-melissa-disfraces",
                    "Melissa & Doug disfraces magnéticos Mejores Amigos",
                    "Probar prendas en una figura, no en el cuerpo",
                    "Tablero para vestir figuras con prendas magnéticas, a partir de 3 años. El gesto es elegir y colocar ropa, no abrochar el botón del abrigo propio. Sirve para nombrar prendas antes de vestirse.",
                    List.of(
                            "Prendas que se colocan y se cambian sin perder botones.",
                            "Edad declarada a partir de 3 años.",
                            "Se guarda plano."
                    ),
                    List.of(
                            "No practica cremallera ni nudo real.",
                            "Las piezas magnéticas se pierden; recuento al guardar."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Desde 3 años; nombra camiseta, abrigo, zapatos."),
                    note("Gesto", "Colocar prendas en la figura, no abrocharse."),
                    note("Material", "Tablero y magnetos; lejos de menores que se los lleven a la boca."),
                    note("Supervisión", "Imanes: recuento y fuera del alcance de hermanos pequeños.")
            ),
            entry(
                    "vestir-melissa-pestillos",
                    "Melissa & Doug tablero de pestillos",
                    "Abrir y cerrar pestillos de la casa",
                    "Tablero de madera con pestillos, cierres y ventanas, a partir de 3 años. No es una prenda: practica el mismo tipo de pinza que un broche o un pestillo de armario. Complementa el oso de vestir, no lo sustituye.",
                    List.of(
                            "Varios pestillos en un marco de madera.",
                            "Edad declarada a partir de 3 años.",
                            "Gesto de abrir y cerrar con dos manos."
                    ),
                    List.of(
                            "No enseña a ponerse el abrigo.",
                            "Pide mesa o suelo; no es un juguete de viaje pequeño."
                    ),
                    "3-6 años",
                    note("Encaje a los 3 años", "Desde 3 años; un pestillo cada vez."),
                    note("Gesto", "Pestillos y ventanas, no botones de camisa."),
                    note("Material", "Madera y herrajes; montaje apretado."),
                    note("Supervisión", "Dedos lejos de cierres que pillen; adulto cerca.")
            )
    );

    private static final List<EditorialEntry> GIFT_SELECTION_3 = List.of(
            entry(
                    "puzle-madera-animales",
                    "Melissa & Doug puzle de animales del safari",
                    "Un regalo que se puede terminar el mismo día",
                    "Puzle de madera de piezas grandes. Si no conoces la casa, un puzle que se completa suele acertar mejor que un juguete «para mayores».",
                    List.of(
                            "Se puede terminar en una sesión.",
                            "Motivo de animales reconocible.",
                            "Madera y piezas grandes."
                    ),
                    List.of(
                            "Si ya monta 16 piezas, puede ser poco reto.",
                            "No cubre movimiento ni autonomía."
                    ),
                    "2-5 años",
                    note("Ocasión", "Cumpleaños o visita cuando no sabes qué tiene."),
                    note("Uso", "Mesa o suelo, con un adulto al principio."),
                    note("Encaje", "Piezas grandes; rango 2-5 años."),
                    note("Límite", "Una escena, no un pack para meses.")
            ),
            entry(
                    "patinete-micro-mini-deluxe",
                    "Micro Mini Deluxe LED",
                    "Un regalo para el aire libre de pie",
                    "Patinete de tres ruedas, 1,95 kg, de 2 a 5 años. Pregunta talla y si hay casco y un sitio sin tráfico. El detalle de modelos está en la comparativa de patinetes.",
                    List.of(
                            "Tres ruedas y poco peso declarado.",
                            "Manillar ajustable.",
                            "Edad 2-5 años."
                    ),
                    List.of(
                            "Hay que preguntar si ya tiene patinete o bici.",
                            "Casco y supervisión no se regalan solos."
                    ),
                    "2-5 años",
                    note("Ocasión", "Aire libre, si hay espacio y casco."),
                    note("Uso", "De pie, tres ruedas."),
                    note("Encaje", "Manillar 48-68 cm según ficha LED."),
                    note("Límite", "No es un triciclo sentado ni una bici sin pedales.")
            ),
            entry(
                    "vajilla-stor-mickey",
                    "Stor vajilla 3 piezas Mickey Mouse",
                    "Un regalo que se usa en cada comida",
                    "Plato, cuenco y vaso de 260 ml, de 3 a 6 años. Si la familia come en casa, un set irrompible se usa más que un juguete de salón.",
                    List.of(
                            "Tres piezas de mesa.",
                            "Base antideslizante y libre de BPA.",
                            "Edad 3-6 años."
                    ),
                    List.of(
                            "El personaje puede cansarse; el plástico de mesa sigue.",
                            "Pregunta si ya tienen set."
                    ),
                    "3-6 años",
                    note("Ocasión", "Regalo útil para la rutina, no solo el día del cumpleaños."),
                    note("Uso", "Comidas diarias."),
                    note("Encaje", "Rango 3-6 años."),
                    note("Límite", "No incluye cubiertos.")
            ),
            entry(
                    "haba-puzles-cuatro-estaciones",
                    "HABA Puzzles Las Cuatro Estaciones",
                    "Cuatro escenas para alargar el regalo",
                    "Cuatro puzles de 15 piezas y figuras de madera, a partir de 3 años. Más margen que un solo tablero si ya encaja siluetas simples.",
                    List.of(
                            "Cuatro motivos para rotar.",
                            "Figuras de madera para jugar después.",
                            "Desde 3 años."
                    ),
                    List.of(
                            "15 piezas pueden ser muchas si aún hace de 5.",
                            "Las figuras son pequeñas."
                    ),
                    "Desde 3 años",
                    note("Ocasión", "Cuando quieres más de una sesión."),
                    note("Uso", "Encajar y juego simbólico sobre la escena."),
                    note("Encaje", "Edad mínima 3 años."),
                    note("Límite", "Figuras: supervisión si hay menores.")
            ),
            entry(
                    "cuentas-melissa-doug",
                    "Melissa & Doug cuentas de madera",
                    "Un regalo pequeño de ensartar",
                    "27 cuentas y 2 cordones, a partir de 3 años. Formato contenido si no quieres una cocina o un patinete. Supervisión: no se deja solo.",
                    List.of(
                            "Madera y dos cordones.",
                            "Se guarda en caja.",
                            "Desde 3 años."
                    ),
                    List.of(
                            "Piezas y cordones: no es un regalo «para dejar en la habitación».",
                            "Si ya tiene un cubo de formas, puede duplicar motricidad fina."
                    ),
                    "3-6 años",
                    note("Ocasión", "Regalo más compacto, no de patio."),
                    note("Uso", "Ensartar en mesa, con adulto."),
                    note("Encaje", "Edad mínima 3 años."),
                    note("Límite", "Riesgo de asfixia si se usan mal.")
            )
    );

    private static final List<EditorialEntry> CHOOSE_GIFT_3 = List.of(
            entry(
                    "juego-montessori-formas",
                    "Melissa & Doug cubo de formas",
                    "Si ya encaja y nombra formas",
                    "Doce piezas grandes en un cubo de madera. Elige este gesto si el niño ya coge, nombra y mete; no si buscas movimiento o la mesa.",
                    List.of(
                            "Uso inmediato, sin reglas largas.",
                            "Piezas grandes.",
                            "Rango 2-4 años."
                    ),
                    List.of(
                            "Si ya tiene un clasificador, no dupliques.",
                            "No cubre aire libre ni comidas."
                    ),
                    "2-4 años",
                    note("Qué ya hace", "Encajar y nombrar."),
                    note("Por qué a los 3", "Sesiones cortas y piezas grandes."),
                    note("Regalo", "Acierto amplio si no conoces otros juguetes."),
                    note("No elijas esto si", "Ya tiene un cubo similar o lo que falta es moverse.")
            ),
            entry(
                    "patinete-micro-mini-deluxe",
                    "Micro Mini Deluxe LED",
                    "Si ya se sostiene de pie y hay un patio",
                    "Tres ruedas y 1,95 kg. Elige movimiento de pie si hay casco y un suelo sin tráfico. Si aún prefiere ir sentado, mira el triciclo en la comparativa de patinetes.",
                    List.of(
                            "Tres ruedas y manillar ajustable.",
                            "Poco peso para levantar.",
                            "2-5 años."
                    ),
                    List.of(
                            "Pregunta talla y si ya hay patinete.",
                            "El casco no es opcional."
                    ),
                    "2-5 años",
                    note("Qué ya hace", "Mantenerse de pie y empujar."),
                    note("Por qué a los 3", "Base de tres ruedas, no dos."),
                    note("Regalo", "Aire libre; pide espacio."),
                    note("No elijas esto si", "No hay casco, o lo que falta es la mesa o encajar.")
            ),
            entry(
                    "vajilla-stor-mickey",
                    "Stor vajilla 3 piezas Mickey Mouse",
                    "Si ya quiere llevar el plato a la mesa",
                    "Set irrompible de 3 a 6 años. Elige autonomía de comida si el niño ya imita servir o beber; no si el hueco es un juguete de sala.",
                    List.of(
                            "Plato, cuenco y vaso.",
                            "Libre de BPA y base antideslizante.",
                            "3-6 años."
                    ),
                    List.of(
                            "Pregunta si ya tienen vajilla infantil.",
                            "Sin cubiertos en el set."
                    ),
                    "3-6 años",
                    note("Qué ya hace", "Comer y beber con menos ayuda."),
                    note("Por qué a los 3", "Piezas que sobreviven a las caídas."),
                    note("Regalo", "Se usa cada día, no solo el cumpleaños."),
                    note("No elijas esto si", "Buscas juego simbólico o movimiento.")
            ),
            entry(
                    "kit-manualidades-natural",
                    "Kit de manualidades con materiales naturales",
                    "Si hay un adulto para crear un rato",
                    "Plastilina ecológica y herramientas de madera. Elige crear si alguien se sienta a modelar; no es un juguete para dejar solo.",
                    List.of(
                            "Materiales naturales declarados y madera.",
                            "Sin pantallas.",
                            "Desde 3 años."
                    ),
                    List.of(
                            "La plastilina se gasta.",
                            "Pide acompañamiento."
                    ),
                    "Desde 3 años",
                    note("Qué ya hace", "Aguantar una sesión corta en mesa con un adulto."),
                    note("Por qué a los 3", "Modelar sin reglamento."),
                    note("Regalo", "Crear; no sustituye patio ni torre."),
                    note("No elijas esto si", "Nadie va a acompañar o buscas un objeto permanente.")
            ),
            entry(
                    "small-foot-grua",
                    "Small Foot grúa de construcción",
                    "Si ya empuja vehículos y le dura el juego",
                    "Grúa FSC 100 % con manivela. Elige un objeto de obra si el niño ya imita oficios y quieres madera que se repita; la manivela pide ayuda al principio.",
                    List.of(
                            "Madera FSC 100 %.",
                            "Giro 360° y manivela.",
                            "Desde 3 años."
                    ),
                    List.of(
                            "Piezas y cuerda: supervisión.",
                            "No es un cubo de formas."
                    ),
                    "Desde 3 años",
                    note("Qué ya hace", "Empujar, cargar y jugar a la obra."),
                    note("Por qué a los 3", "Gesto simple; se puede repetir."),
                    note("Regalo", "Duradero; ocupa mesa o suelo."),
                    note("No elijas esto si", "Buscas encaje de formas o un patinete.")
            )
    );

    private final ProductCatalog productCatalog;

    public ComparisonPageService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Optional<ComparisonPageResponse> getBySlug(String slug) {
        return switch (slug) {
            case BALANCE_BIKES_SLUG -> Optional.of(buildBalanceBikesPage());
            case BOARD_GAMES_SLUG -> Optional.of(buildBoardGamesPage());
            case SCOOTERS_SLUG -> Optional.of(buildScootersPage());
            case TOWERS_SLUG -> Optional.of(buildTowersPage());
            case TABLEWARE_SLUG -> Optional.of(buildTablewarePage());
            case SUSTAINABLE_SLUG -> Optional.of(buildSustainablePage());
            case STEM_5_SLUG -> Optional.of(buildStemFivePage());
            case MONTESSORI_3_SLUG -> Optional.of(buildMontessoriThreePage());
            case PUZZLES_3_SLUG -> Optional.of(buildPuzzlesThreePage());
            case SCOOTERS_3_SLUG -> Optional.of(buildScootersThreePage());
            case TOWERS_3_SLUG -> Optional.of(buildTowersThreePage());
            case TABLEWARE_3_SLUG -> Optional.of(buildTablewareThreePage());
            case GIFTS_3_SLUG -> Optional.of(buildGiftsThreePage());
            case SUSTAINABLE_3_SLUG -> Optional.of(buildSustainableThreePage());
            case DURABLE_3_SLUG -> Optional.of(buildDurableThreePage());
            case ARTS_NATURAL_3_SLUG -> Optional.of(buildArtsNaturalThreePage());
            case MONTESSORI_WOOD_3_SLUG -> Optional.of(buildMontessoriWoodThreePage());
            case SYMBOLIC_3_SLUG -> Optional.of(buildSymbolicThreePage());
            case SENSORY_3_SLUG -> Optional.of(buildSensoryThreePage());
            case BALANCE_GUIDE_3_SLUG -> Optional.of(buildBalanceGuideThreePage());
            case SCOOTERS_TRIKES_3_SLUG -> Optional.of(buildScootersTrikesThreePage());
            case PIKLER_3_SLUG -> Optional.of(buildPiklerThreePage());
            case RIDE_ON_3_SLUG -> Optional.of(buildRideOnThreePage());
            case CUTLERY_3_SLUG -> Optional.of(buildCutleryThreePage());
            case DRESSING_3_SLUG -> Optional.of(buildDressingThreePage());
            case TOWERS_KITCHEN_3_SLUG -> Optional.of(buildTowersKitchenThreePage());
            case TABLEWARE_DAILY_3_SLUG -> Optional.of(buildTablewareDailyThreePage());
            case GIFT_SELECTION_3_SLUG -> Optional.of(buildGiftSelectionThreePage());
            case CHOOSE_GIFT_3_SLUG -> Optional.of(buildChooseGiftThreePage());
            case DURABLE_4_SLUG -> Optional.of(buildDurable4Page());
            case DURABLE_5_SLUG -> Optional.of(buildDurable5Page());
            case ARTS_NATURAL_4_SLUG -> Optional.of(buildArtsNatural4Page());
            case ARTS_NATURAL_5_SLUG -> Optional.of(buildArtsNatural5Page());
            case MONTESSORI_WOOD_4_SLUG -> Optional.of(buildMontessoriWood4Page());
            case MONTESSORI_WOOD_5_SLUG -> Optional.of(buildMontessoriWood5Page());
            case MONTESSORI_4_SLUG -> Optional.of(buildMontessori4Page());
            case MONTESSORI_5_SLUG -> Optional.of(buildMontessori5Page());
            case PUZZLES_4_SLUG -> Optional.of(buildPuzzles4Page());
            case PUZZLES_5_SLUG -> Optional.of(buildPuzzles5Page());
            case SYMBOLIC_4_SLUG -> Optional.of(buildSymbolic4Page());
            case SYMBOLIC_5_SLUG -> Optional.of(buildSymbolic5Page());
            case SENSORY_4_SLUG -> Optional.of(buildSensory4Page());
            case SENSORY_5_SLUG -> Optional.of(buildSensory5Page());
            case BALANCE_GUIDE_4_SLUG -> Optional.of(buildBalanceGuide4Page());
            case BALANCE_GUIDE_5_SLUG -> Optional.of(buildBalanceGuide5Page());
            case SCOOTERS_TRIKES_4_SLUG -> Optional.of(buildScootersTrikes4Page());
            case SCOOTERS_TRIKES_5_SLUG -> Optional.of(buildScootersTrikes5Page());
            case TOWERS_KITCHEN_4_SLUG -> Optional.of(buildTowersKitchen4Page());
            case TOWERS_KITCHEN_5_SLUG -> Optional.of(buildTowersKitchen5Page());
            case TABLEWARE_DAILY_4_SLUG -> Optional.of(buildTablewareDaily4Page());
            case TABLEWARE_DAILY_5_SLUG -> Optional.of(buildTablewareDaily5Page());
            case GIFTS_4_SLUG -> Optional.of(buildGifts4Page());
            case GIFTS_5_SLUG -> Optional.of(buildGifts5Page());
            case GIFT_SELECTION_4_SLUG -> Optional.of(buildGiftSelection4Page());
            case GIFT_SELECTION_5_SLUG -> Optional.of(buildGiftSelection5Page());
            case CHOOSE_GIFT_4_SLUG -> Optional.of(buildChooseGift4Page());
            case CHOOSE_GIFT_5_SLUG -> Optional.of(buildChooseGift5Page());
            case BALANCE_BIKES_4_SLUG -> Optional.of(buildBalanceBikes4Page());
            case BALANCE_BIKES_5_SLUG -> Optional.of(buildBalanceBikes5Page());
            case STEM_4_SLUG -> Optional.of(buildStem4Page());
            case BOARD_GAMES_5_SLUG -> Optional.of(buildBoardGames5Page());
            case SCOOTERS_5_SLUG -> Optional.of(buildScooters5Page());
            case TOWERS_5_SLUG -> Optional.of(buildTowers5Page());
            case TABLEWARE_5_SLUG -> Optional.of(buildTableware5Page());
            case SUSTAINABLE_5_SLUG -> Optional.of(buildSustainable5Page());
            default -> Optional.empty();
        };
    }

    private ComparisonPageResponse buildBalanceBikesPage() {
        Map<String, EditorialEntry> editorialById = new LinkedHashMap<>();
        BALANCE_BIKES.forEach(entry -> editorialById.put(entry.productId(), entry));

        AtomicInteger rank = new AtomicInteger(1);
        List<ComparisonPageResponse.Entry> entries = productCatalog
                .findByIds(BALANCE_BIKES.stream().map(EditorialEntry::productId).toList())
                .stream()
                .filter(product -> product.isAvailableForAge(BALANCE_BIKES_AGE))
                .map(product -> toResponseEntry(
                        rank.getAndIncrement(),
                        product,
                        editorialById.get(product.id())
                ))
                .toList();

        return new ComparisonPageResponse(
                new Seo(
                        BALANCE_BIKES_CANONICAL,
                        "Mejores bicicletas sin pedales para 3 años | Bebes Felices",
                        "Comparamos cinco bicicletas sin pedales aptas para 3 años por ajuste, peso, ruedas y facilidad de manejo, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BALANCE_BIKES_SLUG,
                BALANCE_BIKES_AGE,
                breadcrumbs(),
                new ComparisonPageResponse.Header(
                        "Mejores bicicletas sin pedales para 3 años",
                        "Cinco modelos reales comparados por talla, manejo y tipo de rueda",
                        List.of(
                                "A los 3 años importa más que el niño llegue bien al suelo y pueda controlar la bicicleta que cualquier reclamo comercial. Por eso revisamos ajustes, peso declarado, ruedas y elementos que facilitan el aprendizaje.",
                                "La selección reúne cinco modelos disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 13 de agosto de 2026.",
                                "Una bicicleta sin pedales requiere casco, calzado cerrado, ajuste correcto y supervisión adulta. Comprueba siempre las instrucciones y límites indicados por el fabricante."
                        )
                ),
                List.of(),
                quickSummary(),
                methodology(),
                entries,
                buyingGuide(),
                faq(),
                relatedLinks(),
                trustAuthority(),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                BIKES_PUBLISHED_AT,
                BIKES_UPDATED_AT
        );
    }

    private ComparisonPageResponse buildBoardGamesPage() {
        List<ComparisonPageResponse.Entry> entries = rankedEntries(BOARD_GAMES, BOARD_GAMES_AGE);
        return new ComparisonPageResponse(
                new Seo(
                        BOARD_GAMES_CANONICAL,
                        "Mejores juegos de mesa para 4 años | Bebes Felices",
                        "Comparamos cinco juegos de mesa reales para 4 años por encaje, cooperación, lectura y componentes, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BOARD_GAMES_SLUG,
                BOARD_GAMES_AGE,
                List.of(
                        new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                        new ComparisonPageResponse.Breadcrumb(
                                EditorialDefaults.HUB_4_LABEL,
                                EditorialDefaults.HUB_4_HREF
                        ),
                        new ComparisonPageResponse.Breadcrumb(
                                "Juegos de mesa para 4 años",
                                BOARD_GAMES_CANONICAL
                        )
                ),
                new ComparisonPageResponse.Header(
                        "Mejores juegos de mesa para 4 años",
                        "Cinco juegos reales comparados por reglas, cooperación y tiempo de partida",
                        List.of(
                                "A los 4 años caben las primeras reglas, los turnos cortos y, si el grupo lo admite, un objetivo compartido. Importa más que la partida se pueda explicar en pocos minutos que cualquier reclamo de «el más educativo».",
                                "La selección reúne cinco juegos disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Un adulto debe explicar el objetivo, vigilar piezas pequeñas y adaptar el ritmo. Distinguimos cooperativos y competitivos para que elijas según cómo juega tu casa."
                        )
                ),
                List.of(),
                List.of(
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para empezar a cooperar",
                                "juego-mesa-el-frutal-mini",
                                "Reglas mínimas y un objetivo común frente al cuervo."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para colores y primeros conteos",
                                "juego-mesa-unicornio-tesoro",
                                "Dados, tablero a doble cara y cristales de nube."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para pulso y motricidad fina",
                                "juego-mesa-animal-sobre-animal",
                                "Apilar 29 figuras de madera según el dado."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para observación en familia",
                                "juego-mesa-dobble-kids",
                                "Partidas de 15 minutos y hasta 8 jugadores."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para un cooperativo más estructurado",
                                "juego-mesa-unicornio-fiesta-rosalie",
                                "Dados, ruleta y un objetivo compartido a partir de 4 años."
                        )
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 4 años. No asignamos notas numéricas ni completamos datos que el fabricante o la ficha consultada no permitan confirmar.",
                        List.of(
                                new ComparisonPageResponse.Criterion(
                                        "Encaje a los 4 años",
                                        "Revisamos la edad declarada, si las reglas caben en una partida corta y si el turno se entiende sin un reglamento largo."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Cooperación o competición",
                                        "Dejamos claro si se gana en equipo o hay un ganador individual. A esta edad ambos formatos pueden funcionar; no son intercambiables."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Lectura y dependencia del adulto",
                                        "Priorizamos símbolos, colores y dados frente a texto. Un adulto sigue siendo necesario para explicar y acompañar."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Rejugabilidad",
                                        "Valoramos si hay variantes, azar de dados o suficiente variedad para repetir sin agotarse en una tarde."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Componentes y seguridad",
                                        "Comprobamos materiales declarados y avisos de piezas pequeñas. Ningún juego sustituye la supervisión."
                                )
                        )
                ),
                entries,
                new ComparisonPageResponse.BuyingGuide(List.of(
                        new ComparisonPageResponse.Section(
                                "Empieza por cómo jugáis en casa",
                                List.of(
                                        "Si las derrotas individuales acaban en llanto, un cooperativo como El Frutal Mini suele encajar mejor que un juego de rapidez.",
                                        "Si ya aguanta turnos y le gusta competir un rato, Dobble Kids o Animal sobre Animal cubren observación y pulso sin partidas largas."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Tiempo real de partida",
                                List.of(
                                        "A los 4 años conviene que se pueda terminar. Quince minutos declarados siguen pidiendo un adulto que evite discusiones y recoja piezas.",
                                        "La primera partida cuenta como explicación: no midas el éxito por si «ya juega solo»."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Lectura, idioma y piezas",
                                List.of(
                                        "Dobble Kids está en español. Los HABA de esta lista se juegan con símbolos, dados o ruleta; confirma que el reglamento de tu caja está en un idioma que puedes explicar.",
                                        "Revisa avisos de piezas pequeñas, sobre todo cristales y cartas, si hay hermanos menores de 3 años."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Qué no usamos para ordenar",
                                List.of(
                                        "No ordenamos por precio, número de reseñas ni estrellas. El orden editorial cubre roles distintos: cooperar, contar, apilar, observar y un cooperativo más largo.",
                                        "Comprueba la ficha vigente en Amazon.es antes de comprar: ediciones y contenidos pueden cambiar."
                                )
                        )
                )),
                List.of(
                        new ComparisonPageResponse.Faq(
                                "¿Qué juegos de mesa son adecuados a los 4 años?",
                                "Los de reglas breves, turnos visibles y poca lectura. Los cooperativos de 10-15 minutos y los de observación o apilamiento suelen encajar mejor que los competitivos con texto o partidas largas."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Cooperativo o competitivo?",
                                "Depende del niño y de la casa. Cooperar evita un ganador individual; competir corto puede funcionar si se tolera perder. En esta lista hay ambos, etiquetados con claridad."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Hace falta que el niño sepa leer?",
                                "No en estos cinco. Hace falta un adulto que explique el objetivo y vigile piezas. Dobble Kids se resuelve señalando un dibujo."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿El Frutal Mini es el mismo que el Frutal grande?",
                                "Comparte la idea (recoger fruta antes de que llegue el cuervo) en formato de viaje: menos piezas y hasta 4 jugadores. No es la caja clásica de hasta 8."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian con frecuencia y no forman parte de esta evaluación editorial. La página compara especificaciones y criterios de uso verificables."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Juguetes y regalos para niños de 4 años",
                                EditorialDefaults.HUB_4_HREF,
                                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
                        ),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para adaptar una elección al desarrollo infantil."
                        ),
                        new LinkItem(
                                "Qué habilidades desarrolla un niño de 4 años",
                                "/guias/habilidades-4-anos/",
                                "Atención, reglas, motricidad fina y juego compartido."
                        )
                ),
                new TrustAuthority(
                        "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y solo mostramos hechos revisados el 14 de agosto de 2026.",
                        List.of(
                                "Edad declarada y encaje con partidas cortas a los 4 años.",
                                "Cooperación o competición, según lo indique el fabricante.",
                                "Carga de lectura y necesidad de un adulto.",
                                "Componentes declarados y avisos de seguridad."
                        ),
                        List.of(
                                "Estado editorial publicado y fecha de revisión visibles.",
                                "Sin importes, valoraciones ni puntuaciones.",
                                "Enlaces de afiliación separados del contenido editorial.",
                                "Fecha de revisión visible."
                        )
                ),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                BOARD_GAMES_PUBLISHED_AT,
                BOARD_GAMES_UPDATED_AT
        );
    }

    private ComparisonPageResponse buildScootersPage() {
        List<ComparisonPageResponse.Entry> entries = rankedEntries(SCOOTERS, SCOOTERS_AGE);
        return new ComparisonPageResponse(
                new Seo(
                        SCOOTERS_CANONICAL,
                        "Mejores patinetes y triciclos para 4 años | Bebes Felices",
                        "Comparamos cuatro patinetes de tres ruedas y un triciclo reales para 4 años por talla, estabilidad y tipo de uso, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                SCOOTERS_SLUG,
                SCOOTERS_AGE,
                List.of(
                        new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                        new ComparisonPageResponse.Breadcrumb(
                                EditorialDefaults.HUB_4_LABEL,
                                EditorialDefaults.HUB_4_HREF
                        ),
                        new ComparisonPageResponse.Breadcrumb(
                                "Patinetes y triciclos para 4 años",
                                SCOOTERS_CANONICAL
                        )
                ),
                new ComparisonPageResponse.Header(
                        "Mejores patinetes y triciclos para 4 años",
                        "Cinco modelos reales comparados por estabilidad, talla y tipo de uso",
                        List.of(
                                "A los 4 años el patinete de tres ruedas sigue ofreciendo una base más estable que uno de dos ruedas. Un triciclo cubre otra necesidad: pedalear sentado, a veces con mango de adulto. No son intercambiables.",
                                "La selección reúne cuatro patinetes y un triciclo disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Casco homologado, calzado cerrado, una zona sin tráfico y supervisión adulta no se sustituyen con tres ruedas, luces LED ni un mango parental. Comprueba siempre las instrucciones y límites del fabricante."
                        )
                ),
                List.of(),
                List.of(
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para empezar de pie con tres ruedas",
                                "patinete-micro-mini-deluxe",
                                "1,95 kg declarados, giro por inclinación y manillar 48-68 cm."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para una primera opción sencilla",
                                "patinete-molto-maxi",
                                "De 3 a 5 años, manillar 57-67 cm y montaje sin herramientas."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para llevar y guardar",
                                "patinete-globber-junior-foldable",
                                "Plegable, bloqueo de dirección y tres alturas de manillar."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para más altura a partir de 4 años",
                                "patinete-globber-master-lights",
                                "Manillar de 74 a 94 cm y plegado desde 4 años."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para pedalear sentado",
                                "triciclo-chicco-u-go",
                                "Triciclo 2en1 con mango de adulto y hasta 20 kg."
                        )
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 4 años. No asignamos notas numéricas ni completamos datos que el fabricante o la ficha consultada no permitan confirmar.",
                        List.of(
                                new ComparisonPageResponse.Criterion(
                                        "Encaje a los 4 años",
                                        "Revisamos la edad declarada y, cuando está publicada, la altura del manillar. Un modelo desde 4 años con manillar a 74 cm no encaja igual que uno de 48 a 68 cm."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Estabilidad y tipo de uso",
                                        "Separamos patinete de pie (tres ruedas) y triciclo sentado. Tres ruedas aportan base; no equivalen a un patinete de dos ruedas ni a una bicicleta sin pedales."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Manejo y talla",
                                        "Consideramos el peso del patinete cuando está declarado, el recorrido del manillar y la carga máxima. Sin cifra verificada, no la inventamos."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Transporte",
                                        "Valoramos el plegado o el manillar desmontable solo cuando la ficha lo indica. No deducimos que un modelo no se pueda guardar."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Seguridad práctica",
                                        "Comprobamos freno, bloqueo de dirección o mango parental cuando están documentados. Ninguno sustituye casco, supervisión y una zona segura."
                                )
                        )
                ),
                entries,
                new ComparisonPageResponse.BuyingGuide(List.of(
                        new ComparisonPageResponse.Section(
                                "Decide primero si va de pie o sentado",
                                List.of(
                                        "El patinete practica equilibrio de pie, dirección y frenado. El triciclo practica pedaleo sentado, a veces con mango de adulto. A los 4 años pueden convivir; no cubren lo mismo.",
                                        "Si el objetivo es moverse de pie al aire libre, empieza por un patinete de tres ruedas con manillar que llegue a sus manos. Si aún necesita que le empujen en paseos largos, el triciclo 2en1 cubre ese tramo."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Mide la altura del manillar",
                                List.of(
                                        "El niño debe alcanzar el manillar sin encogerse ni estirarse en exceso. Contrasta su altura con el rango publicado: 48-68 cm no es intercambiable con 74-94 cm.",
                                        "No elijas solo por edad. El Master Lights está indicado desde 4 años, pero su manillar mínimo es 74 cm; un niño bajo puede encajar mejor en Mini Deluxe o Junior."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Plegado, peso y carga",
                                List.of(
                                        "Si lo vas a meter en el coche o en un ascensor, el plegado con modo carrito (Junior y Master) o el manillar desmontable (Mini Deluxe) cambian el día a día.",
                                        "Revisa la carga máxima. Los patinetes de esta lista declaran 50 kg cuando figura; el U-GO declara 20 kg. Comprueba el peso real del niño antes de comprar el triciclo."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Seguridad antes de salir",
                                List.of(
                                        "Casco homologado, calzado cerrado, freno comprobado y una zona sin tráfico, bordillos altos ni agua. Las luces LED no sustituyen visibilidad ni un adulto atento.",
                                        "El bloqueo de dirección y el mango parental ayudan al aprendizaje; no evitan caídas. Un solo niño por vehículo y según el manual del fabricante."
                                )
                        )
                )),
                List.of(
                        new ComparisonPageResponse.Faq(
                                "¿Patinete o triciclo a los 4 años?",
                                "Depende de si quieres equilibrio de pie o pedaleo sentado. El patinete de tres ruedas es más estable que uno de dos; el triciclo no practica ese equilibrio. En esta lista hay ambos, etiquetados con claridad."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Sigue haciendo falta un patinete de tres ruedas a los 4 años?",
                                "A menudo sí, mientras el equilibrio de pie no esté asentado. Tres ruedas no son un recambio de la bicicleta sin pedales: aquí se está de pie, se dirige y se frena."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿El Globber Master Lights vale para cualquier niño de 4 años?",
                                "Está indicado desde 4 años, pero el manillar parte de 74 cm. Si el niño es bajo, Mini Deluxe LED (48-68 cm) o Junior Foldable (54-68 cm) encajan mejor. Mide antes de decidir."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué el triciclo tiene un límite de 20 kg?",
                                "Porque así lo declara Chicco en el U-GO 2en1. A los 4 años hay que comprobar el peso: 20 kg no es equivalente a los 50 kg de los patinetes de esta comparativa."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian con frecuencia y no forman parte de esta evaluación editorial. La página compara especificaciones y criterios de uso verificables."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Juguetes y regalos para niños de 4 años",
                                EditorialDefaults.HUB_4_HREF,
                                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
                        ),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para adaptar una elección al desarrollo infantil."
                        ),
                        new LinkItem(
                                "Bicicletas sin pedales",
                                "/movimiento/bicicletas-sin-pedales/",
                                "El otro eje de movimiento a esta edad: equilibrio sentado."
                        )
                ),
                new TrustAuthority(
                        "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y solo mostramos hechos revisados el 14 de agosto de 2026.",
                        List.of(
                                "Edad declarada y altura de manillar cuando está publicada.",
                                "Uso de pie (patinete) o sentado (triciclo).",
                                "Peso, carga máxima y plegado verificados.",
                                "Freno, bloqueo de dirección o mango parental, sin sustituir casco ni supervisión."
                        ),
                        List.of(
                                "Estado editorial publicado y fecha de revisión visibles.",
                                "Sin importes, valoraciones ni puntuaciones.",
                                "Enlaces de afiliación separados del contenido editorial.",
                                "Fecha de revisión visible."
                        )
                ),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                SCOOTERS_PUBLISHED_AT,
                SCOOTERS_UPDATED_AT
        );
    }

    private ComparisonPageResponse buildTowersPage() {
        return ageComparison(
                TOWERS_SLUG,
                TOWERS_CANONICAL,
                TOWERS_AGE,
                TOWERS,
                "Mejores torres de aprendizaje para 4 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje reales para 4 años por estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres de aprendizaje para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores torres de aprendizaje para 4 años",
                        "Cinco modelos reales comparados por estabilidad, altura y uso en cocina",
                        List.of(
                                "A los 4 años la torre sigue siendo un puesto de colaboración, no un taburete improvisado. Importa que la plataforma llegue a la encimera, que la base no vuelque y que un adulto esté al lado.",
                                "La selección reúne cinco torres disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Nunca junto a fogones, agua hirviendo o cuchillos. Revisa tornillos, huecos para la cabeza y el manual. La certificación no sustituye la supervisión."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar y convertir en mesa", "torre-yoleo-transformer", "Nogal, pizarra magnética y modo escritorio."),
                        summary("Para tres alturas y poco peso", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para guardar detrás de la puerta", "torre-costway-plegable", "Plegable desde 3 años, con barra de seguridad."),
                        summary("Para tres alturas con EN-71", "torre-bey-co", "Patas anticaída y superficie antideslizante."),
                        summary("Para torre y escritorio con ajuste", "torre-maxi-cosi-toucan", "Convertible en mesa; 8,7 kg declarados.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 4 años. No asignamos notas ni completamos datos que la ficha no permita confirmar.",
                        List.of(
                                criterion("Encaje a los 4 años", "Revisamos si la altura de plataforma llega a una encimera típica y si el niño puede subir y bajar sin trepar por fuera."),
                                criterion("Estabilidad", "Base, patas anticaída y barandilla cuando están publicadas. Un adulto sigue siendo obligatorio."),
                                criterion("Regulación y plegado", "Tres alturas, conversión a mesa o plegado solo si la ficha lo declara."),
                                criterion("Materiales", "Madera o contrachapado y certificaciones EN-71 cuando figuran."),
                                criterion("Seguridad práctica", "Huecos, bloqueos y avisos del fabricante. Ninguna torre es un juguete de trepa.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide la encimera antes", List.of(
                                "La plataforma debe dejar los brazos cómodos sobre la superficie de trabajo, sin que el niño se incline fuera de la barandilla.",
                                "Tres alturas no sirven si la más alta sigue baja para tu cocina. Comprueba centímetros reales."
                        )),
                        section("Plegado o uso fijo", List.of(
                                "Si la cocina es pequeña, una torre plegable (YOLEO o COSTWAY) cambia el día a día.",
                                "Si va a durar años, valora conversión a escritorio cuando deje de usarse en la encimera."
                        )),
                        section("Seguridad de uso", List.of(
                                "Suelo nivelado, lejos del fuego y del agua hirviendo. Un solo niño. Adulto presente.",
                                "Revisa tornillos tras el montaje y periódicamente. No la uses como escalera de adulto y de niño a la vez."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue teniendo sentido una torre a los 4 años?",
                                "Sí, si participa en cocina o lavabo y aún no llega. Si ya alcanza con un taburete estable y hay supervisión, puede no hacer falta."),
                        faqItem("¿Madera o contrachapado?",
                                "Lo decisivo es la estabilidad y los cierres. La haya FSC de hauck y el nogal de YOLEO son materiales distintos; no los ordenamos por precio."),
                        faqItem("¿Cuál se pliega?",
                                "COSTWAY y YOLEO se pliegan. hauck y BEY & CO no declaran plegado."),
                        faqItem("¿Puede usarla solo?",
                                "No. Aunque suba y baje, el riesgo está en la encimera: fuego, cuchillos, agua."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Vajilla infantil para 4 años",
                                "/comparativas/" + TABLEWARE_SLUG + "/",
                                "La otra pieza de autonomía en la rutina diaria."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildTablewarePage() {
        return ageComparison(
                TABLEWARE_SLUG,
                TABLEWARE_CANONICAL,
                TABLEWARE_AGE,
                TABLEWARE,
                "Mejores vajillas infantiles para 4 años | Bebes Felices",
                "Comparamos cinco opciones reales de vajilla y vasos para 4 años por piezas, estabilidad y cuidado, con metodología y afiliación transparentes.",
                "Vajilla infantil para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores vajillas infantiles para 4 años",
                        "Cinco productos reales para comer y beber con más autonomía",
                        List.of(
                                "A los 4 años el gesto de llevar el plato a la mesa ya cabe. Importa que no se haga añicos, que la base no resbale y que el vaso se pueda coger. Cubiertos y vaso no siempre vienen en el mismo set.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Revisa el marcado de apto para alimento. Un vaso 360° o una tapa no sustituyen la supervisión con hermanos pequeños."
                        )
                ),
                "Los cinco productos",
                List.of(
                        summary("Para no mezclar la comida", "vajilla-twistshake-dividido", "Tres compartimentos, tapa y base antideslizante."),
                        summary("Para un set de mesa completo", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para beber sin tetina", "vaso-munchkin-miracle-360", "Dos vasos 360° de 207 ml con asas."),
                        summary("Para plato grande de mesa", "vajilla-fun-house", "Plato de 22 cm, cuenco de 16 cm y vaso de 220 ml."),
                        summary("Para guardar lo que sobra", "cuenco-twistshake-tapa", "Cuenco con tapa, sin ser un set completo.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 4 años. No asignamos notas ni completamos volúmenes o materiales que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 4 años", "Priorizamos piezas que el niño pueda llevar a la mesa familiar, no solo tronas de bebé."),
                                criterion("Estabilidad", "Base antideslizante, asas o vaso bajo cuando están publicados."),
                                criterion("Piezas incluidas", "Dejamos claro si es un set, un plato, un vaso o un cuenco."),
                                criterion("Cuidado", "Microondas y lavavajillas solo si la ficha lo indica."),
                                criterion("Material y alimento", "Libre de BPA u otros avisos cuando figuran. No inventamos composiciones.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que falta en casa", List.of(
                                "Si ya hay plato y falta vaso, un Miracle 360 cubre el paso a beber sin tetina.",
                                "Si tira el plato, un modelo con base antideslizante o Click-Mat aporta más que un motivo de personaje."
                        )),
                        section("Set o pieza suelta", List.of(
                                "Un set de 3 piezas simplifica el regalo. Un plato con tapa sirve para llevar o guardar.",
                                "Los cubiertos no están en esta lista: añádelos aparte si aún come con cubiertos de adulto demasiado largos."
                        )),
                        section("Limpieza y seguridad", List.of(
                                "Confirma microondas y cubeta del lavavajillas. Las válvulas 360° hay que desmontarlas.",
                                "Retira piezas con recubrimiento deteriorado. El marcado de alimento manda."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue haciendo falta vajilla infantil a los 4 años?",
                                "Sí, si el cristal de adulto se rompe o el vaso es inestable. Si ya come con la vajilla familiar sin incidentes, no es obligatorio."),
                        faqItem("¿Vaso 360° o vaso abierto?",
                                "El 360° reduce derrames y acerca el gesto de un vaso abierto. El vaso de 260 ml del set Stor es abierto de verdad."),
                        faqItem("¿Por qué hay dos Twistshake?",
                                "Cubren roles distintos: plato con compartimentos y cuenco con tapa. No son un set combinado en un solo ASIN."),
                        faqItem("¿Los personajes importan?",
                                "Para esta evaluación, no. El motivo no ordena el ranking; sí advertimos que es un reclamo que puede caducar."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 4 años",
                                "/comparativas/" + TOWERS_SLUG + "/",
                                "Participar en la cocina con una plataforma estable."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildSustainablePage() {
        return ageComparison(
                SUSTAINABLE_SLUG,
                SUSTAINABLE_CANONICAL,
                SUSTAINABLE_AGE,
                SUSTAINABLE,
                "Mejores regalos sostenibles para 4 años | Bebes Felices",
                "Comparamos cinco regalos reales de madera o plástico reciclado para 4 años, con metodología y afiliación transparentes.",
                "Regalos sostenibles para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos sostenibles para 4 años",
                        "Cinco productos reales de madera certificada o plástico reciclado",
                        List.of(
                                "Sostenible, aquí, quiere decir materiales declarados (madera, FSC, plástico reciclado) y un uso que no se agota en una tarde. No es un sello único ni una promesa de «cero impacto».",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Las cuentas y las figuras pequeñas piden supervisión. Un material responsable no elimina piezas pequeñas."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para ensartar y contar", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones."),
                        summary("Para practicar nudos", "plantoys-ata-zapato", "Madera de caucho y tintes al agua."),
                        summary("Para haya y cartón", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas y figuras de madera."),
                        summary("Para madera FSC de obra", "small-foot-grua", "Grúa giratoria certificada FSC 100 %."),
                        summary("Para plástico reciclado lavable", "green-toys-construccion", "Tres vehículos sin BPA ni PVC.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos materiales y usos verificables para un niño de 4 años. No asignamos una nota de sostenibilidad ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si el gesto (ensartar, encajar, atar, empujar) cabe en esta etapa."),
                                criterion("Materiales", "Madera, FSC, plástico reciclado, tintes o ausencia de BPA/PVC cuando están publicados."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Duración", "Si se puede repetir o guardar. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Seguridad", "Piezas pequeñas, cordones y supervisión. El material no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto, no el adjetivo", List.of(
                                "Si quieres motricidad fina, las cuentas. Si quieres autonomía de vestirse, el zapato PlanToys. Si quieres juego simbólico, la grúa o los vehículos."
                        )),
                        section("Madera y plástico reciclado no son intercambiables", List.of(
                                "Green Toys declara plástico 100 % reciclado y se lava en el lavavajillas. Small Foot y PlanToys declaran madera y procesos distintos.",
                                "FSC 100 % no es lo mismo que «madera» a secas. Lo citamos solo cuando figura."
                        )),
                        section("Supervisión", List.of(
                                "Cordones, cuentas y figuras de puzle no se dejan con menores de 3 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un regalo sostenible a los 4 años?",
                                "Uno de materiales declarados y uso repetible. No publicamos una huella de carbono ni un ranking ecológico numérico."),
                        faqItem("¿Madera siempre es mejor que plástico?",
                                "No en abstracto. El plástico reciclado lavable cubre arena y agua; la madera FSC cubre otro tipo de juego. Elige según el uso."),
                        faqItem("¿Las cuentas de Melissa & Doug son un kit de manualidades?",
                                "No. Se ensartan y se cuentan. El análisis genérico de «kit de manualidades» del circuito de 3 años es otra página."),
                        faqItem("¿Hace falta FSC?",
                                "Es un dato útil cuando está. Su ausencia en otra ficha no significa que la madera sea ilegal; simplemente no lo afirmamos."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Ideas de regalo para 4 años",
                                "/regalos/ideas-regalo-4-anos/",
                                "Selección más amplia por ocasión, no solo materiales."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildStemFivePage() {
        return ageComparison(
                STEM_5_SLUG,
                STEM_5_CANONICAL,
                STEM_5_AGE,
                STEM_5,
                "Mejores juguetes STEM para 5 años | Bebes Felices",
                "Comparamos cinco juguetes existentes aptos para 5 años por lógica, construcción, patrones y uso evolutivo.",
                "Juguetes STEM para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores juguetes STEM para 5 años",
                        "Cinco opciones reales para construir, observar patrones y resolver problemas",
                        List.of(
                                "A los 5 años un juguete STEM útil plantea un problema visible: levantar una carga, mantener una torre, completar un patrón o construir una estructura.",
                                "Reutilizamos productos ya presentes en el catálogo y aptos para esta edad. No añadimos precios, estrellas ni enlaces comerciales inventados.",
                                "STEM no significa dejar al niño solo: los imanes, piezas y reglas requieren revisar la seguridad y acompañar el primer uso."
                        )
                ),
                "Los cinco juguetes",
                List.of(
                        summary("Para construcción tridimensional", "set-construccion-magnetico", "Formas, simetría y estabilidad con piezas magnéticas."),
                        summary("Para mecanismos sencillos", "small-foot-grua", "Giro, manivela y transporte de cargas."),
                        summary("Para equilibrio", "juego-mesa-animal-sobre-animal", "Apilado, apoyo y distribución del peso."),
                        summary("Para orientación espacial", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas."),
                        summary("Para reconocer patrones", "juego-mesa-dobble-kids", "Comparación visual con una regla breve.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el tipo de reto, la autonomía posible y la seguridad para un niño de 5 años. La selección no presupone que todo juguete educativo sea STEM.",
                        List.of(
                                criterion("Reto STEM", "Debe practicar construcción, mecanismos, equilibrio, orientación o patrones de forma observable."),
                                criterion("Encaje a los 5 años", "Todos los productos incluyen esta edad en el rango del catálogo."),
                                criterion("Uso evolutivo", "Valoramos si el reto admite repetición o dificultad creciente."),
                                criterion("Dependencia del adulto", "Indicamos cuándo hace falta explicar reglas o proponer el primer reto."),
                                criterion("Seguridad", "Revisamos imanes, piezas sueltas y la presencia de menores de 3 años.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el tipo de problema", List.of(
                                "Para construir, prioriza el set magnético; para mecanismos, la grúa; para equilibrio, Animal sobre Animal.",
                                "Los puzles y Dobble cubren orientación y patrones, pero no sustituyen una experiencia de construcción."
                        )),
                        section("Busca dificultad ajustable", List.of(
                                "Empieza con pocas piezas o una regla y aumenta el reto cuando pueda terminar sin ayuda constante.",
                                "Un producto evolutivo permite cambiar la consigna sin comprar otro juguete."
                        )),
                        section("Revisa piezas e imanes", List.of(
                                "Los imanes deben estar encapsulados y las piezas sueltas fuera del alcance de menores de 3 años.",
                                "La edad recomendada no sustituye revisar el estado del producto antes de cada uso."
                        ))
                )),
                List.of(
                        faqItem("¿Qué significa STEM a los 5 años?", "Resolver problemas de ciencia, tecnología, ingeniería o matemáticas mediante juego: construir, observar mecanismos, equilibrar o reconocer patrones."),
                        faqItem("¿Hace falta un juguete electrónico?", "No. Una grúa con manivela, un apilado o una construcción magnética permiten observar principios físicos sin pantalla."),
                        faqItem("¿Cuál admite más dificultad?", "La construcción magnética ofrece el margen más directo: se pueden añadir piezas, restricciones y estructuras más complejas."),
                        faqItem("¿Todos sirven para jugar sin adulto?", "No. Conviene explicar el primer reto, acompañar las reglas y revisar imanes y piezas."),
                        faqItem("¿Por qué no aparecen precios ni estrellas?", "Porque cambian y no forman parte del criterio editorial estable.")
                ),
                List.of(
                        new LinkItem("Juguetes y regalos para niños de 5 años", EditorialDefaults.hubHref(5), "Volver al hub de esta edad."),
                        new LinkItem("Ideas de regalo para 5 años", "/regalos/ideas-regalo-5-anos/", "Selección más amplia por necesidad."),
                        new LinkItem("Qué habilidades desarrolla un niño de 5 años", "/guias/habilidades-5-anos/", "Pensamiento lógico, cooperación y autonomía."),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildMontessoriThreePage() {
        return ageComparison(
                MONTESSORI_3_SLUG,
                MONTESSORI_3_CANONICAL,
                AGE_3,
                MONTESSORI_3,
                "Mejores juegos Montessori de formas y encajes para 3 años | Bebes Felices",
                "Comparamos cinco juegos de encaje y clasificación reales para 3 años por piezas, actividad y margen de dificultad, con metodología y afiliación transparentes.",
                "Juegos Montessori para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores juegos Montessori de formas y encajes para 3 años",
                        "Cinco materiales reales para clasificar formas y colores",
                        List.of(
                                "A los 3 años el encaje útil es el que se entiende sin un manual: coger una pieza, nombrar la forma y meterla. El adjetivo «Montessori» no sustituye piezas grandes ni una consigna clara.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Un adulto cercano sigue haciendo falta. Si hay hermanos más pequeños, revisa el tamaño de cada figura antes de dejar el set en el suelo."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para formar animales con modelos", "montessori-janod-animales", "Treinta imanes y diez modelos de 3 a 8 años."),
                        summary("Para tres escenas de encaje", "montessori-melissa-tres-puzzles", "Granja, safari y vehículos a partir de 3 años."),
                        summary("Para clasificar por color", "montessori-janod-ballenas", "Doce animales marinos y cuatro ballenas."),
                        summary("Para nueve formas en un soporte", "montessori-janod-tropik", "Madera FSC de 1 a 3 años."),
                        summary("Para ocho animales con pomos", "puzle-melissa-granja-peg", "Tablero de granja de 2 a 4 años.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la actividad de encaje y su utilidad para un niño de 3 años. No asignamos una nota «Montessori» ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si se puede terminar una ronda sin frustración."),
                                criterion("Actividad", "Clasificar y encajar, no un set con diez modos."),
                                criterion("Piezas", "Número y tamaño. 12 piezas no son 26."),
                                criterion("Margen de dificultad", "Si el material se queda corto en semanas o admite un siguiente nivel."),
                                criterion("Seguridad", "Piezas grandes y supervisión. El nombre comercial no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "Si aún necesita pocos elementos, Tropik o el puzle de granja bastan. Si ya encaja con soltura, Magneti'Book Animales o el pack de tres puzles dan más margen.",
                                "No elijas por el sello Montessori. Elige por si el niño puede completar una ronda hoy."
                        )),
                        section("Cuántas piezas sacar", List.of(
                                "Con doce animales marinos de las ballenas, termina una sesión corta y recoge antes de abrir otro set.",
                                "No mezcles las piezas magnéticas de Magneti'Book Animales con los tableros de madera en la misma mesa."
                        )),
                        section("Supervisión", List.of(
                                "Es un material de mesa o de suelo con un adulto cerca, no un juguete para la habitación a solas.",
                                "Revisa piezas pequeñas si hay menores de 3 años en casa."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un material certificado Montessori?",
                                "No. Lo útil es clasificar y encajar con piezas seguras. El nombre comercial no sustituye el criterio de edad."),
                        faqItem("¿Cuántas piezas son demasiadas a los 3 años?",
                                "Si no puede terminar una ronda, sobran. Empieza por Tropik o el puzle de granja y sube a Magneti'Book Animales después."),
                        faqItem("¿Janod sirve si ya tiene 3 años cumplidos?",
                                "Tropik llega al tope de su rango a los 3 años. Si ya encaja sin esfuerzo, Magneti'Book Animales o el pack de tres puzles dan más recorrido."),
                        faqItem("¿Se puede usar sin un adulto?",
                                "Con supervisión cercana, sí, cuando las piezas son grandes. No es un juguete para dejar solo."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Puzles de piezas grandes para 3 años",
                                "/comparativas/" + PUZZLES_3_SLUG + "/",
                                "Otra vía de motricidad fina: completar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildPuzzlesThreePage() {
        return ageComparison(
                PUZZLES_3_SLUG,
                PUZZLES_3_CANONICAL,
                AGE_3,
                PUZZLES_3,
                "Mejores puzles de piezas grandes para 3 años | Bebes Felices",
                "Comparamos cinco puzles reales para 3 años por número de piezas, soporte y si se pueden terminar en una sesión, con metodología y afiliación transparentes.",
                "Puzles para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores puzles de piezas grandes para 3 años",
                        "Cinco puzles reales para encajar sin piezas diminutas",
                        List.of(
                                "A los 3 años un puzle sirve para encajar, nombrar lo que se ve y terminar. No es un puzzle de 100 piezas ni un reto de paciencia adulta.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Empieza junto, nombra las piezas que faltan y guarda cada escena aparte. El éxito repetible importa más que el recuento impreso en la caja."
                        )
                ),
                "Los cinco puzles",
                List.of(
                        summary("Para piezas grandes de safari", "puzle-madera-animales", "Una escena de madera y animales reconocibles."),
                        summary("Para encajar en tablero", "puzle-melissa-mascotas", "Ocho mascotas de madera sobre tablero."),
                        summary("Para empezar por 5 piezas", "puzle-educa-selva", "Cuatro puzles de 5 a 8 piezas."),
                        summary("Para 15 piezas y haya", "haba-puzles-cuatro-estaciones", "Cuatro estaciones y figuras de madera."),
                        summary("Para 16 piezas de madera", "puzle-educa-disney-madera", "Dos escenas cuando 8 piezas ya no retan.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el número de piezas, el soporte y si un niño de 3 años puede terminar. No asignamos notas ni inventamos recuentos que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si la sesión puede acabarse."),
                                criterion("Dificultad", "De silueta con tablero a 16 piezas sueltas."),
                                criterion("Soporte", "Tablero, marco o piezas sueltas; madera o cartón cuando figura."),
                                criterion("Progresión", "Un pack de 4 puzles no es lo mismo que una sola escena."),
                                criterion("Seguridad", "Piezas y figuras pequeñas fuera del alcance de menores de 3 años.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya monta", List.of(
                                "Si aún encaja siluetas, mascotas o safari. Si termina 8 piezas en un minuto, HABA o Educa Disney.",
                                "Un recuento alto no es un regalo mejor: es un riesgo de dejarlo a medias."
                        )),
                        section("Cartón o madera", List.of(
                                "Ambos valen si son gruesos. La madera suele durar más en un uso intenso.",
                                "Un tablero sujeta las piezas; un puzle suelto de 16 pide más mesa y más paciencia."
                        )),
                        section("Si se rinde a mitad", List.of(
                                "Reduce las piezas a la vista, nombra lo que falta y termina juntos.",
                                "Guarda cada puzle en su bolsa. Mezclar 15 + 16 piezas acaba con la sesión."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas recomendáis a los 3 años?",
                                "Las justas para terminar. Un puzle de silueta o de 5-8 piezas suele encajar mejor que uno de decenas de piezas pequeñas."),
                        faqItem("¿Cartón o madera?",
                                "Ambos si son gruesos. El cartón fino se dobla y frustra."),
                        faqItem("¿Cuándo pasar a 15 o 16 piezas?",
                                "Cuando 8 piezas se resuelven sin esfuerzo. HABA y Educa Disney cubren ese paso, con un adulto al principio."),
                        faqItem("¿El puzle de mascotas es lo mismo que el de safari?",
                                "No. Las mascotas son tablero con siluetas y piezas que se sostienen de pie; el safari es una escena de piezas grandes. Cubren el mismo tramo de edad con gestos distintos."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Juegos Montessori de formas y encajes",
                                "/comparativas/" + MONTESSORI_3_SLUG + "/",
                                "Clasificar formas, no montar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildScootersThreePage() {
        return ageComparison(
                SCOOTERS_3_SLUG,
                SCOOTERS_3_CANONICAL,
                AGE_3,
                SCOOTERS_3,
                "Mejores patinetes de 3 ruedas para 3 años | Bebes Felices",
                "Comparamos cuatro patinetes de tres ruedas y un triciclo reales para 3 años por talla, estabilidad y tipo de uso, con metodología y afiliación transparentes.",
                "Patinetes para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores patinetes de 3 ruedas para 3 años",
                        "Cinco modelos reales comparados por estabilidad, talla y tipo de uso",
                        List.of(
                                "A los 3 años el equilibrio de pie todavía se está asentando. Un patinete de tres ruedas ofrece una base más estable que uno de dos. Un triciclo cubre otra necesidad: pedalear sentado, a veces con mango de adulto.",
                                "La selección reúne cuatro patinetes y un triciclo disponibles en Amazon.es. No incluimos el Globber Master Lights, indicado desde 4 años con manillar desde 74 cm. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Casco homologado, calzado cerrado, una zona sin tráfico y supervisión adulta no se sustituyen con tres ruedas ni con luces LED."
                        )
                ),
                "Los cinco modelos",
                List.of(
                        summary("Para empezar de pie con poco peso", "patinete-micro-mini-deluxe", "1,95 kg y manillar 48-68 cm."),
                        summary("Para una primera opción de 3 a 5 años", "patinete-molto-maxi", "Manillar 57-67 cm y montaje sin herramientas."),
                        summary("Para plegar y llevar", "patinete-globber-junior-foldable", "Tres alturas y modo carrito."),
                        summary("Para empezar sentado y pasar a pie", "patinete-micro-mini-3en1", "Asiento extraíble y manillar 48-68 cm."),
                        summary("Para pedalear sentado", "triciclo-chicco-u-go", "Mango de adulto y hasta 20 kg.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 3 años. No asignamos notas ni completamos pesos o alturas que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y altura de manillar. Un modelo desde 4 años con manillar a 74 cm no entra en esta lista."),
                                criterion("Estabilidad y tipo de uso", "Patinete de pie (tres ruedas) frente a triciclo sentado."),
                                criterion("Manejo y talla", "Peso cuando está declarado, recorrido del manillar y carga máxima."),
                                criterion("Transporte", "Plegado o manillar desmontable solo si la ficha lo indica."),
                                criterion("Seguridad práctica", "Freno, bloqueo o mango parental. Ninguno sustituye casco ni supervisión.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Decide si va de pie o sentado", List.of(
                                "El patinete practica equilibrio de pie. El triciclo practica pedaleo sentado, a veces con mango. A los 3 años el mango sigue siendo útil en paseos largos.",
                                "No son intercambiables con la bicicleta sin pedales."
                        )),
                        section("Mide el manillar", List.of(
                                "El niño debe alcanzar el manillar sin encogerse ni estirarse. 48-68 cm no es intercambiable con 57-67 cm.",
                                "Si el niño es bajo, Mini Deluxe, Mini 3en1 o Junior (54 cm mínimo) encajan mejor que Molto (57 cm)."
                        )),
                        section("Seguridad antes de salir", List.of(
                                "Casco, calzado cerrado, freno comprobado y una zona sin tráfico.",
                                "El asiento del Mini 3en1 y el U-GO declaran 20 kg: comprueba el peso real si aún va sentado."
                        ))
                )),
                List.of(
                        faqItem("¿Patinete o bicicleta sin pedales primero?",
                                "Depende del espacio y del niño. La bici trabaja el equilibrio sentado; el patinete, de pie. A los 3 años ambos pueden convivir con supervisión."),
                        faqItem("¿Por qué no está el Globber Master Lights?",
                                "Está indicado desde 4 años y el manillar parte de 74 cm. No encaja como primera opción a los 3 años."),
                        faqItem("¿Cuándo pasar a dos ruedas?",
                                "Cuando el equilibrio lateral ya es estable y frena con control. Muchas familias lo retrasan más allá de los 3 años."),
                        faqItem("¿Hace falta casco?",
                                "Sí. También calzado cerrado y un adulto atento."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/" + BALANCE_BIKES_SLUG + "/",
                                "La otra vía de movimiento: equilibrio sentado."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildTowersThreePage() {
        return ageComparison(
                TOWERS_3_SLUG,
                TOWERS_3_CANONICAL,
                AGE_3,
                TOWERS_3,
                "Mejores torres de aprendizaje para 3 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje reales para 3 años por estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres de aprendizaje para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores torres de aprendizaje para 3 años",
                        "Cinco modelos reales comparados por estabilidad, altura y uso en cocina",
                        List.of(
                                "A los 3 años la torre es un puesto de colaboración con un adulto al lado, no un taburete ni un juguete de trepa. Importa que la plataforma llegue a la encimera y que la base no vuelque.",
                                "La selección reúne cinco torres disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Nunca junto a fogones, agua hirviendo o cuchillos. La supervisión a esta edad es constante."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar desde 3 años", "torre-costway-plegable", "Plegable y ficha recomendada a partir de 3 años."),
                        summary("Para plegar y convertir en mesa", "torre-yoleo-transformer", "Nogal y modo escritorio más adelante."),
                        summary("Para tres alturas y poco peso", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para patas anticaída y EN-71", "torre-bey-co", "Tres alturas y superficie antideslizante."),
                        summary("Para torre y escritorio", "torre-maxi-cosi-toucan", "Convertible; 8,7 kg declarados.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 3 años. No asignamos notas ni completamos alturas que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 3 años", "Si la ficha recomienda esta edad y si la plataforma llega a una encimera sin trepar por fuera."),
                                criterion("Estabilidad", "Base, patas anticaída y barandilla cuando están publicadas."),
                                criterion("Regulación y plegado", "Tres alturas, conversión a mesa o plegado solo si figura."),
                                criterion("Materiales", "Madera o contrachapado y EN-71 cuando figuran."),
                                criterion("Seguridad práctica", "Un adulto presente. Ninguna torre es un juguete de trepa.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide la encimera", List.of(
                                "La plataforma debe dejar los brazos sobre la superficie, sin asomarse fuera de la barandilla.",
                                "A los 3 años suele usarse una altura intermedia, no la más alta «para crecer» desde el primer día."
                        )),
                        section("Plegado o uso fijo", List.of(
                                "Si la cocina es pequeña, COSTWAY o YOLEO cambian el día a día.",
                                "Pregunta si ya hay torre antes de regalar otra."
                        )),
                        section("Uso a los 3 años", List.of(
                                "Suelo nivelado, un solo niño, adulto al lado. Lejos del fuego y del agua hirviendo.",
                                "Revisa tornillos tras el montaje. No la uses como escalera de adulto y de niño a la vez."
                        ))
                )),
                List.of(
                        faqItem("¿Es segura una torre a los 3 años?",
                                "Puede serlo si es estable, tiene barandilla y hay un adulto. No lo es como juguete de trepa ni junto a peligros de cocina."),
                        faqItem("¿Por qué COSTWAY va primero?",
                                "Porque la ficha la recomienda a partir de 3 años y se pliega. El orden cubre roles, no una nota numérica."),
                        faqItem("¿El niño puede usarla solo?",
                                "No. Aunque suba y baje, el riesgo está en la encimera."),
                        faqItem("¿hauck Learn N Explore se pliega?",
                                "No. La ficha declara tres alturas de plataforma, no plegado."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Vajilla infantil irrompible para 3 años",
                                "/comparativas/" + TABLEWARE_3_SLUG + "/",
                                "La otra pieza de autonomía en la rutina diaria."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildTablewareThreePage() {
        return ageComparison(
                TABLEWARE_3_SLUG,
                TABLEWARE_3_CANONICAL,
                AGE_3,
                TABLEWARE_3,
                "Mejores vajillas infantiles irrompibles para 3 años | Bebes Felices",
                "Comparamos cinco opciones reales de vajilla y vasos para 3 años por piezas, estabilidad y cuidado, con metodología y afiliación transparentes.",
                "Vajilla infantil para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores vajillas infantiles irrompibles para 3 años",
                        "Cinco productos reales para practicar comer y beber sin ayuda",
                        List.of(
                                "A los 3 años tiene sentido un plato, cuenco y vaso que el niño pueda llevar a la mesa. Lo irrompible evita el drama de cada caída y permite repetir el gesto.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Revisa el marcado de apto para alimento. Un vaso 360° no sustituye la supervisión con hermanos pequeños."
                        )
                ),
                "Los cinco productos",
                List.of(
                        summary("Para un set de mesa de 3 a 6 años", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para un plato grande de mesa", "vajilla-fun-house", "22 cm, cuenco de 16 cm y vaso de 220 ml."),
                        summary("Para no mezclar la comida", "vajilla-twistshake-dividido", "Tres compartimentos, tapa y base antideslizante."),
                        summary("Para beber sin tetina", "vaso-munchkin-miracle-360", "Dos vasos 360° de 207 ml con asas."),
                        summary("Para guardar lo que sobra", "cuenco-twistshake-tapa", "Cuenco con tapa, sin ser un set.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos piezas, estabilidad y cuidado para un niño de 3 años. No inventamos volúmenes ni materiales que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 3 años", "Priorizamos sets de mesa 3-6 o 3-8 años frente a solo tronas de bebé."),
                                criterion("Estabilidad", "Base antideslizante, asas o vaso bajo cuando están publicados."),
                                criterion("Piezas incluidas", "Set, plato, vaso o cuenco: no son intercambiables."),
                                criterion("Cuidado", "Microondas y lavavajillas solo si la ficha lo indica."),
                                criterion("Material y alimento", "Libre de BPA u otros avisos cuando figuran.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que falta en casa", List.of(
                                "Si no hay vajilla infantil, un set de 3 piezas (Stor o Fun House) cubre el día a día.",
                                "Si tira el plato, un modelo con base antideslizante aporta más que un personaje."
                        )),
                        section("Vaso 360° o vaso abierto", List.of(
                                "El 360° reduce derrames. El vaso de 260 ml del set Stor es abierto de verdad, más cerca de la mesa familiar.",
                                "A los 3 años conviene practicar el vaso abierto con supervisión."
                        )),
                        section("Limpieza", List.of(
                                "Confirma microondas y cubeta del lavavajillas. Las válvulas 360° hay que desmontarlas.",
                                "Retira piezas con recubrimiento deteriorado."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue haciendo falta vajilla infantil a los 3 años?",
                                "Sí, si el cristal de adulto se rompe o el vaso es inestable. Si ya come con la vajilla familiar sin incidentes, no es obligatorio."),
                        faqItem("¿Plástico, bambú o acero?",
                                "Cualquiera vale si es apto para alimento, estable y no se hace añicos. Revisa el marcado del fabricante."),
                        faqItem("¿Por qué hay dos Twistshake?",
                                "Cubren roles distintos: plato con compartimentos y cuenco con tapa. No son un set combinado en un solo ASIN."),
                        faqItem("¿Cuántas piezas hacen falta?",
                                "Plato o cuenco, vaso y cubiertos infantiles suelen bastar. Un set enorme acaba en el armario."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 3 años",
                                "/comparativas/" + TOWERS_3_SLUG + "/",
                                "Participar en la cocina con una plataforma estable."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildGiftsThreePage() {
        return ageComparison(
                GIFTS_3_SLUG,
                GIFTS_3_CANONICAL,
                AGE_3,
                GIFTS_3,
                "Mejores ideas de regalo para niños de 3 años | Bebes Felices",
                "Comparamos cinco regalos reales para 3 años por necesidad —aprender, moverse, autonomía o crear—, con metodología y afiliación transparentes.",
                "Ideas de regalo para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores ideas de regalo para niños de 3 años",
                        "Cinco regalos reales según lo que el niño ya hace",
                        List.of(
                                "Un buen regalo a los 3 años se usa durante semanas, no solo el día del cumpleaños. Elegimos un producto por necesidad: encajar, completar, moverse, participar en la cocina o crear.",
                                "La selección reúne cinco productos del catálogo, disponibles en Amazon.es cuando la ficha lo permite. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Si ya tiene un cubo de formas, no dupliques: pasa a movimiento, autonomía o un kit de crear. La torre no se regala sin preguntar si hay espacio y un adulto dispuesto a usarla."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para aprender encajando", "juego-montessori-formas", "Cubo de 12 piezas grandes."),
                        summary("Para terminar una escena", "puzle-madera-animales", "Puzle de madera de safari."),
                        summary("Para moverse", "bici-chicco-red-bullet", "Bicicleta sin pedales ligera y ajustable."),
                        summary("Para la cocina", "torre-yoleo-transformer", "Torre plegable convertible en mesa."),
                        summary("Para crear sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera con un adulto.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la necesidad que cubre cada regalo para un niño de 3 años. No es un ranking de «el mejor juguete», sino de roles distintos.",
                        List.of(
                                criterion("Necesidad", "Aprender, moverse, autonomía o crear. No repetimos el mismo gesto cinco veces."),
                                criterion("Uso real", "Si se puede usar el mismo día, con sesiones cortas."),
                                criterion("Regalo", "Si hay que preguntar (torre, talla de bici) o se puede acertar a ciegas (puzle)."),
                                criterion("Duración", "Semanas de uso, no un objeto decorativo."),
                                criterion("Límite", "Casco, supervisión o espacio: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya tiene", List.of(
                                "Si duda, un puzle de piezas grandes o un cubo de formas suele ser más seguro que un juguete «para mayores».",
                                "Si ya hay muchos juguetes de mesa, prioriza movimiento o autonomía."
                        )),
                        section("Pregunta antes de la torre o la bici", List.of(
                                "La torre ocupa sitio y pide un adulto. La bici pide entrepierna, casco y un sitio para usarla.",
                                "El detalle de modelos está en las comparativas de torres y de bicicletas."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. Compara utilidad; el presupuesto lo decides tú al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo no falla a esta edad?",
                                "Uno que pueda usar ya: encajar, moverse con estabilidad o participar en la mesa. Un puzle de piezas grandes suele ser más seguro que un juguete «para mayores»."),
                        faqItem("¿Y si ya tiene muchos juguetes?",
                                "Prioriza autonomía o movimiento, o un kit de crear con un adulto. Evita duplicar lo que ya cubre una necesidad."),
                        faqItem("¿La bicicleta está analizada aquí o en otra página?",
                                "Aquí cuenta como idea de regalo. La comparativa de bicicletas sin pedales detalla talla, peso y ruedas."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y durabilidad."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Regalos sostenibles para 3 años",
                                "/comparativas/" + SUSTAINABLE_3_SLUG + "/",
                                "Materiales declarados y uso que no dependa de pantallas."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildSustainableThreePage() {
        return ageComparison(
                SUSTAINABLE_3_SLUG,
                SUSTAINABLE_3_CANONICAL,
                AGE_3,
                SUSTAINABLE_3,
                "Mejores regalos sostenibles para 3 años | Bebes Felices",
                "Comparamos cinco regalos reales de madera, materiales naturales o plástico reciclado para 3 años, con metodología y afiliación transparentes.",
                "Regalos sostenibles para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos sostenibles para 3 años",
                        "Cinco productos reales pensados para durar, sin pantallas",
                        List.of(
                                "Sostenible, aquí, significa durabilidad y materiales declarados (madera, FSC, plástico reciclado, tintes al agua). No es un sello único ni una huella de carbono que no podamos verificar.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Cuentas, cordones y figuras pequeñas piden supervisión. Un material responsable no elimina piezas pequeñas."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para ensartar y contar", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones."),
                        summary("Para madera FSC de obra", "small-foot-grua", "Grúa giratoria certificada FSC 100 %."),
                        summary("Para plástico reciclado lavable", "green-toys-construccion", "Tres vehículos sin BPA ni PVC."),
                        summary("Para cartón y haya", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas."),
                        summary("Para madera de caucho", "plantoys-ata-zapato", "Tintes al agua para practicar nudos.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos materiales y usos verificables para un niño de 3 años. No asignamos una nota de sostenibilidad ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Materiales", "Madera, FSC, plástico reciclado o tintes cuando están publicados."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Duración", "Si se puede repetir. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Seguridad", "Cordones, cuentas y figuras. El material no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto", List.of(
                                "Ensartar: las cuentas. Obra: Small Foot. Empujar: Green Toys. Encajar: HABA. Atar: PlanToys."
                        )),
                        section("Madera y plástico reciclado no son intercambiables", List.of(
                                "Green Toys declara plástico 100 % reciclado y se lava. Small Foot declara madera FSC 100 %.",
                                "Si la ficha no muestra FSC, no lo afirmamos."
                        )),
                        section("Supervisión", List.of(
                                "Cordones y cuentas no se dejan con menores de 3 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Todo lo de madera es sostenible?",
                                "No. La madera ayuda a la durabilidad, pero no basta. Mira si se usará de verdad y si se puede cuidar."),
                        faqItem("¿Madera siempre es mejor que plástico?",
                                "No en abstracto. El plástico reciclado lavable cubre arena y agua; la madera cubre otro tipo de juego."),
                        faqItem("¿Qué evitáis?",
                                "Productos de un solo uso, piezas diminutas y juguetes que solo funcionan con pantalla. Tampoco afirmamos ecoetiquetas no contrastadas."),
                        faqItem("¿El kit de manualidades tiene certificación?",
                                "Está en la comparativa de arte y manualidades con materiales naturales. Aquí entran objetos de madera, FSC o plástico reciclado que se guardan y se vuelven a sacar."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/comparativas/" + GIFTS_3_SLUG + "/",
                                "Selección por necesidad, no solo por material."
                        ),
                        new LinkItem(
                                "Regalos duraderos para 3 años",
                                "/comparativas/" + DURABLE_3_SLUG + "/",
                                "Objetos pensados para aguantar el juego intenso y seguir usándose."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildDurableThreePage() {
        return ageComparison(
                DURABLE_3_SLUG,
                DURABLE_3_CANONICAL,
                AGE_3,
                DURABLE_3,
                "Mejores regalos duraderos para 3 años | Bebes Felices",
                "Comparamos cinco regalos reales para 3 años pensados para aguantar el juego intenso y seguir usándose, con metodología y afiliación transparentes.",
                "Regalos duraderos para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos duraderos para 3 años",
                        "Cinco productos reales pensados para repetirse, no para un solo uso",
                        List.of(
                                "Duradero, aquí, significa un objeto que se saca muchas veces: puzles de madera, tableros con piezas sujetas o una cocina de madera para juego simbólico. No es un sello de «irrompible» ni una garantía de años que no podamos verificar.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Los puzles de muchas piezas y los accesorios sueltos piden supervisión. Que un juguete aguante no elimina piezas pequeñas ni el acompañamiento de un adulto."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para terminar y volver a guardar", "puzle-madera-animales", "Puzle de madera de piezas grandes."),
                        summary("Para encajar con tablero", "puzle-melissa-mascotas", "Ocho mascotas de madera sobre tablero."),
                        summary("Para rotar cuatro escenas", "puzle-educa-selva", "Puzles progresivos de 5 a 8 piezas."),
                        summary("Para subir a 16 piezas", "puzle-educa-disney-madera", "Dos puzles de madera desde 3 años."),
                        summary("Para juego simbólico largo", "simbolico-theo-klein-miele", "Cocina de madera de 3 a 8 años.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el objeto se puede repetir a los 3 años. No asignamos una nota de durabilidad ni prometemos una vida útil en años.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Duración", "Si se guarda y se saca. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Resistencia", "Material que aguante caídas, agua ocasional o el suelo, según la ficha."),
                                criterion("Seguridad", "Cordones, cuentas y figuras. Un objeto duradero no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto que se va a repetir", List.of(
                                "Terminar una escena: puzle de safari o Disney. Encajar con tablero: mascotas Melissa & Doug. Rotar dificultad: Educa selva. Juego simbólico largo: cocina Theo Klein.",
                                "Si ya tiene un cubo de formas o una grúa de madera, prioriza un puzle o un escenario distinto."
                        )),
                        section("Duradero no es lo mismo que sostenible", List.of(
                                "Aquí miramos si se va a usar más de una temporada. La comparativa de regalos sostenibles detalla materiales declarados (FSC, plástico reciclado, tintes).",
                                "Un kit de plastilina puede ser de materias primas naturales y, aun así, gastarse. Por eso está en arte y manualidades, no aquí."
                        )),
                        section("Supervisión", List.of(
                                "Piezas sueltas y accesorios de cocina no se dejan con menores de 3 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un regalo duradero a los 3 años?",
                                "Uno que se saca muchas veces: encajar, montar una escena o jugar a la cocina. No publicamos una vida útil en años ni un sello de irrompible."),
                        faqItem("¿Por qué no está la grúa de madera?",
                                "Está en la comparativa de regalos sostenibles. Aquí priorizamos puzles y un escenario de madera que no se repiten en esa lista."),
                        faqItem("¿Madera siempre dura más que cartón?",
                                "No en abstracto. Un puzle de cartón grueso puede repetirse si se guardan las piezas; la cocina de madera cubre otro tipo de juego."),
                        faqItem("¿La cocina Theo Klein sustituye una torre de aprendizaje?",
                                "No. Es juego simbólico, no participación real en la cocina. Para autonomía en la encimera, mira la comparativa de torres."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 3 años",
                                "/comparativas/" + SUSTAINABLE_3_SLUG + "/",
                                "Materiales declarados: madera, FSC o plástico reciclado."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildArtsNaturalThreePage() {
        return ageComparison(
                ARTS_NATURAL_3_SLUG,
                ARTS_NATURAL_3_CANONICAL,
                AGE_3,
                ARTS_NATURAL_3,
                "Arte y manualidades con materiales naturales para 3 años | Bebes Felices",
                "Comparamos cinco opciones reales para crear a los 3 años: plastilina ecológica, pintura de dedos natural, plastilina vegetal y un maletín para colorear, con metodología y afiliación transparentes.",
                "Arte y manualidades para 3 años",
                new ComparisonPageResponse.Header(
                        "Arte y manualidades con materiales naturales para 3 años",
                        "Cinco formas de crear en mesa, con un adulto y sin pantallas",
                        List.of(
                                "A los 3 años crear es modelar, pintar con los dedos o colorear en sesiones cortas. Priorizamos plastilina ecológica, pintura de dedos con ingredientes naturales declarados y un maletín que se recoge entero. No es un taller sin adulto ni un set de un solo uso de pegatinas.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Mantel, babero y supervisión. La plastilina y la pintura manchan; el maletín se guarda entero."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para modelar con rodillo", "arte-ses-eco-mega-7", "Siete colores ecológicos y rodillo de madera."),
                        summary("Para pintar con los dedos", "arte-jovi-pintura-dedos-6", "Seis botes con ingredientes naturales."),
                        summary("Para un extra de brillo", "arte-crayola-effects", "Diez témperas con efectos, si ya pinta."),
                        summary("Para muchos colores vegetales", "arte-jovi-plastilina-vegetal-12", "Doce pastillas de base vegetal."),
                        summary("Para colorear y guardar", "arte-crayola-paw-patrol", "Maletín con ceras y hojas.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el gesto de crear a los 3 años y si el material se puede cuidar. No asignamos una nota artística ni un sello eco que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si cabe en una sesión corta con un adulto."),
                                criterion("Materiales", "Naturales, lavables o fáciles de recoger cuando constan en ficha."),
                                criterion("Uso", "Modelar, pintar o colorear: no mezclamos tres gestos en uno."),
                                criterion("Cuidado", "Si se lava, se cierra o se guarda en maletín."),
                                criterion("Límite", "Mancha, piezas sueltas y la plastilina que se gasta.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por el gesto", List.of(
                                "Modelar con herramientas: SES Eco. Pintar con el dedo: Jovi. Modelar muchos colores: plastilina vegetal. Colorear: el maletín. Efectos, si ya aguanta pintar."
                        )),
                        section("Natural no es lo mismo que lavable", List.of(
                                "SES Eco y Jovi declaran materiales ecológicos o de base vegetal. Las témperas Crayola de efectos declaran lavabilidad, no un sello de material natural."
                        )),
                        section("Supervisión", List.of(
                                "Nada de esto se deja solo. Cierra botes y guarda la plastilina al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un kit «eco»?",
                                "No. El mega set SES Eco es una opción del catálogo con plastilina ecológica y rodillo de madera. Pintar con los dedos o modelar con pastillas vegetales también crea, con otro gesto."),
                        faqItem("¿Se puede usar sin adulto?",
                                "No al principio. A los 3 años el adulto dosifica pintura, sujeta el papel y evita que se coma el material."),
                        faqItem("¿Por qué no está el maletín de 100 piezas?",
                                "Esa ficha parte de 4 años. Aquí solo entran productos con edad mínima 3."),
                        faqItem("¿La plastilina dura como la madera?",
                                "No. Se gasta o se guarda en botes. Si buscas un objeto permanente, ve a regalos duraderos o a Montessori de madera."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 3 años",
                                "/comparativas/" + SUSTAINABLE_3_SLUG + "/",
                                "Objetos de madera y materiales declarados que se guardan y se vuelven a sacar."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildMontessoriWoodThreePage() {
        return ageComparison(
                MONTESSORI_WOOD_3_SLUG,
                MONTESSORI_WOOD_3_CANONICAL,
                AGE_3,
                MONTESSORI_WOOD_3,
                "Juegos Montessori de madera para 3 años | Bebes Felices",
                "Comparamos cinco juguetes de madera para 3 años: cubo de formas, láminas, maletín médico, puzle de granja y cuento cooperativo, con metodología y afiliación transparentes.",
                "Montessori de madera para 3 años",
                new ComparisonPageResponse.Header(
                        "Juegos Montessori de madera para 3 años",
                        "Cinco objetos de madera para encajar, imitar o recoger",
                        List.of(
                                "Montessori, aquí, es actividad clara con madera: encajar, imitar la consulta o jugar el cuento en equipo. No es un certificado oficial ni repetir la grúa, las cuentas o el zapato de otra comparativa.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Los accesorios del maletín y las piezas del juego de mesa piden supervisión. La madera no elimina piezas pequeñas."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para empezar con piezas grandes", "juego-montessori-formas", "Cubo de 12 piezas de madera."),
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas de madera de 2 a 5 años."),
                        summary("Para imitar la consulta", "simbolico-sundaymot-33", "Maletín médico de madera con 33 piezas."),
                        summary("Para ocho piezas con pomos", "puzle-melissa-granja-peg", "Tablero de granja de 2 a 4 años."),
                        summary("Para jugar el cuento en equipo", "lectura-three-pigs", "Los 3 Cerditos de Goula, de 3 a 7 años.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos madera y gesto para un niño de 3 años. No es la comparativa de regalos sostenibles: aquí no entra la grúa ni las cuentas.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Madera", "Madera, caucho o figuras declaradas en la ficha."),
                                criterion("Actividad", "Encajar, imitar o recoger: no repetimos el mismo gesto cinco veces."),
                                criterion("Duración", "Si el objeto se saca más de una tarde."),
                                criterion("Seguridad", "Figuras pequeñas y utensilios sueltos.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto, no la etiqueta Montessori", List.of(
                                "Encajar: cubo de 12, Goula o puzle de granja. Imitar: Sundaymot. Cooperar: Los 3 Cerditos."
                        )),
                        section("Si ya tienes la grúa, las cuentas o el zapato", List.of(
                                "Están en regalos sostenibles. Aquí priorizamos otros usos de la madera."
                        )),
                        section("Supervisión", List.of(
                                "Limita los accesorios del maletín al empezar. Las piezas del juego de mesa piden recoger al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta el sello Montessori?",
                                "No. Cuenta la actividad y la madera, no el nombre comercial."),
                        faqItem("¿Dónde está el Janod Tropik?",
                                "En la comparativa de formas y encajes, pensada para el tope de 3 años. Aquí priorizamos el cubo de 12 piezas."),
                        faqItem("¿Dónde están la grúa, las cuentas y el zapato PlanToys?",
                                "En la comparativa de regalos sostenibles. Aquí no los repetimos."),
                        faqItem("¿Todo lo de madera es igual?",
                                "No. Cubo, láminas, maletín médico, puzle de granja y cuento cooperativo no son intercambiables. Elige según el gesto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores juegos Montessori de formas y encajes para 3 años",
                                "/comparativas/" + MONTESSORI_3_SLUG + "/",
                                "Si el gesto que buscas es encajar piezas grandes."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildSymbolicThreePage() {
        return ageComparison(
                SYMBOLIC_3_SLUG,
                SYMBOLIC_3_CANONICAL,
                AGE_3,
                SYMBOLIC_3,
                "Juego simbólico para 3 años | Bebes Felices",
                "Comparamos cinco juguetes de imitación para 3 años: cocinas de madera, una cocinita compacta y un maletín de médico, con metodología y afiliación transparentes.",
                "Juego simbólico para 3 años",
                new ComparisonPageResponse.Header(
                        "Juego simbólico para 3 años",
                        "Cocina o consulta: cinco sets reales para imitar la vida diaria",
                        List.of(
                                "A los 3 años el juego simbólico es repetir lo que ve: cocinar, abrir la nevera o «auscultar». Elegimos cocinas de distinto tamaño y un maletín de médico. No es disfraz de escenario ni una app.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Mide el hueco. Una cocina de pared no cabe en un pasillo. Los accesorios sueltos se recogen al terminar."
                        )
                ),
                "Los cinco sets",
                List.of(
                        summary("Para una cocina de madera completa", "simbolico-theo-klein-miele", "Placa, horno y fregadero."),
                        summary("Para nevera y más zonas", "simbolico-kidkraft-vintage", "Nevera, horno, microondas y teléfono."),
                        summary("Para poco espacio", "simbolico-small-foot-compacta", "Cocinita de madera que se guarda."),
                        summary("Para una cocina compacta de pie", "simbolico-janod-macaron", "Horno, fregadero y cinco accesorios."),
                        summary("Para imitar la consulta", "simbolico-janod-veterinario", "Maletín de veterinario con 16 accesorios.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el escenario de imitación a los 3 años: tamaño, rol y si se puede guardar. No es un ranking de «la mejor cocina».",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y gestos cortos de abrir, verter o cuidar."),
                                criterion("Escenario", "Cocina de pie, compacta o maletín de médico."),
                                criterion("Espacio", "Pared, mesa o caja que se guarda."),
                                criterion("Accesorios", "Si se pueden limitar en cada sesión."),
                                criterion("Seguridad", "Estabilidad, piezas sueltas y que no imite fuego real.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide antes la cocina grande", List.of(
                                "Theo Klein y KidKraft piden un rincón. Small Foot se monta y se guarda. El maletín cabe en un armario."
                        )),
                        section("Cocina o médico", List.of(
                                "No hace falta ambos el mismo año. Elige el rol que el niño ya imita."
                        )),
                        section("Accesorios", List.of(
                                "Los accesorios sueltos no se vuelcan de golpe. Saca cuatro y guarda el resto."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta una cocina de madera grande?",
                                "No. Si no hay pared, la cocinita compacta cubre el mismo gesto."),
                        faqItem("¿El maletín médico es para 3 años?",
                                "La ficha parte de 3 años. Los accesorios piden supervisión y no se llevan a la boca."),
                        faqItem("¿Sustituye ayudar en la cocina de verdad?",
                                "No. Para la encimera real mira las torres de aprendizaje, con adulto."),
                        faqItem("¿Incluís disfraces?",
                                "No en esta lista. Aquí el objeto es cocina o maletín, no un disfraz suelto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 3 años",
                                "/comparativas/" + TOWERS_3_SLUG + "/",
                                "Si lo que busca es participar en la cocina de verdad."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildSensoryThreePage() {
        return ageComparison(
                SENSORY_3_SLUG,
                SENSORY_3_CANONICAL,
                AGE_3,
                SENSORY_3,
                "Juguetes sensoriales para 3 años | Bebes Felices",
                "Comparamos cinco juguetes sensoriales para 3 años: botellas, espuma, tubos, palas y pinzas, con metodología y afiliación transparentes.",
                "Juguetes sensoriales para 3 años",
                new ComparisonPageResponse.Header(
                        "Juguetes sensoriales para 3 años",
                        "Cinco acciones concretas: mirar, apretar, verter o pinzar",
                        List.of(
                                "Sensorial, aquí, es una acción identificable sin luces agresivas ni pantallas: inclinar una botella sellada, modelar espuma, verter con palas o pinzar. No es un pack de «estimulación» genérico.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Bandeja, supervisión y retirar cualquier envase dañado. El contenido de tubos y botellas no se abre."
                        )
                ),
                "Los cinco juguetes",
                List.of(
                        summary("Para mirar e inclinar", "sensorial-emotion-bottles", "Cuatro botellas selladas."),
                        summary("Para apretar", "sensorial-playfoam", "Seis bloques de espuma que no se secan."),
                        summary("Para seguir el movimiento", "sensorial-fidget-tubes", "Tres tubos sellados."),
                        summary("Para verter", "sensorial-scoops", "Cuatro palas para transferir."),
                        summary("Para el agarre", "sensorial-pinzas-jumbo", "Pinzas grandes para recoger.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la acción sensorial y si el material se puede usar a los 3 años sin abrirlo o sin piezas diminutas. No es una terapia ni un diagnóstico.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y un gesto que se entiende en un minuto."),
                                criterion("Acción", "Mirar, apretar, verter o pinzar: una por producto."),
                                criterion("Sellado", "Botellas y tubos enteros; palas y pinzas con material elegido por el adulto."),
                                criterion("Recogida", "Bandeja, maletín o recuento."),
                                criterion("Supervisión", "Nada a la boca; envases dañados fuera.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige la acción", List.of(
                                "Mirar: botellas o tubos. Manos: Playfoam. Transferir: palas. Agarre: pinzas."
                        )),
                        section("No abras lo sellado", List.of(
                                "Botellas y tubos se retiran si fallan. Las palas piden arroz o agua de mesa, con supervisión."
                        )),
                        section("No es un sustituto de evaluación", List.of(
                                "Si hay una necesidad sensorial concreta, consulta a un profesional. Esta página compara juguetes del catálogo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un juguete sensorial a los 3 años?",
                                "Uno con una acción clara: ver, apretar, verter o pinzar. No una caja de luces o sonidos al azar."),
                        faqItem("¿Las botellas de emociones enseñan a gestionarlas?",
                                "No solas. Un adulto nombra lo que se ve. El objeto es inclinar y mirar."),
                        faqItem("¿Playfoam se puede comer?",
                                "No. Fuera del alcance de quien se lleve cosas a la boca."),
                        faqItem("¿Por qué no está el tablero de 100 números?",
                                "Es más contar y presionar burbujas. Aquí priorizamos mirar, modelar y transferir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Arte y manualidades para 3 años",
                                "/comparativas/" + ARTS_NATURAL_3_SLUG + "/",
                                "Si el gesto es pintar o modelar plastilina, no palas o tubos."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildBalanceGuideThreePage() {
        return ageComparison(
                BALANCE_GUIDE_3_SLUG,
                BALANCE_GUIDE_3_CANONICAL,
                AGE_3,
                BALANCE_BIKES,
                "Bicicletas sin pedales para 3 años | Bebes Felices",
                "Guía comparada de cinco bicicletas sin pedales para ganar equilibrio a los 3 años, por peso, sillín y ruedas, con metodología y afiliación transparentes.",
                "Bicicletas sin pedales para 3 años",
                new ComparisonPageResponse.Header(
                        "Bicicletas sin pedales para ganar equilibrio a los 3 años",
                        "Cinco modelos reales para pies en el suelo antes de los pedales",
                        List.of(
                                "A los 3 años la bici sin pedales sirve para empujar, frenar con los pies y coger equilibrio. No sustituye el casco ni un espacio sin tráfico. El ranking de talla y ruedas está también en la comparativa de iniciación; aquí el criterio es si el niño puede recuperar el equilibrio.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Mide la entrepierna. El sillín tiene que permitir apoyar ambos pies. Sin eso, el modelo no encaja, aunque la caja diga 3 años."
                        )
                ),
                "Las cinco bicicletas",
                List.of(
                        summary("Para empezar sencillo", "bici-chicco-red-bullet", "2,7 kg, ruedas antipinchazos y ajustes."),
                        summary("Para el cuadro más ligero", "bici-kinderkraft-tove", "2 kg y sillín bajo."),
                        summary("Para un sillín que crece", "bici-kinderkraft-fly-plus-2", "34 a 42 cm y ruedas de 30 cm."),
                        summary("Para suelo irregular", "bici-kinderkraft-goswift", "Ruedas inflables de 30 cm."),
                        summary("Para postura y reposapiés", "bici-puky-lr-m", "Sillín y manillar ajustables.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede manejar el peso y apoyar los pies para equilibrarse. No es una carrera ni un paso automático a pedales.",
                        List.of(
                                criterion("Equilibrio", "Pies al suelo y peso que pueda levantar al caerse."),
                                criterion("Ajuste", "Recorrido de sillín y manillar."),
                                criterion("Ruedas", "Antipinchazos, espuma o inflables según el terreno."),
                                criterion("Encaje a los 3 años", "Edad o talla declarada y entrepierna real."),
                                criterion("Seguridad", "Casco, calzado cerrado y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Primero la entrepierna", List.of(
                                "Si los pies no llegan, da igual el modelo. Mide y contrasta el rango de sillín."
                        )),
                        section("Ligera para recuperar el equilibrio", List.of(
                                "TOVE declara 2 kg. Chicco, 2,7 kg. Un cuadro que el niño no puede levantar se queda tirado."
                        )),
                        section("Casco siempre", List.of(
                                "La bici sin pedales no elimina caídas. El detalle de cada ficha está también en la comparativa de iniciación."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue teniendo sentido a los 3 años?",
                                "Sí, si el equilibrio aún no está listo para pedales. El salto se ve en el control, no en el cumpleaños."),
                        faqItem("¿En qué se diferencia de la otra comparativa de bicis?",
                                "Aquí el criterio es recuperar el equilibrio y los pies al suelo. La otra detalla talla, ruedas y primer uso con las mismas cinco fichas."),
                        faqItem("¿Hace falta casco?",
                                "Sí. También supervisión y un espacio sin tráfico."),
                        faqItem("¿Patinete o bici primero?",
                                "La bici trabaja el equilibrio sentado; el patinete, de pie. Pueden convivir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/" + BALANCE_BIKES_SLUG + "/",
                                "Comparativa por talla, peso y ruedas para iniciarse."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildScootersTrikesThreePage() {
        return ageComparison(
                SCOOTERS_TRIKES_3_SLUG,
                SCOOTERS_TRIKES_3_CANONICAL,
                AGE_3,
                SCOOTERS_3,
                "Patinetes y triciclos para 3 años | Bebes Felices",
                "Comparamos cuatro patinetes de tres ruedas y un triciclo con mango para moverse de pie o sentado a los 3 años, con metodología y afiliación transparentes.",
                "Patinetes y triciclos para 3 años",
                new ComparisonPageResponse.Header(
                        "Patinetes y triciclos para 3 años",
                        "De pie con tres ruedas o sentado con mango de adulto",
                        List.of(
                                "A los 3 años el movimiento al aire libre puede ser de pie (patinete) o sentado (triciclo). No son el mismo gesto. Casco, calzado cerrado y un espacio sin tráfico valen para los cinco.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "El triciclo no enseña el equilibrio del patinete. El patinete no lleva a un niño cansado en un paseo largo."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para ir de pie ligero", "patinete-micro-mini-deluxe", "1,95 kg y tres ruedas LED."),
                        summary("Para una primera de 3 años", "patinete-molto-maxi", "Edad mínima 3 años y manillar 57-67 cm."),
                        summary("Para plegar y llevar", "patinete-globber-junior-foldable", "Plegado y tres alturas de manillar."),
                        summary("Para empezar sentado y pasar a pie", "patinete-micro-mini-3en1", "Asiento extraíble y manillar 48-68 cm."),
                        summary("Para ir sentado con mango", "triciclo-chicco-u-go", "Triciclo 2en1 hasta 20 kg.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño va de pie o sentado, y si un adulto puede guiar. No mezclamos este gesto con la bici sin pedales.",
                        List.of(
                                criterion("Postura", "De pie en tres ruedas o sentado en triciclo."),
                                criterion("Encaje a los 3 años", "Edad declarada y altura de manillar o mango."),
                                criterion("Manejo", "Peso, plegado o mango de adulto."),
                                criterion("Estabilidad", "Tres puntos de apoyo."),
                                criterion("Seguridad", "Casco, freno o cinturón según el modelo, y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("De pie o sentado", List.of(
                                "Si ya se sostiene y quiere empujar, patinete. Si aún quieres guiar en el paseo, triciclo con mango. El Mini 3en1 cubre el paso de sentado a pie sin pedales."
                        )),
                        section("Peso y plegado", List.of(
                                "Micro declara 1,95 kg. Globber declara plegado. MOLTO no declara peso en la ficha consultada. El Mini 3en1 añade asiento de 20 kg."
                        )),
                        section("Carga del triciclo", List.of(
                                "U-GO declara 20 kg. Comprueba el peso real del niño."
                        ))
                )),
                List.of(
                        faqItem("¿Patinete o triciclo?",
                                "De pie frente a sentado. A los 3 años ambos pueden servir; no hace falta los dos."),
                        faqItem("¿Y la bicicleta sin pedales?",
                                "Otro gesto: equilibrio sentado sin pedales. Está en su comparativa."),
                        faqItem("¿Hace falta casco?",
                                "Sí, también en el triciclo si circula rápido o en pendiente suave."),
                        faqItem("¿Cuándo pasar a dos ruedas?",
                                "Cuando el equilibrio lateral es estable. Muchas familias lo retrasan más allá de los 3 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores patinetes de 3 ruedas para 3 años",
                                "/comparativas/" + SCOOTERS_3_SLUG + "/",
                                "Misma selección, con el foco en tres ruedas e iniciación."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildPiklerThreePage() {
        return ageComparison(
                PIKLER_3_SLUG,
                PIKLER_3_CANONICAL,
                AGE_3,
                PIKLER_3,
                "Triángulos Pikler y estructuras de trepar para 3 años | Bebes Felices",
                "Comparamos cinco opciones para trepar o deslizarse a los 3 años: triángulos de madera, un set combinable y estructuras de plástico, con metodología y afiliación transparentes.",
                "Triángulos Pikler y estructuras de trepar para 3 años",
                new ComparisonPageResponse.Header(
                        "Triángulos Pikler y estructuras de trepar para 3 años",
                        "Cinco estructuras reales para subir, bajar o deslizarse sin ruedas",
                        List.of(
                                "A los 3 años el movimiento no es solo patinete o bici sin pedales. Trepar un marco, gatear un túnel o bajar un tobogán bajo es otro gesto: pies y manos, suelo estable, un adulto al lado. No es un parque infantil ni un columpio.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 2 de septiembre de 2026.",
                                "Mide el hueco. Un triángulo plegado no cabe en cualquier pasillo. Nada de saltar desde lo alto ni dejar al niño solo."
                        )
                ),
                "Las cinco estructuras",
                List.of(
                        summary("Para un triángulo compacto con rampa", "trepar-mamoi-triangulo-blanco", "20 a 57 cm y rampa reversible."),
                        summary("Para madera natural desde 3 años", "trepar-mamoi-triangulo-natural", "60 kg y edad mínima 36 meses."),
                        summary("Para trepar, gatear y deslizarse", "trepar-little-tikes-gimnasio", "Plástico de interior o jardín."),
                        summary("Para un tobogán bajo", "trepar-little-tikes-tobogan", "110 cm de recorrido y 5 kg."),
                        summary("Para triángulo, arco y rampa", "trepar-costway-7en1", "Tres piezas combinables de haya.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede trepar o deslizarse a los 3 años en interior, con supervisión. No es un ranking de «el mejor Pikler».",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si el niño ya sube y baja con control."),
                                criterion("Gesto", "Trepar barras, deslizarse o un circuito corto."),
                                criterion("Espacio", "Hueco en el suelo y si se guarda."),
                                criterion("Material", "Madera o plástico, y carga declarada."),
                                criterion("Seguridad", "Suelo nivelado, un niño a la vez en el tobogán, adulto presente.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Madera o plástico", List.of(
                                "El triángulo Pikler pide un rincón y montaje. Little Tikes cubre trepar y deslizarse en un volumen de jardín o salón, sin barras de madera."
                        )),
                        section("No es bici ni patinete", List.of(
                                "Aquí no hay ruedas. Si buscas empujar sentado, mira correpasillos. Si buscas equilibrio de dos ruedas, la comparativa de bicicletas sin pedales."
                        )),
                        section("Supervisión", List.of(
                                "El fabricante no sustituye a un adulto. Nada de colocarlo junto a una ventana, una escalera o un borde."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un triángulo Pikler a los 3 años?",
                                "Un marco de barras para subir y bajar a su ritmo. A esta edad ya no es el primer gateo: pide control y un adulto cerca."),
                        faqItem("¿Hace falta casco?",
                                "En interior, en una estructura baja, el casco no es el criterio habitual del patinete. Suelo libre de muebles con esquinas y supervisión sí lo son."),
                        faqItem("¿Se puede usar fuera?",
                                "Little Tikes lo declara interior o jardín. Los triángulos de madera de esta lista se guardan secos."),
                        faqItem("¿Sustituye al patinete?",
                                "No. Trepar no enseña a frenar de pie ni el equilibrio de la bici sin pedales."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/" + BALANCE_BIKES_SLUG + "/",
                                "Si el gesto que buscas es equilibrio sentado con ruedas."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildRideOnThreePage() {
        return ageComparison(
                RIDE_ON_3_SLUG,
                RIDE_ON_3_CANONICAL,
                AGE_3,
                RIDE_ON_3,
                "Correpasillos para 3 años | Bebes Felices",
                "Comparamos cinco correpasillos para 3 años: motos de empuje y un coche con antivuelco, con metodología y afiliación transparentes.",
                "Correpasillos para 3 años",
                new ComparisonPageResponse.Header(
                        "Correpasillos para 3 años",
                        "Cinco vehículos para empujar sentado, sin pedales ni patinete",
                        List.of(
                                "Un correpasillos se empuja sentado, con los pies en el suelo. No es una bicicleta sin pedales (dos ruedas, manillar de bici) ni un patinete (de pie). A los 3 años sirve si el asiento aún deja las piernas sueltas.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 2 de septiembre de 2026.",
                                "Mide la entrepierna hasta el asiento. Si las rodillas van encogidas, el modelo se ha quedado pequeño aunque la caja diga 3 años."
                        )
                ),
                "Los cinco correpasillos",
                List.of(
                        summary("Para seguir a los 3 y 4 años", "corre-injusa-africa-twin", "Moto de 2 a 4 años con ruedas anchas."),
                        summary("Para una moto baja hasta 3 años", "corre-injusa-neox-kawasaki", "Asiento 37 cm y carga 30 kg."),
                        summary("Para una moto hasta los 3 años", "corre-feber-motofeber-casual", "18 meses a 3 años, interior o patio."),
                        summary("Para suelos variados", "corre-molto-cross-race", "Moto de empuje a partir de 18 meses."),
                        summary("Para un coche con antivuelco", "corre-smoby-coche", "Formato compacto y hueco bajo el asiento.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede empujar sentado a los 3 años. No mezclamos este gesto con bici sin pedales ni patinete.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad o talla declarada y si las piernas empujan sueltas."),
                                criterion("Gesto", "Moto o coche de empuje; pies al suelo."),
                                criterion("Estabilidad", "Ruedas anchas o antivuelco."),
                                criterion("Uso", "Interior liso o patio sin tráfico."),
                                criterion("Límite", "No es eléctrico, no es bici, no es patinete.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Moto o coche", List.of(
                                "La moto deja las piernas a los lados. El Smoby es un coche bajo con antivuelco. No hace falta ambos el mismo año."
                        )),
                        section("Si ya tiene bici sin pedales", List.of(
                                "No dupliques el mismo gesto de empujar. El correpasillos no enseña equilibrio de dos ruedas."
                        )),
                        section("Tope de edad", List.of(
                                "Neox y Motofeber Casual declaran hasta 3 años. África Twin llega a 4. Comprueba asiento y peso."
                        ))
                )),
                List.of(
                        faqItem("¿En qué se diferencia de la bici sin pedales?",
                                "La bici tiene dos ruedas y manillar de bicicleta. El correpasillos es un asiento ancho sobre tres o cuatro ruedas, sin pedales."),
                        faqItem("¿Y del patinete?",
                                "El patinete se usa de pie. Aquí se va sentado."),
                        faqItem("¿Hace falta casco?",
                                "En interior liso, el criterio habitual es supervisión y no bajar bordillos. En pendiente o junto a otros vehículos, casco."),
                        faqItem("¿Valen los eléctricos?",
                                "No en esta lista. Un coche de batería es otro producto y otra supervisión."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Patinetes y triciclos para 3 años",
                                "/comparativas/" + SCOOTERS_TRIKES_3_SLUG + "/",
                                "Si el gesto es ir de pie o sentado con mango de adulto."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildCutleryThreePage() {
        return ageComparison(
                CUTLERY_3_SLUG,
                CUTLERY_3_CANONICAL,
                AGE_3,
                CUTLERY_3,
                "Cubiertos infantiles para 3 años | Bebes Felices",
                "Comparamos cinco sets reales de tenedor, cuchillo y cuchara para 3 años, distintos de la vajilla, con metodología y afiliación transparentes.",
                "Cubiertos infantiles para 3 años",
                new ComparisonPageResponse.Header(
                        "Cubiertos infantiles para 3 años",
                        "Cinco sets para pinchar y recoger sin cubiertos de adulto",
                        List.of(
                                "A los 3 años la vajilla irrompible cubre plato y vaso. Los cubiertos son otra pieza: mango corto, filo de aprendizaje y un gesto que se repite en cada comida. No son cubiertos de adulto recortados.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 2 de septiembre de 2026.",
                                "Un set de tres suele bastar. El recambio de seis piezas sirve si come en dos casas o en la guardería."
                        )
                ),
                "Los cinco sets",
                List.of(
                        summary("Para tres piezas de acero corto", "cubiertos-twistshake-acero", "Tenedor, cuchillo y cuchara desde 12 meses."),
                        summary("Para asas que no resbalan", "cubiertos-mam-aprendizaje", "Zurdo o diestro, filo de aprendizaje."),
                        summary("Para acero de mesa con cucharilla", "cubiertos-wmf-animales", "Cuatro piezas Cromargan y lavavajillas."),
                        summary("Para recambio en el cajón", "cubiertos-exzact-safari", "Seis piezas desde 24 meses."),
                        summary("Para mango corto a los 3 años", "cubiertos-lehoo-vehiculos", "Seis piezas de acero 304.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede pinchar y recoger a los 3 años con un mango de su tamaño. No es un ranking de «los mejores cubiertos» ni sustituye la comparativa de vajilla.",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y si el mango de adulto aún se le va."),
                                criterion("Piezas", "Tres o seis; con o sin cucharilla."),
                                criterion("Agarre", "Goma, asa curva o acero liso."),
                                criterion("Cuidado", "Lavavajillas solo si la ficha lo indica."),
                                criterion("Seguridad", "Filo de aprendizaje y supervisión en la mesa.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Cubiertos no son vajilla", List.of(
                                "Plato, cuenco y vaso están en la comparativa de vajilla infantil irrompible. Aquí solo entra lo que se lleva a la boca para pinchar o recoger."
                        )),
                        section("Tres piezas o recambio", List.of(
                                "Twistshake y MAM cubren una comida. EXZACT y Lehoo duplican tenedor, cuchillo y cuchara si hay dos sitios."
                        )),
                        section("Acero o mango de goma", List.of(
                                "El mango de goma perdona el resbalón. El acero de mesa (WMF) se parece más a lo que usa el resto de la familia."
                        ))
                )),
                List.of(
                        faqItem("¿A los 3 años siguen haciendo falta cubiertos infantiles?",
                                "Sí, si el tenedor de adulto se le va o el cuchillo de mesa es largo. Si ya come con los de la casa sin incidentes, no es obligatorio."),
                        faqItem("¿Cuchillo infantil corta de verdad?",
                                "Corta blando. No sustituye que un adulto corte carne fibrosa. El filo de aprendizaje no es un cuchillo de cocina."),
                        faqItem("¿Se lavan en el lavavajillas?",
                                "Solo si la ficha lo declara. WMF sí. Lehoo recomienda mano si quieres conservar el dibujo del mango."),
                        faqItem("¿Dónde está el plato y el vaso?",
                                "En la comparativa de vajillas infantiles irrompibles para 3 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores vajillas infantiles para 3 años",
                                "/comparativas/" + TABLEWARE_3_SLUG + "/",
                                "Plato, cuenco y vaso irrompibles, sin cubiertos."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildDressingThreePage() {
        return ageComparison(
                DRESSING_3_SLUG,
                DRESSING_3_CANONICAL,
                AGE_3,
                DRESSING_3,
                "Aprender a vestirse a los 3 años | Bebes Felices",
                "Comparamos cinco materiales reales para botones, cremalleras, cordones y pestillos a los 3 años, con metodología y afiliación transparentes.",
                "Aprender a vestirse a los 3 años",
                new ComparisonPageResponse.Header(
                        "Aprender a vestirse a los 3 años",
                        "Cinco materiales para abrochar, enhebrar o abrir pestillos sin la prisa del abrigo",
                        List.of(
                                "A los 3 años vestirse pide pinza, dos manos y tiempo. Un tablero o un cubo aísla el gesto: botón, cremallera, cordón o pestillo. No sustituye el abrigo real; lo prepara. La torre y la vajilla cubren cocina y mesa; esto cubre la ropa y los cierres de casa.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 2 de septiembre de 2026.",
                                "Un cierre cada vez. Los cordones e imanes se cuentan al guardar. Nada de dejar solo con un cordón al cuello."
                        )
                ),
                "Los cinco materiales",
                List.of(
                        summary("Para botones y cremallera en un oso", "vestir-melissa-habilidades", "Seis prendas que se abrochan a partir de 3 años."),
                        summary("Para enhebrar en la mesa", "vestir-melissa-cordones", "Cinco paneles y cordones, sin el zapato puesto."),
                        summary("Para un cubo que se lleva", "vestir-small-foot-cubo", "Cremallera, botones y velcro en 16 cm."),
                        summary("Para nombrar prendas", "vestir-melissa-disfraces", "Imanes; no abrocha el abrigo real."),
                        summary("Para pestillos de armario", "vestir-melissa-pestillos", "Abrir y cerrar, no ponerse la chaqueta.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede practicar un cierre de vestir o de casa a los 3 años, sentado, con un adulto cerca. No es un ranking de «el mejor marco Montessori».",
                        List.of(
                                criterion("Encaje a los 3 años", "Edad declarada y un solo gesto por sesión."),
                                criterion("Gesto", "Abrochar, enhebrar, colocar prendas o abrir pestillos."),
                                criterion("Material", "Madera, tela o imanes, y si se guarda entero."),
                                criterion("Paso a la prenda real", "Si después hay que repetir el gesto en el abrigo o el zapato."),
                                criterion("Seguridad", "Cordones, imanes y pestillos que pillan; recuento al guardar.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Tablero o cubo", List.of(
                                "El oso aísla botones y cremallera. El cubo de tela se lleva y mezcla seis cierres. Elige según si hay mesa fija o necesitas algo compacto."
                        )),
                        section("No es la torre ni la vajilla", List.of(
                                "La torre llega a la encimera. La vajilla cubre plato y vaso. Vestirse es otro circuito: ropa y cierres, con tiempo y sin prisa de salir a la calle."
                        )),
                        section("Imanes y cordones", List.of(
                                "Los disfraces magnéticos no practican el botón real. Los cordones no se dejan al cuello. Recuento al guardar."
                        ))
                )),
                List.of(
                        faqItem("¿Sustituye aprender en el abrigo real?",
                                "No. El tablero aísla el gesto. Después hay que repetirlo en la prenda, con más tiempo del que parece."),
                        faqItem("¿Hace falta un marco Montessori de tela?",
                                "No. Lo útil es un cierre claro y piezas que no se traguen. El nombre comercial no sustituye la práctica en la ropa de cada día."),
                        faqItem("¿Y el zapato PlanToys?",
                                "Practica nudos en un zapato de madera. Está en regalos sostenibles. Aquí el gesto se reparte entre paneles, cubo y tablero."),
                        faqItem("¿Se puede dejar solo?",
                                "No. Cordones, imanes y pestillos piden un adulto cerca, sobre todo si hay hermanos pequeños."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 3 años",
                                "/comparativas/" + TOWERS_3_SLUG + "/",
                                "Si el gesto que buscas es llegar a la encimera, no abrochar."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildTowersKitchenThreePage() {
        return ageComparison(
                TOWERS_KITCHEN_3_SLUG,
                TOWERS_KITCHEN_3_CANONICAL,
                AGE_3,
                TOWERS_3,
                "Torres para la cocina a los 3 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje para la rutina de cocina a los 3 años: estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres para la cocina a los 3 años",
                new ComparisonPageResponse.Header(
                        "Torres para la cocina a los 3 años",
                        "Cinco torres reales para llegar a la encimera con un adulto",
                        List.of(
                                "A los 3 años la torre sirve para lavar, mezclar u observar en la cocina, no para trepar. La supervisión es constante. Lejos del fuego, del agua hirviendo y de los cuchillos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Mide la encimera y el hueco. Una torre plegable no cabe en cualquier pasillo aunque se pliegue."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar en cocina pequeña", "torre-costway-plegable", "Plegable y recomendada desde 3 años."),
                        summary("Para convertir en mesa", "torre-yoleo-transformer", "Nogal, plegado y escritorio."),
                        summary("Para tres alturas ligeras", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para patas anticaída", "torre-bey-co", "Tres alturas y EN-71."),
                        summary("Para más peso y escritorio", "torre-maxi-cosi-toucan", "8,7 kg, convertible.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la torre sirve en la cocina diaria a los 3 años: llegar a la encimera, no volcar y poder guardar. El detalle de estabilidad también está en la comparativa de torres de aprendizaje.",
                        List.of(
                                criterion("Encimera", "Altura de plataforma y si los brazos quedan sobre la mesa de trabajo."),
                                criterion("Estabilidad", "Barandilla, patas, peso y montaje."),
                                criterion("Guardar", "Plegado o conversión a mesa."),
                                criterion("Encaje a los 3 años", "Ficha y que un adulto esté siempre."),
                                criterion("Peligros de cocina", "Fuego, agua hirviendo, cuchillos: la torre no los elimina.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("La torre no es un taburete", List.of(
                                "Barandilla y adulto. Un taburete de cocina no sustituye este uso."
                        )),
                        section("Plegar o dejar fija", List.of(
                                "COSTWAY y YOLEO se pliegan. BEY & CO no declara plegado."
                        )),
                        section("Un solo niño", List.of(
                                "No es un juguete de trepa ni para dos a la vez junto a la vitro."
                        ))
                )),
                List.of(
                        faqItem("¿Es segura en la cocina a los 3 años?",
                                "Puede serlo con barandilla, suelo nivelado y un adulto. No lo es junto al fuego o como juego."),
                        faqItem("¿Hace falta preguntar antes de regalarla?",
                                "Sí. Ocupa sitio y pide un adulto dispuesto a usarla cada día."),
                        faqItem("¿Madera o metal?",
                                "Lo decisivo es la estabilidad, no el material."),
                        faqItem("¿Dónde está el análisis más largo de estabilidad?",
                                "En la comparativa de torres de aprendizaje para 3 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 3 años",
                                "/comparativas/" + TOWERS_3_SLUG + "/",
                                "Estabilidad, altura y plegado, con el foco en la ficha técnica."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildTablewareDailyThreePage() {
        return ageComparison(
                TABLEWARE_DAILY_3_SLUG,
                TABLEWARE_DAILY_3_CANONICAL,
                AGE_3,
                TABLEWARE_3,
                "Vajilla para la mesa diaria a los 3 años | Bebes Felices",
                "Comparamos cinco opciones irrompibles para que un niño de 3 años coma y beba en la mesa de cada día, con metodología y afiliación transparentes.",
                "Vajilla para la mesa diaria a los 3 años",
                new ComparisonPageResponse.Header(
                        "Vajilla para la mesa diaria a los 3 años",
                        "Plato, vaso o set para repetir el gesto en cada comida",
                        List.of(
                                "A los 3 años la vajilla infantil sirve si se usa todos los días: llevar el plato, beber y sobrevivir a las caídas. No hace falta un set de personaje enorme. Hace falta tamaño de mano y material que no se haga añicos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Revisa el marcado de alimento. El vaso abierto se supervisa al principio."
                        )
                ),
                "Las cinco piezas",
                List.of(
                        summary("Para un set de tres", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para otro set reutilizable", "vajilla-fun-house", "Plato 22 cm, cuenco y vaso 220 ml."),
                        summary("Para plato con tapa", "vajilla-twistshake-dividido", "Compartimentos y base antideslizante."),
                        summary("Para beber sin goteo", "vaso-munchkin-miracle-360", "Dos vasos 360° con asas."),
                        summary("Para guardar o llevar", "cuenco-twistshake-tapa", "Cuenco con tapa desde 6 meses.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la pieza entra en la comida diaria a los 3 años: se puede llevar, lavar y no se rompe. El detalle irrompible también está en la comparativa de vajillas infantiles.",
                        List.of(
                                criterion("Uso diario", "Si se saca en cada comida, no solo en fiestas."),
                                criterion("Mano y peso", "Que el niño pueda llevarlo a la mesa."),
                                criterion("Estabilidad", "Base ancha o antideslizante."),
                                criterion("Cuidado", "Lavado y microondas solo cuando la ficha lo dice."),
                                criterion("Seguridad", "Material de alimento y supervisión del vaso.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Un set o solo el vaso", List.of(
                                "Si ya hay plato, un vaso 360° puede bastar. Si empiezas de cero, un set de tres cubre más."
                        )),
                        section("Personaje o liso", List.of(
                                "El diseño se cansa. Prioriza base estable y BPA free cuando consta."
                        )),
                        section("No hace falta un cajón entero", List.of(
                                "Plato o cuenco, vaso y cubiertos infantiles suelen llegar. Un set enorme acaba en el armario."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas hacen falta para el día a día?",
                                "Plato o cuenco, vaso y cubiertos. El resto es recambio."),
                        faqItem("¿Vaso abierto o 360°?",
                                "El 360° ayuda al principio. A los 3 años también se practica el vaso abierto, con supervisión."),
                        faqItem("¿Plástico, bambú o acero?",
                                "Cualquiera si es apto para alimento y no se hace añicos. Revisa el marcado."),
                        faqItem("¿Dónde comparáis lo irrompible con más detalle?",
                                "En la comparativa de vajillas infantiles para 3 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Mejores vajillas infantiles para 3 años",
                                "/comparativas/" + TABLEWARE_3_SLUG + "/",
                                "Sets, platos y vasos irrompibles, con el foco en la ficha."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildGiftSelectionThreePage() {
        return ageComparison(
                GIFT_SELECTION_3_SLUG,
                GIFT_SELECTION_3_CANONICAL,
                AGE_3,
                GIFT_SELECTION_3,
                "Selección de regalos para 3 años | Bebes Felices",
                "Cinco regalos reales para 3 años por ocasión: un puzle que se termina, aire libre, mesa diaria, cuatro escenas o ensartar, con metodología y afiliación transparentes.",
                "Selección de regalos para 3 años",
                new ComparisonPageResponse.Header(
                        "Selección de regalos para niños de 3 años",
                        "Cinco aciertos por ocasión, sin repetir la comparativa de ideas por necesidad",
                        List.of(
                                "Si no vives en esa casa, un puzle que se termina o un set de mesa suele fallar menos que una torre o una bici. Aquí no repetimos cubo, bici, torre y kit: esa lista está en ideas de regalo por necesidad.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Pregunta antes patinete o vajilla si puede haber duplicado. El casco no se improvisa el día del cumpleaños."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para acertar sin conocer la casa", "puzle-madera-animales", "Puzle de madera que se termina."),
                        summary("Para el patio", "patinete-micro-mini-deluxe", "Tres ruedas ligeras; pide casco."),
                        summary("Para cada comida", "vajilla-stor-mickey", "Set de tres piezas irrompibles."),
                        summary("Para varias sesiones", "haba-puzles-cuatro-estaciones", "Cuatro puzles y figuras."),
                        summary("Para un formato compacto", "cuentas-melissa-doug", "Cuentas de madera; supervisión.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos regalos por ocasión y riesgo de duplicar. No es un ranking de «el mejor regalo» ni de presupuesto.",
                        List.of(
                                criterion("Ocasión", "Visita a ciegas, aire libre, rutina, más sesiones o formato pequeño."),
                                criterion("Uso real", "Si se usa el mismo día o en la comida siguiente."),
                                criterion("Preguntar", "Patinete y vajilla: si ya hay uno, cambia."),
                                criterion("Supervisión", "Cuentas, figuras y casco."),
                                criterion("Límite", "No sustituye preguntar por torre o talla de bici.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Si no conoces los juguetes de la casa", List.of(
                                "Puzle de piezas grandes o HABA de cuatro escenas. Evita torre y bici sin preguntar."
                        )),
                        section("Si el niño está mucho en la mesa", List.of(
                                "La vajilla se usa cada día. Pregunta si ya tienen set."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. El presupuesto lo decides al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo falla menos si no conoces la casa?",
                                "Un puzle de piezas grandes o un set de mesa. La torre y la bici piden pregunta."),
                        faqItem("¿Por qué no están el cubo, la bici y la torre?",
                                "Están en ideas de regalo por necesidad. Aquí cubrimos otra ocasión: visita, patio, mesa, más puzles o formato pequeño."),
                        faqItem("¿Las cuentas son un buen regalo de visita?",
                                "Sí si hay un adulto. No si se van a dejar en el suelo con un hermano pequeño."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y ocasión."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/comparativas/" + GIFTS_3_SLUG + "/",
                                "Una opción por necesidad: aprender, moverse, autonomía o crear."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse buildChooseGiftThreePage() {
        return ageComparison(
                CHOOSE_GIFT_3_SLUG,
                CHOOSE_GIFT_3_CANONICAL,
                AGE_3,
                CHOOSE_GIFT_3,
                "Cómo elegir el regalo según la edad a los 3 años | Bebes Felices",
                "Cinco productos reales según lo que el niño de 3 años ya hace: encajar, moverse de pie, comer, crear o jugar a la obra, con metodología y afiliación transparentes.",
                "Cómo elegir el regalo a los 3 años",
                new ComparisonPageResponse.Header(
                        "Cómo elegir el regalo según la edad a los 3 años",
                        "Cinco productos según lo que ya hace, no según la caja",
                        List.of(
                                "A los 3 años elige por el gesto que ya sostiene: encajar, empujar de pie, llevar el plato, modelar con un adulto o cargar en un juego de obra. La edad de la caja es seguridad, no garantía de acierto.",
                                "La selección reúne cinco productos del catálogo, uno por criterio. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Si ya cubre ese gesto, no lo dupliques: pasa a otra fila. La guía larga de criterios sigue en cómo elegir juguetes por edad."
                        )
                ),
                "Los cinco criterios",
                List.of(
                        summary("Si ya encaja formas", "juego-montessori-formas", "Cubo de 12 piezas grandes."),
                        summary("Si ya se sostiene de pie", "patinete-micro-mini-deluxe", "Tres ruedas; pide casco y sitio."),
                        summary("Si ya quiere la mesa", "vajilla-stor-mickey", "Set irrompible de tres piezas."),
                        summary("Si hay un adulto para crear", "kit-manualidades-natural", "Plastilina ecológica y madera."),
                        summary("Si ya juega a la obra", "small-foot-grua", "Grúa de madera FSC que se repite.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el criterio de elección a los 3 años: qué hace ya el niño. Cada producto es un ejemplo, no «el único regalo correcto».",
                        List.of(
                                criterion("Qué ya hace", "Encajar, ir de pie, comer, crear u obra."),
                                criterion("Por qué a los 3", "Sesiones cortas y piezas o talla compatibles."),
                                criterion("Regalo", "Si se puede acertar sin conocer toda la casa."),
                                criterion("No elijas esto si", "Ese gesto ya está cubierto o falta otra necesidad."),
                                criterion("Límite", "Casco, supervisión o adulto para crear: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "No por lo que «tendría que» hacer. Un juguete para mayores frustra más que uno que se termina."
                        )),
                        section("Un gesto por regalo", List.of(
                                "No hace falta cubo, patinete y grúa el mismo día. Elige la fila que falte."
                        )),
                        section("La guía y esta comparativa", List.of(
                                "La guía de cómo elegir juguetes por edad explica criterios. Aquí hay cinco productos reales para aplicarlos a los 3 años."
                        ))
                )),
                List.of(
                        faqItem("¿La edad de la caja basta?",
                                "Es imprescindible para seguridad, no basta. Contrástala con lo que el niño ya hace."),
                        faqItem("¿Qué evito a los 3 años?",
                                "Reglas largas, piezas diminutas y movimiento sin casco ni supervisión. También duplicar un cubo o un patinete que ya tiene."),
                        faqItem("¿Y si no sé qué tiene en casa?",
                                "Mira la selección de regalos por ocasión: puzle o vajilla suelen fallar menos que torre o bici."),
                        faqItem("¿Dónde está la guía completa?",
                                "En cómo elegir juguetes según la edad, con variantes para 3, 4 y 5 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub3Link(),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/?edad=3",
                                "Criterios prácticos para 3, 4 y 5 años."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/comparativas/" + GIFTS_3_SLUG + "/",
                                "Otra lista por necesidad: aprender, moverse, autonomía o crear."
                        )
                )
        );
    }

    private ComparisonPageResponse buildDurable4Page() {
        return ageComparison(
                DURABLE_4_SLUG,
                DURABLE_4_CANONICAL,
                AGE_4,
                DURABLE_3,
                "Mejores regalos duraderos para 4 años | Bebes Felices",
                "Comparamos cinco regalos reales para 4 años pensados para aguantar el juego intenso y seguir usándose, con metodología y afiliación transparentes.",
                "Regalos duraderos para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos duraderos para 4 años",
                        "Cinco productos reales pensados para repetirse, no para un solo uso",
                        List.of(
                                "Duradero, aquí, significa un objeto que se saca muchas veces: madera, plástico lavable o cartón grueso, sin pantallas ni kits que se consumen en una tarde. No es un sello de «irrompible» ni una garantía de años que no podamos verificar.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Cuentas, cordones y figuras pequeñas piden supervisión. Que un juguete aguante no elimina piezas pequeñas ni el acompañamiento de un adulto."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para el juego de obra que se repite", "small-foot-grua", "Grúa de madera con manivela y giro 360°."),
                        summary("Para empujar y lavar", "green-toys-construccion", "Tres vehículos lavables, de 24 a 72 meses."),
                        summary("Para un gesto que dura años", "plantoys-ata-zapato", "Madera de caucho para practicar nudos hasta los 8."),
                        summary("Para encajar y volver a sacar", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas y figuras de madera."),
                        summary("Para ensartar muchas veces", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el objeto se puede repetir a los 4 años. No asignamos una nota de durabilidad ni prometemos una vida útil en años.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Duración", "Si se guarda y se saca. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Resistencia", "Material que aguante caídas, agua ocasional o el suelo, según la ficha."),
                                criterion("Seguridad", "Cordones, cuentas y figuras. Un objeto duradero no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto que se va a repetir", List.of(
                                "Obra: Small Foot. Empujar y lavar: Green Toys. Atar: PlanToys. Encajar: HABA. Ensartar: las cuentas.",
                                "Si ya tiene un cubo de formas o un puzle de una sola escena, prioriza un objeto que se use en la rutina o al aire libre."
                        )),
                        section("Duradero no es lo mismo que sostenible", List.of(
                                "Aquí miramos si se va a usar más de una temporada. La comparativa de regalos sostenibles detalla materiales declarados (FSC, plástico reciclado, tintes).",
                                "Un kit de plastilina puede ser de materias primas naturales y, aun así, gastarse. Por eso no está en esta lista."
                        )),
                        section("Supervisión", List.of(
                                "Cordones, cuentas y figuras no se dejan con menores de 4 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un regalo duradero a los 4 años?",
                                "Uno que se saca muchas veces: encajar, empujar, atar o ensartar. No publicamos una vida útil en años ni un sello de irrompible."),
                        faqItem("¿Por qué no está el kit de manualidades?",
                                "La plastilina se gasta. Encaja como actividad para crear sin pantallas, y está en la comparativa de regalos sostenibles, no en esta lista de objetos que se vuelven a usar."),
                        faqItem("¿Madera siempre dura más que plástico?",
                                "No en abstracto. Los vehículos Green Toys se lavan y cubren arena y agua; la grúa de madera cubre otro tipo de juego. Elige según el uso."),
                        faqItem("¿El zapato PlanToys sustituye aprender a atarse?",
                                "No. Es un material de ensayo. Un adulto muestra el nudo; el zapato de verdad sigue haciendo falta."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 4 años",
                                "/comparativas/" + SUSTAINABLE_SLUG + "/",
                                "Materiales declarados: madera, FSC o plástico reciclado."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildDurable5Page() {
        return ageComparison(
                DURABLE_5_SLUG,
                DURABLE_5_CANONICAL,
                AGE_5,
                DURABLE_3,
                "Mejores regalos duraderos para 5 años | Bebes Felices",
                "Comparamos cinco regalos reales para 5 años pensados para aguantar el juego intenso y seguir usándose, con metodología y afiliación transparentes.",
                "Regalos duraderos para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos duraderos para 5 años",
                        "Cinco productos reales pensados para repetirse, no para un solo uso",
                        List.of(
                                "Duradero, aquí, significa un objeto que se saca muchas veces: madera, plástico lavable o cartón grueso, sin pantallas ni kits que se consumen en una tarde. No es un sello de «irrompible» ni una garantía de años que no podamos verificar.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Cuentas, cordones y figuras pequeñas piden supervisión. Que un juguete aguante no elimina piezas pequeñas ni el acompañamiento de un adulto."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para el juego de obra que se repite", "small-foot-grua", "Grúa de madera con manivela y giro 360°."),
                        summary("Para empujar y lavar", "green-toys-construccion", "Tres vehículos lavables, de 24 a 72 meses."),
                        summary("Para un gesto que dura años", "plantoys-ata-zapato", "Madera de caucho para practicar nudos hasta los 8."),
                        summary("Para encajar y volver a sacar", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas y figuras de madera."),
                        summary("Para ensartar muchas veces", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el objeto se puede repetir a los 5 años. No asignamos una nota de durabilidad ni prometemos una vida útil en años.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Duración", "Si se guarda y se saca. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Resistencia", "Material que aguante caídas, agua ocasional o el suelo, según la ficha."),
                                criterion("Seguridad", "Cordones, cuentas y figuras. Un objeto duradero no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto que se va a repetir", List.of(
                                "Obra: Small Foot. Empujar y lavar: Green Toys. Atar: PlanToys. Encajar: HABA. Ensartar: las cuentas.",
                                "Si ya tiene un cubo de formas o un puzle de una sola escena, prioriza un objeto que se use en la rutina o al aire libre."
                        )),
                        section("Duradero no es lo mismo que sostenible", List.of(
                                "Aquí miramos si se va a usar más de una temporada. La comparativa de regalos sostenibles detalla materiales declarados (FSC, plástico reciclado, tintes).",
                                "Un kit de plastilina puede ser de materias primas naturales y, aun así, gastarse. Por eso no está en esta lista."
                        )),
                        section("Supervisión", List.of(
                                "Cordones, cuentas y figuras no se dejan con menores de 5 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un regalo duradero a los 5 años?",
                                "Uno que se saca muchas veces: encajar, empujar, atar o ensartar. No publicamos una vida útil en años ni un sello de irrompible."),
                        faqItem("¿Por qué no está el kit de manualidades?",
                                "La plastilina se gasta. Encaja como actividad para crear sin pantallas, y está en la comparativa de regalos sostenibles, no en esta lista de objetos que se vuelven a usar."),
                        faqItem("¿Madera siempre dura más que plástico?",
                                "No en abstracto. Los vehículos Green Toys se lavan y cubren arena y agua; la grúa de madera cubre otro tipo de juego. Elige según el uso."),
                        faqItem("¿El zapato PlanToys sustituye aprender a atarse?",
                                "No. Es un material de ensayo. Un adulto muestra el nudo; el zapato de verdad sigue haciendo falta."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 5 años",
                                "/comparativas/" + SUSTAINABLE_5_SLUG + "/",
                                "Materiales declarados: madera, FSC o plástico reciclado."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildArtsNatural4Page() {
        return ageComparison(
                ARTS_NATURAL_4_SLUG,
                ARTS_NATURAL_4_CANONICAL,
                AGE_4,
                ARTS_NATURAL_3,
                "Arte y manualidades con materiales naturales para 4 años | Bebes Felices",
                "Comparamos cinco opciones reales para crear a los 4 años: plastilina ecológica, témpera lavable y un maletín para colorear, con metodología y afiliación transparentes.",
                "Arte y manualidades para 4 años",
                new ComparisonPageResponse.Header(
                        "Arte y manualidades con materiales naturales para 4 años",
                        "Cinco formas de crear en mesa, con un adulto y sin pantallas",
                        List.of(
                                "A los 4 años crear es modelar, pintar o colorear en sesiones cortas. Priorizamos el kit de materias primas naturales y pinturas lavables que se pueden recoger. No es un taller sin adulto ni un set de un solo uso de pegatinas.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Mantel, babero y supervisión. La plastilina y la témpera manchan; el maletín se guarda entero."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para modelar sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera."),
                        summary("Para pintar con agua", "arte-crayola-tempera-6", "Seis botes de témpera lavable."),
                        summary("Para un extra de brillo", "arte-crayola-effects", "Diez témperas con efectos, si ya pinta."),
                        summary("Para acabado metal", "arte-crayola-metallic", "Seis colores metálicos lavables."),
                        summary("Para colorear y guardar", "arte-crayola-paw-patrol", "Maletín con ceras y hojas.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el gesto de crear a los 4 años y si el material se puede cuidar. No asignamos una nota artística ni un sello eco que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si cabe en una sesión corta con un adulto."),
                                criterion("Materiales", "Naturales, lavables o fáciles de recoger cuando constan en ficha."),
                                criterion("Uso", "Modelar, pintar o colorear: no mezclamos tres gestos en uno."),
                                criterion("Cuidado", "Si se lava, se cierra o se guarda en maletín."),
                                criterion("Límite", "Mancha, piezas sueltas y la plastilina que se gasta.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por el gesto", List.of(
                                "Modelar: el kit. Pintar: témpera de 6. Colorear: el maletín. Efectos y metal, si ya aguanta pintar."
                        )),
                        section("Natural no es lo mismo que lavable", List.of(
                                "El kit declara materias primas naturales y madera. Las témperas Crayola declaran lavabilidad, no un sello de material natural."
                        )),
                        section("Supervisión", List.of(
                                "Nada de esto se deja solo. Cierra botes y guarda la plastilina al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un kit «eco»?",
                                "No. El kit de plastilina es la opción del catálogo con materiales naturales declarados. Pintar lavable también crea, con otro material."),
                        faqItem("¿Se puede usar sin adulto?",
                                "No al principio. A los 4 años el adulto dosifica pintura, sujeta el papel y evita que se coma el material."),
                        faqItem("¿Por qué no está el maletín de 100 piezas?",
                                "Esa ficha parte de 4 años. Aquí solo entran productos con edad mínima 3."),
                        faqItem("¿La plastilina dura como la madera?",
                                "No. Se gasta. Si buscas un objeto permanente, ve a regalos duraderos o a Montessori de madera."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 4 años",
                                "/comparativas/" + SUSTAINABLE_SLUG + "/",
                                "El kit de crear, en el contexto de materiales declarados."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildArtsNatural5Page() {
        return ageComparison(
                ARTS_NATURAL_5_SLUG,
                ARTS_NATURAL_5_CANONICAL,
                AGE_5,
                ARTS_NATURAL_3,
                "Arte y manualidades con materiales naturales para 5 años | Bebes Felices",
                "Comparamos cinco opciones reales para crear a los 5 años: plastilina ecológica, témpera lavable y un maletín para colorear, con metodología y afiliación transparentes.",
                "Arte y manualidades para 5 años",
                new ComparisonPageResponse.Header(
                        "Arte y manualidades con materiales naturales para 5 años",
                        "Cinco formas de crear en mesa, con un adulto y sin pantallas",
                        List.of(
                                "A los 5 años crear es modelar, pintar o colorear en sesiones cortas. Priorizamos el kit de materias primas naturales y pinturas lavables que se pueden recoger. No es un taller sin adulto ni un set de un solo uso de pegatinas.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Mantel, babero y supervisión. La plastilina y la témpera manchan; el maletín se guarda entero."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para modelar sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera."),
                        summary("Para pintar con agua", "arte-crayola-tempera-6", "Seis botes de témpera lavable."),
                        summary("Para un extra de brillo", "arte-crayola-effects", "Diez témperas con efectos, si ya pinta."),
                        summary("Para acabado metal", "arte-crayola-metallic", "Seis colores metálicos lavables."),
                        summary("Para colorear y guardar", "arte-crayola-paw-patrol", "Maletín con ceras y hojas.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el gesto de crear a los 5 años y si el material se puede cuidar. No asignamos una nota artística ni un sello eco que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si cabe en una sesión corta con un adulto."),
                                criterion("Materiales", "Naturales, lavables o fáciles de recoger cuando constan en ficha."),
                                criterion("Uso", "Modelar, pintar o colorear: no mezclamos tres gestos en uno."),
                                criterion("Cuidado", "Si se lava, se cierra o se guarda en maletín."),
                                criterion("Límite", "Mancha, piezas sueltas y la plastilina que se gasta.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por el gesto", List.of(
                                "Modelar: el kit. Pintar: témpera de 6. Colorear: el maletín. Efectos y metal, si ya aguanta pintar."
                        )),
                        section("Natural no es lo mismo que lavable", List.of(
                                "El kit declara materias primas naturales y madera. Las témperas Crayola declaran lavabilidad, no un sello de material natural."
                        )),
                        section("Supervisión", List.of(
                                "Nada de esto se deja solo. Cierra botes y guarda la plastilina al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un kit «eco»?",
                                "No. El kit de plastilina es la opción del catálogo con materiales naturales declarados. Pintar lavable también crea, con otro material."),
                        faqItem("¿Se puede usar sin adulto?",
                                "No al principio. A los 5 años el adulto dosifica pintura, sujeta el papel y evita que se coma el material."),
                        faqItem("¿Por qué no está el maletín de 100 piezas?",
                                "Esa ficha parte de 4 años. Aquí solo entran productos con edad mínima 3."),
                        faqItem("¿La plastilina dura como la madera?",
                                "No. Se gasta. Si buscas un objeto permanente, ve a regalos duraderos o a Montessori de madera."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores regalos sostenibles para 5 años",
                                "/comparativas/" + SUSTAINABLE_5_SLUG + "/",
                                "El kit de crear, en el contexto de materiales declarados."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildMontessoriWood4Page() {
        return ageComparison(
                MONTESSORI_WOOD_4_SLUG,
                MONTESSORI_WOOD_4_CANONICAL,
                AGE_4,
                MONTESSORI_WOOD_3,
                "Juegos Montessori de madera para 4 años | Bebes Felices",
                "Comparamos cinco juguetes de madera para 4 años: cubo de formas, láminas, maletín médico, puzle de granja y cuento cooperativo, con metodología y afiliación transparentes.",
                "Montessori de madera para 4 años",
                new ComparisonPageResponse.Header(
                        "Juegos Montessori de madera para 4 años",
                        "Cinco objetos de madera para encajar, imitar o recoger",
                        List.of(
                                "Montessori, aquí, es actividad clara con madera: encajar, imitar la consulta o jugar el cuento en equipo. No es un certificado oficial ni repetir la grúa o las cuentas de otra comparativa.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Los accesorios del maletín y las piezas del juego de mesa piden supervisión. La madera no elimina piezas pequeñas."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para empezar con piezas grandes", "juego-montessori-formas", "Cubo de 12 piezas de madera."),
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas de madera de 2 a 5 años."),
                        summary("Para imitar la consulta", "simbolico-sundaymot-33", "Maletín médico de madera con 33 piezas."),
                        summary("Para ocho piezas con pomos", "puzle-melissa-granja-peg", "Tablero de granja de 2 a 4 años."),
                        summary("Para jugar el cuento en equipo", "lectura-three-pigs", "Los 3 Cerditos de Goula, de 3 a 7 años.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos madera y gesto para un niño de 4 años. No es la comparativa de formas y encajes: aquí entra también el maletín médico y el juego cooperativo.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Madera", "Madera, caucho o figuras declaradas en la ficha."),
                                criterion("Actividad", "Encajar, imitar o recoger: no repetimos el mismo cubo."),
                                criterion("Duración", "Si el objeto se saca más de una tarde."),
                                criterion("Seguridad", "Figuras pequeñas y utensilios sueltos.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto, no la etiqueta Montessori", List.of(
                                "Encajar: cubo de 12, Goula o puzle de granja. Imitar: Sundaymot. Cooperar: Los 3 Cerditos."
                        )),
                        section("Si ya tienes el cubo de 12 piezas", List.of(
                                "No lo dupliques en la comparativa de formas y encajes. Aquí priorizamos madera con otro uso."
                        )),
                        section("Supervisión", List.of(
                                "Limita los accesorios del maletín al empezar. Las piezas del juego de mesa piden recoger al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta el sello Montessori?",
                                "No. Cuenta la actividad y la madera, no el nombre comercial."),
                        faqItem("¿Dónde está el Janod Tropik?",
                                "En la comparativa de formas y encajes. Aquí priorizamos el cubo de 12 piezas."),
                        faqItem("¿Dónde está el cubo Melissa & Doug?",
                                "En la comparativa de formas y encajes, que compara ese gesto con más piezas de encaje."),
                        faqItem("¿Todo lo de madera es igual?",
                                "No. Cubo, láminas, maletín médico, puzle de granja y cuento cooperativo no son intercambiables. Elige según el gesto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores juegos Montessori de formas y encajes para 4 años",
                                "/comparativas/" + MONTESSORI_4_SLUG + "/",
                                "Si el gesto que buscas es encajar piezas grandes."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildMontessoriWood5Page() {
        return ageComparison(
                MONTESSORI_WOOD_5_SLUG,
                MONTESSORI_WOOD_5_CANONICAL,
                AGE_5,
                MONTESSORI_WOOD_3,
                "Juegos Montessori de madera para 5 años | Bebes Felices",
                "Comparamos cinco juguetes de madera para 5 años: cubo de formas, láminas, maletín médico, puzle de granja y cuento cooperativo, con metodología y afiliación transparentes.",
                "Montessori de madera para 5 años",
                new ComparisonPageResponse.Header(
                        "Juegos Montessori de madera para 5 años",
                        "Cinco objetos de madera para encajar, imitar o recoger",
                        List.of(
                                "Montessori, aquí, es actividad clara con madera: encajar, imitar la consulta o jugar el cuento en equipo. No es un certificado oficial ni repetir la grúa o las cuentas de otra comparativa.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Los accesorios del maletín y las piezas del juego de mesa piden supervisión. La madera no elimina piezas pequeñas."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para empezar con piezas grandes", "juego-montessori-formas", "Cubo de 12 piezas de madera."),
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas de madera de 2 a 5 años."),
                        summary("Para imitar la consulta", "simbolico-sundaymot-33", "Maletín médico de madera con 33 piezas."),
                        summary("Para ocho piezas con pomos", "puzle-melissa-granja-peg", "Tablero de granja de 2 a 4 años."),
                        summary("Para jugar el cuento en equipo", "lectura-three-pigs", "Los 3 Cerditos de Goula, de 3 a 7 años.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos madera y gesto para un niño de 5 años. No es la comparativa de formas y encajes: aquí entra también el maletín médico y el juego cooperativo.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si el gesto cabe en sesiones cortas."),
                                criterion("Madera", "Madera, caucho o figuras declaradas en la ficha."),
                                criterion("Actividad", "Encajar, imitar o recoger: no repetimos el mismo cubo."),
                                criterion("Duración", "Si el objeto se saca más de una tarde."),
                                criterion("Seguridad", "Figuras pequeñas y utensilios sueltos.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto, no la etiqueta Montessori", List.of(
                                "Encajar: cubo de 12, Goula o puzle de granja. Imitar: Sundaymot. Cooperar: Los 3 Cerditos."
                        )),
                        section("Si ya tienes el cubo de 12 piezas", List.of(
                                "No lo dupliques. Esa ficha está en la comparativa de formas y encajes. Aquí priorizamos madera con otro uso."
                        )),
                        section("Supervisión", List.of(
                                "Limita los accesorios del maletín al empezar. Las piezas del juego de mesa piden recoger al terminar."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta el sello Montessori?",
                                "No. Cuenta la actividad y la madera, no el nombre comercial."),
                        faqItem("¿Dónde está el Janod Tropik?",
                                "En la comparativa de formas y encajes. Aquí priorizamos el cubo de 12 piezas."),
                        faqItem("¿Dónde está el cubo Melissa & Doug?",
                                "En la comparativa de formas y encajes, que compara ese gesto con más piezas de encaje."),
                        faqItem("¿Todo lo de madera es igual?",
                                "No. Cubo, láminas, maletín médico, puzle de granja y cuento cooperativo no son intercambiables. Elige según el gesto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores juegos Montessori de formas y encajes para 5 años",
                                "/comparativas/" + MONTESSORI_5_SLUG + "/",
                                "Si el gesto que buscas es encajar piezas grandes."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildMontessori4Page() {
        return ageComparison(
                MONTESSORI_4_SLUG,
                MONTESSORI_4_CANONICAL,
                AGE_4,
                MONTESSORI_4,
                "Mejores juegos Montessori de formas y encajes para 4 años | Bebes Felices",
                "Comparamos cinco juegos de encaje y clasificación reales para 4 años por piezas, actividad y margen de dificultad, con metodología y afiliación transparentes.",
                "Juegos Montessori para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores juegos Montessori de formas y encajes para 4 años",
                        "Cinco materiales reales para clasificar formas y colores",
                        List.of(
                                "A los 4 años el encaje útil es el que se entiende sin un manual: coger una pieza, nombrar la forma y meterla. El adjetivo «Montessori» no sustituye piezas grandes ni una consigna clara.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Un adulto cercano sigue haciendo falta. Si hay hermanos más pequeños, revisa el tamaño de cada figura antes de dejar el set en el suelo."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para empezar con piezas grandes", "juego-montessori-formas", "Cubo de 12 piezas y una sola consigna."),
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas progresivas de 2 a 5 años."),
                        summary("Para ocho formas gruesas", "montessori-formas-geometricas", "Se termina en una sesión corta."),
                        summary("Para clasificar animales", "montessori-noah-ark", "26 piezas; saca un subconjunto al inicio."),
                        summary("Para practicar nudos", "plantoys-ata-zapato", "Madera de caucho para autonomía.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la actividad de encaje y su utilidad para un niño de 4 años. No asignamos una nota «Montessori» ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si se puede terminar una ronda sin frustración."),
                                criterion("Actividad", "Clasificar y encajar, no un set con diez modos."),
                                criterion("Piezas", "Número y tamaño. 12 piezas no son 26."),
                                criterion("Margen de dificultad", "Si el material se queda corto en semanas o admite un siguiente nivel."),
                                criterion("Seguridad", "Piezas grandes y supervisión. El nombre comercial no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "Si aún encaja con ayuda, el cubo de 12 piezas o Janod bastan. Si ya clasifica sin esfuerzo, Goula o el arca dan más margen.",
                                "No elijas por el sello Montessori. Elige por si el niño puede completar una ronda hoy."
                        )),
                        section("Cuántas piezas sacar", List.of(
                                "Con 26 figuras, saca cuatro o cinco al empezar. El arca guarda el resto.",
                                "Mezclar todas las láminas de Goula a la vez convierte el reto en un desorden."
                        )),
                        section("Supervisión", List.of(
                                "Es un material de mesa o de suelo con un adulto cerca, no un juguete para la habitación a solas.",
                                "Revisa piezas pequeñas si hay menores de 4 años en casa."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un material certificado Montessori?",
                                "No. Lo útil es clasificar y encajar con piezas seguras. El nombre comercial no sustituye el criterio de edad."),
                        faqItem("¿Cuántas piezas son demasiadas a los 4 años?",
                                "Si no puede terminar una ronda, sobran. Empieza por un cubo reducido y añade dificultad después."),
                        faqItem("¿Janod sirve si ya tiene 4 años cumplidos?",
                                "Está en el tope de su rango. Si ya clasifica con soltura, Goula o el cubo de 12 dan más recorrido."),
                        faqItem("¿Se puede usar sin un adulto?",
                                "Con supervisión cercana, sí, cuando las piezas son grandes. No es un juguete para dejar solo."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Puzles de piezas grandes para 4 años",
                                "/comparativas/" + MONTESSORI_4_SLUG + "/",
                                "Otra vía de motricidad fina: completar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildMontessori5Page() {
        return ageComparison(
                MONTESSORI_5_SLUG,
                MONTESSORI_5_CANONICAL,
                AGE_5,
                MONTESSORI_5,
                "Mejores juegos Montessori de formas y encajes para 5 años | Bebes Felices",
                "Comparamos cinco juegos de encaje y clasificación reales para 5 años por piezas, actividad y margen de dificultad, con metodología y afiliación transparentes.",
                "Juegos Montessori para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores juegos Montessori de formas y encajes para 5 años",
                        "Cinco materiales reales para clasificar formas y colores",
                        List.of(
                                "A los 5 años el encaje útil es el que se entiende sin un manual: coger una pieza, nombrar la forma y meterla. El adjetivo «Montessori» no sustituye piezas grandes ni una consigna clara.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Un adulto cercano sigue haciendo falta. Si hay hermanos más pequeños, revisa el tamaño de cada figura antes de dejar el set en el suelo."
                        )
                ),
                "Los cinco juegos",
                List.of(
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas progresivas de 2 a 5 años."),
                        summary("Para ocho formas gruesas", "montessori-formas-geometricas", "Se termina en una sesión corta."),
                        summary("Para clasificar animales", "montessori-noah-ark", "26 piezas; saca un subconjunto al inicio."),
                        summary("Para practicar nudos", "plantoys-ata-zapato", "Madera de caucho para autonomía."),
                        summary("Para ensartar y contar", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la actividad de encaje y su utilidad para un niño de 5 años. No asignamos una nota «Montessori» ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si se puede terminar una ronda sin frustración."),
                                criterion("Actividad", "Clasificar y encajar, no un set con diez modos."),
                                criterion("Piezas", "Número y tamaño. 12 piezas no son 26."),
                                criterion("Margen de dificultad", "Si el material se queda corto en semanas o admite un siguiente nivel."),
                                criterion("Seguridad", "Piezas grandes y supervisión. El nombre comercial no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "Si aún encaja con ayuda, el cubo de 12 piezas o Janod bastan. Si ya clasifica sin esfuerzo, Goula o el arca dan más margen.",
                                "No elijas por el sello Montessori. Elige por si el niño puede completar una ronda hoy."
                        )),
                        section("Cuántas piezas sacar", List.of(
                                "Con 26 figuras, saca cuatro o cinco al empezar. El arca guarda el resto.",
                                "Mezclar todas las láminas de Goula a la vez convierte el reto en un desorden."
                        )),
                        section("Supervisión", List.of(
                                "Es un material de mesa o de suelo con un adulto cerca, no un juguete para la habitación a solas.",
                                "Revisa piezas pequeñas si hay menores de 5 años en casa."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta un material certificado Montessori?",
                                "No. Lo útil es clasificar y encajar con piezas seguras. El nombre comercial no sustituye el criterio de edad."),
                        faqItem("¿Cuántas piezas son demasiadas a los 5 años?",
                                "Si no puede terminar una ronda, sobran. Empieza por un cubo reducido y añade dificultad después."),
                        faqItem("¿Janod sirve si ya tiene 5 años cumplidos?",
                                "Está en el tope de su rango. Si ya clasifica con soltura, Goula o el cubo de 12 dan más recorrido."),
                        faqItem("¿Se puede usar sin un adulto?",
                                "Con supervisión cercana, sí, cuando las piezas son grandes. No es un juguete para dejar solo."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Puzles de piezas grandes para 5 años",
                                "/comparativas/" + MONTESSORI_5_SLUG + "/",
                                "Otra vía de motricidad fina: completar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildPuzzles4Page() {
        return ageComparison(
                PUZZLES_4_SLUG,
                PUZZLES_4_CANONICAL,
                AGE_4,
                PUZZLES_3,
                "Mejores puzles de piezas grandes para 4 años | Bebes Felices",
                "Comparamos cinco puzles reales para 4 años por número de piezas, soporte y si se pueden terminar en una sesión, con metodología y afiliación transparentes.",
                "Puzles para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores puzles de piezas grandes para 4 años",
                        "Cinco puzles reales para encajar sin piezas diminutas",
                        List.of(
                                "A los 4 años un puzle sirve para encajar, nombrar lo que se ve y terminar. No es un puzzle de 100 piezas ni un reto de paciencia adulta.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Empieza junto, nombra las piezas que faltan y guarda cada escena aparte. El éxito repetible importa más que el recuento impreso en la caja."
                        )
                ),
                "Los cinco puzles",
                List.of(
                        summary("Para piezas grandes de safari", "puzle-madera-animales", "Una escena de madera y animales reconocibles."),
                        summary("Para encajar en tablero", "puzle-melissa-mascotas", "Ocho mascotas de madera sobre tablero."),
                        summary("Para empezar por 5 piezas", "puzle-educa-selva", "Cuatro puzles de 5 a 8 piezas."),
                        summary("Para 15 piezas y haya", "haba-puzles-cuatro-estaciones", "Cuatro estaciones y figuras de madera."),
                        summary("Para 16 piezas de madera", "puzle-educa-disney-madera", "Dos escenas cuando 8 piezas ya no retan.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el número de piezas, el soporte y si un niño de 4 años puede terminar. No asignamos notas ni inventamos recuentos que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y si la sesión puede acabarse."),
                                criterion("Dificultad", "De silueta con tablero a 16 piezas sueltas."),
                                criterion("Soporte", "Tablero, marco o piezas sueltas; madera o cartón cuando figura."),
                                criterion("Progresión", "Un pack de 4 puzles no es lo mismo que una sola escena."),
                                criterion("Seguridad", "Piezas y figuras pequeñas fuera del alcance de menores de 4 años.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya monta", List.of(
                                "Si aún encaja siluetas, mascotas o safari. Si termina 8 piezas en un minuto, HABA o Educa Disney.",
                                "Un recuento alto no es un regalo mejor: es un riesgo de dejarlo a medias."
                        )),
                        section("Cartón o madera", List.of(
                                "Ambos valen si son gruesos. La madera suele durar más en un uso intenso.",
                                "Un tablero sujeta las piezas; un puzle suelto de 16 pide más mesa y más paciencia."
                        )),
                        section("Si se rinde a mitad", List.of(
                                "Reduce las piezas a la vista, nombra lo que falta y termina juntos.",
                                "Guarda cada puzle en su bolsa. Mezclar 15 + 16 piezas acaba con la sesión."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas recomendáis a los 4 años?",
                                "Las justas para terminar. Un puzle de silueta o de 5-8 piezas suele encajar mejor que uno de decenas de piezas pequeñas."),
                        faqItem("¿Cartón o madera?",
                                "Ambos si son gruesos. El cartón fino se dobla y frustra."),
                        faqItem("¿Cuándo pasar a 15 o 16 piezas?",
                                "Cuando 8 piezas se resuelven sin esfuerzo. HABA y Educa Disney cubren ese paso, con un adulto al principio."),
                        faqItem("¿El puzle de mascotas es lo mismo que el de safari?",
                                "No. Las mascotas son tablero con siluetas y piezas que se sostienen de pie; el safari es una escena de piezas grandes. Cubren el mismo tramo de edad con gestos distintos."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Juegos Montessori de formas y encajes",
                                "/comparativas/" + PUZZLES_4_SLUG + "/",
                                "Clasificar formas, no montar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildPuzzles5Page() {
        return ageComparison(
                PUZZLES_5_SLUG,
                PUZZLES_5_CANONICAL,
                AGE_5,
                PUZZLES_3,
                "Mejores puzles de piezas grandes para 5 años | Bebes Felices",
                "Comparamos cinco puzles reales para 5 años por número de piezas, soporte y si se pueden terminar en una sesión, con metodología y afiliación transparentes.",
                "Puzles para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores puzles de piezas grandes para 5 años",
                        "Cinco puzles reales para encajar sin piezas diminutas",
                        List.of(
                                "A los 5 años un puzle sirve para encajar, nombrar lo que se ve y terminar. No es un puzzle de 100 piezas ni un reto de paciencia adulta.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Empieza junto, nombra las piezas que faltan y guarda cada escena aparte. El éxito repetible importa más que el recuento impreso en la caja."
                        )
                ),
                "Los cinco puzles",
                List.of(
                        summary("Para piezas grandes de safari", "puzle-madera-animales", "Una escena de madera y animales reconocibles."),
                        summary("Para encajar en tablero", "puzle-melissa-mascotas", "Ocho mascotas de madera sobre tablero."),
                        summary("Para empezar por 5 piezas", "puzle-educa-selva", "Cuatro puzles de 5 a 8 piezas."),
                        summary("Para 15 piezas y haya", "haba-puzles-cuatro-estaciones", "Cuatro estaciones y figuras de madera."),
                        summary("Para 16 piezas de madera", "puzle-educa-disney-madera", "Dos escenas cuando 8 piezas ya no retan.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el número de piezas, el soporte y si un niño de 5 años puede terminar. No asignamos notas ni inventamos recuentos que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si la sesión puede acabarse."),
                                criterion("Dificultad", "De silueta con tablero a 16 piezas sueltas."),
                                criterion("Soporte", "Tablero, marco o piezas sueltas; madera o cartón cuando figura."),
                                criterion("Progresión", "Un pack de 4 puzles no es lo mismo que una sola escena."),
                                criterion("Seguridad", "Piezas y figuras pequeñas fuera del alcance de menores de 5 años.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya monta", List.of(
                                "Si aún encaja siluetas, mascotas o safari. Si termina 8 piezas en un minuto, HABA o Educa Disney.",
                                "Un recuento alto no es un regalo mejor: es un riesgo de dejarlo a medias."
                        )),
                        section("Cartón o madera", List.of(
                                "Ambos valen si son gruesos. La madera suele durar más en un uso intenso.",
                                "Un tablero sujeta las piezas; un puzle suelto de 16 pide más mesa y más paciencia."
                        )),
                        section("Si se rinde a mitad", List.of(
                                "Reduce las piezas a la vista, nombra lo que falta y termina juntos.",
                                "Guarda cada puzle en su bolsa. Mezclar 15 + 16 piezas acaba con la sesión."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas recomendáis a los 5 años?",
                                "Las justas para terminar. Un puzle de silueta o de 5-8 piezas suele encajar mejor que uno de decenas de piezas pequeñas."),
                        faqItem("¿Cartón o madera?",
                                "Ambos si son gruesos. El cartón fino se dobla y frustra."),
                        faqItem("¿Cuándo pasar a 15 o 16 piezas?",
                                "Cuando 8 piezas se resuelven sin esfuerzo. HABA y Educa Disney cubren ese paso, con un adulto al principio."),
                        faqItem("¿El puzle de mascotas es lo mismo que el de safari?",
                                "No. Las mascotas son tablero con siluetas y piezas que se sostienen de pie; el safari es una escena de piezas grandes. Cubren el mismo tramo de edad con gestos distintos."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Juegos Montessori de formas y encajes",
                                "/comparativas/" + PUZZLES_5_SLUG + "/",
                                "Clasificar formas, no montar una imagen."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildSymbolic4Page() {
        return ageComparison(
                SYMBOLIC_4_SLUG,
                SYMBOLIC_4_CANONICAL,
                AGE_4,
                SYMBOLIC_3,
                "Juego simbólico para 4 años | Bebes Felices",
                "Comparamos cinco juguetes de imitación para 4 años: cocinas de madera, una cocinita compacta y un maletín de médico, con metodología y afiliación transparentes.",
                "Juego simbólico para 4 años",
                new ComparisonPageResponse.Header(
                        "Juego simbólico para 4 años",
                        "Cocina o consulta: cinco sets reales para imitar la vida diaria",
                        List.of(
                                "A los 4 años el juego simbólico es repetir lo que ve: cocinar, abrir la nevera o «auscultar». Elegimos cocinas de distinto tamaño y un maletín de médico. No es disfraz de escenario ni una app.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Mide el hueco. Una cocina de pared no cabe en un pasillo. Los accesorios sueltos se recogen al terminar."
                        )
                ),
                "Los cinco sets",
                List.of(
                        summary("Para una cocina de madera completa", "simbolico-theo-klein-miele", "Placa, horno y fregadero."),
                        summary("Para nevera y más zonas", "simbolico-kidkraft-vintage", "Nevera, horno, microondas y teléfono."),
                        summary("Para poco espacio", "simbolico-small-foot-compacta", "Cocinita de madera que se guarda."),
                        summary("Para una cocina compacta de pie", "simbolico-janod-macaron", "Horno, fregadero y cinco accesorios."),
                        summary("Para imitar la consulta", "simbolico-janod-veterinario", "Maletín de veterinario con 16 accesorios.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el escenario de imitación a los 4 años: tamaño, rol y si se puede guardar. No es un ranking de «la mejor cocina».",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y gestos cortos de abrir, verter o cuidar."),
                                criterion("Escenario", "Cocina de pie, compacta o maletín de médico."),
                                criterion("Espacio", "Pared, mesa o caja que se guarda."),
                                criterion("Accesorios", "Si se pueden limitar en cada sesión."),
                                criterion("Seguridad", "Estabilidad, piezas sueltas y que no imite fuego real.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide antes la cocina grande", List.of(
                                "Theo Klein y KidKraft piden un rincón. Small Foot se monta y se guarda. El maletín cabe en un armario."
                        )),
                        section("Cocina o médico", List.of(
                                "No hace falta ambos el mismo año. Elige el rol que el niño ya imita."
                        )),
                        section("Accesorios", List.of(
                                "Los accesorios sueltos no se vuelcan de golpe. Saca cuatro y guarda el resto."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta una cocina de madera grande?",
                                "No. Si no hay pared, la cocinita compacta cubre el mismo gesto."),
                        faqItem("¿El maletín médico es para 4 años?",
                                "La ficha parte de 4 años. Los accesorios piden supervisión y no se llevan a la boca."),
                        faqItem("¿Sustituye ayudar en la cocina de verdad?",
                                "No. Para la encimera real mira las torres de aprendizaje, con adulto."),
                        faqItem("¿Incluís disfraces?",
                                "No en esta lista. Aquí el objeto es cocina o maletín, no un disfraz suelto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 4 años",
                                "/comparativas/" + SYMBOLIC_4_SLUG + "/",
                                "Si lo que busca es participar en la cocina de verdad."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildSymbolic5Page() {
        return ageComparison(
                SYMBOLIC_5_SLUG,
                SYMBOLIC_5_CANONICAL,
                AGE_5,
                SYMBOLIC_3,
                "Juego simbólico para 5 años | Bebes Felices",
                "Comparamos cinco juguetes de imitación para 5 años: cocinas de madera, una cocinita compacta y un maletín de médico, con metodología y afiliación transparentes.",
                "Juego simbólico para 5 años",
                new ComparisonPageResponse.Header(
                        "Juego simbólico para 5 años",
                        "Cocina o consulta: cinco sets reales para imitar la vida diaria",
                        List.of(
                                "A los 5 años el juego simbólico es repetir lo que ve: cocinar, abrir la nevera o «auscultar». Elegimos cocinas de distinto tamaño y un maletín de médico. No es disfraz de escenario ni una app.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Mide el hueco. Una cocina de pared no cabe en un pasillo. Los accesorios sueltos se recogen al terminar."
                        )
                ),
                "Los cinco sets",
                List.of(
                        summary("Para una cocina de madera completa", "simbolico-theo-klein-miele", "Placa, horno y fregadero."),
                        summary("Para nevera y más zonas", "simbolico-kidkraft-vintage", "Nevera, horno, microondas y teléfono."),
                        summary("Para poco espacio", "simbolico-small-foot-compacta", "Cocinita de madera que se guarda."),
                        summary("Para una cocina compacta de pie", "simbolico-janod-macaron", "Horno, fregadero y cinco accesorios."),
                        summary("Para imitar la consulta", "simbolico-janod-veterinario", "Maletín de veterinario con 16 accesorios.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el escenario de imitación a los 5 años: tamaño, rol y si se puede guardar. No es un ranking de «la mejor cocina».",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y gestos cortos de abrir, verter o cuidar."),
                                criterion("Escenario", "Cocina de pie, compacta o maletín de médico."),
                                criterion("Espacio", "Pared, mesa o caja que se guarda."),
                                criterion("Accesorios", "Si se pueden limitar en cada sesión."),
                                criterion("Seguridad", "Estabilidad, piezas sueltas y que no imite fuego real.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide antes la cocina grande", List.of(
                                "Theo Klein y KidKraft piden un rincón. Small Foot se monta y se guarda. El maletín cabe en un armario."
                        )),
                        section("Cocina o médico", List.of(
                                "No hace falta ambos el mismo año. Elige el rol que el niño ya imita."
                        )),
                        section("Accesorios", List.of(
                                "Los accesorios sueltos no se vuelcan de golpe. Saca cuatro y guarda el resto."
                        ))
                )),
                List.of(
                        faqItem("¿Hace falta una cocina de madera grande?",
                                "No. Si no hay pared, la cocinita compacta cubre el mismo gesto."),
                        faqItem("¿El maletín médico es para 5 años?",
                                "La ficha parte de 5 años. Los accesorios piden supervisión y no se llevan a la boca."),
                        faqItem("¿Sustituye ayudar en la cocina de verdad?",
                                "No. Para la encimera real mira las torres de aprendizaje, con adulto."),
                        faqItem("¿Incluís disfraces?",
                                "No en esta lista. Aquí el objeto es cocina o maletín, no un disfraz suelto."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 5 años",
                                "/comparativas/" + SYMBOLIC_5_SLUG + "/",
                                "Si lo que busca es participar en la cocina de verdad."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildSensory4Page() {
        return ageComparison(
                SENSORY_4_SLUG,
                SENSORY_4_CANONICAL,
                AGE_4,
                SENSORY_3,
                "Juguetes sensoriales para 4 años | Bebes Felices",
                "Comparamos cinco juguetes sensoriales para 4 años: botellas, espuma, tubos, palas y pinzas, con metodología y afiliación transparentes.",
                "Juguetes sensoriales para 4 años",
                new ComparisonPageResponse.Header(
                        "Juguetes sensoriales para 4 años",
                        "Cinco acciones concretas: mirar, apretar, verter o pinzar",
                        List.of(
                                "Sensorial, aquí, es una acción identificable sin luces agresivas ni pantallas: inclinar una botella sellada, modelar espuma, verter con palas o pinzar. No es un pack de «estimulación» genérico.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Bandeja, supervisión y retirar cualquier envase dañado. El contenido de tubos y botellas no se abre."
                        )
                ),
                "Los cinco juguetes",
                List.of(
                        summary("Para mirar e inclinar", "sensorial-emotion-bottles", "Cuatro botellas selladas."),
                        summary("Para apretar", "sensorial-playfoam", "Seis bloques de espuma que no se secan."),
                        summary("Para seguir el movimiento", "sensorial-fidget-tubes", "Tres tubos sellados."),
                        summary("Para verter", "sensorial-scoops", "Cuatro palas para transferir."),
                        summary("Para el agarre", "sensorial-pinzas-jumbo", "Pinzas grandes para recoger.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la acción sensorial y si el material se puede usar a los 4 años sin abrirlo o sin piezas diminutas. No es una terapia ni un diagnóstico.",
                        List.of(
                                criterion("Encaje a los 4 años", "Edad declarada y un gesto que se entiende en un minuto."),
                                criterion("Acción", "Mirar, apretar, verter o pinzar: una por producto."),
                                criterion("Sellado", "Botellas y tubos enteros; palas y pinzas con material elegido por el adulto."),
                                criterion("Recogida", "Bandeja, maletín o recuento."),
                                criterion("Supervisión", "Nada a la boca; envases dañados fuera.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige la acción", List.of(
                                "Mirar: botellas o tubos. Manos: Playfoam. Transferir: palas. Agarre: pinzas."
                        )),
                        section("No abras lo sellado", List.of(
                                "Botellas y tubos se retiran si fallan. Las palas piden arroz o agua de mesa, con supervisión."
                        )),
                        section("No es un sustituto de evaluación", List.of(
                                "Si hay una necesidad sensorial concreta, consulta a un profesional. Esta página compara juguetes del catálogo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un juguete sensorial a los 4 años?",
                                "Uno con una acción clara: ver, apretar, verter o pinzar. No una caja de luces o sonidos al azar."),
                        faqItem("¿Las botellas de emociones enseñan a gestionarlas?",
                                "No solas. Un adulto nombra lo que se ve. El objeto es inclinar y mirar."),
                        faqItem("¿Playfoam se puede comer?",
                                "No. Fuera del alcance de quien se lleve cosas a la boca."),
                        faqItem("¿Por qué no está el tablero de 100 números?",
                                "Es más contar y presionar burbujas. Aquí priorizamos mirar, modelar y transferir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Arte y manualidades para 4 años",
                                "/comparativas/" + SENSORY_4_SLUG + "/",
                                "Si el gesto es pintar o modelar plastilina, no palas o tubos."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildSensory5Page() {
        return ageComparison(
                SENSORY_5_SLUG,
                SENSORY_5_CANONICAL,
                AGE_5,
                SENSORY_3,
                "Juguetes sensoriales para 5 años | Bebes Felices",
                "Comparamos cinco juguetes sensoriales para 5 años: botellas, espuma, tubos, palas y pinzas, con metodología y afiliación transparentes.",
                "Juguetes sensoriales para 5 años",
                new ComparisonPageResponse.Header(
                        "Juguetes sensoriales para 5 años",
                        "Cinco acciones concretas: mirar, apretar, verter o pinzar",
                        List.of(
                                "Sensorial, aquí, es una acción identificable sin luces agresivas ni pantallas: inclinar una botella sellada, modelar espuma, verter con palas o pinzar. No es un pack de «estimulación» genérico.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Bandeja, supervisión y retirar cualquier envase dañado. El contenido de tubos y botellas no se abre."
                        )
                ),
                "Los cinco juguetes",
                List.of(
                        summary("Para mirar e inclinar", "sensorial-emotion-bottles", "Cuatro botellas selladas."),
                        summary("Para apretar", "sensorial-playfoam", "Seis bloques de espuma que no se secan."),
                        summary("Para seguir el movimiento", "sensorial-fidget-tubes", "Tres tubos sellados."),
                        summary("Para verter", "sensorial-scoops", "Cuatro palas para transferir."),
                        summary("Para el agarre", "sensorial-pinzas-jumbo", "Pinzas grandes para recoger.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la acción sensorial y si el material se puede usar a los 5 años sin abrirlo o sin piezas diminutas. No es una terapia ni un diagnóstico.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y un gesto que se entiende en un minuto."),
                                criterion("Acción", "Mirar, apretar, verter o pinzar: una por producto."),
                                criterion("Sellado", "Botellas y tubos enteros; palas y pinzas con material elegido por el adulto."),
                                criterion("Recogida", "Bandeja, maletín o recuento."),
                                criterion("Supervisión", "Nada a la boca; envases dañados fuera.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige la acción", List.of(
                                "Mirar: botellas o tubos. Manos: Playfoam. Transferir: palas. Agarre: pinzas."
                        )),
                        section("No abras lo sellado", List.of(
                                "Botellas y tubos se retiran si fallan. Las palas piden arroz o agua de mesa, con supervisión."
                        )),
                        section("No es un sustituto de evaluación", List.of(
                                "Si hay una necesidad sensorial concreta, consulta a un profesional. Esta página compara juguetes del catálogo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un juguete sensorial a los 5 años?",
                                "Uno con una acción clara: ver, apretar, verter o pinzar. No una caja de luces o sonidos al azar."),
                        faqItem("¿Las botellas de emociones enseñan a gestionarlas?",
                                "No solas. Un adulto nombra lo que se ve. El objeto es inclinar y mirar."),
                        faqItem("¿Playfoam se puede comer?",
                                "No. Fuera del alcance de quien se lleve cosas a la boca."),
                        faqItem("¿Por qué no está el tablero de 100 números?",
                                "Es más contar y presionar burbujas. Aquí priorizamos mirar, modelar y transferir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Arte y manualidades para 5 años",
                                "/comparativas/" + SENSORY_5_SLUG + "/",
                                "Si el gesto es pintar o modelar plastilina, no palas o tubos."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildBalanceGuide4Page() {
        return ageComparison(
                BALANCE_GUIDE_4_SLUG,
                BALANCE_GUIDE_4_CANONICAL,
                AGE_4,
                BALANCE_BIKES,
                "Bicicletas sin pedales para 4 años | Bebes Felices",
                "Guía comparada de cinco bicicletas sin pedales para ganar equilibrio a los 4 años, por peso, sillín y ruedas, con metodología y afiliación transparentes.",
                "Bicicletas sin pedales para 4 años",
                new ComparisonPageResponse.Header(
                        "Bicicletas sin pedales para ganar equilibrio a los 4 años",
                        "Cinco modelos reales para pies en el suelo antes de los pedales",
                        List.of(
                                "A los 4 años la bici sin pedales sirve para empujar, frenar con los pies y coger equilibrio. No sustituye el casco ni un espacio sin tráfico. El ranking de talla y ruedas está también en la comparativa de iniciación; aquí el criterio es si el niño puede recuperar el equilibrio.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Mide la entrepierna. El sillín tiene que permitir apoyar ambos pies. Sin eso, el modelo no encaja, aunque la caja diga 4 años."
                        )
                ),
                "Las cinco bicicletas",
                List.of(
                        summary("Para empezar sencillo", "bici-chicco-red-bullet", "2,7 kg, ruedas antipinchazos y ajustes."),
                        summary("Para el cuadro más ligero", "bici-kinderkraft-tove", "2 kg y sillín bajo."),
                        summary("Para un sillín que crece", "bici-kinderkraft-fly-plus-2", "34 a 42 cm y ruedas de 30 cm."),
                        summary("Para suelo irregular", "bici-kinderkraft-goswift", "Ruedas inflables de 30 cm."),
                        summary("Para postura y reposapiés", "bici-puky-lr-m", "Sillín y manillar ajustables.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede manejar el peso y apoyar los pies para equilibrarse. No es una carrera ni un paso automático a pedales.",
                        List.of(
                                criterion("Equilibrio", "Pies al suelo y peso que pueda levantar al caerse."),
                                criterion("Ajuste", "Recorrido de sillín y manillar."),
                                criterion("Ruedas", "Antipinchazos, espuma o inflables según el terreno."),
                                criterion("Encaje a los 4 años", "Edad o talla declarada y entrepierna real."),
                                criterion("Seguridad", "Casco, calzado cerrado y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Primero la entrepierna", List.of(
                                "Si los pies no llegan, da igual el modelo. Mide y contrasta el rango de sillín."
                        )),
                        section("Ligera para recuperar el equilibrio", List.of(
                                "TOVE declara 2 kg. Chicco, 2,7 kg. Un cuadro que el niño no puede levantar se queda tirado."
                        )),
                        section("Casco siempre", List.of(
                                "La bici sin pedales no elimina caídas. El detalle de cada ficha está también en la comparativa de iniciación."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue teniendo sentido a los 4 años?",
                                "Sí, si el equilibrio aún no está listo para pedales. El salto se ve en el control, no en el cumpleaños."),
                        faqItem("¿En qué se diferencia de la otra comparativa de bicis?",
                                "Aquí el criterio es recuperar el equilibrio y los pies al suelo. La otra detalla talla, ruedas y primer uso con las mismas cinco fichas."),
                        faqItem("¿Hace falta casco?",
                                "Sí. También supervisión y un espacio sin tráfico."),
                        faqItem("¿Patinete o bici primero?",
                                "La bici trabaja el equilibrio sentado; el patinete, de pie. Pueden convivir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 4 años",
                                "/comparativas/" + BALANCE_GUIDE_4_SLUG + "/",
                                "Comparativa por talla, peso y ruedas para iniciarse."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildBalanceGuide5Page() {
        return ageComparison(
                BALANCE_GUIDE_5_SLUG,
                BALANCE_GUIDE_5_CANONICAL,
                AGE_5,
                BALANCE_BIKES,
                "Bicicletas sin pedales para 5 años | Bebes Felices",
                "Guía comparada de cinco bicicletas sin pedales para ganar equilibrio a los 5 años, por peso, sillín y ruedas, con metodología y afiliación transparentes.",
                "Bicicletas sin pedales para 5 años",
                new ComparisonPageResponse.Header(
                        "Bicicletas sin pedales para ganar equilibrio a los 5 años",
                        "Cinco modelos reales para pies en el suelo antes de los pedales",
                        List.of(
                                "A los 5 años la bici sin pedales sirve para empujar, frenar con los pies y coger equilibrio. No sustituye el casco ni un espacio sin tráfico. El ranking de talla y ruedas está también en la comparativa de iniciación; aquí el criterio es si el niño puede recuperar el equilibrio.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Mide la entrepierna. El sillín tiene que permitir apoyar ambos pies. Sin eso, el modelo no encaja, aunque la caja diga 5 años."
                        )
                ),
                "Las cinco bicicletas",
                List.of(
                        summary("Para empezar sencillo", "bici-chicco-red-bullet", "2,7 kg, ruedas antipinchazos y ajustes."),
                        summary("Para el cuadro más ligero", "bici-kinderkraft-tove", "2 kg y sillín bajo."),
                        summary("Para un sillín que crece", "bici-kinderkraft-fly-plus-2", "34 a 42 cm y ruedas de 30 cm."),
                        summary("Para suelo irregular", "bici-kinderkraft-goswift", "Ruedas inflables de 30 cm."),
                        summary("Para postura y reposapiés", "bici-puky-lr-m", "Sillín y manillar ajustables.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño puede manejar el peso y apoyar los pies para equilibrarse. No es una carrera ni un paso automático a pedales.",
                        List.of(
                                criterion("Equilibrio", "Pies al suelo y peso que pueda levantar al caerse."),
                                criterion("Ajuste", "Recorrido de sillín y manillar."),
                                criterion("Ruedas", "Antipinchazos, espuma o inflables según el terreno."),
                                criterion("Encaje a los 5 años", "Edad o talla declarada y entrepierna real."),
                                criterion("Seguridad", "Casco, calzado cerrado y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Primero la entrepierna", List.of(
                                "Si los pies no llegan, da igual el modelo. Mide y contrasta el rango de sillín."
                        )),
                        section("Ligera para recuperar el equilibrio", List.of(
                                "TOVE declara 2 kg. Chicco, 2,7 kg. Un cuadro que el niño no puede levantar se queda tirado."
                        )),
                        section("Casco siempre", List.of(
                                "La bici sin pedales no elimina caídas. El detalle de cada ficha está también en la comparativa de iniciación."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue teniendo sentido a los 5 años?",
                                "Sí, si el equilibrio aún no está listo para pedales. El salto se ve en el control, no en el cumpleaños."),
                        faqItem("¿En qué se diferencia de la otra comparativa de bicis?",
                                "Aquí el criterio es recuperar el equilibrio y los pies al suelo. La otra detalla talla, ruedas y primer uso con las mismas cinco fichas."),
                        faqItem("¿Hace falta casco?",
                                "Sí. También supervisión y un espacio sin tráfico."),
                        faqItem("¿Patinete o bici primero?",
                                "La bici trabaja el equilibrio sentado; el patinete, de pie. Pueden convivir."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 5 años",
                                "/comparativas/" + BALANCE_GUIDE_5_SLUG + "/",
                                "Comparativa por talla, peso y ruedas para iniciarse."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildScootersTrikes4Page() {
        return ageComparison(
                SCOOTERS_TRIKES_4_SLUG,
                SCOOTERS_TRIKES_4_CANONICAL,
                AGE_4,
                SCOOTERS_3,
                "Patinetes y triciclos para 4 años | Bebes Felices",
                "Comparamos cuatro patinetes de tres ruedas y un triciclo con mango para moverse de pie o sentado a los 4 años, con metodología y afiliación transparentes.",
                "Patinetes y triciclos para 4 años",
                new ComparisonPageResponse.Header(
                        "Patinetes y triciclos para 4 años",
                        "De pie con tres ruedas o sentado con mango de adulto",
                        List.of(
                                "A los 4 años el movimiento al aire libre puede ser de pie (patinete) o sentado (triciclo). No son el mismo gesto. Casco, calzado cerrado y un espacio sin tráfico valen para los cinco.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "El triciclo no enseña el equilibrio del patinete. El patinete no lleva a un niño cansado en un paseo largo."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para ir de pie ligero", "patinete-micro-mini-deluxe", "1,95 kg y tres ruedas LED."),
                        summary("Para una primera de 4 años", "patinete-molto-maxi", "Edad mínima 4 años y manillar 57-67 cm."),
                        summary("Para plegar y llevar", "patinete-globber-junior-foldable", "Plegado y tres alturas de manillar."),
                        summary("Para empezar sentado y pasar a pie", "patinete-micro-mini-3en1", "Asiento extraíble y manillar 48-68 cm."),
                        summary("Para ir sentado con mango", "triciclo-chicco-u-go", "Triciclo 2en1 hasta 20 kg.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño va de pie o sentado, y si un adulto puede guiar. No mezclamos este gesto con la bici sin pedales.",
                        List.of(
                                criterion("Postura", "De pie en tres ruedas o sentado en triciclo."),
                                criterion("Encaje a los 4 años", "Edad declarada y altura de manillar o mango."),
                                criterion("Manejo", "Peso, plegado o mango de adulto."),
                                criterion("Estabilidad", "Tres puntos de apoyo."),
                                criterion("Seguridad", "Casco, freno o cinturón según el modelo, y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("De pie o sentado", List.of(
                                "Si ya se sostiene y quiere empujar, patinete. Si aún quieres guiar en el paseo, triciclo con mango."
                        )),
                        section("Peso y plegado", List.of(
                                "Micro declara 1,95 kg. Globber declara plegado. MOLTO no declara peso en la ficha consultada."
                        )),
                        section("Carga del triciclo", List.of(
                                "U-GO declara 20 kg. Comprueba el peso real del niño."
                        ))
                )),
                List.of(
                        faqItem("¿Patinete o triciclo?",
                                "De pie frente a sentado. A los 4 años ambos pueden servir; no hace falta los dos."),
                        faqItem("¿Y la bicicleta sin pedales?",
                                "Otro gesto: equilibrio sentado sin pedales. Está en su comparativa."),
                        faqItem("¿Hace falta casco?",
                                "Sí, también en el triciclo si circula rápido o en pendiente suave."),
                        faqItem("¿Cuándo pasar a dos ruedas?",
                                "Cuando el equilibrio lateral es estable. Muchas familias lo retrasan más allá de los 4 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores patinetes de 3 ruedas para 4 años",
                                "/comparativas/" + SCOOTERS_TRIKES_4_SLUG + "/",
                                "Misma selección, con el foco en tres ruedas e iniciación."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildScootersTrikes5Page() {
        return ageComparison(
                SCOOTERS_TRIKES_5_SLUG,
                SCOOTERS_TRIKES_5_CANONICAL,
                AGE_5,
                SCOOTERS_3,
                "Patinetes y triciclos para 5 años | Bebes Felices",
                "Comparamos cuatro patinetes de tres ruedas y un triciclo con mango para moverse de pie o sentado a los 5 años, con metodología y afiliación transparentes.",
                "Patinetes y triciclos para 5 años",
                new ComparisonPageResponse.Header(
                        "Patinetes y triciclos para 5 años",
                        "De pie con tres ruedas o sentado con mango de adulto",
                        List.of(
                                "A los 5 años el movimiento al aire libre puede ser de pie (patinete) o sentado (triciclo). No son el mismo gesto. Casco, calzado cerrado y un espacio sin tráfico valen para los cinco.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "El triciclo no enseña el equilibrio del patinete. El patinete no lleva a un niño cansado en un paseo largo."
                        )
                ),
                "Las cinco opciones",
                List.of(
                        summary("Para ir de pie ligero", "patinete-micro-mini-deluxe", "1,95 kg y tres ruedas LED."),
                        summary("Para una primera de 5 años", "patinete-molto-maxi", "Edad mínima 5 años y manillar 57-67 cm."),
                        summary("Para plegar y llevar", "patinete-globber-junior-foldable", "Plegado y tres alturas de manillar."),
                        summary("Para empezar sentado y pasar a pie", "patinete-micro-mini-3en1", "Asiento extraíble y manillar 48-68 cm."),
                        summary("Para ir sentado con mango", "triciclo-chicco-u-go", "Triciclo 2en1 hasta 20 kg.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si el niño va de pie o sentado, y si un adulto puede guiar. No mezclamos este gesto con la bici sin pedales.",
                        List.of(
                                criterion("Postura", "De pie en tres ruedas o sentado en triciclo."),
                                criterion("Encaje a los 5 años", "Edad declarada y altura de manillar o mango."),
                                criterion("Manejo", "Peso, plegado o mango de adulto."),
                                criterion("Estabilidad", "Tres puntos de apoyo."),
                                criterion("Seguridad", "Casco, freno o cinturón según el modelo, y zona sin coches.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("De pie o sentado", List.of(
                                "Si ya se sostiene y quiere empujar, patinete. Si aún quieres guiar en el paseo, triciclo con mango."
                        )),
                        section("Peso y plegado", List.of(
                                "Micro declara 1,95 kg. Globber declara plegado. MOLTO no declara peso en la ficha consultada."
                        )),
                        section("Carga del triciclo", List.of(
                                "U-GO declara 20 kg. Comprueba el peso real del niño."
                        ))
                )),
                List.of(
                        faqItem("¿Patinete o triciclo?",
                                "De pie frente a sentado. A los 5 años ambos pueden servir; no hace falta los dos."),
                        faqItem("¿Y la bicicleta sin pedales?",
                                "Otro gesto: equilibrio sentado sin pedales. Está en su comparativa."),
                        faqItem("¿Hace falta casco?",
                                "Sí, también en el triciclo si circula rápido o en pendiente suave."),
                        faqItem("¿Cuándo pasar a dos ruedas?",
                                "Cuando el equilibrio lateral es estable. Muchas familias lo retrasan más allá de los 5 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores patinetes de 3 ruedas para 5 años",
                                "/comparativas/" + SCOOTERS_TRIKES_5_SLUG + "/",
                                "Misma selección, con el foco en tres ruedas e iniciación."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildTowersKitchen4Page() {
        return ageComparison(
                TOWERS_KITCHEN_4_SLUG,
                TOWERS_KITCHEN_4_CANONICAL,
                AGE_4,
                TOWERS_3,
                "Torres para la cocina a los 4 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje para la rutina de cocina a los 4 años: estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres para la cocina a los 4 años",
                new ComparisonPageResponse.Header(
                        "Torres para la cocina a los 4 años",
                        "Cinco torres reales para llegar a la encimera con un adulto",
                        List.of(
                                "A los 4 años la torre sirve para lavar, mezclar u observar en la cocina, no para trepar. La supervisión es constante. Lejos del fuego, del agua hirviendo y de los cuchillos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Mide la encimera y el hueco. Una torre plegable no cabe en cualquier pasillo aunque se pliegue."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar en cocina pequeña", "torre-costway-plegable", "Plegable y recomendada desde 4 años."),
                        summary("Para convertir en mesa", "torre-yoleo-transformer", "Nogal, plegado y escritorio."),
                        summary("Para tres alturas ligeras", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para patas anticaída", "torre-bey-co", "Tres alturas y EN-71."),
                        summary("Para más peso y escritorio", "torre-maxi-cosi-toucan", "8,7 kg, convertible.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la torre sirve en la cocina diaria a los 4 años: llegar a la encimera, no volcar y poder guardar. El detalle de estabilidad también está en la comparativa de torres de aprendizaje.",
                        List.of(
                                criterion("Encimera", "Altura de plataforma y si los brazos quedan sobre la mesa de trabajo."),
                                criterion("Estabilidad", "Barandilla, patas, peso y montaje."),
                                criterion("Guardar", "Plegado o conversión a mesa."),
                                criterion("Encaje a los 4 años", "Ficha y que un adulto esté siempre."),
                                criterion("Peligros de cocina", "Fuego, agua hirviendo, cuchillos: la torre no los elimina.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("La torre no es un taburete", List.of(
                                "Barandilla y adulto. Un taburete de cocina no sustituye este uso."
                        )),
                        section("Plegar o dejar fija", List.of(
                                "COSTWAY y YOLEO se pliegan. BEY & CO no declara plegado."
                        )),
                        section("Un solo niño", List.of(
                                "No es un juguete de trepa ni para dos a la vez junto a la vitro."
                        ))
                )),
                List.of(
                        faqItem("¿Es segura en la cocina a los 4 años?",
                                "Puede serlo con barandilla, suelo nivelado y un adulto. No lo es junto al fuego o como juego."),
                        faqItem("¿Hace falta preguntar antes de regalarla?",
                                "Sí. Ocupa sitio y pide un adulto dispuesto a usarla cada día."),
                        faqItem("¿Madera o metal?",
                                "Lo decisivo es la estabilidad, no el material."),
                        faqItem("¿Dónde está el análisis más largo de estabilidad?",
                                "En la comparativa de torres de aprendizaje para 4 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 4 años",
                                "/comparativas/" + TOWERS_KITCHEN_4_SLUG + "/",
                                "Estabilidad, altura y plegado, con el foco en la ficha técnica."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildTowersKitchen5Page() {
        return ageComparison(
                TOWERS_KITCHEN_5_SLUG,
                TOWERS_KITCHEN_5_CANONICAL,
                AGE_5,
                TOWERS_3,
                "Torres para la cocina a los 5 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje para la rutina de cocina a los 5 años: estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres para la cocina a los 5 años",
                new ComparisonPageResponse.Header(
                        "Torres para la cocina a los 5 años",
                        "Cinco torres reales para llegar a la encimera con un adulto",
                        List.of(
                                "A los 5 años la torre sirve para lavar, mezclar u observar en la cocina, no para trepar. La supervisión es constante. Lejos del fuego, del agua hirviendo y de los cuchillos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Mide la encimera y el hueco. Una torre plegable no cabe en cualquier pasillo aunque se pliegue."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar en cocina pequeña", "torre-costway-plegable", "Plegable y recomendada desde 5 años."),
                        summary("Para convertir en mesa", "torre-yoleo-transformer", "Nogal, plegado y escritorio."),
                        summary("Para tres alturas ligeras", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para patas anticaída", "torre-bey-co", "Tres alturas y EN-71."),
                        summary("Para más peso y escritorio", "torre-maxi-cosi-toucan", "8,7 kg, convertible.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la torre sirve en la cocina diaria a los 5 años: llegar a la encimera, no volcar y poder guardar. El detalle de estabilidad también está en la comparativa de torres de aprendizaje.",
                        List.of(
                                criterion("Encimera", "Altura de plataforma y si los brazos quedan sobre la mesa de trabajo."),
                                criterion("Estabilidad", "Barandilla, patas, peso y montaje."),
                                criterion("Guardar", "Plegado o conversión a mesa."),
                                criterion("Encaje a los 5 años", "Ficha y que un adulto esté siempre."),
                                criterion("Peligros de cocina", "Fuego, agua hirviendo, cuchillos: la torre no los elimina.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("La torre no es un taburete", List.of(
                                "Barandilla y adulto. Un taburete de cocina no sustituye este uso."
                        )),
                        section("Plegar o dejar fija", List.of(
                                "COSTWAY y YOLEO se pliegan. BEY & CO no declara plegado."
                        )),
                        section("Un solo niño", List.of(
                                "No es un juguete de trepa ni para dos a la vez junto a la vitro."
                        ))
                )),
                List.of(
                        faqItem("¿Es segura en la cocina a los 5 años?",
                                "Puede serlo con barandilla, suelo nivelado y un adulto. No lo es junto al fuego o como juego."),
                        faqItem("¿Hace falta preguntar antes de regalarla?",
                                "Sí. Ocupa sitio y pide un adulto dispuesto a usarla cada día."),
                        faqItem("¿Madera o metal?",
                                "Lo decisivo es la estabilidad, no el material."),
                        faqItem("¿Dónde está el análisis más largo de estabilidad?",
                                "En la comparativa de torres de aprendizaje para 5 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 5 años",
                                "/comparativas/" + TOWERS_KITCHEN_5_SLUG + "/",
                                "Estabilidad, altura y plegado, con el foco en la ficha técnica."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildTablewareDaily4Page() {
        return ageComparison(
                TABLEWARE_DAILY_4_SLUG,
                TABLEWARE_DAILY_4_CANONICAL,
                AGE_4,
                TABLEWARE_3,
                "Vajilla para la mesa diaria a los 4 años | Bebes Felices",
                "Comparamos cinco opciones irrompibles para que un niño de 4 años coma y beba en la mesa de cada día, con metodología y afiliación transparentes.",
                "Vajilla para la mesa diaria a los 4 años",
                new ComparisonPageResponse.Header(
                        "Vajilla para la mesa diaria a los 4 años",
                        "Plato, vaso o set para repetir el gesto en cada comida",
                        List.of(
                                "A los 4 años la vajilla infantil sirve si se usa todos los días: llevar el plato, beber y sobrevivir a las caídas. No hace falta un set de personaje enorme. Hace falta tamaño de mano y material que no se haga añicos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Revisa el marcado de alimento. El vaso abierto se supervisa al principio."
                        )
                ),
                "Las cinco piezas",
                List.of(
                        summary("Para un set de tres", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para otro set reutilizable", "vajilla-fun-house", "Plato 22 cm, cuenco y vaso 220 ml."),
                        summary("Para plato con tapa", "vajilla-twistshake-dividido", "Compartimentos y base antideslizante."),
                        summary("Para beber sin goteo", "vaso-munchkin-miracle-360", "Dos vasos 360° con asas."),
                        summary("Para guardar o llevar", "cuenco-twistshake-tapa", "Cuenco con tapa desde 6 meses.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la pieza entra en la comida diaria a los 4 años: se puede llevar, lavar y no se rompe. El detalle irrompible también está en la comparativa de vajillas infantiles.",
                        List.of(
                                criterion("Uso diario", "Si se saca en cada comida, no solo en fiestas."),
                                criterion("Mano y peso", "Que el niño pueda llevarlo a la mesa."),
                                criterion("Estabilidad", "Base ancha o antideslizante."),
                                criterion("Cuidado", "Lavado y microondas solo cuando la ficha lo dice."),
                                criterion("Seguridad", "Material de alimento y supervisión del vaso.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Un set o solo el vaso", List.of(
                                "Si ya hay plato, un vaso 360° puede bastar. Si empiezas de cero, un set de tres cubre más."
                        )),
                        section("Personaje o liso", List.of(
                                "El diseño se cansa. Prioriza base estable y BPA free cuando consta."
                        )),
                        section("No hace falta un cajón entero", List.of(
                                "Plato o cuenco, vaso y cubiertos infantiles suelen llegar. Un set enorme acaba en el armario."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas hacen falta para el día a día?",
                                "Plato o cuenco, vaso y cubiertos. El resto es recambio."),
                        faqItem("¿Vaso abierto o 360°?",
                                "El 360° ayuda al principio. A los 4 años también se practica el vaso abierto, con supervisión."),
                        faqItem("¿Plástico, bambú o acero?",
                                "Cualquiera si es apto para alimento y no se hace añicos. Revisa el marcado."),
                        faqItem("¿Dónde comparáis lo irrompible con más detalle?",
                                "En la comparativa de vajillas infantiles para 4 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Mejores vajillas infantiles para 4 años",
                                "/comparativas/" + TABLEWARE_DAILY_4_SLUG + "/",
                                "Sets, platos y vasos irrompibles, con el foco en la ficha."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildTablewareDaily5Page() {
        return ageComparison(
                TABLEWARE_DAILY_5_SLUG,
                TABLEWARE_DAILY_5_CANONICAL,
                AGE_5,
                TABLEWARE_3,
                "Vajilla para la mesa diaria a los 5 años | Bebes Felices",
                "Comparamos cinco opciones irrompibles para que un niño de 5 años coma y beba en la mesa de cada día, con metodología y afiliación transparentes.",
                "Vajilla para la mesa diaria a los 5 años",
                new ComparisonPageResponse.Header(
                        "Vajilla para la mesa diaria a los 5 años",
                        "Plato, vaso o set para repetir el gesto en cada comida",
                        List.of(
                                "A los 5 años la vajilla infantil sirve si se usa todos los días: llevar el plato, beber y sobrevivir a las caídas. No hace falta un set de personaje enorme. Hace falta tamaño de mano y material que no se haga añicos.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Revisa el marcado de alimento. El vaso abierto se supervisa al principio."
                        )
                ),
                "Las cinco piezas",
                List.of(
                        summary("Para un set de tres", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para otro set reutilizable", "vajilla-fun-house", "Plato 22 cm, cuenco y vaso 220 ml."),
                        summary("Para plato con tapa", "vajilla-twistshake-dividido", "Compartimentos y base antideslizante."),
                        summary("Para beber sin goteo", "vaso-munchkin-miracle-360", "Dos vasos 360° con asas."),
                        summary("Para guardar o llevar", "cuenco-twistshake-tapa", "Cuenco con tapa desde 6 meses.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos si la pieza entra en la comida diaria a los 5 años: se puede llevar, lavar y no se rompe. El detalle irrompible también está en la comparativa de vajillas infantiles.",
                        List.of(
                                criterion("Uso diario", "Si se saca en cada comida, no solo en fiestas."),
                                criterion("Mano y peso", "Que el niño pueda llevarlo a la mesa."),
                                criterion("Estabilidad", "Base ancha o antideslizante."),
                                criterion("Cuidado", "Lavado y microondas solo cuando la ficha lo dice."),
                                criterion("Seguridad", "Material de alimento y supervisión del vaso.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Un set o solo el vaso", List.of(
                                "Si ya hay plato, un vaso 360° puede bastar. Si empiezas de cero, un set de tres cubre más."
                        )),
                        section("Personaje o liso", List.of(
                                "El diseño se cansa. Prioriza base estable y BPA free cuando consta."
                        )),
                        section("No hace falta un cajón entero", List.of(
                                "Plato o cuenco, vaso y cubiertos infantiles suelen llegar. Un set enorme acaba en el armario."
                        ))
                )),
                List.of(
                        faqItem("¿Cuántas piezas hacen falta para el día a día?",
                                "Plato o cuenco, vaso y cubiertos. El resto es recambio."),
                        faqItem("¿Vaso abierto o 360°?",
                                "El 360° ayuda al principio. A los 5 años también se practica el vaso abierto, con supervisión."),
                        faqItem("¿Plástico, bambú o acero?",
                                "Cualquiera si es apto para alimento y no se hace añicos. Revisa el marcado."),
                        faqItem("¿Dónde comparáis lo irrompible con más detalle?",
                                "En la comparativa de vajillas infantiles para 5 años, con las mismas cinco fichas."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Mejores vajillas infantiles para 5 años",
                                "/comparativas/" + TABLEWARE_DAILY_5_SLUG + "/",
                                "Sets, platos y vasos irrompibles, con el foco en la ficha."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildGifts4Page() {
        return ageComparison(
                GIFTS_4_SLUG,
                GIFTS_4_CANONICAL,
                AGE_4,
                GIFTS_3,
                "Mejores ideas de regalo para niños de 4 años | Bebes Felices",
                "Comparamos cinco regalos reales para 4 años por necesidad —aprender, moverse, autonomía o crear—, con metodología y afiliación transparentes.",
                "Ideas de regalo para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores ideas de regalo para niños de 4 años",
                        "Cinco regalos reales según lo que el niño ya hace",
                        List.of(
                                "Un buen regalo a los 4 años se usa durante semanas, no solo el día del cumpleaños. Elegimos un producto por necesidad: encajar, completar, moverse, participar en la cocina o crear.",
                                "La selección reúne cinco productos del catálogo, disponibles en Amazon.es cuando la ficha lo permite. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Si ya tiene un cubo de formas, no dupliques: pasa a movimiento, autonomía o un kit de crear. La torre no se regala sin preguntar si hay espacio y un adulto dispuesto a usarla."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para aprender encajando", "juego-montessori-formas", "Cubo de 12 piezas grandes."),
                        summary("Para terminar una escena", "puzle-madera-animales", "Puzle de madera de safari."),
                        summary("Para moverse", "bici-chicco-red-bullet", "Bicicleta sin pedales ligera y ajustable."),
                        summary("Para la cocina", "torre-costway-plegable", "Torre plegable recomendada desde 4 años."),
                        summary("Para crear sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera con un adulto.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la necesidad que cubre cada regalo para un niño de 4 años. No es un ranking de «el mejor juguete», sino de roles distintos.",
                        List.of(
                                criterion("Necesidad", "Aprender, moverse, autonomía o crear. No repetimos el mismo gesto cinco veces."),
                                criterion("Uso real", "Si se puede usar el mismo día, con sesiones cortas."),
                                criterion("Regalo", "Si hay que preguntar (torre, talla de bici) o se puede acertar a ciegas (puzle)."),
                                criterion("Duración", "Semanas de uso, no un objeto decorativo."),
                                criterion("Límite", "Casco, supervisión o espacio: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya tiene", List.of(
                                "Si duda, un puzle de piezas grandes o un cubo de formas suele ser más seguro que un juguete «para mayores».",
                                "Si ya hay muchos juguetes de mesa, prioriza movimiento o autonomía."
                        )),
                        section("Pregunta antes de la torre o la bici", List.of(
                                "La torre ocupa sitio y pide un adulto. La bici pide entrepierna, casco y un sitio para usarla.",
                                "El detalle de modelos está en las comparativas de torres y de bicicletas."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. Compara utilidad; el presupuesto lo decides tú al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo no falla a esta edad?",
                                "Uno que pueda usar ya: encajar, moverse con estabilidad o participar en la mesa. Un puzle de piezas grandes suele ser más seguro que un juguete «para mayores»."),
                        faqItem("¿Y si ya tiene muchos juguetes?",
                                "Prioriza autonomía o movimiento, o un kit de crear con un adulto. Evita duplicar lo que ya cubre una necesidad."),
                        faqItem("¿La bicicleta está analizada aquí o en otra página?",
                                "Aquí cuenta como idea de regalo. La comparativa de bicicletas sin pedales detalla talla, peso y ruedas."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y durabilidad."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Regalos sostenibles para 4 años",
                                "/comparativas/" + GIFTS_4_SLUG + "/",
                                "Materiales declarados y uso que no dependa de pantallas."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildGifts5Page() {
        return ageComparison(
                GIFTS_5_SLUG,
                GIFTS_5_CANONICAL,
                AGE_5,
                GIFTS_5,
                "Mejores ideas de regalo para niños de 5 años | Bebes Felices",
                "Comparamos cinco regalos reales para 5 años por necesidad —aprender, moverse, autonomía o crear—, con metodología y afiliación transparentes.",
                "Ideas de regalo para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores ideas de regalo para niños de 5 años",
                        "Cinco regalos reales según lo que el niño ya hace",
                        List.of(
                                "Un buen regalo a los 5 años se usa durante semanas, no solo el día del cumpleaños. Elegimos un producto por necesidad: encajar, completar, moverse, participar en la cocina o crear.",
                                "La selección reúne cinco productos del catálogo, disponibles en Amazon.es cuando la ficha lo permite. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Si ya tiene un cubo de formas, no dupliques: pasa a movimiento, autonomía o un kit de crear. La torre no se regala sin preguntar si hay espacio y un adulto dispuesto a usarla."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para construir y razonar", "set-construccion-magnetico", "Piezas magnéticas para estructuras tridimensionales."),
                        summary("Para terminar una escena", "puzle-madera-animales", "Puzle de madera de safari."),
                        summary("Para moverse", "bici-sin-pedales-basica", "Bicicleta sin pedales ligera y ajustable."),
                        summary("Para jugar en familia", "juego-mesa-cooperativo", "Cooperativo de reglas sencillas."),
                        summary("Para crear sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera con un adulto.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos la necesidad que cubre cada regalo para un niño de 5 años. No es un ranking de «el mejor juguete», sino de roles distintos.",
                        List.of(
                                criterion("Necesidad", "Aprender, moverse, autonomía o crear. No repetimos el mismo gesto cinco veces."),
                                criterion("Uso real", "Si se puede usar el mismo día, con sesiones cortas."),
                                criterion("Regalo", "Si hay que preguntar (torre, talla de bici) o se puede acertar a ciegas (puzle)."),
                                criterion("Duración", "Semanas de uso, no un objeto decorativo."),
                                criterion("Límite", "Casco, supervisión o espacio: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige según lo que ya tiene", List.of(
                                "Si duda, un puzle de piezas grandes o un cubo de formas suele ser más seguro que un juguete «para mayores».",
                                "Si ya hay muchos juguetes de mesa, prioriza movimiento o autonomía."
                        )),
                        section("Pregunta antes de la torre o la bici", List.of(
                                "La torre ocupa sitio y pide un adulto. La bici pide entrepierna, casco y un sitio para usarla.",
                                "El detalle de modelos está en las comparativas de torres y de bicicletas."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. Compara utilidad; el presupuesto lo decides tú al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo no falla a esta edad?",
                                "Uno que pueda usar ya: encajar, moverse con estabilidad o participar en la mesa. Un puzle de piezas grandes suele ser más seguro que un juguete «para mayores»."),
                        faqItem("¿Y si ya tiene muchos juguetes?",
                                "Prioriza autonomía o movimiento, o un kit de crear con un adulto. Evita duplicar lo que ya cubre una necesidad."),
                        faqItem("¿La bicicleta está analizada aquí o en otra página?",
                                "Aquí cuenta como idea de regalo. La comparativa de bicicletas sin pedales detalla talla, peso y ruedas."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y durabilidad."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Regalos sostenibles para 5 años",
                                "/comparativas/" + GIFTS_5_SLUG + "/",
                                "Materiales declarados y uso que no dependa de pantallas."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildGiftSelection4Page() {
        return ageComparison(
                GIFT_SELECTION_4_SLUG,
                GIFT_SELECTION_4_CANONICAL,
                AGE_4,
                GIFT_SELECTION_3,
                "Selección de regalos para 4 años | Bebes Felices",
                "Cinco regalos reales para 4 años por ocasión: un puzle que se termina, aire libre, mesa diaria, cuatro escenas o ensartar, con metodología y afiliación transparentes.",
                "Selección de regalos para 4 años",
                new ComparisonPageResponse.Header(
                        "Selección de regalos para niños de 4 años",
                        "Cinco aciertos por ocasión, sin repetir la comparativa de ideas por necesidad",
                        List.of(
                                "Si no vives en esa casa, un puzle que se termina o un set de mesa suele fallar menos que una torre o una bici. Aquí no repetimos cubo, bici, torre y kit: esa lista está en ideas de regalo por necesidad.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Pregunta antes patinete o vajilla si puede haber duplicado. El casco no se improvisa el día del cumpleaños."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para acertar sin conocer la casa", "puzle-madera-animales", "Puzle de madera que se termina."),
                        summary("Para el patio", "patinete-micro-mini-deluxe", "Tres ruedas ligeras; pide casco."),
                        summary("Para cada comida", "vajilla-stor-mickey", "Set de tres piezas irrompibles."),
                        summary("Para varias sesiones", "haba-puzles-cuatro-estaciones", "Cuatro puzles y figuras."),
                        summary("Para un formato compacto", "cuentas-melissa-doug", "Cuentas de madera; supervisión.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos regalos por ocasión y riesgo de duplicar. No es un ranking de «el mejor regalo» ni de presupuesto.",
                        List.of(
                                criterion("Ocasión", "Visita a ciegas, aire libre, rutina, más sesiones o formato pequeño."),
                                criterion("Uso real", "Si se usa el mismo día o en la comida siguiente."),
                                criterion("Preguntar", "Patinete y vajilla: si ya hay uno, cambia."),
                                criterion("Supervisión", "Cuentas, figuras y casco."),
                                criterion("Límite", "No sustituye preguntar por torre o talla de bici.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Si no conoces los juguetes de la casa", List.of(
                                "Puzle de piezas grandes o HABA de cuatro escenas. Evita torre y bici sin preguntar."
                        )),
                        section("Si el niño está mucho en la mesa", List.of(
                                "La vajilla se usa cada día. Pregunta si ya tienen set."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. El presupuesto lo decides al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo falla menos si no conoces la casa?",
                                "Un puzle de piezas grandes o un set de mesa. La torre y la bici piden pregunta."),
                        faqItem("¿Por qué no están el cubo, la bici y la torre?",
                                "Están en ideas de regalo por necesidad. Aquí cubrimos otra ocasión: visita, patio, mesa, más puzles o formato pequeño."),
                        faqItem("¿Las cuentas son un buen regalo de visita?",
                                "Sí si hay un adulto. No si se van a dejar en el suelo con un hermano pequeño."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y ocasión."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Ideas de regalo para 4 años",
                                "/comparativas/" + GIFT_SELECTION_4_SLUG + "/",
                                "Una opción por necesidad: aprender, moverse, autonomía o crear."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildGiftSelection5Page() {
        return ageComparison(
                GIFT_SELECTION_5_SLUG,
                GIFT_SELECTION_5_CANONICAL,
                AGE_5,
                GIFT_SELECTION_3,
                "Selección de regalos para 5 años | Bebes Felices",
                "Cinco regalos reales para 5 años por ocasión: un puzle que se termina, aire libre, mesa diaria, cuatro escenas o ensartar, con metodología y afiliación transparentes.",
                "Selección de regalos para 5 años",
                new ComparisonPageResponse.Header(
                        "Selección de regalos para niños de 5 años",
                        "Cinco aciertos por ocasión, sin repetir la comparativa de ideas por necesidad",
                        List.of(
                                "Si no vives en esa casa, un puzle que se termina o un set de mesa suele fallar menos que una torre o una bici. Aquí no repetimos cubo, bici, torre y kit: esa lista está en ideas de regalo por necesidad.",
                                "La selección reúne cinco productos del catálogo. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Pregunta antes patinete o vajilla si puede haber duplicado. El casco no se improvisa el día del cumpleaños."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para acertar sin conocer la casa", "puzle-madera-animales", "Puzle de madera que se termina."),
                        summary("Para el patio", "patinete-micro-mini-deluxe", "Tres ruedas ligeras; pide casco."),
                        summary("Para cada comida", "vajilla-stor-mickey", "Set de tres piezas irrompibles."),
                        summary("Para varias sesiones", "haba-puzles-cuatro-estaciones", "Cuatro puzles y figuras."),
                        summary("Para un formato compacto", "cuentas-melissa-doug", "Cuentas de madera; supervisión.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos regalos por ocasión y riesgo de duplicar. No es un ranking de «el mejor regalo» ni de presupuesto.",
                        List.of(
                                criterion("Ocasión", "Visita a ciegas, aire libre, rutina, más sesiones o formato pequeño."),
                                criterion("Uso real", "Si se usa el mismo día o en la comida siguiente."),
                                criterion("Preguntar", "Patinete y vajilla: si ya hay uno, cambia."),
                                criterion("Supervisión", "Cuentas, figuras y casco."),
                                criterion("Límite", "No sustituye preguntar por torre o talla de bici.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Si no conoces los juguetes de la casa", List.of(
                                "Puzle de piezas grandes o HABA de cuatro escenas. Evita torre y bici sin preguntar."
                        )),
                        section("Si el niño está mucho en la mesa", List.of(
                                "La vajilla se usa cada día. Pregunta si ya tienen set."
                        )),
                        section("Sin importes", List.of(
                                "No publicamos rangos de precio. El presupuesto lo decides al comprar."
                        ))
                )),
                List.of(
                        faqItem("¿Qué regalo falla menos si no conoces la casa?",
                                "Un puzle de piezas grandes o un set de mesa. La torre y la bici piden pregunta."),
                        faqItem("¿Por qué no están el cubo, la bici y la torre?",
                                "Están en ideas de regalo por necesidad. Aquí cubrimos otra ocasión: visita, patio, mesa, más puzles o formato pequeño."),
                        faqItem("¿Las cuentas son un buen regalo de visita?",
                                "Sí si hay un adulto. No si se van a dejar en el suelo con un hermano pequeño."),
                        faqItem("¿Incluís rangos de precio?",
                                "No. Comparamos utilidad y ocasión."),
                        faqItem("¿Por qué no aparecen valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/comparativas/" + GIFT_SELECTION_5_SLUG + "/",
                                "Una opción por necesidad: aprender, moverse, autonomía o crear."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildChooseGift4Page() {
        return ageComparison(
                CHOOSE_GIFT_4_SLUG,
                CHOOSE_GIFT_4_CANONICAL,
                AGE_4,
                CHOOSE_GIFT_3,
                "Cómo elegir el regalo según la edad a los 4 años | Bebes Felices",
                "Cinco productos reales según lo que el niño de 4 años ya hace: encajar, moverse de pie, comer, crear o jugar a la obra, con metodología y afiliación transparentes.",
                "Cómo elegir el regalo a los 4 años",
                new ComparisonPageResponse.Header(
                        "Cómo elegir el regalo según la edad a los 4 años",
                        "Cinco productos según lo que ya hace, no según la caja",
                        List.of(
                                "A los 4 años elige por el gesto que ya sostiene: encajar, empujar de pie, llevar el plato, modelar con un adulto o cargar en un juego de obra. La edad de la caja es seguridad, no garantía de acierto.",
                                "La selección reúne cinco productos del catálogo, uno por criterio. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Si ya cubre ese gesto, no lo dupliques: pasa a otra fila. La guía larga de criterios sigue en cómo elegir juguetes por edad."
                        )
                ),
                "Los cinco criterios",
                List.of(
                        summary("Si ya encaja formas", "juego-montessori-formas", "Cubo de 12 piezas grandes."),
                        summary("Si ya se sostiene de pie", "patinete-micro-mini-deluxe", "Tres ruedas; pide casco y sitio."),
                        summary("Si ya quiere la mesa", "vajilla-stor-mickey", "Set irrompible de tres piezas."),
                        summary("Si hay un adulto para crear", "kit-manualidades-natural", "Plastilina ecológica y madera."),
                        summary("Si ya juega a la obra", "small-foot-grua", "Grúa de madera FSC que se repite.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el criterio de elección a los 4 años: qué hace ya el niño. Cada producto es un ejemplo, no «el único regalo correcto».",
                        List.of(
                                criterion("Qué ya hace", "Encajar, ir de pie, comer, crear u obra."),
                                criterion("Por qué a los 4", "Sesiones cortas y piezas o talla compatibles."),
                                criterion("Regalo", "Si se puede acertar sin conocer toda la casa."),
                                criterion("No elijas esto si", "Ese gesto ya está cubierto o falta otra necesidad."),
                                criterion("Límite", "Casco, supervisión o adulto para crear: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "No por lo que «tendría que» hacer. Un juguete para mayores frustra más que uno que se termina."
                        )),
                        section("Un gesto por regalo", List.of(
                                "No hace falta cubo, patinete y grúa el mismo día. Elige la fila que falte."
                        )),
                        section("La guía y esta comparativa", List.of(
                                "La guía de cómo elegir juguetes por edad explica criterios. Aquí hay cinco productos reales para aplicarlos a los 4 años."
                        ))
                )),
                List.of(
                        faqItem("¿La edad de la caja basta?",
                                "Es imprescindible para seguridad, no basta. Contrástala con lo que el niño ya hace."),
                        faqItem("¿Qué evito a los 4 años?",
                                "Reglas largas, piezas diminutas y movimiento sin casco ni supervisión. También duplicar un cubo o un patinete que ya tiene."),
                        faqItem("¿Y si no sé qué tiene en casa?",
                                "Mira la selección de regalos por ocasión: puzle o vajilla suelen fallar menos que torre o bici."),
                        faqItem("¿Dónde está la guía completa?",
                                "En cómo elegir juguetes según la edad, con variantes para 3, 4 y 5 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub4Link(),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/?edad=4",
                                "Criterios prácticos para 3, 4 y 5 años."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 4 años",
                                "/comparativas/" + CHOOSE_GIFT_4_SLUG + "/",
                                "Otra lista por necesidad: aprender, moverse, autonomía o crear."
                        )
                )
        );
    }
    private ComparisonPageResponse buildChooseGift5Page() {
        return ageComparison(
                CHOOSE_GIFT_5_SLUG,
                CHOOSE_GIFT_5_CANONICAL,
                AGE_5,
                CHOOSE_GIFT_3,
                "Cómo elegir el regalo según la edad a los 5 años | Bebes Felices",
                "Cinco productos reales según lo que el niño de 5 años ya hace: encajar, moverse de pie, comer, crear o jugar a la obra, con metodología y afiliación transparentes.",
                "Cómo elegir el regalo a los 5 años",
                new ComparisonPageResponse.Header(
                        "Cómo elegir el regalo según la edad a los 5 años",
                        "Cinco productos según lo que ya hace, no según la caja",
                        List.of(
                                "A los 5 años elige por el gesto que ya sostiene: encajar, empujar de pie, llevar el plato, modelar con un adulto o cargar en un juego de obra. La edad de la caja es seguridad, no garantía de acierto.",
                                "La selección reúne cinco productos del catálogo, uno por criterio. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Si ya cubre ese gesto, no lo dupliques: pasa a otra fila. La guía larga de criterios sigue en cómo elegir juguetes por edad."
                        )
                ),
                "Los cinco criterios",
                List.of(
                        summary("Si ya encaja formas", "juego-montessori-formas", "Cubo de 12 piezas grandes."),
                        summary("Si ya se sostiene de pie", "patinete-micro-mini-deluxe", "Tres ruedas; pide casco y sitio."),
                        summary("Si ya quiere la mesa", "vajilla-stor-mickey", "Set irrompible de tres piezas."),
                        summary("Si hay un adulto para crear", "kit-manualidades-natural", "Plastilina ecológica y madera."),
                        summary("Si ya juega a la obra", "small-foot-grua", "Grúa de madera FSC que se repite.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el criterio de elección a los 5 años: qué hace ya el niño. Cada producto es un ejemplo, no «el único regalo correcto».",
                        List.of(
                                criterion("Qué ya hace", "Encajar, ir de pie, comer, crear u obra."),
                                criterion("Por qué a los 5", "Sesiones cortas y piezas o talla compatibles."),
                                criterion("Regalo", "Si se puede acertar sin conocer toda la casa."),
                                criterion("No elijas esto si", "Ese gesto ya está cubierto o falta otra necesidad."),
                                criterion("Límite", "Casco, supervisión o adulto para crear: lo dejamos explícito.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que ya hace", List.of(
                                "No por lo que «tendría que» hacer. Un juguete para mayores frustra más que uno que se termina."
                        )),
                        section("Un gesto por regalo", List.of(
                                "No hace falta cubo, patinete y grúa el mismo día. Elige la fila que falte."
                        )),
                        section("La guía y esta comparativa", List.of(
                                "La guía de cómo elegir juguetes por edad explica criterios. Aquí hay cinco productos reales para aplicarlos a los 5 años."
                        ))
                )),
                List.of(
                        faqItem("¿La edad de la caja basta?",
                                "Es imprescindible para seguridad, no basta. Contrástala con lo que el niño ya hace."),
                        faqItem("¿Qué evito a los 5 años?",
                                "Reglas largas, piezas diminutas y movimiento sin casco ni supervisión. También duplicar un cubo o un patinete que ya tiene."),
                        faqItem("¿Y si no sé qué tiene en casa?",
                                "Mira la selección de regalos por ocasión: puzle o vajilla suelen fallar menos que torre o bici."),
                        faqItem("¿Dónde está la guía completa?",
                                "En cómo elegir juguetes según la edad, con variantes para 3, 4 y 5 años."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/?edad=5",
                                "Criterios prácticos para 3, 4 y 5 años."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/comparativas/" + CHOOSE_GIFT_5_SLUG + "/",
                                "Otra lista por necesidad: aprender, moverse, autonomía o crear."
                        )
                )
        );
    }
    private ComparisonPageResponse buildBalanceBikes4Page() {
        Map<String, EditorialEntry> editorialById = new LinkedHashMap<>();
        BALANCE_BIKES.forEach(entry -> editorialById.put(entry.productId(), entry));

        AtomicInteger rank = new AtomicInteger(1);
        List<ComparisonPageResponse.Entry> entries = productCatalog
                .findByIds(BALANCE_BIKES.stream().map(EditorialEntry::productId).toList())
                .stream()
                .filter(product -> product.isAvailableForAge(AGE_4))
                .map(product -> toResponseEntry(
                        rank.getAndIncrement(),
                        product,
                        editorialById.get(product.id())
                ))
                .toList();

        return new ComparisonPageResponse(
                new Seo(
                        BALANCE_BIKES_4_CANONICAL,
                        "Mejores bicicletas sin pedales para 4 años | Bebes Felices",
                        "Comparamos cinco bicicletas sin pedales aptas para 4 años por ajuste, peso, ruedas y facilidad de manejo, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BALANCE_BIKES_4_SLUG,
                AGE_4,
                breadcrumbs(),
                new ComparisonPageResponse.Header(
                        "Mejores bicicletas sin pedales para 4 años",
                        "Cinco modelos reales comparados por talla, manejo y tipo de rueda",
                        List.of(
                                "A los 4 años importa más que el niño llegue bien al suelo y pueda controlar la bicicleta que cualquier reclamo comercial. Por eso revisamos ajustes, peso declarado, ruedas y elementos que facilitan el aprendizaje.",
                                "La selección reúne cinco modelos disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 14 de agosto de 2026.",
                                "Una bicicleta sin pedales requiere casco, calzado cerrado, ajuste correcto y supervisión adulta. Comprueba siempre las instrucciones y límites indicados por el fabricante."
                        )
                ),
                List.of(),
                quickSummary(),
                methodology(),
                entries,
                buyingGuide(),
                faq(),
                relatedLinks(),
                trustAuthority(),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                BIKES_PUBLISHED_AT,
                BIKES_UPDATED_AT
        );
    }
    private ComparisonPageResponse buildBalanceBikes5Page() {
        Map<String, EditorialEntry> editorialById = new LinkedHashMap<>();
        BALANCE_BIKES.forEach(entry -> editorialById.put(entry.productId(), entry));

        AtomicInteger rank = new AtomicInteger(1);
        List<ComparisonPageResponse.Entry> entries = productCatalog
                .findByIds(BALANCE_BIKES.stream().map(EditorialEntry::productId).toList())
                .stream()
                .filter(product -> product.isAvailableForAge(AGE_5))
                .map(product -> toResponseEntry(
                        rank.getAndIncrement(),
                        product,
                        editorialById.get(product.id())
                ))
                .toList();

        return new ComparisonPageResponse(
                new Seo(
                        BALANCE_BIKES_5_CANONICAL,
                        "Mejores bicicletas sin pedales para 5 años | Bebes Felices",
                        "Comparamos cinco bicicletas sin pedales aptas para 5 años por ajuste, peso, ruedas y facilidad de manejo, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BALANCE_BIKES_5_SLUG,
                AGE_5,
                breadcrumbs(),
                new ComparisonPageResponse.Header(
                        "Mejores bicicletas sin pedales para 5 años",
                        "Cinco modelos reales comparados por talla, manejo y tipo de rueda",
                        List.of(
                                "A los 5 años importa más que el niño llegue bien al suelo y pueda controlar la bicicleta que cualquier reclamo comercial. Por eso revisamos ajustes, peso declarado, ruedas y elementos que facilitan el aprendizaje.",
                                "La selección reúne cinco modelos disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Una bicicleta sin pedales requiere casco, calzado cerrado, ajuste correcto y supervisión adulta. Comprueba siempre las instrucciones y límites indicados por el fabricante."
                        )
                ),
                List.of(),
                quickSummary(),
                methodology(),
                entries,
                buyingGuide(),
                faq(),
                relatedLinks(),
                trustAuthority(),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                BIKES_PUBLISHED_AT,
                BIKES_UPDATED_AT
        );
    }
    private ComparisonPageResponse buildStem4Page() {
        return ageComparison(
                STEM_4_SLUG,
                STEM_4_CANONICAL,
                AGE_4,
                STEM_5,
                "Mejores juguetes STEM para 4 años | Bebes Felices",
                "Comparamos cinco juguetes existentes aptos para 4 años por lógica, construcción, patrones y uso evolutivo.",
                "Juguetes STEM para 4 años",
                new ComparisonPageResponse.Header(
                        "Mejores juguetes STEM para 4 años",
                        "Cinco opciones reales para construir, observar patrones y resolver problemas",
                        List.of(
                                "A los 4 años un juguete STEM útil plantea un problema visible: levantar una carga, mantener una torre, completar un patrón o construir una estructura.",
                                "Reutilizamos productos ya presentes en el catálogo y aptos para esta edad. No añadimos precios, estrellas ni enlaces comerciales inventados.",
                                "STEM no significa dejar al niño solo: los imanes, piezas y reglas requieren revisar la seguridad y acompañar el primer uso."
                        )
                ),
                "Los cinco juguetes",
                List.of(
                        summary("Para construcción tridimensional", "set-construccion-magnetico", "Formas, simetría y estabilidad con piezas magnéticas."),
                        summary("Para mecanismos sencillos", "small-foot-grua", "Giro, manivela y transporte de cargas."),
                        summary("Para equilibrio", "juego-mesa-animal-sobre-animal", "Apilado, apoyo y distribución del peso."),
                        summary("Para orientación espacial", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas."),
                        summary("Para reconocer patrones", "juego-mesa-dobble-kids", "Comparación visual con una regla breve.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos el tipo de reto, la autonomía posible y la seguridad para un niño de 4 años. La selección no presupone que todo juguete educativo sea STEM.",
                        List.of(
                                criterion("Reto STEM", "Debe practicar construcción, mecanismos, equilibrio, orientación o patrones de forma observable."),
                                criterion("Encaje a los 4 años", "Todos los productos incluyen esta edad en el rango del catálogo."),
                                criterion("Uso evolutivo", "Valoramos si el reto admite repetición o dificultad creciente."),
                                criterion("Dependencia del adulto", "Indicamos cuándo hace falta explicar reglas o proponer el primer reto."),
                                criterion("Seguridad", "Revisamos imanes, piezas sueltas y la presencia de menores de 3 años.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el tipo de problema", List.of(
                                "Para construir, prioriza el set magnético; para mecanismos, la grúa; para equilibrio, Animal sobre Animal.",
                                "Los puzles y Dobble cubren orientación y patrones, pero no sustituyen una experiencia de construcción."
                        )),
                        section("Busca dificultad ajustable", List.of(
                                "Empieza con pocas piezas o una regla y aumenta el reto cuando pueda terminar sin ayuda constante.",
                                "Un producto evolutivo permite cambiar la consigna sin comprar otro juguete."
                        )),
                        section("Revisa piezas e imanes", List.of(
                                "Los imanes deben estar encapsulados y las piezas sueltas fuera del alcance de menores de 3 años.",
                                "La edad recomendada no sustituye revisar el estado del producto antes de cada uso."
                        ))
                )),
                List.of(
                        faqItem("¿Qué significa STEM a los 4 años?", "Resolver problemas de ciencia, tecnología, ingeniería o matemáticas mediante juego: construir, observar mecanismos, equilibrar o reconocer patrones."),
                        faqItem("¿Hace falta un juguete electrónico?", "No. Una grúa con manivela, un apilado o una construcción magnética permiten observar principios físicos sin pantalla."),
                        faqItem("¿Cuál admite más dificultad?", "La construcción magnética ofrece el margen más directo: se pueden añadir piezas, restricciones y estructuras más complejas."),
                        faqItem("¿Todos sirven para jugar sin adulto?", "No. Conviene explicar el primer reto, acompañar las reglas y revisar imanes y piezas."),
                        faqItem("¿Por qué no aparecen precios ni estrellas?", "Porque cambian y no forman parte del criterio editorial estable.")
                ),
                List.of(
                        new LinkItem("Juguetes y regalos para niños de 4 años", EditorialDefaults.hubHref(5), "Volver al hub de esta edad."),
                        new LinkItem("Ideas de regalo para 4 años", "/regalos/ideas-regalo-5-anos/", "Selección más amplia por necesidad."),
                        new LinkItem("Qué habilidades desarrolla un niño de 4 años", "/guias/habilidades-5-anos/", "Pensamiento lógico, cooperación y autonomía."),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildBoardGames5Page() {
        List<ComparisonPageResponse.Entry> entries = rankedEntries(BOARD_GAMES, AGE_5);
        return new ComparisonPageResponse(
                new Seo(
                        BOARD_GAMES_5_CANONICAL,
                        "Mejores juegos de mesa para 5 años | Bebes Felices",
                        "Comparamos cinco juegos de mesa reales para 5 años por encaje, cooperación, lectura y componentes, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BOARD_GAMES_5_SLUG,
                AGE_5,
                List.of(
                        new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                        new ComparisonPageResponse.Breadcrumb(
                                EditorialDefaults.HUB_4_LABEL,
                                EditorialDefaults.HUB_4_HREF
                        ),
                        new ComparisonPageResponse.Breadcrumb(
                                "Juegos de mesa para 5 años",
                                BOARD_GAMES_5_CANONICAL
                        )
                ),
                new ComparisonPageResponse.Header(
                        "Mejores juegos de mesa para 5 años",
                        "Cinco juegos reales comparados por reglas, cooperación y tiempo de partida",
                        List.of(
                                "A los 5 años caben las primeras reglas, los turnos cortos y, si el grupo lo admite, un objetivo compartido. Importa más que la partida se pueda explicar en pocos minutos que cualquier reclamo de «el más educativo».",
                                "La selección reúne cinco juegos disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Un adulto debe explicar el objetivo, vigilar piezas pequeñas y adaptar el ritmo. Distinguimos cooperativos y competitivos para que elijas según cómo juega tu casa."
                        )
                ),
                List.of(),
                List.of(
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para empezar a cooperar",
                                "juego-mesa-el-frutal-mini",
                                "Reglas mínimas y un objetivo común frente al cuervo."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para colores y primeros conteos",
                                "juego-mesa-unicornio-tesoro",
                                "Dados, tablero a doble cara y cristales de nube."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para pulso y motricidad fina",
                                "juego-mesa-animal-sobre-animal",
                                "Apilar 29 figuras de madera según el dado."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para observación en familia",
                                "juego-mesa-dobble-kids",
                                "Partidas de 15 minutos y hasta 8 jugadores."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para un cooperativo más estructurado",
                                "juego-mesa-unicornio-fiesta-rosalie",
                                "Dados, ruleta y un objetivo compartido a partir de 5 años."
                        )
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 5 años. No asignamos notas numéricas ni completamos datos que el fabricante o la ficha consultada no permitan confirmar.",
                        List.of(
                                new ComparisonPageResponse.Criterion(
                                        "Encaje a los 5 años",
                                        "Revisamos la edad declarada, si las reglas caben en una partida corta y si el turno se entiende sin un reglamento largo."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Cooperación o competición",
                                        "Dejamos claro si se gana en equipo o hay un ganador individual. A esta edad ambos formatos pueden funcionar; no son intercambiables."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Lectura y dependencia del adulto",
                                        "Priorizamos símbolos, colores y dados frente a texto. Un adulto sigue siendo necesario para explicar y acompañar."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Rejugabilidad",
                                        "Valoramos si hay variantes, azar de dados o suficiente variedad para repetir sin agotarse en una tarde."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Componentes y seguridad",
                                        "Comprobamos materiales declarados y avisos de piezas pequeñas. Ningún juego sustituye la supervisión."
                                )
                        )
                ),
                entries,
                new ComparisonPageResponse.BuyingGuide(List.of(
                        new ComparisonPageResponse.Section(
                                "Empieza por cómo jugáis en casa",
                                List.of(
                                        "Si las derrotas individuales acaban en llanto, un cooperativo como El Frutal Mini suele encajar mejor que un juego de rapidez.",
                                        "Si ya aguanta turnos y le gusta competir un rato, Dobble Kids o Animal sobre Animal cubren observación y pulso sin partidas largas."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Tiempo real de partida",
                                List.of(
                                        "A los 5 años conviene que se pueda terminar. Quince minutos declarados siguen pidiendo un adulto que evite discusiones y recoja piezas.",
                                        "La primera partida cuenta como explicación: no midas el éxito por si «ya juega solo»."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Lectura, idioma y piezas",
                                List.of(
                                        "Dobble Kids está en español. Los HABA de esta lista se juegan con símbolos, dados o ruleta; confirma que el reglamento de tu caja está en un idioma que puedes explicar.",
                                        "Revisa avisos de piezas pequeñas, sobre todo cristales y cartas, si hay hermanos menores de 3 años."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Qué no usamos para ordenar",
                                List.of(
                                        "No ordenamos por precio, número de reseñas ni estrellas. El orden editorial cubre roles distintos: cooperar, contar, apilar, observar y un cooperativo más largo.",
                                        "Comprueba la ficha vigente en Amazon.es antes de comprar: ediciones y contenidos pueden cambiar."
                                )
                        )
                )),
                List.of(
                        new ComparisonPageResponse.Faq(
                                "¿Qué juegos de mesa son adecuados a los 5 años?",
                                "Los de reglas breves, turnos visibles y poca lectura. Los cooperativos de 10-15 minutos y los de observación o apilamiento suelen encajar mejor que los competitivos con texto o partidas largas."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Cooperativo o competitivo?",
                                "Depende del niño y de la casa. Cooperar evita un ganador individual; competir corto puede funcionar si se tolera perder. En esta lista hay ambos, etiquetados con claridad."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Hace falta que el niño sepa leer?",
                                "No en estos cinco. Hace falta un adulto que explique el objetivo y vigile piezas. Dobble Kids se resuelve señalando un dibujo."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿El Frutal Mini es el mismo que el Frutal grande?",
                                "Comparte la idea (recoger fruta antes de que llegue el cuervo) en formato de viaje: menos piezas y hasta 4 jugadores. No es la caja clásica de hasta 8."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian con frecuencia y no forman parte de esta evaluación editorial. La página compara especificaciones y criterios de uso verificables."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Juguetes y regalos para niños de 5 años",
                                EditorialDefaults.HUB_4_HREF,
                                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
                        ),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para adaptar una elección al desarrollo infantil."
                        ),
                        new LinkItem(
                                "Qué habilidades desarrolla un niño de 5 años",
                                "/guias/habilidades-4-anos/",
                                "Atención, reglas, motricidad fina y juego compartido."
                        )
                ),
                new TrustAuthority(
                        "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y solo mostramos hechos revisados el 17 de agosto de 2026.",
                        List.of(
                                "Edad declarada y encaje con partidas cortas a los 5 años.",
                                "Cooperación o competición, según lo indique el fabricante.",
                                "Carga de lectura y necesidad de un adulto.",
                                "Componentes declarados y avisos de seguridad."
                        ),
                        List.of(
                                "Estado editorial publicado y fecha de revisión visibles.",
                                "Sin importes, valoraciones ni puntuaciones.",
                                "Enlaces de afiliación separados del contenido editorial.",
                                "Fecha de revisión visible."
                        )
                ),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                BOARD_GAMES_PUBLISHED_AT,
                BOARD_GAMES_UPDATED_AT
        );
    }
    private ComparisonPageResponse buildScooters5Page() {
        List<ComparisonPageResponse.Entry> entries = rankedEntries(SCOOTERS, AGE_5);
        return new ComparisonPageResponse(
                new Seo(
                        SCOOTERS_5_CANONICAL,
                        "Mejores patinetes y triciclos para 5 años | Bebes Felices",
                        "Comparamos cuatro patinetes de tres ruedas y un triciclo reales para 5 años por talla, estabilidad y tipo de uso, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                SCOOTERS_5_SLUG,
                AGE_5,
                List.of(
                        new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                        new ComparisonPageResponse.Breadcrumb(
                                EditorialDefaults.HUB_4_LABEL,
                                EditorialDefaults.HUB_4_HREF
                        ),
                        new ComparisonPageResponse.Breadcrumb(
                                "Patinetes y triciclos para 5 años",
                                SCOOTERS_5_CANONICAL
                        )
                ),
                new ComparisonPageResponse.Header(
                        "Mejores patinetes y triciclos para 5 años",
                        "Cinco modelos reales comparados por estabilidad, talla y tipo de uso",
                        List.of(
                                "A los 5 años el patinete de tres ruedas sigue ofreciendo una base más estable que uno de dos ruedas. Un triciclo cubre otra necesidad: pedalear sentado, a veces con mango de adulto. No son intercambiables.",
                                "La selección reúne cuatro patinetes y un triciclo disponibles en Amazon.es. No usamos precios, valoraciones de usuarios ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Casco homologado, calzado cerrado, una zona sin tráfico y supervisión adulta no se sustituyen con tres ruedas, luces LED ni un mango parental. Comprueba siempre las instrucciones y límites del fabricante."
                        )
                ),
                List.of(),
                List.of(
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para empezar de pie con tres ruedas",
                                "patinete-micro-mini-deluxe",
                                "1,95 kg declarados, giro por inclinación y manillar 48-68 cm."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para una primera opción sencilla",
                                "patinete-molto-maxi",
                                "De 3 a 5 años, manillar 57-67 cm y montaje sin herramientas."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para llevar y guardar",
                                "patinete-globber-junior-foldable",
                                "Plegable, bloqueo de dirección y tres alturas de manillar."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para más altura a partir de 5 años",
                                "patinete-globber-master-lights",
                                "Manillar de 74 a 94 cm y plegado desde 5 años."
                        ),
                        new ComparisonPageResponse.QuickSummaryItem(
                                "Para pedalear sentado",
                                "triciclo-chicco-u-go",
                                "Triciclo 2en1 con mango de adulto y hasta 20 kg."
                        )
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 5 años. No asignamos notas numéricas ni completamos datos que el fabricante o la ficha consultada no permitan confirmar.",
                        List.of(
                                new ComparisonPageResponse.Criterion(
                                        "Encaje a los 5 años",
                                        "Revisamos la edad declarada y, cuando está publicada, la altura del manillar. Un modelo desde 5 años con manillar a 74 cm no encaja igual que uno de 48 a 68 cm."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Estabilidad y tipo de uso",
                                        "Separamos patinete de pie (tres ruedas) y triciclo sentado. Tres ruedas aportan base; no equivalen a un patinete de dos ruedas ni a una bicicleta sin pedales."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Manejo y talla",
                                        "Consideramos el peso del patinete cuando está declarado, el recorrido del manillar y la carga máxima. Sin cifra verificada, no la inventamos."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Transporte",
                                        "Valoramos el plegado o el manillar desmontable solo cuando la ficha lo indica. No deducimos que un modelo no se pueda guardar."
                                ),
                                new ComparisonPageResponse.Criterion(
                                        "Seguridad práctica",
                                        "Comprobamos freno, bloqueo de dirección o mango parental cuando están documentados. Ninguno sustituye casco, supervisión y una zona segura."
                                )
                        )
                ),
                entries,
                new ComparisonPageResponse.BuyingGuide(List.of(
                        new ComparisonPageResponse.Section(
                                "Decide primero si va de pie o sentado",
                                List.of(
                                        "El patinete practica equilibrio de pie, dirección y frenado. El triciclo practica pedaleo sentado, a veces con mango de adulto. A los 5 años pueden convivir; no cubren lo mismo.",
                                        "Si el objetivo es moverse de pie al aire libre, empieza por un patinete de tres ruedas con manillar que llegue a sus manos. Si aún necesita que le empujen en paseos largos, el triciclo 2en1 cubre ese tramo."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Mide la altura del manillar",
                                List.of(
                                        "El niño debe alcanzar el manillar sin encogerse ni estirarse en exceso. Contrasta su altura con el rango publicado: 48-68 cm no es intercambiable con 74-94 cm.",
                                        "No elijas solo por edad. El Master Lights está indicado desde 5 años, pero su manillar mínimo es 74 cm; un niño bajo puede encajar mejor en Mini Deluxe o Junior."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Plegado, peso y carga",
                                List.of(
                                        "Si lo vas a meter en el coche o en un ascensor, el plegado con modo carrito (Junior y Master) o el manillar desmontable (Mini Deluxe) cambian el día a día.",
                                        "Revisa la carga máxima. Los patinetes de esta lista declaran 50 kg cuando figura; el U-GO declara 20 kg. Comprueba el peso real del niño antes de comprar el triciclo."
                                )
                        ),
                        new ComparisonPageResponse.Section(
                                "Seguridad antes de salir",
                                List.of(
                                        "Casco homologado, calzado cerrado, freno comprobado y una zona sin tráfico, bordillos altos ni agua. Las luces LED no sustituyen visibilidad ni un adulto atento.",
                                        "El bloqueo de dirección y el mango parental ayudan al aprendizaje; no evitan caídas. Un solo niño por vehículo y según el manual del fabricante."
                                )
                        )
                )),
                List.of(
                        new ComparisonPageResponse.Faq(
                                "¿Patinete o triciclo a los 5 años?",
                                "Depende de si quieres equilibrio de pie o pedaleo sentado. El patinete de tres ruedas es más estable que uno de dos; el triciclo no practica ese equilibrio. En esta lista hay ambos, etiquetados con claridad."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Sigue haciendo falta un patinete de tres ruedas a los 5 años?",
                                "A menudo sí, mientras el equilibrio de pie no esté asentado. Tres ruedas no son un recambio de la bicicleta sin pedales: aquí se está de pie, se dirige y se frena."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿El Globber Master Lights vale para cualquier niño de 5 años?",
                                "Está indicado desde 5 años, pero el manillar parte de 74 cm. Si el niño es bajo, Mini Deluxe LED (48-68 cm) o Junior Foldable (54-68 cm) encajan mejor. Mide antes de decidir."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué el triciclo tiene un límite de 20 kg?",
                                "Porque así lo declara Chicco en el U-GO 2en1. A los 5 años hay que comprobar el peso: 20 kg no es equivalente a los 50 kg de los patinetes de esta comparativa."
                        ),
                        new ComparisonPageResponse.Faq(
                                "¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian con frecuencia y no forman parte de esta evaluación editorial. La página compara especificaciones y criterios de uso verificables."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Juguetes y regalos para niños de 5 años",
                                EditorialDefaults.HUB_4_HREF,
                                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
                        ),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para adaptar una elección al desarrollo infantil."
                        ),
                        new LinkItem(
                                "Bicicletas sin pedales",
                                "/movimiento/bicicletas-sin-pedales/",
                                "El otro eje de movimiento a esta edad: equilibrio sentado."
                        )
                ),
                new TrustAuthority(
                        "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y solo mostramos hechos revisados el 17 de agosto de 2026.",
                        List.of(
                                "Edad declarada y altura de manillar cuando está publicada.",
                                "Uso de pie (patinete) o sentado (triciclo).",
                                "Peso, carga máxima y plegado verificados.",
                                "Freno, bloqueo de dirección o mango parental, sin sustituir casco ni supervisión."
                        ),
                        List.of(
                                "Estado editorial publicado y fecha de revisión visibles.",
                                "Sin importes, valoraciones ni puntuaciones.",
                                "Enlaces de afiliación separados del contenido editorial.",
                                "Fecha de revisión visible."
                        )
                ),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                SCOOTERS_PUBLISHED_AT,
                SCOOTERS_UPDATED_AT
        );
    }
    private ComparisonPageResponse buildTowers5Page() {
        return ageComparison(
                TOWERS_5_SLUG,
                TOWERS_5_CANONICAL,
                AGE_5,
                TOWERS,
                "Mejores torres de aprendizaje para 5 años | Bebes Felices",
                "Comparamos cinco torres de aprendizaje reales para 5 años por estabilidad, altura y plegado, con metodología y afiliación transparentes.",
                "Torres de aprendizaje para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores torres de aprendizaje para 5 años",
                        "Cinco modelos reales comparados por estabilidad, altura y uso en cocina",
                        List.of(
                                "A los 5 años la torre sigue siendo un puesto de colaboración, no un taburete improvisado. Importa que la plataforma llegue a la encimera, que la base no vuelque y que un adulto esté al lado.",
                                "La selección reúne cinco torres disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Nunca junto a fogones, agua hirviendo o cuchillos. Revisa tornillos, huecos para la cabeza y el manual. La certificación no sustituye la supervisión."
                        )
                ),
                "Las cinco torres",
                List.of(
                        summary("Para plegar y convertir en mesa", "torre-yoleo-transformer", "Nogal, pizarra magnética y modo escritorio."),
                        summary("Para tres alturas y poco peso", "torre-hauck-learn-n-explore", "Haya FSC y plataforma de 33 a 45 cm."),
                        summary("Para guardar detrás de la puerta", "torre-costway-plegable", "Plegable desde 3 años, con barra de seguridad."),
                        summary("Para tres alturas con EN-71", "torre-bey-co", "Patas anticaída y superficie antideslizante."),
                        summary("Para torre y escritorio con ajuste", "torre-maxi-cosi-toucan", "Convertible en mesa; 8,7 kg declarados.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 5 años. No asignamos notas ni completamos datos que la ficha no permita confirmar.",
                        List.of(
                                criterion("Encaje a los 5 años", "Revisamos si la altura de plataforma llega a una encimera típica y si el niño puede subir y bajar sin trepar por fuera."),
                                criterion("Estabilidad", "Base, patas anticaída y barandilla cuando están publicadas. Un adulto sigue siendo obligatorio."),
                                criterion("Regulación y plegado", "Tres alturas, conversión a mesa o plegado solo si la ficha lo declara."),
                                criterion("Materiales", "Madera o contrachapado y certificaciones EN-71 cuando figuran."),
                                criterion("Seguridad práctica", "Huecos, bloqueos y avisos del fabricante. Ninguna torre es un juguete de trepa.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Mide la encimera antes", List.of(
                                "La plataforma debe dejar los brazos cómodos sobre la superficie de trabajo, sin que el niño se incline fuera de la barandilla.",
                                "Tres alturas no sirven si la más alta sigue baja para tu cocina. Comprueba centímetros reales."
                        )),
                        section("Plegado o uso fijo", List.of(
                                "Si la cocina es pequeña, una torre plegable (YOLEO o COSTWAY) cambia el día a día.",
                                "Si va a durar años, valora conversión a escritorio cuando deje de usarse en la encimera."
                        )),
                        section("Seguridad de uso", List.of(
                                "Suelo nivelado, lejos del fuego y del agua hirviendo. Un solo niño. Adulto presente.",
                                "Revisa tornillos tras el montaje y periódicamente. No la uses como escalera de adulto y de niño a la vez."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue teniendo sentido una torre a los 5 años?",
                                "Sí, si participa en cocina o lavabo y aún no llega. Si ya alcanza con un taburete estable y hay supervisión, puede no hacer falta."),
                        faqItem("¿Madera o contrachapado?",
                                "Lo decisivo es la estabilidad y los cierres. La haya FSC de hauck y el nogal de YOLEO son materiales distintos; no los ordenamos por precio."),
                        faqItem("¿Cuál se pliega?",
                                "COSTWAY y YOLEO se pliegan. hauck y BEY & CO no declaran plegado."),
                        faqItem("¿Puede usarla solo?",
                                "No. Aunque suba y baje, el riesgo está en la encimera: fuego, cuchillos, agua."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Vajilla infantil para 5 años",
                                "/comparativas/" + TOWERS_5_SLUG + "/",
                                "La otra pieza de autonomía en la rutina diaria."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildTableware5Page() {
        return ageComparison(
                TABLEWARE_5_SLUG,
                TABLEWARE_5_CANONICAL,
                AGE_5,
                TABLEWARE,
                "Mejores vajillas infantiles para 5 años | Bebes Felices",
                "Comparamos cinco opciones reales de vajilla y vasos para 5 años por piezas, estabilidad y cuidado, con metodología y afiliación transparentes.",
                "Vajilla infantil para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores vajillas infantiles para 5 años",
                        "Cinco productos reales para comer y beber con más autonomía",
                        List.of(
                                "A los 5 años el gesto de llevar el plato a la mesa ya cabe. Importa que no se haga añicos, que la base no resbale y que el vaso se pueda coger. Cubiertos y vaso no siempre vienen en el mismo set.",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Revisa el marcado de apto para alimento. Un vaso 360° o una tapa no sustituyen la supervisión con hermanos pequeños."
                        )
                ),
                "Los cinco productos",
                List.of(
                        summary("Para no mezclar la comida", "vajilla-twistshake-dividido", "Tres compartimentos, tapa y base antideslizante."),
                        summary("Para un set de mesa completo", "vajilla-stor-mickey", "Plato, cuenco y vaso de 260 ml."),
                        summary("Para beber sin tetina", "vaso-munchkin-miracle-360", "Dos vasos 360° de 207 ml con asas."),
                        summary("Para plato grande de mesa", "vajilla-fun-house", "Plato de 22 cm, cuenco de 16 cm y vaso de 220 ml."),
                        summary("Para guardar lo que sobra", "cuenco-twistshake-tapa", "Cuenco con tapa, sin ser un set completo.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos especificaciones verificables y su utilidad para un niño de 5 años. No asignamos notas ni completamos volúmenes o materiales que la ficha no declare.",
                        List.of(
                                criterion("Encaje a los 5 años", "Priorizamos piezas que el niño pueda llevar a la mesa familiar, no solo tronas de bebé."),
                                criterion("Estabilidad", "Base antideslizante, asas o vaso bajo cuando están publicados."),
                                criterion("Piezas incluidas", "Dejamos claro si es un set, un plato, un vaso o un cuenco."),
                                criterion("Cuidado", "Microondas y lavavajillas solo si la ficha lo indica."),
                                criterion("Material y alimento", "Libre de BPA u otros avisos cuando figuran. No inventamos composiciones.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Empieza por lo que falta en casa", List.of(
                                "Si ya hay plato y falta vaso, un Miracle 360 cubre el paso a beber sin tetina.",
                                "Si tira el plato, un modelo con base antideslizante o Click-Mat aporta más que un motivo de personaje."
                        )),
                        section("Set o pieza suelta", List.of(
                                "Un set de 3 piezas simplifica el regalo. Un plato con tapa sirve para llevar o guardar.",
                                "Los cubiertos no están en esta lista: añádelos aparte si aún come con cubiertos de adulto demasiado largos."
                        )),
                        section("Limpieza y seguridad", List.of(
                                "Confirma microondas y cubeta del lavavajillas. Las válvulas 360° hay que desmontarlas.",
                                "Retira piezas con recubrimiento deteriorado. El marcado de alimento manda."
                        ))
                )),
                List.of(
                        faqItem("¿Sigue haciendo falta vajilla infantil a los 5 años?",
                                "Sí, si el cristal de adulto se rompe o el vaso es inestable. Si ya come con la vajilla familiar sin incidentes, no es obligatorio."),
                        faqItem("¿Vaso 360° o vaso abierto?",
                                "El 360° reduce derrames y acerca el gesto de un vaso abierto. El vaso de 260 ml del set Stor es abierto de verdad."),
                        faqItem("¿Por qué hay dos Twistshake?",
                                "Cubren roles distintos: plato con compartimentos y cuenco con tapa. No son un set combinado en un solo ASIN."),
                        faqItem("¿Los personajes importan?",
                                "Para esta evaluación, no. El motivo no ordena el ranking; sí advertimos que es un reclamo que puede caducar."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Torres de aprendizaje para 5 años",
                                "/comparativas/" + TABLEWARE_5_SLUG + "/",
                                "Participar en la cocina con una plataforma estable."
                        ),
                        chooseByAgeLink()
                )
        );
    }
    private ComparisonPageResponse buildSustainable5Page() {
        return ageComparison(
                SUSTAINABLE_5_SLUG,
                SUSTAINABLE_5_CANONICAL,
                AGE_5,
                SUSTAINABLE,
                "Mejores regalos sostenibles para 5 años | Bebes Felices",
                "Comparamos cinco regalos reales de madera o plástico reciclado para 5 años, con metodología y afiliación transparentes.",
                "Regalos sostenibles para 5 años",
                new ComparisonPageResponse.Header(
                        "Mejores regalos sostenibles para 5 años",
                        "Cinco productos reales de madera certificada o plástico reciclado",
                        List.of(
                                "Sostenible, aquí, quiere decir materiales declarados (madera, FSC, plástico reciclado) y un uso que no se agota en una tarde. No es un sello único ni una promesa de «cero impacto».",
                                "La selección reúne cinco productos disponibles en Amazon.es. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 17 de agosto de 2026.",
                                "Las cuentas y las figuras pequeñas piden supervisión. Un material responsable no elimina piezas pequeñas."
                        )
                ),
                "Los cinco regalos",
                List.of(
                        summary("Para ensartar y contar", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones."),
                        summary("Para practicar nudos", "plantoys-ata-zapato", "Madera de caucho y tintes al agua."),
                        summary("Para haya y cartón", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas y figuras de madera."),
                        summary("Para madera FSC de obra", "small-foot-grua", "Grúa giratoria certificada FSC 100 %."),
                        summary("Para plástico reciclado lavable", "green-toys-construccion", "Tres vehículos sin BPA ni PVC.")
                ),
                new ComparisonPageResponse.Methodology(
                        "Comparamos materiales y usos verificables para un niño de 5 años. No asignamos una nota de sostenibilidad ni completamos certificaciones que la ficha no muestre.",
                        List.of(
                                criterion("Encaje a los 5 años", "Edad declarada y si el gesto (ensartar, encajar, atar, empujar) cabe en esta etapa."),
                                criterion("Materiales", "Madera, FSC, plástico reciclado, tintes o ausencia de BPA/PVC cuando están publicados."),
                                criterion("Uso real", "Qué se hace con el objeto: no basta con que sea de madera."),
                                criterion("Duración", "Si se puede repetir o guardar. Evitamos kits de un solo uso que no están en esta lista."),
                                criterion("Seguridad", "Piezas pequeñas, cordones y supervisión. El material no elimina el riesgo.")
                        )
                ),
                new ComparisonPageResponse.BuyingGuide(List.of(
                        section("Elige el gesto, no el adjetivo", List.of(
                                "Si quieres motricidad fina, las cuentas. Si quieres autonomía de vestirse, el zapato PlanToys. Si quieres juego simbólico, la grúa o los vehículos."
                        )),
                        section("Madera y plástico reciclado no son intercambiables", List.of(
                                "Green Toys declara plástico 100 % reciclado y se lava en el lavavajillas. Small Foot y PlanToys declaran madera y procesos distintos.",
                                "FSC 100 % no es lo mismo que «madera» a secas. Lo citamos solo cuando figura."
                        )),
                        section("Supervisión", List.of(
                                "Cordones, cuentas y figuras de puzle no se dejan con menores de 3 años.",
                                "Un regalo duradero se usa en sesiones; no tiene por qué entretener dos horas solo."
                        ))
                )),
                List.of(
                        faqItem("¿Qué es un regalo sostenible a los 5 años?",
                                "Uno de materiales declarados y uso repetible. No publicamos una huella de carbono ni un ranking ecológico numérico."),
                        faqItem("¿Madera siempre es mejor que plástico?",
                                "No en abstracto. El plástico reciclado lavable cubre arena y agua; la madera FSC cubre otro tipo de juego. Elige según el uso."),
                        faqItem("¿Las cuentas de Melissa & Doug son un kit de manualidades?",
                                "No. Se ensartan y se cuentan. El análisis genérico de «kit de manualidades» del circuito de 3 años es otra página."),
                        faqItem("¿Hace falta FSC?",
                                "Es un dato útil cuando está. Su ausencia en otra ficha no significa que la madera sea ilegal; simplemente no lo afirmamos."),
                        faqItem("¿Por qué no aparecen importes ni valoraciones?",
                                "Porque cambian y no forman parte de esta evaluación editorial.")
                ),
                List.of(
                        hub5Link(),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/regalos/ideas-regalo-4-anos/",
                                "Selección más amplia por ocasión, no solo materiales."
                        ),
                        chooseByAgeLink()
                )
        );
    }

    private ComparisonPageResponse ageComparison(
            String slug,
            String canonical,
            int age,
            List<EditorialEntry> editorial,
            String seoTitle,
            String seoDescription,
            String breadcrumbLabel,
            ComparisonPageResponse.Header header,
            @SuppressWarnings("unused") String comparativaNavLabel,
            List<ComparisonPageResponse.QuickSummaryItem> quickSummary,
            ComparisonPageResponse.Methodology methodology,
            ComparisonPageResponse.BuyingGuide buyingGuide,
            List<ComparisonPageResponse.Faq> faq,
            List<LinkItem> relatedLinks
    ) {
        String publishedAt = switch (age) {
            case AGE_3 -> THREE_YEAR_PUBLISHED_AT;
            case AGE_5 -> FIVE_YEAR_PUBLISHED_AT;
            default -> FOUR_YEAR_PUBLISHED_AT;
        };
        String updatedAt = switch (age) {
            case AGE_3 -> THREE_YEAR_UPDATED_AT;
            case AGE_5 -> FIVE_YEAR_UPDATED_AT;
            default -> FOUR_YEAR_UPDATED_AT;
        };
        return new ComparisonPageResponse(
                new Seo(canonical, seoTitle, seoDescription),
                PageStatus.PUBLISHED,
                slug,
                age,
                List.of(
                        new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                        new ComparisonPageResponse.Breadcrumb(
                                EditorialDefaults.hubLabel(age),
                                EditorialDefaults.hubHref(age)
                        ),
                        new ComparisonPageResponse.Breadcrumb(breadcrumbLabel, canonical)
                ),
                header,
                List.of(),
                quickSummary,
                methodology,
                rankedEntries(editorial, age),
                buyingGuide,
                faq,
                relatedLinks,
                new TrustAuthority(
                        "La selección parte de productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y evitamos datos comerciales no verificables.",
                        List.of(
                                "Edad o uso declarado para " + age + " años.",
                                "Especificaciones de la ficha consultada.",
                                "Materiales y certificaciones solo cuando figuran.",
                                "Límites de seguridad sin sustituir la supervisión."
                        ),
                        List.of(
                                "Estado editorial publicado y fecha de revisión visibles.",
                                "Sin importes, valoraciones ni puntuaciones.",
                                "Enlaces de afiliación separados del contenido editorial.",
                                "Fecha de revisión visible."
                        )
                ),
                affiliation(),
                legalLinks(),
                new ComparisonPageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                publishedAt,
                updatedAt
        );
    }

    private static ComparisonPageResponse.QuickSummaryItem summary(String label, String productId, String text) {
        return new ComparisonPageResponse.QuickSummaryItem(label, productId, text);
    }

    private static ComparisonPageResponse.Criterion criterion(String name, String description) {
        return new ComparisonPageResponse.Criterion(name, description);
    }

    private static ComparisonPageResponse.Section section(String title, List<String> paragraphs) {
        return new ComparisonPageResponse.Section(title, paragraphs);
    }

    private static ComparisonPageResponse.Faq faqItem(String question, String answer) {
        return new ComparisonPageResponse.Faq(question, answer);
    }

    private static LinkItem hub4Link() {
        return new LinkItem(
                "Juguetes y regalos para niños de 4 años",
                EditorialDefaults.HUB_4_HREF,
                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
        );
    }

    private static LinkItem hub5Link() {
        return new LinkItem(
                "Juguetes y regalos para niños de 5 años",
                EditorialDefaults.hubHref(5),
                "Página por edad con propuestas de aprendizaje, movimiento y autonomía."
        );
    }

    private static LinkItem hub3Link() {
        return new LinkItem(
                "Juguetes y regalos para niños de 3 años",
                EditorialDefaults.HUB_3_HREF,
                "Página por edad con propuestas de movimiento, aprendizaje y autonomía."
        );
    }

    private static LinkItem chooseByAgeLink() {
        return new LinkItem(
                "Cómo elegir juguetes según la edad",
                "/guias/como-elegir-juguetes-por-edad/",
                "Criterios prácticos para adaptar una elección al desarrollo infantil."
        );
    }

    private List<ComparisonPageResponse.Entry> rankedEntries(List<EditorialEntry> editorial, int age) {
        Map<String, EditorialEntry> editorialById = new LinkedHashMap<>();
        editorial.forEach(entry -> editorialById.put(entry.productId(), entry));
        AtomicInteger rank = new AtomicInteger(1);
        return productCatalog
                .findByIds(editorial.stream().map(EditorialEntry::productId).toList())
                .stream()
                .filter(product -> product.isAvailableForAge(age))
                .map(product -> toResponseEntry(
                        rank.getAndIncrement(),
                        product,
                        editorialById.get(product.id())
                ))
                .toList();
    }

    private ComparisonPageResponse.Entry toResponseEntry(
            int rank,
            Product product,
            EditorialEntry editorial
    ) {
        String affiliateHref = product.hasValidatedAffiliateLink()
                ? product.affiliateLink().url()
                : null;
        return new ComparisonPageResponse.Entry(
                rank,
                product.id(),
                editorial.title(),
                editorial.bestFor(),
                editorial.editorialSummary(),
                editorial.pros(),
                editorial.cons(),
                editorial.ageRange(),
                editorial.criteriaNotes(),
                affiliateHref
        );
    }

    private List<ComparisonPageResponse.Breadcrumb> breadcrumbs() {
        return List.of(
                new ComparisonPageResponse.Breadcrumb("Inicio", "/"),
                new ComparisonPageResponse.Breadcrumb("3 años", "/por-edad/3-anos/"),
                new ComparisonPageResponse.Breadcrumb(
                        "Bicicletas sin pedales para 3 años",
                        BALANCE_BIKES_CANONICAL
                )
        );
    }

    private List<ComparisonPageResponse.QuickSummaryItem> quickSummary() {
        return List.of(
                new ComparisonPageResponse.QuickSummaryItem(
                        "Para empezar con una opción sencilla",
                        "bici-chicco-red-bullet",
                        "Ajuste de sillín y manillar con ruedas antipinchazos."
                ),
                new ComparisonPageResponse.QuickSummaryItem(
                        "Para priorizar ligereza",
                        "bici-kinderkraft-tove",
                        "Peso declarado de 2 kg y sillín bajo."
                ),
                new ComparisonPageResponse.QuickSummaryItem(
                        "Para un recorrido amplio de sillín",
                        "bici-kinderkraft-fly-plus-2",
                        "Regulación de 34 a 42 cm y ruedas de espuma de 30 cm."
                ),
                new ComparisonPageResponse.QuickSummaryItem(
                        "Para superficies variadas",
                        "bici-kinderkraft-goswift",
                        "Ruedas inflables de 30 cm y cuadro de magnesio."
                ),
                new ComparisonPageResponse.QuickSummaryItem(
                        "Para postura ajustable y reposapiés",
                        "bici-puky-lr-m",
                        "Sillín y manillar ajustables con ruedas sin aire."
                )
        );
    }

    private ComparisonPageResponse.Methodology methodology() {
        return new ComparisonPageResponse.Methodology(
                "Comparamos especificaciones verificables y su utilidad para un niño de 3 años. No asignamos notas numéricas ni completamos datos que el fabricante o la ficha consultada no permitan confirmar.",
                List.of(
                        new ComparisonPageResponse.Criterion(
                                "Ajuste y talla",
                                "Revisamos la edad declarada y, cuando está disponible, el recorrido del sillín y el ajuste del manillar. La medida de entrepierna sigue siendo más útil que la edad por sí sola."
                        ),
                        new ComparisonPageResponse.Criterion(
                                "Facilidad de manejo",
                                "Consideramos el peso declarado, el control de giro y la capacidad del niño para levantar y dirigir la bicicleta."
                        ),
                        new ComparisonPageResponse.Criterion(
                                "Ruedas y terreno",
                                "Distinguimos entre ruedas EVA, espuma, sin aire e inflables porque cambian el mantenimiento y el comportamiento sobre firme irregular."
                        ),
                        new ComparisonPageResponse.Criterion(
                                "Uso evolutivo",
                                "Valoramos los ajustes conocidos y los límites declarados sin deducir rangos que no estén verificados."
                        ),
                        new ComparisonPageResponse.Criterion(
                                "Seguridad práctica",
                                "Comprobamos límites de carga y elementos de control publicados, recordando que ninguno sustituye casco, supervisión y una zona segura."
                        )
                )
        );
    }

    private ComparisonPageResponse.BuyingGuide buyingGuide() {
        return new ComparisonPageResponse.BuyingGuide(List.of(
                new ComparisonPageResponse.Section(
                        "Empieza por la altura del sillín",
                        List.of(
                                "El niño debe poder sentarse y apoyar ambos pies en el suelo con control. Mide la entrepierna con el calzado que utilizará y contrástala con la altura mínima del sillín.",
                                "No elijas solo por edad: dos niños de 3 años pueden necesitar ajustes distintos. Si no hay medidas verificadas, confírmalas en la ficha vigente antes de comprar."
                        )
                ),
                new ComparisonPageResponse.Section(
                        "Peso y control",
                        List.of(
                                "Un modelo ligero suele ser más fácil de levantar, girar y transportar. El peso no decide por sí solo: también cuentan la postura, la geometría y la confianza del niño.",
                                "Los límites de giro pueden suavizar movimientos bruscos durante el aprendizaje, pero no evitan caídas ni reemplazan la supervisión."
                        )
                ),
                new ComparisonPageResponse.Section(
                        "Elige las ruedas según el terreno",
                        List.of(
                                "EVA, espuma y ruedas sin aire reducen el mantenimiento y eliminan los pinchazos. Son prácticas para paseos habituales sobre suelo regular.",
                                "Las ruedas inflables requieren controlar la presión y pueden pincharse, pero resultan una opción a considerar cuando se circula por superficies variadas."
                        )
                ),
                new ComparisonPageResponse.Section(
                        "Seguridad antes de salir",
                        List.of(
                                "Ajusta sillín y manillar siguiendo el manual, revisa cierres y estado de las ruedas, y utiliza casco homologado y calzado cerrado.",
                                "Practica lejos de tráfico, escaleras, pendientes pronunciadas y agua. La persona adulta debe mantener supervisión activa."
                        )
                )
        ));
    }

    private List<ComparisonPageResponse.Faq> faq() {
        return List.of(
                new ComparisonPageResponse.Faq(
                        "¿Qué altura debe tener el sillín para un niño de 3 años?",
                        "Debe permitir apoyar ambos pies en el suelo con seguridad. Mide la entrepierna y comprueba la altura mínima real del modelo; la edad no basta para decidir."
                ),
                new ComparisonPageResponse.Faq(
                        "¿Son mejores las ruedas inflables o las que no necesitan aire?",
                        "Depende del uso. Las que no necesitan aire evitan pinchazos y mantenimiento; las inflables exigen revisar la presión y pueden resultar adecuadas para firmes más variados."
                ),
                new ComparisonPageResponse.Faq(
                        "¿Una bicicleta ligera siempre es la mejor opción?",
                        "La ligereza facilita el manejo, pero también deben encajar la altura del sillín, la postura, las ruedas y el terreno habitual."
                ),
                new ComparisonPageResponse.Faq(
                        "¿Necesita casco aunque la bicicleta no tenga pedales?",
                        "Sí. También necesita calzado cerrado, una zona sin tráfico y supervisión adulta constante."
                ),
                new ComparisonPageResponse.Faq(
                        "¿Por qué no aparecen importes ni valoraciones?",
                        "Porque cambian con frecuencia y no forman parte de esta evaluación editorial. La página compara especificaciones y criterios de uso verificables."
                )
        );
    }

    private List<LinkItem> relatedLinks() {
        return List.of(
                new LinkItem(
                        "Juguetes y regalos para niños de 3 años",
                        "/por-edad/3-anos/",
                        "Página por edad con propuestas de movimiento, aprendizaje y autonomía."
                ),
                new LinkItem(
                        "Cómo elegir juguetes según la edad",
                        "/guias/como-elegir-juguetes-por-edad/",
                        "Criterios prácticos para adaptar una elección al desarrollo infantil."
                ),
                new LinkItem(
                        "Bicicletas sin pedales",
                        "/movimiento/bicicletas-sin-pedales/",
                        "Información general sobre ajuste, aprendizaje y seguridad."
                )
        );
    }

    private TrustAuthority trustAuthority() {
        return new TrustAuthority(
                "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y solo mostramos hechos revisados el 13 de agosto de 2026.",
                List.of(
                        "Aptitud declarada para la edad objetivo.",
                        "Ajuste de sillín y manillar cuando está documentado.",
                        "Peso, tipo de rueda y elementos de manejo verificados.",
                        "Límites declarados y seguridad de uso."
                ),
                List.of(
                        "Estado editorial publicado y fecha de revisión visibles.",
                        "Sin importes, valoraciones ni puntuaciones.",
                        "Enlaces de afiliación separados del contenido editorial.",
                        "Fecha de revisión visible."
                )
        );
    }

    private Affiliation affiliation() {
        return new Affiliation(
                "Bebes Felices participa en el Programa de Afiliados de Amazon. Si compras mediante un enlace de esta página, podemos recibir una comisión sin coste adicional para ti. La afiliación no modifica el orden ni el contenido editorial.",
                "Algunos enlaces pueden ser de afiliado de Amazon."
        );
    }

    private List<LegalLink> legalLinks() {
        return List.of(
                new LegalLink("Aviso legal", "/aviso-legal/"),
                new LegalLink("Política de privacidad", "/politica-privacidad/"),
                new LegalLink("Política de cookies", "/politica-cookies/"),
                new LegalLink("Condiciones de uso", "/condiciones-uso/"),
                new LegalLink("Información sobre afiliación", "/informacion-afiliacion/")
        );
    }

    private static ComparisonPageResponse.CriterionNote note(String criterion, String note) {
        return new ComparisonPageResponse.CriterionNote(criterion, note);
    }

    private static EditorialEntry entry(
            String productId,
            String title,
            String bestFor,
            String editorialSummary,
            List<String> pros,
            List<String> cons,
            String ageRange,
            ComparisonPageResponse.CriterionNote... notes
    ) {
        return new EditorialEntry(
                productId,
                title,
                bestFor,
                editorialSummary,
                pros,
                cons,
                ageRange,
                List.of(notes)
        );
    }

    private record EditorialEntry(
            String productId,
            String title,
            String bestFor,
            String editorialSummary,
            List<String> pros,
            List<String> cons,
            String ageRange,
            List<ComparisonPageResponse.CriterionNote> criteriaNotes
    ) {
    }
}
