package com.tj.eggstolife.user.service;

import com.tj.eggstolife.user.dto.JoinDTO;
import com.tj.eggstolife.user.entity.UserEntity;
import com.tj.eggstolife.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    // password 를 받아서 그대로 저장하면 안되고 암호화 진행해주어야함
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 스프링 의존성 주입을 통해 서비스 클래스의 테스트와 유연성을 높이고, 코드의 결합도를 낮추기 위함
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    // 화원 가입 요청할 때 받은 user 이름과 password DTO 로 받고 이 값들이 데이터베이스에 없는 지 중복검사 진행한다.
    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);

    }
}
