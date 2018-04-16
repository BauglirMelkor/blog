package com.crossover.techtrial;

import com.crossover.techtrial.model.Comment;
import com.crossover.techtrial.repository.CommentRepository;
import com.crossover.techtrial.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setup() {
        PageImpl<Comment> pageImp = new PageImpl(new ArrayList());
        PageRequest pageRequest = new PageRequest(1, 1);
        when(commentRepository.findByArticleIdOrderByDate(1L, pageRequest)).thenReturn(pageImp);
    }

    @Test
    public void saveCommentSuccessfully() {
        PageRequest pageRequest = new PageRequest(1, 1);
        Page<Comment> pageResult = commentService.findAll(1L, 1, 1);
        Assert.notNull(pageResult);
    }


}
