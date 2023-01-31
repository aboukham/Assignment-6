package com.example.Assignment6.repo;

import com.example.Assignment6.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    List<Comment> findAll();

}
