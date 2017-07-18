package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Article;
import com.xiaomai.supershopowner.service.ArticleService;

import net.sf.json.JSONObject;

/**
 * 
 * @author 叩学聪
 * @version 文章表数据
 * @return 返回文章信息表数据
 * 根据购创建文章时间查询(createTime)
 */
@Controller
@RequestMapping(value="/article")
public class ArticleRS extends BaseRS{
	@Autowired
	public ArticleService articleService;
	
	@RequestMapping(value="/findArticle" , method = RequestMethod.POST)
	public @ResponseBody String getArticle(@Context HttpHeaders headers ,@RequestBody Article article){
		RSResult result = new RSResult();
		HashMap<String , Object> map = super.getQueryMap();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		map.put("createTime", format.format(article.getCreateTime()));
		try {
			List<Article> articleList = articleService.getArticle(map);
		
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(articleList);
		} catch (Exception e) {
			if(BizErr.EX_UPDATE_FAIL.equals(e.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
