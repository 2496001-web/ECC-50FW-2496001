package Hello.hello_spring;

import Hello.hello_spring.repository.MemberRepository;
import Hello.hello_spring.repository.MemoryMemberRepository;
import Hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration 어노테이션: 어? 이거 읽고 스프링에 등록하라는 뜻이네?
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
        //생성자로 MemoryMemberRepository 필요
        //스프링빈에 등록되어 있는 MemberRepository를 MemberService에 넣어 줌

    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
