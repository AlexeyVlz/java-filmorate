package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjectsFilmControllerTest {

    public static Film correctFilm() {
        return new Film(1, "Прибытие", "Отличный фильм", LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(7080));
    }

    public static Film correctFilm2() {
        return new Film(2, "Вор", "Сильный фильм", LocalDate.of
                (1997, 10, 13), Duration.ofSeconds(7080));
    }

    public  static Film emptyNameFilm() {
        return new Film(1, "", "Отличный фильм", LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(7080));
    }

    public static Film tooLongDescription() {
        String description = "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv" +
                "sfsdffgdbfvzdvmdlvnDJVnsbj:SDLJVn;DLKvn:DvnfjvcKJCvbjhfbskvjskjv";
        return new Film(1, "��������", description, LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(7080));
    }

    public static Film errorDateRelease() {
        return new Film(1, "Прибытие", "Отличный фильм", LocalDate.of
                (1016, 11, 11), Duration.ofSeconds(7080));
    }

    public static Film errorDuration() {
        return new Film(1, "Прибытие", "Отличный фильм", LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(-1));
    }

    public static Film uncorrectIdByUpdate() {
        return new Film(3, "Прибытие", "Обновление информации", LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(7080));
    }

    public static Film correctIdByUpdate() {
        return new Film(1, "Прибытие", "Обновление информации", LocalDate.of
                (2016, 11, 11), Duration.ofSeconds(7080));
    }
}
