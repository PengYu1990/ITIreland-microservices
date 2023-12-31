package works.itireland.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
        private Long id;
        private String message;

        // 0 unread, -1 read
        private int state;
        private String fromUsername;
        private String toUsername;

        // 0: System, 1: User
        private int type;
}
