package ticket.ksy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ticket.ksy.domain.Member;
import ticket.ksy.domain.Role;
import ticket.ksy.dto.MemberDTO;
import ticket.ksy.repository.MemberRepository;
import ticket.ksy.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("greeting", (String) session.getAttribute("greeting"));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        log.info("get successed");
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        return "register";
    }


    @PostMapping("/register")
    public String register_process(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO,
                                   BindingResult result) {
        if (result.hasErrors()) {

            return "register";
        } else {
            Member member = new Member();
            member.memberSetting(memberDTO.getEmail(), memberDTO.getNickname(), passwordEncoder.encode(memberDTO.getPassword()), Role.ADMIN.name());
            Long member_id = memberService.save(member).getId();
            return "register_success";
        }
    }

    @GetMapping("/denied")
    public String deniedProcess() {
        return "denied";
    }

    @GetMapping("/member")
    public String member_page() {
        log.info("memberPage");
        return "memberPage";
    }

    @GetMapping("/admin")
    public String admin_page() {
        log.info("adminPage");
        return "adminPage";
    }
}
