package hello.hellospring.service;

import static org.assertj.core.api.Assertions.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void tearDown() {
        memberRepository.clearStore();
    }

    @DisplayName("회원가입을 확인한다.")
    @Test
    void join() {
        // given
        final Member member = new Member();
        member.setName("hello");

        // when
        final Long actual = memberService.join(member);

        // then
        final Member expected = memberService.findOne(actual).get();
        assertThat(member.getName()).isEqualTo(expected.getName());
    }

    @DisplayName("중복된 이름으로 이미 가입되어 있을 시 예외를 발생한다.")
    @Test
    void join_throwsDuplicateNameExistException() {
        // given
        final Member member1 = new Member();
        member1.setName("spring");

        final Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 회원입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
