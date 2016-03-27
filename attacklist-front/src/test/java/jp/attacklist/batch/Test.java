package jp.attacklist.batch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import jp.attacklist.batch.TownWorkCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seasar.dbflute.unit.seasar.ContainerTestCase;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.robot.S2Robot;
import org.seasar.robot.entity.AccessResult;
import org.seasar.robot.entity.AccessResultData;
import org.seasar.robot.service.DataService;
import org.seasar.robot.transformer.impl.FileTransformer;
import org.seasar.robot.util.AccessResultCallback;

public class Test extends ContainerTestCase {

	@Resource
	S2Container s2container;

	@Resource
	protected S2Robot s2Robot;

	@Resource
	protected DataService dataService;

	@Resource
	protected FileTransformer transformer;

	public void testHoge() throws Exception {

		String url = "http://r-notes.com/";
		// URL の追加 (この URL を起点にして巡回します)
		s2Robot.addUrl(url);
		// 対象URLを上記で指定した以下のみにフィルタリング
		s2Robot.addIncludeFilter(url + ".*");
		// jpgファイルを除外
		s2Robot.addExcludeFilter(".*jpg");

		// 巡回する深さを指定
		s2Robot.getRobotContext().setMaxDepth(1);

		// クロールの実行 (実行時のセッション ID が返ります)
		String sessionId = s2Robot.execute();

		final ArrayList<String> arrayList = new ArrayList<String>();

		dataService.iterate(sessionId, new AccessResultCallback() {
			public void iterate(AccessResult accessResult) {
				String str;
				AccessResultData accessResultData = accessResult.getAccessResultData();
				//				File file = (File) transformer.getData(accessResultData);

				//				String str = file.toString();
				str = accessResultData.getDataAsString();
				arrayList.add(str);

				//	                System.out.println(accessResult.getUrl());
			}
		});

		System.out.println(arrayList);
		// クロール結果の消去 (必要な場合に実行)
		s2Robot.cleanup(sessionId);
	}

	public void testHoge2() throws Exception {

		String url = "http://townwork.net/tokyo/ct_sa52101/";
		// URL の追加 (この URL を起点にして巡回します)
		// 同じドメインのURLは複数指定しても無効になるっぽい。でも異なるドメイン（例/yahooとtownwork等）なら複数のURL指定も有効となった。
		s2Robot.addUrl(url);
		// 実行するスレッド数をセット
		s2Robot.setNumOfThread(5);
		// 対象URLを上記で指定した以下のみにフィルタリング
		// 下記の方法では上手にページング後の詳細ページを取得できていない。クローリング開始のURLをページングできる数の分指定
		// 指定したほうがよいと思う。
		s2Robot.addIncludeFilter("http://townwork.net/(tokyo/ct_sa52101|detail)/" + ".*");
		// jpgファイルを除外
		s2Robot.addExcludeFilter(".*jpg");
		// 巡回する深さを指定
		s2Robot.getRobotContext().setMaxDepth(1);

		// クロールの実行 (実行時のセッション ID が返ります)
		String sessionId = s2Robot.execute();

		dataService.iterate(sessionId, new AccessResultCallback() {
			public void iterate(AccessResult accessResult) {
				AccessResultData data = accessResult.getAccessResultData();
				if (accessResult.getUrl().matches("http://townwork.net/detail/.*")) {

					Document document = Jsoup.parse(data.getDataAsString());
					Elements ditailElements = document.select(".job-ditail-tbl-inner");
					for (Element elm : ditailElements) {
						System.out.println(elm.text());
					}

					System.out.println("----------------------------");
					System.out.print(accessResult.getParentUrl());
					System.out.print(" > ");
					System.out.println(accessResult.getUrl());
					System.out.println(data.getDataAsString());
					System.out.println("----------------------------");
				}
			}
		});
		s2Robot.cleanup(sessionId);
	}

	/**
	 * 引数で指定されたタウンワークのエリア別ページの最大ページ番号を返します。
	 * @param url エリア別ページのURL
	 * @return 最大ページ番号
	 * @throws IOException
	 */
	private int getMaxPageNumber(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		Elements elements = document.select(".pager-number");
		Element elm = elements.last();
		Elements children = elm.children();
		Element targetElm = children.last();
		int maxPageNum = Integer.parseInt(targetElm.text());

		return maxPageNum;
	}

	public void testHoge3() throws Exception {

		String url = "http://townwork.net/tokyo/ct_sa52101/";
		//		int maxPageNum = getMaxPageNumber(url);
		int maxPageNum = 10;

		List<String> parseLise = new ArrayList<String>();
		for (int i = 1; i <= maxPageNum; i++) {
			//ページ数分Crawl
			doCrawl(url + "?page=" + String.valueOf(i), parseLise);
			parseLise.add("---------------- finish 1page --------------");
		}
		for (String str : parseLise) {
			System.out.println(str);
		}
	}

	private void doCrawl(String url, final List<String> list) {
		s2Robot.addUrl(url);
		// 実行するスレッド数をセット
		s2Robot.setNumOfThread(1);
		// 対象URLを上記で指定した以下のみにフィルタリング
		s2Robot.addIncludeFilter("http://townwork.net/(tokyo/ct_sa52101/.*page.*|detail/.+)");
		// jpgファイルを除外
		s2Robot.addExcludeFilter(".*jpg");
		// 巡回する深さを指定
		s2Robot.getRobotContext().setMaxDepth(1);
		// クロールの実行 (実行時のセッション ID が返ります)
		String sessionId = s2Robot.execute();

		System.out.println(dataService.getCount(sessionId));

		dataService.iterate(sessionId, new AccessResultCallback() {
			public void iterate(AccessResult accessResult) {
				AccessResultData data = accessResult.getAccessResultData();
				if (accessResult.getUrl().matches("http://townwork.net/detail/.+")) {

					Document document = Jsoup.parse(data.getDataAsString());
					list.add("-----------------start-----------------");
					list.add(accessResult.getUrl());

					for (String telNumber : getTelNumber(document)) {
						list.add(telNumber);
					}

					list.add(getCompanyName(document));
					list.add(getCompanyAddress(document));
					list.add(getBusinessLineup(document));
					list.add(getJobs(document));

					list.add("-----------------end-----------------");
				}
			}

		});
		s2Robot.cleanup(sessionId);
	}

	private String getCompanyName(Document document) {
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

	private String getCompanyAddress(Document document) {
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

	private String getJobs(Document document) {
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

	private String getBusinessLineup(Document document) {
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

	private List<String> getTelNumber(Document document) {

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
			//電話番号の取得は２回迄、電話番号の桁数未満の場合は終了
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
		return telNumList;
	}

	public void testHoge4() throws Exception {
		SingletonS2ContainerFactory.init();
		String url = "http://townwork.net/tokyo/ct_sa52101/tw_sa5210104/";
		String includeFilterRegex = "http://townwork.net/(tokyo/ct_sa52101/tw_sa5210104/.*page.*|detail/.+)";
		String excludeFilterRegex = ".*jpg";
		String detailMatchRegex = "http://townwork.net/detail/.+";
		TownWorkCrawler crawler =
				new TownWorkCrawler(url,includeFilterRegex, excludeFilterRegex, detailMatchRegex);
		crawler.run();
//		Thread thread = new Thread(crawler);
//		thread.start();
//
//		Thread.sleep(10000);
	}

}