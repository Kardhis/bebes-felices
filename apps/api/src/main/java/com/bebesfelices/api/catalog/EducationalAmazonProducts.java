package com.bebesfelices.api.catalog;

import java.time.LocalDate;
import java.util.List;

/**
 * Productos contrastados en Amazon España para las colecciones educativas.
 * Cada ASIN se ha comprobado con una ficha viva en {@code www.amazon.es}.
 */
final class EducationalAmazonProducts {

    private static final String MARKETPLACE = "www.amazon.es";
    private static final LocalDate REVIEWED_AT = LocalDate.of(2026, 8, 18);

    private EducationalAmazonProducts() {
    }

    static List<Product> all() {
        return List.of(
                product("montessori-goula-baby-shapes", "B0BVR2K2N2", "Goula Baby Shapes", "Láminas y piezas de madera para encajar formas y colores con dificultad progresiva.", 2, 5, "Montessori"),
                product("montessori-janod-tropik", "B09R7Y6V1N", "Janod Mis primeras formas Tropik", "Soporte de madera FSC con piezas para encajar formas y colores.", 1, 3, "Montessori"),
                product("montessori-noah-ark", "B004KPKWH2", "Melissa & Doug Arca de Noé clasificadora", "Arca de madera con 26 piezas de animales para clasificar por forma.", 2, 5, "Montessori"),
                product("montessori-formas-geometricas", "B0020ZX668", "Melissa & Doug puzzle de formas geométricas", "Ocho piezas gruesas de madera para reconocer formas y colores.", 2, 5, "Montessori"),
                product("montessori-janod-animales", "B07C8CDF7F", "Janod Magneti'Book Animales", "Libro magnético con treinta imanes y diez modelos para recrear animales, de 3 a 8 años.", 3, 8, "Montessori"),
                product("montessori-melissa-tres-puzzles", "B09BW4GL7W", "Melissa & Doug pack de 3 puzzles de encaje", "Tres tableros de madera con granja, safari y vehículos, a partir de 3 años.", 3, 4, "Montessori"),
                product("montessori-janod-ballenas", "B09PVNL12L", "Janod clasificar ballenas por colores", "Cuatro ballenas de madera y doce animales marinos para clasificar por color con pinzas.", 2, 5, "Montessori"),

                product("puzle-educa-disney-madera", "B0CSP7HMG4", "Educa Disney Animals, 2 puzles de madera", "Dos puzles de 16 piezas de madera, recomendados a partir de 3 años.", 3, 6, "Puzles"),
                product("puzle-educa-selva", "B08SBSMC16", "Educa My First animales de la selva", "Cuatro puzles progresivos de 5 a 8 piezas para empezar a encajar.", 2, 5, "Puzles"),
                product("puzle-melissa-mascotas", "B0015XYV2E", "Melissa & Doug puzle de mascotas", "Tablero de madera con ocho piezas gruesas de mascotas que encajan por silueta y se sostienen de pie.", 2, 4, "Puzles"),
                product("puzle-melissa-granja-peg", "B00HWHO7M6", "Melissa & Doug puzle de granja con agarres", "Tablero de madera con ocho piezas con pomos para encajar animales de granja.", 2, 4, "Puzles"),

                product("stem-geomag-rainbow", "B07NZW9JJD", "Geomag Rainbow 32 piezas", "Barras, bolas y paneles magnéticos para construir estructuras desde 3 años.", 3, 8, "STEM"),
                product("stem-gears-beginners", "B00000DMCE", "Learning Resources Gears! principiantes", "Engranajes y manivelas para ver cómo un giro mueve el resto.", 3, 7, "STEM"),
                product("stem-code-go-mouse", "B01A5YMCH4", "Learning Resources Code & Go Robot Mouse", "Ratón programable con laberinto y tarjetas de secuencias.", 4, 8, "STEM"),
                product("stem-gravitrax-junior", "B0BSXC3F6Z", "GraviTrax Junior Starter Set L", "Circuito configurable para observar gravedad, recorrido y velocidad.", 3, 7, "STEM"),

                product("mesa-animal-mini", "B08R3XTWYG", "HABA Animal sobre Animal Mini", "Versión compacta de apilamiento a partir de 5 años, en caja de lata.", 5, 10, "Juegos de mesa"),

                product("simbolico-theo-klein-miele", "B07PQ8SCT3", "Theo Klein Cocina Miele 7199", "Cocina de madera con placa, horno, fregadero y accesorios.", 3, 8, "Juego simbólico"),
                product("simbolico-kidkraft-vintage", "B004A2QTRC", "KidKraft cocina vintage blanca", "Cocina de madera con nevera, horno, microondas y teléfono de juguete.", 3, 8, "Juego simbólico"),
                product("simbolico-small-foot-compacta", "B07GWXFWBC", "Small Foot cocina compacta", "Cocina de madera FSC que se monta y desmonta sin herramientas, con horno y utensilios.", 3, 8, "Juego simbólico"),
                product("simbolico-janod-macaron", "B06XHT5H1Q", "Janod cocina Macaron", "Cocina de madera con horno, fregadero de acero, placas con sonido y cinco accesorios.", 3, 8, "Juego simbólico"),
                product("simbolico-janod-veterinario", "B0CTKKWYJH", "Janod maletín de veterinario", "Maletín de tela con dieciséis accesorios de madera FSC para imitar el cuidado de animales, de 3 a 8 años.", 3, 8, "Juego simbólico"),
                product("simbolico-sundaymot-33", "B09YRBCPVL", "Sundaymot maletín médico de madera", "Set de 33 piezas para representar consultas médicas y dentales.", 3, 8, "Juego simbólico"),

                product("sensorial-emotion-bottles", "B09NQQQL5Z", "Learning Resources botellas sensoriales de emociones", "Cuatro botellas selladas con movimientos y expresiones diferentes.", 3, 7, "Sensoriales"),
                product("sensorial-playfoam", "B00J5LPDOS", "Learning Resources Playfoam, 6 bloques", "Espuma moldeable que no se seca ni se pega, para explorar textura y forma.", 3, 8, "Sensoriales"),
                product("sensorial-fidget-tubes", "B0BQ8K62B5", "Learning Resources Sensory Trio Fidget Tubes", "Tres tubos sellados con arena, brillo y cuentas para observar e inclinar.", 3, 8, "Sensoriales"),
                product("sensorial-scoops", "B0BQ8VB58C", "Learning Resources palas sensoriales Helping Hands", "Cuatro palas para verter, tamizar y transferir con manos pequeñas.", 3, 8, "Sensoriales"),
                product("sensorial-pinzas-jumbo", "B0041RXI16", "Learning Resources pinzas jumbo", "Pinzas grandes para recoger, transferir y fortalecer el agarre.", 3, 8, "Sensoriales"),
                product("sensorial-hundred-board", "B0BPN4TMM5", "Learning Resources tablero sensorial de 100 números", "Tablero de burbujas 10x10 para presionar, contar y regular la atención.", 3, 8, "Sensoriales"),

                product("mundos-schleich-foal", "B005VOXMNM", "Schleich potro Akhal-Teke", "Figura detallada para escenas de granja y relatos con animales.", 3, 10, "Pequeños mundos"),
                product("mundos-terra-wild", "B00TYO5UKI", "Terra by Battat animales salvajes", "Colección de miniaturas de fauna para crear un mundo de juego.", 3, 6, "Pequeños mundos"),
                product("mundos-schleich-farm", "B079NH9PVH", "Schleich Farm World granja 42407", "Granja con establo, animales y accesorios para juego narrativo desde 3 años.", 3, 10, "Pequeños mundos"),
                product("mundos-schleich-shire", "B009MJU686", "Schleich yegua Shire", "Figura de caballo de granja pintada a mano para escenas cotidianas.", 3, 10, "Pequeños mundos"),
                product("mundos-schleich-barn", "B0BSNZL3CS", "Schleich Farm World establo 42605", "Establo con animales y accesorios para alimentar, guardar y contar historias.", 3, 10, "Pequeños mundos"),
                product("mundos-schleich-farm-set", "B06VT4255G", "Schleich Farm World set de 5 animales", "Ternero, gato, perro, ganso y potro para ampliar un escenario de granja desde 3 años.", 3, 10, "Pequeños mundos"),

                product("musical-hape-piano", "B07CP2FCCV", "Baby Einstein Hape Magic Touch Piano", "Piano de madera con volumen regulable, partituras y canciones.", 1, 5, "Musicales"),
                product("musical-hape-xylophone", "B00712O2D6", "Hape Picafuerte Xilófono", "Banco de bolas y xilófono extraíble para explorar notas y ritmo.", 1, 10, "Musicales"),
                product("musical-percussion-8", "B0DD7XM4Q1", "Set de instrumentos de percusión 8 en 1", "Instrumentos de madera con maracas, percusión y accesorios.", 3, 10, "Musicales"),
                product("musical-hape-drum", "B07PTB92V6", "Hape tambor de dos caras", "Tambor de madera con baquetas y soporte para explorar ritmo y timbre.", 1, 5, "Musicales"),
                product("musical-hape-ukulele", "B09K8X7WRC", "Hape Rock N Roll ukelele", "Ukelele de madera con cuerdas reales y guía para rasgueo inicial.", 3, 8, "Musicales"),
                product("musical-hape-guitar", "B08C3R47GT", "Baby Einstein Hape guitarra Magic Touch", "Guitarra de madera táctil, sin cuerdas, para explorar acordes y volumen a los 3 años.", 1, 3, "Musicales"),

                product("construccion-lego-classic-10698", "B00PY3EYQO", "LEGO Classic caja grande 10698", "Caja de ladrillos, bases, ruedas, ventanas y piezas para construcción libre.", 4, 12, "Construcción"),
                product("construccion-lego-classic-10696", "B00NVDP3ZU", "LEGO Classic caja mediana 10696", "Caja de ladrillos creativos con base verde, coches y animales.", 4, 12, "Construcción"),
                product("construccion-duplo-10909", "B01N0OTT4X", "LEGO DUPLO caja del corazón 10909", "Ladrillos grandes DUPLO para construir formas reconocibles con manos pequeñas.", 2, 5, "Construcción"),
                product("construccion-playmags-32", "B09R9MTSKD", "Playmags azulejos magnéticos, 32 piezas", "Piezas magnéticas transparentes para estructuras planas y 3D.", 3, 10, "Construcción"),
                product("construccion-desire-magnetic", "B07MGYKZ36", "Bloques magnéticos educativos STEM", "Piezas geométricas imantadas para construir y tumbar sin un único modelo.", 3, 7, "Construcción"),
                product("construccion-gears-super", "B00000JGWY", "Learning Resources Gears! Gears! Gears! superjuego", "Engranajes para construir mecanismos y estructuras que se mantienen al girar.", 3, 8, "Construcción"),

                product("arte-crayola-pokemon-5in1", "B0C7HMDHLV", "Crayola Pokémon set creativo 5 en 1", "Rotuladores, ceras, páginas, pegatinas y figuras de cartón.", 4, 10, "Arte y manualidades"),
                product("arte-crayola-case-100", "B004TVMHGQ", "Crayola maletín de 100 accesorios", "Maletín con ceras, lápices y rotuladores para creación libre.", 4, 10, "Arte y manualidades"),
                product("arte-crayola-paw-patrol", "B0B3QNJ8F8", "Crayola maletín Patrulla Canina", "Maletín con material para colorear y hojas temáticas.", 3, 10, "Arte y manualidades"),
                product("arte-crayola-tempera-6", "B004Z4LTWU", "Crayola témpera lavable, 6 colores", "Seis botes de pintura al agua para papel y materiales porosos.", 3, 10, "Arte y manualidades"),
                product("arte-crayola-effects", "B07SRM3KW8", "Crayola témperas de efectos especiales", "Diez colores lavables con acabados neón, brillo y metal.", 3, 10, "Arte y manualidades"),
                product("arte-crayola-metallic", "B00FY2O79O", "Crayola pintura metálica lavable", "Seis colores con acabado metálico para proyectos creativos.", 3, 10, "Arte y manualidades"),
                product("arte-ses-eco-mega-7", "B08PC5TJBG", "SES Creative Eco mega set de modelado", "Siete colores de plastilina ecológica con rodillo de madera y cortadores de plástico reciclado.", 2, 12, "Arte y manualidades"),
                product("arte-jovi-pintura-dedos-6", "B0CBSSTRQT", "Jovi pintura de dedos lavable, 6 colores", "Seis botes de pintura de dedos a base de ingredientes naturales, sin gluten.", 2, 10, "Arte y manualidades"),
                product("arte-jovi-plastilina-vegetal-12", "B0FJSDDP29", "Jovi plastilina vegetal, 12 pastillas", "Doce pastillas de plastilina de base vegetal, no tóxica y sin gluten, a partir de 3 años.", 3, 10, "Arte y manualidades"),

                product("experimenta-numberblocks", "B0BT87WKYK", "Numberblocks amigos del uno al cinco", "Figuras proporcionales para ver el resultado de juntar y separar cantidades.", 3, 7, "Experimentación"),
                product("experimenta-cuisenaire", "B000FFWCOW", "Learning Resources regletas Cuisenaire", "Regletas para comprobar longitudes y cantidades al colocar y comparar.", 4, 8, "Experimentación"),

                product("lectura-educa-writing", "B0D4ZK8MFC", "Educa Mis primeras escrituras", "Láminas reutilizables para trazar y comprobar el recorrido de la letra.", 4, 6, "Lectoescritura"),
                product("lectura-smart-panda-magnets", "B07T31MWTD", "Smart Panda letras y números magnéticos", "Letras y números magnéticos para reconocer grafías y formar palabras sencillas.", 5, 10, "Lectoescritura"),
                product("lectura-diset-leer", "B0068A96N8", "Diset Yo Aprendo a Leer", "Maletín autocorrectivo: la letra solo encaja si es la correcta.", 5, 8, "Lectoescritura"),
                product("lectura-unicornio-memo", "B086FCQT6M", "HABA Unicornio Destello Memo mágico", "Juego de memoria con cartas para turnos, vocabulario e imágenes.", 3, 7, "Lectoescritura"),
                product("lectura-frutalito", "B0088MES78", "HABA El Frutalito", "Partida corta que nombra colores y frutas mientras se recolecta en grupo.", 3, 8, "Lectoescritura"),
                product("lectura-three-pigs", "B07B37TT7F", "Goula Los 3 Cerditos", "Relato conocido con turnos, vocabulario y una modalidad para llegar juntos a casa.", 3, 7, "Lectoescritura"),

                product("matematicas-sum-swamp", "B00004TDLD", "Learning Resources Sum Swamp", "Recorrido en el que cada tirada cambia el avance de forma visible.", 5, 9, "Matemáticas y lógica"),
                product("matematicas-lets-go-code", "B01N7MMCMO", "Learning Resources Let's Go Code!", "Juego sin pantalla para secuencias, direcciones y pensamiento lógico.", 5, 9, "Matemáticas y lógica")
        );
    }

    private static Product product(
            String id,
            String asin,
            String title,
            String description,
            int minAge,
            int maxAge,
            String subcategory
    ) {
        return new Product(
                id,
                ProductSource.MANUAL,
                asin,
                MARKETPLACE,
                title,
                description,
                minAge,
                maxAge,
                List.of("Juguetes educativos", subcategory),
                ProductStatus.ACTIVE,
                null,
                REVIEWED_AT
        );
    }
}
