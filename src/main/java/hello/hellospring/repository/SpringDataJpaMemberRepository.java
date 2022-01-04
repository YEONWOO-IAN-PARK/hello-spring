package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository
            extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    // 와 같은 방식으로 메소드 이름과 파라미터를 근거로 유추하여 스프링데이터JPA는 JPQL을 생성한다.
    @Override
    Optional<Member> findByName(String name);
}
