package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.model.FilmMpa;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class ObjectsFilmControllerTest {

    public static Film addCorrectFilm() {
        return Film.builder()
                    .name("Прибытие")
                    .description("Отличный фильм")
                    .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                    .duration(118)
                    .ratingMpa(new FilmMpa(1, "G"))
                    //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                    .build();
    }

    public static Film addCorrectFilm2() {
        return Film.builder()
                .name("Вор")
                .description("Сильный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(1997, 10, 13)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public  static Film addEmptyNameFilm() {
        return Film.builder()
                .name("")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addTooLongDescription() {
        String description = "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv";
        return Film.builder()
                .name("Прибытие")
                .description(description)
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addErrorDateRelease() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(1016, 11, 11)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addErrorDuration() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(-1)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film uncorrectIdByUpdate() {
        Film film = Film.builder()
                .name("Прибытие")
                .description("Обновление информации")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
        film.setId(3);
        return film;
    }

    public static Film correctIdByUpdate() {
        Film film = Film.builder()
                .name("Прибытие")
                .description("Обновление информации")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .ratingMpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
        film.setId(1);
        return film;
    }
}
