package com.bebesfelices.api.service;

import com.bebesfelices.api.catalog.Product;
import com.bebesfelices.api.catalog.ProductCatalog;
import com.bebesfelices.api.dto.PageStatus;
import com.bebesfelices.api.dto.ProductAnalysisResponse;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductAnalysisPageService {

    public static final List<String> PUBLISHED_PRODUCT_IDS = List.of(
            "juego-montessori-formas",
            "puzle-madera-animales",
            "patinete-3-ruedas",
            "torre-aprendizaje-madera",
            "set-vajilla-infantil",
            "kit-manualidades-natural"
    );

    private final Map<String, EditorialAnalysis> analyses = new LinkedHashMap<>();
    private final ProductCatalog productCatalog;

    public ProductAnalysisPageService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        registerAll();
    }

    public Optional<ProductAnalysisResponse> getByProductId(String productId) {
        EditorialAnalysis editorial = analyses.get(productId);
        if (editorial == null) {
            return Optional.empty();
        }
        return productCatalog.findById(productId)
                .map(product -> toResponse(product, editorial));
    }

    public List<String> publishedProductIds() {
        return PUBLISHED_PRODUCT_IDS;
    }

    private void registerAll() {
        register(new EditorialAnalysis(
                "juego-montessori-formas",
                "/juguetes-educativos/juegos-montessori/",
                "Juegos Montessori de formas y encajes",
                "Niños de 3 años que empiezan a clasificar formas y colores con piezas grandes",
                "Un juego de encaje de madera permite practicar clasificación sin instrucciones largas. Encaja en sesiones cortas, con supervisión cercana, y evita las piezas diminutas que a esta edad son un riesgo.",
                List.of(
                        "Piezas de madera para clasificar formas, colores y tamaños.",
                        "Actividad evidente: encajar, no un set con modos ocultos.",
                        "Apto para repetir el mismo gesto muchas veces."
                ),
                List.of(
                        "No sustituye el juego al aire libre ni el movimiento.",
                        "Si el set es enorme, puede abrumar en una sola sesión."
                ),
                List.of(
                        "Comprueba que las piezas no quepan enteras en la boca de un niño pequeño.",
                        "Usa el juego en el suelo o en una mesa estable, no cerca de escaleras.",
                        "Un adulto debe estar cerca aunque la actividad sea autónoma."
                ),
                List.of(
                        "Edad mínima del fabricante compatible con 3 años.",
                        "Piezas grandes y acabado sin astillas evidentes.",
                        "Una actividad clara, no diez juguetes en una caja."
                )
        ));
        register(new EditorialAnalysis(
                "puzle-madera-animales",
                "/juguetes-educativos/puzles/",
                "Puzles de piezas grandes",
                "Niños que practican motricidad fina con imágenes reconocibles y pocas piezas",
                "Un puzle de madera de animales concentra encaje, vocabulario y una tarea que se puede terminar. Las piezas grandes reducen el riesgo frente a puzzles de cartón fino y recuento alto.",
                List.of(
                        "Piezas grandes y resistentes para manos pequeñas.",
                        "Motivo de animales fácil de nombrar.",
                        "Se puede completar en una sesión corta."
                ),
                List.of(
                        "Se queda pequeño si el niño ya completa puzzles de muchas piezas con soltura.",
                        "Sin marco, las piezas se desplazan y frustran."
                ),
                List.of(
                        "Verifica que no haya piezas pequeñas sueltas en el set.",
                        "Revisa astillas o cantos vivos antes del primer uso.",
                        "Guarda las piezas juntas para no perder el sentido de «completar»."
                ),
                List.of(
                        "Número de piezas acorde a una sesión breve.",
                        "Madera o cartón grueso, no láminas que se doblan.",
                        "Imagen clara, sin recortes minúsculos."
                )
        ));
        register(new EditorialAnalysis(
                "patinete-3-ruedas",
                "/movimiento/patinetes/",
                "Patinetes de 3 ruedas",
                "Primer movimiento de pie, con una base estable y supervisión al aire libre",
                "El patinete de tres ruedas aporta estabilidad extra mientras se afianza el equilibrio. Es un complemento de la bicicleta sin pedales, no un recambio: aquí se practica estar de pie, dirigir y frenar.",
                List.of(
                        "Base de tres ruedas más estable que un patinete de dos ruedas.",
                        "Útil en paseos cortos sobre suelo regular.",
                        "Permite practicar dirección con menos caídas laterales al inicio."
                ),
                List.of(
                        "No enseña el mismo equilibrio que una bici sin pedales.",
                        "En suelos irregulares o pendientes, el riesgo sube aunque tenga tres ruedas."
                ),
                List.of(
                        "Casco homologado y calzado cerrado en cada uso.",
                        "Espacio sin tráfico, bordillos altos ni agua.",
                        "Un adulto mantiene supervisión activa; no es un juguete de portería."
                ),
                List.of(
                        "Altura de manillar que no fuerce los hombros.",
                        "Peso que se pueda recoger sin esfuerzo excesivo.",
                        "Freno o sistema de detención comprensible para el adulto que acompaña."
                )
        ));
        register(new EditorialAnalysis(
                "torre-aprendizaje-madera",
                "/autonomia/torres-de-aprendizaje/",
                "Torres de aprendizaje",
                "Familias que quieren que el niño participe en la cocina con una altura segura",
                "La torre de aprendizaje de madera es una plataforma con barandilla para alcanzar la encimera. A los 3 años tiene sentido si hay un adulto al lado y se usa como puesto de colaboración, no como juguete de trepa.",
                List.of(
                        "Altura regulable para acompañar el crecimiento.",
                        "Barandilla que delimita el puesto de pie.",
                        "Permite tareas reales: lavar, mezclar, observar."
                ),
                List.of(
                        "Ocupa espacio en cocina y no sirve si no hay supervisión.",
                        "Mal usada junto al fuego o a cuchillos es un riesgo grave."
                ),
                List.of(
                        "Colócala sobre suelo nivelado, lejos de fogones y de agua hirviendo.",
                        "No dejes al niño solo en la torre.",
                        "Revisa holguras, tornillos y que no haya huecos para la cabeza."
                ),
                List.of(
                        "Base estable que no se vuelque al inclinarse hacia la encimera.",
                        "Regulación de altura con cierre fiable.",
                        "Acabado de madera sin astillas en la zona de manos y pies."
                )
        ));
        register(new EditorialAnalysis(
                "set-vajilla-infantil",
                "/autonomia/vajilla-infantil/",
                "Vajilla infantil irrompible",
                "Practicar comer y beber en la mesa con piezas que sobreviven a las caídas",
                "Un set irrompible de tamaño adaptado permite repetir el gesto de comer sin que cada vuelco acabe en cristales. Es autonomía de rutina, no un juguete de sala.",
                List.of(
                        "Piezas de tamaño adaptado para manos pequeñas.",
                        "Material que no se hace añicos al caer.",
                        "Uso diario, fácil de integrar en la mesa familiar."
                ),
                List.of(
                        "No enseña por sí solo hábitos; hace falta constancia en las comidas.",
                        "Un diseño inestable o muy ligero se vuelca igual de a menudo."
                ),
                List.of(
                        "Elige materiales aptos para alimento y revisa el marcado del fabricante.",
                        "Supervisa el vaso abierto al principio.",
                        "No uses piezas con roturas o recubrimientos deteriorados."
                ),
                List.of(
                        "Base ancha en plato y vaso.",
                        "Peso que el niño pueda llevar a la mesa.",
                        "Limpieza sencilla, sin recovecos imposibles."
                )
        ));
        register(new EditorialAnalysis(
                "kit-manualidades-natural",
                "/sostenibles/",
                "Regalos más duraderos",
                "Crear en sesiones cortas con madera, fieltro o cartón, sin depender de una pantalla",
                "El kit de manualidades con materiales naturales propone actividades de dificultad progresiva. Encaja cuando hay un adulto dispuesto a sentarse un rato; no es un juguete de dejar solo sobre la mesa.",
                List.of(
                        "Materiales naturales para crear sin pantallas.",
                        "Actividades que se pueden alargar o acortar.",
                        "Enfoque más duradero que los sets de un solo uso."
                ),
                List.of(
                        "Requiere acompañamiento; no entretiene solo durante una tarde completa.",
                        "Si faltan piezas de recambio, algunas actividades se agotan."
                ),
                List.of(
                        "Revisa que no haya piezas pequeñas si hay hermanos menores.",
                        "Usa tijeras y pegamentos solo con un adulto.",
                        "Ventila si hay pinturas; sigue las indicaciones del fabricante."
                ),
                List.of(
                        "Piezas y materiales compatibles con 3 años.",
                        "Actividades que se pueden terminar en una sesión corta.",
                        "Caja o bolsa para no perder componentes."
                )
        ));
    }

    private void register(EditorialAnalysis analysis) {
        analyses.put(analysis.productId(), analysis);
    }

    private ProductAnalysisResponse toResponse(Product product, EditorialAnalysis editorial) {
        String path = "/analisis/" + product.id() + "/";
        String affiliateHref = product.hasValidatedAffiliateLink()
                ? product.affiliateLink().url()
                : null;
        String ageRange = product.maxAge() == Integer.MAX_VALUE
                ? "Desde " + product.minAge() + " años"
                : product.minAge() + "-" + product.maxAge() + " años";
        return new ProductAnalysisResponse(
                new Seo(
                        EditorialDefaults.canonical(path),
                        product.title() + " | Análisis | Bebes Felices",
                        editorial.editorialSummary()
                ),
                PageStatus.PUBLISHED,
                product.id(),
                path,
                List.of(
                        new ProductAnalysisResponse.Breadcrumb("Inicio", "/"),
                        new ProductAnalysisResponse.Breadcrumb(
                                EditorialDefaults.HUB_3_LABEL,
                                EditorialDefaults.HUB_3_HREF
                        ),
                        new ProductAnalysisResponse.Breadcrumb(product.title(), path)
                ),
                new ProductAnalysisResponse.Header(
                        "Análisis",
                        product.title(),
                        List.of(product.description(), editorial.editorialSummary())
                ),
                product.categories().get(0),
                ageRange,
                editorial.forWhom(),
                editorial.editorialSummary(),
                editorial.pros(),
                editorial.cons(),
                editorial.safetyNotes(),
                editorial.buyingChecks(),
                affiliateHref,
                List.of(
                        new LinkItem(
                                editorial.collectionTitle(),
                                editorial.collectionHref(),
                                "Volver a la categoría y a los criterios de compra."
                        ),
                        new LinkItem(
                                "Juguetes y regalos para niños de 3 años",
                                EditorialDefaults.HUB_3_HREF,
                                "Hub por edad con el resto de la selección."
                        ),
                        new LinkItem(
                                "Cómo analizamos en Bebes Felices",
                                "/como-analizamos/",
                                "Metodología y límites de estas fichas."
                        )
                ),
                EditorialDefaults.trustAuthority(),
                EditorialDefaults.affiliation(),
                EditorialDefaults.legalLinks(),
                new ProductAnalysisResponse.Author(
                        EditorialDefaults.AUTHOR_NAME,
                        EditorialDefaults.AUTHOR_ROLE
                ),
                EditorialDefaults.PUBLISHED_AT,
                EditorialDefaults.UPDATED_AT
        );
    }

    private record EditorialAnalysis(
            String productId,
            String collectionHref,
            String collectionTitle,
            String forWhom,
            String editorialSummary,
            List<String> pros,
            List<String> cons,
            List<String> safetyNotes,
            List<String> buyingChecks
    ) {
    }
}
