package com.xiaomai.supershopowner.api;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.UserDto;
import com.xiaomai.supershopowner.common.BizErr;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.UserTransfer;
import com.xiaomai.supershopowner.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserRS extends BaseRS{
	@Autowired
	public UserService userService;
	
	@Autowired
	public SuperStoreService superStoreService;
	/**
     * 用户登录接口
     * 
     */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody UserTransfer user){
		
		RSResult result = new RSResult();
		try{ 
			UserDto userDto = superStoreService.login(user.getUserAccount(), user.getPassword());
			
			if(userDto.getCode().equals("1")){
				UserTransfer userTransfer = userService.login(user);
				userTransfer.setAddress(userDto.getAddress());
				userTransfer.setManagerName(userDto.getManagerName());
				userTransfer.setPhone(userDto.getPhone());
				userTransfer.setSex(userDto.getSex());
				userTransfer.setStoreCode(userDto.getStoreCode());
				userTransfer.setStoreName(userDto.getStoreName());
				result.setCode("200");
				result.setMsg("Success");
				result.setResult(userTransfer);
			}else{
				result.setCode("201");
				result.setMsg("Fail");
				result.setResult(null);
			}
		}catch(Exception ex){
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
     * 根据token查询用户 
     * 
     */
	@RequestMapping(value="/checkToken",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestParam String token){
		RSResult result = new RSResult();
		try{ 
			Boolean tokens = userService.findUserByToken(token);
				result.setCode("200");
				result.setMsg("Fail");
				result.setResult(tokens);
		}catch(Exception ex){
			if(BizErr.EX_UPDATE_FAIL.equals(ex.getMessage())){
				result.setCode("201");
				result.setMsg("Fail");
				result.setResult(null);	
			}
		}
		return JSONObject.fromObject(result).toString();
	}
}
