package cn.mark.o2o.entity;

import java.util.Date;

/**
 * 区域实体类
 * @author mark
 *
 */
public class Area{
	private Integer areaId;//ID
	private String areaName;//名称
	private Integer priority;//权重,越大越排前显示
	private Date createTime;//创建时间
	private Date lastEditTime;//更新时间
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
