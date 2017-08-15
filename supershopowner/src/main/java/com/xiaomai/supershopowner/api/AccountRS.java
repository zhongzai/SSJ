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
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Account;
import com.xiaomai.supershopowner.service.AccountService;
import com.xiaomai.supershopowner.service.UserService;

@Controller
@RequestMapping(value="/account")
public class AccountRS extends BaseRS{
	
	@Autowired
	public AccountService accountService;
	@Autowired
	protected CheckToken checkToken;
	/**
     * 账户余额接口
     * 
     */
	@RequestMapping(value="/balance",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody Account account){
		
		RSResult result = new RSResult();
		try{ 
            Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
			
			Account accounts = accountService.findByUserAccount(account.getUserAccount());
				result.setCode("201");
				result.setMsg("Fail");
				result.setResult(accounts);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
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
}
