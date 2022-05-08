package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//    private final EntityManager entityManager;
//
//    public SpringConfig(final DataSource dataSource, final EntityManager entityManager) {
//        this.dataSource = dataSource;
//        this.entityManager = entityManager;
//    }

    private final MemberRepository memberRepository;

    public SpringConfig(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // Memory에서 Jdbc로 변경
//        return new JdbcTemplateMemberRepository(dataSource); // Jdbc에서 JdbcTemplate으로 변경
//        return new JpaMemberRepository(entityManager);
//    }
}
