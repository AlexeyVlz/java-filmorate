package ru.yandex.practicum.filmorate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FilmGenres {

    private final Integer id;
    private final String name;

    public FilmGenres(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmGenres that = (FilmGenres) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "FilmGenres{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
