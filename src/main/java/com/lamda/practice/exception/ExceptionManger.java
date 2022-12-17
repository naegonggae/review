package com.lamda.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 에러가 발생했다면 백엔드 개발자는 상황에 맞는 httpstatus 상태코드를 던져주고 그 상태코드에 맞는 페이지는 프론트앤드개발자가 해야함

@RestControllerAdvice
// 어노테이션을 달아주면 Layered 아키텍처의 어떤 구간에서 발생하는 에러도 이곳에서 처리 할 수 있습니다.
public class ExceptionManger { // 이름 상관없음

    // @ExceptionHandler
    //특정 에러만 잡아서 처리 할 수 있습니다.
    //모든 에러를 RestControllerAdvice에서 다 처리 하지 않아도 되기 때문
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> rentimeExceptionHandler(RuntimeException e) {
        // RuntimeException이 나면 Controller대신 이곳에서 리턴을 해줍니다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
        // INTERNAL_SERVER_ERROR를 리턴하고 ResponseBody에 e.getMessage()를 추가해서 보냅니다.
    }

    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<?> hospitalExceptionHandler(HospitalException hospitalException) {
        // 내가 만든 어플리케이션에서 특정 에러코드(숫자가 아니여도 됨)를 프론트엔드에게 알려주고 싶을때
        return ResponseEntity.status(hospitalException.getErrorCode().getHttpStatus())
                .body(hospitalException.getErrorCode().getMessage());
    }
}
