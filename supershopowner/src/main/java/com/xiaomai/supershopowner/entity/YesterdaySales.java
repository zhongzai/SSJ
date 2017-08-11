package com.xiaomai.supershopowner.entity;
import com.xiaomai.supershopowner.entity.Loss;
import com.xiaomai.supershopowner.entity.Sale;
public class YesterdaySales {
	private Loss loss;
	
	private Sale sale;

	public Loss getLoss() {
		return loss;
	}

	public void setLoss(Loss loss) {
		this.loss = loss;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
}
