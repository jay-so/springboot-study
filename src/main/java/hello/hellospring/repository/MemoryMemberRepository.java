package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    @Autowired
    private static Map<Long,Member> store = new HashMap<>(); //실무에서는 동시성 문제이기 때문에 공유되는 변수일 경우 ConcurrentHashmap을 고려함
    private static long sequence = 0L; //키값을 생성함, 실무에서는 AtomicLong 사용을 고려함

    @Override
    public Member save(Member member) { //회원 저장
        member.setId(++sequence); //회원 id의 시작값에 1을 증가함
        store.put(member.getId(),member); //HashMap에 키, 값을 대입함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //회원 id를 조회
        return Optional.ofNullable(store.get(id)); //결과가 Null일 수도 있으니 Option로 감싸서 조회함
    }

    @Override
    public Optional<Member> findByName(String name) { //회원 이름 조회
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() { //저장된 전체의 회원 조회
        return new ArrayList<>(store.values()); //store.values()에 있는것이 Member
    }

    public void clearStore(){
        store.clear();
    }
}