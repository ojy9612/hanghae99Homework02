package com.homework.hanghae99homework02.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
public class User{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column
    private int layout = 1;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Likes> likesList = new ArrayList<>();

    public void addLikes(Likes likes){
        likes.setUser(this);
        this.likesList.add(likes);
    }

    public void addBoard(Board board){
        this.boardList.add(board);
        board.setUser(this);
    }

    @Builder
    public User(String name, String password, List<String> roles, String email, String nickname) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.nickname = nickname;
    }

    public User(){

    }
}