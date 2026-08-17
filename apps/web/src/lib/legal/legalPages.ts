import { SITE_URL } from "@/lib/seo/metadata";

export const LEGAL_UPDATED_AT = "2026-08-17";

export const LEGAL_PAGE_SLUGS = [
  "aviso-legal",
  "politica-privacidad",
  "politica-cookies",
  "condiciones-uso",
  "informacion-afiliacion",
] as const;

export type LegalPageSlug = (typeof LEGAL_PAGE_SLUGS)[number];

export type LegalTextPart = {
  text: string;
  href?: string;
  external?: boolean;
};

export type LegalContentBlock =
  | {
      type: "paragraph";
      parts: LegalTextPart[];
    }
  | {
      type: "list";
      items: LegalTextPart[][];
    };

export type LegalSection = {
  id: string;
  title: string;
  blocks: LegalContentBlock[];
};

export type LegalPage = {
  slug: LegalPageSlug;
  label: string;
  title: string;
  metaDescription: string;
  canonicalUrl: string;
  kicker: string;
  introduction: string;
  updatedAt: string;
  sections: LegalSection[];
};

const canonicalBase = SITE_URL.replace(/\/$/, "");

const text = (value: string): LegalTextPart[] => [{ text: value }];

const paragraph = (...parts: LegalTextPart[]): LegalContentBlock => ({
  type: "paragraph",
  parts,
});

const list = (...items: LegalTextPart[][]): LegalContentBlock => ({
  type: "list",
  items,
});

