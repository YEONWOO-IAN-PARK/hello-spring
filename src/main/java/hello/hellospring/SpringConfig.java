package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig  {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }

    /**
     * @Configuration 어노테이션이 부착된 자바클래스는 Spring Bean Configuration xml파일을 대체한 스프링 빈 설정 파일이다.
     *
     * @Service, @Controller, @Component, ...등을 사용하지 않을때 사용한다.
     *
     * @Bean 어노테이션은 @Component와 같은 기능을 하지만,
     * 1. 상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링의 빈으로 등록한다.
     * 2. 개발자가 제어하지 못하는 외부 라이브러리 등을 스프링의 빈으로 등록할 때 사용된다.
     *
     * ex1)
     *      @Bean
     *      public MemeberRepository memberRepository() {
     *          return new MemoryMemberRepository(); -> return new DBMemberRepository();
     *      }
     *
     * ex2)
     *      @Bean
     *      public ArrayList<String> array() {
     *          return new ArrayList<String>();
     *      }
     */
}
