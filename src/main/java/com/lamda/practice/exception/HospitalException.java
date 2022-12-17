package com.lamda.practice.exception;
// CustomException
// 비즈니스 로직을 내가 짠 경우 java에서 정의한 예외 말고도 다른 예외가 있을 수 있습니다.
// Login할 때 가입한 userName이 없는 경우
// Login할 때 Password틀린 경우
// 결제를 할때 주문한 가격 이상 입금이 된 경우

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;

    @Override
    public String toString() {
        return errorCode.name();
    }
}
