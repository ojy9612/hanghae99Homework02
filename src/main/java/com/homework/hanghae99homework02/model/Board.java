package com.homework.hanghae99homework02.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.homework.hanghae99homework02.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long board_id;

    @Column
    private String image;

    @Column
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Likes> likesList = new ArrayList<>();


    public void addLikes(Likes likes){
        likes.setBoard(this);
        this.likesList.add(likes);
    }

    public Board(String image, String content, User user) {
        this.image = image;
        this.content = content;
        user.addBoard(this);

    }

    public void update(BoardDto boardDto){
        this.image = boardDto.getImage();
        this.content = boardDto.getContent();
    }

    public Board(){

    }

}
