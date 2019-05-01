package com.coding.sample.graphql.component;

import com.coding.sample.graphql.entity.Article;
import com.coding.sample.graphql.entity.Author;
import com.coding.sample.graphql.mapper.ArticleMapper;
import com.coding.sample.graphql.mapper.AuthorMapper;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private ArticleMapper articleMapper;

    public Author findAuthorById(Long id) {
        return authorMapper.selectByPrimaryKey(id);
    }

    public Article findArticleById(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

}
