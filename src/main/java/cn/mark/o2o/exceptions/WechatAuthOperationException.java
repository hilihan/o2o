package cn.mark.o2o.exceptions;

public class WechatAuthOperationException extends RuntimeException {

	private static final long serialVersionUID = -7952629412206509959L;
	
	public WechatAuthOperationException(String msg) {
		super(msg);
	}
	
}
