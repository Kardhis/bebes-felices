package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.CategoryPageResponse;
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
public class CategoryPageService {

    public static final String EDUCATIONAL_TOYS_SLUG = "juguetes-educativos";
    public static final String MOVEMENT_SLUG = "movimiento";
    public static final String AUTONOMY_SLUG = "autonomia";
    public static final String GIFTS_SLUG = "regalos";

    private final Map<String, CategoryDefinition> categories = new LinkedHashMap<>();
    private final CollectionPageService collectionPageService;

    public CategoryPageService(CollectionPageService collectionPageService) {
        this.collectionPageService = collectionPageService;
        registerAll();
    }

    public Optional<CategoryPageResponse> getBySlug(String slug) {
        CategoryDefinition definition = categories.get(slug);
        if (definition == null) {
            return Optional.empty();
        }

        List<CategoryPageResponse.ChildCollection> childCollections = definition.collectionSlugs().stream()
                .map(collectionPageService::getBySlug)
                .flatMap(Optional::stream)
                .filter(page -> page.status() == PageStatus.PUBLISHED)
                .map(this::toChildLink)
                .toList();

        if (childCollections.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(toResponse(definition, childCollections));
    }

    public List<String> publishedSlugs() {
        return List.copyOf(categories.keySet());
    }

    public List<String> collectionSlugsForCategory(String slug) {
        CategoryDefinition definition = categories.get(slug);
        if (definition == null) {
            return List.of();
        }
        return List.copyOf(definition.collectionSlugs());
    }

    private void registerAll() {
        register(new CategoryDefinition(
                EDUCATIONAL_TOYS_SLUG,
                "/juguetes-educativos/",
                "Juguetes educativos",
                "Juguetes educativos para niños de 3 a 5 años",
                List.of(
                        "Esta categoría reúne selecciones editoriales por edad: juego simbólico, construcción, música, creatividad, lenguaje, lógica y juego compartido.",
                        "Las páginas se agrupan por una edad principal de 3, 4 o 5 años y muestran productos cuya categoría y rango de edad han sido revisados."
                ),
                List.of(
                        CollectionPageService.MONTESSORI_SLUG,
                        CollectionPageService.PUZZLES_SLUG,
                        CollectionPageService.STEM_SLUG,
                        CollectionPageService.BOARD_GAMES_SLUG,
                        CollectionPageService.SYMBOLIC_PLAY_SLUG,
                        CollectionPageService.SENSORY_TOYS_SLUG,
                        CollectionPageService.SMALL_WORLDS_SLUG,
                        CollectionPageService.MUSICAL_TOYS_SLUG,
                        CollectionPageService.CONSTRUCTION_TOYS_SLUG,
                        CollectionPageService.ARTS_CRAFTS_SLUG,
                        CollectionPageService.EXPERIMENTATION_SLUG,
                        CollectionPageService.LITERACY_SLUG,
                        CollectionPageService.MATH_LOGIC_SLUG,
                        CollectionPageService.COOPERATIVE_SEL_SLUG
                ),
                List.of(
                        faq(
                                "¿Por dónde empiezo si no conozco la edad exacta?",
                                "Usa las páginas por edad de Bebes Felices. A los 3 años predominan encajes y piezas grandes; a los 4 y 5 aparecen reglas sencillas y retos de construcción."
                        ),
                        faq(
                                "¿Estas páginas son rankings?",
                                "No. Son índices con criterios de compra y enlaces a comparativas o análisis cuando los hay. No publicamos puntuaciones inventadas."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Checklist práctico antes de comprar."
                        ),
                        new LinkItem(
                                "Juguetes y regalos para niños de 3 años",
                                EditorialDefaults.hubHref(3),
                                "Hub por edad con necesidades y selección destacada."
                        )
                )
        ));

        register(new CategoryDefinition(
                MOVEMENT_SLUG,
                "/movimiento/",
                "Movimiento",
                "Juguetes de movimiento para niños de 3 a 5 años",
                List.of(
                        "Bicicletas sin pedales, patinetes y juego activo al aire libre, con criterios de talla, peso y supervisión.",
                        "Las comparativas cubren modelos concretos; estas páginas explican qué buscar antes de elegir."
                ),
                List.of(
                        CollectionPageService.SCOOTERS_SLUG,
                        CollectionPageService.BALANCE_BIKES_SLUG
                ),
                List.of(
                        faq(
                                "¿A qué edad tiene sentido una bicicleta sin pedales?",
                                "Suele iniciarse hacia los 3 años con un modelo ligero y sillín a la altura correcta. A los 4 y 5 años muchos niños la usan para consolidar equilibrio."
                        ),
                        faq(
                                "¿Patinete de dos o tres ruedas?",
                                "A los 3 años la estabilidad extra de tres ruedas ayuda. Más adelante puede interesar un patinete de dos ruedas si el equilibrio ya está asentado."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "Comparativa por seguridad, talla y facilidad de uso."
                        ),
                        new LinkItem(
                                "Mejores patinetes para 4 años",
                                "/comparativas/mejores-patinetes-4-anos/",
                                "Estabilidad, plegado y uso real en exterior."
                        )
                )
        ));

        register(new CategoryDefinition(
                AUTONOMY_SLUG,
                "/autonomia/",
                "Autonomía",
                "Productos de autonomía para niños de 3 a 5 años",
                List.of(
                        "Torres de aprendizaje, vajilla adaptada y objetos que ayudan a participar en la rutina diaria con seguridad.",
                        "Priorizamos utilidad real en casa, materiales resistentes y encaje con la edad, no gadgets decorativos."
                ),
                List.of(
                        CollectionPageService.TOWERS_SLUG,
                        CollectionPageService.TABLEWARE_SLUG
                ),
                List.of(
                        faq(
                                "¿Una torre de aprendizaje es segura?",
                                "Depende del modelo, la supervisión y el espacio. Revisa estabilidad, barandilla y que el niño pueda subir y bajar sin quedar atrapado."
                        ),
                        faq(
                                "¿La vajilla infantil ayuda de verdad?",
                                "Sí, cuando el plato no se desliza, el peso es manejable y el niño puede servirse con autonomía progresiva."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Mejores torres de aprendizaje para 4 años",
                                "/comparativas/mejores-torres-aprendizaje-4-anos/",
                                "Estabilidad, altura y uso en cocina."
                        ),
                        new LinkItem(
                                "Mejores vajillas infantiles para 4 años",
                                "/comparativas/mejores-vajillas-infantiles-4-anos/",
                                "Antideslizante, materiales y facilidad de limpieza."
                        )
                )
        ));

        register(new CategoryDefinition(
                GIFTS_SLUG,
                "/regalos/",
                "Regalos",
                "Ideas de regalo por edad para niños de 3 a 5 años",
                List.of(
                        "Selecciones por edad y ocasión —incluidos cumpleaños— con utilidad real: aprender, moverse, ganar autonomía o crear.",
                        "Cada página reúne opciones del catálogo editorial con enlaces a comparativas o análisis cuando existen."
                ),
                List.of(
                        CollectionPageService.GIFTS_3_SLUG,
                        CollectionPageService.GIFTS_4_SLUG,
                        CollectionPageService.GIFTS_5_SLUG
                ),
                List.of(
                        faq(
                                "¿Cómo acertar con un regalo de cumpleaños?",
                                "Elige según lo que el niño ya hace: encajar, equilibrarse, imitar tareas de casa o jugar en partidas cortas. Evita piezas diminutas y reglas largas a edades tempranas."
                        ),
                        faq(
                                "¿Publicáis precios?",
                                "No. Los importes cambian con frecuencia. Evaluamos encaje, seguridad y durabilidad; el presupuesto lo decides tú al comprar."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para 3, 4 y 5 años."
                        ),
                        new LinkItem(
                                "Regalos más duraderos",
                                "/sostenibles/",
                                "Enfoque en materiales y uso a largo plazo."
                        )
                )
        ));
    }

    private void register(CategoryDefinition definition) {
        categories.put(definition.slug(), definition);
    }

    private CategoryPageResponse.ChildCollection toChildLink(CollectionPageResponse collection) {
        String description = collection.header().introductionParagraphs().isEmpty()
                ? collection.seo().metaDescription()
                : collection.header().introductionParagraphs().get(0);
        return new CategoryPageResponse.ChildCollection(
                collection.header().h1(),
                collection.canonicalPath(),
                description,
                collection.hubAge()
        );
    }

    private CategoryPageResponse toResponse(
            CategoryDefinition definition,
            List<CategoryPageResponse.ChildCollection> childCollections
    ) {
        return new CategoryPageResponse(
                new Seo(
                        EditorialDefaults.canonical(definition.path()),
                        definition.h1() + " | Bebes Felices",
                        definition.introduction().get(0)
                ),
                PageStatus.PUBLISHED,
                definition.slug(),
                definition.path(),
                List.of(
                        new CategoryPageResponse.Breadcrumb("Inicio", "/"),
                        new CategoryPageResponse.Breadcrumb(definition.label(), definition.path())
                ),
                new CategoryPageResponse.Header(
                        definition.label(),
                        definition.h1(),
                        definition.introduction()
                ),
                childCollections,
                definition.faq(),
                definition.relatedLinks(),
                EditorialDefaults.trustAuthority(),
                EditorialDefaults.affiliation(),
                EditorialDefaults.legalLinks(),
                new CategoryPageResponse.Author(
                        EditorialDefaults.AUTHOR_NAME,
                        EditorialDefaults.AUTHOR_ROLE
                ),
                EditorialDefaults.PUBLISHED_AT,
                EditorialDefaults.UPDATED_AT
        );
    }

    private static CategoryPageResponse.Faq faq(String question, String answer) {
        return new CategoryPageResponse.Faq(question, answer);
    }

    private record CategoryDefinition(
            String slug,
            String path,
            String label,
            String h1,
            List<String> introduction,
            List<String> collectionSlugs,
            List<CategoryPageResponse.Faq> faq,
            List<LinkItem> relatedLinks
    ) {
    }
}
