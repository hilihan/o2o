package cn.mark.o2o.entity;

import java.util.Date;

/**
 * 微信登录实体类
 * @author mark
 */
public class WechatAuth{
	private Long wechatAuthId;//主键ID
	private String openId;//微信获取用户信息的凭证，对于某个公众号具有唯一性
	private PersonInfo personInfo;//用户信息
	private Date createTime;//创建时间
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
}
