package com.homework.hanghae99homework02.service;

import com.homework.hanghae99homework02.dto.AwsS3;
import com.homework.hanghae99homework02.dto.BoardDto;
import com.homework.hanghae99homework02.exception.ErrorCode;
import com.homework.hanghae99homework02.exception.WrongIdException;
import com.homework.hanghae99homework02.model.Board;
import com.homework.hanghae99homework02.model.User;
import com.homework.hanghae99homework02.repository.BoardRepository;
import com.homework.hanghae99homework02.repository.UserRepository;
import com.homework.hanghae99homework02.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final AwsS3Service awsS3Service;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository, AwsS3Service awsS3Service) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.awsS3Service = awsS3Service;
    }

    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    public Board createBoard(MultipartFile multipartFile, BoardDto boardDto, UserDetailsImpl userDetailsImpl) {
        User user = userRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );

        AwsS3 awsS3;
        try {
            awsS3 = awsS3Service.upload(multipartFile,"mydir");
        } catch (IOException e) {
            System.out.println(111);
            throw new RuntimeException(e);
        }

        Board board = new Board(awsS3.getPath(), awsS3.getKey(), boardDto.getContent(), boardDto.getLayout(), user);

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
            awsS3Service.remove(AwsS3.builder()
                            .key(board.getImageKey())
                            .path(board.getImageLink())
                            .build());

            boardRepository.delete(board);
            return board_id;
        }else{
            throw new WrongIdException(ErrorCode.JUST_HANDLE_SELF);
        }

    }

    @Transactional
    public Board updateBoard(MultipartFile multipartFile,Long board_id, BoardDto boardDto, UserDetailsImpl userDetailsImpl) {
        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("ID를 찾을 수 없습니다.")
        );
        User user = userRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );

        if(Objects.equals(board.getUser().getId(), user.getId())){
            awsS3Service.remove(AwsS3.builder()
                            .path(board.getImageLink())
                            .key(board.getImageKey())
                            .build());

            AwsS3 awsS3;
            try {
                awsS3 = awsS3Service.upload(multipartFile, "mydir");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            board.update(awsS3.getPath(), awsS3.getKey(), boardDto.getContent(), boardDto.getLayout());
            return board;
        }else{
            throw new WrongIdException(ErrorCode.JUST_HANDLE_SELF);
        }
    }

}
