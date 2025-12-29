package Hello.hello_spring.service;

import Hello.hello_spring.domain.Member;
import Hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    //어떤 메소드 실행 전에 이 메소드 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    //어떤 메소드가 끝나면 이 메소드 실행
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //위의 경우는 정상경우
    //테스트는 예외 경우를 잡는 게 더 중요함!!
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        //위에서 e가 메시지를 반환하므로 메시지 검증은 아래와 같이 함
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //try-catch 쓰지 않는 이유?
        //1. 테스트 코드의 가독성과 의도를 명확히 하기 위해
        //2. assertThrows()는 람다 블록 안의 코드가 예외를 잡아 던지지 않으면 테스트 실패

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}