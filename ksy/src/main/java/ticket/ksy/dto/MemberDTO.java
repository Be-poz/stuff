package ticket.ksy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class MemberDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    @Pattern(regexp="[가-힣a-zA-Z0-9]*",message = "한글,알파벳,숫자로만 입력해주세요")
    @Size(min=2,max=8,message = "2~8글자만 가능합니다")
    private String nickname;

    @NotEmpty
    @Pattern(regexp ="[a-zA-Z0-9]*",message = "알파벳과 숫자로만 입력해주세요")
    @Size(min=4,max=20,message = "4~20글자만 가능합니다")
    private String password;

    @NotEmpty
    @Pattern(regexp ="[a-zA-Z0-9]*",message = "알파벳과 숫자로만 입력해주세요")
    @Size(min=4,max=20,message = "4~20글자만 가능합니다")
    private String password2;

    private boolean userIdExist;

}
