package com.xiaomai.supershopowner.api;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.StoreDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.UserDto;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.UserTransfer;
import com.xiaomai.supershopowner.entity.Vision;
import com.xiaomai.supershopowner.service.UserService;
import com.xiaomai.supershopowner.service.VisionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/user")
public class UserRS extends BaseRS{
	@Autowired
	public UserService userService;
	@Autowired
	protected CheckToken checkToken;
	
	@Autowired
	public SuperStoreService superStoreService;
	@Autowired
	public VisionService visionService;
	/**
     * 用户登录接口
     * 
     */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody UserTransfer user){
		
		RSResult result = new RSResult();
		try{ 
			UserDto userDto = superStoreService.login(user.getUserAccount(), user.getPassword());
			
			StoreDto  storeDto = superStoreService.getStoreInfo(userDto.getStoreCode());
			
			if(userDto.getCode().equals("1")){
				UserTransfer userTransfer = userService.login(user);
				userTransfer.setAddress(userDto.getAddress());
				userTransfer.setManagerName(userDto.getManagerName());
				userTransfer.setPhone(userDto.getPhone());
				userTransfer.setSex(userDto.getSex());
				userTransfer.setStoreCode(userDto.getStoreCode());
				userTransfer.setStoreName(userDto.getStoreName());
				userTransfer.setLongitude(storeDto==null?null:storeDto.getX());
				userTransfer.setDimension(storeDto==null?null:storeDto.getY());
				userTransfer.setUserAccount(user.getUserAccount());
				userTransfer.setDoorStatus(userDto.getDoorStatus());
				result.setCode("200");
				result.setMsg("Success");
				result.setResult(userTransfer);
			}else{
				result.setCode("202");
				result.setMsg("Fail");
				result.setResult(null);
			}
		}catch(Exception ex){
			ex.printStackTrace();
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
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
				result.setMsg("success");
				result.setResult(tokens);
		}catch(Exception ex){
				result.setCode("201");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/**
     * 版本控制接口
     * 
     */
	@RequestMapping(value="/visionControl",method = RequestMethod.POST)
	public @ResponseBody String visionControl(HttpServletRequest request,@RequestParam String visionCode){
		RSResult result = new RSResult();
		try{ 
			 Boolean res=checkToken.check(request.getHeader("token"));
			 if(res==true){
			Vision vision = visionService.findNewVision(visionCode);
				result.setCode("200");
				result.setMsg("success");
				result.setResult(vision);
			 }else{
					result.setCode("201");
					result.setMsg("token失效！");
					result.setResult(null);
				}
		}catch(Exception ex){
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
