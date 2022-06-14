package com.homework.hanghae99homework02.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class BoardDto {

    private int layout;
    private String content;

}
