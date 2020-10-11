package ticket.ksy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.ksy.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
}
