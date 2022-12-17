package com.lamda.practice.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void name() {
        List<Teacher> teachers = new ArrayList<>();
        Teacher kyeongrok = new Teacher("김경록", true, true);
        Teacher kyeonghwan = new Teacher("고경환", true, false);
        Teacher sujin = new Teacher("김수진", false, true);
        Teacher sohyun = new Teacher("강소현", true, true);

        teachers.add(kyeongrok);
        teachers.add(kyeonghwan);
        teachers.add(sujin);
        teachers.add(sohyun);

        // 알고리즘 true이신 분들
        System.out.println("---알고리즘 true이신 분들--");
        List<Teacher> likeAlgorithmTeachers = teachers.stream()
                .filter(teacher -> teacher.isLikeAlgorithm() == true)
                .collect(Collectors.toList());

        for (Teacher teacher : likeAlgorithmTeachers) {
            System.out.println(teacher.getName());
        }

        // DB에서 읽어왔습니다.
        List<Teacher> likeAlgorithmTeacher = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).isLikeAlgorithm()) {
                likeAlgorithmTeacher.add(teachers.get(i));
            }
        }

        // .map 추가
        System.out.println("---SpringBoot이 true이신 분들 map--");
        List<String> likeSpringBootTeachers = teachers.stream()
                .filter(teacher -> teacher.isLikeSpringBoot() == true)
                .map(teacher -> teacher.getName())
                .collect(Collectors.toList());

        for (String teacherName : likeSpringBootTeachers) {
            System.out.println(teacherName);
        }

        Optional<Teacher> optionalTeacher = Optional.of(kyeongrok);
        optionalTeacher.orElseThrow(() -> new RuntimeException());

        // 다른이름으로 받을수도 있어(=item)
        List<String> likeSpringBootTeachers1 = teachers.stream()
                .filter(item -> item.isLikeSpringBoot() == true)
                .map(item -> item.getName())
                .collect(Collectors.toList());
    }
    // .filter( Predicate<T, R>) 괄호 안이 Predicate: True or False
    // .map( Function<T, R>)

    @Test
    void predicateTest() {
        // 숫자 num을 넣으면 10보다 큰지 true, false로 리턴 해주는 내장 인터페이스
        Predicate<Integer> predicate = num -> num > 10;
        System.out.println(predicate.test(10));
    }

    // .map은 변화를 줄 수 있음
    @Test
    void map() {
        String[] list = {"1", "2", "3"};
        List<Integer> nums = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .collect(Collectors.toList());

        // teachers에서 isLikeSpringBoot()이 true인 선생님의 이름의 자릿수를 List로 바꿔보세요
        // List<Teacher> —-> List<Integer>
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("김경록", true, true));
        teachers.add(new Teacher("고경환", true, false));
        teachers.add(new Teacher("김수진", false, true));
        teachers.add(new Teacher("강소현", true, true));
        teachers.add(new Teacher("이상", false, true));

        //::의 사용
        //.filter(Teacher::isLikeSpringBoot)
        //==
        //.filter(teacher -> teacher.isLikeSpringBoot() == true)
        List<Integer> digitsOfNames = teachers.stream()
                .filter(Teacher::isLikeSpringBoot)
                .map(Teacher::cntNameDigit)
                //.map(teacher -> teacher.getName().length()) // 원래 방식
                .collect(Collectors.toList());
        System.out.println(digitsOfNames);

        //.map의 3단계 활용법
        //.map(teacher -> teacher.getName().length())  // 1단계 map에서 처리
        //.map(teacher -> teacher.cntNameDigit())  // 2단계 함수 내장
        //.map(Teacher::cntNameDigit) // 3단계 ::문법 적용
    }

    //Collection을 연산해서 한개의 값으로 만들어주는 연산
    @Test
    void reduce() {
        String[] list = {"1", "2", "3"};
        List<Integer> nums = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .collect(Collectors.toList());

        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        System.out.println(sum);

        int reduceSum = Arrays.stream(list)
                .map(strNum -> Integer.parseInt(strNum))
                .reduce(0, (a, b) -> a + b); // 재귀와 흡사합니다.
        System.out.println("reduceSum: " + reduceSum);
    }

    @Test
    void optionalTest() {
        // DB에서 select되어 값이 있는 상태
        Optional<Teacher> optionalTeacher = Optional.of(new Teacher("김경록", true, true));
        Optional<Teacher> emptyTeacher = Optional.empty();

        List<Teacher> teachers = new ArrayList<>();
        teachers.size(); // --> 값이 없으면 0

        // -- get
        Optional<Teacher> optionalTeacher3 = Optional.of(new Teacher("김경록", true, true));
        Teacher teacher = optionalTeacher3.get(); //값이 있는 경우

        // -- 값이 없는 경우 get
        // 있는 경우 값을 뽑고 없으면 RuntimeException을 throw합니다.
        Optional<Teacher> emptyTeacher2 = Optional.empty();
        emptyTeacher2.get();
        emptyTeacher2.orElseThrow(RuntimeException::new);
        Teacher teacher1 = emptyTeacher2.orElseThrow(()-> new RuntimeException()); // 위의 것과 다른방식


        // -- 값이 있는 경우 무언가 처리 하고 싶을 때
        Optional<Teacher> optionalTeacher4 = Optional.of(new Teacher("김경록", true, true));
        optionalTeacher4.ifPresent(sth -> {
            throw new RuntimeException(sth.getName()); // throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("UserName:%s", request.getUserName()));
        });

        // -- .ofNullable, orElse()
        Optional<Teacher> optionalTeacher5 = Optional.of(new Teacher(null, true, true));
        String name = Optional.ofNullable(optionalTeacher5.get().getName()).orElse("이름이 없습니다.");
        System.out.println(name);
    }
}