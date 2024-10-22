package com.tj.eggstolife.user.controller;

import com.tj.eggstolife.user.dto.JoinDTO;
import com.tj.eggstolife.user.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }
    // post 방식으로 데이터가 날라올 것이기 때문에 postmapping 진행해준다.
    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO) {

        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
/**
 * Autowired는 필드 주입을 사용할 때 편리하지만, 생성자 주입을 할 때
 * 불변성: 생성자를 통해 주입된 필드는 한번 초기화 되면 변경될 수 없음
 * , 테스트 용이성, 의존성 명확화 같은 부분에 이점이 있음
 */