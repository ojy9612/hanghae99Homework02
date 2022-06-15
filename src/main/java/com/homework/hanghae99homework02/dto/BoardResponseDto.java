package com.homework.hanghae99homework02.dto;

import com.homework.hanghae99homework02.model.Board;
import com.homework.hanghae99homework02.model.Likes;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {

    private final Long board_id;
    private final String imageLink;
    private final String content;
    private final String userNickname;
    private final int layout;
    private List<Likes> likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponseDto(Board board) {
        this.board_id = board.getBoard_id();
        this.imageLink = board.getImageLink();
        this.content = board.getContent();
        this.userNickname = board.getUser().getNickname();
        this.layout = board.getLayout();
        this.likes = board.getLikesList();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getModifiedAt();
    }
}
