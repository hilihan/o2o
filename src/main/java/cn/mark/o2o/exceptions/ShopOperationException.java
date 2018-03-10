package cn.mark.o2o.exceptions;

public class ShopOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 265640648910088072L;

	public ShopOperationException(String msg) {
		super(msg);
	}
}
