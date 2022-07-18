package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.model.FilmMpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ObjectsFilmControllerTest {

    public static Film addCorrectFilm() {
        return Film.builder()
                    .name("Прибытие")
                    .description("Отличный фильм")
                    .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                    .duration(118)
                    .mpa(new FilmMpa(1, "G"))
                    //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                    .build();
    }

    public static Film addCorrectFilm2() {
        return Film.builder()
                .name("Вор")
                .description("Сильный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(1997, 10, 13)))
                .duration(118)
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addCorrectFilm3() {
        return Film.builder()
                .name("Бегущий по лезвию 2049")
                .description("Интересный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2017, 10, 05)))
                .duration(163)
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film CorrectFilmWithGenres() {
        return Film.builder()
                .name("Вор")
                .description("Сильный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(1997, 10, 13)))
                .duration(118)
                .mpa(new FilmMpa(1, "G"))
                .genres(new ArrayList<>(List.of(new FilmGenres(2, "Драма"),
                        new FilmGenres(6, "Боевик"))))
                .build();
    }

    public  static Film addEmptyNameFilm() {
        return Film.builder()
                .name("")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .mpa(new FilmMpa(1, "G"))
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
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addErrorDateRelease() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(1016, 11, 11)))
                .duration(118)
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film addErrorDuration() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(-1)
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
    }

    public static Film uncorrectIdByUpdate() {
        Film film = Film.builder()
                .name("Прибытие")
                .description("Обновление информации")
                .releaseDate(java.sql.Date.valueOf(LocalDate.of(2016, 11, 11)))
                .duration(118)
                .mpa(new FilmMpa(1, "G"))
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
                .mpa(new FilmMpa(1, "G"))
                //.genres(new ArrayList<FilmGenres>(List.of(new FilmGenres(2, "Драма"))))
                .build();
        film.setId(1);
        return film;
    }
}
