package jp.attacklist.common.entity.cbean.nss;

import org.seasar.dbflute.cbean.ConditionQuery;
import jp.attacklist.common.entity.cbean.cq.MemberSecurityCQ;

/**
 * The nest select set-upper of MEMBER_SECURITY.
 * @author DBFlute(AutoGenerator)
 */
public class MemberSecurityNss {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected MemberSecurityCQ _query;
    public MemberSecurityNss(MemberSecurityCQ query) { _query = query; }
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
        _query.doNss(new MemberSecurityCQ.NssCall() { public ConditionQuery qf() { return _query.queryMember(); }});
        return new MemberNss(_query.queryMember());
    }

}
