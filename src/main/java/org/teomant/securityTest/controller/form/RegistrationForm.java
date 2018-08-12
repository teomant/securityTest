package org.teomant.securityTest.controller.form;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RegistrationForm {
    String username;
    String password;
    String role;
}
