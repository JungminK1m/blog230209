package shop.mtcoding.min.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinRequestDto {
        private String username;
        private String password;
        private String email;

    }

    @Getter
    @Setter
    public static class LoginRequestDto {
        private String username;
        private String password;

    }
}
