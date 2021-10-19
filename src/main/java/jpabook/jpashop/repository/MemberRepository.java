package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /*
    * Spring Data JPA
    * JpaRepository라는 인터페이스를 제공하는데, 여기에 기본적인 CRUD 기능이 모두 제공됨
    * findByName처럼 일반화 하기 어려운 기능도 메서드 이름으로 정확한 JPQL 쿼리를 실행함
    */

    //select m from Member m where m.name= ?
    List<Member> findByName(String name);
}
