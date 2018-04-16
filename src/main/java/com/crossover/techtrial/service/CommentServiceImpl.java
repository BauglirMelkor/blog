package com.crossover.techtrial.service;

import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    /*
     * Returns all the Comments related to article along with Pagination
     * information.
     */
    public PageImpl<Comment> findAll(Long articleId, Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
        return commentRepository.findByArticleIdOrderByDate(articleId, pageRequest);
    }


    /*
     * Save the default article.
     */
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

}
