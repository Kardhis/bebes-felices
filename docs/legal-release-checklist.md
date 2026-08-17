# Condiciones de publicación de las páginas legales

Este documento es una puerta de publicación interna. No forma parte del contenido
visible para visitantes y debe completarse antes de migrar `bebesfelices.es` desde
WordPress a este repositorio.

## Infraestructura y privacidad

- [ ] Identificar los proveedores definitivos del frontend Next.js, la API Spring
  Boot, DNS/CDN/proxy y correo electrónico.
- [ ] Documentar para cada proveedor su función, entidad contratante, país o región
  de tratamiento, subencargados relevantes y garantías aplicables.
- [ ] Verificar qué logs técnicos se generan, qué campos contienen, quién puede
  acceder a ellos y durante cuánto tiempo se conservan.
- [ ] Confirmar si existe alguna transferencia internacional y, en su caso,
  documentar su mecanismo jurídico y garantías.
- [ ] Actualizar la política de privacidad si el resultado de esta comprobación
  modifica las categorías de destinatarios, transferencias o plazos descritos.

## Cookies y peticiones de terceros

- [ ] Auditar en el entorno real las cookies, `localStorage`, `sessionStorage`,
  scripts y peticiones de terceros antes de interactuar con la página.
- [ ] Repetir la auditoría después de navegar, abrir el menú y pulsar enlaces
  internos.
- [ ] Confirmar que no hay analítica, publicidad, perfilado ni cookies no
  esenciales.
- [ ] Si aparece una tecnología no esencial, detener la publicación hasta
  implementar consentimiento previo, rechazo al mismo nivel y retirada del
  consentimiento, y actualizar la política de cookies.

## Amazon Afiliados

- [ ] Revisar la formulación de identificación frente al acuerdo vigente de
  Amazon Afiliados España.
- [ ] Confirmar que el identificador de afiliado y los ASIN de producción son los
  correctos.
- [ ] Confirmar el estado real de Amazon Creators API. No describirla como activa
  hasta que Amazon habilite el acceso.
- [ ] Verificar que cualquier uso futuro de datos o imágenes de Amazon cumple las
  condiciones de licencia, actualización y caché aplicables.

## Revisión y migración

- [ ] Obtener revisión de los cinco textos por una gestoría o profesional
  jurídico. La implementación técnica no constituye asesoramiento legal.
- [ ] Ejecutar tests, lint y build del commit exacto que vaya a desplegarse.
- [ ] Comprobar en producción las cinco rutas, metadata, canonical, sitemap,
  navegación por teclado y ausencia de la página «En construcción».
- [ ] Migrar el dominio solo después de completar las comprobaciones anteriores.
