package ita.ms.users.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {
    String email;
    String password;
    String firstName;
    String lastName;
}
