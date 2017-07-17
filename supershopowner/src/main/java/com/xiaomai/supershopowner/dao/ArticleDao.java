package com.xiaomai.supershopowner.dao;

import org.springframework.stereotype.Repository;

import com.xiaomai.supershopowner.entity.Article;

@Repository
public class ArticleDao extends BaseDaoImpl<Article, Integer>{

	@Override
	public String getNameSpace() {
		return "mappers.ArticleMapper";
	}
	
}
