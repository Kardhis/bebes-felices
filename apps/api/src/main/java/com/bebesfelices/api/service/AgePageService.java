package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.dto.AgePageResponse;
import com.bebesfelices.api.dto.shared.AgeLink;
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

/**
 * Construye el contenido de las páginas hub por edad (3, 4 y 5 años).
 * <p>
 * Como en {@link com.bebesfelices.api.controller.HomeController}, el
 * contenido es inicialmente mock; cuando exista persistencia y CMS se
 * sustituirá sin cambiar el contrato {@link AgePageResponse}.
 */
@Service
public class AgePageService {

    private static final String PUBLISHED_AT = "2026-08-11";
    private static final String UPDATED_AT = "2026-08-11";
    private static final String BALANCE_BIKE_SPOTLIGHT_ID = "bici-chicco-red-bullet";
    private static final String BOARD_GAME_SPOTLIGHT_ID = "juego-mesa-el-frutal-mini";
    private static final String SCOOTER_SPOTLIGHT_ID = "patinete-micro-mini-deluxe";
    private static final String TOWER_SPOTLIGHT_ID = "torre-yoleo-transformer";
    private static final String TABLEWARE_SPOTLIGHT_ID = "vajilla-twistshake-dividido";
    private static final String SUSTAINABLE_SPOTLIGHT_ID = "cuentas-melissa-doug";
    private static final String STEM_5_SPOTLIGHT_ID = "set-construccion-magnetico";
    private static final String BALANCE_BIKES_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.BALANCE_BIKES_SLUG + "/";
    private static final String BOARD_GAMES_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.BOARD_GAMES_SLUG + "/";
    private static final String SCOOTERS_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.SCOOTERS_SLUG + "/";
    private static final String TOWERS_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.TOWERS_SLUG + "/";
    private static final String TABLEWARE_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.TABLEWARE_SLUG + "/";
    private static final String SUSTAINABLE_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.SUSTAINABLE_SLUG + "/";
    private static final String STEM_5_COMPARISON_HREF = "/comparativas/"
            + ComparisonPageService.STEM_5_SLUG + "/";

    private static final List<String> AGE_3_PRODUCT_IDS = List.of(
            "juego-montessori-formas",
            "puzle-madera-animales",
            BALANCE_BIKE_SPOTLIGHT_ID,
            "patinete-3-ruedas",
            "torre-aprendizaje-madera",
            "set-vajilla-infantil",
            "kit-manualidades-natural"
    );
    private static final List<String> AGE_4_PRODUCT_IDS = List.of(
            "juego-montessori-formas",
            "puzle-madera-animales",
            "bici-sin-pedales-basica",
            SCOOTER_SPOTLIGHT_ID,
            TOWER_SPOTLIGHT_ID,
            TABLEWARE_SPOTLIGHT_ID,
            "set-construccion-magnetico",
            BOARD_GAME_SPOTLIGHT_ID,
            SUSTAINABLE_SPOTLIGHT_ID
    );
    private static final List<String> AGE_5_PRODUCT_IDS = List.of(
            "puzle-madera-animales",
            "bici-sin-pedales-basica",
            "torre-aprendizaje-madera",
            "set-vajilla-infantil",
            STEM_5_SPOTLIGHT_ID,
            "juego-mesa-cooperativo",
            "kit-manualidades-natural"
    );

    private static final Map<Integer, String> AGE_LABELS = Map.of(
            3, "3 años",
            4, "4 años",
            5, "5 años"
    );

    private final ProductCatalog productCatalog;

    public AgePageService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Optional<AgePageResponse> getBySlug(String slug) {
        return switch (slug) {
            case "3-anos" -> Optional.of(buildFor(3));
            case "4-anos" -> Optional.of(buildFor(4));
            case "5-anos" -> Optional.of(buildFor(5));
            default -> Optional.empty();
        };
    }

