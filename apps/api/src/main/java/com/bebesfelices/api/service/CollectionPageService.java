package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.dto.CollectionPageResponse;
import com.bebesfelices.api.dto.PageStatus;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CollectionPageService {

    public static final String MONTESSORI_SLUG = "juegos-montessori";
    public static final String PUZZLES_SLUG = "puzles";
    public static final String SCOOTERS_SLUG = "patinetes";
    public static final String TOWERS_SLUG = "torres-de-aprendizaje";
    public static final String TABLEWARE_SLUG = "vajilla-infantil";
    public static final String SUSTAINABLE_3_SLUG = "regalos-duraderos-3-anos";
    public static final String SUSTAINABLE_4_SLUG = "regalos-duraderos-4-anos";
    public static final String SUSTAINABLE_5_SLUG = "regalos-duraderos-5-anos";
    public static final String GIFTS_3_SLUG = "ideas-regalo-3-anos";
    public static final String STEM_SLUG = "juegos-stem";
    public static final String BALANCE_BIKES_SLUG = "bicicletas-sin-pedales";
    public static final String PIKLER_SLUG = "triangulos-pikler";
    public static final String RIDE_ON_SLUG = "correpasillos";
    public static final String CUTLERY_SLUG = "cubiertos-infantiles";
    public static final String DRESSING_SLUG = "aprender-vestirse";
    public static final String GIFTS_4_SLUG = "ideas-regalo-4-anos";
    public static final String GIFTS_5_SLUG = "ideas-regalo-5-anos";
    public static final String BOARD_GAMES_SLUG = "juegos-de-mesa";
    public static final String SYMBOLIC_PLAY_SLUG = "juego-simbolico";
    public static final String SENSORY_TOYS_SLUG = "juguetes-sensoriales";
    public static final String SMALL_WORLDS_SLUG = "munecos-figuras-pequenos-mundos";
    public static final String MUSICAL_TOYS_SLUG = "juguetes-musicales";
    public static final String CONSTRUCTION_TOYS_SLUG = "juguetes-construccion";
    public static final String ARTS_CRAFTS_SLUG = "arte-manualidades";
    public static final String EXPERIMENTATION_SLUG = "causa-efecto-experimentacion";
    public static final String LITERACY_SLUG = "lenguaje-lectoescritura";
    public static final String MATH_LOGIC_SLUG = "matematicas-logica";
    public static final String COOPERATIVE_SEL_SLUG = "juegos-cooperativos-socioemocionales";

    private final Map<String, EditorialCollection> collections = new LinkedHashMap<>();
    private final ProductCatalog productCatalog;

    public CollectionPageService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        registerAll();
    }

    public Optional<CollectionPageResponse> getBySlug(String slug) {
        EditorialCollection editorial = collections.get(slug);
        if (editorial == null) {
            return Optional.empty();
        }
        List<CollectionPageResponse.CollectionProduct> products = editorial.productIds().stream()
                .map(productId -> validatedProduct(editorial, productId))
                .map(product -> toCollectionProduct(product, editorial.productHref(product.id())))
                .toList();
        return Optional.of(toResponse(editorial, products));
    }

    public List<String> publishedSlugs() {
        return List.copyOf(collections.keySet());
    }

    private void registerAll() {
        register(new EditorialCollection(
                MONTESSORI_SLUG,
                "/juguetes-educativos/juegos-montessori/",
                "Juguetes educativos",
                "Juegos Montessori de formas y encajes",
                "Clasificación de formas, colores y tamaños con piezas grandes, pensada para niños de 3 años.",
                List.of(
                        "A los 3 años el juego de encaje y clasificación ayuda a nombrar formas y colores sin instrucciones largas.",
                        "Busca piezas grandes, madera o materiales resistentes, y un tamaño que el niño pueda completar en una sesión corta."
                ),
                List.of(
                        "Piezas demasiado grandes para tragarse y fáciles de agarrar.",
                        "Una sola actividad clara: encajar, apilar o clasificar, no un set con diez modos.",
                        "Material que aguante el uso en el suelo y en la mesa.",
                        "Edad mínima del fabricante compatible con 3 años."
                ),
                List.of(
                        "montessori-janod-animales",
                        "montessori-melissa-tres-puzzles",
                        "montessori-janod-ballenas",
                        "montessori-janod-tropik",
                        "puzle-melissa-granja-peg",
                        "small-foot-grua"
                ),
                mergeHrefs(
                        comparisonHrefs(
                                ComparisonPageService.MONTESSORI_3_SLUG,
                                "montessori-janod-animales",
                                "montessori-melissa-tres-puzzles",
                                "montessori-janod-ballenas",
                                "montessori-janod-tropik",
                                "puzle-melissa-granja-peg"
                        ),
                        comparisonHrefs(
                                ComparisonPageService.SUSTAINABLE_3_SLUG,
                                "small-foot-grua"
                        )
                ),
                List.of(
                        faq("¿Hace falta un material «Montessori» certificado?",
                                "No. Lo útil es la actividad: clasificar y encajar con autonomía y piezas seguras. El nombre comercial no sustituye el criterio de edad y seguridad."),
                        faq("¿Cuántas piezas son demasiadas a los 3 años?",
                                "Si el niño no puede terminar una ronda sin frustrarse, sobran. Empieza por un set reducido y añade dificultad más adelante."),
                        faq("¿Se puede usar sin un adulto encima?",
                                "Con supervisión cercana, sí, cuando las piezas son grandes y la actividad es evidente. No es un juguete para dejar solo en una habitación.")
                ),
                List.of(
                        educationalToysLink(3),
                        new LinkItem(
                                "Mejores juegos Montessori para 3 años",
                                "/comparativas/" + ComparisonPageService.MONTESSORI_3_SLUG + "/",
                                "Comparativa de encaje y clasificación con piezas grandes."
                        ),
                        chooseByAgeLink()
                )
        ));
        register(new EditorialCollection(
                PUZZLES_SLUG,
                "/juguetes-educativos/puzles/",
                "Juguetes educativos",
                "Puzles de piezas grandes para 3 años",
                "Motricidad fina sin piezas pequeñas de riesgo, con imágenes reconocibles y un número de piezas manejable.",
                List.of(
                        "Un puzle a los 3 años sirve para encajar, nombrar lo que se ve y completar una tarea breve. No es un puzzle de 100 piezas ni un reto de paciencia adulta.",
                        "Prioriza madera o cartón grueso, pomos o piezas anchas, y un motivo que el niño ya reconozca (animales, vehículos, casa)."
                ),
                List.of(
                        "Pocas piezas grandes, no un recuento alto «para crecer».",
                        "Imagen clara, sin detalles minúsculos.",
                        "Tablero o marco que sujete las piezas mientras se completa.",
                        "Sin piezas sueltas del tamaño de un tapón si hay hermanos más pequeños."
                ),
                List.of(
                        "puzle-madera-animales",
                        "puzle-melissa-mascotas",
                        "puzle-educa-selva",
                        "haba-puzles-cuatro-estaciones",
                        "puzle-educa-disney-madera",
                        "puzle-melissa-granja-peg"
                ),
                mergeHrefs(
                        comparisonHrefs(
                                ComparisonPageService.PUZZLES_3_SLUG,
                                "puzle-madera-animales",
                                "haba-puzles-cuatro-estaciones",
                                "puzle-educa-disney-madera",
                                "puzle-educa-selva",
                                "puzle-melissa-mascotas"
                        ),
                        comparisonHrefs(
                                ComparisonPageService.MONTESSORI_3_SLUG,
                                "puzle-melissa-granja-peg"
                        )
                ),
                List.of(
                        faq("¿Cuántas piezas recomendáis a los 3 años?",
                                "Las justas para terminar en una sesión corta. Un puzle de madera de animales con piezas grandes suele encajar mejor que uno de decenas de piezas pequeñas."),
                        faq("¿Cartón o madera?",
                                "Ambos valen si son gruesos. La madera suele durar más en un uso intenso; el cartón fino se dobla y frustra."),
                        faq("¿Qué hacer si se rinde a mitad?",
                                "Reduce el número de piezas a la vista, nombra lo que falta y termina juntos. El objetivo es el éxito repetible, no el récord.")
                ),
                List.of(
                        educationalToysLink(3),
                        new LinkItem(
                                "Mejores puzles para 3 años",
                                "/comparativas/" + ComparisonPageService.PUZZLES_3_SLUG + "/",
                                "Comparativa por número de piezas y si se puede terminar."
                        ),
                        chooseByAgeLink()
                )
        ));
        register(new EditorialCollection(
                SCOOTERS_SLUG,
                "/movimiento/patinetes/",
                "Movimiento",
                "Patinetes de 3 ruedas para 3 años",
                "Estabilidad extra para el juego al aire libre, con supervisión y un espacio sin tráfico.",
                List.of(
                        "A los 3 años el equilibrio todavía se está asentando. Un patinete de tres ruedas ofrece una base más estable que uno de dos ruedas y permite practicar dirección y frenado con menos caídas laterales.",
                        "No sustituye a la bicicleta sin pedales: cubre otro gesto. Elige altura de manillar, peso que el niño pueda recoger y ruedas adecuadas al suelo habitual."
                ),
                List.of(
                        "Tres ruedas o una base ancha para el primer uso.",
                        "Manillar a una altura que no obligue a estirar los brazos.",
                        "Peso que un adulto pueda llevar y un niño pueda enderezar.",
                        "Casco, calzado cerrado y un espacio sin coches ni desniveles bruscos."
                ),
                List.of(
                        "patinete-yvolution-y-glider",
                        "patinete-colorbaby-eezi-mini",
                        "patinete-globber-primo-foldable",
                        "patinete-micro-mini-3en1",
                        "triciclo-chicco-u-go"
                ),
                comparisonHrefs(
                        ComparisonPageService.SCOOTERS_3_SLUG,
                        "patinete-yvolution-y-glider",
                        "patinete-colorbaby-eezi-mini",
                        "patinete-globber-primo-foldable",
                        "patinete-micro-mini-3en1",
                        "triciclo-chicco-u-go"
                ),
                List.of(
                        faq("¿Patinete o bicicleta sin pedales primero?",
                                "Depende del niño y del espacio. La bici sin pedales trabaja el equilibrio sentado; el patinete, de pie. A los 3 años ambos pueden convivir si hay supervisión."),
                        faq("¿Cuándo pasar a dos ruedas?",
                                "Cuando el equilibrio lateral ya es estable y el niño frena con control. No hay una edad fija; muchas familias lo retrasan más allá de los 3 años."),
                        faq("¿Hace falta casco?",
                                "Sí. También calzado cerrado y un adulto atento. El patinete no elimina el riesgo de caída.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Mejores patinetes de 3 ruedas para 3 años",
                                "/comparativas/" + ComparisonPageService.SCOOTERS_3_SLUG + "/",
                                "Ranking editorial de cuatro patinetes y un triciclo."
                        ),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "La otra vía de movimiento con ranking editorial."
                        )
                )
        ));
        register(new EditorialCollection(
                PIKLER_SLUG,
                "/movimiento/triangulos-pikler/",
                "Movimiento",
                "Triángulos Pikler y estructuras de trepar para 3 años",
                "Trepar, gatear o deslizarse en interior, con supervisión y un suelo nivelado.",
                List.of(
                        "A los 3 años el movimiento no se agota en ruedas. Un triángulo Pikler o un gimnasio bajo permite subir, bajar y deslizarse a su ritmo, sin patinete ni bici.",
                        "Mide el hueco. La madera pide un rincón seco; el plástico de jardín también cabe en un salón. Un adulto permanece al lado: no es un parque."
                ),
                List.of(
                        "Edad declarada compatible con 3 años y un gesto de trepar o deslizarse.",
                        "Hueco en el suelo y posibilidad de guardar.",
                        "Carga y estabilidad declaradas.",
                        "Uso con adulto; suelo libre de esquinas y desniveles."
                ),
                List.of(
                        "trepar-mamoi-triangulo-blanco",
                        "trepar-aiyaplay-3en1",
                        "trepar-little-tikes-gimnasio",
                        "trepar-smoby-xs",
                        "trepar-costway-7en1"
                ),
                comparisonHrefs(
                        ComparisonPageService.PIKLER_3_SLUG,
                        "trepar-mamoi-triangulo-blanco",
                        "trepar-aiyaplay-3en1",
                        "trepar-little-tikes-gimnasio",
                        "trepar-smoby-xs",
                        "trepar-costway-7en1"
                ),
                List.of(
                        faq("¿Pikler o patinete?",
                                "No es el mismo gesto. El triángulo trabaja subir y bajar; el patinete, ir de pie con ruedas. Pueden convivir si hay espacio y supervisión."),
                        faq("¿Hace falta casco?",
                                "En una estructura baja de interior el criterio habitual es suelo libre y un adulto. El casco del patinete no se sustituye aquí por el marco de madera."),
                        faq("¿Se puede dejar solo?",
                                "No. Tampoco junto a una ventana, una escalera o un borde.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Triángulos Pikler y estructuras de trepar para 3 años",
                                "/comparativas/" + ComparisonPageService.PIKLER_3_SLUG + "/",
                                "Comparativa de madera y plástico para trepar o deslizarse."
                        ),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "La vía de movimiento con dos ruedas y pies en el suelo."
                        )
                )
        ));
        register(new EditorialCollection(
                RIDE_ON_SLUG,
                "/movimiento/correpasillos/",
                "Movimiento",
                "Correpasillos para 3 años",
                "Empujar sentado, con los pies en el suelo, sin pedales ni patinete.",
                List.of(
                        "Un correpasillos se empuja sentado. No es una bicicleta sin pedales ni un patinete de pie. A los 3 años tiene sentido si el asiento deja las piernas sueltas.",
                        "Mide entrepierna y asiento. Si las rodillas van encogidas, el modelo se ha quedado pequeño aunque la caja diga 3 años. Interior liso o patio sin tráfico."
                ),
                List.of(
                        "Asiento a una altura que permita empujar con ambos pies.",
                        "Ruedas anchas o base de cuatro ruedas.",
                        "Edad o peso declarado compatible con 3 años.",
                        "Sin motor; supervisión y zona sin coches."
                ),
                List.of(
                        "corre-injusa-winner-repsol",
                        "corre-injusa-tundra-tornado",
                        "corre-feber-dream",
                        "corre-molto-cross-premium",
                        "corre-little-tikes-cozy-coupe"
                ),
                comparisonHrefs(
                        ComparisonPageService.RIDE_ON_3_SLUG,
                        "corre-injusa-winner-repsol",
                        "corre-injusa-tundra-tornado",
                        "corre-feber-dream",
                        "corre-molto-cross-premium",
                        "corre-little-tikes-cozy-coupe"
                ),
                List.of(
                        faq("¿Correpasillos o bici sin pedales?",
                                "La bici tiene dos ruedas y manillar de bicicleta. El correpasillos es un asiento ancho sobre tres o cuatro ruedas. No enseñan lo mismo."),
                        faq("¿Y un patinete?",
                                "El patinete se usa de pie. Aquí se va sentado."),
                        faq("¿Valen los coches eléctricos?",
                                "No en esta selección. Un vehículo de batería pide otra supervisión y no cubre el empuje con los pies.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Correpasillos para 3 años",
                                "/comparativas/" + ComparisonPageService.RIDE_ON_3_SLUG + "/",
                                "Comparativa de motos de empuje y un Cozy Coupe."
                        ),
                        new LinkItem(
                                "Patinetes y triciclos para 3 años",
                                "/comparativas/" + ComparisonPageService.SCOOTERS_TRIKES_3_SLUG + "/",
                                "La vía de movimiento de pie o con mango de adulto."
                        )
                )
        ));
        register(new EditorialCollection(
                TOWERS_SLUG,
                "/autonomia/torres-de-aprendizaje/",
                "Autonomía",
                "Torres de aprendizaje para 3 años",
                "Participar en la cocina con una plataforma estable, barandilla y altura regulable.",
                List.of(
                        "Una torre de aprendizaje permite ver la encimera y colaborar en tareas reales: lavar, mezclar, observar. A los 3 años la supervisión es constante; la torre no es un taburete improvisado.",
                        "Revisa estabilidad, barandilla, regulación de altura y que no haya huecos para la cabeza o los pies. El uso es en interior, sobre suelo nivelado, con un adulto al lado."
                ),
                List.of(
                        "Base ancha y estructura que no se vuelque al inclinarse.",
                        "Barandilla continua a una altura que retenga sin atrapar.",
                        "Peldaños o plataforma regulable según crezca.",
                        "Uso solo con adulto presente; nunca cerca de fuegos, agua hirviendo o cuchillos."
                ),
                List.of(
                        "torre-costway-plegable",
                        "torre-yoleo-transformer",
                        "torre-hauck-learn-n-explore",
                        "torre-bey-co",
                        "torre-maxi-cosi-toucan"
                ),
                comparisonHrefs(
                        ComparisonPageService.TOWERS_3_SLUG,
                        "torre-costway-plegable",
                        "torre-yoleo-transformer",
                        "torre-hauck-learn-n-explore",
                        "torre-bey-co",
                        "torre-maxi-cosi-toucan"
                ),
                List.of(
                        faq("¿Es segura una torre a los 3 años?",
                                "Puede serlo si es estable, tiene barandilla y hay un adulto encima. No lo es si se usa como juguete de trepa o junto a peligros de cocina."),
                        faq("¿Madera o metal?",
                                "Lo decisivo es la estabilidad y los cierres, no el material. La madera bien acabada aguanta bien el uso diario si no hay astillas."),
                        faq("¿Sustituye a un taburete?",
                                "No es el mismo objeto. La torre está pensada para permanecer de pie con protección; un taburete no.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 3 años",
                                "/comparativas/" + ComparisonPageService.TOWERS_3_SLUG + "/",
                                "Comparativa por estabilidad, altura y plegado."
                        ),
                        new LinkItem(
                                "Vajilla infantil irrompible para 3 años",
                                "/comparativas/" + ComparisonPageService.TABLEWARE_3_SLUG + "/",
                                "Otra pieza de la autonomía en la rutina diaria."
                        )
                )
        ));
        register(new EditorialCollection(
                TABLEWARE_SLUG,
                "/autonomia/vajilla-infantil/",
                "Autonomía",
                "Vajilla infantil irrompible para 3 años",
                "Practicar comer sin ayuda con piezas de tamaño adaptado y materiales que aguanten caídas.",
                List.of(
                        "A los 3 años tiene sentido un plato, cuenco y vaso que el niño pueda llevar a la mesa. Lo irrompible evita el drama de cada caída y permite repetir el gesto.",
                        "Busca tamaño de mano, base estable y materiales aptos para alimento. No hace falta un set decorativo; hace falta que se pueda lavar y usar todos los días."
                ),
                List.of(
                        "Material que no se haga añicos al caer.",
                        "Tamaño y peso que el niño pueda sujetar con dos manos si hace falta.",
                        "Vaso o taza con base ancha para reducir vuelcos.",
                        "Fácil de limpiar; evita piezas con recovecos imposibles."
                ),
                List.of(
                        "vajilla-stor-mickey",
                        "vajilla-fun-house",
                        "vajilla-twistshake-dividido",
                        "vaso-munchkin-miracle-360",
                        "cuenco-twistshake-tapa"
                ),
                comparisonHrefs(
                        ComparisonPageService.TABLEWARE_3_SLUG,
                        "vajilla-stor-mickey",
                        "vajilla-fun-house",
                        "vajilla-twistshake-dividido",
                        "vaso-munchkin-miracle-360",
                        "cuenco-twistshake-tapa"
                ),
                List.of(
                        faq("¿Plástico, bambú o acero?",
                                "Cualquiera vale si es apto para alimento, estable y no se rompe en astillas. Revisa el marcado del fabricante y evita pinturas dudosas en la zona de contacto."),
                        faq("¿Un vaso con pajita es mejor?",
                                "Puede ayudar al principio. A los 3 años también conviene practicar un vaso abierto estable, con supervisión ante atragantamientos."),
                        faq("¿Cuántas piezas hacen falta?",
                                "Plato o cuenco, vaso y cubiertos infantiles suelen bastar. Un set enorme acaba en el armario.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Mejores vajillas infantiles para 3 años",
                                "/comparativas/" + ComparisonPageService.TABLEWARE_3_SLUG + "/",
                                "Comparativa de sets, platos y vasos irrompibles."
                        ),
                        new LinkItem(
                                "Torres de aprendizaje para 3 años",
                                "/comparativas/" + ComparisonPageService.TOWERS_3_SLUG + "/",
                                "Para participar en la cocina con una altura segura."
                        )
                )
        ));
        register(new EditorialCollection(
                CUTLERY_SLUG,
                "/autonomia/cubiertos-infantiles/",
                "Autonomía",
                "Cubiertos infantiles para 3 años",
                "Tenedor, cuchillo y cuchara de tamaño de mano, distintos de la vajilla de plato y vaso.",
                List.of(
                        "A los 3 años la vajilla cubre plato y vaso. Los cubiertos son otra pieza: mango corto y filo de aprendizaje para pinchar y recoger sin el tenedor de adulto.",
                        "Un set de tres suele bastar. Revisa si se lava en el lavavajillas y si el cuchillo es de aprendizaje, no de cocina."
                ),
                List.of(
                        "Mango que quepa en una mano de 3 años.",
                        "Tenedor, cuchillo y cuchara; el recambio es opcional.",
                        "Filo de aprendizaje; no un cuchillo de mesa de adulto.",
                        "Cuidado según ficha: lavavajillas solo si consta."
                ),
                List.of(
                        "cubiertos-twistshake-acero",
                        "cubiertos-mam-aprendizaje",
                        "cubiertos-wmf-animales",
                        "cubiertos-exzact-safari",
                        "cubiertos-lehoo-vehiculos"
                ),
                comparisonHrefs(
                        ComparisonPageService.CUTLERY_3_SLUG,
                        "cubiertos-twistshake-acero",
                        "cubiertos-mam-aprendizaje",
                        "cubiertos-wmf-animales",
                        "cubiertos-exzact-safari",
                        "cubiertos-lehoo-vehiculos"
                ),
                List.of(
                        faq("¿Siguen haciendo falta a los 3 años?",
                                "Sí, si el cubierto de adulto se le va. Si ya come con los de la casa sin incidentes, no es obligatorio."),
                        faq("¿Dónde está el plato?",
                                "En la colección de vajilla infantil. Aquí solo entra tenedor, cuchillo y cuchara."),
                        faq("¿El cuchillo corta?",
                                "Corta blando. Un adulto sigue cortando carne fibrosa.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Cubiertos infantiles para 3 años",
                                "/comparativas/" + ComparisonPageService.CUTLERY_3_SLUG + "/",
                                "Comparativa de cinco sets de tenedor, cuchillo y cuchara."
                        ),
                        new LinkItem(
                                "Mejores vajillas infantiles para 3 años",
                                "/comparativas/" + ComparisonPageService.TABLEWARE_3_SLUG + "/",
                                "Plato, cuenco y vaso irrompibles."
                        )
                )
        ));
        register(new EditorialCollection(
                DRESSING_SLUG,
                "/autonomia/aprender-vestirse/",
                "Autonomía",
                "Aprender a vestirse para 3 años",
                "Botones, cremalleras, cordones y pestillos para practicar el gesto sin la prisa del abrigo.",
                List.of(
                        "A los 3 años vestirse pide pinza y tiempo. Un tablero o un cubo aísla el botón, la cremallera o el cordón. No sustituye el abrigo real; lo prepara.",
                        "Un cierre cada vez. Cordones e imanes se cuentan al guardar. Un adulto permanece cerca."
                ),
                List.of(
                        "Un gesto claro: abrochar, enhebrar o abrir un pestillo.",
                        "Piezas que no se traguen y recuento al guardar.",
                        "Edad declarada compatible con 3 años.",
                        "Paso posterior a la prenda o el zapato reales."
                ),
                List.of(
                        "vestir-melissa-habilidades",
                        "vestir-melissa-cordones",
                        "vestir-small-foot-cubo",
                        "vestir-melissa-disfraces",
                        "vestir-melissa-pestillos"
                ),
                comparisonHrefs(
                        ComparisonPageService.DRESSING_3_SLUG,
                        "vestir-melissa-habilidades",
                        "vestir-melissa-cordones",
                        "vestir-small-foot-cubo",
                        "vestir-melissa-disfraces",
                        "vestir-melissa-pestillos"
                ),
                List.of(
                        faq("¿Sustituye el abrigo de cada mañana?",
                                "No. El tablero aísla el gesto. Después hay que repetirlo en la prenda, con más tiempo del que parece."),
                        faq("¿Hace falta un marco Montessori de tela?",
                                "No. Lo útil es un cierre claro. El nombre comercial no sustituye la práctica en la ropa de cada día."),
                        faq("¿Se puede dejar solo?",
                                "No. Cordones, imanes y pestillos piden un adulto cerca.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Aprender a vestirse a los 3 años",
                                "/comparativas/" + ComparisonPageService.DRESSING_3_SLUG + "/",
                                "Comparativa de tableros, cubo y pestillos."
                        ),
                        new LinkItem(
                                "Mejores torres de aprendizaje para 3 años",
                                "/comparativas/" + ComparisonPageService.TOWERS_3_SLUG + "/",
                                "Si el gesto es llegar a la encimera, no abrochar."
                        )
                )
        ));
        register(new EditorialCollection(
                SUSTAINABLE_3_SLUG,
                "/sostenibles/regalos-duraderos-3-anos/",
                "Sostenibles",
                "Regalos y juguetes más duraderos para 3 años",
                "Opciones pensadas para durar: materiales resistentes, menos piezas de usar y tirar, y un uso que no dependa de pantallas.",
                List.of(
                        "Sostenible, en esta página, significa sobre todo durabilidad y materiales que aguanten el juego intenso de los 3 años. No es un sello publicitario ni una lista de certificaciones que no podamos verificar una a una.",
                        "Las opciones pensadas para repetirse —puzles de madera, tableros con piezas sujetas y cocina simbólica— están en la comparativa de regalos duraderos. El kit de plastilina ecológica queda en arte y manualidades: se usa para crear, pero el material se gasta. La grúa, los vehículos lavables y las cuentas están en regalos sostenibles."
                ),
                List.of(
                        "Material que sobreviva a caídas y al agua ocasional de las manos.",
                        "Pocas piezas de recambio imposible; mejor un objeto completo.",
                        "Uso que no dependa de pilas ni de una app.",
                        "Encaje real con 3 años: piezas grandes y sesiones cortas."
                ),
                List.of(
                        "small-foot-grua",
                        "green-toys-construccion",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "cuentas-melissa-doug",
                        "arte-ses-eco-mega-7"
                ),
                mergeHrefs(
                        comparisonHrefs(
                                ComparisonPageService.DURABLE_3_SLUG,
                                "puzle-madera-animales",
                                "puzle-melissa-mascotas",
                                "puzle-educa-selva",
                                "puzle-educa-disney-madera",
                                "simbolico-theo-klein-miele"
                        ),
                        mergeHrefs(
                                comparisonHrefs(
                                        ComparisonPageService.SUSTAINABLE_3_SLUG,
                                        "small-foot-grua",
                                        "green-toys-construccion",
                                        "plantoys-ata-zapato",
                                        "haba-puzles-cuatro-estaciones",
                                        "cuentas-melissa-doug"
                                ),
                                comparisonHrefs(
                                        ComparisonPageService.ARTS_NATURAL_3_SLUG,
                                        "arte-ses-eco-mega-7"
                                )
                        )
                ),
                List.of(
                        faq("¿Todo lo de madera es duradero?",
                                "No. La madera ayuda, pero no basta. Mira también si el juguete se usará de verdad y si se puede cuidar."),
                        faq("¿Qué evitáis?",
                                "Productos de un solo uso, piezas diminutas y juguetes que solo funcionan con pantalla. El kit de plastilina está aquí como actividad para crear; la comparativa de duraderos no lo incluye porque el material se gasta."),
                        faq("¿Sirve como regalo?",
                                "Sí, si la familia va a repetir esas actividades. Un objeto de madera o plástico lavable encaja cuando hay un uso real, no solo el día del cumpleaños.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Mejores regalos duraderos para 3 años",
                                "/comparativas/" + ComparisonPageService.DURABLE_3_SLUG + "/",
                                "Comparativa de juguetes pensados para aguantar el juego intenso."
                        ),
                        new LinkItem(
                                "Mejores regalos sostenibles para 3 años",
                                "/comparativas/" + ComparisonPageService.SUSTAINABLE_3_SLUG + "/",
                                "Comparativa de madera, materiales naturales y plástico reciclado."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/comparativas/" + ComparisonPageService.GIFTS_3_SLUG + "/",
                                "Más opciones por ocasión, no solo por material."
                        )
                )
        ));
        register(new EditorialCollection(
                GIFTS_3_SLUG,
                "/regalos/ideas-regalo-3-anos/",
                "Regalos",
                "Ideas de regalo para niños de 3 años",
                "Selección por ocasión y presupuesto, sin perder utilidad: aprender, moverse, autonomía o crear.",
                List.of(
                        "Un buen regalo a los 3 años se usa en casa o al aire libre durante semanas, no solo el día del cumpleaños. Evitamos lo puramente decorativo y lo que exige reglas largas.",
                        "Aquí reunimos las opciones del catálogo para esta edad. Cada producto enlaza a la comparativa donde está evaluado."
                ),
                List.of(
                        "Que resuelva una necesidad: aprender, moverse, autonomía o crear.",
                        "Piezas grandes y materiales resistentes.",
                        "Sesiones cortas, sin instrucciones de adulto constantes.",
                        "Revisa la fecha de la ficha y la edad mínima del fabricante."
                ),
                List.of(
                        "juego-montessori-formas",
                        "puzle-madera-animales",
                        "bici-chicco-red-bullet",
                        "patinete-yvolution-y-glider",
                        "torre-costway-plegable",
                        "vajilla-stor-mickey",
                        "kit-manualidades-natural"
                ),
                Map.of(
                        "juego-montessori-formas",
                        "/comparativas/" + ComparisonPageService.MONTESSORI_WOOD_3_SLUG + "/#producto-juego-montessori-formas",
                        "puzle-madera-animales",
                        "/comparativas/" + ComparisonPageService.PUZZLES_3_SLUG + "/#producto-puzle-madera-animales",
                        "bici-chicco-red-bullet",
                        "/comparativas/" + ComparisonPageService.BALANCE_BIKES_SLUG + "/#producto-bici-chicco-red-bullet",
                        "patinete-yvolution-y-glider",
                        "/comparativas/" + ComparisonPageService.SCOOTERS_3_SLUG + "/#producto-patinete-yvolution-y-glider",
                        "torre-costway-plegable",
                        "/comparativas/" + ComparisonPageService.TOWERS_3_SLUG + "/#producto-torre-costway-plegable",
                        "vajilla-stor-mickey",
                        "/comparativas/" + ComparisonPageService.TABLEWARE_3_SLUG + "/#producto-vajilla-stor-mickey",
                        "kit-manualidades-natural",
                        "/comparativas/" + ComparisonPageService.GIFTS_3_SLUG + "/#producto-kit-manualidades-natural"
                ),
                List.of(
                        faq("¿Qué regalo no falla a esta edad?",
                                "Uno que el niño pueda usar ya: encajar, moverse con estabilidad o participar en la mesa. Si dudas, un puzle de piezas grandes o un juego de formas suele ser más seguro que un juguete «para mayores»."),
                        faq("¿Y si ya tiene muchos juguetes?",
                                "Prioriza autonomía o movimiento, que se usan en la rutina, o un kit de crear con un adulto. Evita duplicar lo que ya cubre una necesidad."),
                        faq("¿Incluís rangos de precio?",
                                "No publicamos importes. Comparamos utilidad y durabilidad; el presupuesto lo decides tú en el momento de compra.")
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Regalos más duraderos",
                                "/comparativas/" + ComparisonPageService.SUSTAINABLE_3_SLUG + "/",
                                "Enfoque en materiales y uso a largo plazo."
                        ),
                        chooseByAgeLink()
                )
        ));
        register(new EditorialCollection(
                STEM_SLUG,
                "/juguetes-educativos/juegos-stem/",
                "Juguetes educativos",
                "Sets de construcción magnética para 4 años",
                "Lógica espacial y estructuras estables, con piezas que se sujetan entre sí y un reto que puede crecer.",
                List.of(
                        "A los 4 años la construcción deja de ser solo apilar: las piezas magnéticas y los engranajes permiten formas que se tienen en pie y se pueden deshacer sin frustración inmediata.",
                        "Busca un número de piezas manejable, imanes que no se desprendan y un tamaño que no acabe en la boca de un hermano pequeño."
                ),
                List.of(
                        "Piezas grandes o medianas, no un set de cientos de fichas diminutas.",
                        "Imanes encapsulados, no sueltos.",
                        "Un objetivo claro: construir y tumbar, no diez modos en el manual.",
                        "Edad mínima del fabricante compatible con 4 años."
                ),
                List.of(
                        "stem-geomag-rainbow",
                        "stem-gears-beginners",
                        "stem-code-go-mouse",
                        "stem-gravitrax-junior",
                        "construccion-playmags-32",
                        "construccion-desire-magnetic"
                ),
                hideAnalysisHrefs(
                        "stem-geomag-rainbow",
                        "stem-gears-beginners",
                        "stem-code-go-mouse",
                        "stem-gravitrax-junior",
                        "construccion-playmags-32",
                        "construccion-desire-magnetic"
                ),
                List.of(
                        faq("¿Es lo mismo que un puzle?",
                                "No. El puzle encaja una imagen; el set magnético prueba equilibrio y formas en el espacio. A los 4 años ambos pueden convivir."),
                        faq("¿Cuántas piezas hacen falta?",
                                "Las justas para terminar una torre o una casa en una sesión. Un set enorme se desborda sobre la mesa y se deja a medias."),
                        faq("¿Hay riesgo con los imanes?",
                                "Sí si se desprenden. Elige piezas en las que el imán no se pueda sacar y mantén el juego fuera del alcance de menores de 3 años.")
                ),
                List.of(
                        educationalToysLink(4),
                        analysisLink("set-construccion-magnetico", "Set de construcción magnético"),
                        chooseByAgeLink()
                ),
                4
        ));
        register(new EditorialCollection(
                BALANCE_BIKES_SLUG,
                "/movimiento/bicicletas-sin-pedales/",
                "Movimiento",
                "Bicicletas sin pedales para ganar seguridad a los 4 años",
                "Modelos para consolidar el equilibrio antes de la bici con pedales, con talla, peso y supervisión.",
                List.of(
                        "A los 4 años muchos niños ya no están iniciándose: están ganando confianza para ir más lejos. Sigue importando que los pies lleguen al suelo y que el cuadro no pese de más.",
                        "Esta página cubre el criterio de uso. Si buscas un ranking de modelos reales, la comparativa de bicicletas sin pedales para 3 años incluye fichas que también cubren esta edad."
                ),
                List.of(
                        "Sillín a una altura que permita apoyar ambos pies.",
                        "Peso que el niño pueda levantar y dirigir.",
                        "Ruedas adecuadas al terreno habitual (sin aire o inflables).",
                        "Casco, calzado cerrado y un espacio sin tráfico."
                ),
                List.of("bici-sin-pedales-basica"),
                Map.of(),
                List.of(
                        faq("¿A los 4 años sigue teniendo sentido una bici sin pedales?",
                                "Sí, si el equilibrio aún no está listo para pedales. No hay una fecha fija; el salto se ve en el control, no en el cumpleaños."),
                        faq("¿Dónde comparáis modelos concretos?",
                                "En la comparativa de bicicletas sin pedales para 3 años, con cinco modelos reales. Varios cubren también los 4 años según la ficha del fabricante."),
                        faq("¿Hace falta casco?",
                                "Sí. También supervisión y un espacio sin coches. La bici sin pedales no elimina el riesgo de caída.")
                ),
                List.of(
                        hubLink(4),
                        analysisLink("bici-sin-pedales-basica", "Bicicleta sin pedales básica"),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "Ranking de cinco modelos reales, útil también si el niño ya tiene 4."
                        )
                ),
                4
        ));
        register(new EditorialCollection(
                GIFTS_4_SLUG,
                "/regalos/ideas-regalo-4-anos/",
                "Regalos",
                "Ideas de regalo para niños de 4 años",
                "Selección por ocasión y presupuesto, sin perder utilidad: construir, jugar una partida corta, moverse o ganar autonomía.",
                List.of(
                        "Un buen regalo a los 4 años se usa durante semanas: un set que se puede complicar, un juego de mesa de reglas cortas o un objeto de la rutina. Evitamos lo puramente decorativo.",
                        "El juego de mesa, el patinete, la torre, la vajilla y el maletín de veterinario destacados se recomiendan desde sus comparativas; el resto tiene análisis propio."
                ),
                List.of(
                        "Que resuelva una necesidad: aprender, moverse, autonomía o crear.",
                        "Un margen de dificultad para que no se quede pequeño en un mes.",
                        "Reglas o gestos explicables en pocos minutos.",
                        "Revisa la fecha de la ficha y la edad mínima del fabricante."
                ),
                List.of(
                        "juego-montessori-formas",
                        "puzle-madera-animales",
                        "bici-sin-pedales-basica",
                        "patinete-lionelo-timmy",
                        "torre-yoleo-transformer",
                        "vajilla-twistshake-dividido",
                        "set-construccion-magnetico",
                        "lectura-frutalito",
                        "simbolico-janod-veterinario"
                ),
                Map.of(
                        "lectura-frutalito",
                        "/comparativas/mejores-juegos-de-mesa-4-anos/#producto-lectura-frutalito",
                        "patinete-lionelo-timmy",
                        "/comparativas/mejores-patinetes-4-anos/#producto-patinete-lionelo-timmy",
                        "torre-yoleo-transformer",
                        "/comparativas/mejores-torres-aprendizaje-4-anos/#producto-torre-yoleo-transformer",
                        "vajilla-twistshake-dividido",
                        "/comparativas/mejores-vajillas-infantiles-4-anos/#producto-vajilla-twistshake-dividido",
                        "simbolico-janod-veterinario",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-simbolico-janod-veterinario"
                ),
                List.of(
                        faq("¿Qué regalo no falla a esta edad?",
                                "Uno que ya pueda usar: construir, una partida corta o moverse con más seguridad. Si dudas, un cooperativo de reglas mínimas o un set magnético suele ser más útil que un juguete de personaje."),
                        faq("¿Y si ya tiene muchos juguetes?",
                                "Prioriza un juego de mesa que se saque en familia o un producto de autonomía. Evita duplicar lo que ya cubre una necesidad."),
                        faq("¿Incluís rangos de precio?",
                                "No publicamos importes. Comparamos utilidad y durabilidad; el presupuesto lo decides tú en el momento de compra.")
                ),
                List.of(
                        hubLink(4),
                        new LinkItem(
                                "Mejores juegos de mesa para 4 años",
                                "/comparativas/mejores-juegos-de-mesa-4-anos/",
                                "Cinco juegos reales si el regalo es una partida en familia."
                        ),
                        new LinkItem(
                                "Mejores patinetes y triciclos para 4 años",
                                "/comparativas/mejores-patinetes-4-anos/",
                                "Cinco modelos reales si el regalo es moverse al aire libre."
                        ),
                        new LinkItem(
                                "Mejores regalos sostenibles para 4 años",
                                "/comparativas/mejores-regalos-sostenibles-4-anos/",
                                "Madera o plástico reciclado si priorizas materiales declarados."
                        ),
                        chooseByAgeLink()
                ),
                4
        ));
        register(new EditorialCollection(
                SUSTAINABLE_4_SLUG,
                "/sostenibles/regalos-duraderos-4-anos/",
                "Sostenibles",
                "Regalos y juguetes más duraderos para 4 años",
                "Madera certificada, plástico reciclado y objetos que se pueden repetir, sin depender de pantallas.",
                List.of(
                        "A los 4 años la durabilidad se nota en el uso diario: cuidar, encajar, imitar oficios o practicar un nudo. Sostenible, aquí, es material declarado y un objeto que se saca muchas veces.",
                        "Las cinco opciones de esta página están desarrolladas en la comparativa de regalos sostenibles para 4 años. No afirmamos ecoetiquetas que no hayamos contrastado en ficha."
                ),
                List.of(
                        "Material declarado: madera, FSC o plástico reciclado, según la ficha.",
                        "Uso que no dependa de pilas ni de una app.",
                        "Un margen de dificultad para que no se quede pequeño en un mes.",
                        "Supervisión si hay accesorios sueltos, cordones o piezas pequeñas."
                ),
                List.of(
                        "simbolico-janod-veterinario",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "green-toys-construccion"
                ),
                Map.of(
                        "simbolico-janod-veterinario",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-simbolico-janod-veterinario",
                        "plantoys-ata-zapato",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-small-foot-grua",
                        "green-toys-construccion",
                        "/comparativas/mejores-regalos-sostenibles-4-anos/#producto-green-toys-construccion"
                ),
                List.of(
                        faq("¿Todo lo de madera es sostenible?",
                                "No. La madera ayuda a la durabilidad, pero no basta. Mira también si el juguete se usará de verdad y si se puede cuidar."),
                        faq("¿Qué evitáis?",
                                "Productos de un solo uso y juguetes que solo funcionan con pantalla. Tampoco afirmamos ecoetiquetas que no hayamos contrastado."),
                        faq("¿Sirve como regalo?",
                                "Sí, si la familia va a repetir esas actividades. Un objeto de madera o plástico reciclado encaja cuando hay un uso real, no solo el día del cumpleaños.")
                ),
                List.of(
                        hubLink(4),
                        new LinkItem(
                                "Mejores regalos sostenibles para 4 años",
                                "/comparativas/mejores-regalos-sostenibles-4-anos/",
                                "Comparativa de madera certificada y plástico reciclado."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 4 años",
                                "/regalos/ideas-regalo-4-anos/",
                                "Más opciones por ocasión, no solo por material."
                        )
                ),
                4
        ));
        register(new EditorialCollection(
                SUSTAINABLE_5_SLUG,
                "/sostenibles/regalos-duraderos-5-anos/",
                "Sostenibles",
                "Regalos y juguetes más duraderos para 5 años",
                "Objetos que se pueden complicar: crear, construir, ensartar o encajar, con materiales pensados para repetir.",
                List.of(
                        "A los 5 años conviene un reto que se pueda terminar y volver a sacar: crear sin pantallas, construir o completar un puzle. La durabilidad cuenta si el objeto acompaña varios meses.",
                        "Priorizamos madera, fieltro, cartón grueso o plástico reciclado declarado, frente a sets de un solo uso. El kit de manualidades tiene análisis propio; el resto del catálogo sostenible cubre esta edad."
                ),
                List.of(
                        "Material que aguante un uso más intenso y sesiones más largas.",
                        "Dificultad que se pueda ajustar sin cambiar de producto.",
                        "Uso que no dependa de pilas ni de una app.",
                        "Edad mínima, piezas y supervisión revisadas antes del uso."
                ),
                List.of(
                        "kit-manualidades-natural",
                        "cuentas-melissa-doug",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "green-toys-construccion"
                ),
                hideAnalysisHrefs(
                        "cuentas-melissa-doug",
                        "plantoys-ata-zapato",
                        "haba-puzles-cuatro-estaciones",
                        "small-foot-grua",
                        "green-toys-construccion"
                ),
                List.of(
                        faq("¿Qué encaja a los 5 años si buscamos durar?",
                                "Un objeto que se pueda complicar: crear, construir o completar. Si ya monta puzles de 15 piezas, elige un reto que no se agote en una tarde."),
                        faq("¿Todo lo de madera es sostenible?",
                                "No. La madera ayuda a la durabilidad, pero no basta. Mira también si el juguete se usará de verdad y si se puede cuidar."),
                        faq("¿Incluís precios o certificaciones una a una?",
                                "No publicamos importes. Tampoco afirmamos ecoetiquetas que no hayamos contrastado en ficha.")
                ),
                List.of(
                        hubLink(5),
                        analysisLink("kit-manualidades-natural", "Kit de manualidades con materiales naturales"),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/regalos/ideas-regalo-5-anos/",
                                "Más opciones por ocasión, no solo por material."
                        )
                ),
                5
        ));
        register(new EditorialCollection(
                GIFTS_5_SLUG,
                "/regalos/ideas-regalo-5-anos/",
                "Regalos",
                "Ideas de regalo para niños de 5 años",
                "Regalos para 5 años elegidos por reto, cooperación, creatividad y uso evolutivo.",
                List.of(
                        "A los 5 años conviene regalar un reto que se pueda terminar y volver a complicar: construir, resolver, cooperar o crear.",
                        "La construcción magnética enlaza a la comparativa STEM; el resto de productos tiene una comparativa o un análisis ya publicado."
                ),
                List.of(
                        "Encaje con los intereses reales del niño.",
                        "Dificultad que se pueda ajustar sin cambiar de producto.",
                        "Reglas o proyectos que terminen en una sesión razonable.",
                        "Edad mínima, piezas e imanes revisados antes del uso."
                ),
                List.of(
                        "set-construccion-magnetico",
                        "juego-mesa-cooperativo",
                        "puzle-madera-animales",
                        "bici-sin-pedales-basica",
                        "torre-aprendizaje-madera",
                        "set-vajilla-infantil",
                        "kit-manualidades-natural"
                ),
                Map.of(
                        "set-construccion-magnetico",
                        "/comparativas/mejores-juguetes-stem-5-anos/#producto-set-construccion-magnetico"
                ),
                List.of(
                        faq("¿Qué regalo educativo encaja a los 5 años?",
                                "Uno que plantee un problema asumible y permita subir la dificultad: construir, completar un puzle o coordinar una partida."),
                        faq("¿Es mejor un juego cooperativo?",
                                "Es útil si quieres practicar acuerdos y turnos sin un ganador individual, pero debe encajar con los intereses del niño."),
                        faq("¿Incluís precios o estrellas?",
                                "No. Son datos variables y no forman parte de esta selección editorial.")
                ),
                List.of(
                        hubLink(5),
                        new LinkItem(
                                "Mejores juguetes STEM para 5 años",
                                "/comparativas/mejores-juguetes-stem-5-anos/",
                                "Cinco opciones existentes para construir y resolver problemas."
                        ),
                        new LinkItem(
                                "Juegos de mesa para 5 años",
                                "/juguetes-educativos/juegos-de-mesa/",
                                "Cooperación, turnos y reglas asumibles."
                        ),
                        chooseByAgeLink()
                ),
                5
        ));
        register(new EditorialCollection(
                BOARD_GAMES_SLUG,
                "/juguetes-educativos/juegos-de-mesa/",
                "Juguetes educativos",
                "Juegos de mesa para niños de 5 años",
                "Juegos cooperativos para practicar turnos, acuerdos y una estrategia sencilla a los 5 años.",
                List.of(
                        "A esta edad ya se pueden encadenar varias reglas breves y tomar decisiones en grupo, siempre con una partida que se pueda terminar.",
                        "La selección reúne seis juegos con ficha viva en Amazon España. El análisis editorial del cooperativo genérico sigue enlazado más abajo."
                ),
                List.of(
                        "Objetivo y turno explicables en pocos minutos.",
                        "Duración compatible con la atención del grupo.",
                        "Componentes seguros para la edad y para hermanos menores.",
                        "Posibilidad de ajustar la dificultad o simplificar reglas."
                ),
                List.of(
                        "lectura-frutalito",
                        "lectura-unicornio-memo",
                        "juego-mesa-animal-sobre-animal",
                        "juego-mesa-dobble-kids",
                        "lectura-three-pigs",
                        "mesa-animal-mini"
                ),
                hideAnalysisHrefs(
                        "lectura-frutalito",
                        "lectura-unicornio-memo",
                        "juego-mesa-animal-sobre-animal",
                        "juego-mesa-dobble-kids",
                        "lectura-three-pigs",
                        "mesa-animal-mini"
                ),
                List.of(
                        faq("¿Cooperativo significa que nadie pierde?",
                                "El grupo gana o pierde unido. Sigue habiendo un resultado, pero no un ganador individual."),
                        faq("¿Puede jugar sin adulto?",
                                "Conviene que un adulto explique la primera partida y ayude a resolver dudas o conflictos."),
                        faq("¿Cuánto debe durar?",
                                "Lo suficiente para terminar antes de que desaparezca la atención. Comprueba la duración del producto concreto.")
                ),
                List.of(
                        educationalToysLink(5),
                        analysisLink("juego-mesa-cooperativo", "Juego de mesa cooperativo"),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/regalos/ideas-regalo-5-anos/",
                                "Más opciones por necesidad e interés."
                        )
                ),
                5
        ));
        registerEducationalCollection(
                SYMBOLIC_PLAY_SLUG,
                "Juguetes de juego simbólico para niños de 3 años",
                "Cocinas y maletines de profesiones para representar situaciones cotidianas a partir de 3 años.",
                "El juego de imitación permite recrear escenas conocidas con objetos claros y manejables.",
                "Revisamos sets de cocina y medicina con funciones comprensibles, piezas resistentes y edad declarada.",
                "Juego simbólico",
                3,
                List.of(
                        "Accesorios reconocibles y adecuados para manos pequeñas.",
                        "Escenario abierto que admita historias diferentes.",
                        "Piezas resistentes y advertencias de seguridad claras.",
                        "Montaje, tamaño y espacio necesarios en casa."
                ),
                List.of(
                        "simbolico-theo-klein-miele", "simbolico-kidkraft-vintage",
                        "simbolico-small-foot-compacta", "simbolico-janod-macaron",
                        "simbolico-janod-veterinario", "simbolico-sundaymot-33"
                )
        );
        registerEducationalCollection(
                SENSORY_TOYS_SLUG,
                "Juguetes sensoriales para niños de 3 años",
                "Tubos, herramientas y piezas manipulables para explorar movimiento, tacto y transferencia a los 3 años.",
                "Una propuesta sensorial debe ofrecer una acción concreta sin depender de luces o estímulos intensos.",
                "Estas opciones se usan con supervisión y respetando la respuesta individual de cada niño.",
                "Sensoriales",
                3,
                List.of(
                        "Materiales lavables, sellados o fáciles de revisar.",
                        "Tamaño compatible con la edad y ausencia de piezas sueltas peligrosas.",
                        "Actividad sensorial identificable: observar, presionar o transferir.",
                        "Supervisión necesaria y facilidad de recogida."
                ),
                List.of(
                        "sensorial-emotion-bottles", "sensorial-playfoam",
                        "sensorial-fidget-tubes", "sensorial-scoops",
                        "sensorial-pinzas-jumbo", "sensorial-hundred-board"
                )
        );
        registerEducationalCollection(
                SMALL_WORLDS_SLUG,
                "Muñecos, figuras y pequeños mundos para niños de 3 años",
                "Figuras de animales para crear escenas, ampliar vocabulario y contar historias desde los 3 años.",
                "Los pequeños mundos funcionan mejor con pocas figuras reconocibles y espacio para inventar.",
                "La selección reúne sets de inicio con edad 3-8 años declarada por el fabricante.",
                "Pequeños mundos",
                3,
                List.of(
                        "Figuras estables y de tamaño seguro para la edad.",
                        "Detalles reconocibles sin mecanismos frágiles.",
                        "Conjunto inicial manejable y ampliable.",
                        "Temática que facilite historias variadas."
                ),
                List.of(
                        "mundos-schleich-foal", "mundos-terra-wild",
                        "mundos-schleich-farm", "mundos-schleich-shire",
                        "mundos-schleich-barn", "mundos-schleich-farm-set"
                )
        );
        registerEducationalCollection(
                MUSICAL_TOYS_SLUG,
                "Juguetes musicales para niños de 3 años",
                "Instrumentos infantiles para explorar pulsación, ritmo y escucha a partir de 3 años.",
                "Un instrumento infantil debe poder producir sonido con una acción sencilla y repetible.",
                "Comparamos seis instrumentos con acciones sencillas, edades compatibles y formatos diferentes.",
                "Musicales",
                3,
                List.of(
                        "Edad del fabricante y advertencias sobre cuerdas o volumen.",
                        "Tamaño y agarre adaptados al niño.",
                        "Sonido controlable y acción musical real.",
                        "Guía de inicio comprensible con ayuda adulta."
                ),
                List.of(
                        "musical-hape-piano", "musical-hape-xylophone",
                        "musical-percussion-8", "musical-hape-drum",
                        "musical-hape-ukulele", "musical-hape-guitar"
                )
        );
        registerEducationalCollection(
                CONSTRUCTION_TOYS_SLUG,
                "Juguetes de construcción para niños de 4 años",
                "Piezas magnéticas para planificar y levantar estructuras abiertas a los 4 años.",
                "La construcción libre permite empezar con formas planas y avanzar hacia volúmenes sencillos.",
                "Las seis opciones combinan ladrillos y piezas magnéticas con distintos tamaños y posibilidades.",
                "Construcción",
                4,
                List.of(
                        "Piezas certificadas y sin daños en bordes o uniones.",
                        "Cantidad suficiente sin abrumar en la primera sesión.",
                        "Compatibilidad para ampliar el set.",
                        "Posibilidades de construcción abierta, no un único modelo."
                ),
                List.of(
                        "construccion-lego-classic-10698", "construccion-lego-classic-10696",
                        "construccion-duplo-10909", "construccion-playmags-32",
                        "construccion-desire-magnetic", "construccion-gears-super"
                )
        );
        registerEducationalCollection(
                ARTS_CRAFTS_SLUG,
                "Arte y manualidades para niños de 4 años",
                "Pintura, modelado y superficies reutilizables para crear con materiales adecuados desde 4 años.",
                "A los 4 años convienen herramientas fáciles de agarrar y proyectos que admitan resultados distintos.",
                "Priorizamos materiales no tóxicos, lavables y con una edad declarada compatible.",
                "Arte y manualidades",
                4,
                List.of(
                        "Materiales no tóxicos y edad indicada por el fabricante.",
                        "Lavabilidad de piel, ropa y superficie de trabajo.",
                        "Herramientas manejables y consumibles identificados.",
                        "Actividad abierta o instrucciones breves."
                ),
                List.of(
                        "arte-crayola-pokemon-5in1", "arte-crayola-case-100",
                        "arte-crayola-paw-patrol", "arte-crayola-tempera-6",
                        "arte-crayola-effects", "arte-crayola-metallic"
                )
        );
        registerEducationalCollection(
                EXPERIMENTATION_SLUG,
                "Juguetes de causa y efecto para niños de 4 años",
                "Imanes, pistas y engranajes para observar relaciones de causa y efecto a los 4 años.",
                "La experimentación resulta visible cuando una acción cambia el recorrido, la atracción o el movimiento.",
                "Revisamos propuestas manipulables con actividades abiertas y edad compatible.",
                null,
                4,
                List.of(
                        "Relación clara entre la acción y el resultado.",
                        "Piezas y advertencias compatibles con 4 años.",
                        "Posibilidad de repetir cambiando una variable.",
                        "Guía útil sin convertir el juego en una ficha escolar."
                ),
                List.of(
                        "experimenta-numberblocks", "experimenta-cuisenaire",
                        "stem-gravitrax-junior", "stem-code-go-mouse",
                        "construccion-gears-super", "stem-geomag-rainbow"
                )
        );
        registerEducationalCollection(
                LITERACY_SLUG,
                "Juegos de lenguaje y lectoescritura para niños de 5 años",
                "Letras y juegos de palabras para reconocer grafías y formar palabras sencillas a los 5 años.",
                "Estas propuestas acompañan el interés por las letras sin exigir que todos los niños lean al mismo ritmo.",
                "Seleccionamos materiales manipulables con niveles breves y acompañamiento adulto posible.",
                "Lectoescritura",
                5,
                List.of(
                        "Idioma de las letras, tarjetas e instrucciones.",
                        "Nivel inicial compatible con reconocimiento de letras.",
                        "Piezas grandes y fáciles de ordenar.",
                        "Progresión sin presión ni ejercicios repetitivos largos."
                ),
                List.of(
                        "lectura-educa-writing", "lectura-smart-panda-magnets",
                        "lectura-diset-leer", "lectura-unicornio-memo",
                        "lectura-frutalito", "lectura-three-pigs"
                )
        );
        registerEducationalCollection(
                MATH_LOGIC_SLUG,
                "Juegos de matemáticas y lógica para niños de 5 años",
                "Conteo, cantidades y operaciones iniciales mediante juegos manipulables para 5 años.",
                "El material concreto ayuda a ver cantidades antes de pasar a símbolos y operaciones.",
                "Las opciones elegidas permiten contar, comparar y resolver retos cortos con dificultad graduable.",
                null,
                5,
                List.of(
                        "Reto inicial explicable con ejemplos concretos.",
                        "Números y cantidades visibles y manipulables.",
                        "Dificultad ajustable sin depender de lectura avanzada.",
                        "Partidas o actividades de duración breve."
                ),
                List.of(
                        "matematicas-sum-swamp", "matematicas-lets-go-code",
                        "experimenta-numberblocks", "experimenta-cuisenaire",
                        "sensorial-hundred-board", "stem-code-go-mouse"
                )
        );
        registerEducationalCollection(
                COOPERATIVE_SEL_SLUG,
                "Juegos cooperativos y socioemocionales para niños de 5 años",
                "Juegos con objetivo compartido para practicar acuerdos, turnos y decisiones en grupo a los 5 años.",
                "En un juego cooperativo el grupo toma decisiones y comparte el resultado de la partida.",
                "Revisamos seis propuestas reales cuya edad declarada incluye los 5 años.",
                null,
                5,
                List.of(
                        "Objetivo común explícito y reglas asumibles.",
                        "Participación de todos durante la partida.",
                        "Duración compatible con la atención del grupo.",
                        "Resultado compartido sin eliminar la necesidad de decidir."
                ),
                List.of(
                        "lectura-frutalito",
                        "lectura-three-pigs",
                        "lectura-unicornio-memo",
                        "mesa-goula-go-gorilla",
                        "mesa-haba-primer-frutal",
                        "mesa-haba-frutal-aniversario"
                )
        );
    }

    private void registerEducationalCollection(
            String slug,
            String h1,
            String metaDescription,
            String introduction,
            String reviewScope,
            String expectedProductCategory,
            int hubAge,
            List<String> buyingCriteria,
            List<String> productIds
    ) {
        register(new EditorialCollection(
                slug,
                "/juguetes-educativos/" + slug + "/",
                "Juguetes educativos",
                h1,
                metaDescription,
                List.of(introduction, reviewScope),
                buyingCriteria,
                productIds,
                productIds.stream().collect(java.util.stream.Collectors.toMap(
                        productId -> productId,
                        productId -> "",
                        (first, second) -> first,
                        LinkedHashMap::new
                )),
                List.of(
                        faq("¿Es una lista ordenada de mejor a peor?",
                                "No. Son opciones revisadas que cumplen la edad y la categoría; la elección depende del niño y del contexto."),
                        faq("¿Cómo comprobáis la edad?",
                                "Contrastamos la recomendación publicada por el fabricante o una ficha comercial identificada."),
                        faq("¿Los enlaces de Amazon están revisados?",
                                "Sí. Cada opción de esta página apunta a un ASIN concreto de Amazon España. El botón solo aparece cuando ese destino ha sido validado.")
                ),
                List.of(educationalToysLink(hubAge), chooseByAgeLink()),
                hubAge,
                expectedProductCategory
        ));
    }

    private void register(EditorialCollection collection) {
        collections.put(collection.slug(), collection);
    }

    private static Map<String, String> hideAnalysisHrefs(String... productIds) {
        Map<String, String> hrefs = new LinkedHashMap<>();
        for (String productId : productIds) {
            hrefs.put(productId, "");
        }
        return hrefs;
    }

    private static Map<String, String> comparisonHrefs(String comparisonSlug, String... productIds) {
        Map<String, String> hrefs = new LinkedHashMap<>();
        for (String productId : productIds) {
            hrefs.put(productId, "/comparativas/" + comparisonSlug + "/#producto-" + productId);
        }
        return hrefs;
    }

    private static Map<String, String> mergeHrefs(Map<String, String> first, Map<String, String> second) {
        Map<String, String> hrefs = new LinkedHashMap<>(first);
        hrefs.putAll(second);
        return hrefs;
    }

    private Product validatedProduct(EditorialCollection editorial, String productId) {
        Product product = productCatalog.findById(productId)
                .orElseThrow(() -> new IllegalStateException(
                        "Unknown product '" + productId + "' in collection '" + editorial.slug() + "'"
                ));
        if (!product.isAvailableForAge(editorial.hubAge())) {
            throw new IllegalStateException(
                    "Product '" + productId + "' does not support age " + editorial.hubAge()
            );
        }
        if (editorial.expectedProductCategory() != null
                && !product.categories().contains(editorial.expectedProductCategory())) {
            throw new IllegalStateException(
                    "Product '" + productId + "' does not belong to category '"
                            + editorial.expectedProductCategory() + "'"
            );
        }
        return product;
    }

    private CollectionPageResponse toResponse(
            EditorialCollection editorial,
            List<CollectionPageResponse.CollectionProduct> products
    ) {
        return new CollectionPageResponse(
                new Seo(
                        EditorialDefaults.canonical(editorial.path()),
                        editorial.h1() + " | Bebes Felices",
                        editorial.metaDescription()
                ),
                PageStatus.PUBLISHED,
                editorial.slug(),
                editorial.path(),
                editorial.hubAge(),
                List.of(
                        new CollectionPageResponse.Breadcrumb("Inicio", "/"),
                        parentBreadcrumb(editorial),
                        new CollectionPageResponse.Breadcrumb(editorial.h1(), editorial.path())
                ),
                new CollectionPageResponse.Header(
                        editorial.kicker(),
                        editorial.h1(),
                        editorial.introduction()
                ),
                editorial.buyingCriteria(),
                products,
                editorial.faq(),
                editorial.related(),
                EditorialDefaults.trustAuthority(),
                EditorialDefaults.affiliation(),
                EditorialDefaults.legalLinks(),
                new CollectionPageResponse.Author(
                        EditorialDefaults.AUTHOR_NAME,
                        EditorialDefaults.AUTHOR_ROLE
                ),
                EditorialDefaults.PUBLISHED_AT,
                EditorialDefaults.UPDATED_AT
        );
    }

    private CollectionPageResponse.CollectionProduct toCollectionProduct(Product product, String href) {
        boolean comparison = href != null && href.contains("/comparativas/");
        String affiliateHref = product.hasValidatedAffiliateLink()
                ? product.affiliateLink().url()
                : null;
        return new CollectionPageResponse.CollectionProduct(
                product.title(),
                product.categories().size() > 1 ? product.categories().get(1) : product.categories().get(0),
                product.description(),
                ageRange(product),
                href,
                affiliateHref,
                href == null ? null : comparison ? "Ver comparativa completa" : "Ver análisis completo"
        );
    }

    private static String ageRange(Product product) {
        if (product.maxAge() == Integer.MAX_VALUE) {
            return "Desde " + product.minAge() + " años";
        }
        return product.minAge() + "-" + product.maxAge() + " años";
    }

    private static CollectionPageResponse.Breadcrumb parentBreadcrumb(EditorialCollection editorial) {
        if (isEducationalToysCollection(editorial)) {
            return new CollectionPageResponse.Breadcrumb(
                    "Juguetes educativos",
                    educationalToysHref(editorial.hubAge())
            );
        }
        return new CollectionPageResponse.Breadcrumb(
                EditorialDefaults.hubLabel(editorial.hubAge()),
                EditorialDefaults.hubHref(editorial.hubAge())
        );
    }

    private static boolean isEducationalToysCollection(EditorialCollection editorial) {
        return editorial.path().startsWith("/juguetes-educativos/");
    }

    private static String educationalToysHref(int age) {
        return "/juguetes-educativos/?edad=" + age;
    }

    private static LinkItem educationalToysLink(int age) {
        return new LinkItem(
                "Juguetes educativos para " + age + " años",
                educationalToysHref(age),
                "Volver a las selecciones educativas de esta edad."
        );
    }

    private static LinkItem hubLink() {
        return hubLink(3);
    }

    private static LinkItem hubLink(int age) {
        return new LinkItem(
                "Juguetes y regalos para niños de " + age + " años",
                EditorialDefaults.hubHref(age),
                "Volver al hub por edad."
        );
    }

    private static LinkItem chooseByAgeLink() {
        return new LinkItem(
                "Cómo elegir juguetes según la edad",
                "/guias/como-elegir-juguetes-por-edad/",
                "Criterios prácticos antes de comprar."
        );
    }

    private static LinkItem analysisLink(String productId, String title) {
        return new LinkItem(
                title,
                "/analisis/" + productId + "/",
                "Análisis editorial de esta opción."
        );
    }

    private static CollectionPageResponse.Faq faq(String question, String answer) {
        return new CollectionPageResponse.Faq(question, answer);
    }

    private record EditorialCollection(
            String slug,
            String path,
            String kicker,
            String h1,
            String metaDescription,
            List<String> introduction,
            List<String> buyingCriteria,
            List<String> productIds,
            Map<String, String> hrefOverrides,
            List<CollectionPageResponse.Faq> faq,
            List<LinkItem> related,
            int hubAge,
            String expectedProductCategory
    ) {
        EditorialCollection(
                String slug,
                String path,
                String kicker,
                String h1,
                String metaDescription,
                List<String> introduction,
                List<String> buyingCriteria,
                List<String> productIds,
                Map<String, String> hrefOverrides,
                List<CollectionPageResponse.Faq> faq,
                List<LinkItem> related
        ) {
            this(
                    slug,
                    path,
                    kicker,
                    h1,
                    metaDescription,
                    introduction,
                    buyingCriteria,
                    productIds,
                    hrefOverrides,
                    faq,
                    related,
                    3,
                    null
            );
        }

        EditorialCollection(
                String slug,
                String path,
                String kicker,
                String h1,
                String metaDescription,
                List<String> introduction,
                List<String> buyingCriteria,
                List<String> productIds,
                Map<String, String> hrefOverrides,
                List<CollectionPageResponse.Faq> faq,
                List<LinkItem> related,
                int hubAge
        ) {
            this(
                    slug,
                    path,
                    kicker,
                    h1,
                    metaDescription,
                    introduction,
                    buyingCriteria,
                    productIds,
                    hrefOverrides,
                    faq,
                    related,
                    hubAge,
                    null
            );
        }

        String productHref(String productId) {
            String href = hrefOverrides.getOrDefault(productId, "/analisis/" + productId + "/");
            return href.isBlank() ? null : href;
        }
    }
}
