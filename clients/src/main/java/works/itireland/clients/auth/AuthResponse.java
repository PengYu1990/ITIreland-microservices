package works.itireland.clients.auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String username;
    private String email;

    private String profile;

    //0:active, 1:disabled, 2:delete
    private int state;

    private int credits;

    private int level;

    private String headShotUrl;

    private String location;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ctime;

    private String token;

}
