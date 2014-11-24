package jp.attacklist.batch;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seasar.framework.util.tiger.Pair;

/**
 * anをクローリングします
 * @author hitoshi_masuzawa
 */
public class AnCrawler extends Crawler {

	/**
	 * @param url クロールの起点となるURL
	 * @param includeFilterRegex クロール対象URLのフィルタリング(正規表現)
	 * @param excludeFilterRegex クロール除外URLのフィルタリング(正規表現)
	 * @param detailMatchRegex 企業情報の詳細ページにマッチするURL(正規表現)
	 */
	public AnCrawler(String url, String includeFilterRegex, String excludeFilterRegex, String detailMatchRegex) {
		super(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
	}

	@Override
	protected String getPagingUrl(int pagingNumber) {
		return url + "/" + String.valueOf(pagingNumber);
	}

	@Override
	protected String getMediaName() {
		return "an";
	}

	@Override
	protected String getBodyText(Document document){
		Element body = document.body();
		return body.text();
	}

	@Override
	protected int getMaxPageNumber(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		Elements elements = document.select(".paging_paging ._walink_webanpaging");
		Element elm = elements.last();
		int maxPageNum = Integer.parseInt(elm.text());
		return maxPageNum;
	}

	@Override
	protected String getCompanyName(Document document) {
		String conpanyName = "";
		Elements elements = document.select(".pageTitle");
		for (Element elm : elements) {
			if (elm.text().matches(".*の求人情報$")) {
				StringBuilder tmpConpanyName = new StringBuilder(elm.text());
				conpanyName =
						tmpConpanyName.delete(tmpConpanyName.lastIndexOf("の求人情報"), tmpConpanyName.length())
								.toString();
				break;
			}
		}
		return conpanyName;
	}

	@Override
	protected String getCompanyNameKana(Document document) {
		String conpanyNameKana = "";
		Elements elements = document.select("p.ruby");
		Element element = elements.first();
		conpanyNameKana = element.text();
		return conpanyNameKana;
	}


	@Override
	protected Pair<String, String> getTelNumber(Document document) {

		List<String> telNumList = new ArrayList<String>();

		//電話番号が取得できなかった場合の設定値
		String strTelNumber = "-";
		Elements elements = document.select(".fontSize01");

		//２つの電話番号を連結
		for (Element element : elements) {
			strTelNumber = strTelNumber  + "/" +  element.text();
		}
		// 数字のみの文字数
		int numLength = strTelNumber.replaceAll("[^0-9]", "").length();
		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(strTelNumber);

		// 数字のみにマッチする箇所をループ
		while (matcher.find()) {
			//電話番号を既に２回取得、もしくは電話番号の桁数未満の場合は終了
			if (telNumList.size() >= 2 || numLength < 10) {
				break;
			}
			int startIndex = matcher.start();
			String telStr = strTelNumber.substring(startIndex);
			telStr = telStr.replaceAll("[^0-9]+", "");

			//電話番号ではない場合
			if (!telStr.matches("^0.*") || telStr.length() < 10) {
				continue;
			}

			if (telStr.matches("^0[8975]0[0-9]*")) {
				// 携帯番号の場合
				telStr = telStr.substring(0, 11);
				telNumList.add(telStr);
				numLength = numLength - 11;
			} else if (telStr.matches("^0[1-9][0-9]*")) {
				// 固定電話、フリーダイヤルの場合
				telStr = telStr.substring(0, 10);
				telNumList.add(telStr);
				numLength = numLength - 10;
			}
		}
		//TODO ダサイから実装方法を要検討
		if (telNumList.size() == 1) {
			telNumList.add("-");
		} else if (telNumList.size() == 0) {
			telNumList.add("-");
			telNumList.add("-");
		}
		return Pair.pair(telNumList.get(0), telNumList.get(1));
	}

	@Override
	protected String getCompanyAddress(Document document) {
		String conpanyAddress = "";
		Elements elements = document.select("tr");
		for (Element elm : elements) {
			if (elm.text().matches("所在地.*")) {
				Elements children = elm.children();
				conpanyAddress = children.last().text();
				break;
			}
		}
		return conpanyAddress;
	}

	@Override
	protected String getBusinessLineup(Document document) {
		String businessLineup = "";
		Elements elements = document.select("tr");
		for (Element elm : elements) {
			if (elm.text().matches("事業内容.*")) {
				Elements children = elm.children();
				businessLineup = children.last().text();
				break;
			}
		}
		return businessLineup;
	}

	@Override
	protected String getJobs(Document document) {
		String jobs = "";
		Elements elements = document.select("tr");
		for (Element elm : elements) {
			for(Element childElm : elm.children().first().children()){
				if(childElm.getElementsByTag("img").attr("alt").equals("募集職種")){
					jobs = elm.children().last().text();
					break;
				}
			}
		}
		return jobs;
	}

	@Override
	protected String getArea(Document document) {
		String area = "";
		Elements elements = document.select("._walink_bc3");
		area = elements.text();
		return area;
	}

	@Override
	protected String getStartDate(Document document) {
		String strDate = "";
		Elements elements = document.select(".postingDate");
		Element element = elements.first();
		strDate = element.text();
		int startIndex = strDate.indexOf("掲載期間：") + 5;
		int endIndex = strDate.indexOf("～");
		strDate = strDate.substring(startIndex, endIndex);

		Date currentDate = new Date();
		String year = new SimpleDateFormat("yyyy").format(currentDate);

		// 掲載開始日をyyyyMMdd形式の文字列に変換
		try {
			strDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy年MM月dd日").parse(year + "年" + strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return strDate;
	}

	@Override
	protected String getEndDate(Document document) {
		String endDate = "";
		Elements elements = document.select(".postingDate");
		Element element = elements.first();
		endDate = element.text();
		int startIndex = endDate.indexOf("～");
		endDate = endDate.substring(startIndex + 1);

		Date currentDate = new Date();
		String year = new SimpleDateFormat("yyyy").format(currentDate);

		// 掲載終了日をyyyyMMdd形式の文字列に変換
		try {
			Date tmpEndDate = new SimpleDateFormat("yyyy年MM月dd日").parse(year + "年" + endDate);
			//掲載終了日が過去になってしまったら一年加算する
			if(currentDate.after(tmpEndDate)){
				tmpEndDate = DateUtils.addYears(tmpEndDate, 1);
			}
			endDate = new SimpleDateFormat("yyyyMMdd").format(tmpEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}


	@Override
	protected int getCountUsePhotographs(Document document) {
		HashSet<String> set = new HashSet<String>();
		Elements elements = document.select("img");
		for (Element element : elements) {
			String src = element.attr("src");
			if(src.matches("http://proxy.weban.jp/Image/.*$")){
				set.add(src);
			}
		}
		return set.size();
	}

	@Override
	protected Class<? extends Crawler> getChildClazz() {
		return AnCrawler.class;
	}
}
