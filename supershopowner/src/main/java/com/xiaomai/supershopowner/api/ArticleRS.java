package com.xiaomai.supershopowner.api;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.CheckToken;
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
	public @ResponseBody String getArticle(HttpServletRequest request,@RequestBody Article article){
		String token = request.getHeader("token");
		RSResult result = new RSResult();
		HashMap<String , Object> map = super.getQueryMap();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
		map.put("createTime", format.format(article.getCreateTime()));
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
			if(BizErr.EX_UPDATE_FAIL.equals(e.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
