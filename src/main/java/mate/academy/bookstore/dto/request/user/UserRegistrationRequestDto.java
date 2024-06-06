package mate.academy.bookstore.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.validation.FieldMatch;

@Data
@FieldMatch(firstFieldName = "password", secondFieldName = "repeatedPassword",
        message = "Your passwords must match")
public class UserRegistrationRequestDto {
    @Email
    private String email;
    @NotBlank(message = "Your password can't be empty")
    @Size(min = 8, max = 20,
            message = "Your password must contain at least 8 characters and at most 20 characters")
    private String password;
    @NotBlank(message = "Confirm your password!")
    private String repeatedPassword;
    @NotBlank(message = "First name can't be empty")
    private String firstName;
    @NotBlank(message = "Last name can't be empty")
    private String lastName;
    private String shippingAddress;
}
