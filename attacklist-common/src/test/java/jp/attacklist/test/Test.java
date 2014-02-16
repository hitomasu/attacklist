package jp.attacklist.test;

import java.util.List;

import javax.annotation.Resource;

import jp.attacklist.common.entity.cbean.MemberCB;
import jp.attacklist.common.entity.cbean.TweetCB;
import jp.attacklist.common.entity.exbhv.MemberBhv;
import jp.attacklist.common.entity.exbhv.TweetBhv;
import jp.attacklist.common.entity.exentity.Member;
import jp.attacklist.common.entity.exentity.Tweet;

import org.apache.poi.hssf.record.formula.TblPtg;
import org.seasar.dbflute.bhv.ConditionBeanSetupper;
import org.seasar.dbflute.cbean.ListResultBean;
import org.seasar.dbflute.cbean.SubQuery;
import org.seasar.dbflute.cbean.coption.LikeSearchOption;
import org.seasar.dbflute.unit.seasar.ContainerTestCase;

public class Test extends ContainerTestCase{

	@Resource
	protected MemberBhv memberBhv;

	@Resource
	protected TweetBhv tweetBhv;

	public void testHoge() throws Exception {

		Member member = new Member();
		member.setMailAddress("kumasan@leihauoli.com");

		memberBhv.insert(member);
	}

	public void testFuga() throws Exception {

		MemberCB cb = new MemberCB();
		cb.query().setMailAddress_LikeSearch("kuma", new LikeSearchOption().likePrefix());

		List<Member> memberList = memberBhv.selectList(cb);
		for (Member member : memberList) {
			System.out.println(member.getMailAddress());
		}
	}

	public void testTweet() throws Exception {
		TweetCB cb = new TweetCB();
		cb.query().queryMember().queryMemberSecurityAsOne().setPassword_Equal("kumasan");

		List<Tweet> selectList = tweetBhv.selectList(cb);

	}

	public void testname() throws Exception {

		MemberCB cb = new MemberCB();
		cb.setupSelect_MemberSecurityAsOne();

		cb.query().setMailAddress_PrefixSearch("kuma");
//		cb.query().derivedTweetList().count(new SubQuery<TweetCB>() {
//
//			@Override
//			public void query(TweetCB subCB) {
//				subCB.specify().columnTweetId();
//				subCB.query().setMessage_LikeSearch("kumasan", new LikeSearchOption().likeContain());
//			}
//		}).between(3, 5);

		List<Member> memberList = memberBhv.selectList(cb);

		memberBhv.loadTweetList(memberList, new ConditionBeanSetupper<TweetCB>() {

			@Override
			public void setup(TweetCB cb) {
				cb.query().setMessage_LikeSearch("kumasan", new LikeSearchOption().likeContain());
			}
		});




		for (Member member : memberList) {
			member.getMemberSecurityAsOne().getPassword();

			List<Tweet> tweetList = member.getTweetList();
			for (Tweet tweet : tweetList) {
			}
		}


	}
}
