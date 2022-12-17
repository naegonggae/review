package com.lamda.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private String Name;
    private boolean isLikeAlgorithm;
    private boolean isLikeSpringBoot;
}
