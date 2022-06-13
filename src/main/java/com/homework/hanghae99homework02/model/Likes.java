package com.homework.hanghae99homework02.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Likes {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "likes")
    private List<Board> boardList = new ArrayList<>();

    public void addBoard(Board board){
        board.setLikes(this);
        this.boardList.add(board);
    }

    public Likes() {}

}
