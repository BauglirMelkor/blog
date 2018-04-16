package com.crossover.techtrial;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import com.crossover.techtrial.service.ArticleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;

    Article article;

    @Before
    public void setup() {
        List<Article> articleList = new ArrayList<>();
        article = new Article();
        article.setTitle("How");
        articleList.add(article);
        when(articleRepository.findTop10ByTitleContainingIgnoreCase("How")).thenReturn(articleList);
        when(articleRepository.save(article)).thenReturn(article);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
    }

    @Test
    public void searchArticleSuccessfully() {
        List<Article> articleList = articleService.search("How");
        Assert.notNull(articleList);
    }

    @Test
    public void saveArticleSuccessfully() {
        Article article1 = articleService.save(article);
        Assert.notNull(article1);
    }

    @Test
    public void findByIdSuccessfully() {
        Article article1 = articleService.findById(1L);
        Assert.notNull(article1);
    }


}
