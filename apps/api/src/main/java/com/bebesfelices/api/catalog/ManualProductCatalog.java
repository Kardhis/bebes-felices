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
                "B00005RF5G",
                "www.amazon.es",
                "Juego Montessori de formas y encajes",
                "Cubo de madera Melissa & Doug con 12 piezas grandes para clasificar formas y colores.",
                2, 4,
                List.of("Juguetes educativos", "Montessori"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 7, 20)
        ));
        register(new Product(
                "puzle-madera-animales",
                ProductSource.MANUAL,
                "B00HWHNNRG",
                "www.amazon.es",
                "Puzle de madera de animales",
                "Puzle Melissa & Doug de animales del safari con piezas grandes para manos pequeñas.",
                2, 5,
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
                "bici-chillafish-bmxie",
                ProductSource.MANUAL,
                "B07LD5YTGR",
                "www.amazon.es",
                "Chillafish BMXie 2",
                "Bicicleta sin pedales para 2 a 5 años y hasta 35 kg, con ruedas sin aire de 12 pulgadas, sillín regulable de 32 a 41 cm, freno de pie y reposapiés.",
                2, 5,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "bici-eight4two",
                ProductSource.MANUAL,
                "B0FJFW99GQ",
                "www.amazon.es",
                "EIGHT4TWO",
                "Bicicleta sin pedales de 2,9 kg para 2 a 5 años, con ruedas antipinchazos y sillín y manillar regulables sin herramientas.",
                2, 5,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "bici-momi-breki",
                ProductSource.MANUAL,
                "B0CGF5DBMT",
                "www.amazon.es",
                "MoMi BREKI",
                "Bicicleta sin pedales desde 3 años y hasta 25 kg, de 3 kg, con ruedas EVA de 12 pulgadas antipinchazos y sillín regulable con cierre rápido.",
                3, 6,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "bici-sawyer-ultraligera",
                ProductSource.MANUAL,
                "B08MWDSZ9W",
                "www.amazon.es",
                "Sawyer Bikes ultraligera",
                "Bicicleta sin pedales ultraligera con sillín y manillar ajustables para dominar el equilibrio en 2 a 5 años.",
                2, 5,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
        ));
        register(new Product(
                "bici-gaslike-16-con-freno",
                ProductSource.MANUAL,
                "B09YNRMQQ1",
                "www.amazon.es",
                "GASLIKE 16\" con freno",
                "Bicicleta sin pedales con ruedas de 16 pulgadas, freno y caballete para aprender equilibrio con un tamaño pensado para niños de 3 a 7 años.",
                3, 7,
                List.of("Movimiento", "Bicicletas sin pedales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 13)
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
                "patinete-micro-mini-deluxe",
                ProductSource.MANUAL,
                null,
                null,
                "Micro Mini Deluxe LED",
                "Patinete de tres ruedas para 2 a 5 años, de 1,95 kg, con giro por inclinación, manillar ajustable, luces LED sin pilas y carga máxima de 50 kg.",
                2, 5,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "patinete-molto-maxi",
                ProductSource.MANUAL,
                null,
                null,
                "MOLTO Maxi Scooter",
                "Patinete de tres ruedas para 3 a 5 años, con luces LED en las ruedas, manillar regulable de 57 a 67 cm, freno trasero y montaje sin herramientas.",
                3, 5,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "patinete-globber-junior-foldable",
                ProductSource.MANUAL,
                null,
                null,
                "Globber Junior Foldable Lights",
                "Patinete plegable de tres ruedas desde 2 años, con luces LED por dinamo, bloqueo de dirección, manillar de 3 alturas y carga máxima de 50 kg.",
                2, 6,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "patinete-yvolution-y-glider",
                ProductSource.MANUAL,
                null,
                null,
                "Yvolution Y Glider",
                "Patinete de tres ruedas para 3 a 8 años, con giro por inclinación, ruedas LED sin pilas, manillar de 4 alturas y plegado para llevar.",
                3, 8,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "patinete-colorbaby-eezi-mini",
                ProductSource.MANUAL,
                null,
                null,
                "ColorBaby Eezi Mini",
                "Patinete de tres ruedas desde 24 meses y hasta 20 kg, con manillar desmontable de 3 alturas, ruedas delanteras oscilantes y freno trasero.",
                2, 5,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "patinete-globber-primo-foldable",
                ProductSource.MANUAL,
                null,
                null,
                "Globber Primo Foldable Lights",
                "Patinete plegable de tres ruedas para 3 a 7 años, con luces LED por dinamo, bloqueo de dirección, manillar de 3 alturas y carga máxima de 50 kg.",
                3, 7,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "patinete-lionelo-timmy",
                ProductSource.MANUAL,
                "B0CD7YW5H8",
                "www.amazon.es",
                "Lionelo Timmy",
                "Patinete de tres ruedas desde 3 años y hasta 50 kg, de 3 kg, con manillar de 4 alturas (64-76 cm), luces LED en ruedas y plataforma, freno trasero y plegado.",
                3, 8,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "patinete-globber-go-up-plus",
                ProductSource.MANUAL,
                "B0BYSV5944",
                "www.amazon.es",
                "Globber GO UP Foldable Plus Eco",
                "Patinete evolutivo 3en1 plegable, con barra de empuje, asiento extraíble, manillar de 68-78 cm en modo patinete y carga máxima de 50 kg (asiento 20 kg).",
                1, 6,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "triciclo-lionelo-tris-plus",
                ProductSource.MANUAL,
                "B0D2RJVY4P",
                "www.amazon.es",
                "LIONELO Tris Plus",
                "Triciclo 2 en 1 de 9 meses a 5 años y hasta 25 kg, de 9,7 kg, con mango parental de 80-100 cm, asiento giratorio 360°, rueda libre, ruedas PU y freno trasero.",
                1, 5,
                List.of("Movimiento", "Triciclos"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 3)
        ));
        register(new Product(
                "patinete-micro-mini-3en1",
                ProductSource.MANUAL,
                null,
                null,
                "Micro Mini 3en1 Deluxe Plus",
                "Patinete evolutivo de tres ruedas para 1 a 5 años, con asiento, reposapiés y bastón de empuje extraíbles, manillar de 48-68 cm y carga máxima de 50 kg.",
                1, 5,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "patinete-globber-master-lights",
                ProductSource.MANUAL,
                null,
                null,
                "Globber Master Lights",
                "Patinete plegable de tres ruedas desde 4 años, con manillar de 5 alturas entre 74 y 94 cm, luces LED por dinamo y carga máxima de 50 kg.",
                4, 14,
                List.of("Movimiento", "Patinetes"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "triciclo-chicco-u-go",
                ProductSource.MANUAL,
                null,
                null,
                "Chicco U-GO 2en1",
                "Triciclo de 18 meses a 5 años y hasta 20 kg, con mango telescópico para el adulto, modo de pedaleo libre, cinturón y estructura metálica.",
                2, 5,
                List.of("Movimiento", "Triciclos"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
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
                "juego-mesa-animal-sobre-animal",
                ProductSource.MANUAL,
                "B00D6J9SJQ",
                "www.amazon.es",
                "HABA Animal sobre Animal",
                "Juego de habilidad y apilamiento a partir de 4 años, con 29 figuras de madera y un dado de símbolos.",
                4, Integer.MAX_VALUE,
                List.of("Juguetes educativos", "Juegos de mesa"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 17)
        ));
        register(new Product(
                "juego-mesa-dobble-kids",
                ProductSource.MANUAL,
                "B00OM7VIC6",
                "www.amazon.es",
                "Dobble Kids",
                "Juego de cartas de observación y reflejos en español, a partir de 4 años, de 2 a 8 jugadores y partidas de unos 15 minutos.",
                4, Integer.MAX_VALUE,
                List.of("Juguetes educativos", "Juegos de mesa"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 17)
        ));
        register(new Product(
                "kit-manualidades-natural",
                ProductSource.MANUAL,
                "B09MSCSYB3",
                "www.amazon.es",
                "Kit de manualidades con materiales naturales",
                "SES Creative Eco: plastilina de materias primas naturales y herramientas de madera, a partir de 3 años, para modelar con un adulto.",
                3, 5,
                List.of("Regalos", "Creatividad"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 26)
        ));
        register(new Product(
                "torre-yoleo-transformer",
                ProductSource.MANUAL,
                "B09999YJXZ",
                "www.amazon.es",
                "YOLEO Transformer",
                "Torre de aprendizaje plegable de madera de nogal, de 42 x 45 x 86 cm, convertible en silla y escritorio, con pizarra magnética de doble cara.",
                2, 6,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "torre-hauck-learn-n-explore",
                ProductSource.MANUAL,
                null,
                null,
                "hauck Learn N Explore",
                "Torre de aprendizaje de madera de haya FSC, de 1 a 6 años, con plataforma regulable en 3 alturas (33 a 45 cm), 90,5 cm de alto y carga máxima de 40 kg.",
                1, 6,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "torre-costway-plegable",
                ProductSource.MANUAL,
                null,
                null,
                "COSTWAY Plegable 3 en 1",
                "Torre de aprendizaje plegable de madera, indicada a partir de 3 años, con estructura en A, barra de seguridad, pizarra y conversión a mesa y silla. Altura 91 cm y carga máxima de 60 kg.",
                3, Integer.MAX_VALUE,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "torre-bey-co",
                ProductSource.MANUAL,
                null,
                null,
                "BEY & CO Torre de aprendizaje",
                "Torre de madera con 3 alturas ajustables, superficie antideslizante, patas anticaída y certificación EN-71, indicada desde que el niño se mantiene de pie.",
                1, 6,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "torre-maxi-cosi-toucan",
                ProductSource.MANUAL,
                null,
                null,
                "Maxi-Cosi Toucan 3 en 1",
                "Torre de aprendizaje de madera FSC que se transforma en mesa y silla o en taburete, con 3 alturas (29,5 a 41,4 cm), 46 x 91 x 43,3 cm y 8,7 kg. Modo torre de 1,5 a 6 años.",
                2, 6,
                List.of("Autonomía", "Mobiliario infantil"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "vajilla-stor-mickey",
                ProductSource.MANUAL,
                null,
                null,
                "Stor vajilla 3 piezas Mickey Mouse",
                "Set de plato, cuenco y vaso de 260 ml de plástico libre de BPA, con base antideslizante y apto para microondas.",
                3, 6,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "vajilla-twistshake-dividido",
                ProductSource.MANUAL,
                null,
                null,
                "Twistshake plato con compartimentos",
                "Plato de 20 cm con 3 compartimentos, tapa, base antideslizante, plástico PP y TPE libre de BPA, apto para microondas y lavavajillas desde 6 meses.",
                1, Integer.MAX_VALUE,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "vaso-munchkin-miracle-360",
                ProductSource.MANUAL,
                null,
                null,
                "Munchkin Miracle 360 con asas",
                "Set de 2 vasos de aprendizaje de 207 ml con borde 360°, válvula antigoteo, asas y plástico libre de BPA, aptos para lavavajillas.",
                1, Integer.MAX_VALUE,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "vajilla-fun-house",
                ProductSource.MANUAL,
                null,
                null,
                "Fun House vajilla 3 piezas",
                "Set reutilizable de polipropileno con plato de 22 cm, cuenco de 16 cm y vaso de 220 ml, apto para microondas.",
                3, 8,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 9, 2)
        ));
        register(new Product(
                "cuenco-twistshake-tapa",
                ProductSource.MANUAL,
                null,
                null,
                "Twistshake cuenco con tapa",
                "Cuenco de PP y silicona con tapa, libre de BPA, indicado desde 6 meses para guardar o llevar.",
                1, Integer.MAX_VALUE,
                List.of("Autonomía", "Rutina diaria"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "cuentas-melissa-doug",
                ProductSource.MANUAL,
                null,
                null,
                "Melissa & Doug cuentas de madera",
                "Juego de 27 cuentas de madera con formas, números del 1 al 10 y 2 cordones, para ensartar a partir de 3 años.",
                3, 6,
                List.of("Regalos", "Sostenibles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "plantoys-ata-zapato",
                ProductSource.MANUAL,
                "B092HVBGB3",
                "www.amazon.es",
                "PlanToys Ata el zapato",
                "Juguete de madera de caucho para practicar nudos, de 3 a 8 años, fabricado en Tailandia con pegamento sin formaldehído y tintes al agua.",
                3, 8,
                List.of("Regalos", "Sostenibles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "haba-puzles-cuatro-estaciones",
                ProductSource.MANUAL,
                "B01CSUXO2U",
                "www.amazon.es",
                "HABA Puzzles Las Cuatro Estaciones",
                "Cuatro rompecabezas de 15 piezas a partir de 3 años, con figuras de madera de haya sostenible y cartón resistente.",
                3, Integer.MAX_VALUE,
                List.of("Regalos", "Sostenibles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 17)
        ));
        register(new Product(
                "small-foot-grua",
                ProductSource.MANUAL,
                "B07MVR126C",
                "www.amazon.es",
                "Small Foot grúa de construcción",
                "Juego de madera FSC 100 % a partir de 3 años, con grúa giratoria 360°, manivela y accesorios de obra.",
                3, Integer.MAX_VALUE,
                List.of("Regalos", "Sostenibles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 17)
        ));
        register(new Product(
                "green-toys-construccion",
                ProductSource.MANUAL,
                null,
                null,
                "Green Toys vehículos de construcción",
                "Tres vehículos de plástico 100 % reciclado, sin BPA, ftalatos ni PVC, lavables en lavavajillas, para 24 a 72 meses.",
                2, 6,
                List.of("Regalos", "Sostenibles"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 14)
        ));
        register(new Product(
                "hape-doctor-on-call",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Doctor on Call",
                "Maletín con estetoscopio, termómetro, jeringa y otros accesorios para representar una consulta.",
                3, 8,
                List.of("Juguetes educativos", "Juego simbólico"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "hape-little-doctor",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Little Doctor's Medical Set",
                "Set de imitación médica con instrumental y elementos interactivos para representar revisiones.",
                3, 8,
                List.of("Juguetes educativos", "Juego simbólico"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "hape-gourmet-kitchen",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Gourmet Kitchen",
                "Cocina de madera con horno, fregadero y mandos para recrear rutinas domésticas.",
                3, 8,
                List.of("Juguetes educativos", "Juego simbólico"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-sensory-fidget-tubes",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Sensory Trio Fidget Tubes",
                "Tres tubos sensoriales sellados con movimientos visuales diferentes para observar y manipular.",
                3, 8,
                List.of("Juguetes educativos", "Sensoriales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-sensory-scoops",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Helping Hands Sensory Scoops",
                "Cuatro herramientas para recoger, verter y transferir materiales sensoriales con supervisión.",
                3, 8,
                List.of("Juguetes educativos", "Sensoriales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-emotion-fidget-poppers",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Rainbow Emotion Fidget Poppers",
                "Piezas de silicona manipulables con seis colores y expresiones emocionales.",
                3, 8,
                List.of("Juguetes educativos", "Sensoriales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "schleich-farm-starter-42729",
                ProductSource.MANUAL,
                null,
                null,
                "Schleich Farm World Farm Starter Set",
                "Cuatro figuras de animales de granja para montar escenas y crear historias propias.",
                3, 8,
                List.of("Juguetes educativos", "Pequeños mundos"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "schleich-wild-life-42387",
                ProductSource.MANUAL,
                null,
                null,
                "Schleich Wild Life Starter Set",
                "Figuras de fauna salvaje con las que construir un pequeño mundo de safari.",
                3, 8,
                List.of("Juguetes educativos", "Pequeños mundos"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "schleich-africa-starter-42721",
                ProductSource.MANUAL,
                null,
                null,
                "Schleich Wild Life Africa Starter Set",
                "Cuatro figuras de animales para recrear escenas y relatos de fauna africana.",
                3, 8,
                List.of("Juguetes educativos", "Pequeños mundos"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "hape-rock-ukulele",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Rock Ukulele",
                "Ukelele infantil con cuatro cuerdas afinables, asa y guía para los primeros ritmos.",
                3, 8,
                List.of("Juguetes educativos", "Musicales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "hape-lights-ukulele",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Learn with Lights Ukulele",
                "Instrumento de cuerdas con guía luminosa y canciones para practicar pulsación y ritmo.",
                3, 8,
                List.of("Juguetes educativos", "Musicales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "hape-electronic-ukulele",
                ProductSource.MANUAL,
                null,
                null,
                "Hape Electronic Ukulele",
                "Ukelele con cuerdas, guía de luces y dos modos de práctica musical.",
                3, 8,
                List.of("Juguetes educativos", "Musicales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "magna-tiles-classic-32",
                ProductSource.MANUAL,
                null,
                null,
                "MAGNA-TILES Classic 32-Piece Set",
                "Piezas magnéticas geométricas para crear diseños planos y estructuras tridimensionales.",
                3, 10,
                List.of("Juguetes educativos", "Construcción"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "magna-tiles-builder-32",
                ProductSource.MANUAL,
                null,
                null,
                "MAGNA-TILES Builder 32-Piece Set",
                "Piezas magnéticas con elementos de obra para construir vehículos y estructuras abiertas.",
                3, 10,
                List.of("Juguetes educativos", "Construcción"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "magna-tiles-metropolis-110",
                ProductSource.MANUAL,
                null,
                null,
                "MAGNA-TILES Metropolis 110-Piece Set",
                "Set amplio de formas magnéticas para levantar estructuras de distintas escalas.",
                3, 10,
                List.of("Juguetes educativos", "Construcción"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "crayola-washable-paint-set",
                ProductSource.MANUAL,
                null,
                null,
                "Crayola Washable Paint Set",
                "Conjunto de pinturas lavables, pinceles, papel y esponjas para creación libre.",
                4, 10,
                List.of("Juguetes educativos", "Arte y manualidades"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "crayola-pumpkin-painting",
                ProductSource.MANUAL,
                null,
                null,
                "Crayola Creature Pumpkin Painting Kit",
                "Manualidad guiada con pintura lavable, Model Magic, pincel y piezas decorativas.",
                4, 10,
                List.of("Juguetes educativos", "Arte y manualidades"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "crayola-light-ups-owl",
                ProductSource.MANUAL,
                null,
                null,
                "Crayola Light-Ups Owl",
                "Superficie reutilizable para dibujar con rotuladores lavables, borrar y volver a crear.",
                4, 10,
                List.of("Juguetes educativos", "Arte y manualidades"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-magnet-movers",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources STEM Explorers Magnet Movers",
                "Material guiado para experimentar con atracción, repulsión y objetos magnéticos.",
                4, 8,
                List.of("Juguetes educativos", "Experimentación"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-tumble-trax",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Tumble Trax Magnetic Marble Run",
                "Pistas magnéticas configurables para observar recorridos, velocidad y cambios de dirección.",
                4, 10,
                List.of("Juguetes educativos", "Experimentación"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-gears-machines-motion",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Gears! Gears! Gears! Machines in Motion",
                "Engranajes, ruedas y ejes para construir mecanismos cuyo movimiento resulta visible.",
                4, 10,
                List.of("Juguetes educativos", "Experimentación"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-word-construction",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Word Construction",
                "Piezas giratorias para formar palabras sencillas y cambiar consonantes y vocales.",
                5, 9,
                List.of("Juguetes educativos", "Lectoescritura"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-magnetic-lowercase-letters",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Jumbo Lowercase Magnetic Letters",
                "Letras minúsculas grandes y manipulables para ordenar y formar palabras sencillas.",
                5, 9,
                List.of("Juguetes educativos", "Lectoescritura"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-spell-smores",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Let's Spell S'Mores!",
                "Juego por niveles para construir palabras de tres letras con piezas manipulables.",
                5, 9,
                List.of("Juguetes educativos", "Lectoescritura"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-sum-swamp",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Sum Swamp",
                "Juego de recorrido que introduce sumas y restas mediante dados y decisiones sencillas.",
                5, 9,
                List.of("Juguetes educativos", "Matemáticas y lógica"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-dumpling-dash",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Dumpling Dash",
                "Juego de conteo, reconocimiento numérico y operaciones iniciales con piezas manipulables.",
                5, 9,
                List.of("Juguetes educativos", "Matemáticas y lógica"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "lr-ten-frame-trays",
                ProductSource.MANUAL,
                null,
                null,
                "Learning Resources Connecting Ten-Frame Trays",
                "Marcos de diez y fichas para visualizar cantidades, patrones y relaciones numéricas.",
                5, 9,
                List.of("Juguetes educativos", "Matemáticas y lógica"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "haba-orchard",
                ProductSource.MANUAL,
                null,
                null,
                "HABA Orchard",
                "Juego cooperativo en el que el grupo recoge la fruta antes de que llegue el cuervo.",
                3, 6,
                List.of("Juguetes educativos", "Cooperativos y socioemocionales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "haba-prima-ballerina",
                ProductSource.MANUAL,
                null,
                null,
                "HABA Prima Ballerina",
                "Juego cooperativo de movimiento en el que se completa una secuencia de baile en grupo.",
                4, 8,
                List.of("Juguetes educativos", "Cooperativos y socioemocionales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        register(new Product(
                "peaceable-race-treasure",
                ProductSource.MANUAL,
                null,
                null,
                "Peaceable Kingdom Race to the Treasure!",
                "Juego cooperativo para construir un camino, reunir llaves y decidir juntos antes de que llegue el ogro.",
                5, 9,
                List.of("Juguetes educativos", "Cooperativos y socioemocionales"),
                ProductStatus.ACTIVE,
                null,
                LocalDate.of(2026, 8, 18)
        ));
        EducationalAmazonProducts.all().forEach(this::register);
        MovementAmazonProducts.all().forEach(this::register);
        AutonomyAmazonProducts.all().forEach(this::register);
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
