package ticket.ksy.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ticket.ksy.domain.Member;
import ticket.ksy.domain.Role;
import ticket.ksy.repository.MemberRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackedLoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member=memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다."));
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + member.getRole()));
        return new User(member.getEmail(),member.getPassword(),authorities);
    }

//    private Collection<? extends GrantedAuthority> setAuthorities(Member member) {
//        List<Role> roles = member.getRoles();
//        List<GrantedAuthority> list = new ArrayList<>();
//        roles.forEach(role->list.add(new SimpleGrantedAuthority("ROLE_"+role.name())));
//        return list;
//    }
}
