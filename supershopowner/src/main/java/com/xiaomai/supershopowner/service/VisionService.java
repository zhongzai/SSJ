package com.xiaomai.supershopowner.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaomai.supershopowner.dao.VisionDao;
import com.xiaomai.supershopowner.entity.Vision;

@Service
public class VisionService implements BaseService<Vision, Integer>{
	@Resource
	public VisionDao visionDao;
	
	public Vision findNewVision(String visonCode) throws SQLException {
		return visionDao.findNewVision(visonCode);
	}

	@Override
	public Integer insert(Vision t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Vision t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vision findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vision> findListAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vision> findListAllWithMap(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsEntity(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
