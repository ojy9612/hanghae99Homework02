package com.homework.hanghae99homework02.service;

import com.homework.hanghae99homework02.dto.BoardDto;
import com.homework.hanghae99homework02.model.Board;
import com.homework.hanghae99homework02.model.User;
import com.homework.hanghae99homework02.repository.BoardRepository;
import com.homework.hanghae99homework02.repository.UserRepository;
import com.homework.hanghae99homework02.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    public Board createBoard(BoardDto boardDto, UserDetailsImpl userDetailsImpl) {
        User user = userRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        Board board = new Board(boardDto.getImage(),
                boardDto.getContent(),
                user);
        return boardRepository.save(board);
    }


    public Board getOneBoard(Long board_id) {
        return boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("ID를 찾을 수 없습니다.")
        );
    }


    public Long removeBoard(Long board_id, UserDetailsImpl userDetailsImpl) {
        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("ID를 찾을 수 없습니다.")
        );
        User user = userRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );

        if(Objects.equals(board.getUser().getId(), user.getId())){
            boardRepository.delete(board);
            return board_id;
        }else{
            return null; // Bad Request 400 에러 보내기 https://bcp0109.tistory.com/303 TODO
        }

    }

    @Transactional
    public Board updateBoard(Long board_id,BoardDto boardDto, UserDetailsImpl userDetailsImpl) {
        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("ID를 찾을 수 없습니다.")
        );
        User user = userRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );

        if(Objects.equals(board.getUser().getId(), user.getId())){
            board.update(boardDto);
            return board;
        }else{
            return null;
        }
    }


}
