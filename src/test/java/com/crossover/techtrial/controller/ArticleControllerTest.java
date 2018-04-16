package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Article;
import com.crossover.techtrial.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ArticleRepository articleRepository;

    private Article article;

    @Before
    public void setup() throws Exception {
        article = new Article();
        article.setEmail("serhadburakan@hotmail.com");
        article.setTitle("How to boil eggs");
        article.setContent("Get a bowl and heat the water to reach boiling point. Put chicken egg for approximately 10 minutes.");
        article.setDate(LocalDateTime.now());
        article.setPublished(true);
        articleRepository.save(article);
    }

    @Test
    public void testArticleShouldBeCreated() throws Exception {
        HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"title\": \"hello\",\"content\": \"user1@gmail.com\" }");
        ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article, Article.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
    }

    @Test
    public void testArticleShouldBeFetchedById() throws Exception {

        ResponseEntity<Article> resultAsset = template.getForEntity("/articles/" + article.getId(), Article.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
        Assert.assertEquals(resultAsset.getBody().getId(), article.getId());
    }

    @Test
    public void testArticleByIdNotFoundError() {
        ResponseEntity<Article> resultAsset = template.getForEntity("/articles/1000", Article.class);
        Assert.assertEquals(resultAsset.getStatusCode(), HttpStatus.NOT_FOUND);
    }


    @Test
    public void testArticleShouldBeUpdated() throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Article> articleEntity = new HttpEntity<>(article, headers);
        ResponseEntity<Article> resultAsset = template.exchange("/articles/" + article.getId(), HttpMethod.PUT, articleEntity, Article.class);
        Assert.assertNotNull(resultAsset.getBody().getId());
        Assert.assertEquals(resultAsset.getBody().getId(), article.getId());
    }

    @Test
    public void searchArticleSuccessful() throws Exception {
        ResponseEntity<Article[]> resultAsset = template.getForEntity("/articles/search?text=How", Article[].class);
        Assert.assertNotNull(resultAsset.getBody()[0].getId());
    }

    @Test
    public void testArticleShouldBeDeleted() throws Exception {
        template.delete("/articles/1");
        ResponseEntity<Article> resultAsset = template.getForEntity("/articles/" + article.getId(), Article.class);
        Assert.assertNotNull(resultAsset.getBody());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}
