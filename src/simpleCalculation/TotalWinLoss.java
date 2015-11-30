package simpleCalculation;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class TotalWinLoss {

	private JFrame frame;
	private JTextField oneShare;
	private JTextField oneTotalInv;
	private JTextField oneProfit;
	private JTextField onePreBal;
	private JTextField twoShare;
	private JTextField twoTotalInv;
	private JTextField twoProfit;
	private JTextField twoPreBal;
	private JTextField totalProfit;
	ProfitAndPercentageProfit calculate = new ProfitAndPercentageProfit();
	GetLatestStockQuotes latestQuote = new GetLatestStockQuotes();
	private JTextField totalBal;
	private JTextField sellOne;
	private JTextField buyOne;
	private JTextField buyTwo;
	private JTextField sellTwo;
	private JTextField asOf;
	private JTextField buyThree;
	private JTextField sellThree;
	private JTextField threeShare;
	private JTextField threeTotalInv;
	private JTextField threeProfit;
	private JTextField threePreBal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TotalWinLoss window = new TotalWinLoss();
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
	public TotalWinLoss() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // final calculate

				String currentDate = latestQuote.date("ENPH");
				DecimalFormat df = new DecimalFormat("#.00");
				
				String noOfSharesInput1 = oneShare.getText();
				String noOfSharesInput2 = twoShare.getText();
				String noOfSharesInput3 = threeShare.getText();

				double noOfShares1 = Double.parseDouble(noOfSharesInput1);
				double noOfShares2 = Double.parseDouble(noOfSharesInput2);
				double noOfShares3 = Double.parseDouble(noOfSharesInput3);

				double currentPrice1 = latestQuote.price("ENPH");
				String currentPric1 = df.format(currentPrice1);
				double currentPrice2 = latestQuote.price("EVEP");
				String currentPric2 = df.format(currentPrice2);
				double currentPrice3 = latestQuote.price("NBG");
				String currentPric3 = df.format(currentPrice3);

				double buyingPrice1 = 2.28;
				double buyingPrice2 = 3.64;
				double buyingPrice3 = 0.43;

				double totalInvest1 = buyingPrice1 * noOfShares1;
				String totalInves1 = df.format(totalInvest1);
				double totalInvest2 = buyingPrice2 * noOfShares2;
				String totalInves2 = df.format(totalInvest2);
				double totalInvest3 = buyingPrice3 * noOfShares3;
				String totalInves3 = df.format(totalInvest3);

				double profitAmt1 = calculate.profitAmt(buyingPrice1, currentPrice1, noOfShares1);
				String profitAm1 = df.format(profitAmt1);
				double profitAmt2 = calculate.profitAmt(buyingPrice2, currentPrice2, noOfShares2);
				String profitAm2 = df.format(profitAmt2);
				double profitAmt3 = calculate.profitAmt(buyingPrice3, currentPrice3, noOfShares3);
				String profitAm3 = df.format(profitAmt3);

				double preBal1 = totalInvest1 + profitAmt1;
				String preBa1 = df.format(preBal1);

				double preBal2 = totalInvest2 + profitAmt2;
				String preBa2 = df.format(preBal2);

				double preBal3 = totalInvest3 + profitAmt3;
				String preBa3 = df.format(preBal3);

				double totalProfit = profitAmt1 + profitAmt2 + profitAmt3;
				String totalProfi = df.format(totalProfit);

				double totalBal = preBal1 + preBal2 + preBal3;
				String totalBa = df.format(totalBal);

				oneTotalInv.setText("$" + totalInves1);
				onePreBal.setText("$" + preBa1);

				twoTotalInv.setText("$" + totalInves2);
				twoPreBal.setText("$" + preBa2);

				threeTotalInv.setText("$" + totalInves3);
				threePreBal.setText("$" + preBa3);

				buyOne.setText("2.28");
				buyTwo.setText("3.64");
				buyThree.setText(".43");
				sellOne.setText(currentPric1);
				sellTwo.setText(currentPric2);
				sellThree.setText(currentPric3);

				asOf.setText(currentDate);
				if (profitAmt1 < 0) {
					oneProfit.setForeground(Color.RED);
					oneProfit.setText("$" + profitAm1);
				} else if (profitAmt1 > 0) {
					oneProfit.setForeground(Color.GREEN);
					oneProfit.setText("$" + profitAm1);
				} else if (profitAmt1 == 0) {
					oneProfit.setForeground(Color.BLACK);
					oneProfit.setText("$" + profitAm1);
				}

				if (profitAmt2 < 0) {
					twoProfit.setForeground(Color.RED);
					twoProfit.setText("$" + profitAm2);
				} else if (profitAmt2 > 0) {
					twoProfit.setForeground(Color.GREEN);
					twoProfit.setText("$" + profitAm2);
				} else if (profitAmt2 == 0) {
					twoProfit.setForeground(Color.BLACK);
					twoProfit.setText("$" + profitAm2);
				}

				if (profitAmt3 < 0) {
					threeProfit.setForeground(Color.RED);
					threeProfit.setText("$" + profitAm3);
				} else if (profitAmt3 > 0) {
					threeProfit.setForeground(Color.GREEN);
					threeProfit.setText("$" + profitAm3);
				} else if (profitAmt3 == 0) {
					threeProfit.setForeground(Color.BLACK);
					threeProfit.setText("$" + profitAm3);
				}

				if (totalBal < 0) {
					TotalWinLoss.this.totalBal.setForeground(Color.RED);
					TotalWinLoss.this.totalBal.setText("$" + totalBa);
				} else {
					TotalWinLoss.this.totalBal.setForeground(Color.BLACK);
					TotalWinLoss.this.totalBal.setText("$" + totalBa);
				}

				if (totalProfit < 0) {
					TotalWinLoss.this.totalProfit.setForeground(Color.RED);
					TotalWinLoss.this.totalProfit.setText("$" + totalProfi);
				} else if (totalProfit > 0) {
					TotalWinLoss.this.totalProfit.setForeground(Color.GREEN);
					TotalWinLoss.this.totalProfit.setText("$" + totalProfi);
				} else if (totalProfit == 0) {
					TotalWinLoss.this.totalProfit.setForeground(Color.BLACK);
					TotalWinLoss.this.totalProfit.setText("$" + totalProfi);
				}

			}
		});
		btnNewButton.setBounds(198, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("ENPH");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(40, 50, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblEvep = new JLabel("EVEP");
		lblEvep.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEvep.setBounds(410, 50, 53, 23);
		frame.getContentPane().add(lblEvep);

		JLabel lblShares = new JLabel("Shares");
		lblShares.setBounds(10, 115, 46, 14);
		frame.getContentPane().add(lblShares);

		JLabel lblTotalInvest = new JLabel("Total Invest");
		lblTotalInvest.setBounds(10, 140, 76, 14);
		frame.getContentPane().add(lblTotalInvest);

		JLabel lblProfit = new JLabel("Profit");
		lblProfit.setBounds(10, 165, 76, 14);
		frame.getContentPane().add(lblProfit);

		JLabel lblPreBal = new JLabel("Pre Bal");
		lblPreBal.setBounds(10, 192, 76, 14);
		frame.getContentPane().add(lblPreBal);

		JLabel label = new JLabel("Shares");
		label.setBounds(358, 119, 46, 14);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("Total Invest");
		label_1.setBounds(358, 144, 76, 14);
		frame.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Profit");
		label_2.setBounds(358, 169, 76, 14);
		frame.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("Pre Bal");
		label_3.setBounds(358, 196, 76, 14);
		frame.getContentPane().add(label_3);

		oneShare = new JTextField();
		oneShare.setText("2800");
		oneShare.setBounds(84, 112, 86, 20);
		frame.getContentPane().add(oneShare);
		oneShare.setColumns(10);

		oneTotalInv = new JTextField();
		oneTotalInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		oneTotalInv.setColumns(10);
		oneTotalInv.setBounds(84, 137, 86, 20);
		frame.getContentPane().add(oneTotalInv);

		oneProfit = new JTextField();
		oneProfit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		oneProfit.setColumns(10);
		oneProfit.setBounds(84, 162, 86, 20);
		frame.getContentPane().add(oneProfit);

		onePreBal = new JTextField();
		onePreBal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		onePreBal.setColumns(10);
		onePreBal.setBounds(84, 189, 86, 20);
		frame.getContentPane().add(onePreBal);

		twoShare = new JTextField();
		twoShare.setText("1800");
		twoShare.setColumns(10);
		twoShare.setBounds(432, 115, 86, 20);
		frame.getContentPane().add(twoShare);

		twoTotalInv = new JTextField();
		twoTotalInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		twoTotalInv.setColumns(10);
		twoTotalInv.setBounds(432, 140, 86, 20);
		frame.getContentPane().add(twoTotalInv);

		twoProfit = new JTextField();
		twoProfit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		twoProfit.setColumns(10);
		twoProfit.setBounds(432, 165, 86, 20);
		frame.getContentPane().add(twoProfit);

		twoPreBal = new JTextField();
		twoPreBal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		twoPreBal.setColumns(10);
		twoPreBal.setBounds(432, 192, 86, 20);
		frame.getContentPane().add(twoPreBal);

		JLabel lblTotalProfit = new JLabel("Total Profit");
		lblTotalProfit.setBounds(145, 254, 76, 14);
		frame.getContentPane().add(lblTotalProfit);

		totalProfit = new JTextField();
		totalProfit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		totalProfit.setColumns(10);
		totalProfit.setBounds(219, 251, 94, 20);
		frame.getContentPane().add(totalProfit);

		JLabel lblTotalBal = new JLabel("Total Bal");
		lblTotalBal.setBounds(145, 282, 76, 14);
		frame.getContentPane().add(lblTotalBal);

		totalBal = new JTextField();
		totalBal.setColumns(10);
		totalBal.setBounds(219, 279, 94, 20);
		frame.getContentPane().add(totalBal);

		JLabel lblBuy = new JLabel("Buy");
		lblBuy.setBounds(10, 84, 28, 14);
		frame.getContentPane().add(lblBuy);

		JLabel lblSell = new JLabel("Sell");
		lblSell.setBounds(99, 87, 28, 14);
		frame.getContentPane().add(lblSell);

		sellOne = new JTextField();
		sellOne.setColumns(10);
		sellOne.setBounds(124, 84, 46, 20);
		frame.getContentPane().add(sellOne);

		buyOne = new JTextField();
		buyOne.setColumns(10);
		buyOne.setBounds(40, 81, 46, 20);
		frame.getContentPane().add(buyOne);

		JLabel label_4 = new JLabel("Buy");
		label_4.setBounds(358, 84, 28, 14);
		frame.getContentPane().add(label_4);

		buyTwo = new JTextField();
		buyTwo.setColumns(10);
		buyTwo.setBounds(388, 81, 46, 20);
		frame.getContentPane().add(buyTwo);

		JLabel label_5 = new JLabel("Sell");
		label_5.setBounds(447, 86, 28, 14);
		frame.getContentPane().add(label_5);

		sellTwo = new JTextField();
		sellTwo.setColumns(10);
		sellTwo.setBounds(472, 84, 46, 20);
		frame.getContentPane().add(sellTwo);

		JButton calculator = new JButton("Help?");
		calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // caluclator

				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException e1) {
					e1.getStackTrace();
				}

			}
		});
		calculator.setBounds(402, 278, 66, 23);
		frame.getContentPane().add(calculator);

		JLabel As = new JLabel("As Of :");
		As.setBounds(84, 229, 76, 14);
		frame.getContentPane().add(As);

		asOf = new JTextField();
		asOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		asOf.setColumns(10);
		asOf.setBounds(145, 223, 236, 20);
		frame.getContentPane().add(asOf);

		JLabel lblNbg = new JLabel("NBG");
		lblNbg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNbg.setBounds(232, 50, 53, 23);
		frame.getContentPane().add(lblNbg);

		buyThree = new JTextField();
		buyThree.setColumns(10);
		buyThree.setBounds(210, 81, 46, 20);
		frame.getContentPane().add(buyThree);

		sellThree = new JTextField();
		sellThree.setColumns(10);
		sellThree.setBounds(294, 84, 46, 20);
		frame.getContentPane().add(sellThree);

		JLabel label_7 = new JLabel("Buy");
		label_7.setBounds(180, 84, 28, 14);
		frame.getContentPane().add(label_7);

		JLabel label_8 = new JLabel("Shares");
		label_8.setBounds(180, 119, 46, 14);
		frame.getContentPane().add(label_8);

		threeShare = new JTextField();
		threeShare.setText("697");
		threeShare.setColumns(10);
		threeShare.setBounds(254, 115, 86, 20);
		frame.getContentPane().add(threeShare);

		JLabel label_9 = new JLabel("Sell");
		label_9.setBounds(269, 86, 28, 14);
		frame.getContentPane().add(label_9);

		threeTotalInv = new JTextField();
		threeTotalInv.setColumns(10);
		threeTotalInv.setBounds(254, 140, 86, 20);
		frame.getContentPane().add(threeTotalInv);

		JLabel label_10 = new JLabel("Total Invest");
		label_10.setBounds(180, 144, 76, 14);
		frame.getContentPane().add(label_10);

		threeProfit = new JTextField();
		threeProfit.setColumns(10);
		threeProfit.setBounds(254, 165, 86, 20);
		frame.getContentPane().add(threeProfit);

		JLabel label_11 = new JLabel("Profit");
		label_11.setBounds(180, 169, 76, 14);
		frame.getContentPane().add(label_11);

		JLabel label_12 = new JLabel("Pre Bal");
		label_12.setBounds(180, 196, 76, 14);
		frame.getContentPane().add(label_12);

		threePreBal = new JTextField();
		threePreBal.setColumns(10);
		threePreBal.setBounds(254, 192, 86, 20);
		frame.getContentPane().add(threePreBal);
	}
}
