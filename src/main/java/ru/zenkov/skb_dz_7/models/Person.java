package ru.zenkov.skb_dz_7.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class Person {

    @NotNull
    @Min(0)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Min(value = 18, message = "Строго +18!")
    @Max(value = 65, message = "Извините, но вы слишком стары(")
    private Integer age;

    @Email(message = "Неккорректный адрес почты")
    private String emailAddress;

}
