package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.*;
import javax.validation.constraints.Size;

@Data
public class Film {

    private int id;
    @NonNull @NotBlank
    private final String name;
    @NonNull @NotBlank @Size(min = 1, max = 200)
    private final String description;
    @NonNull
    private final LocalDate releaseDate;
    @NonNull
    private final int duration;
    @NonNull
    private final Set<Integer> likes;
}
