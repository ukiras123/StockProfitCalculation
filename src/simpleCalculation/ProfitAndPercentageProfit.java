package simpleCalculation;

import java.text.DecimalFormat;

public class ProfitAndPercentageProfit {

	public  double profitPer(double invest, double profit)
	{
		double percentage = (profit / invest ) * 100;
		return percentage;
	}
	
	public  double profitAmt(double buyingPrice, double sellingPrice, double noOfShares)
	{
		double profit = noOfShares * (sellingPrice - buyingPrice);
		return profit;
	}
	
	public  double totalInvest(double buyingPrice, double noOfShares)
	{
		double totalInvestment = buyingPrice * noOfShares;
		return totalInvestment;
	}

	public  double totalBalance(double invest, double profit)
	{
		double totalBalance = invest + profit;
		return totalBalance;
	}
	
}
