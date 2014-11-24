package jp.attacklist.batch;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seasar.framework.util.tiger.Pair;

/**
 * タウンワークとタウンワーク社員をクローリングします
 * @author hitoshi_masuzawa
 */
public class TownWorkCrawler extends Crawler {

	/**
	 * @param url クロールの起点となるURL
	 * @param includeFilterRegex クロール対象URLのフィルタリング(正規表現)
	 * @param excludeFilterRegex クロール除外URLのフィルタリング(正規表現)
	 * @param detailMatchRegex 企業情報の詳細ページにマッチするURL(正規表現)
	 */
	public TownWorkCrawler(String url, String includeFilterRegex, String excludeFilterRegex, String detailMatchRegex) {
		super(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
	}

	@Override
	protected String getPagingUrl(int pagingNumber){
		return url + "?page=" + String.valueOf(pagingNumber);
	}


	@Override
	protected String getMediaName() {
		return "TownWork";
	}

	@Override
	protected String getBodyText(Document document){
		Element body = document.body();
		return body.text();
	}

	@Override
	protected int getMaxPageNumber(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		Elements elements = document.select(".pager-number");
		Element elm = elements.last();
		Elements children = elm.children();
		Element targetElm = children.last();
		int maxPageNum = Integer.parseInt(targetElm.text());
		//TODO 一時的に固定値を返却
		return 1;
		//		return maxPageNum;
	}

	@Override
	protected String getCompanyName(Document document) {
		String conpanyName = "";
		Elements elements = document.select(".job-ditail-tbl-inner");
		for (Element elm : elements) {
			Elements children = elm.children();
			if (children.first().text().matches("社名.*")) {
				conpanyName = children.last().text();
				break;
			}
		}
		return conpanyName;
	}

	@Override
	protected String getCompanyNameKana(Document document) {
		String conpanyNameKana = "";
		return conpanyNameKana;
	}


	@Override
	protected Pair<String, String> getTelNumber(Document document) {
		List<String> telNumList = new ArrayList<String>();

		String strTelNumber = "-";
		Elements elements = document.select(".detail-tel-num");

		if (elements.isEmpty()) {
			elements = document.select(".detail-tel-ttl");
		}

		if (elements.isEmpty()) {
			telNumList.add(strTelNumber);
		}

		strTelNumber = elements.last().text();
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
		Elements elements = document.select(".job-ditail-tbl-inner");
		for (Element elm : elements) {
			Elements children = elm.children();
			if (children.first().text().matches("会社住所.*")) {
				conpanyAddress = children.last().text();
				break;
			}
		}
		return conpanyAddress;
	}

	@Override
	protected String getBusinessLineup(Document document) {
		String businessLineup = "";
		Elements elements = document.select(".job-ditail-tbl-inner");
		for (Element elm : elements) {
			Elements children = elm.children();
			if (children.first().text().matches("会社事業内容.*")) {
				businessLineup = children.last().text();
				break;
			}
		}
		return businessLineup;
	}

	@Override
	protected String getJobs(Document document) {
		String jobs = "";
		Elements elements = document.select(".job-ditail-tbl-inner");
		for (Element elm : elements) {
			Elements children = elm.children();
			if (children.first().text().matches("職種.*")) {
				jobs = children.last().text();
				break;
			}
		}
		return jobs;
	}

	@Override
	protected String getArea(Document document) {
		String area = "";
		Elements elements = document.select(".breadcrumbs-lst");
		Element element = elements.first();
		Elements children = element.children();
		outside: for (Element child : children) {
			Elements children2 = child.children();
			for (Element child2 : children2) {
				if (child2.attr("href").matches("^/.+/ct_[a-z][a-z]..+/$")) {
					area = child2.text();
					//　エリア名を取得したら大外のループから抜ける
					break outside;
				}
			}
		}
		return area;
	}

	@Override
	protected String getStartDate(Document document) {
		String strDate = "";
		Elements elements = document.select(".job-age-txt");
		Element element = elements.first();
		strDate = element.text();
		int startIndex = strDate.indexOf("掲載期間：") + 5;
		int endIndex = strDate.indexOf("～");
		strDate = strDate.substring(startIndex, endIndex);

		// 掲載開始日をyyyyMMdd形式の文字列に変換
		try {
			strDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy年MM月dd日").parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return strDate;
	}

	@Override
	protected String getEndDate(Document document) {
		String endDate = "";
		Elements elements = document.select(".job-age-txt");
		Element element = elements.first();
		endDate = element.text();
		int startIndex = endDate.indexOf("～");
//		int endIndex = endDate.indexOf("～");
		endDate = endDate.substring(startIndex + 1);

		// 掲載終了日をyyyyMMdd形式の文字列に変換
		try {
			endDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyy年MM月dd日").parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return endDate;
	}


	@Override
	protected int getCountUsePhotographs(Document document) {
		//タウンワークの場合は写真ありの場合はフロム・エーナビからの転載とイコール
		HashSet<String> set = new HashSet<String>();
		Elements elements = document.select("img");
		for (Element element : elements) {
			String src = element.attr("src");
			if(src.matches("/jo_img/fan/detail/.*")){
				set.add(src);
			}
		}
		return set.size();
	}

	@Override
	protected Class<? extends Crawler> getChildClazz() {
		return TownWorkCrawler.class;
	}

}
