package cn.mark.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mark.o2o.dao.HeadLineDao;
import cn.mark.o2o.entity.HeadLine;
import cn.mark.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	
	@Autowired HeadLineDao headLineDao;

	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
		return headLineDao.queryHeadLine(headLineCondition);
	}

}
