package cn.mark.o2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException {
	private static final long serialVersionUID = 4593690287628430891L;

	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
