
package simpleCalculation;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetLatestStockQuotes {

	// Given symbol, get HTML
	private String readHTML(String symbol) {
		String content = null;
		URLConnection connection = null;
		try {
			connection = new URL("http://finance.yahoo.com/q?s=" + symbol).openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter("\\Z");
			content = scanner.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}

	// Given symbol, get current stock price.
	public double price(String symbol) {
		String html = readHTML(symbol);
		int p = html.indexOf("yfs_l84", 0); // "yfs_l84" index
		int from = html.indexOf(">", p); // ">" index
		int to = html.indexOf("</span>", from); // "</span>" index
		String price = html.substring(from + 1, to);
		if (price.matches("(1|2|3|4|5|6|7|8|9|0).*"))
		{
		return Double.parseDouble(price.replaceAll(",", ""));
		}
		else
		{
			return 0.00;
		}
	}

	// Given symbol, get current stock name.
	public String name(String symbol) {
		String html = readHTML(symbol);
		int p = html.indexOf("<title>", 0);
		int from = html.indexOf("Summary for ", p);
		int to = html.indexOf("- Yahoo! Finance", from);
		String name = html.substring(from + 12, to);
		return name;
	}

	// Given symbol, get current date.
	public String date(String symbol) {
		String html = readHTML(symbol);
		int p = html.indexOf("<span id=\"yfs_market_time\">", 0);
		int from = html.indexOf(">", p);
		int to = html.indexOf("-", from); // no closing small tag
		String date = html.substring(from + 1, to);
		return date;
	}

}
