package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 테스트케이스마다 달면, 테스트 실행을 한 뒤, 테스트가 끝나면 Rollback 해줌
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원가입을 확인한다.")
    @Test
    void join() {
        // given
        final Member member = new Member();
        member.setName("spring");

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
}
