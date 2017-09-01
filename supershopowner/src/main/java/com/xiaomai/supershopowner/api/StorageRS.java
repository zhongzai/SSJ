package com.xiaomai.supershopowner.api;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.StorageTransfer;
import com.xiaomai.supershopowner.service.StorageService;

import net.sf.json.JSONObject;


@RestController
@RequestMapping(value="storage")
public class StorageRS  extends BaseRS {
	
	private org.slf4j.Logger log = LoggerFactory.getLogger(StorageRS.class);
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	protected CheckToken checkToken;
	
	//查询库存
	@RequestMapping(value="findStorageByCode", method = RequestMethod.POST)
	public String findStorageByCode(HttpServletRequest request,@RequestParam(value="storeCode",required=false) String storeCode){
		RSResult rr = new RSResult();
		StorageTransfer storageTransfer=null;
		try{
			Boolean res = checkToken.check(request.getHeader("token"));
			if(res == true){
				log.debug("call the findStorageByCodeRS...");
				storageTransfer = storageService.findStorageByCode(storeCode);
				rr.setCode("200");
				rr.setMsg("查询库存成功");
				rr.setResult(storageTransfer);
			}else{
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		}
		catch(Exception e){
			log.error("called orderRS failure", e);
			rr.setCode("400");
			rr.setMsg("查询库存失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr).toString();
	}

}
