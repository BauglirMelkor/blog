package com.crossover.techtrial.repository;

import com.crossover.techtrial.model.Comment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {

    @Override
    List<Comment> findAll();

    PageImpl<Comment> findByArticleIdOrderByDate(Long articleId, Pageable page);
}
