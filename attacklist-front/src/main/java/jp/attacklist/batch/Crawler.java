package jp.attacklist.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.tiger.Pair;
import org.seasar.robot.S2Robot;
import org.seasar.robot.entity.AccessResult;
import org.seasar.robot.entity.AccessResultData;
import org.seasar.robot.service.DataService;
import org.seasar.robot.util.AccessResultCallback;

public abstract class Crawler implements Runnable {

	protected S2Container s2container;
	protected S2Robot s2Robot;
	protected DataService dataService;

	/* クロールの起点となるURL */
	protected String url;
	/* クロール対象URLのフィルタリング(正規表現) */
	protected String includeFilterRegex;
	/* クロール除外URLのフィルタリング(正規表現) */
	protected String excludeFilterRegex;
	/* 企業情報の詳細ページにマッチするURL(正規表現) */
	protected String detailMatchRegex;

	/**
	 * @param url クロールの起点となるURL
	 * @param includeFilterRegex クロール対象URLのフィルタリング(正規表現)
	 * @param excludeFilterRegex クロール除外URLのフィルタリング(正規表現)
	 * @param detailMatchRegex 企業情報の詳細ページにマッチするURL(正規表現)
	 */
	public Crawler(String url, String includeFilterRegex, String excludeFilterRegex, String detailMatchRegex) {
		s2container = SingletonS2ContainerFactory.getContainer();
		this.s2Robot = (S2Robot) s2container.getComponent(S2Robot.class);
		this.dataService = (DataService) s2container.getComponent(DataService.class);

		this.url = url;
		this.includeFilterRegex = includeFilterRegex;
		this.excludeFilterRegex = excludeFilterRegex;
		this.detailMatchRegex = detailMatchRegex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		crawling();
	}

	/**
	 * エリア掲載ページ数分のクローリングをします。
	 */
	protected void crawling() {

		try {
			// クロールする最大ページ数を取得
			int maxPageCount = getMaxPageNumber(url);
			for (int i = 1; i <= maxPageCount; i++) {
				//ページ数分Crawl
				doCrawl(url + "?page=" + String.valueOf(i));
			}
		} catch (IOException e) {
			//TODO どこまでクロールが成功しているかをログで出力
			e.printStackTrace();
		} finally {
		}

	}

	/**
	 * クローリングを実行します。
	 * @param strtUrl クローリングを開始するURL(ページ指定あり)
	 */
	protected void doCrawl(String strtUrl) {
		//TODO データアクセスクラスができるまでの仮置き
		final List<String> parseLise = new ArrayList<String>();

		//S2Robotの初期設定
		initS2robot(strtUrl);

		// クロールの実行 (実行時のセッション ID が返ります)
		String sessionId = s2Robot.execute();

		dataService.iterate(sessionId, new AccessResultCallback() {
			public void iterate(AccessResult accessResult) {
				AccessResultData data = accessResult.getAccessResultData();
				if (accessResult.getUrl().matches(detailMatchRegex)) {
					Document document = Jsoup.parse(data.getDataAsString());
					parseLise.add("----------------------------------------");
					parseLise.add(getCompanyName(document));
					parseLise.add(getCompanyAddress(document));

					parseLise.add(getTelNumber(document).getFirst());
					parseLise.add(getTelNumber(document).getSecond());

					parseLise.add(getBusinessLineup(document));
					parseLise.add(getJobs(document));
					parseLise.add("----------------------------------------");
				}
			}
		});
		//TODO データアクセスクラスが出来るまでの仮処理
		for (String str : parseLise) {
			System.out.println(str);
		}

		// クロール結果の消去
		s2Robot.cleanup(sessionId);
	}

	/**
	 * S2Robotの各種パラメーターを設定します。
	 * @param strtUrl クローリングを開始するURL
	 */
	private void initS2robot(String strtUrl) {
		// クローリングを始めるURLをセットstrtUrl
		s2Robot.addUrl(strtUrl);
		// 実行するスレッド数をセット
		s2Robot.setNumOfThread(1);
		// 対象URLを上記で指定した以下のみにフィルタリング
		s2Robot.addIncludeFilter(includeFilterRegex);
		// クロール除外のURLをセット
		s2Robot.addExcludeFilter(excludeFilterRegex);
		// 巡回する深さを指定
		s2Robot.getRobotContext().setMaxDepth(1);
	}

	/**
	 * エリア別ページの最大ページ数を返します。
	 * @param url エリア別ページのURL
	 * @return 最大ページ番号
	 * @throws IOException
	 */
	abstract protected int getMaxPageNumber(String url) throws IOException;

	/**
	 * 引数で受けたHTMLドキュメントを解析して企業名を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return　企業名
	 */
	abstract protected String getCompanyName(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して電話番号をペアの値で返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 電話番号
	 */
	abstract protected Pair<String, String> getTelNumber(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して企業所在地を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 企業所在地
	 */
	abstract protected String getCompanyAddress(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して事業内容を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 事業内容
	 */
	abstract protected String getBusinessLineup(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して募集職種を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 募集職種
	 */
	abstract protected String getJobs(Document document);

}
