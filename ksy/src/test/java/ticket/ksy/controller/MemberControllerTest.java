package ticket.ksy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ticket.ksy.dto.MemberDTO;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void memberRegisterTest() throws Exception{
        //given
        MemberDTO memberDTO = MemberDTO.builder()
                        .email("ddd@naver.com")
                        .nickname("ff")
                        .password("ff")
                        .joinedAt(LocalDateTime.now())
                .build();
        //when
        mockMvc.perform(post("/members")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
//                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(memberDTO)))
                .andDo(print());
        //then
    }

    @Test
    @DisplayName("주어진 Member email/pwd 를 통한 값 확인 테스트")
    public void loginTest() throws Exception{
        //given
        MemberDTO memberDTO = MemberDTO.builder()
                .email("ddd@naver.com")
                .nickname("ff")
                .password("ff")
                .joinedAt(LocalDateTime.now())
                .build();
        //when&then
        mockMvc.perform(post("/members/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                    .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)))
                .andDo(print());
    }

}



















