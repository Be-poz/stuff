package ticket.ksy.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ticket.ksy.domain.Member;
import ticket.ksy.domain.QMember;

import javax.persistence.EntityManager;

import static ticket.ksy.domain.QMember.member;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Member findMember(String email, String password) {
        return queryFactory
                .selectFrom(member)
                .where(
                        emailAndpwdEq(email,password)
                )
                .fetchOne();
    }

    private BooleanBuilder emailAndpwdEq(String email, String pwd) {
        BooleanBuilder bb = new BooleanBuilder();
        bb.and(emailEq(email));
        bb.and(pwdEq(pwd));
        return bb;
    }

    private BooleanExpression emailEq(String email) {
        return member.email.eq(email);
    }

    private BooleanExpression pwdEq(String pwd){
        return member.password.eq(pwd);
    }
}
