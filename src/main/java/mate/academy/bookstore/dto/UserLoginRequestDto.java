package mate.academy.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto (
        @Email
        String email,
        @NotBlank(message = "Your password can't be empty")
        @Size(min = 8, max = 20,
                message = "Your password must contain at least 8 characters"
                        + " and at most 20 characters")
        String password
) {
}
