package ru.yandex.practicum.filmorate.model;

import java.util.Map;
import java.util.Objects;

public class FilmMpa {

    private final Integer id;

    public FilmMpa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmMpa filmMpa = (FilmMpa) o;
        return Objects.equals(id, filmMpa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FilmMpa{" +
                "id=" + id +
                '}';
    }
}
