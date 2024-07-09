package com.bar.travelplanner.dto;

import com.bar.travelplanner.utils.constants.DTOConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String name;

    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = DTOConstants.USERNAME_REG_EXP, message = "Username should be 2-20 characters long and can consist of English letters, digits, underscores, or hyphens")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 4, max = 20, message = "Password length should be between 4-20 characters")
    private String password;
}
