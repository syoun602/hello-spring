package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void tearDown() {
        repository.clearStore();
    }

    @Test
    void save() {
        // given
        final Member member = new Member();
        member.setName("spring");
        repository.save(member);

        // when
        final Member actual = repository.findById(member.getId()).get();

        // then
        assertThat(actual).isEqualTo(member);
    }

    @Test
    void findById() {
        // given
        final Member member1 = new Member();
        member1.setName("spring1");
        final Member savedMember = repository.save(member1);

        // when
        final Member actual = repository.findById(savedMember.getId()).get();

        // then
        assertThat(actual.getName()).isEqualTo(member1.getName());
    }

    @Test
    void findByName() {
        // given
        final Member member1 = new Member();
        final Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring2");
        repository.save(member1);
        repository.save(member2);

        // when
        final Member actual = repository.findByName("spring1").get();

        // then
        assertThat(actual).isEqualTo(member1);
    }

    @Test
    void findAll() {
        // given
        final Member member1 = new Member();
        final Member member2 = new Member();
        member1.setName("spring1");
        member2.setName("spring2");
        repository.save(member1);
        repository.save(member2);

        // when
        final List<String> actual = repository.findAll().stream()
                .map(Member::getName)
                .collect(Collectors.toList());

        // then
        assertThat(actual).containsAll(List.of("spring1", "spring2"));
    }
}
