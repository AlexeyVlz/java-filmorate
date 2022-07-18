package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import javax.validation.constraints.Pattern;

@Data
@Builder(toBuilder = true)
public class User {

    private  int id;
    @NonNull @Email
    private  String email;
    private  String name;
    @NonNull @NotBlank
    private  String login;
    private Date birthday;

}
