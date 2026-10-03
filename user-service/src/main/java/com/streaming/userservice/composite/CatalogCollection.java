package com.streaming.userservice.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** Composite que trata videos y otras colecciones de forma uniforme. */
public class CatalogCollection implements CatalogComponent {

    private final String name;
    private final List<CatalogComponent> children = new ArrayList<>();

    public CatalogCollection(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la colección no puede estar vacío");
        }
        this.name = name;
    }

    public CatalogCollection add(CatalogComponent component) {
        Objects.requireNonNull(component, "El componente no puede ser null");
        if (component == this) {
            throw new IllegalArgumentException("Una colección no puede agregarse a sí misma");
        }
        children.add(component);
        return this;
    }

    public boolean remove(CatalogComponent component) {
        return children.remove(component);
    }

    public List<CatalogComponent> children() {
        return List.copyOf(children);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int durationMinutes() {
        return children.stream().mapToInt(CatalogComponent::durationMinutes).sum();
    }

    @Override
    public int videoCount() {
        return children.stream().mapToInt(CatalogComponent::videoCount).sum();
    }

    @Override
    public String describe() {
        String contents = children.stream()
                .map(CatalogComponent::describe)
                .collect(Collectors.joining("; "));
        return "Colección: " + name + " [" + contents + "]";
    }
}
