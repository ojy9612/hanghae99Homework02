package com.homework.hanghae99homework02.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
public class BoardDto {

    @NotEmpty(message = "경태님? layout 비었어요")
    private int layout;

    @NotEmpty(message = "경태님?? content비었어요")
    private String content;

    public BoardDto(int layout, String content) {
        this.layout = layout;
        this.content = content;
    }
}
