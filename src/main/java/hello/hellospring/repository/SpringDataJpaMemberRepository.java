package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // Spring Data Jpa가 JpaRepository를 상속받은 인터페이스에 대해 구현체를 자동으로 만들어서 등록해줌
    @Override
    Optional<Member> findByName(final String name);
}
