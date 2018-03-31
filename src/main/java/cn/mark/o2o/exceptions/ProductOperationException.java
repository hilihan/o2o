package cn.mark.o2o.exceptions;

public class ProductOperationException extends RuntimeException {

	private static final long serialVersionUID = -7164027322225709306L;

	public ProductOperationException(String msg) {
		super(msg);
	}
}
