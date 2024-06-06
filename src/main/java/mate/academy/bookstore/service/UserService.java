package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.request.user.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.response.user.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
