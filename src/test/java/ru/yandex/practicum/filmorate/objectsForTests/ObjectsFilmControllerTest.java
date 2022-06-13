package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class ObjectsFilmControllerTest {

    public static Film addCorrectFilm() {
        return new Film("Прибытие", "Отличный фильм", LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
    }

    public static Film addCorrectFilm2() {
        return new Film( "Вор", "Сильный фильм", LocalDate.of
                (1997, 10, 13), 118, new TreeSet<>());
    }

    public  static Film addEmptyNameFilm() {
        return new Film("", "Отличный фильм", LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
    }

    public static Film addTooLongDescription() {
        String description = "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv";
        return new Film("Прибытие", description, LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
    }

    public static Film addErrorDateRelease() {
        return new Film("Прибытие", "Отличный фильм", LocalDate.of
                (1016, 11, 11), 118, new TreeSet<>());
    }

    public static Film addErrorDuration() {
        return new Film("Прибытие", "Отличный фильм", LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
    }

    public static Film uncorrectIdByUpdate() {
        Film film = new Film("Прибытие", "Обновление информации", LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
        film.setId(3);
        return film;
    }

    public static Film correctIdByUpdate() {
        Film film = new Film("Прибытие", "Обновление информации", LocalDate.of
                (2016, 11, 11), 118, new TreeSet<>());
        film.setId(1);
        return film;
    }
}
