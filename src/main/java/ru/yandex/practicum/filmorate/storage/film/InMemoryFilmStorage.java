package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;

/*@Component
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
        if(film.getLikes() == null) {
            film = film.toBuilder().likes(new TreeSet<>()).build();
        }
        film.setId(++id);
        films.put(film.getId(), film);
        return film;
    }

    public Film update(Film film){
        if(!films.containsKey(film.getId())){
            throw new NullPointerException("Такой фильм ранее не включался в рейтинг");
        }
        if(film.getLikes() == null) {
            film = film.toBuilder().likes(new TreeSet<>()).build();
        }
        films.put(film.getId(), film);
        return film;
    }

    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    public void removeFilmById(Integer id) {
        if(!films.containsKey(id)){
            throw new NullPointerException("Фильм не найден");
        }
        films.remove(id);
    }
}*/
