package com.xiaomai.supershopowner.api;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.User;
import com.xiaomai.supershopowner.service.UserService;

@Controller
@RequestMapping("/user")
public class UserRS extends BaseRS{
    @Autowired
    private UserService userService;
    
    
    
    @RequestMapping(value = "/findUser",method = RequestMethod.POST)
	public @ResponseBody String getUserByName(){
		RSResult result = new RSResult();
		try{
			User user = userService.findUserByName("15321990260");
			result.setCode("200");
			result.setMsg("Success");
			result.setResult(user);
			
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
