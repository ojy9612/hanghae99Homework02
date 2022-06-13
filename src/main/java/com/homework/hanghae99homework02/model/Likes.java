package com.homework.hanghae99homework02.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Likes{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToMany(mappedBy = "likes", fetch = FetchType.LAZY)
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "likes", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    public void addUser(User user){
        user.setLikes(this);
        this.userList.add(user);
    }

    public void addBoard(Board board){
        board.setLikes(this);
        this.boardList.add(board);
    }

    public Likes() {

    }

}
