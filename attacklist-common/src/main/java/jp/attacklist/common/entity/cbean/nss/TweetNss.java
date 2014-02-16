package jp.attacklist.common.entity.cbean.nss;

import org.seasar.dbflute.cbean.ConditionQuery;
import jp.attacklist.common.entity.cbean.cq.TweetCQ;

/**
 * The nest select set-upper of TWEET.
 * @author DBFlute(AutoGenerator)
 */
public class TweetNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected TweetCQ _query;
    public TweetNss(TweetCQ query) { _query = query; }
    public boolean hasConditionQuery() { return _query != null; }

    // ===================================================================================
    //                                                                     Nested Relation
    //                                                                     ===============
    /**
     * With nested relation columns to select clause. <br />
     * MEMBER by my MEMBER_ID, named 'member'.
     * @return The set-upper of more nested relation. {...with[nested-relation].with[more-nested-relation]} (NotNull)
     */
    public MemberNss withMember() {
        _query.doNss(new TweetCQ.NssCall() { public ConditionQuery qf() { return _query.queryMember(); }});
        return new MemberNss(_query.queryMember());
    }

}
