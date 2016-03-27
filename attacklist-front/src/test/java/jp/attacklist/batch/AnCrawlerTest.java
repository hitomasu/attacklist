package jp.attacklist.batch;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.seasar.dbflute.unit.seasar.ContainerTestCase;

public class AnCrawlerTest extends ContainerTestCase {


	String url = "http://weban.jp/kanto/spcty3026";
	String includeFilterRegex = "http://weban.jp/(kanto/spcty3026/.+|detail/.+)";
	String excludeFilterRegex = ".*jpg";
	String detailMatchRegex = "http://weban.jp/detail/.+";

	String detailPageUrl = "http://weban.jp/detail/736996044.html";

	@Test
	public void test_getMaxPageNumber() throws IOException {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		System.out.println("エリア別最大ページ数：" + anCrawler.getMaxPageNumber(url));
	}

	@Test
	public void test_getPagingUrl() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		System.out.println("ページング設定済みのクロールURL：" + anCrawler.getPagingUrl(1));
	}

	@Test
	public void test_getCompanyName() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("会社名：" + anCrawler.getCompanyName(document));
	}

	@Test
	public void test_getCompanyNameKana() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("会社名カナ：" + anCrawler.getCompanyNameKana(document));
	}

	@Test
	public void test_getTelNumber() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("電話番号：" + anCrawler.getTelNumber(document));

	}

	@Test
	public void test_getCompanyAddress() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("企業所在地：" + anCrawler.getCompanyAddress(document));

	}

	@Test
	public void test_getBusinessLineup() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("事業内容：" + anCrawler.getBusinessLineup(document));

	}

	@Test
	public void test_getJobs() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("募集職種：" + anCrawler.getJobs(document));

	}

	@Test
	public void test_getArea() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("掲載エリア：" + anCrawler.getArea(document));
	}

	@Test
	public void test_getStartDate() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("掲載開始日：" + anCrawler.getStartDate(document));
	}

	@Test
	public void test_getEndDate() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("掲載終了日：" + anCrawler.getEndDate(document));
	}

	@Test
	public void test_getCountUsePhotographs() throws Exception {
		AnCrawler anCrawler = new AnCrawler(url, includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		Document document = Jsoup.connect(detailPageUrl).get();
		System.out.println("原稿内の写真点数：" + anCrawler.getCountUsePhotographs(document));
	}


}
