package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception {
        //given : ~ 것이 주어졌을 때
        Member member = new Member();
        member.setName("taz");

        //when : ~ 하면
        Long savedId = memberService.join(member);

        //then : ~ 된다
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("taz");

        Member member2 = new Member();
        member2.setName("taz");

        //when
        memberService.join(member1);
        memberService.join(member2); //에외가 발생해야 한다!!

        //then
        fail("예외가 발생해야 한다");
    }
}