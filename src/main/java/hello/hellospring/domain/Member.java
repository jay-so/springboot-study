package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id //Pk매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Identity = DB가 자동 생성
    private Long id; //회원 id 식별자 - 시스템이 임의로 생성함
    private String name; //회원 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
