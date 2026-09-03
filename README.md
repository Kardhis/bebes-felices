# Bebes Felices

Plataforma editorial de guías, comparativas y recomendaciones de productos para niños de **3 a 5 años**. El sitio prioriza criterios claros de selección, transparencia editorial y SEO técnico, con enlaces de afiliación de Amazon debidamente señalizados.

**Sitio de producción:** [bebesfelices.es](https://bebesfelices.es)

---

## Tabla de contenidos

- [Arquitectura](#arquitectura)
- [Stack tecnológico](#stack-tecnológico)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Requisitos previos](#requisitos-previos)
- [Puesta en marcha](#puesta-en-marcha)
- [Variables de entorno](#variables-de-entorno)
- [Scripts disponibles](#scripts-disponibles)
- [Tests](#tests)
- [API REST](#api-rest)
- [SEO y metadatos](#seo-y-metadatos)
- [Estado del proyecto](#estado-del-proyecto)

---

## Arquitectura

Monorepo con dos aplicaciones independientes:

```
┌─────────────────────┐         ┌──────────────────────────┐
│   apps/web          │  HTTP   │   apps/api               │
│   Next.js 16        │ ──────► │   Spring Boot 4          │
│   (frontend SSR)    │  JSON   │   (contenido + catálogo) │
└─────────────────────┘         └──────────────────────────┘
         │                                    │
         ▼                                    ▼
   bebesfelices.es                    localhost:8080
```

- **Frontend (`apps/web`):** renderiza la home, las páginas hub por edad (`/por-edad/3-anos/`, etc.) y rutas futuras. Consume la API en tiempo de build y con revalidación incremental (ISR, 60 s).
- **Backend (`apps/api`):** expone el contenido editorial y el catálogo de productos como JSON. En el MVP el contenido es estático en código; la arquitectura está preparada para sustituirlo por persistencia o CMS sin romper el contrato de la API.

---

## Stack tecnológico

| Capa        | Tecnologías                                              |
|-------------|----------------------------------------------------------|
| Frontend    | Next.js 16, React 19, TypeScript, Tailwind CSS 4        |
| Backend     | Java 17, Spring Boot 4, Spring Web, Spring Validation   |
| Tests       | Vitest + Testing Library (web), JUnit 5 (API)           |
| Tooling     | npm workspaces, ESLint, Maven                           |

---

## Estructura del repositorio

```
BebesFelices/
├── apps/
│   ├── web/                          # Frontend Next.js
│   │   ├── src/
│   │   │   ├── app/                  # App Router (páginas, sitemap, robots)
│   │   │   ├── components/           # UI (home, age, comparison, editorial)
│   │   │   └── lib/                  # Clientes API, SEO, utilidades
│   │   └── package.json
│   └── api/                          # Backend Spring Boot
│       └── src/main/java/com/bebesfelices/api/
│           ├── controller/           # Endpoints REST
│           ├── service/              # Lógica de páginas por edad
│           ├── catalog/              # Catálogo de productos (manual / Amazon)
│           └── dto/                  # Contratos JSON
├── package.json                      # Scripts del monorepo
└── README.md
```

---

## Requisitos previos

- **Node.js** 20 o superior
- **npm** 10+
- **Java** 17
- **Maven** 3.9+

---

## Puesta en marcha

### 1. Instalar dependencias del frontend

```bash
npm install
```

### 2. Arrancar la API

Para arrancar API y frontend juntos desde la raíz:

```bash
npm run dev
```

También se pueden ejecutar por separado:

```bash
cd apps/api
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

### 3. Arrancar el frontend

En otra terminal, desde la raíz del repositorio:

```bash
npm run dev:web
```

Abre [http://localhost:3000](http://localhost:3000).

> El frontend espera la API en `http://localhost:8080` por defecto. Si no está disponible, la Home muestra una pantalla controlada con una acción de reintento, sin exponer detalles técnicos.

---

## Variables de entorno

Copia `.env.example` a `apps/web/.env.local` para el frontend y exporta las variables `APP_*` en el entorno de la API:

```env
# URL base de la API (obligatoria en producción)
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080

# URL canónica del sitio (sitemap, JSON-LD, metadatos)
NEXT_PUBLIC_SITE_URL=http://localhost:3000
```

| Variable                    | Descripción                                      | Valor por defecto              |
|-----------------------------|--------------------------------------------------|--------------------------------|
| `NEXT_PUBLIC_API_BASE_URL`  | Origen de la API REST                            | `http://localhost:8080`        |
| `NEXT_PUBLIC_SITE_URL`      | URL pública del sitio                            | `https://bebesfelices.es`      |
| `APP_SITE_URL`              | URL canónica que emite la API                    | `https://bebesfelices.es`      |
| `APP_CORS_ALLOWED_ORIGINS`  | Orígenes web permitidos, separados por comas     | URLs locales de Next.js        |

### Amazon Creators API

El backend activa automáticamente el enriquecimiento de productos cuando
encuentra todas las credenciales necesarias. Para Amazon España, exporta las
variables antes de arrancar Spring Boot:

```bash
export AMAZON_CREATORS_CREDENTIAL_ID="..."
export AMAZON_CREATORS_CREDENTIAL_SECRET="..."
export AMAZON_CREATORS_CREDENTIAL_VERSION="3.2"
export AMAZON_CREATORS_PARTNER_TAG="..."
export AMAZON_PRODUCT_JUEGO_MONTESSORI_FORMAS_ASIN="B00005RF5G"
export AMAZON_PRODUCT_PUZLE_MADERA_ANIMALES_ASIN="B00HWHNNRG"
export AMAZON_PRODUCT_BICI_SIN_PEDALES_BASICA_ASIN="..."
export AMAZON_PRODUCT_BICI_CHICCO_RED_BULLET_ASIN="B004MW55Z2"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_TOVE_ASIN="B0CF5XRJ6S"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_FLY_PLUS_2_ASIN="B0CZTVT1DN"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_GOSWIFT_ASIN="B092JTG2YL"
export AMAZON_PRODUCT_BICI_PUKY_LR_M_ASIN="B0DJ7DS33P"
export AMAZON_PRODUCT_PATINETE_3_RUEDAS_ASIN="B0B82TSPP8"
export AMAZON_PRODUCT_PATINETE_MICRO_MINI_DELUXE_ASIN="B0B82TSPP8"
export AMAZON_PRODUCT_PATINETE_MOLTO_MAXI_ASIN="B0D45VJLR8"
export AMAZON_PRODUCT_PATINETE_GLOBBER_JUNIOR_FOLDABLE_ASIN="B0BYSX61WD"
export AMAZON_PRODUCT_PATINETE_GLOBBER_MASTER_LIGHTS_ASIN="B08G19X6GK"
export AMAZON_PRODUCT_PATINETE_MICRO_MINI_3EN1_ASIN="B07RM5Z2LY"
export AMAZON_PRODUCT_PATINETE_YVOLUTION_Y_GLIDER_ASIN="B09WHX5FCK"
export AMAZON_PRODUCT_PATINETE_COLORBABY_EEZI_MINI_ASIN="B0BD8WT7ZL"
export AMAZON_PRODUCT_PATINETE_GLOBBER_PRIMO_FOLDABLE_ASIN="B09D3TW5MT"
export AMAZON_PRODUCT_PATINETE_LIONELO_TIMMY_ASIN="B0CD7YW5H8"
export AMAZON_PRODUCT_PATINETE_GLOBBER_GO_UP_PLUS_ASIN="B0BYSV5944"
export AMAZON_PRODUCT_TRICICLO_KINDERKRAFT_ASTON_ASIN="B0D5B7JM1Q"
export AMAZON_PRODUCT_TRICICLO_CHICCO_U_GO_ASIN="B00URLWKYG"
export AMAZON_PRODUCT_TORRE_APRENDIZAJE_MADERA_ASIN="B0B7RFPP5Z"
export AMAZON_PRODUCT_SET_VAJILLA_INFANTIL_ASIN="B0CZTZ917D"
export AMAZON_PRODUCT_KIT_MANUALIDADES_NATURAL_ASIN="B09MSCSYB3"
export AMAZON_PRODUCT_JUEGO_MESA_EL_FRUTAL_MINI_ASIN="B08R3YTDPQ"
export AMAZON_PRODUCT_JUEGO_MESA_UNICORNIO_TESORO_ASIN="B01MRA4YCR"
export AMAZON_PRODUCT_JUEGO_MESA_ANIMAL_SOBRE_ANIMAL_ASIN="B00D6J9SJQ"
export AMAZON_PRODUCT_JUEGO_MESA_DOBBLE_KIDS_ASIN="B00OM7VIC6"
export AMAZON_PRODUCT_JUEGO_MESA_UNICORNIO_FIESTA_ROSALIE_ASIN="B06XCLF568"
```

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `AMAZON_CREATORS_CREDENTIAL_ID` | Credential ID OAuth 2.0 | Sin configurar |
| `AMAZON_CREATORS_CREDENTIAL_SECRET` | Credential Secret OAuth 2.0 | Sin configurar |
| `AMAZON_CREATORS_CREDENTIAL_VERSION` | Región de la credencial (`3.2` para UE) | `3.2` |
| `AMAZON_CREATORS_PARTNER_TAG` | Tracking ID de Afiliados para Amazon España | Sin configurar |
| `AMAZON_CREATORS_MARKETPLACE` | Marketplace consultado | `www.amazon.es` |
| `AMAZON_CREATORS_PRODUCT_CACHE_TTL` | Tiempo de caché de productos | `1h` |
| `AMAZON_PRODUCT_JUEGO_MONTESSORI_FORMAS_ASIN` | ASIN del juego Montessori de formas | `B00005RF5G` |
| `AMAZON_PRODUCT_PUZLE_MADERA_ANIMALES_ASIN` | ASIN del puzle de madera de animales | `B00HWHNNRG` |
| `AMAZON_PRODUCT_BICI_SIN_PEDALES_BASICA_ASIN` | ASIN piloto para la bicicleta genérica | Sin configurar |
| `AMAZON_PRODUCT_BICI_CHICCO_RED_BULLET_ASIN` | ASIN de Chicco Red Bullet | `B004MW55Z2` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_TOVE_ASIN` | ASIN de Kinderkraft TOVE | `B0CF5XRJ6S` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_FLY_PLUS_2_ASIN` | ASIN de Kinderkraft FLY PLUS 2 | `B0CZTVT1DN` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_GOSWIFT_ASIN` | ASIN de Kinderkraft GOSWIFT | `B092JTG2YL` |
| `AMAZON_PRODUCT_BICI_PUKY_LR_M_ASIN` | ASIN de PUKY LR M | `B0DJ7DS33P` |
| `AMAZON_PRODUCT_PATINETE_3_RUEDAS_ASIN` | ASIN del patinete de 3 ruedas | `B0B82TSPP8` |
| `AMAZON_PRODUCT_PATINETE_MICRO_MINI_DELUXE_ASIN` | ASIN de Micro Mini Deluxe LED | `B0B82TSPP8` |
| `AMAZON_PRODUCT_PATINETE_MOLTO_MAXI_ASIN` | ASIN de MOLTO Maxi Scooter | `B0D45VJLR8` |
| `AMAZON_PRODUCT_PATINETE_GLOBBER_JUNIOR_FOLDABLE_ASIN` | ASIN de Globber Junior Foldable Lights | `B0BYSX61WD` |
| `AMAZON_PRODUCT_PATINETE_GLOBBER_MASTER_LIGHTS_ASIN` | ASIN de Globber Master Lights | `B08G19X6GK` |
| `AMAZON_PRODUCT_PATINETE_MICRO_MINI_3EN1_ASIN` | ASIN de Micro Mini 3en1 Deluxe Plus | `B07RM5Z2LY` |
| `AMAZON_PRODUCT_PATINETE_YVOLUTION_Y_GLIDER_ASIN` | ASIN de Yvolution Y Glider | `B09WHX5FCK` |
| `AMAZON_PRODUCT_PATINETE_COLORBABY_EEZI_MINI_ASIN` | ASIN de ColorBaby Eezi Mini | `B0BD8WT7ZL` |
| `AMAZON_PRODUCT_PATINETE_GLOBBER_PRIMO_FOLDABLE_ASIN` | ASIN de Globber Primo Foldable Lights | `B09D3TW5MT` |
| `AMAZON_PRODUCT_PATINETE_LIONELO_TIMMY_ASIN` | ASIN de Lionelo Timmy | `B0CD7YW5H8` |
| `AMAZON_PRODUCT_PATINETE_GLOBBER_GO_UP_PLUS_ASIN` | ASIN de Globber GO UP Foldable Plus Eco | `B0BYSV5944` |
| `AMAZON_PRODUCT_TRICICLO_KINDERKRAFT_ASTON_ASIN` | ASIN de Kinderkraft Aston | `B0D5B7JM1Q` |
| `AMAZON_PRODUCT_TRICICLO_CHICCO_U_GO_ASIN` | ASIN de Chicco U-GO 2en1 | `B00URLWKYG` |
| `AMAZON_PRODUCT_TORRE_APRENDIZAJE_MADERA_ASIN` | ASIN de la torre de aprendizaje | `B0B7RFPP5Z` |
| `AMAZON_PRODUCT_TORRE_YOLEO_TRANSFORMER_ASIN` | ASIN de YOLEO Transformer | `B09999YJXZ` |
| `AMAZON_PRODUCT_TORRE_HAUCK_LEARN_N_EXPLORE_ASIN` | ASIN de hauck Learn N Explore | `B0BVRHDCJ7` |
| `AMAZON_PRODUCT_TORRE_COSTWAY_PLEGABLE_ASIN` | ASIN de COSTWAY Plegable 3 en 1 | `B0D1GJGDJW` |
| `AMAZON_PRODUCT_TORRE_BEY_CO_ASIN` | ASIN de BEY & CO | `B09BRF6D95` |
| `AMAZON_PRODUCT_TORRE_MAXI_COSI_TOUCAN_ASIN` | ASIN de Maxi-Cosi Toucan 3 en 1 | `B0D4QVVBQD` |
| `AMAZON_PRODUCT_SET_VAJILLA_INFANTIL_ASIN` | ASIN del set de vajilla infantil | `B0CZTZ917D` |
| `AMAZON_PRODUCT_VAJILLA_STOR_MICKEY_ASIN` | ASIN de Stor vajilla Mickey Mouse | `B0CZTZ917D` |
| `AMAZON_PRODUCT_VAJILLA_TWISTSHAKE_DIVIDIDO_ASIN` | ASIN de Twistshake plato con compartimentos | `B0799HW4HR` |
| `AMAZON_PRODUCT_VASO_MUNCHKIN_MIRACLE_360_ASIN` | ASIN de Munchkin Miracle 360 | `B07HZQTB3V` |
| `AMAZON_PRODUCT_VAJILLA_FUN_HOUSE_ASIN` | ASIN de Fun House vajilla 3 piezas | `B07TDSPP4B` |
| `AMAZON_PRODUCT_CUENCO_TWISTSHAKE_TAPA_ASIN` | ASIN de Twistshake cuenco con tapa | `B0799J4GNK` |
| `AMAZON_PRODUCT_KIT_MANUALIDADES_NATURAL_ASIN` | ASIN del kit SES Creative Eco | `B09MSCSYB3` |
| `AMAZON_PRODUCT_CUENTAS_MELISSA_DOUG_ASIN` | ASIN de Melissa & Doug cuentas de madera | `B0015XJUV6` |
| `AMAZON_PRODUCT_PLANTOYS_ATA_ZAPATO_ASIN` | ASIN de PlanToys Ata el zapato | `B092HVBGB3` |
| `AMAZON_PRODUCT_HABA_PUZLES_CUATRO_ESTACIONES_ASIN` | ASIN de HABA Puzzles Las Cuatro Estaciones | `B01CSUXO2U` |
| `AMAZON_PRODUCT_SMALL_FOOT_GRUA_ASIN` | ASIN de Small Foot grúa | `B07MVR126C` |
| `AMAZON_PRODUCT_GREEN_TOYS_CONSTRUCCION_ASIN` | ASIN de Green Toys vehículos de construcción | `B01KJNM7O4` |
| `AMAZON_PRODUCT_JUEGO_MESA_EL_FRUTAL_MINI_ASIN` | ASIN de HABA El Frutal Mini | `B08R3YTDPQ` |
| `AMAZON_PRODUCT_JUEGO_MESA_UNICORNIO_TESORO_ASIN` | ASIN de Unicornio Destello El Tesoro de las Nubes | `B01MRA4YCR` |
| `AMAZON_PRODUCT_JUEGO_MESA_ANIMAL_SOBRE_ANIMAL_ASIN` | ASIN de HABA Animal sobre Animal | `B00D6J9SJQ` |
| `AMAZON_PRODUCT_JUEGO_MESA_DOBBLE_KIDS_ASIN` | ASIN de Dobble Kids (DOKI01ES) | `B00OM7VIC6` |
| `AMAZON_PRODUCT_JUEGO_MESA_UNICORNIO_FIESTA_ROSALIE_ASIN` | ASIN de Unicornio Destello Una Fiesta para Rosalie | `B06XCLF568` |

No guardes credenciales en el repositorio ni las expongas como variables
`NEXT_PUBLIC_*`. El backend dispone de un fallback manual que no necesita
Creators API: si están configurados `AMAZON_CREATORS_PARTNER_TAG` y el ASIN del
producto, construye y valida un enlace `https://www.amazon.es/dp/{ASIN}?tag={TAG}`.
Los ASIN de la comparativa de bicicletas, de las comparativas de juegos de mesa y de patinetes para 4 años y de la selección destacada de 3 años están verificados y configurados por defecto; sus variables permiten sustituirlos sin modificar el código.
Si falta cualquiera de ellos, sirve el contenido editorial con
`affiliateHref: null`. Cuando sí existen todas las credenciales de Creators API,
el catálogo intenta enriquecer el producto y vuelve al mismo enlace manual si
Amazon no responde o devuelve una URL no válida.

---

## Scripts disponibles

Desde la raíz del monorepo:

| Comando           | Descripción                          |
|-------------------|--------------------------------------|
| `npm run dev`     | API y frontend coordinados           |
| `npm run dev:web` | Servidor de desarrollo Next.js       |
| `npm run build:web` | Build de producción del frontend   |
| `npm run lint:web`  | ESLint sobre `apps/web`            |
| `npm run validate`  | Tests, lint y build con la API activa |
| `npm run verify`    | Validación completa, incluido E2E   |

Dentro de `apps/web`:

| Comando        | Descripción                    |
|----------------|--------------------------------|
| `npm run dev`  | `next dev --webpack`           |
| `npm run build`| Build de producción            |
| `npm run start`| Servidor de producción         |
| `npm run test` | Vitest (unit tests)            |
| `npm run test:e2e` | Smoke tests Playwright      |

Dentro de `apps/api`:

| Comando                  | Descripción              |
|--------------------------|--------------------------|
| `mvn spring-boot:run`    | Arrancar la API          |
| `mvn test`               | Ejecutar tests JUnit     |
| `mvn package`            | Compilar JAR ejecutable  |

---

## Tests

**Frontend:**

```bash
cd apps/web
npm test
```

**Backend:**

```bash
cd apps/api
mvn test
```

Los tests cubren clientes API, OAuth y mapeo de Amazon Creators, componentes
clave, esquemas JSON-LD, controladores REST, Home y el catálogo de productos.
Antes del primer E2E instala Chromium con `npx playwright install chromium`.

---

## API REST

Base URL: `http://localhost:8080/api`

| Método | Ruta                    | Descripción                              |
|--------|-------------------------|------------------------------------------|
| `GET`  | `/api/home`             | Contenido de la página de inicio         |
| `GET`  | `/api/age-pages/{slug}` | Página hub por edad (`3-anos`, `4-anos`, `5-anos`) |
| `GET`  | `/api/comparison-pages/{slug}` | Comparativa editorial (`mejores-bicicletas-sin-pedales-3-anos`, `mejores-juegos-de-mesa-4-anos`, `mejores-patinetes-4-anos`, `mejores-torres-aprendizaje-4-anos`, `mejores-vajillas-infantiles-4-anos`, `mejores-regalos-sostenibles-4-anos`) |
| `GET`  | `/api/article-pages/{slug}` | Guías y metodología (`como-elegir-juguetes-por-edad`, `habilidades-3-anos`, `habilidades-4-anos`, `como-analizamos`) |
| `GET`  | `/api/collection-pages/{slug}` | Categorías, regalos y sostenibles de los circuitos de 3 y 4 años |
| `GET`  | `/api/product-pages/{id}` | Análisis editorial de un producto del catálogo |

Ejemplo:

```bash
curl http://localhost:8080/api/age-pages/3-anos
curl http://localhost:8080/api/comparison-pages/mejores-bicicletas-sin-pedales-3-anos
curl http://localhost:8080/api/comparison-pages/mejores-juegos-de-mesa-4-anos
curl http://localhost:8080/api/comparison-pages/mejores-patinetes-4-anos
curl http://localhost:8080/api/article-pages/habilidades-4-anos
curl http://localhost:8080/api/article-pages/habilidades-3-anos
curl http://localhost:8080/api/collection-pages/puzles
curl http://localhost:8080/api/product-pages/patinete-3-ruedas
```

En desarrollo, CORS permite peticiones desde `http://localhost:3000`.

---

## SEO y metadatos

El frontend incluye:

- Metadatos dinámicos (`title`, `description`, URL canónica, Open Graph) por página
- JSON-LD: `Organization`, `WebSite`, `WebPage`, `BreadcrumbList`, `CollectionPage`, `FAQPage`, `ItemList`, `Article`
- `sitemap.xml` y `robots.txt` generados automáticamente
- Rutas con barra final (`trailingSlash: true`)
- Generación estática de páginas por edad, comparativas, guías, colecciones y análisis con `generateStaticParams`

---

## Estado del proyecto

Este repositorio contiene el **MVP funcional** del proyecto:

- Home editorial con navegación por edad y categorías
- Páginas hub completas para 3, 4 y 5 años (selección destacada, FAQ, guías, rankings)
- Circuito editorial de 3 años: guías, categorías, regalos, análisis y metodología
- Circuito editorial de 4 años: comparativas de juegos de mesa, patinetes, torres, vajilla y regalos sostenibles, guía de habilidades, STEM, bicicletas, regalos y análisis
- Circuito editorial de 5 años: comparativa STEM, juegos de mesa, guía de habilidades, regalos y análisis
- Catálogo de productos manual en memoria
- Avisos de afiliación Amazon visibles
- Infraestructura de tests en frontend y backend

**Próximos pasos previstos** (según la arquitectura actual):

- Activación de Amazon Creators API con credenciales y ASIN reales
- Persistencia de contenido / CMS
- Despliegue en producción con variables de entorno definitivas

---

## Licencia

Proyecto privado. Todos los derechos reservados.
