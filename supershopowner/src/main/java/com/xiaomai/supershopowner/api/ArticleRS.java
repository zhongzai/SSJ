package com.xiaomai.supershopowner.api;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
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
	@Autowired
	protected CheckToken checkToken;
	
	@RequestMapping(value="/findArticle" , method = RequestMethod.POST)
	public @ResponseBody String getArticle(HttpServletRequest request){
		RSResult result = new RSResult();
		HashMap<String , Object> map = super.getQueryMap();
		try {
			Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
			List<Article> articleList = articleService.getArticle(map);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(articleList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		} catch (Exception e) {
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
