package com.bebesfelices.api.service;

import com.bebesfelices.api.dto.HomeResponse;
import com.bebesfelices.api.dto.shared.AgeLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private static final String UPDATED_AT = "2026-08-17";

    private final String siteUrl;

    public HomeService(@Value("${app.site-url:https://bebesfelices.es}") String siteUrl) {
        this.siteUrl = stripTrailingSlash(siteUrl);
    }

    public HomeResponse getHome() {
        return new HomeResponse(
                new Seo(
                        siteUrl + "/",
                        "Productos, juguetes y regalos para niños de 3 a 5 años | Bebes Felices",
                        "Guías y comparativas para elegir juguetes y regalos adecuados según edad (3, 4 y 5 años), con criterios claros y transparencia de afiliación."
                ),
                new HomeResponse.Hero(
                        "Bebes Felices",
                        "Productos, juguetes y regalos para niños de 3 a 5 años",
                        "Selecciones, comparativas y guías claras para ayudarte a elegir con criterio según edad, desarrollo, presupuesto y uso.",
                        "Buscar por edad",
                        "/#por-edad",
                        "Explorar categorías",
                        "/#categorias",
                        "/images/home-hero.jpg",
                        "Bicicleta infantil y juguetes educativos de madera en una habitación luminosa"
                ),
                ageNavigation(),
                mainCategories(),
                featuredGuides(),
                recentComparisons(),
                EditorialDefaults.trustAuthority(),
                EditorialDefaults.affiliation(),
                EditorialDefaults.legalLinks(),
                UPDATED_AT
        );
    }

    private List<AgeLink> ageNavigation() {
        return List.of(
                new AgeLink("3 años", EditorialDefaults.hubHref(3)),
                new AgeLink("4 años", EditorialDefaults.hubHref(4)),
                new AgeLink("5 años", EditorialDefaults.hubHref(5))
        );
    }

    private List<LinkItem> mainCategories() {
        return List.of(
                new LinkItem(
                        "Sostenibles",
                        "/" + CategoryPageService.SUSTAINABLE_SLUG + "/",
                        "Opciones más duraderas y materiales pensados a largo plazo."
                ),
                new LinkItem(
                        "Educativos",
                        "/" + CategoryPageService.EDUCATIONAL_TOYS_SLUG + "/",
                        "STEM, Montessori, construcción y aprendizaje mediante el juego."
                ),
                new LinkItem(
                        "Movimiento",
                        "/" + CategoryPageService.MOVEMENT_SLUG + "/",
                        "Bicicletas sin pedales, patinetes y juego activo al aire libre."
                ),
                new LinkItem(
                        "Autonomía",
                        "/" + CategoryPageService.AUTONOMY_SLUG + "/",
                        "Productos que ayudan a ganar independencia en la rutina diaria."
                ),
                new LinkItem(
                        "Regalos",
                        "/" + CategoryPageService.GIFTS_SLUG + "/",
                        "Ideas por edad, ocasión y presupuesto sin perder utilidad."
                )
        );
    }

    private List<LinkItem> featuredGuides() {
        return List.of(
                new LinkItem(
                        "Cómo elegir juguetes según la edad",
                        "/guias/" + ArticlePageService.CHOOSE_BY_AGE_SLUG + "/",
                        "Criterios prácticos para 3, 4 y 5 años."
                ),
                new LinkItem(
                        "Ideas de regalo por edad",
                        "/" + CategoryPageService.GIFTS_SLUG + "/",
                        "Selecciones por edad y ocasión con utilidad real."
                ),
                new LinkItem(
                        "Cómo analizamos en Bebes Felices",
                        "/como-analizamos/",
                        "Metodología, fuentes y límites de nuestras recomendaciones."
                )
        );
    }

    private List<LinkItem> recentComparisons() {
        return List.of(
                comparison(
                        "Mejores bicicletas sin pedales para 3 años",
                        ComparisonPageService.BALANCE_BIKES_SLUG,
                        "Comparativa por seguridad, talla y facilidad de uso."
                ),
                comparison(
                        "Mejores juegos de mesa para 4 años",
                        ComparisonPageService.BOARD_GAMES_SLUG,
                        "Opciones cooperativas y de turnos cortos."
                ),
                comparison(
                        "Mejores juguetes STEM para 5 años",
                        ComparisonPageService.STEM_5_SLUG,
                        "Construcción, lógica y experimentación."
                )
        );
    }

    private LinkItem comparison(String title, String slug, String description) {
        return new LinkItem(title, "/comparativas/" + slug + "/", description);
    }

    private static String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
