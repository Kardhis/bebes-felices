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
    public static final String SUSTAINABLE_SLUG = "sostenibles";
    public static final String GIFTS_3_SLUG = "ideas-regalo-3-anos";

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
                .map(productCatalog::findById)
                .flatMap(Optional::stream)
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
                List.of("juego-montessori-formas"),
                Map.of(),
                List.of(
                        faq("¿Hace falta un material «Montessori» certificado?",
                                "No. Lo útil es la actividad: clasificar y encajar con autonomía y piezas seguras. El nombre comercial no sustituye el criterio de edad y seguridad."),
                        faq("¿Cuántas piezas son demasiadas a los 3 años?",
                                "Si el niño no puede terminar una ronda sin frustrarse, sobran. Empieza por un set reducido y añade dificultad más adelante."),
                        faq("¿Se puede usar sin un adulto encima?",
                                "Con supervisión cercana, sí, cuando las piezas son grandes y la actividad es evidente. No es un juguete para dejar solo en una habitación.")
                ),
                List.of(
                        hubLink(),
                        analysisLink("juego-montessori-formas", "Juego Montessori de formas y encajes"),
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
                List.of("puzle-madera-animales"),
                Map.of(),
                List.of(
                        faq("¿Cuántas piezas recomendáis a los 3 años?",
                                "Las justas para terminar en una sesión corta. Un puzle de madera de animales con piezas grandes suele encajar mejor que uno de decenas de piezas pequeñas."),
                        faq("¿Cartón o madera?",
                                "Ambos valen si son gruesos. La madera suele durar más en un uso intenso; el cartón fino se dobla y frustra."),
                        faq("¿Qué hacer si se rinde a mitad?",
                                "Reduce el número de piezas a la vista, nombra lo que falta y termina juntos. El objetivo es el éxito repetible, no el récord.")
                ),
                List.of(
                        hubLink(),
                        analysisLink("puzle-madera-animales", "Puzle de madera de animales"),
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
                List.of("patinete-3-ruedas"),
                Map.of(),
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
                        analysisLink("patinete-3-ruedas", "Patinete de 3 ruedas"),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "La otra vía de movimiento con ranking editorial."
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
                List.of("torre-aprendizaje-madera"),
                Map.of(),
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
                        analysisLink("torre-aprendizaje-madera", "Torre de aprendizaje de madera"),
                        new LinkItem(
                                "Vajilla infantil irrompible",
                                "/autonomia/vajilla-infantil/",
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
                List.of("set-vajilla-infantil"),
                Map.of(),
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
                        analysisLink("set-vajilla-infantil", "Set de vajilla infantil irrompible"),
                        new LinkItem(
                                "Torres de aprendizaje",
                                "/autonomia/torres-de-aprendizaje/",
                                "Para participar en la cocina con una altura segura."
                        )
                )
        ));
        register(new EditorialCollection(
                SUSTAINABLE_SLUG,
                "/sostenibles/",
                "Sostenibles",
                "Regalos y juguetes más duraderos para 3 años",
                "Opciones pensadas para durar: materiales resistentes, menos piezas de usar y tirar, y un uso que no dependa de pantallas.",
                List.of(
                        "Sostenible, en esta página, significa sobre todo durabilidad y materiales que aguanten el juego intenso de los 3 años. No es un sello publicitario ni una lista de certificaciones que no podamos verificar una a una.",
                        "Priorizamos objetos de madera, fieltro o cartón grueso que se puedan reparar o reutilizar, frente a sets de un solo uso. El kit de manualidades con materiales naturales es el ejemplo del catálogo para esta edad."
                ),
                List.of(
                        "Material que sobreviva a caídas y al agua ocasional de las manos.",
                        "Pocas piezas de recambio imposible; mejor un objeto completo.",
                        "Uso que no dependa de pilas ni de una app.",
                        "Encaje real con 3 años: piezas grandes y sesiones cortas."
                ),
                List.of("kit-manualidades-natural"),
                Map.of(),
                List.of(
                        faq("¿Todo lo de madera es sostenible?",
                                "No. La madera ayuda a la durabilidad, pero no basta. Mira también si el juguete se usará de verdad y si se puede cuidar."),
                        faq("¿Qué evitáis?",
                                "Productos de un solo uso, piezas diminutas y juguetes que solo funcionan con pantalla. Tampoco afirmamos ecoetiquetas que no hayamos contrastado."),
                        faq("¿Sirve como regalo?",
                                "Sí, si la familia va a usar esas actividades. Un kit de crear sin pantallas encaja bien cuando hay un adulto dispuesto a acompañar un rato.")
                ),
                List.of(
                        hubLink(),
                        analysisLink("kit-manualidades-natural", "Kit de manualidades con materiales naturales"),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/regalos/ideas-regalo-3-anos/",
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
                        "Aquí reunimos las opciones del catálogo para esta edad. La bicicleta sin pedales se recomienda desde la comparativa; el resto tiene análisis propio."
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
                        "patinete-3-ruedas",
                        "torre-aprendizaje-madera",
                        "set-vajilla-infantil",
                        "kit-manualidades-natural"
                ),
                Map.of(
                        "bici-chicco-red-bullet",
                        "/comparativas/mejores-bicicletas-sin-pedales-3-anos/#producto-bici-chicco-red-bullet"
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
                                "/sostenibles/",
                                "Enfoque en materiales y uso a largo plazo."
                        ),
                        chooseByAgeLink()
                )
        ));
    }

    private void register(EditorialCollection collection) {
        collections.put(collection.slug(), collection);
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
                List.of(
                        new CollectionPageResponse.Breadcrumb("Inicio", "/"),
                        new CollectionPageResponse.Breadcrumb(
                                EditorialDefaults.HUB_3_LABEL,
                                EditorialDefaults.HUB_3_HREF
                        ),
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
        boolean comparison = href.contains("/comparativas/");
        String affiliateHref = product.hasValidatedAffiliateLink()
                ? product.affiliateLink().url()
                : null;
        return new CollectionPageResponse.CollectionProduct(
                product.title(),
                product.categories().get(0),
                product.description(),
                ageRange(product),
                href,
                affiliateHref,
                comparison ? "Ver comparativa completa" : "Ver análisis completo"
        );
    }

    private static String ageRange(Product product) {
        if (product.maxAge() == Integer.MAX_VALUE) {
            return "Desde " + product.minAge() + " años";
        }
        return product.minAge() + "-" + product.maxAge() + " años";
    }

    private static LinkItem hubLink() {
        return new LinkItem(
                "Juguetes y regalos para niños de 3 años",
                EditorialDefaults.HUB_3_HREF,
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
            List<LinkItem> related
    ) {
        String productHref(String productId) {
            return hrefOverrides.getOrDefault(productId, "/analisis/" + productId + "/");
        }
    }
}
