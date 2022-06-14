package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class ObjectsFilmControllerTest {

    public static Film addCorrectFilm() {
        return Film.builder()
                    .name("Прибытие")
                    .description("Отличный фильм")
                    .releaseDate(LocalDate.of(2016, 11, 11))
                    .duration(118)
                    .likes(new TreeSet<>())
                    .build();
    }

    public static Film addCorrectFilm2() {
        return Film.builder()
                .name("Вор")
                .description("Сильный фильм")
                .releaseDate(LocalDate.of(1997, 10, 13))
                .duration(118)
                .likes(new TreeSet<>())
                .build();
    }

    public  static Film addEmptyNameFilm() {
        return Film.builder()
                .name("")
                .description("Отличный фильм")
                .releaseDate(LocalDate.of(2016, 11, 11))
                .duration(118)
                .likes(new TreeSet<>())
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
                .releaseDate(LocalDate.of(2016, 11, 11))
                .duration(118)
                .likes(new TreeSet<>())
                .build();
    }

    public static Film addErrorDateRelease() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(LocalDate.of(1016, 11, 11))
                .duration(118)
                .likes(new TreeSet<>())
                .build();
    }

    public static Film addErrorDuration() {
        return Film.builder()
                .name("Прибытие")
                .description("Отличный фильм")
                .releaseDate(LocalDate.of(2016, 11, 11))
                .duration(-1)
                .likes(new TreeSet<>())
                .build();
    }

    public static Film uncorrectIdByUpdate() {
        Film film = Film.builder()
                .name("Прибытие")
                .description("Обновление информации")
                .releaseDate(LocalDate.of(2016, 11, 11))
                .duration(118)
                .likes(new TreeSet<>())
                .build();
        film.setId(3);
        return film;
    }

    public static Film correctIdByUpdate() {
        Film film = Film.builder()
                .name("Прибытие")
                .description("Обновление информации")
                .releaseDate(LocalDate.of(2016, 11, 11))
                .duration(118)
                .likes(new TreeSet<>())
                .build();
        film.setId(1);
        return film;
    }
}
