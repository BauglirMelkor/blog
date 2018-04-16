package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Comment;
import org.springframework.data.domain.PageImpl;

public interface CommentService {

    /*
     * Returns all the Comments related to article along with Pagination
     * information.
     */
    PageImpl<Comment> findAll(Long articleId, Integer pageNumber, Integer pageSize);

    /*
     * Save the default article.
     */
    Comment save(Comment comment);

}
