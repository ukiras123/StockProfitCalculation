package simpleCalculation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalculateYourProfit {

	private JFrame frame;
	private JTextField invest;
	private JTextField buyShare;
	private JTextField sellShare;
	private JTextField totalShares;
	private JTextField profit;
	private JTextField profitPercentage;
	ProfitAndPercentageProfit calculate = new ProfitAndPercentageProfit();
	GetLatestStockQuotes latestQuote = new GetLatestStockQuotes();
	private JTextField totalBalance;

	Font f = new Font("Arial", Font.BOLD, 11); // Font
	private JTextField symbol;
	private JTextField currentTime;

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
		frame.setBounds(100, 100, 546, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Calculate

				if ((totalShares.getText().equals("") || buyShare.getText().equals(""))
						|| ((sellShare.getText().equals("") && symbol.getText().equals("")))) {
					currentTime.setBackground(Color.RED);
					invest.setText(null);
					profit.setText(null);
					profitPercentage.setText(null);
					totalBalance.setText(null);
				} else {
					currentTime.setBackground(Color.WHITE);
					if (!symbol.getText().equals("")) {
						Double currentPrice = latestQuote.price(symbol.getText().toUpperCase());
						String currentDate = latestQuote.date(symbol.getText().toUpperCase());
						String curPrice = Double.toString(currentPrice);
						sellShare.setText(curPrice);
						currentTime.setBackground(Color.WHITE);
						currentTime.setText(currentDate);
					}
					String sellignPriceInput = sellShare.getText();
					double sellignPrice = Double.parseDouble(sellignPriceInput); // sell
																					// price
																					// per
																					// share
					String noOfSharesInput = totalShares.getText();
					double noOfShares = Double.parseDouble(noOfSharesInput); // invest
					String buyingPriceInput = buyShare.getText();
					double buyingPrice = Double.parseDouble(buyingPriceInput); // buy
																				// price
																				// per
																				// share

					double profitAmt = calculate.profitAmt(buyingPrice, sellignPrice, noOfShares);
					double totalInvest = calculate.totalInvest(buyingPrice, noOfShares);
					double profitPer = calculate.profitPer(totalInvest, profitAmt);
					double balance = calculate.totalBalance(totalInvest, profitAmt);
					DecimalFormat df = new DecimalFormat("#.00");

					String profitAmount = df.format(profitAmt);
					String profitPercent = df.format(profitPer);
					String totalInvestAmount = df.format(totalInvest);
					String totalBal = df.format(balance);
					invest.setText("$" + totalInvestAmount);

					if (profitPer < 0) {
						profitPercentage.setForeground(Color.RED);
						profitPercentage.setText(profitPercent + "%");

					} else {
						profitPercentage.setForeground(Color.BLACK);
						profitPercentage.setText(profitPercent + "%");
					}

					if (profitAmt < 0) {
						profit.setForeground(Color.RED);
						profit.setText("$" + profitAmount);

					} else {
						profit.setForeground(Color.BLACK);
						profit.setText("$" + profitAmount);
					}

					if (balance < totalInvest) {
						totalBalance.setForeground(Color.RED);
						totalBalance.setText("$" + totalBal);
					} else {
						totalBalance.setForeground(Color.BLACK);
						totalBalance.setText("$" + totalBal);

					}
				}

			}
		});
		btnCalculate.setBounds(44, 104, 89, 23);
		frame.getContentPane().add(btnCalculate);

		JLabel lblBuy = new JLabel("Total Investment");
		lblBuy.setBounds(44, 163, 132, 14);
		frame.getContentPane().add(lblBuy);

		invest = new JTextField();
		invest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // invest amoutn
			}
		});
		invest.setBounds(193, 163, 102, 20);
		frame.getContentPane().add(invest);
		invest.setColumns(10);

		JLabel lblSell = new JLabel("Buying Price per Share *");
		lblSell.setBounds(44, 52, 139, 14);
		frame.getContentPane().add(lblSell);

		buyShare = new JTextField();
		buyShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // buying price per
															// share
			}
		});
		buyShare.setColumns(10);
		buyShare.setBounds(193, 52, 102, 20);
		frame.getContentPane().add(buyShare);

		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setBounds(44, 188, 61, 14);
		frame.getContentPane().add(lblProfit);

		profit = new JTextField();
		profit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // profit
			}
		});
		profit.setColumns(10);
		profit.setBounds(193, 188, 102, 20);
		frame.getContentPane().add(profit);

		JLabel lblProfitPercentage = new JLabel("Profit %");
		lblProfitPercentage.setBounds(44, 214, 61, 14);
		frame.getContentPane().add(lblProfitPercentage);

		profitPercentage = new JTextField();
		profitPercentage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // profit percentage

			}
		});
		profitPercentage.setColumns(10);
		profitPercentage.setBounds(193, 213, 102, 20);
		frame.getContentPane().add(profitPercentage);

		JLabel lblSellingPricePer = new JLabel("Selling Price per Share");
		lblSellingPricePer.setBounds(44, 79, 139, 14);
		frame.getContentPane().add(lblSellingPricePer);

		sellShare = new JTextField();
		sellShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Selling price per
															// share
			}
		});
		sellShare.setColumns(10);
		sellShare.setBounds(193, 79, 102, 20);
		frame.getContentPane().add(sellShare);

		JLabel lblTotalSharesBought = new JLabel("Total Shares *");
		lblTotalSharesBought.setBounds(44, 24, 139, 14);
		frame.getContentPane().add(lblTotalSharesBought);

		totalShares = new JTextField();
		totalShares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Total Shares bought
			}
		});
		totalShares.setColumns(10);
		totalShares.setBounds(193, 21, 102, 20);
		frame.getContentPane().add(totalShares);

		JLabel lblYourTotalBalance = new JLabel("Your Total Balance");
		lblYourTotalBalance.setBounds(44, 244, 118, 14);
		frame.getContentPane().add(lblYourTotalBalance);

		totalBalance = new JTextField();
		totalBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // total balance
			}
		});
		totalBalance.setColumns(10);
		totalBalance.setBounds(193, 241, 102, 20);
		frame.getContentPane().add(totalBalance);

		JLabel lblStockTickerSymbol = new JLabel("Just give Stock Ticker Symbol");
		lblStockTickerSymbol.setBounds(340, 52, 198, 14);
		frame.getContentPane().add(lblStockTickerSymbol);

		symbol = new JTextField();
		symbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // Getting Symbol
			}
		});
		symbol.setColumns(10);
		symbol.setBounds(340, 76, 102, 20);
		frame.getContentPane().add(symbol);

		JLabel lblNewLabel = new JLabel("Wanna get the latest quote?");
		lblNewLabel.setBounds(340, 39, 198, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("As Of:");
		lblNewLabel_1.setBounds(44, 138, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		currentTime = new JTextField();
		currentTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // current time
			}
		});
		currentTime.setColumns(10);
		currentTime.setBounds(87, 135, 208, 20);
		frame.getContentPane().add(currentTime);

		JButton btnCalculator = new JButton("Need a Calculator");
		btnCalculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // calculator
				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException e) {
					e.getStackTrace();
				}

			}
		});
		btnCalculator.setBounds(340, 134, 139, 23);
		frame.getContentPane().add(btnCalculator);
	}
}
