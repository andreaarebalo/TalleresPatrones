package com.streaming.userservice.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.streaming.userservice.adapter.LegacyVideoPlayer;
import com.streaming.userservice.adapter.VideoStreamerAdapter;
import com.streaming.userservice.bridge.LivePlayback;
import com.streaming.userservice.bridge.MobileRenderer;
import com.streaming.userservice.bridge.OnDemandPlayback;
import com.streaming.userservice.bridge.StreamingPlayback;
import com.streaming.userservice.bridge.StreamingRenderer;
import com.streaming.userservice.bridge.WebRenderer;
import com.streaming.userservice.composite.CatalogCollection;
import com.streaming.userservice.composite.CatalogVideo;
import com.streaming.userservice.decorator.AdvertisingDecorator;
import com.streaming.userservice.decorator.SubtitlesDecorator;
import com.streaming.userservice.factory.streaming.Mobile720pStreamer;
import com.streaming.userservice.factory.streaming.VideoStreamer;
import com.streaming.userservice.factory.streaming.Web4KStreamer;

@RestController
@RequestMapping("/api/patterns")
public class StructuralPatternsController {

    /** Adapter: adapta una API heredada a la interfaz VideoStreamer ya usada por la app. */
    @GetMapping("/adapter")
    public Map<String, String> adapter() {
        VideoStreamer streamer = new VideoStreamerAdapter(new LegacyVideoPlayer(), "720p HD");
        return Map.of(
                "patron", "Adapter",
                "objetivo", "Adaptar el reproductor heredado a VideoStreamer",
                "resultado", streamer.streamQuality());
    }

    /** Bridge: combina de manera independiente el modo de reproducción y la plataforma. */
    @GetMapping("/bridge")
    public Map<String, String> bridge(
            @RequestParam(defaultValue = "ondemand") String mode,
            @RequestParam(defaultValue = "web") String device,
            @RequestParam(defaultValue = "Contenido de ejemplo") String title) {
        StreamingRenderer renderer = switch (device.toLowerCase()) {
            case "web" -> new WebRenderer();
            case "mobile" -> new MobileRenderer();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "device debe ser 'web' o 'mobile'");
        };

        StreamingPlayback playback = switch (mode.toLowerCase()) {
            case "ondemand", "on-demand" -> new OnDemandPlayback(renderer);
            case "live" -> new LivePlayback(renderer);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "mode debe ser 'ondemand' o 'live'");
        };

        return Map.of(
                "patron", "Bridge",
                "modo", mode,
                "dispositivo", device,
                "resultado", playback.play(title));
    }

    /** Composite: calcula duración y cantidad sobre un árbol de colecciones y videos. */
    @GetMapping("/composite")
    public Map<String, Object> composite() {
        CatalogCollection films = new CatalogCollection("Películas");
        films.add(new CatalogVideo("Inception", 148));
        films.add(new CatalogVideo("Interestelar", 169));

        CatalogCollection catalog = new CatalogCollection("Recomendados");
        catalog.add(films);
        catalog.add(new CatalogVideo("Our Planet (episodio)", 50));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("patron", "Composite");
        response.put("estructura", catalog.describe());
        response.put("cantidadVideos", catalog.videoCount());
        response.put("duracionTotalMinutos", catalog.durationMinutes());
        return response;
    }

    /** Decorator: añade subtítulos y/o anuncios sin cambiar el streamer original. */
    @GetMapping("/decorator")
    public Map<String, String> decorator(
            @RequestParam(defaultValue = "web") String device,
            @RequestParam(defaultValue = "es") String subtitles,
            @RequestParam(defaultValue = "false") boolean ads) {
        VideoStreamer streamer = switch (device.toLowerCase()) {
            case "web" -> new Web4KStreamer();
            case "mobile" -> new Mobile720pStreamer();
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "device debe ser 'web' o 'mobile'");
        };

        if (subtitles != null && !subtitles.isBlank() && !"none".equalsIgnoreCase(subtitles)) {
            streamer = new SubtitlesDecorator(streamer, subtitles);
        }
        if (ads) {
            streamer = new AdvertisingDecorator(streamer);
        }

        return Map.of(
                "patron", "Decorator",
                "dispositivo", device,
                "resultado", streamer.streamQuality());
    }
}
