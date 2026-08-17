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
    private static final int BALANCE_BIKES_AGE = 3;
    private static final int BOARD_GAMES_AGE = 4;
    private static final int SCOOTERS_AGE = 4;
    private static final int TOWERS_AGE = 4;
    private static final int TABLEWARE_AGE = 4;
    private static final int SUSTAINABLE_AGE = 4;
    private static final int STEM_5_AGE = 5;
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
                quickNavigation(),
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
                List.of(
                        new ComparisonPageResponse.QuickNavItem("Resumen rápido", "#resumen-rapido"),
                        new ComparisonPageResponse.QuickNavItem("Cómo comparamos", "#metodologia"),
                        new ComparisonPageResponse.QuickNavItem("Los cinco juegos", "#comparativa"),
                        new ComparisonPageResponse.QuickNavItem("Guía de compra", "#guia-de-compra"),
                        new ComparisonPageResponse.QuickNavItem("Preguntas frecuentes", "#faq")
                ),
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
                List.of(
                        new ComparisonPageResponse.QuickNavItem("Resumen rápido", "#resumen-rapido"),
                        new ComparisonPageResponse.QuickNavItem("Cómo comparamos", "#metodologia"),
                        new ComparisonPageResponse.QuickNavItem("Los cinco modelos", "#comparativa"),
                        new ComparisonPageResponse.QuickNavItem("Guía de compra", "#guia-de-compra"),
                        new ComparisonPageResponse.QuickNavItem("Preguntas frecuentes", "#faq")
                ),
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

    private ComparisonPageResponse ageComparison(
            String slug,
            String canonical,
            int age,
            List<EditorialEntry> editorial,
            String seoTitle,
            String seoDescription,
            String breadcrumbLabel,
            ComparisonPageResponse.Header header,
            String comparativaNavLabel,
            List<ComparisonPageResponse.QuickSummaryItem> quickSummary,
            ComparisonPageResponse.Methodology methodology,
            ComparisonPageResponse.BuyingGuide buyingGuide,
            List<ComparisonPageResponse.Faq> faq,
            List<LinkItem> relatedLinks
    ) {
        String publishedAt = age == STEM_5_AGE ? STEM_5_PUBLISHED_AT : FOUR_YEAR_PUBLISHED_AT;
        String updatedAt = age == STEM_5_AGE ? STEM_5_UPDATED_AT : FOUR_YEAR_UPDATED_AT;
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
                List.of(
                        new ComparisonPageResponse.QuickNavItem("Resumen rápido", "#resumen-rapido"),
                        new ComparisonPageResponse.QuickNavItem("Cómo comparamos", "#metodologia"),
                        new ComparisonPageResponse.QuickNavItem(comparativaNavLabel, "#comparativa"),
                        new ComparisonPageResponse.QuickNavItem("Guía de compra", "#guia-de-compra"),
                        new ComparisonPageResponse.QuickNavItem("Preguntas frecuentes", "#faq")
                ),
                quickSummary,
                methodology,
                rankedEntries(editorial, age),
                buyingGuide,
                faq,
                relatedLinks,
                new TrustAuthority(
                        "La selección parte de cinco productos reales y separa los datos de catálogo del análisis editorial. Conservamos los títulos editoriales y evitamos datos comerciales no verificables.",
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

    private List<ComparisonPageResponse.QuickNavItem> quickNavigation() {
        return List.of(
                new ComparisonPageResponse.QuickNavItem("Resumen rápido", "#resumen-rapido"),
                new ComparisonPageResponse.QuickNavItem("Cómo comparamos", "#metodologia"),
                new ComparisonPageResponse.QuickNavItem("Las cinco bicicletas", "#comparativa"),
                new ComparisonPageResponse.QuickNavItem("Guía de compra", "#guia-de-compra"),
                new ComparisonPageResponse.QuickNavItem("Preguntas frecuentes", "#faq")
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
