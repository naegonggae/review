package com.lamda.practice.controller;

import com.lamda.practice.service.TeacherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    // @Service, @Configuration-@Been, @Component 셋중에 하나 달려있으면 new TeacherService()해서
    // application contects에다가 등록해줘 그리고 그것들을 @Autowired를 통해 불러와
    // 그런데 보통 @Autowired이거 생략하고 private final ~~ 하고 생성자를 통해 DI해줌
    private final TeacherService teacherService;

    // 이것도 어노테이션으로 생략가능
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // spring을 쓰기전까지는 이렇게 했었음
    // 그런데 new를 많이 하면 가비지 컬랙션을 해야함.
    // 스프링부트는 대규모 트레픽을 트레픽을 처리하게 만들어져있어 그래서 컨트롤러는 굉장히 많이 사용되는데 사용될때마다 new를 해주면 메모리도 꽉차고
    // 가비지컬랙션도 많이 돌아
    // new 한거는 heap영역에 올라가 그래서 쓰고나서 계속 없애줘야함
    //TeacherService teacherService = new TeacherService();
/*
    @GetMapping
    public String get() {
        teacherService.world();
        return "hello";
    }

 */

    @GetMapping("/{userName}")
    public String get(@PathVariable String userName) {
        String r = teacherService.world(userName);
        return "world";
    }
}
