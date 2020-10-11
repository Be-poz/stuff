package ticket.ksy.repository;

import ticket.ksy.domain.Member;

public interface MemberRepositoryCustom {
    Member findMember(String email, String password);
}