    private AgePageResponse buildFor(int age) {
        String slug = age + "-anos";
        String ageLabel = AGE_LABELS.get(age);
        String canonicalUrl = "https://bebesfelices.es/por-edad/" + slug + "/";

        return new AgePageResponse(
                new Seo(
                        canonicalUrl,
                        "Mejores juguetes y regalos para niños de " + age + " años | Bebes Felices",
                        metaDescriptionFor(age)
                ),
                age,
                ageLabel,
                slug,
                breadcrumbsFor(ageLabel, canonicalUrl),
                headerFor(age),
                quickNavigationFor(age),
                quickSummaryFor(age),
                optionsByNeedFor(age),
                featuredSelectionFor(age),
                developmentSkillsFor(age),
                buyingConsiderationsFor(age),
                featuredGuidesFor(age),
                featuredRankingsFor(age),
                giftIdeasFor(age),
                informativeArticlesFor(age),
                faqFor(age),
                otherAgesExcluding(age),
                trustAuthority(),
                affiliation(),
                legalLinks(),
                new AgePageResponse.Author("Equipo editorial BebesFelices", "Redacción"),
                PUBLISHED_AT,
                UPDATED_AT
        );
    }

    private String metaDescriptionFor(int age) {
        return "Guías, comparativas y una selección editorial de juguetes educativos, "
                + "movimiento y autonomía para niños de " + age
                + " años, con criterios claros y transparencia de afiliación.";
    }

    private List<AgePageResponse.Breadcrumb> breadcrumbsFor(String ageLabel, String canonicalUrl) {
        return List.of(
                new AgePageResponse.Breadcrumb("Inicio", "/"),
                new AgePageResponse.Breadcrumb("Por edad", "/#por-edad"),
                new AgePageResponse.Breadcrumb(ageLabel, canonicalUrl)
        );
    }

