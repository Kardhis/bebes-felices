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
    private static final int BALANCE_BIKES_AGE = 3;
    private static final int BOARD_GAMES_AGE = 4;
    private static final int SCOOTERS_AGE = 4;
    private static final int TOWERS_AGE = 4;
    private static final int TABLEWARE_AGE = 4;
    private static final int SUSTAINABLE_AGE = 4;
    private static final int STEM_5_AGE = 5;
    private static final int AGE_3 = 3;
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
                    "torre-bianconiglio-evo",
                    "Bianconiglio Kids EVO",
                    "Tres alturas en una torre ligera",
                    "Torre clásica de abedul fabricada en Italia, de 40 x 42 x 88 cm y 7,5 kg, con plataforma en 3 alturas. El sistema anticaídas KidSafe es un accesorio, no siempre incluido.",
                    List.of(
                            "Tres alturas de plataforma (30, 40 y 50 cm en las fichas EVO).",
                            "Peso declarado de 7,5 kg, más fácil de desplazar que modelos de 10 kg.",
                            "Certificación EN-71 y bordes redondeados según el fabricante.",
                            "Compatible con el accesorio KidSafe."
                    ),
                    List.of(
                            "KidSafe y las extensiones laterales se venden aparte en varias fichas.",
                            "Otras variantes EVO declaran 40 kg de carga; comprueba la ficha vigente de tu color."
                    ),
                    "Hasta unos 6-7 años",
                    List.of(
                            note("Encaje a los 4 años", "Las tres alturas cubren el crecimiento; mide tu encimera."),
                            note("Estabilidad", "Diseño antitrepa y barandilla; KidSafe es opcional."),
                            note("Regulación", "Tres posiciones de plataforma."),
                            note("Seguridad", "Uso solo con adulto; el fabricante pide supervisión constante.")
                    )
            ),
            new EditorialEntry(
                    "torre-kleiner-riese",
                    "Kleiner Riese 4 en 1",
                    "Plegar y guardar detrás de una puerta",
                    "Torre plegable de 6,5 kg en contrachapado de abedul de 18 mm, con barra NFS, asas para llevarla y uso como taburete de adulto hasta 80 kg. La ficha la recomienda a partir de 3 años.",
                    List.of(
                            "Peso declarado de 6,5 kg y plegado a 91 x 45,5 x 29 cm.",
                            "Barra de seguridad NFS y barniz EN 71-3.",
                            "Contrachapado de abedul de 18 mm.",
                            "También como taburete o escalón de adulto hasta 80 kg."
                    ),
                    List.of(
                            "Plegada sigue midiendo 91 cm de alto: cabe detrás de una puerta, no en un cajón.",
                            "El uso como escalón de adulto no es el uso infantil; no mezcles ambos a la vez."
                    ),
                    "Recomendada desde 3 años",
                    List.of(
                            note("Encaje a los 4 años", "La ficha la recomienda a partir de 3 años."),
                            note("Estabilidad", "Barra NFS y tablero de 18 mm."),
                            note("Regulación", "Plegado para guardar; confirma la altura de plataforma en tu unidad."),
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
                    "torre-bianconiglio-transformer",
                    "Bianconiglio Kids Transformer Ajustable",
                    "Convertir la torre en escritorio con ajuste de altura",
                    "Torre convertible en mesa, reposapiés y escritorio, con ajuste de altura, 45 x 41 x 90 cm y 10 kg, fabricada en Italia. Distinta de la EVO: aquí el reclamo es el cambio de uso.",
                    List.of(
                            "Se transforma en mesa, reposapiés y escritorio.",
                            "Ajuste de altura según la ficha.",
                            "Fabricación artesanal en Italia.",
                            "Dimensiones 45 x 41 x 90 cm y 10 kg declarados."
                    ),
                    List.of(
                            "Con 10 kg es más pesada que la EVO de 7,5 kg.",
                            "Comprueba qué incluye tu color: algunas versiones añaden pizarra."
                    ),
                    "Uso infantil con adulto",
                    List.of(
                            note("Encaje a los 4 años", "El escritorio alarga el uso cuando la torre de cocina se queda corta."),
                            note("Estabilidad", "Base de 45 x 41 cm; monta según el manual."),
                            note("Regulación", "Ajuste de altura y modos torre o mesa."),
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
                    "Juego con plato de 22 cm, cuenco de 16 cm y vaso de 220 ml, reutilizable y apto para microondas.",
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
                    "juego-montessori-formas",
                    "Melissa & Doug cubo de formas",
                    "Clasificar formas y colores con piezas grandes",
                    "Cubo de madera con 12 piezas grandes para encajar por la forma. A los 3 años la actividad es evidente: coger, nombrar y meter. No hace falta un reglamento.",
                    List.of(
                            "Doce piezas grandes, pensadas para manos pequeñas.",
                            "Clasificación de formas y colores en un solo objeto.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "A los 4 años el cubo puede quedarse corto si ya clasifica sin esfuerzo.",
                            "No incluye un segundo nivel de dificultad en la misma caja."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Edad declarada hasta 4 años; las piezas grandes evitan frustración inicial."),
                    note("Actividad", "Encajar y nombrar; una sola consigna clara."),
                    note("Piezas", "12 piezas grandes de madera."),
                    note("Seguridad", "Supervisión cercana; no es un juguete para dejar solo.")
            ),
            entry(
                    "montessori-goula-baby-shapes",
                    "Goula Baby Shapes",
                    "Aumentar la dificultad sin cambiar de juguete",
                    "Láminas y piezas de madera para encajar formas y colores con dificultad progresiva. A los 3 años permite empezar fácil y añadir láminas cuando el cubo ya no reta.",
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
                    note("Encaje a los 3 años", "Cabe en 3 años y admite crecer hasta 5."),
                    note("Actividad", "Encaje con niveles, no un cubo único."),
                    note("Piezas", "Láminas y piezas de madera."),
                    note("Seguridad", "Revisa que no falten piezas pequeñas sueltas.")
            ),
            entry(
                    "montessori-formas-geometricas",
                    "Melissa & Doug puzzle de formas geométricas",
                    "Reconocer ocho formas gruesas",
                    "Ocho piezas gruesas de madera para reconocer formas y colores. Menos piezas que el cubo de 12: se termina en una sesión corta.",
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
                    note("Encaje a los 3 años", "Pocas piezas gruesas, fáciles de completar."),
                    note("Actividad", "Encajar formas, no montar una escena."),
                    note("Piezas", "Ocho piezas de madera."),
                    note("Seguridad", "Piezas gruesas; supervisión si hay hermanos más pequeños.")
            ),
            entry(
                    "montessori-noah-ark",
                    "Melissa & Doug Arca de Noé clasificadora",
                    "Clasificar animales por la forma",
                    "Arca de madera con 26 piezas de animales para clasificar por forma. A los 3 años pide más vocabulario (nombres de animales) y más recogida al terminar.",
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
                    note("Encaje a los 3 años", "Útil si ya encaja formas simples y quiere nombrar animales."),
                    note("Actividad", "Clasificar por silueta, no solo encajar un cubo."),
                    note("Piezas", "26 figuras; saca un subconjunto al inicio."),
                    note("Seguridad", "Comprueba que ninguna figura sea demasiado pequeña.")
            ),
            entry(
                    "montessori-janod-tropik",
                    "Janod Mis primeras formas Tropik",
                    "Un primer encaje de madera FSC",
                    "Soporte de madera FSC con piezas para encajar formas y colores. El rango llega hasta los 3 años: encaja como primer material, no como reto que dure todo el curso.",
                    List.of(
                            "Madera FSC declarada en la ficha.",
                            "Encaje de formas y colores en un soporte único.",
                            "Indicada de 1 a 3 años."
                    ),
                    List.of(
                            "El máximo declarado es 3 años: si ya clasifica con soltura, Goula o el cubo dan más margen.",
                            "Menos piezas que el arca o el cubo de 12."
                    ),
                    "1-3 años",
                    note("Encaje a los 3 años", "Está en el tope del rango; sirve si aún empieza a encajar."),
                    note("Actividad", "Primeras formas en un soporte fijo."),
                    note("Materiales", "Madera FSC según la ficha."),
                    note("Seguridad", "Supervisión; no lo dejes con un bebé que se lleve piezas a la boca sin control.")
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
                    "puzle-melissa-granja",
                    "Melissa & Doug puzle de animales domésticos",
                    "Encajar y oír el animal",
                    "Tablero de madera con piezas grandes y sonidos para reconocer animales. A los 3 años el sonido refuerza el nombre; no sustituye terminar el encaje.",
                    List.of(
                            "Piezas grandes sobre tablero.",
                            "Sonidos para reconocer animales domésticos.",
                            "Rango declarado de 2 a 4 años."
                    ),
                    List.of(
                            "El máximo declarado es 4 años.",
                            "Los sonidos piden pilas o un mecanismo; revisa el estado antes de regalarlo."
                    ),
                    "2-4 años",
                    note("Encaje a los 3 años", "Tablero y piezas grandes, con un extra de vocabulario."),
                    note("Dificultad", "Encaje guiado por silueta, no un puzle de 16 piezas sueltas."),
                    note("Soporte", "Tablero de madera que sujeta las piezas."),
                    note("Seguridad", "El mecanismo de sonido no se desmonta; supervisión si se golpea.")
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
                            "La ficha consultada no declara plegado ni el peso del patinete."
                    ),
                    "3-5 años",
                    note("Encaje a los 3 años", "La edad mínima declarada es 3 años."),
                    note("Estabilidad", "Tres ruedas y plataforma antideslizante."),
                    note("Manejo", "Freno trasero; no hay peso declarado en la ficha."),
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
                    "torre-kleiner-riese",
                    "Kleiner Riese 4 en 1",
                    "Plegar y guardar en una cocina pequeña",
                    "Torre plegable de 6,5 kg en contrachapado de abedul de 18 mm, con barra NFS. La ficha la recomienda a partir de 3 años: encaje directo con esta página.",
                    List.of(
                            "Recomendada a partir de 3 años en la ficha.",
                            "Peso declarado de 6,5 kg y plegado a 91 x 45,5 x 29 cm.",
                            "Barra de seguridad NFS y barniz EN 71-3.",
                            "Contrachapado de abedul de 18 mm."
                    ),
                    List.of(
                            "Plegada sigue midiendo 91 cm de alto.",
                            "El uso como escalón de adulto (hasta 80 kg) no se mezcla con el uso infantil."
                    ),
                    "Recomendada desde 3 años",
                    note("Encaje a los 3 años", "La ficha la recomienda a partir de 3 años."),
                    note("Estabilidad", "Barra NFS y tablero de 18 mm."),
                    note("Regulación", "Plegado para guardar; confirma la altura de plataforma."),
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
                    "torre-bianconiglio-evo",
                    "Bianconiglio Kids EVO",
                    "Tres alturas en una torre ligera",
                    "Torre de abedul de 40 x 42 x 88 cm y 7,5 kg, con plataforma en 3 alturas (30, 40 y 50 cm). A los 3 años suele usarse la posición intermedia; KidSafe es un accesorio.",
                    List.of(
                            "Tres alturas de plataforma.",
                            "Peso declarado de 7,5 kg.",
                            "Certificación EN-71 y bordes redondeados según el fabricante."
                    ),
                    List.of(
                            "KidSafe se vende aparte en varias fichas.",
                            "Comprueba la carga máxima de tu color vigente."
                    ),
                    "Hasta unos 6-7 años",
                    note("Encaje a los 3 años", "Empieza por 30 o 40 cm; no dejes la más alta si aún trepa por fuera."),
                    note("Estabilidad", "Barandilla; KidSafe es opcional."),
                    note("Regulación", "Tres posiciones de plataforma."),
                    note("Seguridad", "El fabricante pide supervisión constante.")
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
                    "torre-bianconiglio-transformer",
                    "Bianconiglio Kids Transformer Ajustable",
                    "Torre convertible en escritorio",
                    "Torre convertible en mesa, reposapiés y escritorio, de 45 x 41 x 90 cm y 10 kg, fabricada en Italia. A los 3 años pesa más de desplazar que la EVO de 7,5 kg.",
                    List.of(
                            "Conversión a mesa y escritorio.",
                            "Ajuste de altura.",
                            "Fabricada en Italia; 10 kg declarados."
                    ),
                    List.of(
                            "Con 10 kg es más pesada que Kleiner Riese (6,5 kg) o EVO (7,5 kg).",
                            "El modo escritorio no se usa a los 3 años: es margen futuro."
                    ),
                    "Uso infantil con adulto",
                    note("Encaje a los 3 años", "Sirve si la altura de torre llega a tu encimera."),
                    note("Estabilidad", "Estructura más pesada; confirma bloqueos al convertir."),
                    note("Regulación", "Torre, mesa y escritorio."),
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
                    "Juego con plato de 22 cm, cuenco de 16 cm y vaso de 220 ml, reutilizable y apto para microondas. El plato es de mesa, no de aprendizaje de bebé.",
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
                    "torre-kleiner-riese",
                    "Kleiner Riese 4 en 1",
                    "Un regalo de autonomía para la cocina",
                    "Torre recomendada a partir de 3 años, plegable, de 6,5 kg. Tiene sentido si la familia cocina con el niño y hay un adulto dispuesto a estar al lado.",
                    List.of(
                            "Ficha recomendada desde 3 años.",
                            "Plegado para cocinas pequeñas.",
                            "Barra NFS y tablero de 18 mm."
                    ),
                    List.of(
                            "No es un juguete: sin supervisión no se regala.",
                            "Ocupa sitio aunque se pliegue."
                    ),
                    "Recomendada desde 3 años",
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

    private static final List<EditorialEntry> SUSTAINABLE_3 = List.of(
            entry(
                    "kit-manualidades-natural",
                    "Kit de manualidades con materiales naturales",
                    "Crear con plastilina ecológica y madera",
                    "SES Creative Eco: tres potes de plastilina de materias primas naturales y herramientas de madera, a partir de 3 años. Sostenible, aquí, es material declarado y uso repetible, no un sello que no podamos verificar.",
                    List.of(
                            "Materias primas naturales y herramientas de madera según la ficha.",
                            "Uso sin electrónica.",
                            "Edad declarada a partir de 3 años."
                    ),
                    List.of(
                            "La plastilina se gasta; no es un objeto de madera permanente.",
                            "Hace falta un adulto para el primer uso."
                    ),
                    "Desde 3 años",
                    note("Encaje a los 3 años", "Edad mínima 3 años; sesiones cortas con un adulto."),
                    note("Materiales", "Plastilina ecológica y herramientas de madera."),
                    note("Uso", "Modelar en mesa, no juego de exterior."),
                    note("Duración", "Se puede repetir mientras quede material.")
            ),
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
                        summary("Para tres alturas y poco peso", "torre-bianconiglio-evo", "7,5 kg y plataforma a 30, 40 y 50 cm."),
                        summary("Para guardar detrás de la puerta", "torre-kleiner-riese", "6,5 kg, plegado y barra NFS."),
                        summary("Para tres alturas con EN-71", "torre-bey-co", "Patas anticaída y superficie antideslizante."),
                        summary("Para torre y escritorio con ajuste", "torre-bianconiglio-transformer", "Convertible en mesa; 10 kg declarados.")
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
                                "Si la cocina es pequeña, una torre plegable (YOLEO o Kleiner Riese) cambia el día a día.",
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
                                "Lo decisivo es la estabilidad y los cierres. El abedul de 18 mm y el nogal de esta lista son materiales distintos; no los ordenamos por precio."),
                        faqItem("¿El sistema anticaídas viene de serie?",
                                "En la EVO, KidSafe es un accesorio en varias fichas. No lo des por incluido."),
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
                        summary("Para empezar con piezas grandes", "juego-montessori-formas", "Cubo de 12 piezas y una sola consigna."),
                        summary("Para subir la dificultad", "montessori-goula-baby-shapes", "Láminas progresivas de 2 a 5 años."),
                        summary("Para ocho formas gruesas", "montessori-formas-geometricas", "Se termina en una sesión corta."),
                        summary("Para clasificar animales", "montessori-noah-ark", "26 piezas; saca un subconjunto al inicio."),
                        summary("Para un primer encaje FSC", "montessori-janod-tropik", "Rango hasta 3 años; primer material.")
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
                                "Si aún encaja con ayuda, el cubo de 12 piezas o Janod bastan. Si ya clasifica sin esfuerzo, Goula o el arca dan más margen.",
                                "No elijas por el sello Montessori. Elige por si el niño puede completar una ronda hoy."
                        )),
                        section("Cuántas piezas sacar", List.of(
                                "Con 26 figuras, saca cuatro o cinco al empezar. El arca guarda el resto.",
                                "Mezclar todas las láminas de Goula a la vez convierte el reto en un desorden."
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
                                "Si no puede terminar una ronda, sobran. Empieza por un cubo reducido y añade dificultad después."),
                        faqItem("¿Janod sirve si ya tiene 3 años cumplidos?",
                                "Está en el tope de su rango. Si ya clasifica con soltura, Goula o el cubo de 12 dan más recorrido."),
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
                        summary("Para encajar con sonido", "puzle-melissa-granja", "Tablero de siluetas y animales domésticos."),
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
                                "Si aún encaja siluetas, granja o safari. Si termina 8 piezas en un minuto, HABA o Educa Disney.",
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
                        faqItem("¿El puzle de granja es lo mismo que el de safari?",
                                "No. La granja es tablero con sonido; el safari es una escena de piezas grandes. Cubren el mismo tramo de edad con gestos distintos."),
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
                "Comparamos tres patinetes de tres ruedas y un triciclo reales para 3 años por talla, estabilidad y tipo de uso, con metodología y afiliación transparentes.",
                "Patinetes para 3 años",
                new ComparisonPageResponse.Header(
                        "Mejores patinetes de 3 ruedas para 3 años",
                        "Cuatro modelos reales comparados por estabilidad, talla y tipo de uso",
                        List.of(
                                "A los 3 años el equilibrio de pie todavía se está asentando. Un patinete de tres ruedas ofrece una base más estable que uno de dos. Un triciclo cubre otra necesidad: pedalear sentado, a veces con mango de adulto.",
                                "La selección reúne tres patinetes y un triciclo disponibles en Amazon.es. No incluimos el Globber Master Lights, indicado desde 4 años con manillar desde 74 cm. No usamos precios, valoraciones ni puntuaciones; el orden es editorial y parte de datos revisados el 26 de agosto de 2026.",
                                "Casco homologado, calzado cerrado, una zona sin tráfico y supervisión adulta no se sustituyen con tres ruedas ni con luces LED."
                        )
                ),
                "Los cuatro modelos",
                List.of(
                        summary("Para empezar de pie con poco peso", "patinete-micro-mini-deluxe", "1,95 kg y manillar 48-68 cm."),
                        summary("Para una primera opción de 3 a 5 años", "patinete-molto-maxi", "Manillar 57-67 cm y montaje sin herramientas."),
                        summary("Para plegar y llevar", "patinete-globber-junior-foldable", "Tres alturas y modo carrito."),
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
                                "Si el niño es bajo, Mini Deluxe o Junior (54 cm mínimo) encajan mejor que Molto (57 cm)."
                        )),
                        section("Seguridad antes de salir", List.of(
                                "Casco, calzado cerrado, freno comprobado y una zona sin tráfico.",
                                "El U-GO declara 20 kg: comprueba el peso real antes de elegir el triciclo."
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
                        summary("Para plegar desde 3 años", "torre-kleiner-riese", "6,5 kg y ficha recomendada a partir de 3 años."),
                        summary("Para plegar y convertir en mesa", "torre-yoleo-transformer", "Nogal y modo escritorio más adelante."),
                        summary("Para tres alturas y poco peso", "torre-bianconiglio-evo", "7,5 kg y plataforma a 30, 40 y 50 cm."),
                        summary("Para patas anticaída y EN-71", "torre-bey-co", "Tres alturas y superficie antideslizante."),
                        summary("Para torre y escritorio", "torre-bianconiglio-transformer", "Convertible; 10 kg declarados.")
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
                                "Si la cocina es pequeña, Kleiner Riese o YOLEO cambian el día a día.",
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
                        faqItem("¿Por qué Kleiner Riese va primero?",
                                "Porque la ficha la recomienda a partir de 3 años y se pliega. El orden cubre roles, no una nota numérica."),
                        faqItem("¿El niño puede usarla solo?",
                                "No. Aunque suba y baje, el riesgo está en la encimera."),
                        faqItem("¿KidSafe viene de serie en la EVO?",
                                "En varias fichas es un accesorio. No lo des por incluido."),
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
                        summary("Para la cocina", "torre-kleiner-riese", "Torre plegable recomendada desde 3 años."),
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
                        summary("Para crear sin pantallas", "kit-manualidades-natural", "Plastilina ecológica y herramientas de madera."),
                        summary("Para ensartar y contar", "cuentas-melissa-doug", "27 cuentas de madera y 2 cordones."),
                        summary("Para madera FSC de obra", "small-foot-grua", "Grúa giratoria certificada FSC 100 %."),
                        summary("Para plástico reciclado lavable", "green-toys-construccion", "Tres vehículos sin BPA ni PVC."),
                        summary("Para cartón y haya", "haba-puzles-cuatro-estaciones", "Cuatro puzles de 15 piezas.")
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
                                "Crear: el kit. Ensartar: las cuentas. Obra: Small Foot. Empujar: Green Toys. Encajar: HABA."
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
                                "En el catálogo constan plastilina ecológica de materias primas naturales y herramientas de madera. No publicamos un sello concreto que no figure en ficha."),
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
            case STEM_5_AGE -> STEM_5_PUBLISHED_AT;
            default -> FOUR_YEAR_PUBLISHED_AT;
        };
        String updatedAt = switch (age) {
            case AGE_3 -> THREE_YEAR_UPDATED_AT;
            case STEM_5_AGE -> STEM_5_UPDATED_AT;
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
