# Guía: cuatro patrones estructurales para la plataforma de streaming

Módulo: `user-service` (Java 17, Spring Boot y Maven). Conservé los patrones existentes **Builder, Prototype, Factory Method y Abstract Factory**. Los cuatro de esta guía son adicionales.

## Qué hace cada patrón y dónde está

| Patrón | Archivos nuevos | Uso en la plataforma |
|---|---|---|
| **Adapter** | `src/main/java/com/streaming/userservice/adapter/LegacyVideoPlayer.java` y `VideoStreamerAdapter.java` | Como un traductor: adapta el reproductor heredado a la interfaz `VideoStreamer` que ya usa la app. |
| **Bridge** | `src/main/java/com/streaming/userservice/bridge/` (`StreamingPlayback`, `OnDemandPlayback`, `LivePlayback`, `StreamingRenderer`, `WebRenderer`, `MobileRenderer`) | Separa el modo de reproducción (en vivo o bajo demanda) de la plataforma (web o móvil). Cada eje puede variar de forma independiente. |
| **Composite** | `src/main/java/com/streaming/userservice/composite/` (`CatalogComponent`, `CatalogVideo`, `CatalogCollection`) | Modela el catálogo como un árbol de videos y colecciones anidadas, y suma duración/cantidad uniformemente. |
| **Decorator** | `src/main/java/com/streaming/userservice/decorator/` (`VideoStreamerDecorator`, `SubtitlesDecorator`, `AdvertisingDecorator`) | Añade subtítulos y/o publicidad al streamer existente sin modificar las clases originales. |

También agregué:

- `src/main/java/com/streaming/userservice/controller/StructuralPatternsController.java`: endpoints de demostración REST.
- `src/test/java/com/streaming/userservice/StructuralPatternsTest.java`: seis pruebas unitarias de los patrones y sus respuestas.

## Probar los endpoints

La aplicación conserva el puerto `8081`. Al iniciar el servicio con su PostgreSQL configurado, prueba estas direcciones en navegador, Postman o Insomnia:

```text
GET http://localhost:8081/api/patterns/adapter
GET http://localhost:8081/api/patterns/bridge?mode=live&device=mobile&title=Concierto
GET http://localhost:8081/api/patterns/composite
GET http://localhost:8081/api/patterns/decorator?device=mobile&subtitles=es&ads=true
```

Parámetros válidos:

- Bridge: `mode=ondemand` o `mode=live`; `device=web` o `device=mobile`.
- Decorator: `device=web` o `device=mobile`; `subtitles=es` (o `subtitles=none` para omitirlo); `ads=true` o `ads=false`.

Ejemplos sin PostgreSQL:

```bash
curl "http://localhost:8081/api/patterns/adapter"
curl "http://localhost:8081/api/patterns/bridge?mode=live&device=mobile&title=Concierto"
curl "http://localhost:8081/api/patterns/composite"
curl "http://localhost:8081/api/patterns/decorator?device=mobile&subtitles=es&ads=true"
```

## Ejecutar las pruebas automatizadas

Abre una terminal en la carpeta que contiene `pom.xml`:

```bash
./mvnw -Dtest=StructuralPatternsTest test
```

En Windows PowerShell:

```powershell
.\mvnw.cmd -Dtest=StructuralPatternsTest test
```

Este comando ejecuta las seis pruebas nuevas sin iniciar la base de datos. Para ejecutar **toda** la suite se usa `./mvnw test`; la prueba preexistente `UserServiceApplicationTests` arranca todo Spring Boot y necesita PostgreSQL accesible según `src/main/resources/application.properties` (localhost, puerto 5433). Si esa base no está iniciada, dicha prueba falla por conexión aunque las pruebas de patrones pasen.
