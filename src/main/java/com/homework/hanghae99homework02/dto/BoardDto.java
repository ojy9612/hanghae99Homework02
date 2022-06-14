package com.homework.hanghae99homework02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
public class BoardDto {

    private int layout;
    private String content;

    public BoardDto(int layout, String content) {
        this.layout = layout;
        this.content = content;
    }
}
