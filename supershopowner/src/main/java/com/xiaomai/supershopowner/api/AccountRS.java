package com.xiaomai.supershopowner.api;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imxiaomai.member.dto.Pager;
import com.imxiaomai.member.po.Member;
import com.imxiaomai.member.po.MemberBalanceLog;
import com.imxiaomai.member.service.MemberService;
import com.xiaomai.mall.scan.client.service.RechargeDubboService;
import com.xiaomai.mall.scan.client.vo.InvoiceVo;
import com.xiaomai.mall.scan.client.vo.RechargeVo;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.Account;
import com.xiaomai.supershopowner.entity.Flow;
import com.xiaomai.supershopowner.entity.RechargeTransfer;

@Controller
@RequestMapping(value="/account")
public class AccountRS extends BaseRS{
	
	@Autowired
	public MemberService memberService;
	@Autowired
	protected CheckToken checkToken;
	@Autowired
	protected RechargeDubboService rechargeDubboService;
	
	/**
     * 账户余额接口
     * 
     */
	@RequestMapping(value="/balance",method = RequestMethod.POST)
	public @ResponseBody String balance(HttpServletRequest request,@RequestBody Account account){
		
		RSResult result = new RSResult();
		try{ 
            Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
			
			Member member = memberService.getMemberInfo(account.getPhone(), null);
			if(member!=null){
				account.setBalance(member.getBalance());
			}
				result.setCode("200");
				result.setMsg("success");
				result.setResult(account);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
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
     * 账户交易流水
     * 
     */
	@RequestMapping(value="/flow",method = RequestMethod.POST)
	public @ResponseBody String flowList(HttpServletRequest request,@RequestBody Flow flow){
		
		RSResult result = new RSResult();
		try{ 
            Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
				
			MemberBalanceLog log = new 	MemberBalanceLog();
			log.setMobile(flow.getPhone());
			
			Pager<MemberBalanceLog> pager = memberService.getMemberBalanceLogPageByExample(log, Integer.valueOf(request.getHeader("pageNum")), Integer.valueOf(request.getHeader("pageSize")));
			
			List<MemberBalanceLog> balanceLogs= pager.getResult();
			
			List<Flow> flowList =new  ArrayList<>();
			
			for(MemberBalanceLog logs : balanceLogs){
				Flow flows = new Flow();
				if(Integer.valueOf(logs.getLogType())==1302){
					flows.setAmount(logs.getPayBalance());
				}else{
					flows.setAmount(logs.getBalance());
				}
				flows.setFlowCode(logs.getPayNo());
				flows.setFlowTime(logs.getPayTime());
				flows.setType(Integer.valueOf(logs.getLogType()));
				
				flowList.add(flows);
			}
				result.setCode("200");
				result.setMsg("success");
				result.setResult(flowList);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
			ex.printStackTrace();
				result.setCode("400");
				result.setMsg("Fail");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
	
	/**
     * 账户充值接口
     * 
     */
	@RequestMapping(value="/recharge",method = RequestMethod.POST)
	public @ResponseBody String recharge(HttpServletRequest request,@RequestBody RechargeTransfer recharge){
		
		RSResult result = new RSResult();
		try{ 
            Boolean res=checkToken.check(request.getHeader("token"));
			
			if(res==true){
				
				RechargeVo rechargeVo = new RechargeVo();
				
				rechargeVo.setMemberMobile(recharge.getMemberMobile());
				rechargeVo.setPayType(1);
				rechargeVo.setRechargeAmount(recharge.getRechargeAmount());
				rechargeVo.setShopCode(recharge.getStoreCode());
				rechargeVo.setInvoice(new InvoiceVo());
				rechargeVo.setPlatform("SH");
			
			    String parms = rechargeDubboService.createRechargeOrder(rechargeVo);
				result.setCode("200");
				result.setMsg("success");
				result.setResult(parms);
			}else{
				result.setCode("201");
				result.setMsg("token失效！");
				result.setResult(null);
			}
		}catch(Exception ex){
			ex.printStackTrace();
				result.setCode("400");
				result.setMsg("会员不存在，无法充值");
				result.setResult(null);	
		}
		return JSONObject.fromObject(result,JSONObjectConfig.getTime()).toString();
	}
}
