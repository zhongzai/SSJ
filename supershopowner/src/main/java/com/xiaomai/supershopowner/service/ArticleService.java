package com.xiaomai.supershopowner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.BizException;
import com.xiaomai.supershopowner.common.WebPage;
import com.xiaomai.supershopowner.dao.ArticleDao;
import com.xiaomai.supershopowner.entity.Article;
import com.xiaomai.supershopowner.entity.Cust;


@Service
public class ArticleService implements BaseService<Article, Integer>{
	@Resource
	public ArticleDao articleDao;
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(Cust.class);
	
	public List<Article> getArticle(Map<String , Object> map){
		List<Article> list = new ArrayList<>();
		SimpleDateFormat formatt = new SimpleDateFormat("YYYY-MM-dd");
		try {
			list = articleDao.findListAllWithMap(map);
			for(Article articles : list){
				
				articles.setCreateDate(formatt.format(articles.getCreateTime()));
			}	
		} catch (Exception e) {
			throw new BizException(BizErr.EX_TRANSACTION_FAIL);
		}
		return list;
	}

	@Override
	public Integer insert(Article t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Article t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebPage<Article> findPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}