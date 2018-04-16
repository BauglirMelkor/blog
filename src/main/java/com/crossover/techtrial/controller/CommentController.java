package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.service.ArticleService;
import com.crossover.techtrial.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ArticleService articleService;

    @PostMapping(path = "articles/{article-id}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "article-id") Long articleId,
                                                 @RequestBody Comment comment) {
        comment.setArticle(articleService.findById(articleId));
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping(path = "articles/{article-id}/{pageNumber}/{pageSize}/comments")
    public ResponseEntity<PageImpl<Comment>> getComments(@PathVariable("article-id") Long articleId, @PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize) {

        return new ResponseEntity<>(commentService.findAll(articleId, pageNumber, pageSize), HttpStatus.OK);
    }
}
