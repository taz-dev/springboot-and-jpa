package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryOld {

    //@PersistenceContext
    //private EntityManager em;
    private final EntityManager em;

    //회원 저장
    public void save(Member member) {
        em.persist(member);
    }

    //회원 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //전체 회원목록 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //"이름"으로 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
