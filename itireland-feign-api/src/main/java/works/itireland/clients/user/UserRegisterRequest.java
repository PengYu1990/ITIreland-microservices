package works.itireland.clients.user;

import lombok.Data;


@Data
public class UserRegisterRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String profile;
    private String location;
}
