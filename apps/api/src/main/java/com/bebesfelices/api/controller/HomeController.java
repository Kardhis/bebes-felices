package com.bebesfelices.api.controller;

import com.bebesfelices.api.dto.HomeResponse;
import com.bebesfelices.api.dto.shared.AgeLink;
import com.bebesfelices.api.dto.shared.Affiliation;
import com.bebesfelices.api.dto.shared.LegalLink;
import com.bebesfelices.api.dto.shared.LinkItem;
import com.bebesfelices.api.dto.shared.Seo;
import com.bebesfelices.api.dto.shared.TrustAuthority;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @GetMapping("/home")
    public HomeResponse getHome() {
        return new HomeResponse(
                new Seo(
                        "https://bebesfelices.es/",
                        "Productos, juguetes y regalos para niños de 3 a 5 años | Bebes Felices",
                        "Guías y comparativas para elegir juguetes y regalos adecuados según edad (3, 4 y 5 años), con criterios claros y transparencia de afiliación."
                ),
                new HomeResponse.Hero(
                        "Bebes Felices",
                        "Productos, juguetes y regalos para niños de 3 a 5 años",
                        "Selecciones, comparativas y guías claras para ayudarte a elegir con criterio según edad, desarrollo, presupuesto y uso.",
                        "Buscar por edad",
                        "#por-edad",
                        "Explorar categorías",
                        "#categorias",
                        "https://images.unsplash.com/photo-1503454537195-1dcabb73ffb9?auto=format&fit=crop&w=2000&q=80",
                        "Niña jugando en el suelo con piezas de madera y juguetes educativos"
                ),
                List.of(
                        new AgeLink("3 años", "/por-edad/3-anos/"),
                        new AgeLink("4 años", "/por-edad/4-anos/"),
                        new AgeLink("5 años", "/por-edad/5-anos/")
                ),
                List.of(
                        new LinkItem(
                                "Juguetes educativos",
                                "/juguetes-educativos/",
                                "STEM, Montessori, construcción y aprendizaje mediante el juego."
                        ),
                        new LinkItem(
                                "Movimiento",
                                "/movimiento/",
                                "Bicicletas sin pedales, patinetes y juego activo al aire libre."
                        ),
                        new LinkItem(
                                "Autonomía",
                                "/autonomia/",
                                "Productos que ayudan a ganar independencia en la rutina diaria."
                        ),
                        new LinkItem(
                                "Regalos",
                                "/regalos/",
                                "Ideas por edad, ocasión y presupuesto sin perder utilidad."
                        ),
                        new LinkItem(
                                "Sostenibles",
                                "/sostenibles/",
                                "Opciones más duraderas y materiales pensados a largo plazo."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Cómo elegir juguetes según la edad",
                                "/guias/como-elegir-juguetes-por-edad/",
                                "Criterios prácticos para 3, 4 y 5 años."
                        ),
                        new LinkItem(
                                "Guía de regalos de cumpleaños",
                                "/guias/regalos-cumpleanos-3-a-5-anos/",
                                "Ideas útiles que no acaban en el fondo del armario."
                        ),
                        new LinkItem(
                                "Cómo analizamos en Bebes Felices",
                                "/como-analizamos/",
                                "Metodología, fuentes y límites de nuestras recomendaciones."
                        )
                ),
                List.of(
                        new LinkItem(
                                "Mejores bicicletas sin pedales para 3 años",
                                "/comparativas/mejores-bicicletas-sin-pedales-3-anos/",
                                "Comparativa por seguridad, talla y facilidad de uso."
                        ),
                        new LinkItem(
                                "Mejores juegos de mesa para 4 años",
                                "/comparativas/mejores-juegos-de-mesa-4-anos/",
                                "Opciones cooperativas y de turnos cortos."
                        ),
                        new LinkItem(
                                "Mejores juguetes STEM para 5 años",
                                "/comparativas/mejores-juguetes-stem-5-anos/",
                                "Construcción, lógica y experimentación."
                        )
                ),
                new TrustAuthority(
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
                ),
                new Affiliation(
                        "Bebes Felices participa en el Programa de Afiliados de Amazon. Algunos enlaces de esta página son enlaces de afiliado. Esto significa que podemos recibir una comisión si realizas una compra, sin que el precio cambie para ti.",
                        "Algunos enlaces pueden ser de afiliado de Amazon."
                ),
                List.of(
                        new LegalLink("Aviso legal", "/aviso-legal/"),
                        new LegalLink("Política de privacidad", "/politica-privacidad/"),
                        new LegalLink("Política de cookies", "/politica-cookies/"),
                        new LegalLink("Condiciones de uso", "/condiciones-uso/"),
                        new LegalLink("Información sobre afiliación", "/informacion-afiliacion/")
                ),
                "2026-07-29"
        );
    }
}
