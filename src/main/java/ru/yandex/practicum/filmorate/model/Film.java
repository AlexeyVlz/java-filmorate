package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
public class Film {

    private int id;
    @NonNull @NotBlank
    private final String name;
    @NonNull @NotBlank @Size(min = 1, max = 200)
    private final String description;
    @NonNull
    private final Date releaseDate;
    @NonNull @Positive
    private final int duration;
    @NonNull
    private final FilmMpa ratingMpa;
    private List<FilmGenres> genres;
}
