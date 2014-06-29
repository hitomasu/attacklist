package jp.attacklist.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seasar.framework.util.tiger.Pair;

public class TownWorkCrawler extends Crawler {

	public TownWorkCrawler(String url, String includeFilterRegex, String excludeFilterRegex, String detailMatchRegex) {
		super(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
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
	protected Pair<String, String> getTelNumber(Document document) {
		List<String> telNumList = new ArrayList<String>();

		String strTelNumber = "-";

		Elements elements = document.select(".detail-tel-ttl");
		if (elements.size() == 0) {
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

			if (telStr.matches("^0[9875]0")) {
				// 携帯番号の場合
				telStr = telStr.substring(0, 11);
				telNumList.add(telStr);
				numLength = numLength - 11;
			} else if (telStr.matches("^0.*")) {
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
}
