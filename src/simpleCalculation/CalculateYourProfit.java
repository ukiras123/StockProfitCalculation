package simpleCalculation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class CalculateYourProfit {

	private JFrame frame;
	private JTextField invest;
	private JTextField buyShare;
	private JTextField sellShare;
	private JTextField totalShares;
	private JTextField profit;
	private JTextField profitPercentage;
	ProfitAndPercentageProfit calculate = new ProfitAndPercentageProfit();
	private JTextField totalBalance;

	Font f = new Font("Arial", Font.BOLD, 11); // Font

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculateYourProfit window = new CalculateYourProfit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculateYourProfit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Calculate your Profit");
		frame.setBounds(100, 100, 373, 288);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						//Calculate
				
				String noOfSharesInput = totalShares.getText();
				double noOfShares = Double.parseDouble(noOfSharesInput);  //invest
				String buyingPriceInput = buyShare.getText();
				double buyingPrice = Double.parseDouble(buyingPriceInput);  //buy price per share
				String sellignPriceInput = sellShare.getText();
				double sellignPrice = Double.parseDouble(sellignPriceInput);  //sell price per share
				
				double profitAmt =  calculate.profitAmt(buyingPrice, sellignPrice,noOfShares);
				double totalInvest = calculate.totalInvest(buyingPrice, noOfShares);
				double profitPer =  calculate.profitPer(totalInvest,profitAmt);
				double balance = calculate.totalBalance(totalInvest, profitAmt);
				DecimalFormat df = new DecimalFormat("#.00");
				
			    String profitAmount = df.format(profitAmt);
			    String profitPercent = df.format(profitPer);
			    String totalInvestAmount = df.format(totalInvest);
			    String totalBal = df.format(balance);  
			    invest.setText("$"+totalInvestAmount);
			    
			    if(profitPer<0)
				{
			    	profitPercentage.setForeground(Color.RED);
				    profitPercentage.setText(profitPercent+"%");

				}
				else
				{
					profitPercentage.setForeground(Color.BLACK);
				    profitPercentage.setText(profitPercent+"%");
				}
			    
			    if(profitAmt<0)
				{
					profit.setForeground(Color.RED);
				    profit.setText("$"+profitAmount);

				}
				else
				{
					profit.setForeground(Color.BLACK);
				    profit.setText("$"+profitAmount);
				}
			    
			    
			    if(balance<totalInvest){
			    	totalBalance.setForeground(Color.RED);
				    totalBalance.setText("$"+totalBal);
			    }
			    else 
			    {
			    	totalBalance.setForeground(Color.BLACK);
				    totalBalance.setText("$"+totalBal);

			    }
			    	
			}
		});
		btnCalculate.setBounds(44, 104, 89, 23);
		frame.getContentPane().add(btnCalculate);
		
		JLabel lblBuy = new JLabel("Total Investment");
		lblBuy.setBounds(44, 133, 132, 14);
		frame.getContentPane().add(lblBuy);
		
		invest = new JTextField();
		invest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //invest amoutn
			}
		});
		invest.setBounds(193, 133, 102, 20);
		frame.getContentPane().add(invest);
		invest.setColumns(10);
		
		JLabel lblSell = new JLabel("Buying Price per Share");
		lblSell.setBounds(44, 52, 139, 14);
		frame.getContentPane().add(lblSell);
		
		buyShare = new JTextField();
		buyShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //buying price per share
			}
		});
		buyShare.setColumns(10);
		buyShare.setBounds(193, 52, 102, 20);
		frame.getContentPane().add(buyShare);
		
		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setBounds(44, 158, 61, 14);
		frame.getContentPane().add(lblProfit);
		
		profit = new JTextField();
		profit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   //profit
			}
		});
		profit.setColumns(10);
		profit.setBounds(193, 158, 102, 20);
		frame.getContentPane().add(profit);
		
		JLabel lblProfitPercentage = new JLabel("Profit %");
		lblProfitPercentage.setBounds(44, 184, 61, 14);
		frame.getContentPane().add(lblProfitPercentage);
		
		profitPercentage = new JTextField();
		profitPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {      //profit percentage
				
			}
		});
		profitPercentage.setColumns(10);
		profitPercentage.setBounds(193, 183, 102, 20);
		frame.getContentPane().add(profitPercentage);
		
		JLabel lblSellingPricePer = new JLabel("Selling Price per Share");
		lblSellingPricePer.setBounds(44, 79, 139, 14);
		frame.getContentPane().add(lblSellingPricePer);
		
		sellShare = new JTextField();
		sellShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     //Selling price per share
			}
		});
		sellShare.setColumns(10);
		sellShare.setBounds(193, 79, 102, 20);
		frame.getContentPane().add(sellShare);
		
		JLabel lblTotalSharesBought = new JLabel("Total Shares");
		lblTotalSharesBought.setBounds(44, 24, 139, 14);
		frame.getContentPane().add(lblTotalSharesBought);
		
		totalShares = new JTextField();
		totalShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {     //Total Shares bought
			}
		});
		totalShares.setColumns(10);
		totalShares.setBounds(193, 21, 102, 20);
		frame.getContentPane().add(totalShares);
		
		JLabel lblYourTotalBalance = new JLabel("Your Total Balance");
		lblYourTotalBalance.setBounds(44, 214, 118, 14);
		frame.getContentPane().add(lblYourTotalBalance);
		
		totalBalance = new JTextField();
		totalBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {    //total balance
			}
		});
		totalBalance.setColumns(10);
		totalBalance.setBounds(193, 211, 102, 20);
		frame.getContentPane().add(totalBalance);
	}
}
