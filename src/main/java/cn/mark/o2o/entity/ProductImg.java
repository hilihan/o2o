package cn.mark.o2o.entity;

import java.util.Date;

/**
 * 商品详情图实体类
 * @author mark
 */
public class ProductImg {
	private Long productImgId;//主键ID
	private String imgAddr;//图片地址
	private String imgDesc;//图片简介
	private Integer priority;//权重
	private Date createTime;//创建时间
	private Long productId;//标明是属于哪个商品的图片
	
	public Long getProductImgId() {
		return productImgId;
	}
	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
