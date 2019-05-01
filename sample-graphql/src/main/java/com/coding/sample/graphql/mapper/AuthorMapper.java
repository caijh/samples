package com.coding.sample.graphql.mapper;

import com.coding.sample.graphql.entity.Author;

public interface AuthorMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Author record);

    int insertSelective(Author record);

    Author selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Author record);

    int updateByPrimaryKey(Author record);

}