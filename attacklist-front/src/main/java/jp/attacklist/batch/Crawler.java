package jp.attacklist.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.attacklist.batch.CrawlerTest;

import org.apache.log4j.Logger;
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
	protected Logger log = Logger.getLogger(getChildClazz());

	/** クロールの起点となるURL */
	protected String url;
	/** クロール対象URLのフィルタリング(正規表現) */
	protected String includeFilterRegex;
	/** クロール除外URLのフィルタリング(正規表現) */
	protected String excludeFilterRegex;
	/** 企業情報の詳細ページにマッチするURL(正規表現) */
	protected String detailMatchRegex;

	/**
	 * 実際に起動しているクラスのCalssNameを返します
	 * @return CalssName
	 */
	abstract protected Class<? extends Crawler> getChildClazz();

	/**
	 * コンストラクタ
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
		startCrawling();
	}

	/**
	 * エリア掲載ページ数分のクローリングをします。
	 */
	protected void startCrawling() {

		//エラー時のログ出力用
		String crawlUrl = "Before start";
		try {
			// クロールする最大ページ数を取得
			int maxPageCount = getMaxPageNumber(url);
			//ページ数分Crawl
			for (int i = 1; i <= maxPageCount; i++) {
				//ページング指定されたURLを取得
				crawlUrl = getPagingUrl(i);
				doCrawl(crawlUrl);
			}
		} catch (IOException e) {
			//どこまでクロールが成功しているかをログで出力
			e.printStackTrace();
			log.error(crawlUrl);

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
					parseLise.add("掲載媒体名：" + getMediaName());
					parseLise.add("企業名：" + getCompanyName(document));
					parseLise.add("所在地：" + getCompanyAddress(document));

					parseLise.add("電話番号1：" + getTelNumber(document).getFirst());
					parseLise.add("電話番号2：" + getTelNumber(document).getSecond());

					parseLise.add("事業内容：" + getBusinessLineup(document));
					parseLise.add("募集職種：" + getJobs(document));
					parseLise.add("掲載エリア：" + getArea(document));
					parseLise.add("掲載開始日：" + getStartDate(document));
					parseLise.add("掲載終了日：" + getEndDate(document));
					parseLise.add("写真点数：" + String.valueOf(getCountUsePhotographs(document)));
//					parseLise.add("本文：" + getBodyText(document));
					parseLise.add("----------------------------------------");
				}
			}
		});
		//TODO データアクセスクラスが出来るまでの仮処理
		for (String str : parseLise) {
			log.debug(str);
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
	 *クロール対象のURLをページング指定を追加します。
	 * @param pagingNumber
	 * @return ページング指定済みのクロール対象URL
	 */
	abstract protected String getPagingUrl(int pagingNumber);

	/**
	 * 掲載媒体名を返します。
	 * @return　掲載媒体名
	 */
	abstract protected String getMediaName();

	/**
	 * 引数で受けたHTMLドキュメントを解析して原稿本文を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return　原稿本文
	 */
	abstract protected String getBodyText(Document document);


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
	 * 引数で受けたHTMLドキュメントを解析して企業名（カナ）を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return　企業名（カナ）
	 */
	abstract protected String getCompanyNameKana(Document document);


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

	/**
	 * 引数で受けたHTMLドキュメントを解析して掲載エリアを返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 掲載エリア
	 */
	abstract protected String getArea(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して掲載開始日を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 掲載開始日
	 */
	abstract protected String getStartDate(Document document);

	/**
	 * 引数で受けたHTMLドキュメントを解析して掲載終了日を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 掲載終了日
	 */
	abstract protected String getEndDate(Document document);


	/**
	 * 引数で受けたHTMLドキュメントを解析して原稿内の写真数を返します。
	 * @param document 解析するHTMLドキュメント
	 * @return 原稿内の写真数
	 */
	abstract protected int getCountUsePhotographs(Document document);

}
