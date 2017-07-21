package com.xiaomai.supershopowner.api;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.UserTransfer;
import com.xiaomai.supershopowner.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserRS extends BaseRS{
	@Autowired
	public UserService userService;
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody UserTransfer user){
		
		RSResult result = new RSResult();
		try{ 
			
			UserTransfer userTransfer = userService.login(user);
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(userTransfer);
			
		}catch(Exception ex){
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
