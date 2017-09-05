package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.imxiaomai.shop.web.superStoreDubbo.SuperStoreService;
import com.imxiaomai.shop.web.superStoreDubbo.domain.MemberDto;
import com.xiaomai.supershopowner.dao.CustDao;
import com.xiaomai.supershopowner.entity.Cust;
import com.xiaomai.supershopowner.entity.CustTransfer;

@Service
public class CustService  implements BaseService<Cust, Integer>{
	@Resource
	public CustDao custDao;
	@Resource
	public SuperStoreService superStoreService;
	public CustTransfer getfindComingTime(HashMap<String,Object> map){
		CustTransfer custTransfer = new CustTransfer();
		try {
			List<Cust>  list = custDao.findList(map);
			Integer custNumber = custDao.findListCount(map);
			List<Cust> listNew = new ArrayList<Cust>();
			custTransfer.setCustList(list);
			custTransfer.setCustNumber(custNumber);
			MemberDto memberDto = new MemberDto();

			if(list.size()!=0){
				for(Cust cust : list){
					memberDto  =	superStoreService.getMemberById(Integer.parseInt(cust.getCustId()));
					cust.setCustSex(memberDto==null?null:memberDto.getCustSex());
					cust.setCustPhone(memberDto==null?null:memberDto.getCustPhone());
					cust.setCustName(memberDto==null?null:memberDto.getCustName());
					cust.setStoredValue(memberDto==null?false:memberDto.isStoredValue());
					listNew.add(cust);
					custTransfer.setCustList(listNew);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return custTransfer;
	}
	@Override
	public Integer insert(Cust t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Cust t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cust findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cust> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cust> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
}
