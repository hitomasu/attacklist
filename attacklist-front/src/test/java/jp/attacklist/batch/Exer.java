package jp.attacklist.batch;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class Exer {

	public static void main(String[] args) {

		SingletonS2ContainerFactory.init();

		final Thread shutdownThread = new Thread() {
			@Override
			public void run() {
				SingletonS2ContainerFactory.destroy();
			}
		};
		// JVM終了時にS2コンテナの終了処理
		Runtime.getRuntime().addShutdownHook(shutdownThread);

		String url = "http://townwork.net/tokyo/ct_sa52101/";
		String includeFilterRegex = "http://townwork.net/(tokyo/ct_sa52101/.*page.*|detail/.+)";
		String excludeFilterRegex = ".*jpg";
		String detailMatchRegex = "http://townwork.net/detail/.+";

		TownWorkCrawler crawler =
				new TownWorkCrawler("http://townwork.net/tokyo/ct_sa52127/",
						"http://townwork.net/(tokyo/ct_sa52127/.*page.*|detail/.+)", excludeFilterRegex,
						detailMatchRegex);
		TownWorkCrawler crawler2 =
				new TownWorkCrawler("http://townwork.net/tokyo/ct_sa52101/",
						"http://townwork.net/(tokyo/ct_sa52101/.*page.*|detail/.+)", excludeFilterRegex,
						detailMatchRegex);
		TownWorkCrawler crawler3 =
				new TownWorkCrawler("http://townwork.net/tokyo/ct_sa52125/",
						"http://townwork.net/(tokyo/ct_sa52125/.*page.*|detail/.+)", excludeFilterRegex,
						detailMatchRegex);
		TownWorkCrawler crawler4 =
				new TownWorkCrawler("http://townwork.net/tokyo/ct_sa52102/",
						"http://townwork.net/(tokyo/ct_sa52102/.*page.*|detail/.+)", excludeFilterRegex,
						detailMatchRegex);


		Thread thread = new Thread(crawler);
		Thread thread2 = new Thread(crawler2);
		Thread thread3 = new Thread(crawler3);
		Thread thread4 = new Thread(crawler4);
		thread.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
