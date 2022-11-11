package com.project.schedule.repository;

import com.project.schedule.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")//쿼리 수행시 lazy조회가 아닌, eagar조회로 authorities 정보를 같이 가져옴
    Optional<User> findOneWithAuthoritiesByUsername(String username);//username을 기준으로 user정보를 가져올때, 권한정보도 같이 가져오는 메서드
}
