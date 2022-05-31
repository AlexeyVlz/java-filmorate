package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Film {

    private int id;
    private final String name;
    private final String description;
    private final LocalDate releaseDate;
    private final Duration duration;


}
