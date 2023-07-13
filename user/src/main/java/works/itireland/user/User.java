package works.itireland.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

/**
 * User Domain class
 */
@Data
@Entity
@Table(name = "users")
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text")
    private String profile;

    private String profileImageName;

    //0:active, -1:delete, -2:disabled
    private int state;

    private int credits;

    private int level;

    private String headShotUrl;

    private LocalDateTime ctime;

    private String location;

}
