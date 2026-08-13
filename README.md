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
│   │   │   ├── components/           # UI (home, age, SEO)
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

> El frontend espera la API en `http://localhost:8080` por defecto. Si la API no está en marcha, las páginas que consumen datos fallarán en tiempo de renderizado.

---

## Variables de entorno

Crea un archivo `apps/web/.env.local` para desarrollo:

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

### Amazon Creators API

El backend activa automáticamente el enriquecimiento de productos cuando
encuentra todas las credenciales necesarias. Para Amazon España, exporta las
variables antes de arrancar Spring Boot:

```bash
export AMAZON_CREATORS_CREDENTIAL_ID="..."
export AMAZON_CREATORS_CREDENTIAL_SECRET="..."
export AMAZON_CREATORS_CREDENTIAL_VERSION="3.2"
export AMAZON_CREATORS_PARTNER_TAG="..."
export AMAZON_PRODUCT_JUEGO_MONTESSORI_FORMAS_ASIN="..."
export AMAZON_PRODUCT_BICI_SIN_PEDALES_BASICA_ASIN="..."
export AMAZON_PRODUCT_BICI_CHICCO_RED_BULLET_ASIN="B004MW55Z2"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_TOVE_ASIN="B0CF5XRJ6S"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_FLY_PLUS_2_ASIN="B0CZTVT1DN"
export AMAZON_PRODUCT_BICI_KINDERKRAFT_GOSWIFT_ASIN="B092JTG2YL"
export AMAZON_PRODUCT_BICI_PUKY_LR_M_ASIN="B0DJ7DS33P"
```

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `AMAZON_CREATORS_CREDENTIAL_ID` | Credential ID OAuth 2.0 | Sin configurar |
| `AMAZON_CREATORS_CREDENTIAL_SECRET` | Credential Secret OAuth 2.0 | Sin configurar |
| `AMAZON_CREATORS_CREDENTIAL_VERSION` | Región de la credencial (`3.2` para UE) | `3.2` |
| `AMAZON_CREATORS_PARTNER_TAG` | Tracking ID de Afiliados para Amazon España | Sin configurar |
| `AMAZON_CREATORS_MARKETPLACE` | Marketplace consultado | `www.amazon.es` |
| `AMAZON_CREATORS_PRODUCT_CACHE_TTL` | Tiempo de caché de productos | `1h` |
| `AMAZON_PRODUCT_JUEGO_MONTESSORI_FORMAS_ASIN` | ASIN piloto para el juego Montessori | Sin configurar |
| `AMAZON_PRODUCT_BICI_SIN_PEDALES_BASICA_ASIN` | ASIN piloto para la bicicleta | Sin configurar |
| `AMAZON_PRODUCT_BICI_CHICCO_RED_BULLET_ASIN` | ASIN de Chicco Red Bullet | `B004MW55Z2` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_TOVE_ASIN` | ASIN de Kinderkraft TOVE | `B0CF5XRJ6S` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_FLY_PLUS_2_ASIN` | ASIN de Kinderkraft FLY PLUS 2 | `B0CZTVT1DN` |
| `AMAZON_PRODUCT_BICI_KINDERKRAFT_GOSWIFT_ASIN` | ASIN de Kinderkraft GOSWIFT | `B092JTG2YL` |
| `AMAZON_PRODUCT_BICI_PUKY_LR_M_ASIN` | ASIN de PUKY LR M | `B0DJ7DS33P` |

No guardes credenciales en el repositorio ni las expongas como variables
`NEXT_PUBLIC_*`. El backend dispone de un fallback manual que no necesita
Creators API: si están configurados `AMAZON_CREATORS_PARTNER_TAG` y el ASIN del
producto, construye y valida un enlace `https://www.amazon.es/dp/{ASIN}?tag={TAG}`.
Los cinco ASIN de la primera comparativa están verificados y configurados por
defecto; sus variables permiten sustituirlos sin modificar el código.
Si falta cualquiera de ellos, sirve el contenido editorial con
`affiliateHref: null`. Cuando sí existen todas las credenciales de Creators API,
el catálogo intenta enriquecer el producto y vuelve al mismo enlace manual si
Amazon no responde o devuelve una URL no válida.

---

## Scripts disponibles

Desde la raíz del monorepo:

| Comando           | Descripción                          |
|-------------------|--------------------------------------|
| `npm run dev:web` | Servidor de desarrollo Next.js       |
| `npm run build:web` | Build de producción del frontend   |
| `npm run lint:web`  | ESLint sobre `apps/web`            |

Dentro de `apps/web`:

| Comando        | Descripción                    |
|----------------|--------------------------------|
| `npm run dev`  | `next dev --webpack`           |
| `npm run build`| Build de producción            |
| `npm run start`| Servidor de producción         |
| `npm run test` | Vitest (unit tests)            |

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
clave, esquemas JSON-LD, controladores REST y el catálogo de productos.

---

## API REST

Base URL: `http://localhost:8080/api`

| Método | Ruta                    | Descripción                              |
|--------|-------------------------|------------------------------------------|
| `GET`  | `/api/home`             | Contenido de la página de inicio         |
| `GET`  | `/api/age-pages/{slug}` | Página hub por edad (`3-anos`, `4-anos`, `5-anos`) |
| `GET`  | `/api/comparison-pages/{slug}` | Comparativa editorial; disponible `mejores-bicicletas-sin-pedales-3-anos` |

Ejemplo:

```bash
curl http://localhost:8080/api/age-pages/3-anos
curl http://localhost:8080/api/comparison-pages/mejores-bicicletas-sin-pedales-3-anos
```

En desarrollo, CORS permite peticiones desde `http://localhost:3000`.

---

## SEO y metadatos

El frontend incluye:

- Metadatos dinámicos (`title`, `description`, URL canónica) por página
- JSON-LD: `Organization`, `WebSite`, `BreadcrumbList`, `CollectionPage`, `FAQPage`, `ItemList`
- `sitemap.xml` y `robots.txt` generados automáticamente
- Rutas con barra final (`trailingSlash: true`)
- Generación estática de páginas por edad con `generateStaticParams`

---

## Estado del proyecto

Este repositorio contiene el **MVP funcional** del proyecto:

- Home editorial con navegación por edad y categorías
- Páginas hub completas para 3, 4 y 5 años (selección destacada, FAQ, guías, rankings)
- Catálogo de productos manual en memoria
- Avisos de afiliación Amazon visibles
- Infraestructura de tests en frontend y backend

**Próximos pasos previstos** (según la arquitectura actual):

- Activación de Amazon Creators API con credenciales y ASIN reales
- Persistencia de contenido / CMS
- Páginas de categorías, guías y comparativas referenciadas en la home
- Despliegue en producción con variables de entorno definitivas

---

## Licencia

Proyecto privado. Todos los derechos reservados.
