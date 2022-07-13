package ru.yandex.practicum.filmorate.storage.mpa;

import ru.yandex.practicum.filmorate.model.FilmMpa;

import java.sql.ResultSet;
import java.util.List;

public interface MpaStorage {

    FilmMpa getMpaById(Integer id);

    List<FilmMpa> getAllMpa();

}
