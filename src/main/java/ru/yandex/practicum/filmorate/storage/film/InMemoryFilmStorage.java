package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryFilmStorage implements FilmStorage{

    private final Map<Integer, Film> films;
    private int id;

    public InMemoryFilmStorage() {
        this.films = new HashMap<>();
        this.id = 0;
    }

    public Map<Integer, Film> getFilms() {
        return films;
    }

    public int getId() {
        return id;
    }

    public Film add(Film film){
        film.setId(++id);
        films.put(film.getId(), film);
        return film;
    }

    public Film update(Film film){
        if(!films.containsKey(film.getId())){
            throw new NullPointerException("Такой фильм ранее не включался в рейтинг");
        }
        films.put(film.getId(), film);
        return film;
    }

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }
}
