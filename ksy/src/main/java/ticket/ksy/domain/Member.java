package ticket.ksy.domain;

import lombok.*;
import ticket.ksy.dto.MemberDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    private String password;
    private LocalDateTime joinedAt;

    private String role;

    public void memberSetting(String email, String nickname, String password, String role) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        joinedAt = LocalDateTime.now();
        this.role = role;
    }
}
