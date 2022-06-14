package com.homework.hanghae99homework02.repository;


import com.homework.hanghae99homework02.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


}
