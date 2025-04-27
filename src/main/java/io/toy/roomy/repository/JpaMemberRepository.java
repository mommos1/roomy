package io.toy.roomy.repository;

import io.toy.roomy.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// jpa 는 모든 데이터 변경이 트렌젝션 안에서 실행되어야함
@Repository
@Transactional
public class JpaMemberRepository{

    // jpa 는 EntityManager 라는것으로 동작 Spring boot 가 자동으로 생성해줌
    //private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        //this.em = em;
    }
}
