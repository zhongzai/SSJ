package com.xiaomai.supershopowner.api;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.BaseDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.LossReportDto;
import com.imxiaomai.shop.web.superStoreDubbo.domain.Pager;
import com.xiaomai.supershopowner.common.CheckToken;
import com.xiaomai.supershopowner.common.JSONObjectConfig;
import com.xiaomai.supershopowner.common.RSResult;
import com.xiaomai.supershopowner.entity.LossTransfer;

@RestController
@RequestMapping(value = "loss")
public class LossRS extends BaseRS {

	private org.slf4j.Logger log = LoggerFactory.getLogger(OrderRS.class);

	@Autowired
	protected CheckToken checkToken;

	@Autowired
	SuperStoreService superStoreService;

	// 损耗单列表
	@RequestMapping(value = "getLossByStoreCode", method = RequestMethod.POST)
	public String getLossByStoreCode(
			@RequestParam(value = "storeCode", required = false) String storeCode) {
		RSResult rr = new RSResult();
		Pager<LossReportDto> ld =null;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("called getLossByStoreCode starting...");
				ld = superStoreService.getLossRepootListByShopcode(
						storeCode,
						null == request.getHeader("pageNum") ? 1 : Integer
								.valueOf(request.getHeader("pageNum")),
						null == request.getHeader("pageSize") ? 10 : Integer
								.valueOf(request.getHeader("pageSize")));
				rr.setCode("200");
				rr.setMsg("查询损耗单列表成功");
				rr.setResult(ld);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called getLossByStoreCode failure", e);
			rr.setCode("400");
			rr.setMsg("查询损耗单列表失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getTime())
				.toString();
	}

	// 损耗单详情
	@RequestMapping(value = "getLossGoods", method = RequestMethod.POST)
	public String getLossGoods(
			@RequestParam(value = "lossCode", required = false) String lossCode) {
		RSResult rr = new RSResult();
		DecimalFormat df = new DecimalFormat("0.00");
		LossTransfer ltf = new LossTransfer();
		List<LossReportDto> ld = new ArrayList<LossReportDto>();
		Double lossTotalAmount = 0.0;
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("called getLossGoods starting...");
				ld = superStoreService.getLossRepootDtoByLossReportNo(lossCode);
				for (LossReportDto lrd : ld) {
					lossTotalAmount+=lrd.getLossAmount().doubleValue();
				}
				ltf.setLossReportDto(ld);
				ltf.setLossTotalAmount(String.valueOf(df.format(lossTotalAmount)));
				log.debug("called getLossGoods end");
				rr.setCode("200");
				rr.setMsg("查询损耗详情成功");
				rr.setResult(ltf);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			log.error("called getLossGoods failure", e);
			rr.setCode("400");
			rr.setMsg("查询损耗详情失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getTime())
				.toString();
	}

	// 新建损耗单
	@RequestMapping(value = "addloss", method = RequestMethod.POST)
	public String inserLoss(@RequestBody LossTransfer lossTransfer) {
		RSResult rr = new RSResult();
		BaseDto baseDto = new BaseDto();
		Boolean res;
		try {
			res = checkToken.check(request.getHeader("token"));
			if (res == true) {
				log.debug("called inser loss starting...");
				baseDto = superStoreService.addLossReport(lossTransfer.getLossReportDetailDto());
				rr.setCode("200");
				rr.setMsg("插入损耗单成功");
				rr.setResult(baseDto);
			} else {
				rr.setCode("201");
				rr.setMsg("token失效！");
				rr.setResult(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("called inser loss failure", e);
			rr.setCode("400");
			rr.setMsg("增加损耗单失败");
			rr.setResult(null);
		}
		return JSONObject.fromObject(rr, JSONObjectConfig.getInstance())
				.toString();
	}

}