    private AgePageResponse.Header headerFor(int age) {
        String h1 = "Mejores juguetes y regalos para niños de " + age + " años";
        List<String> introductionParagraphs = switch (age) {
            case 3 -> List.of(
                    "A los 3 años el lenguaje avanza muy rápido, aparece el juego simbólico y crece el "
                            + "interés por imitar a los adultos en tareas cotidianas.",
                    "La motricidad gruesa mejora, aunque el equilibrio y la coordinación todavía se están "
                            + "asentando, por lo que conviene elegir productos robustos, de piezas grandes y "
                            + "fáciles de manipular.",
                    "Es una edad en la que la supervisión sigue siendo constante y las sesiones de juego "
                            + "autónomo son cortas, así que los objetos más útiles son los que permiten éxito "
                            + "rápido y repetición sin frustración.",
                    "En esta página reunimos juguetes educativos y Montessori para practicar formas y "
                            + "clasificación, primeras bicicletas sin pedales y patinetes estables para moverse "
                            + "con seguridad, y productos de autonomía como torres de aprendizaje o vajilla "
                            + "adaptada para las rutinas diarias.",
                    "También encontrarás ideas de regalo pensadas específicamente para esta edad. Cada "
                            + "recomendación se organiza según la necesidad que resuelve, no según el precio, "
                            + "para que puedas decidir con criterio."
            );
            case 4 -> List.of(
                    "A los 4 años la coordinación motriz mejora notablemente: se afina la motricidad fina "
                            + "necesaria para recortar, enhebrar o dibujar con más control, y el equilibrio "
                            + "permite avanzar hacia actividades de movimiento más exigentes.",
                    "Aumenta también la capacidad de atención, el interés por los juegos con reglas sencillas "
                            + "y las primeras dinámicas cooperativas con otros niños. Es habitual que empiece a "
                            + "interesarse por las letras, los números y por juguetes que planteen pequeños retos "
                            + "de construcción o lógica.",
                    "En esta página encontrarás juguetes educativos con un componente STEM más presente, "
                            + "bicicletas sin pedales pensadas para ganar seguridad antes de dar el salto a la "
                            + "bicicleta con pedales, y productos de autonomía que acompañan la independencia "
                            + "creciente en la rutina diaria.",
                    "Añadimos también juegos de mesa cooperativos e ideas de regalo adaptadas a esta edad, "
                            + "evitando valoraciones inventadas o listados genéricos."
            );
            case 5 -> List.of(
                    "A los 5 años muchos niños se preparan para una etapa escolar más estructurada: aumenta "
                            + "la capacidad de concentración, mejora el equilibrio y la coordinación para "
                            + "actividades de movimiento más avanzadas, y crece el interés por juegos de "
                            + "estrategia sencilla, construcción compleja y primeras nociones de lectura y números.",
                    "También es habitual que quieran completar proyectos creativos más largos y participar "
                            + "en las decisiones sobre lo que quieren jugar o construir.",
                    "En esta página reunimos juguetes educativos con más complejidad, opciones de movimiento "
                            + "para consolidar el equilibrio ya adquirido, productos de autonomía pensados para "
                            + "tareas domésticas algo más avanzadas y juegos de mesa cooperativos con partidas "
                            + "más largas.",
                    "Sumamos ideas de regalo y contenidos informativos sobre qué esperar en esta etapa, con "
                            + "actualización visible y sin datos comerciales que no podamos verificar."
            );
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
        return new AgePageResponse.Header(h1, introductionParagraphs);
    }

    private List<AgePageResponse.QuickNavItem> quickNavigationFor(@SuppressWarnings("unused") int age) {
        return List.of();
    }

    private List<AgePageResponse.QuickSummaryItem> quickSummaryFor(@SuppressWarnings("unused") int age) {
        return List.of();
    }

    private List<AgePageResponse.NeedGroup> optionsByNeedFor(int age) {
        String giftHref = "/regalos/ideas-regalo-" + age + "-anos/";
        return switch (age) {
            case 3 -> List.of(
                    new AgePageResponse.NeedGroup("Para aprender jugando", "#para-aprender", List.of(
                            new LinkItem("Juegos Montessori de formas y encajes", "/juguetes-educativos/juegos-montessori/", "Clasificación de formas, colores y tamaños con piezas grandes."),
                            new LinkItem("Puzles de piezas grandes", "/juguetes-educativos/puzles/", "Motricidad fina sin piezas pequeñas de riesgo.")
                    )),
                    new AgePageResponse.NeedGroup("Para moverse con seguridad", "#para-moverse", List.of(
                            new LinkItem("Mejores bicicletas sin pedales para 3 años", BALANCE_BIKES_COMPARISON_HREF, "Comparativa de modelos ligeros para iniciarse con seguridad."),
                            new LinkItem("Patinetes de 3 ruedas", "/movimiento/patinetes/", "Estabilidad extra para el juego al aire libre.")
                    )),
                    new AgePageResponse.NeedGroup("Para ganar autonomía", "#para-autonomia", List.of(
                            new LinkItem("Torres de aprendizaje", "/autonomia/torres-de-aprendizaje/", "Participar en la cocina con seguridad."),
                            new LinkItem("Vajilla infantil irrompible", "/autonomia/vajilla-infantil/", "Practicar comer sin ayuda.")
                    )),
                    new AgePageResponse.NeedGroup("Para regalar", "#para-regalar", List.of(
                            new LinkItem("Ideas de regalo para 3 años", giftHref, "Selección por ocasión y presupuesto."),
                            new LinkItem("Regalos sostenibles", "/sostenibles/", "Opciones más duraderas y materiales responsables.")
                    ))
            );
            case 4 -> List.of(
                    new AgePageResponse.NeedGroup("Para aprender jugando", "#para-aprender", List.of(
                            new LinkItem("Sets de construcción magnética", "/juguetes-educativos/juegos-stem/", "Lógica espacial y estructuras estables."),
                            new LinkItem("Juegos de mesa cooperativos", BOARD_GAMES_COMPARISON_HREF, "Comparativa de opciones cooperativas y de turnos cortos.")
                    )),
                    new AgePageResponse.NeedGroup("Para moverse con seguridad", "#para-moverse", List.of(
                            new LinkItem("Bicicletas sin pedales para ganar seguridad", "/movimiento/bicicletas-sin-pedales/", "Modelos para consolidar el equilibrio antes de la bici con pedales."),
                            new LinkItem("Patinetes y triciclos", SCOOTERS_COMPARISON_HREF, "Comparativa de patinetes de tres ruedas y un triciclo para más autonomía al aire libre.")
                    )),
                    new AgePageResponse.NeedGroup("Para ganar autonomía", "#para-autonomia", List.of(
                            new LinkItem("Mobiliario infantil adaptado", TOWERS_COMPARISON_HREF, "Comparativa de torres de aprendizaje para participar en la cocina con seguridad."),
                            new LinkItem("Utensilios para la rutina diaria", TABLEWARE_COMPARISON_HREF, "Comparativa de vajilla y vasos para comer y beber con menos ayuda.")
                    )),
                    new AgePageResponse.NeedGroup("Para regalar", "#para-regalar", List.of(
                            new LinkItem("Ideas de regalo para 4 años", giftHref, "Selección por ocasión y presupuesto."),
                            new LinkItem("Regalos sostenibles", SUSTAINABLE_COMPARISON_HREF, "Comparativa de madera certificada y plástico reciclado.")
                    ))
            );
            case 5 -> List.of(
                    new AgePageResponse.NeedGroup("Para aprender jugando", "#para-aprender", List.of(
                            new LinkItem("Construcción avanzada", STEM_5_COMPARISON_HREF, "Comparativa de retos de lógica, mecanismos y estructuras."),
                            new LinkItem("Juegos de mesa cooperativos", "/juguetes-educativos/juegos-de-mesa/", "Partidas más largas con estrategia compartida.")
                    )),
                    new AgePageResponse.NeedGroup("Para moverse con seguridad", "#para-moverse", List.of(
                            new LinkItem("Bicicletas sin pedales para consolidar equilibrio", "/movimiento/bicicletas-sin-pedales/", "El paso previo a la bicicleta con pedales."),
                            new LinkItem("Patinetes de dos ruedas", "/movimiento/patinetes/", "Mayor exigencia de equilibrio y control.")
                    )),
                    new AgePageResponse.NeedGroup("Para ganar autonomía", "#para-autonomia", List.of(
                            new LinkItem("Organización y tareas domésticas", "/autonomia/torres-de-aprendizaje/", "Participación más completa en la rutina familiar."),
                            new LinkItem("Utensilios y hábitos personales", "/autonomia/vajilla-infantil/", "Independencia en comidas y cuidado personal.")
                    )),
                    new AgePageResponse.NeedGroup("Para regalar", "#para-regalar", List.of(
                            new LinkItem("Ideas de regalo para 5 años", giftHref, "Selección por ocasión y presupuesto."),
                            new LinkItem("Regalos sostenibles", "/sostenibles/", "Opciones más duraderas y materiales responsables.")
                    ))
            );
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
    }

    private List<AgePageResponse.FeaturedProduct> featuredSelectionFor(int age) {
        List<String> productIds = switch (age) {
            case 3 -> AGE_3_PRODUCT_IDS;
            case 4 -> AGE_4_PRODUCT_IDS;
            case 5 -> AGE_5_PRODUCT_IDS;
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
        return productCatalog.findByIds(productIds).stream()
                .filter(product -> product.isAvailableForAge(age))
                .map(product -> toFeaturedProduct(product, age))
                .toList();
    }

    private AgePageResponse.FeaturedProduct toFeaturedProduct(Product product, int age) {
        String affiliateHref = product.hasValidatedAffiliateLink()
                ? product.affiliateLink().url()
                : null;
        String comparisonHref = comparisonHrefFor(product.id(), age);
        boolean linksToComparison = comparisonHref != null;
        String href = linksToComparison
                ? comparisonHref + "#producto-" + product.id()
                : "/analisis/" + product.id() + "/";
        String ctaLabel = linksToComparison ? "Ver comparativa completa" : "Ver análisis completo";
        String ageRange = product.maxAge() == Integer.MAX_VALUE
                ? "Desde " + product.minAge() + " años"
                : product.minAge() + "-" + product.maxAge() + " años";
        return new AgePageResponse.FeaturedProduct(
                product.title(),
                product.categories().get(0),
                reasonFor(product.id()),
                ageRange,
                href,
                affiliateHref,
                ctaLabel
        );
    }

    private String comparisonHrefFor(String productId, int age) {
        if (age == 3 && BALANCE_BIKE_SPOTLIGHT_ID.equals(productId)) {
            return BALANCE_BIKES_COMPARISON_HREF;
        }
        if (age == 5 && STEM_5_SPOTLIGHT_ID.equals(productId)) {
            return STEM_5_COMPARISON_HREF;
        }
        if (age != 4) {
            return null;
        }
        return switch (productId) {
            case BOARD_GAME_SPOTLIGHT_ID -> BOARD_GAMES_COMPARISON_HREF;
            case SCOOTER_SPOTLIGHT_ID -> SCOOTERS_COMPARISON_HREF;
            case TOWER_SPOTLIGHT_ID -> TOWERS_COMPARISON_HREF;
            case TABLEWARE_SPOTLIGHT_ID -> TABLEWARE_COMPARISON_HREF;
            case SUSTAINABLE_SPOTLIGHT_ID -> SUSTAINABLE_COMPARISON_HREF;
            default -> null;
        };
    }

    private String reasonFor(String productId) {
        return switch (productId) {
            case "juego-montessori-formas" -> "Piezas de madera de encaje que ayudan a clasificar formas y colores sin necesidad de supervisión constante.";
            case "puzle-madera-animales" -> "Piezas grandes y resistentes, ideales para practicar motricidad fina sin piezas pequeñas de riesgo.";
            case "bici-sin-pedales-basica" -> "Cuadro ligero y sillín regulable en altura, pensado para progresar en equilibrio de forma segura.";
            case BALANCE_BIKE_SPOTLIGHT_ID -> "Una bicicleta ligera con sillín y manillar ajustables, incluida en nuestra comparativa para iniciarse con seguridad.";
            case BOARD_GAME_SPOTLIGHT_ID -> "Un cooperativo de reglas mínimas, con fruta de madera y un cuervo al que hay que adelantarse, incluido en nuestra comparativa para 4 años.";
            case SCOOTER_SPOTLIGHT_ID -> "Un patinete ligero de tres ruedas con giro por inclinación, incluido en nuestra comparativa para moverse de pie con más autonomía.";
            case TOWER_SPOTLIGHT_ID -> "Torre plegable convertible en escritorio, incluida en nuestra comparativa para participar en la cocina con un adulto.";
            case TABLEWARE_SPOTLIGHT_ID -> "Plato con compartimentos y tapa, incluido en nuestra comparativa para comer con más autonomía.";
            case SUSTAINABLE_SPOTLIGHT_ID -> "Cuentas de madera para ensartar y contar, incluidas en nuestra comparativa de regalos de materiales declarados.";
            case "patinete-3-ruedas" -> "Base de tres ruedas que aporta estabilidad extra mientras se afianza el equilibrio.";
            case "torre-aprendizaje-madera" -> "Plataforma con barandilla que permite participar en la cocina con una altura segura y regulable.";
            case "set-vajilla-infantil" -> "Piezas irrompibles y de tamaño adaptado para practicar comer de forma autónoma.";
            case "set-construccion-magnetico" -> "Piezas magnéticas que facilitan construir estructuras estables y practicar lógica espacial.";
            case "juego-mesa-cooperativo" -> "Partidas cortas en las que se gana o se pierde en equipo, ideales para introducir turnos y reglas.";
            case "kit-manualidades-natural" -> "Materiales naturales para crear sin depender de pantallas, con actividades de dificultad progresiva.";
            default -> throw new IllegalArgumentException("Producto sin motivo editorial definido: " + productId);
        };
    }

    private List<AgePageResponse.DevelopmentSkill> developmentSkillsFor(int age) {
        return switch (age) {
            case 3 -> List.of(
                    new AgePageResponse.DevelopmentSkill("Lenguaje y vocabulario", "El juego con adultos y con otros niños amplía el vocabulario y la construcción de frases."),
                    new AgePageResponse.DevelopmentSkill("Juego simbólico", "Empieza a representar situaciones cotidianas mediante el juego, base de la creatividad futura."),
                    new AgePageResponse.DevelopmentSkill("Motricidad gruesa", "Camina, corre y empieza a mantener el equilibrio en superficies inestables con apoyo."),
                    new AgePageResponse.DevelopmentSkill("Motricidad fina", "Encaja, apila y manipula piezas grandes con mayor precisión que el año anterior."),
                    new AgePageResponse.DevelopmentSkill("Autonomía inicial", "Comienza a participar en tareas sencillas de la rutina diaria con supervisión cercana.")
            );
            case 4 -> List.of(
                    new AgePageResponse.DevelopmentSkill("Atención sostenida", "Puede mantener la concentración en una actividad durante periodos algo más largos."),
                    new AgePageResponse.DevelopmentSkill("Motricidad fina avanzada", "Mejora el control necesario para recortar, enhebrar o dibujar formas reconocibles."),
                    new AgePageResponse.DevelopmentSkill("Equilibrio y coordinación", "Gana estabilidad para actividades de movimiento más exigentes, como la bicicleta sin pedales."),
                    new AgePageResponse.DevelopmentSkill("Juego con reglas", "Empieza a entender y respetar turnos y normas sencillas en juegos compartidos."),
                    new AgePageResponse.DevelopmentSkill("Interés por letras y números", "Curiosidad creciente por identificar símbolos, contar y reconocer patrones.")
            );
            case 5 -> List.of(
                    new AgePageResponse.DevelopmentSkill("Pensamiento lógico", "Resuelve retos de construcción y clasificación con estrategias más planificadas."),
                    new AgePageResponse.DevelopmentSkill("Equilibrio consolidado", "Consolida el equilibrio adquirido y puede afrontar actividades de movimiento más avanzadas."),
                    new AgePageResponse.DevelopmentSkill("Juego cooperativo", "Coopera con otros niños hacia un objetivo común, no solo por turnos."),
                    new AgePageResponse.DevelopmentSkill("Autonomía en tareas", "Completa tareas domésticas sencillas de principio a fin con menos supervisión."),
                    new AgePageResponse.DevelopmentSkill("Primeras nociones de lectura y números", "Muestra interés por letras, sonidos y cantidades en contextos de juego.")
            );
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
    }

    private List<String> buyingConsiderationsFor(int age) {
        return switch (age) {
            case 3 -> List.of(
                    "Comprueba la edad mínima indicada por el fabricante y evita piezas pequeñas que supongan riesgo de asfixia.",
                    "Prioriza materiales resistentes y bordes redondeados, ya que el juego suele ser intenso y poco delicado.",
                    "Mantén la supervisión activa durante el uso, especialmente en juguetes de movimiento.",
                    "Elige productos con dimensiones y peso adecuados para que el niño pueda manipularlos sin ayuda.",
                    "Revisa la fecha de última actualización de la página antes de decidir: los catálogos cambian con frecuencia.",
                    "Valora la durabilidad frente al precio: a esta edad el ritmo de desgaste es alto."
            );
            case 4 -> List.of(
                    "Verifica que el producto permita cierto margen de dificultad creciente, en lugar de quedarse pequeño en pocos meses.",
                    "Comprueba el rango de altura o edad recomendado para bicicletas y patinetes antes de comprar.",
                    "Da preferencia a juegos con reglas breves y explicables en pocos minutos.",
                    "Ten en cuenta el espacio disponible en casa para juguetes de construcción con muchas piezas.",
                    "Revisa si el producto requiere mantenimiento (pilas, ajustes, limpieza) antes de decidir.",
                    "Compara opciones similares por utilidad real, no solo por diseño o color."
            );
            case 5 -> List.of(
                    "Elige productos que planteen un reto real, evitando juguetes pensados para edades muy inferiores.",
                    "Comprueba que los juegos de mesa cooperativos incluyan variantes de dificultad para que no se agoten rápido.",
                    "Revisa el tiempo medio de partida o de uso: a esta edad ya toleran sesiones más largas.",
                    "Valora si el producto admite un uso evolutivo, es decir, que siga siendo útil en los próximos cursos.",
                    "Ten en cuenta las preferencias del niño o la niña, no solo el criterio adulto sobre lo educativo.",
                    "Revisa la fecha de actualización de la página y la metodología antes de decidir."
            );
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
    }

    private List<LinkItem> featuredGuidesFor(int age) {
        return List.of(
                new LinkItem(
                        "Cómo elegir juguetes según la edad",
                        "/guias/como-elegir-juguetes-por-edad/?edad=" + age,
                        "Criterios prácticos para 3, 4 y 5 años."
                )
        );
    }

    private List<LinkItem> featuredRankingsFor(int age) {
        return switch (age) {
            case 3 -> List.of(new LinkItem(
                    "Mejores bicicletas sin pedales para 3 años",
                    BALANCE_BIKES_COMPARISON_HREF,
                    "Comparativa por seguridad, talla y facilidad de uso."
            ));
            case 4 -> List.of(
                    new LinkItem(
                            "Mejores juegos de mesa para 4 años",
                            BOARD_GAMES_COMPARISON_HREF,
                            "Opciones cooperativas y de turnos cortos."
                    ),
                    new LinkItem(
                            "Mejores patinetes y triciclos para 4 años",
                            SCOOTERS_COMPARISON_HREF,
                            "Tres ruedas de pie o triciclo sentado, según la necesidad."
                    ),
                    new LinkItem(
                            "Mejores torres de aprendizaje para 4 años",
                            TOWERS_COMPARISON_HREF,
                            "Estabilidad, altura y plegado para la cocina."
                    ),
                    new LinkItem(
                            "Mejores vajillas infantiles para 4 años",
                            TABLEWARE_COMPARISON_HREF,
                            "Platos, vasos y cuencos para la mesa diaria."
                    ),
                    new LinkItem(
                            "Mejores regalos sostenibles para 4 años",
                            SUSTAINABLE_COMPARISON_HREF,
                            "Madera certificada o plástico reciclado, sin pantallas."
                    )
            );
            case 5 -> List.of(new LinkItem(
                    "Mejores juguetes STEM para 5 años",
                    STEM_5_COMPARISON_HREF,
                    "Construcción, lógica y experimentación."
            ));
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
    }

    private List<LinkItem> giftIdeasFor(int age) {
        return List.of(new LinkItem(
                "Ideas de regalo para niños de " + age + " años",
                "/regalos/ideas-regalo-" + age + "-anos/",
                "Selección por ocasión y presupuesto sin perder utilidad."
        ));
    }

    private List<LinkItem> informativeArticlesFor(int age) {
        return List.of(new LinkItem(
                "Qué habilidades desarrolla un niño de " + age + " años",
                "/guias/habilidades-" + age + "-anos/",
                "Explicación práctica del desarrollo esperable a esta edad, con ejemplos de juego."
        ));
    }

    private List<AgePageResponse.Faq> faqFor(int age) {
        return switch (age) {
            case 3 -> List.of(
                    new AgePageResponse.Faq(
                            "¿Qué juguetes son adecuados para un niño de 3 años?",
                            "En general, piezas grandes, resistentes y fáciles de manipular: juegos Montessori, puzles de pocas piezas y primeros juguetes de movimiento con buena estabilidad."
                    ),
                    new AgePageResponse.Faq(
                            "¿Es buena edad para empezar con la bicicleta sin pedales?",
                            "Sí, es una de las edades habituales para iniciarse, siempre con un modelo ligero y un sillín ajustado a la altura del niño."
                    ),
                    new AgePageResponse.Faq(
                            "¿Cuánto tiempo de juego autónomo puede tener un niño de 3 años?",
                            "Suelen ser periodos cortos; lo importante es que el juguete permita éxito rápido y no dependa de instrucciones complejas."
                    ),
                    new AgePageResponse.Faq(
                            "¿Qué debo evitar por seguridad a esta edad?",
                            "Piezas pequeñas que puedan tragarse, materiales frágiles y productos sin el rango de edad claramente indicado por el fabricante."
                    )
            );
            case 4 -> List.of(
                    new AgePageResponse.Faq(
                            "¿Qué diferencia hay respecto a los juguetes de 3 años?",
                            "A los 4 años se pueden introducir retos algo mayores: construcción con más piezas, juegos con reglas sencillas y actividades de movimiento más exigentes."
                    ),
                    new AgePageResponse.Faq(
                            "¿Cuándo pasar de patinete de 3 ruedas a bicicleta sin pedales?",
                            "Depende del niño; muchas familias lo hacen alrededor de esta edad, cuando el equilibrio ya es más estable."
                    ),
                    new AgePageResponse.Faq(
                            "¿Qué juegos de mesa son adecuados a los 4 años?",
                            "Los cooperativos de turnos cortos suelen funcionar mejor que los competitivos con reglas largas."
                    ),
                    new AgePageResponse.Faq(
                            "¿Cómo elegir un regalo útil para esta edad?",
                            "Prioriza productos que resuelvan una necesidad concreta (movimiento, autonomía o juego educativo) frente a artículos puramente decorativos."
                    )
            );
            case 5 -> List.of(
                    new AgePageResponse.Faq(
                            "¿Qué cambia en los juguetes recomendados a los 5 años?",
                            "Se pueden plantear retos más complejos: construcción avanzada, juegos de mesa con partidas más largas y mayor autonomía en las tareas."
                    ),
                    new AgePageResponse.Faq(
                            "¿Es buena edad para dar el salto a la bicicleta con pedales?",
                            "Muchos niños ya tienen el equilibrio necesario, aunque conviene seguir el ritmo individual y no solo la edad."
                    ),
                    new AgePageResponse.Faq(
                            "¿Cómo saber si un juguete educativo sigue siendo un reto?",
                            "Si lo resuelve sin apenas esfuerzo en poco tiempo, probablemente necesite un nivel de dificultad mayor."
                    ),
                    new AgePageResponse.Faq(
                            "¿Qué buscar en un regalo para esta edad?",
                            "Productos que admitan un uso evolutivo y que respondan a los intereses reales del niño, no solo al criterio adulto sobre lo educativo."
                    )
            );
            default -> throw new IllegalArgumentException("Edad no soportada: " + age);
        };
    }

    private List<AgeLink> otherAgesExcluding(int age) {
        Map<Integer, String> remaining = new LinkedHashMap<>(AGE_LABELS);
        remaining.remove(age);
        return remaining.entrySet().stream()
                .map(entry -> new AgeLink(entry.getValue(), "/por-edad/" + entry.getKey() + "-anos/"))
                .toList();
    }

    private TrustAuthority trustAuthority() {
        return new TrustAuthority(
                "Seleccionamos productos con criterios explícitos de edad, seguridad, utilidad y relación calidad-precio. Distinguimos entre investigación, opiniones de compradores y experiencia propia; no inventamos puntuaciones.",
                List.of(
                        "Edad recomendada y encaje con desarrollo y seguridad.",
                        "Utilidad educativa y facilidad de uso en casa.",
                        "Durabilidad y opiniones reales de compradores.",
                        "Relación calidad-precio y actualización del contenido."
                ),
                List.of(
                        "Aviso visible de Amazon Afiliados.",
                        "Fecha de actualización en la página.",
                        "Enlace a la metodología editorial."
                )
        );
    }

    private Affiliation affiliation() {
        return new Affiliation(
                "Bebes Felices participa en el Programa de Afiliados de Amazon. Algunos enlaces de esta página son enlaces de afiliado. Esto significa que podemos recibir una comisión si realizas una compra, sin que el precio cambie para ti.",
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
}
