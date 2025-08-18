package com.semenov.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserDto {

    private Long id;
    private String login;
    private String name;
    private String password;
    private LocalDate birthdate;
}
