package com.homework.hanghae99homework02.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.homework.hanghae99homework02.dto.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String image;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Likes likes;


    public Board(String image, String content, User user, Likes likes) {
        this.image = image;
        this.content = content;
        user.addBoard(this);
        if(likes != null){
            likes.addBoard(this);
        }
    }

    public void update(BoardDto boardDto){
        this.image = boardDto.getImage();
        this.content = boardDto.getContent();
    }

    public Board(){

    }

}
