package com.coding.sample.graphql.component;

import java.util.Date;

import com.coding.sample.graphql.entity.Article;
import com.coding.sample.graphql.entity.Author;
import com.coding.sample.graphql.mapper.ArticleMapper;
import com.coding.sample.graphql.mapper.AuthorMapper;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private ArticleMapper articleMapper;

    public boolean newAuthor(String name, Date birthday) {
        Author author = new Author();
        author.setName(name);
        author.setBirthday(birthday);
        return authorMapper.insert(author) > 0;
    }

    public boolean newArticle(String title, String content, Long authorId) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthorId(authorId);
        articleMapper.insert(article);
        return true;
    }

}
