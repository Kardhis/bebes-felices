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
    private static final int TARGET_AGE = 3;
    private static final String PUBLISHED_AT = "2026-08-13";
    private static final String UPDATED_AT = "2026-08-13";
    private static final String CANONICAL_URL = "https://bebesfelices.es/comparativas/"
            + BALANCE_BIKES_SLUG + "/";

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

    private final ProductCatalog productCatalog;

    public ComparisonPageService(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Optional<ComparisonPageResponse> getBySlug(String slug) {
        if (!BALANCE_BIKES_SLUG.equals(slug)) {
            return Optional.empty();
        }
        return Optional.of(buildBalanceBikesPage());
    }

    private ComparisonPageResponse buildBalanceBikesPage() {
        Map<String, EditorialEntry> editorialById = new LinkedHashMap<>();
        BALANCE_BIKES.forEach(entry -> editorialById.put(entry.productId(), entry));

        AtomicInteger rank = new AtomicInteger(1);
        List<ComparisonPageResponse.Entry> entries = productCatalog
                .findByIds(BALANCE_BIKES.stream().map(EditorialEntry::productId).toList())
                .stream()
                .filter(product -> product.isAvailableForAge(TARGET_AGE))
                .map(product -> toResponseEntry(
                        rank.getAndIncrement(),
                        product,
                        editorialById.get(product.id())
                ))
                .toList();

        return new ComparisonPageResponse(
                new Seo(
                        CANONICAL_URL,
                        "Mejores bicicletas sin pedales para 3 años | Bebes Felices",
                        "Comparamos cinco bicicletas sin pedales aptas para 3 años por ajuste, peso, ruedas y facilidad de manejo, con metodología y afiliación transparentes."
                ),
                PageStatus.PUBLISHED,
                BALANCE_BIKES_SLUG,
                TARGET_AGE,
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
                PUBLISHED_AT,
                UPDATED_AT
        );
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
                        CANONICAL_URL
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
