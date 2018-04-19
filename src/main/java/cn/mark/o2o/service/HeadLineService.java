package cn.mark.o2o.service;

import java.io.IOException;
import java.util.List;

import cn.mark.o2o.entity.HeadLine;

public interface HeadLineService {
	/**
	 * 根据传入的条件返回指定的头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
