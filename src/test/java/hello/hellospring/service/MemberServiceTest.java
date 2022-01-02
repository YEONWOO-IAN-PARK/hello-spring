package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /**
     * 테스트 메서드가 실행되기 전에, 실행되는 메서드
     *  해석: MemberService 클래스에서 static으로 선언된 MemberRepository는 MemberService생성자를 통해 주입된 MemeberRepository이다.
     *  주입된 MemberRepository는 MemberService가 사용되는 클래스 내부에서 확인가능하다.
     *  
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given (무언가가 주어졌을 때)
        Member member = new Member();
        member.setName("hello");

        // when (이것을 실행했을 때)
        Long savedId = memberService.join(member);

        // then (이러한 결과가 나와야한다)
        Member findedMember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findedMember.getName());
    }

    @Test
    public void duplicate_member_exception() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        /*try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}