const legalPages: Record<LegalPageSlug, LegalPage> = {
  "aviso-legal": {
    slug: "aviso-legal",
    label: "Aviso legal",
    title: "Aviso legal | BebesFelices",
    metaDescription:
      "Identificación del titular, condiciones de acceso y responsabilidades de BebesFelices.",
    canonicalUrl: `${canonicalBase}/aviso-legal/`,
    kicker: "Información legal",
    introduction:
      "Este aviso identifica al responsable de BebesFelices y establece las reglas básicas de acceso y uso de la web.",
    updatedAt: LEGAL_UPDATED_AT,
    sections: [
      {
        id: "titular",
        title: "Identificación del titular",
        blocks: [
          list(
            text("Titular: David Castel Castells."),
            text("NIF: 44204902Y."),
            text(
              "Domicilio: Carrer Mossèn Amadeu Oller, 36, 08014 Barcelona, España.",
            ),
            [
              { text: "Correo electrónico: " },
              {
                text: "davidcastelcastells@gmail.com",
                href: "mailto:davidcastelcastells@gmail.com",
              },
              { text: "." },
            ],
          ),
        ],
      },
      {
        id: "finalidad",
        title: "Finalidad de la web",
        blocks: [
          paragraph(
            ...text(
              "BebesFelices es una web editorial dirigida a personas adultas. Publica guías, comparativas y análisis sobre juguetes, regalos y productos para niños de 3 a 5 años.",
            ),
          ),
          paragraph(
            ...text(
              "La web no vende productos directamente. Algunas páginas contienen enlaces que llevan a Amazon España, donde la compra, el pago, la entrega y cualquier devolución se tramitan bajo las condiciones de Amazon.",
            ),
          ),
        ],
      },
      {
        id: "acceso-uso",
        title: "Acceso y uso",
        blocks: [
          paragraph(
            ...text(
              "Puedes navegar por el sitio de forma gratuita y sin registrarte. Al utilizarlo, te comprometes a hacerlo de manera lícita, respetando estos textos legales, los derechos de terceros y el funcionamiento normal de la web.",
            ),
          ),
          paragraph(
            ...text(
              "No está permitido intentar acceder sin autorización a sistemas, alterar contenidos, introducir código malicioso, realizar extracciones automatizadas abusivas ni utilizar la web para actividades ilícitas.",
            ),
          ),
        ],
      },
      {
        id: "propiedad-intelectual",
        title: "Propiedad intelectual e industrial",
        blocks: [
          paragraph(
            ...text(
              "Salvo que se indique otra cosa, los textos, la selección y organización editorial, el diseño y los elementos propios de BebesFelices pertenecen al titular o se utilizan con autorización. No puedes reproducirlos, transformarlos o explotarlos comercialmente sin permiso o sin una base legal que lo permita.",
            ),
          ),
          paragraph(
            ...text(
              "Las marcas, nombres comerciales, imágenes y materiales de terceros pertenecen a sus respectivos titulares. Su aparición no implica patrocinio ni respaldo de BebesFelices.",
            ),
          ),
        ],
      },
      {
        id: "contenido-editorial",
        title: "Contenido editorial y actualización",
        blocks: [
          paragraph(
            ...text(
              "El contenido se prepara con finalidad informativa y se revisa periódicamente, pero los catálogos, especificaciones, recomendaciones de edad, disponibilidad y condiciones comerciales pueden cambiar. Comprueba siempre la información vigente del fabricante y del vendedor antes de decidir.",
            ),
          ),
          paragraph(
            { text: "Puedes consultar cómo se elaboran las recomendaciones en " },
            {
              text: "Cómo analizamos",
              href: "/como-analizamos/",
            },
            { text: "." },
          ),
        ],
      },
      {
        id: "seguridad-infantil",
        title: "Seguridad infantil",
        blocks: [
          paragraph(
            ...text(
              "Las referencias a edades, habilidades y seguridad son orientación editorial para adultos. No sustituyen las instrucciones del fabricante, el marcado del producto, la supervisión adulta ni el asesoramiento médico, educativo o profesional cuando resulte necesario.",
            ),
          ),
          paragraph(
            ...text(
              "Antes de usar un producto infantil, revisa su estado, advertencias, piezas pequeñas, límites de edad y condiciones de uso.",
            ),
          ),
        ],
      },
      {
        id: "terceros-afiliacion",
        title: "Enlaces externos y afiliación",
        blocks: [
          paragraph(
            ...text(
              "Los enlaces externos se ofrecen para ampliar información o facilitar el acceso a productos. BebesFelices no controla la disponibilidad, seguridad, privacidad ni condiciones de sitios de terceros.",
            ),
          ),
          paragraph(
            { text: "Algunos enlaces son de afiliación. Consulta la " },
            {
              text: "información sobre afiliación",
              href: "/informacion-afiliacion/",
            },
            {
              text: " para conocer cómo funciona esta relación y cómo se mantiene la independencia editorial.",
            },
          ),
        ],
      },
      {
        id: "responsabilidad",
        title: "Responsabilidad y disponibilidad",
        blocks: [
          paragraph(
            ...text(
              "El titular procura que la web funcione correctamente y que su contenido sea útil, pero no puede garantizar disponibilidad ininterrumpida ni ausencia absoluta de errores. Podrá corregir, actualizar o retirar contenidos cuando sea necesario.",
            ),
          ),
          paragraph(
            ...text(
              "Nada de este aviso limita los derechos que te reconozca la normativa aplicable ni excluye responsabilidades que legalmente no puedan excluirse.",
            ),
          ),
        ],
      },
      {
        id: "legislacion-contacto",
        title: "Legislación, cambios y contacto",
        blocks: [
          paragraph(
            ...text(
              "Este sitio se rige por la legislación española, sin perjuicio de las normas imperativas de protección de consumidores que resulten aplicables. Cualquier controversia se someterá a los juzgados y tribunales que determine la normativa vigente.",
            ),
          ),
          paragraph(
            { text: "Para comunicar una incidencia legal, escribe a " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            {
              text: ". El aviso podrá actualizarse para reflejar cambios normativos o del servicio.",
            },
          ),
        ],
      },
    ],
  },
  "politica-privacidad": {
    slug: "politica-privacidad",
    label: "Política de privacidad",
    title: "Política de privacidad | BebesFelices",
    metaDescription:
      "Información sobre el tratamiento de datos personales y los derechos de privacidad en BebesFelices.",
    canonicalUrl: `${canonicalBase}/politica-privacidad/`,
    kicker: "Privacidad",
    introduction:
      "BebesFelices está diseñada para poder consultarse sin crear una cuenta ni entregar datos personales mediante formularios.",
    updatedAt: LEGAL_UPDATED_AT,
    sections: [
      {
        id: "responsable",
        title: "Responsable del tratamiento",
        blocks: [
          list(
            text("Responsable: David Castel Castells."),
            text("NIF: 44204902Y."),
            text(
              "Domicilio: Carrer Mossèn Amadeu Oller, 36, 08014 Barcelona, España.",
            ),
            [
              { text: "Correo de privacidad: " },
              {
                text: "davidcastelcastells@gmail.com",
                href: "mailto:davidcastelcastells@gmail.com",
              },
              { text: "." },
            ],
          ),
          paragraph(
            ...text(
              "No se ha designado delegado de protección de datos porque, atendiendo a la actividad prevista, no resulta obligatorio.",
            ),
          ),
        ],
      },
      {
        id: "datos-no-recopilados",
        title: "Datos que la web no solicita",
        blocks: [
          paragraph(
            ...text(
              "En su lanzamiento, BebesFelices no ofrece cuentas, formularios, comentarios, newsletter ni suscripciones. Tampoco utiliza analítica, publicidad comportamental, perfiles o decisiones automatizadas.",
            ),
          ),
          paragraph(
            ...text(
              "No se solicita el nombre, la dirección, el teléfono, información de compra ni datos de menores para navegar por el contenido.",
            ),
          ),
        ],
      },
      {
        id: "contacto-correo",
        title: "Comunicaciones por correo electrónico",
        blocks: [
          paragraph(
            ...text(
              "Si decides escribir al correo publicado, se tratarán tu dirección, el contenido del mensaje y los datos que incluyas voluntariamente para responder a tu consulta y conservar la trazabilidad necesaria de la comunicación.",
            ),
          ),
          paragraph(
            ...text(
              "La base jurídica será el interés legítimo en atender y proteger las comunicaciones y, según el contenido de la solicitud, la aplicación de medidas precontractuales o el cumplimiento de obligaciones legales.",
            ),
          ),
          paragraph(
            ...text(
              "Los mensajes se conservarán durante el tiempo necesario para responder y, después, durante los plazos exigibles para atender posibles responsabilidades. No envíes datos de salud, documentos de identidad ni información de menores si no es imprescindible.",
            ),
          ),
        ],
      },
      {
        id: "datos-tecnicos",
        title: "Datos técnicos y seguridad",
        blocks: [
          paragraph(
            ...text(
              "Los sistemas que alojan la web y la API pueden generar registros técnicos imprescindibles, como dirección IP, fecha, recurso solicitado, agente de usuario y errores. Su finalidad es entregar el sitio, mantener su seguridad, diagnosticar fallos y prevenir abusos.",
            ),
          ),
          paragraph(
            ...text(
              "Estos registros se conservan únicamente durante el periodo técnicamente necesario y el que resulte exigible para gestionar incidentes o responsabilidades.",
            ),
          ),
        ],
      },
      {
        id: "proveedores-destinatarios",
        title: "Proveedores y destinatarios",
        blocks: [
          paragraph(
            ...text(
              "Podrán acceder a datos los proveedores estrictamente necesarios para alojar y proteger la web, prestar el servicio de correo y mantener la infraestructura, actuando con las garantías contractuales que correspondan. No se venden datos personales ni se ceden con fines publicitarios.",
            ),
          ),
          paragraph(
            ...text(
              "Solo se utilizan proveedores que ofrecen garantías adecuadas para el tratamiento. Si algún servicio implicara una transferencia internacional de datos, solo se utilizará con una base y garantías válidas conforme al RGPD.",
            ),
          ),
        ],
      },
      {
        id: "amazon",
        title: "Navegación a Amazon",
        blocks: [
          paragraph(
            ...text(
              "BebesFelices no recibe información sobre tu cuenta ni sobre el detalle de tus compras en Amazon. Cuando pulsas un enlace externo, abandonas esta web y Amazon trata los datos de navegación y compra bajo sus propias condiciones y políticas.",
            ),
          ),
          paragraph(
            {
              text: "Puedes consultar la información de privacidad de Amazon España en su ",
            },
            {
              text: "aviso de privacidad",
              href: "https://www.amazon.es/gp/help/customer/display.html?nodeId=GX7NJQ4ZB8MHFRNJ",
              external: true,
            },
            { text: "." },
          ),
        ],
      },
      {
        id: "derechos",
        title: "Tus derechos",
        blocks: [
          paragraph(
            ...text(
              "Puedes solicitar acceso, rectificación, supresión, oposición, limitación y portabilidad cuando correspondan. También puedes retirar un consentimiento sin que ello afecte a la licitud del tratamiento anterior.",
            ),
          ),
          paragraph(
            { text: "Para ejercerlos, escribe a " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            {
              text: " indicando tu petición. Solo se solicitará información adicional para verificar tu identidad cuando sea necesario.",
            },
          ),
          paragraph(
            {
              text: "Si consideras que tus derechos no han sido atendidos, puedes reclamar ante la ",
            },
            {
              text: "Agencia Española de Protección de Datos",
              href: "https://www.aepd.es/",
              external: true,
            },
            { text: "." },
          ),
        ],
      },
      {
        id: "menores-seguridad",
        title: "Menores y seguridad",
        blocks: [
          paragraph(
            ...text(
              "El contenido trata sobre productos infantiles, pero la web se dirige a personas adultas y no pretende recabar datos de menores. Si se detecta que un menor ha enviado información personal, se adoptarán medidas para eliminarla.",
            ),
          ),
          paragraph(
            ...text(
              "Se aplicarán medidas razonables para proteger la información, aunque ningún sistema conectado a Internet puede garantizar seguridad absoluta.",
            ),
          ),
        ],
      },
      {
        id: "actualizaciones",
        title: "Cookies y actualizaciones",
        blocks: [
          paragraph(
            { text: "Consulta la " },
            {
              text: "política de cookies",
              href: "/politica-cookies/",
            },
            {
              text: " para conocer el estado actual de cookies y tecnologías similares.",
            },
          ),
          paragraph(
            ...text(
              "Esta política se actualizará cuando cambien los tratamientos, proveedores o requisitos aplicables. La fecha de la versión vigente aparece al comienzo de la página.",
            ),
          ),
        ],
      },
    ],
  },
  "politica-cookies": {
    slug: "politica-cookies",
    label: "Política de cookies",
    title: "Política de cookies | BebesFelices",
    metaDescription:
      "Información sobre cookies y tecnologías similares utilizadas por BebesFelices.",
    canonicalUrl: `${canonicalBase}/politica-cookies/`,
    kicker: "Cookies",
    introduction:
      "La aplicación inicial de BebesFelices no utiliza cookies de analítica, publicidad o personalización ni guarda preferencias en tu navegador.",
    updatedAt: LEGAL_UPDATED_AT,
    sections: [
      {
        id: "que-son",
        title: "Qué son las cookies",
        blocks: [
          paragraph(
            ...text(
              "Las cookies son pequeños archivos que una web puede guardar en tu dispositivo. Tecnologías como el almacenamiento local, identificadores o píxeles pueden cumplir funciones parecidas y también deben evaluarse según su finalidad.",
            ),
          ),
        ],
      },
      {
        id: "estado-actual",
        title: "Uso actual en BebesFelices",
        blocks: [
          paragraph(
            ...text(
              "El código previsto para el lanzamiento no instala cookies propias, no usa almacenamiento local y no integra herramientas de analítica, publicidad, redes sociales, remarketing o elaboración de perfiles.",
            ),
          ),
          list(
            text("Cookies de analítica: no utilizadas."),
            text("Cookies publicitarias o de seguimiento: no utilizadas."),
            text("Cookies de personalización: no utilizadas."),
            text("Almacenamiento local o de sesión: no utilizado."),
          ),
          paragraph(
            ...text(
              "Por este motivo no se muestra un banner de consentimiento. La configuración del entorno de producción se audita para comprobar que el alojamiento, el proxy o la CDN no añaden tecnologías distintas de las descritas.",
            ),
          ),
        ],
      },
      {
        id: "recursos-tecnicos",
        title: "Recursos técnicos necesarios",
        blocks: [
          paragraph(
            ...text(
              "La web utiliza recursos técnicos para entregar páginas, estilos, fuentes e imágenes. Las fuentes se sirven desde la propia aplicación y la imagen externa de portada se procesa mediante el sistema de imágenes de Next.js.",
            ),
          ),
          paragraph(
            ...text(
              "Si la infraestructura necesitara una cookie estrictamente técnica para seguridad, equilibrio de carga o prestación de una función solicitada, se incorporará al inventario de esta política. Una cookie técnica no podrá reutilizarse para analítica o publicidad.",
            ),
          ),
        ],
      },
      {
        id: "amazon-terceros",
        title: "Enlaces a Amazon y otros terceros",
        blocks: [
          paragraph(
            ...text(
              "Los enlaces de afiliación no instalan por sí mismos cookies de Amazon mientras permaneces en BebesFelices. Si pulsas uno, abandonas esta web y Amazon puede utilizar cookies o tecnologías propias conforme a sus políticas.",
            ),
          ),
          paragraph(
            {
              text: "Puedes revisar la configuración y la información de cookies de Amazon en su ",
            },
            {
              text: "aviso de cookies",
              href: "https://www.amazon.es/gp/help/customer/display.html?nodeId=201890250",
              external: true,
            },
            { text: "." },
          ),
        ],
      },
      {
        id: "consentimiento",
        title: "Qué ocurrirá si se incorporan nuevas tecnologías",
        blocks: [
          paragraph(
            ...text(
              "Si en el futuro se incorporan cookies o tecnologías no esenciales, no se activarán antes de obtener una elección válida. La web ofrecerá información clara y opciones de aceptar o rechazar con la misma visibilidad, además de un medio para cambiar la decisión.",
            ),
          ),
          paragraph(
            ...text(
              "La política y el inventario se actualizarán antes de activar cualquier servicio nuevo.",
            ),
          ),
        ],
      },
      {
        id: "control-navegador",
        title: "Control desde tu navegador",
        blocks: [
          paragraph(
            ...text(
              "Los navegadores permiten consultar, bloquear y borrar cookies. Si bloqueas recursos estrictamente necesarios, algunas funciones podrían dejar de operar correctamente. Consulta la ayuda de tu navegador para conocer los pasos concretos.",
            ),
          ),
        ],
      },
      {
        id: "contacto-actualizacion",
        title: "Contacto y actualización",
        blocks: [
          paragraph(
            { text: "Si observas una cookie no descrita, comunícalo a " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            {
              text: " para que pueda investigarse. La fecha de la última revisión aparece al comienzo de esta página.",
            },
          ),
        ],
      },
    ],
  },
  "condiciones-uso": {
    slug: "condiciones-uso",
    label: "Condiciones de uso",
    title: "Condiciones de uso | BebesFelices",
    metaDescription:
      "Condiciones aplicables al acceso y uso del contenido editorial de BebesFelices.",
    canonicalUrl: `${canonicalBase}/condiciones-uso/`,
    kicker: "Uso del sitio",
    introduction:
      "Estas condiciones explican cómo puedes utilizar el contenido editorial de BebesFelices y cuáles son sus límites.",
    updatedAt: LEGAL_UPDATED_AT,
    sections: [
      {
        id: "objeto-aceptacion",
        title: "Objeto y aceptación",
        blocks: [
          paragraph(
            ...text(
              "BebesFelices ofrece información editorial gratuita sobre productos y actividades para niños de 3 a 5 años. Al acceder a la web aceptas utilizarla conforme a estas condiciones y a la legislación aplicable.",
            ),
          ),
          paragraph(
            ...text(
              "Si no estás de acuerdo, puedes dejar de utilizar el sitio. Las condiciones no sustituyen las reglas específicas de los servicios externos a los que accedas.",
            ),
          ),
        ],
      },
      {
        id: "publico-naturaleza",
        title: "Público y naturaleza del contenido",
        blocks: [
          paragraph(
            ...text(
              "La web está dirigida a personas adultas que buscan orientación para elegir productos infantiles. No está diseñada como servicio para menores ni para que estos envíen información.",
            ),
          ),
          paragraph(
            ...text(
              "Las guías y comparativas son informativas. No constituyen asesoramiento médico, pedagógico, jurídico o de seguridad, ni garantizan que un producto sea adecuado para todas las personas o situaciones.",
            ),
          ),
        ],
      },
      {
        id: "sin-venta",
        title: "BebesFelices no vende productos",
        blocks: [
          paragraph(
            ...text(
              "La web no gestiona pedidos, cobros, envíos, garantías ni devoluciones. Si pulsas un enlace y compras en Amazon, la relación contractual se establece entre tú y el vendedor correspondiente bajo las condiciones mostradas allí.",
            ),
          ),
          paragraph(
            ...text(
              "Los precios y la disponibilidad pueden cambiar. BebesFelices no publica esos datos como hechos permanentes ni controla las condiciones comerciales de Amazon.",
            ),
          ),
        ],
      },
      {
        id: "seguridad-supervision",
        title: "Elección, seguridad y supervisión",
        blocks: [
          paragraph(
            ...text(
              "Comprueba siempre las instrucciones, advertencias, edad recomendada, marcado, montaje y mantenimiento indicados por el fabricante. La edad es orientativa y no sustituye la valoración adulta de las capacidades y circunstancias del niño.",
            ),
          ),
          paragraph(
            ...text(
              "La supervisión adulta y el uso correcto son esenciales. Si existen dudas médicas, educativas o de seguridad, consulta a un profesional cualificado.",
            ),
          ),
        ],
      },
      {
        id: "criterio-editorial",
        title: "Criterio editorial y afiliación",
        blocks: [
          paragraph(
            ...text(
              "Las selecciones se basan en criterios editoriales y pueden cambiar al actualizarse las fuentes o el catálogo. No se garantiza que una recomendación siga disponible ni que sea la mejor opción para cada caso.",
            ),
          ),
          paragraph(
            { text: "Consulta " },
            {
              text: "Cómo analizamos",
              href: "/como-analizamos/",
            },
            { text: " y la " },
            {
              text: "información sobre afiliación",
              href: "/informacion-afiliacion/",
            },
            {
              text: " para conocer los criterios y la relación con Amazon.",
            },
          ),
        ],
      },
      {
        id: "uso-permitido",
        title: "Uso permitido",
        blocks: [
          paragraph(
            ...text(
              "Puedes consultar y compartir enlaces a las páginas para fines personales y lícitos. No puedes suplantar al titular, alterar la web, interferir con su seguridad, reutilizar masivamente el contenido ni explotar materiales protegidos sin autorización.",
            ),
          ),
        ],
      },
      {
        id: "propiedad-terceros",
        title: "Propiedad intelectual y servicios de terceros",
        blocks: [
          paragraph(
            ...text(
              "El contenido propio está protegido por la normativa de propiedad intelectual. Las marcas y materiales de terceros siguen perteneciendo a sus titulares.",
            ),
          ),
          paragraph(
            ...text(
              "BebesFelices no controla las políticas, disponibilidad o seguridad de Amazon ni de otros sitios externos. Revisa sus condiciones antes de facilitar datos o realizar una compra.",
            ),
          ),
        ],
      },
      {
        id: "disponibilidad-responsabilidad",
        title: "Disponibilidad y responsabilidad",
        blocks: [
          paragraph(
            ...text(
              "El servicio puede interrumpirse por mantenimiento, seguridad, fallos técnicos o causas ajenas al titular. Los contenidos podrán corregirse, actualizarse o retirarse.",
            ),
          ),
          paragraph(
            ...text(
              "Estas condiciones no excluyen responsabilidades que no puedan limitarse legalmente ni afectan a tus derechos imperativos como consumidor.",
            ),
          ),
        ],
      },
      {
        id: "cambios-legislacion",
        title: "Cambios, legislación y contacto",
        blocks: [
          paragraph(
            ...text(
              "Las condiciones podrán modificarse para reflejar cambios del servicio o de la normativa. Se aplica la legislación española y serán competentes los juzgados y tribunales que determine la normativa vigente.",
            ),
          ),
          paragraph(
            { text: "Para consultas sobre estas condiciones, escribe a " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            { text: "." },
          ),
        ],
      },
    ],
  },
  "informacion-afiliacion": {
    slug: "informacion-afiliacion",
    label: "Información sobre afiliación",
    title: "Información sobre afiliación | BebesFelices",
    metaDescription:
      "Cómo funcionan los enlaces de afiliación de Amazon España y la independencia editorial de BebesFelices.",
    canonicalUrl: `${canonicalBase}/informacion-afiliacion/`,
    kicker: "Transparencia comercial",
    introduction:
      "BebesFelices utiliza enlaces de afiliación de Amazon España para financiar parte de su actividad editorial sin modificar el precio de compra.",
    updatedAt: LEGAL_UPDATED_AT,
    sections: [
      {
        id: "identificacion",
        title: "Identificación como afiliado",
        blocks: [
          paragraph(
            ...text(
              "En calidad de Afiliado de Amazon, obtengo ingresos por las compras adscritas que cumplen los requisitos aplicables.",
            ),
          ),
          paragraph(
            ...text(
              "El titular de BebesFelices participa en el Programa de Afiliados de Amazon España. Esta participación no significa que Amazon patrocine, respalde o controle el contenido de la web.",
            ),
          ),
        ],
      },
      {
        id: "funcionamiento",
        title: "Cómo funcionan los enlaces",
        blocks: [
          paragraph(
            ...text(
              "Algunos botones y enlaces llevan a productos o páginas de Amazon e incluyen un identificador de afiliado. Si realizas una compra que cumple los requisitos del programa, el titular puede recibir una comisión.",
            ),
          ),
          paragraph(
            ...text(
              "Usar un enlace de afiliado no añade un coste específico al precio que pagas. La atribución, el cálculo y el pago de la comisión dependen de las reglas de Amazon.",
            ),
          ),
        ],
      },
      {
        id: "independencia",
        title: "Independencia editorial",
        blocks: [
          paragraph(
            ...text(
              "La existencia o cuantía de una posible comisión no determina por sí sola el orden ni la valoración editorial. El contenido se redacta con criterios de edad, seguridad, utilidad, durabilidad y relación entre utilidad y coste.",
            ),
          ),
          paragraph(
            { text: "La metodología completa está disponible en " },
            {
              text: "Cómo analizamos",
              href: "/como-analizamos/",
            },
            {
              text: ". Cuando no hay un enlace validado, el análisis puede publicarse sin botón de compra.",
            },
          ),
        ],
      },
      {
        id: "precios-compra",
        title: "Precios, disponibilidad y compra",
        blocks: [
          paragraph(
            ...text(
              "BebesFelices no vende los productos ni controla su precio, disponibilidad, entrega, garantía o devolución. Comprueba en Amazon la información vigente antes de comprar.",
            ),
          ),
          paragraph(
            ...text(
              "La compra se realiza en Amazon y queda sujeta a las condiciones del vendedor y de la plataforma. BebesFelices no recibe los datos de tu cuenta ni el detalle individual de tu pedido.",
            ),
          ),
        ],
      },
      {
        id: "enlaces-transparencia",
        title: "Transparencia de los enlaces",
        blocks: [
          paragraph(
            ...text(
              "Las páginas con recomendaciones muestran un aviso de afiliación y los enlaces comerciales se marcan técnicamente como patrocinados. Los enlaces se abren en Amazon para que puedas identificar claramente el cambio de sitio.",
            ),
          ),
          paragraph(
            ...text(
              "No debes interpretar la presencia de una marca, un producto o un enlace como una recomendación de Amazon ni como una colaboración patrocinada distinta del programa de afiliación descrito aquí.",
            ),
          ),
        ],
      },
      {
        id: "creators-api",
        title: "Amazon Creators API",
        blocks: [
          paragraph(
            ...text(
              "La integración técnica está preparada para utilizar Amazon Creators API cuando la cuenta cumpla los requisitos y el acceso sea habilitado por Amazon. En este momento no se afirma que la API esté activa.",
            ),
          ),
          paragraph(
            ...text(
              "Mientras no esté disponible, el sitio puede construir enlaces de Amazon España a partir de identificadores de producto y del identificador de afiliado. Cualquier uso futuro de datos o imágenes facilitados por Amazon se ajustará a las condiciones vigentes del programa.",
            ),
          ),
        ],
      },
      {
        id: "privacidad-contacto",
        title: "Privacidad, cambios y contacto",
        blocks: [
          paragraph(
            { text: "Cuando visitas Amazon se aplican sus políticas. Consulta también la " },
            {
              text: "política de privacidad de BebesFelices",
              href: "/politica-privacidad/",
            },
            { text: "." },
          ),
          paragraph(
            { text: "Para preguntas sobre afiliación, escribe a " },
            {
              text: "davidcastelcastells@gmail.com",
              href: "mailto:davidcastelcastells@gmail.com",
            },
            {
              text: ". Esta información se revisará cuando cambien el programa o las integraciones utilizadas.",
            },
          ),
        ],
      },
    ],
  },
};

export const LEGAL_PAGES = LEGAL_PAGE_SLUGS.map((slug) => legalPages[slug]);

export const LEGAL_LINKS = LEGAL_PAGES.map(({ label, slug }) => ({
  label,
  href: `/${slug}/`,
}));

export function getLegalPage(slug: LegalPageSlug): LegalPage {
  return legalPages[slug];
}
