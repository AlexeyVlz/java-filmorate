package ru.yandex.practicum.filmorate.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FilmGenres {

    private final Integer id;

    public FilmGenres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmGenres that = (FilmGenres) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FilmGenres{" +
                "id=" + id +
                '}';
    }
}
