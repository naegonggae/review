package com.lamda.practice.service;

import com.lamda.practice.configuration.Calculator;
import com.lamda.practice.domain.Teacher;
import com.lamda.practice.exception.ErrorCode;
import com.lamda.practice.exception.HospitalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @RestControllerAdvice가 없으면 world()에서 예외처리하고 컨트롤러에서 예외가 뭔지 확인하고 사용자한테 보내줘야함
@Service
public class TeacherService{

    /*
    public String world() {
        // controller에서 호출할때 exception이 발생할 수 있다.
        Optional<Teacher> teacherOptional = Optional.empty();
        // teacher를 받아와서 이름을 꺼내고 싶다. 그리고 유저에게 에러가 왜 났는지 알려주고 싶다.
        // 근데 이렇게만 하면 정작 화면에는 알수없는 메세지 뜨고 콘솔창에만 메세지가 나온다.
        // 그래서 어떤 문제가 발생했는지 유저에게 보여주기 위해 @RestControllerAdvice를 쓴다.
        // @RestControllerAdvice는 특정 Exception이 발생하면 그게 어디든 이쪽에서 처리할 수 있습니다.
        // 왜냐하면 Error를 Throw하면 바로 위로만 가기 때문입니다.
        Teacher teacher = teacherOptional.orElseThrow(()->new RuntimeException("해당 선생님이 없습니다."));

        return "World";
    }

     */
/*
    public String world(String userName) {
        Optional<Teacher> teacherOptional = Optional.empty();
        //Java의 기능이 아닌 내
        //가 만든 Business로직에서 에러가 난 것을 처리
        teacherOptional.ifPresent(teacher -> {
            throw new HospitalException(ErrorCode.DUPLICATE_USER_NAME, "DB에 "+userName+" 으로 검색 했을 때 빈 값이 아닙니다.");
        });
        Teacher teacher = teacherOptional.orElseThrow(()->new RuntimeException("해당 선생님이 없습니다."));

        return String.valueOf(plus.calc(20, 30));
    }

 */
    public String world(String userName) {
        Optional<Teacher> teacherOptional = Optional.empty();

        //HospitalException을 Throw할 때 HospitalException에 선언한 ErrorCode와 message를 전달 합니다.
        Teacher teacher = teacherOptional.orElseThrow(()->
                new HospitalException(ErrorCode.USERNAME_NOT_FOUND, "DB에 "+userName+"으로 검색 했을 때 빈 값이 아닙니다."));

        return "world";
    }
}
