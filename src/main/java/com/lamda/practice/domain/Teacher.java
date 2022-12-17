package com.lamda.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Teacher {
    private String Name;
    private boolean isLikeAlgorithm;
    private boolean isLikeSpringBoot;

    public int cntNameDigit() {
        return this.Name.length();
    }
}
