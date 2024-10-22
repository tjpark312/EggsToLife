package com.tj.eggstolife.user.repository;

import com.tj.eggstolife.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

// 데이터 베이스에 접근할 수 있는 repository 생성
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);
}
