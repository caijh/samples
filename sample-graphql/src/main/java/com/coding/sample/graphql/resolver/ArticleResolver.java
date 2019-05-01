package com.coding.sample.graphql.resolver;

import com.coding.sample.graphql.entity.Article;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class ArticleResolver implements GraphQLResolver<Article> {

}
