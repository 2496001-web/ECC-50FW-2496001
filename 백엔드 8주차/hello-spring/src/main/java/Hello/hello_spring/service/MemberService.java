package Hello.hello_spring.service;

import Hello.hello_spring.domain.Member;
import Hello.hello_spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //회원 서비스를 만들려면, 먼저 회원을 저장할 저장소가 있어야 함
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원가입 서비스
    public long join(Member member) {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복 회원 검증
        //통과하면 저장
        memberRepository.save(member);
        return member.getId(); //아이디 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //멤버 아이디 반호나
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
