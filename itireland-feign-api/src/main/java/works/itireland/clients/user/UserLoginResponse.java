package works.itireland.clients.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User Domain class
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String profile;

    //0:active, -1:delete, -2:disabled
    private int state;
    private int credits;

    private String role;

    private int level;
    private String headShotUrl;

    private LocalDateTime ctime;

    private String location;

}
