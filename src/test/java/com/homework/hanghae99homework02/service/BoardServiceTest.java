package com.homework.hanghae99homework02.service;

import com.homework.hanghae99homework02.dto.BoardDto;
import com.homework.hanghae99homework02.model.User;
import com.homework.hanghae99homework02.repository.BoardRepository;
import com.homework.hanghae99homework02.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    private BoardService boardService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 생성")
    void createBoard() {
        User user = User.builder()
                .name("아이디1")
                .password("비밀번호")
                .roles(Collections.singletonList("ROLE_USER"))
                .email("이메일1@메일.일")
                .nickname("닉네임1")
                .build();
        user.setId(1L);
        BoardDto boardDto = new BoardDto();
        boardDto.setImage("제목1");
        boardDto.setContent("내용1");
//        Board board = new Board(boardDto.getTitle(),boardDto.getContent(),user);
//        board.setId(1L);
//
//        Mockito.doReturn(Optional.of(user))
//                .when(userRepository).findByUsername(user.getUsername());
//
//
//        Mockito.doReturn(board)
//                .when(boardRepository).save(board);

//        Board result = boardService.createBoard(boardDto, new UserDetailsImpl(user.getUsername(), user.getRoles()));
//        System.out.println("result = " + result);
//        assertThat(result).isEqualTo(board);
    }

    @Test
    void getAllBoard() {
    }

    @Test
    void getOneBoard() {
    }

    @Test
    void removeBoard() {
    }

    @Test
    void updateBoard() {
    }

}