package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.ArticlePageResponse;
import com.bebesfelices.api.dto.PageStatus;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlePageService {

    public static final String CHOOSE_BY_AGE_SLUG = "como-elegir-juguetes-por-edad";
    public static final String SKILLS_3_SLUG = "habilidades-3-anos";
    public static final String METHODOLOGY_SLUG = "como-analizamos";

    public Optional<ArticlePageResponse> getBySlug(String slug) {
        return switch (slug) {
            case CHOOSE_BY_AGE_SLUG -> Optional.of(chooseByAge());
            case SKILLS_3_SLUG -> Optional.of(skillsThreeYears());
            case METHODOLOGY_SLUG -> Optional.of(methodology());
            default -> Optional.empty();
        };
    }

    public List<String> publishedSlugs() {
        return List.of(CHOOSE_BY_AGE_SLUG, SKILLS_3_SLUG, METHODOLOGY_SLUG);
    }

    private ArticlePageResponse chooseByAge() {
        String path = "/guias/" + CHOOSE_BY_AGE_SLUG + "/";
        return article(
                CHOOSE_BY_AGE_SLUG,
                path,
                "Guía de compra",
                "Cómo elegir juguetes según la edad",
                "Criterios prácticos para 3, 4 y 5 años, sin depender solo del diseño o de la edad impresa en la caja.",
                List.of(
                        "Elegir un juguete útil empieza por observar al niño, no por el estante más llamativo. La edad del fabricante es una pista de seguridad, no una garantía de que el producto encaje con el desarrollo real.",
                        "Esta guía resume qué revisar antes de comprar y cómo usar las páginas por edad de Bebes Felices para decidir con criterio."
                ),
                hubBreadcrumbs("Cómo elegir juguetes según la edad", path),
                List.of(
                        section("edad-real", "Empieza por lo que ya hace, no solo por los años",
                                "Un niño de 3 años puede estar empezando a clasificar formas mientras otro ya imita tareas de casa. Mira si encaja, apila, corre con estabilidad o aguanta un juego con turnos cortos.",
                                "Si el producto exige instrucciones largas, piezas diminutas o equilibrio consolidado, probablemente llegue pronto, pero no ahora. Es mejor un objeto que permita éxito rápido y repetición que uno «para crecer» que frustre."),
                        section("seguridad", "Seguridad antes que el diseño",
                                "Comprueba la edad mínima del fabricante y evita piezas pequeñas a los 3 años. Prioriza materiales resistentes, bordes redondeados y un peso que el niño pueda manejar sin ayuda constante.",
                                "En movimiento, la supervisión no se sustituye con el producto: casco, calzado cerrado y un espacio sin tráfico siguen siendo la base, también en bicicletas sin pedales o patinetes."),
                        section("atencion", "Sesiones cortas y reglas simples",
                                "A los 3 años el juego autónomo suele ser breve. A los 4 aumenta la atención y aparecen reglas sencillas. A los 5 ya hay margen para partidas o construcciones más largas.",
                                "Elige según esa duración real: un puzle de pocas piezas grandes, un juego cooperativo de turnos cortos o un reto STEM con más piezas no sirven para la misma tarde."),
                        section("necesidad", "Elige por la necesidad que quieres cubrir",
                                "Aprender, moverse, ganar autonomía o acertar un regalo no se resuelven con el mismo tipo de producto. Las páginas por edad de Bebes Felices organizan las opciones así, no por precio.",
                                "Si buscas movimiento a los 3 años, empieza por la comparativa de bicicletas sin pedales. Si buscas clasificación o motricidad fina, ve a Montessori o puzles. Si el objetivo es la rutina diaria, mira torres de aprendizaje o vajilla adaptada."),
                        section("actualizar", "Revisa la fecha y no inventes datos",
                                "Los catálogos cambian. Mira la fecha de actualización, la metodología y si el enlace de Amazon está validado. Si no hay precio ni valoración en la página, es deliberado: no los mantenemos porque varían y no forman parte del criterio editorial.")
                ),
                List.of(
                        faq(
                                "¿La edad de la caja es suficiente para decidir?",
                                "Es imprescindible para seguridad, pero no basta. Contrástala con lo que el niño ya hace: encajar, equilibrarse, seguir turnos o participar en la cocina."
                        ),
                        faq(
                                "¿Qué debo evitar a los 3 años?",
                                "Piezas pequeñas, materiales frágiles, instrucciones complejas y juguetes de movimiento sin supervisión. También los productos sin rango de edad claro."
                        ),
                        faq(
                                "¿Cómo uso las páginas de Bebes Felices?",
                                "Entra por la edad, elige la necesidad (aprender, moverse, autonomía o regalo) y sigue la comparativa, la categoría o el análisis. Cada página debe devolverte al hub con el contexto de esa edad."
                        ),
                        faq(
                                "¿Por qué no hay precios ni estrellas?",
                                "Porque cambian con frecuencia y no los verificamos como dato estable. Evaluamos encaje, seguridad, utilidad y durabilidad con información que podemos sostener."
                        )
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Qué habilidades desarrolla un niño de 3 años",
                                "/guias/habilidades-3-anos/",
                                "Desarrollo esperable a esta edad y cómo encaja con el juego."
                        ),
                        new LinkItem(
                                "Cómo analizamos en Bebes Felices",
                                "/como-analizamos/",
                                "Metodología, límites y transparencia de afiliación."
                        )
                )
        );
    }

    private ArticlePageResponse skillsThreeYears() {
        String path = "/guias/" + SKILLS_3_SLUG + "/";
        return article(
                SKILLS_3_SLUG,
                path,
                "Desarrollo",
                "Qué habilidades desarrolla un niño de 3 años",
                "Explicación práctica del desarrollo esperable a esta edad, con ejemplos de juego y sin convertir cada hito en una norma rígida.",
                List.of(
                        "A los 3 años el lenguaje avanza muy rápido, aparece el juego simbólico y crece el interés por imitar a los adultos. La motricidad mejora, aunque el equilibrio todavía se está asentando.",
                        "Este artículo amplía el bloque de habilidades del hub de 3 años: qué suele estar aprendiendo un niño y qué tipo de objetos le ayudan sin exigir de más."
                ),
                hubBreadcrumbs("Qué habilidades desarrolla un niño de 3 años", path),
                List.of(
                        section("lenguaje", "Lenguaje, vocabulario y juego simbólico",
                                "El juego con adultos y con otros niños amplía el vocabulario y las frases. Representar situaciones cotidianas —cocinar, cuidar, conducir— es la base del juego simbólico y de la creatividad posterior.",
                                "Encajan objetos de clasificación, figuras o rincones de imitación con piezas grandes. No hace falta un set complejo: basta con que el niño pueda nombrar, pedir y repetir sin frustrarse."),
                        section("motricidad-gruesa", "Motricidad gruesa y primer equilibrio",
                                "Camina, corre y empieza a mantenerse en superficies menos estables, todavía con apoyo. Las bicicletas sin pedales ligeras y los patinetes de tres ruedas sirven para practicar ese equilibrio con supervisión.",
                                "El éxito rápido importa: si el producto es pesado, alto o inestable, el niño deja de usarlo. La comparativa de bicicletas sin pedales para 3 años cubre talla, peso y facilidad de uso."),
                        section("motricidad-fina", "Motricidad fina con piezas grandes",
                                "Encaja, apila y manipula con más precisión que el año anterior, pero las piezas pequeñas siguen siendo un riesgo. Los puzles de madera de pocas piezas y los juegos Montessori de formas practican esa precisión sin agujas ni cuentas diminutas."),
                        section("autonomia", "Autonomía inicial en la rutina",
                                "Empieza a participar en tareas sencillas con supervisión cercana: subir a una torre de aprendizaje para ver la encimera, usar vajilla irrompible, recoger. El objetivo no es independencia total, sino éxito repetible en casa.",
                                "Elige altura regulable, barandilla y materiales que aguanten un uso intenso. A esta edad el desgaste es alto y las sesiones autónomas son cortas."),
                        section("limites", "Qué no esperar todavía",
                                "No es razonable exigir partidas largas, reglas competitivas o construcción de muchas piezas pequeñas. Tampoco un equilibrio de bici con pedales «porque ya tiene 3 años».",
                                "Si un juguete solo funciona con instrucciones adultas constantes, probablemente llegue mejor más adelante. Prioriza repetición, robustez y una necesidad concreta.")
                ),
                List.of(
                        faq(
                                "¿Todos los niños de 3 años desarrollan lo mismo al mismo tiempo?",
                                "No. Estas descripciones son orientativas. El ritmo varía; lo útil es observar qué ya hace el niño y elegir objetos que lo acompañen, no que lo adelanten a la fuerza."
                        ),
                        faq(
                                "¿Qué juguetes encajan mejor con este momento?",
                                "Piezas grandes para clasificar y encajar, primer movimiento estable y productos de autonomía de la rutina. Evita piezas pequeñas y reglas largas."
                        ),
                        faq(
                                "¿La bicicleta sin pedales es adecuada a los 3 años?",
                                "Es una edad habitual para iniciarse, con un modelo ligero, sillín a la altura correcta y supervisión. No sustituye casco ni un espacio seguro."
                        ),
                        faq(
                                "¿Cómo se relaciona esto con un regalo?",
                                "Un buen regalo a esta edad resuelve una necesidad (aprender, moverse, autonomía) y se puede usar en sesiones cortas. Las ideas de regalo para 3 años siguen ese criterio."
                        )
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Checklist práctico antes de comprar."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 3 años",
                                "/regalos/ideas-regalo-3-anos/",
                                "Selección por ocasión y presupuesto, sin perder utilidad."
                        )
                )
        );
    }

    private ArticlePageResponse methodology() {
        String path = "/como-analizamos/";
        return article(
                METHODOLOGY_SLUG,
                path,
                "Metodología",
                "Cómo analizamos en Bebes Felices",
                "Criterios, fuentes, límites y transparencia de afiliación de nuestras recomendaciones para niños de 3 a 5 años.",
                List.of(
                        "Bebes Felices publica guías, comparativas y análisis para ayudar a elegir con criterio. Esta página explica qué hacemos, qué no hacemos y cómo se señala la afiliación.",
                        "No inventamos puntuaciones, precios ni valoraciones. Si un dato no se puede verificar de forma estable, no lo convertimos en cifra de marketing."
                ),
                List.of(
                        new ArticlePageResponse.Breadcrumb("Inicio", "/"),
                        new ArticlePageResponse.Breadcrumb("Cómo analizamos", path)
                ),
                List.of(
                        section("criterios", "Qué evaluamos",
                                "Edad recomendada y encaje con el desarrollo y la seguridad. Utilidad educativa y facilidad de uso en casa. Durabilidad razonable frente al desgaste típico de 3 a 5 años. Relación calidad-precio entendida como utilidad, no como el importe más bajo del momento.",
                                "En comparativas, además, contrastamos talla, peso, regulación y tipo de uso cuando el fabricante lo declara. Conservamos títulos editoriales y separamos el análisis de cualquier enlace comercial."),
                        section("limites", "Qué no aparece en nuestras páginas",
                                "No publicamos estrellas agregadas, rankings numéricos inventados ni precios. Esos datos cambian y, en el caso de Amazon, no los mantenemos como hecho editorial.",
                                "Tampoco afirmamos experiencia de uso que no tengamos. Distinguimos investigación de ficha, opiniones de compradores cuando las citamos como tales, y criterio propio."),
                        section("fuentes", "De dónde sale la información",
                                "El catálogo interno identifica cada producto con un id estable. Cuando hay ASIN y Partner Tag, el backend construye un enlace de Amazon España validado. Si falta cualquiera de los dos, no mostramos un botón de compra.",
                                "Las comparativas de productos reales documentan la fecha de revisión. El resto del contenido del hub de 3 años usa descripciones editoriales del catálogo; no atribuimos marcas ni especificaciones que no estén en esa ficha."),
                        section("afiliacion", "Afiliación de Amazon",
                                "Participamos en el Programa de Afiliados de Amazon. Si compras a través de un enlace de afiliado, podemos recibir una comisión sin coste extra para ti.",
                                "Esa comisión no cambia el orden editorial ni convierte un producto genérico en una ficha de Amazon. El aviso aparece en las páginas con recomendaciones y el pie incluye información legal sobre afiliación."),
                        section("actualizacion", "Fechas y responsabilidad",
                                "Cada página muestra fecha de actualización. Revisa esa fecha antes de decidir: los catálogos y las fichas cambian.",
                                "Esta metodología es la versión larga del bloque «Cómo seleccionamos los productos» que aparece en hubs, comparativas y análisis.")
                ),
                List.of(
                        faq(
                                "¿Por qué no hay precios?",
                                "Porque no los podemos mantener fiables. Preferimos criterios de uso, seguridad y encaje por edad, que sí podemos revisar."
                        ),
                        faq(
                                "¿Un enlace de afiliado cambia la recomendación?",
                                "No. El contenido editorial se escribe primero. El enlace, si existe, se añade cuando el catálogo valida la URL de Amazon España."
                        ),
                        faq(
                                "¿Qué pasa si un producto no tiene botón de Amazon?",
                                "Se muestra el análisis o la ficha editorial y un aviso de que el enlace estará disponible cuando haya ASIN y Partner Tag validados."
                        ),
                        faq(
                                "¿Esta metodología vale para 4 y 5 años?",
                                "Sí. El proceso es el mismo. En esta fase el circuito de páginas interiores está cerrado sobre todo para 3 años; los hubs de 4 y 5 ya existen con su propio contenido."
                        )
                ),
                List.of(
                        hubLink(),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Aplicación práctica de estos criterios antes de comprar."
                        ),
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "Ejemplo de comparativa con productos reales y metodología visible."
                        )
                )
        );
    }

    private ArticlePageResponse article(
            String slug,
            String path,
            String kicker,
            String h1,
            String metaDescription,
            List<String> introduction,
            List<ArticlePageResponse.Breadcrumb> breadcrumbs,
            List<ArticlePageResponse.Section> sections,
            List<ArticlePageResponse.Faq> faq,
            List<LinkItem> related
    ) {
        return new ArticlePageResponse(
                new Seo(EditorialDefaults.canonical(path), h1 + " | Bebes Felices", metaDescription),
                PageStatus.PUBLISHED,
                slug,
                path,
                breadcrumbs,
                new ArticlePageResponse.Header(kicker, h1, introduction),
                sections,
                faq,
                related,
                EditorialDefaults.trustAuthority(),
                EditorialDefaults.affiliation(),
                EditorialDefaults.legalLinks(),
                new ArticlePageResponse.Author(
                        EditorialDefaults.AUTHOR_NAME,
                        EditorialDefaults.AUTHOR_ROLE
                ),
                EditorialDefaults.PUBLISHED_AT,
                EditorialDefaults.UPDATED_AT
        );
    }

    private List<ArticlePageResponse.Breadcrumb> hubBreadcrumbs(String current, String path) {
        return List.of(
                new ArticlePageResponse.Breadcrumb("Inicio", "/"),
                new ArticlePageResponse.Breadcrumb(
                        EditorialDefaults.HUB_3_LABEL,
                        EditorialDefaults.HUB_3_HREF
                ),
                new ArticlePageResponse.Breadcrumb(current, path)
        );
    }

    private LinkItem hubLink() {
        return new LinkItem(
                "Juguetes y regalos para niños de 3 años",
                EditorialDefaults.HUB_3_HREF,
                "Hub por edad con necesidades, selección y comparativa de bicicletas."
        );
    }

    private static ArticlePageResponse.Section section(String id, String title, String... paragraphs) {
        return new ArticlePageResponse.Section(id, title, List.of(paragraphs));
    }

    private static ArticlePageResponse.Faq faq(String question, String answer) {
        return new ArticlePageResponse.Faq(question, answer);
    }
}
