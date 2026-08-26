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
    public static final String SKILLS_4_SLUG = "habilidades-4-anos";
    public static final String SKILLS_5_SLUG = "habilidades-5-anos";
    public static final String METHODOLOGY_SLUG = "como-analizamos";

    public Optional<ArticlePageResponse> getBySlug(String slug) {
        return switch (slug) {
            case CHOOSE_BY_AGE_SLUG -> Optional.of(chooseByAge());
            case SKILLS_3_SLUG -> Optional.of(skillsThreeYears());
            case SKILLS_4_SLUG -> Optional.of(skillsFourYears());
            case SKILLS_5_SLUG -> Optional.of(skillsFiveYears());
            case METHODOLOGY_SLUG -> Optional.of(methodology());
            default -> Optional.empty();
        };
    }

    public List<String> publishedSlugs() {
        return List.of(CHOOSE_BY_AGE_SLUG, SKILLS_3_SLUG, SKILLS_4_SLUG, SKILLS_5_SLUG, METHODOLOGY_SLUG);
    }

    private ArticlePageResponse chooseByAge() {
        String path = "/guias/" + CHOOSE_BY_AGE_SLUG + "/";
        ArticlePageResponse.AgeVariant age3 = chooseByAgeVariant(3);
        ArticlePageResponse.AgeVariant age4 = chooseByAgeVariant(4);
        ArticlePageResponse.AgeVariant age5 = chooseByAgeVariant(5);
        return article(
                CHOOSE_BY_AGE_SLUG,
                path,
                "Guía de compra",
                "Cómo elegir juguetes según la edad",
                "Criterios prácticos para 3, 4 y 5 años, sin depender solo del diseño o de la edad impresa en la caja.",
                age3.introductionParagraphs(),
                guidesBreadcrumbs("Cómo elegir juguetes según la edad", path),
                age3.sections(),
                age3.faq(),
                age3.relatedLinks(),
                List.of(age3, age4, age5)
        );
    }

    private ArticlePageResponse.AgeVariant chooseByAgeVariant(int age) {
        return switch (age) {
            case 4 -> new ArticlePageResponse.AgeVariant(
                    4,
                    List.of(
                            "A los 4 años elige por lo que ya sostiene: turnos cortos, un poco más de pulso y un equilibrio más estable. La edad de la caja sigue siendo una pista de seguridad, no una garantía de encaje.",
                            "Esta guía concreta qué revisar a esta edad y cómo usar las páginas de 4 años de Bebes Felices para decidir con criterio."
                    ),
                    List.of(
                            section("edad-real", "Empieza por lo que ya hace, no solo por los años",
                                    "Un niño de 4 años puede estar empezando a respetar turnos mientras otro ya construye con un objetivo claro. Mira si aguanta una partida corta, encaja más piezas o se mueve con más seguridad.",
                                    "Si el producto exige un reglamento largo, estrategia de adulto o equilibrio de bici con pedales, probablemente llegue pronto, pero no ahora. Es mejor un objeto que permita éxito repetible que uno «para mayores» que frustre."),
                            section("seguridad", "Seguridad antes que el diseño",
                                    "Sigue comprobando la edad mínima del fabricante. A los 4 años el pulso mejora, pero las piezas pequeñas siguen siendo un riesgo si hay hermanos menores. Revisa imanes encapsulados y un peso que el niño pueda manejar.",
                                    "En movimiento, la supervisión no se sustituye con el producto: casco, calzado cerrado y un espacio sin tráfico. El paso a dos ruedas o a un patinete más ágil depende del equilibrio real, no del cumpleaños."),
                            section("atencion", "Sesiones cortas y reglas simples",
                                    "A los 4 años aumenta la atención y aparecen reglas sencillas que se pueden explicar en pocos minutos. Todavía no es una tarde de partida competitiva larga.",
                                    "Elige según esa duración real: un cooperativo de turnos cortos, un set de construcción con un objetivo visible o un puzle con más piezas que a los 3 años, no un reto que no se pueda terminar."),
                            section("necesidad", "Elige por la necesidad que quieres cubrir",
                                    "Aprender, moverse, ganar autonomía o acertar un regalo no se resuelven con el mismo tipo de producto. Las páginas de 4 años de Bebes Felices organizan las opciones así, no por precio.",
                                    "Si buscas una partida en familia, empieza por la comparativa de juegos de mesa. Si buscas movimiento, mira patinetes o bicicletas sin pedales. Si el objetivo es la rutina, revisa torres y vajilla. Para materiales más duraderos, ve a la selección sostenible."),
                            section("actualizar", "Revisa la fecha y no inventes datos",
                                    "Los catálogos cambian. Mira la fecha de actualización de la comparativa o del análisis de 4 años, la metodología y si el enlace de Amazon está validado.",
                                    "Si no hay precio ni valoración en la página, es deliberado: no los mantenemos porque varían y no forman parte del criterio editorial.")
                    ),
                    List.of(
                            faq(
                                    "¿La edad de la caja es suficiente para decidir?",
                                    "Es imprescindible para seguridad, pero no basta. Contrástala con lo que el niño ya hace: turnos cortos, construir con un objetivo o moverse con más estabilidad."
                            ),
                            faq(
                                    "¿Qué debo evitar a los 4 años?",
                                    "Reglamentos largos, piezas diminutas si hay hermanos pequeños, imanes que se puedan desprender y movimiento sin supervisión. También los productos sin rango de edad claro."
                            ),
                            faq(
                                    "¿Cómo uso las páginas de Bebes Felices?",
                                    "Entra por 4 años, elige la necesidad (aprender, moverse, autonomía o regalo) y sigue la comparativa, la categoría o el análisis. Cada página debe devolverte al hub con el contexto de esa edad."
                            ),
                            faq(
                                    "¿Por qué no hay precios ni estrellas?",
                                    "Porque cambian con frecuencia y no los verificamos como dato estable. Evaluamos encaje, seguridad, utilidad y durabilidad con información que podemos sostener."
                            )
                    ),
                    List.of(
                            hubLink(4),
                            new LinkItem(
                                    "Qué habilidades desarrolla un niño de 4 años",
                                    "/guias/habilidades-4-anos/",
                                    "Desarrollo esperable a esta edad y cómo encaja con el juego."
                            ),
                            new LinkItem(
                                    "Mejores juegos de mesa para 4 años",
                                    "/comparativas/mejores-juegos-de-mesa-4-anos/",
                                    "Cooperativos y partidas cortas con productos reales."
                            )
                    )
            );
            case 5 -> new ArticlePageResponse.AgeVariant(
                    5,
                    List.of(
                            "A los 5 años elige un reto que se pueda terminar y volver a complicar: construir, cooperar o resolver. La edad de la caja sigue siendo una pista de seguridad, no una garantía de encaje.",
                            "Esta guía concreta qué revisar a esta edad y cómo usar las páginas de 5 años de Bebes Felices para decidir con criterio."
                    ),
                    List.of(
                            section("edad-real", "Empieza por lo que ya hace, no solo por los años",
                                    "Un niño de 5 años puede planificar unos pasos, seguir varias reglas sencillas o colaborar hacia un objetivo común. Mira si termina una construcción, aguanta una partida corta o explica qué quiere probar.",
                                    "Si el producto exige lectura compleja, estrategia de adulto o un proyecto que no se puede cerrar, probablemente llegue mejor más adelante. Es mejor un reto asumible que se pueda repetir que uno «de cole» que frustre."),
                            section("seguridad", "Seguridad antes que el diseño",
                                    "Sigue comprobando la edad mínima del fabricante. A los 5 años la precisión mejora, pero imanes, piezas sueltas y movimiento siguen pidiendo revisión. El peso y el tamaño tienen que poder manejarse sin ayuda constante.",
                                    "En movimiento, casco, calzado cerrado y un espacio sin tráfico no se negocian. Autonomía no significa dejar solo un set con imanes o un patinete junto a un desnivel."),
                            section("atencion", "Sesiones cortas y reglas simples",
                                    "A los 5 años hay más margen para partidas o construcciones más largas, siempre con una meta que se pueda alcanzar. Un reto enorme abierto acaba abandonado.",
                                    "Elige según esa duración real: un cooperativo con varias reglas breves, un set STEM al que se puedan añadir piezas o un puzle que se complete en una sesión, no un modo experto que solo entiende el adulto."),
                            section("necesidad", "Elige por la necesidad que quieres cubrir",
                                    "Pensamiento lógico, cooperación, movimiento o un regalo con uso evolutivo no se resuelven con el mismo producto. Las páginas de 5 años de Bebes Felices organizan las opciones así, no por precio.",
                                    "Si buscas construir y resolver, empieza por la comparativa STEM. Si buscas turnos y acuerdos, ve a juegos de mesa. Si el objetivo es un regalo que se pueda complicar, mira las ideas para 5 años."),
                            section("actualizar", "Revisa la fecha y no inventes datos",
                                    "Los catálogos cambian. Mira la fecha de actualización de la comparativa o del análisis de 5 años, la metodología y si el enlace de Amazon está validado.",
                                    "Si no hay precio ni valoración en la página, es deliberado: no los mantenemos porque varían y no forman parte del criterio editorial.")
                    ),
                    List.of(
                            faq(
                                    "¿La edad de la caja es suficiente para decidir?",
                                    "Es imprescindible para seguridad, pero no basta. Contrástala con lo que el niño ya hace: planificar un par de pasos, cooperar o terminar un reto visible."
                            ),
                            faq(
                                    "¿Qué debo evitar a los 5 años?",
                                    "Proyectos que no se pueden terminar, reglas que solo un adulto puede leer en cada turno, imanes sueltos y movimiento sin supervisión. También los productos sin rango de edad claro."
                            ),
                            faq(
                                    "¿Cómo uso las páginas de Bebes Felices?",
                                    "Entra por 5 años, elige la necesidad (aprender, moverse, autonomía o regalo) y sigue la comparativa, la categoría o el análisis. Cada página debe devolverte al hub con el contexto de esa edad."
                            ),
                            faq(
                                    "¿Por qué no hay precios ni estrellas?",
                                    "Porque cambian con frecuencia y no los verificamos como dato estable. Evaluamos encaje, seguridad, utilidad y durabilidad con información que podemos sostener."
                            )
                    ),
                    List.of(
                            hubLink(5),
                            new LinkItem(
                                    "Qué habilidades desarrolla un niño de 5 años",
                                    "/guias/habilidades-5-anos/",
                                    "Pensamiento lógico, cooperación y autonomía a esta edad."
                            ),
                            new LinkItem(
                                    "Mejores juguetes STEM para 5 años",
                                    "/comparativas/mejores-juguetes-stem-5-anos/",
                                    "Construcción, mecanismos y patrones con productos reales."
                            )
                    )
            );
            default -> new ArticlePageResponse.AgeVariant(
                    3,
                    List.of(
                            "Elegir un juguete útil a los 3 años empieza por observar al niño, no por el estante más llamativo. La edad del fabricante es una pista de seguridad, no una garantía de que el producto encaje con el desarrollo real.",
                            "Esta guía concreta qué revisar a esta edad y cómo usar las páginas de 3 años de Bebes Felices para decidir con criterio."
                    ),
                    List.of(
                            section("edad-real", "Empieza por lo que ya hace, no solo por los años",
                                    "Un niño de 3 años puede estar empezando a clasificar formas mientras otro ya imita tareas de casa. Mira si encaja, apila, corre con estabilidad o aguanta un juego con turnos muy cortos.",
                                    "Si el producto exige instrucciones largas, piezas diminutas o equilibrio consolidado, probablemente llegue pronto, pero no ahora. Es mejor un objeto que permita éxito rápido y repetición que uno «para crecer» que frustre."),
                            section("seguridad", "Seguridad antes que el diseño",
                                    "Comprueba la edad mínima del fabricante y evita piezas pequeñas a los 3 años. Prioriza materiales resistentes, bordes redondeados y un peso que el niño pueda manejar sin ayuda constante.",
                                    "En movimiento, la supervisión no se sustituye con el producto: casco, calzado cerrado y un espacio sin tráfico siguen siendo la base, también en bicicletas sin pedales o patinetes."),
                            section("atencion", "Sesiones cortas y reglas simples",
                                    "A los 3 años el juego autónomo suele ser breve. Una sola actividad clara —encajar, apilar o completar un puzle de pocas piezas— encaja mejor que un set con diez modos.",
                                    "Elige según esa duración real: piezas grandes, un objetivo visible y la posibilidad de terminar en una sesión corta. Las reglas competitivas y las partidas largas llegan más adelante."),
                            section("necesidad", "Elige por la necesidad que quieres cubrir",
                                    "Aprender, moverse, ganar autonomía o acertar un regalo no se resuelven con el mismo tipo de producto. Las páginas de 3 años de Bebes Felices organizan las opciones así, no por precio.",
                                    "Si buscas movimiento, empieza por la comparativa de bicicletas sin pedales. Si buscas clasificación o motricidad fina, ve a Montessori o puzles. Si el objetivo es la rutina diaria, mira torres de aprendizaje o vajilla adaptada."),
                            section("actualizar", "Revisa la fecha y no inventes datos",
                                    "Los catálogos cambian. Mira la fecha de actualización, la metodología y si el enlace de Amazon está validado.",
                                    "Si no hay precio ni valoración en la página, es deliberado: no los mantenemos porque varían y no forman parte del criterio editorial.")
                    ),
                    List.of(
                            faq(
                                    "¿La edad de la caja es suficiente para decidir?",
                                    "Es imprescindible para seguridad, pero no basta. Contrástala con lo que el niño ya hace: encajar, equilibrarse, seguir un turno muy corto o participar en la cocina."
                            ),
                            faq(
                                    "¿Qué debo evitar a los 3 años?",
                                    "Piezas pequeñas, materiales frágiles, instrucciones complejas y juguetes de movimiento sin supervisión. También los productos sin rango de edad claro."
                            ),
                            faq(
                                    "¿Cómo uso las páginas de Bebes Felices?",
                                    "Entra por 3 años, elige la necesidad (aprender, moverse, autonomía o regalo) y sigue la comparativa, la categoría o el análisis. Cada página debe devolverte al hub con el contexto de esa edad."
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
        };
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

    private ArticlePageResponse skillsFourYears() {
        String path = "/guias/" + SKILLS_4_SLUG + "/";
        return article(
                SKILLS_4_SLUG,
                path,
                "Desarrollo",
                "Qué habilidades desarrolla un niño de 4 años",
                "Explicación práctica del desarrollo esperable a esta edad, con ejemplos de juego y sin convertir cada hito en una norma rígida.",
                List.of(
                        "A los 4 años suele mejorar la atención, el control de la mano y el equilibrio. Aparecen las reglas sencillas y la curiosidad por letras, números y retos de construcción.",
                        "Este artículo amplía el bloque de habilidades del hub de 4 años: qué suele estar aprendiendo un niño y qué tipo de objetos le ayudan sin exigir de más."
                ),
                hubBreadcrumbs(4, "Qué habilidades desarrolla un niño de 4 años", path),
                List.of(
                        section("atencion", "Atención sostenida y reglas sencillas",
                                "Puede mantenerse en una actividad algo más tiempo que el año anterior y empezar a respetar turnos. Un juego cooperativo de partida corta o un set de construcción con un objetivo claro encajan mejor que un reglamento largo.",
                                "Si se rinde a los dos minutos, reduce piezas a la vista y termina juntos. El éxito repetible importa más que completar el modo «experto» de la caja."),
                        section("motricidad-fina", "Motricidad fina más precisa",
                                "Recortar, enhebrar o dibujar formas reconocibles pide un control que a los 3 años aún no estaba asentado. Los puzles de más piezas, la construcción magnética y los juegos de apilar practican esa precisión.",
                                "Siguen existiendo riesgos de piezas pequeñas si hay hermanos menores. Revisa la edad mínima del fabricante aunque el niño de 4 años ya manipule con soltura."),
                        section("equilibrio", "Equilibrio y movimiento más exigente",
                                "Gana estabilidad para la bicicleta sin pedales y, en algunos casos, para un patinete con menos apoyo. No hay una edad fija para pasar de tres ruedas a dos o de bici sin pedales a bici con pedales.",
                                "Elige talla, peso y un espacio sin tráfico. La comparativa de bicicletas sin pedales para 3 años sigue siendo útil si el modelo cubre también los 4."),
                        section("letras", "Interés por letras, números y patrones",
                                "La curiosidad por contar, reconocer símbolos o repetir patrones no obliga a un juguete «de aprender a leer». Un dado de colores, un cooperativo de recoger fruta o un Dobble de animales ya entrenan mirar, nombrar y esperar turno.",
                                "Evita convertir cada tarde en una ficha escolar. El juego sirve cuando el niño quiere repetir, no cuando el adulto quiere adelantar curso."),
                        section("limites", "Qué no esperar todavía",
                                "No es razonable exigir partidas competitivas largas, estrategia de adulto o construcción de muchas piezas diminutas sin frustración.",
                                "Si un juego solo funciona con un adulto leyendo el reglamento en cada turno, probablemente llegue mejor más adelante. Prioriza reglas visibles y una necesidad concreta.")
                ),
                List.of(
                        faq(
                                "¿Todos los niños de 4 años desarrollan lo mismo al mismo tiempo?",
                                "No. Estas descripciones son orientativas. Observa qué ya hace el niño —turnos, pulso, equilibrio— y elige objetos que lo acompañen, no que lo adelanten."
                        ),
                        faq(
                                "¿Qué juguetes encajan mejor con este momento?",
                                "Construcción con piezas manejables, juegos de mesa de reglas cortas, movimiento para consolidar equilibrio y autonomía de la rutina. Evita instrucciones largas y piezas diminutas si hay hermanos pequeños."
                        ),
                        faq(
                                "¿Es buena edad para los juegos de mesa cooperativos?",
                                "Sí, cuando las reglas se explican en pocos minutos y se gana o se pierde en equipo. La comparativa de juegos de mesa para 4 años cubre cooperativos y también opciones de observación o pulso."
                        ),
                        faq(
                                "¿Cómo se relaciona esto con un regalo?",
                                "Un buen regalo a esta edad se usa durante semanas: construir, jugar una partida corta o moverse. Las ideas de regalo para 4 años siguen ese criterio."
                        )
                ),
                List.of(
                        hubLink(4),
                        new LinkItem(
                                "Mejores juegos de mesa para 4 años",
                                "/comparativas/mejores-juegos-de-mesa-4-anos/",
                                "Comparativa con productos reales, sin precios ni estrellas."
                        ),
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Checklist práctico antes de comprar."
                        )
                )
        );
    }

    private ArticlePageResponse skillsFiveYears() {
        String path = "/guias/" + SKILLS_5_SLUG + "/";
        return article(
                SKILLS_5_SLUG,
                path,
                "Desarrollo",
                "Qué habilidades desarrolla un niño de 5 años",
                "Pensamiento lógico, cooperación, coordinación y autonomía a los 5 años, con ejemplos de juego y ritmos orientativos.",
                List.of(
                        "A los 5 años suele aumentar el tiempo de concentración y la capacidad de planificar una construcción, seguir varias reglas sencillas y colaborar hacia un objetivo común.",
                        "Estas referencias son orientativas: sirven para elegir un reto que acompañe lo que el niño ya hace, no para convertir cada habilidad en una prueba."
                ),
                hubBreadcrumbs(5, "Qué habilidades desarrolla un niño de 5 años", path),
                List.of(
                        section("pensamiento-logico", "Pensamiento lógico y resolución de problemas",
                                "Puede anticipar algunos pasos, clasificar por más de un criterio y corregir una construcción después de observar por qué falla.",
                                "Los sets magnéticos, puzles y mecanismos sencillos funcionan mejor cuando el reto es visible y se puede ajustar con menos o más piezas."),
                        section("atencion", "Atención para proyectos más largos",
                                "La actividad puede durar más que a los 4 años, aunque sigue necesitando una meta alcanzable. Terminar una estructura o una partida corta aporta más que dejar abierto un reto enorme.",
                                "Divide los proyectos largos en pasos y deja que explique qué quiere probar antes de intervenir."),
                        section("cooperacion", "Cooperación, turnos y reglas",
                                "Ya puede coordinarse con otros hacia un objetivo común y recordar varias reglas sencillas. Un juego cooperativo permite practicar acuerdos sin centrar toda la partida en ganar a otra persona.",
                                "Un adulto sigue ayudando a resolver conflictos y a adaptar la duración cuando baja la atención."),
                        section("motricidad", "Coordinación y motricidad fina",
                                "Mejoran el equilibrio, el pulso y la orientación espacial. Apilar, encajar, dibujar y construir pueden exigir más precisión, siempre dentro del rango de seguridad del fabricante.",
                                "La dificultad debe subir por el reto, no por introducir piezas peligrosamente pequeñas."),
                        section("autonomia", "Autonomía con responsabilidad gradual",
                                "Puede completar tareas sencillas de principio a fin, recoger componentes y participar en decisiones sobre el juego.",
                                "Autonomía no significa ausencia de supervisión: imanes, accesorios pequeños y actividades de movimiento conservan sus precauciones.")
                ),
                List.of(
                        faq("¿Todos los niños de 5 años dominan estas habilidades?", "No. Son referencias orientativas y el desarrollo varía. Observa lo que ya hace y ofrece el siguiente reto asumible."),
                        faq("¿Qué juguetes apoyan el pensamiento lógico?", "Construcciones, puzles, mecanismos sencillos y juegos de patrones con dificultad ajustable."),
                        faq("¿Ya puede jugar partidas largas?", "Puede mantener más atención, pero sigue siendo preferible una partida que se pueda terminar y adaptar."),
                        faq("¿Cómo se relacionan estas habilidades con un regalo?", "Un buen regalo admite repetición, dificultad creciente y responde a un interés real del niño.")
                ),
                List.of(
                        hubLink(5),
                        new LinkItem(
                                "Mejores juguetes STEM para 5 años",
                                "/comparativas/mejores-juguetes-stem-5-anos/",
                                "Construcción, mecanismos, equilibrio y patrones."
                        ),
                        new LinkItem(
                                "Ideas de regalo para 5 años",
                                "/regalos/ideas-regalo-5-anos/",
                                "Selección por necesidad y uso evolutivo."
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
        return article(
                slug,
                path,
                kicker,
                h1,
                metaDescription,
                introduction,
                breadcrumbs,
                sections,
                faq,
                related,
                List.of()
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
            List<LinkItem> related,
            List<ArticlePageResponse.AgeVariant> ageVariants
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
                ageVariants,
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

    private List<ArticlePageResponse.Breadcrumb> guidesBreadcrumbs(String current, String path) {
        return List.of(
                new ArticlePageResponse.Breadcrumb("Inicio", "/"),
                new ArticlePageResponse.Breadcrumb("Guías", "/guias/"),
                new ArticlePageResponse.Breadcrumb(current, path)
        );
    }

    private List<ArticlePageResponse.Breadcrumb> hubBreadcrumbs(String current, String path) {
        return hubBreadcrumbs(3, current, path);
    }

    private List<ArticlePageResponse.Breadcrumb> hubBreadcrumbs(int age, String current, String path) {
        return List.of(
                new ArticlePageResponse.Breadcrumb("Inicio", "/"),
                new ArticlePageResponse.Breadcrumb(
                        EditorialDefaults.hubLabel(age),
                        EditorialDefaults.hubHref(age)
                ),
                new ArticlePageResponse.Breadcrumb(current, path)
        );
    }

    private LinkItem hubLink() {
        return hubLink(3);
    }

    private LinkItem hubLink(int age) {
        return new LinkItem(
                "Juguetes y regalos para niños de " + age + " años",
                EditorialDefaults.hubHref(age),
                "Hub por edad con necesidades, selección y contenidos de esta etapa."
        );
    }

    private static ArticlePageResponse.Section section(String id, String title, String... paragraphs) {
        return new ArticlePageResponse.Section(id, title, List.of(paragraphs));
    }

    private static ArticlePageResponse.Faq faq(String question, String answer) {
        return new ArticlePageResponse.Faq(question, answer);
    }
}
