package cn.mark.o2o.service.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.mark.o2o.service.ProductSellDailyService;

public class ProductSellDailyJob extends QuartzJobBean{
	
	private ProductSellDailyService productSellDailyService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		productSellDailyService.dailyCalculate();
		
	}

	public void setProductSellDailyService(ProductSellDailyService productSellDailyService) {
		this.productSellDailyService = productSellDailyService;
	}
	
}
