package com.xiaomai.supershopowner.api;
import java.util.List;

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
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Flow;
import com.xiaomai.supershopowner.service.FlowService;

@Controller
@RequestMapping(value="/userFlow")
public class UserFlowRS extends BaseRS{
	@Autowired
	public FlowService flowService;
	
	@Autowired
	protected CheckToken checkToken;
	/**
     * 用户流水接口
     * 
     */
	@RequestMapping(value="/list",method = RequestMethod.POST)
	public @ResponseBody String list(HttpServletRequest request,@RequestBody Flow flow){
		
		RSResult result = new RSResult();
		try{ 
            Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
			
			List<Flow> flowList = flowService.findByStoreCode(flow.getStoreCode());
				result.setCode("200");
				result.setMsg("Fail");
				result.setResult(flowList);
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
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
