package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Film {

    private int id;
    @NonNull @NotBlank
    private final String name;
    @NonNull @NotBlank
    private final String description;
    @NonNull
    private final LocalDate releaseDate;
    @NonNull
    private final Duration duration;


}
