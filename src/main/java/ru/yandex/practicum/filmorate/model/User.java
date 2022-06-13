package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import javax.validation.constraints.Pattern;

@Data
@Builder(toBuilder = true)
public class User {

    private  int id;
    @NonNull @Email
    private  String email;
    @NonNull @NotBlank /*@Pattern(regexp = "^/S*$")*/
    private  String login;
    private  String name;
    @NonNull
    private LocalDate birthday;
    @Builder.Default
    private Set<Integer> friendsList = new TreeSet<>();
}
