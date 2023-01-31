package com.example.Assignment6.controller;

import com.example.Assignment6.entity.User;
import com.example.Assignment6.entity.dto.CommentDto;
import com.example.Assignment6.entity.dto.PostDto;
import com.example.Assignment6.service.CommentService;
import com.example.Assignment6.service.PostService;
import com.example.Assignment6.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("")
    public List<PostDto> allPost(@RequestParam(value = "author" ,required = false) String author, @RequestParam(value = "title" ,required = false) String title) {
        return author == null && title == null  ? postService.findAll() : postService.findByAuthorAndTitle(author, title);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable long id, HttpServletResponse res) {
        PostDto p = postService.findById(id);
        if(p == null){
            res.setStatus(404);
            return null;
        }
        else return p;
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        postService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto p, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        p.setUserId(user.getId());
        postService.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long postId, @RequestBody PostDto p) {
        postService.update(postId,p);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public void saveComment(@PathVariable("id") long postId, @RequestBody CommentDto c) {
        commentService.save(postId, c);
    }

}
