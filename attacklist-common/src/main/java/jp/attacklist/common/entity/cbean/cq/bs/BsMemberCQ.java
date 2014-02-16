package jp.attacklist.common.entity.cbean.cq.bs;

import java.util.Map;

import org.seasar.dbflute.cbean.*;
import org.seasar.dbflute.cbean.cvalue.ConditionValue;
import org.seasar.dbflute.cbean.sqlclause.SqlClause;
import org.seasar.dbflute.exception.IllegalConditionBeanOperationException;
import jp.attacklist.common.entity.cbean.cq.ciq.*;
import jp.attacklist.common.entity.cbean.*;
import jp.attacklist.common.entity.cbean.cq.*;

/**
 * The base condition-query of MEMBER.
 * @author DBFlute(AutoGenerator)
 */
public class BsMemberCQ extends AbstractBsMemberCQ {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected MemberCIQ _inlineQuery;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BsMemberCQ(ConditionQuery childQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(childQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                 InlineView/OrClause
    //                                                                 ===================
    /**
     * Prepare InlineView query. <br />
     * {select ... from ... left outer join (select * from MEMBER) where FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">inline()</span>.setFoo...;
     * </pre>
     * @return The condition-query for InlineView query. (NotNull)
     */
    public MemberCIQ inline() {
        if (_inlineQuery == null) { _inlineQuery = xcreateCIQ(); }
        _inlineQuery.xsetOnClause(false); return _inlineQuery;
    }

    protected MemberCIQ xcreateCIQ() {
        MemberCIQ ciq = xnewCIQ();
        ciq.xsetBaseCB(_baseCB);
        return ciq;
    }

    protected MemberCIQ xnewCIQ() {
        return new MemberCIQ(xgetReferrerQuery(), xgetSqlClause(), xgetAliasName(), xgetNestLevel(), this);
    }

    /**
     * Prepare OnClause query. <br />
     * {select ... from ... left outer join MEMBER on ... and FOO = [value] ...}
     * <pre>
     * cb.query().queryMemberStatus().<span style="color: #FD4747">on()</span>.setFoo...;
     * </pre>
     * @return The condition-query for OnClause query. (NotNull)
     * @throws IllegalConditionBeanOperationException When this condition-query is base query.
     */
    public MemberCIQ on() {
        if (isBaseQuery()) { throw new IllegalConditionBeanOperationException("OnClause for local table is unavailable!"); }
        MemberCIQ inlineQuery = inline(); inlineQuery.xsetOnClause(true); return inlineQuery;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====

    protected ConditionValue _memberId;
    public ConditionValue getMemberId() {
        if (_memberId == null) { _memberId = nCV(); }
        return _memberId;
    }
    protected ConditionValue getCValueMemberId() { return getMemberId(); }

    protected Map<String, MemberSecurityCQ> _memberId_ExistsReferrer_MemberSecurityAsOneMap;
    public Map<String, MemberSecurityCQ> getMemberId_ExistsReferrer_MemberSecurityAsOne() { return _memberId_ExistsReferrer_MemberSecurityAsOneMap; }
    public String keepMemberId_ExistsReferrer_MemberSecurityAsOne(MemberSecurityCQ subQuery) {
        if (_memberId_ExistsReferrer_MemberSecurityAsOneMap == null) { _memberId_ExistsReferrer_MemberSecurityAsOneMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_ExistsReferrer_MemberSecurityAsOneMap.size() + 1);
        _memberId_ExistsReferrer_MemberSecurityAsOneMap.put(key, subQuery); return "memberId_ExistsReferrer_MemberSecurityAsOne." + key;
    }

    protected Map<String, TweetCQ> _memberId_ExistsReferrer_TweetListMap;
    public Map<String, TweetCQ> getMemberId_ExistsReferrer_TweetList() { return _memberId_ExistsReferrer_TweetListMap; }
    public String keepMemberId_ExistsReferrer_TweetList(TweetCQ subQuery) {
        if (_memberId_ExistsReferrer_TweetListMap == null) { _memberId_ExistsReferrer_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_ExistsReferrer_TweetListMap.size() + 1);
        _memberId_ExistsReferrer_TweetListMap.put(key, subQuery); return "memberId_ExistsReferrer_TweetList." + key;
    }

    protected Map<String, MemberSecurityCQ> _memberId_NotExistsReferrer_MemberSecurityAsOneMap;
    public Map<String, MemberSecurityCQ> getMemberId_NotExistsReferrer_MemberSecurityAsOne() { return _memberId_NotExistsReferrer_MemberSecurityAsOneMap; }
    public String keepMemberId_NotExistsReferrer_MemberSecurityAsOne(MemberSecurityCQ subQuery) {
        if (_memberId_NotExistsReferrer_MemberSecurityAsOneMap == null) { _memberId_NotExistsReferrer_MemberSecurityAsOneMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_NotExistsReferrer_MemberSecurityAsOneMap.size() + 1);
        _memberId_NotExistsReferrer_MemberSecurityAsOneMap.put(key, subQuery); return "memberId_NotExistsReferrer_MemberSecurityAsOne." + key;
    }

    protected Map<String, TweetCQ> _memberId_NotExistsReferrer_TweetListMap;
    public Map<String, TweetCQ> getMemberId_NotExistsReferrer_TweetList() { return _memberId_NotExistsReferrer_TweetListMap; }
    public String keepMemberId_NotExistsReferrer_TweetList(TweetCQ subQuery) {
        if (_memberId_NotExistsReferrer_TweetListMap == null) { _memberId_NotExistsReferrer_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_NotExistsReferrer_TweetListMap.size() + 1);
        _memberId_NotExistsReferrer_TweetListMap.put(key, subQuery); return "memberId_NotExistsReferrer_TweetList." + key;
    }

    protected Map<String, TweetCQ> _memberId_SpecifyDerivedReferrer_TweetListMap;
    public Map<String, TweetCQ> getMemberId_SpecifyDerivedReferrer_TweetList() { return _memberId_SpecifyDerivedReferrer_TweetListMap; }
    public String keepMemberId_SpecifyDerivedReferrer_TweetList(TweetCQ subQuery) {
        if (_memberId_SpecifyDerivedReferrer_TweetListMap == null) { _memberId_SpecifyDerivedReferrer_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_SpecifyDerivedReferrer_TweetListMap.size() + 1);
        _memberId_SpecifyDerivedReferrer_TweetListMap.put(key, subQuery); return "memberId_SpecifyDerivedReferrer_TweetList." + key;
    }

    protected Map<String, MemberSecurityCQ> _memberId_InScopeRelation_MemberSecurityAsOneMap;
    public Map<String, MemberSecurityCQ> getMemberId_InScopeRelation_MemberSecurityAsOne() { return _memberId_InScopeRelation_MemberSecurityAsOneMap; }
    public String keepMemberId_InScopeRelation_MemberSecurityAsOne(MemberSecurityCQ subQuery) {
        if (_memberId_InScopeRelation_MemberSecurityAsOneMap == null) { _memberId_InScopeRelation_MemberSecurityAsOneMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_InScopeRelation_MemberSecurityAsOneMap.size() + 1);
        _memberId_InScopeRelation_MemberSecurityAsOneMap.put(key, subQuery); return "memberId_InScopeRelation_MemberSecurityAsOne." + key;
    }

    protected Map<String, TweetCQ> _memberId_InScopeRelation_TweetListMap;
    public Map<String, TweetCQ> getMemberId_InScopeRelation_TweetList() { return _memberId_InScopeRelation_TweetListMap; }
    public String keepMemberId_InScopeRelation_TweetList(TweetCQ subQuery) {
        if (_memberId_InScopeRelation_TweetListMap == null) { _memberId_InScopeRelation_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_InScopeRelation_TweetListMap.size() + 1);
        _memberId_InScopeRelation_TweetListMap.put(key, subQuery); return "memberId_InScopeRelation_TweetList." + key;
    }

    protected Map<String, MemberSecurityCQ> _memberId_NotInScopeRelation_MemberSecurityAsOneMap;
    public Map<String, MemberSecurityCQ> getMemberId_NotInScopeRelation_MemberSecurityAsOne() { return _memberId_NotInScopeRelation_MemberSecurityAsOneMap; }
    public String keepMemberId_NotInScopeRelation_MemberSecurityAsOne(MemberSecurityCQ subQuery) {
        if (_memberId_NotInScopeRelation_MemberSecurityAsOneMap == null) { _memberId_NotInScopeRelation_MemberSecurityAsOneMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_NotInScopeRelation_MemberSecurityAsOneMap.size() + 1);
        _memberId_NotInScopeRelation_MemberSecurityAsOneMap.put(key, subQuery); return "memberId_NotInScopeRelation_MemberSecurityAsOne." + key;
    }

    protected Map<String, TweetCQ> _memberId_NotInScopeRelation_TweetListMap;
    public Map<String, TweetCQ> getMemberId_NotInScopeRelation_TweetList() { return _memberId_NotInScopeRelation_TweetListMap; }
    public String keepMemberId_NotInScopeRelation_TweetList(TweetCQ subQuery) {
        if (_memberId_NotInScopeRelation_TweetListMap == null) { _memberId_NotInScopeRelation_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_NotInScopeRelation_TweetListMap.size() + 1);
        _memberId_NotInScopeRelation_TweetListMap.put(key, subQuery); return "memberId_NotInScopeRelation_TweetList." + key;
    }

    protected Map<String, TweetCQ> _memberId_QueryDerivedReferrer_TweetListMap;
    public Map<String, TweetCQ> getMemberId_QueryDerivedReferrer_TweetList() { return _memberId_QueryDerivedReferrer_TweetListMap; }
    public String keepMemberId_QueryDerivedReferrer_TweetList(TweetCQ subQuery) {
        if (_memberId_QueryDerivedReferrer_TweetListMap == null) { _memberId_QueryDerivedReferrer_TweetListMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_memberId_QueryDerivedReferrer_TweetListMap.size() + 1);
        _memberId_QueryDerivedReferrer_TweetListMap.put(key, subQuery); return "memberId_QueryDerivedReferrer_TweetList." + key;
    }
    protected Map<String, Object> _memberId_QueryDerivedReferrer_TweetListParameterMap;
    public Map<String, Object> getMemberId_QueryDerivedReferrer_TweetListParameter() { return _memberId_QueryDerivedReferrer_TweetListParameterMap; }
    public String keepMemberId_QueryDerivedReferrer_TweetListParameter(Object parameterValue) {
        if (_memberId_QueryDerivedReferrer_TweetListParameterMap == null) { _memberId_QueryDerivedReferrer_TweetListParameterMap = newLinkedHashMapSized(4); }
        String key = "subQueryParameterKey" + (_memberId_QueryDerivedReferrer_TweetListParameterMap.size() + 1);
        _memberId_QueryDerivedReferrer_TweetListParameterMap.put(key, parameterValue); return "memberId_QueryDerivedReferrer_TweetListParameter." + key;
    }

    /** 
     * Add order-by as ascend. <br />
     * MEMBER_ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsMemberCQ addOrderBy_MemberId_Asc() { regOBA("MEMBER_ID"); return this; }

    /**
     * Add order-by as descend. <br />
     * MEMBER_ID: {PK, ID, NotNull, BIGINT(19)}
     * @return this. (NotNull)
     */
    public BsMemberCQ addOrderBy_MemberId_Desc() { regOBD("MEMBER_ID"); return this; }

    protected ConditionValue _mailAddress;
    public ConditionValue getMailAddress() {
        if (_mailAddress == null) { _mailAddress = nCV(); }
        return _mailAddress;
    }
    protected ConditionValue getCValueMailAddress() { return getMailAddress(); }

    /** 
     * Add order-by as ascend. <br />
     * MAIL_ADDRESS: {UQ, NotNull, VARCHAR(250)}
     * @return this. (NotNull)
     */
    public BsMemberCQ addOrderBy_MailAddress_Asc() { regOBA("MAIL_ADDRESS"); return this; }

    /**
     * Add order-by as descend. <br />
     * MAIL_ADDRESS: {UQ, NotNull, VARCHAR(250)}
     * @return this. (NotNull)
     */
    public BsMemberCQ addOrderBy_MailAddress_Desc() { regOBD("MAIL_ADDRESS"); return this; }

    // ===================================================================================
    //                                                             SpecifiedDerivedOrderBy
    //                                                             =======================
    /**
     * Add order-by for specified derived column as ascend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #FD4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] asc</span>
     * cb.<span style="color: #FD4747">addSpecifiedDerivedOrderBy_Asc</span>(<span style="color: #FD4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsMemberCQ addSpecifiedDerivedOrderBy_Asc(String aliasName)
    { registerSpecifiedDerivedOrderBy_Asc(aliasName); return this; }

    /**
     * Add order-by for specified derived column as descend.
     * <pre>
     * cb.specify().derivedPurchaseList().max(new SubQuery&lt;PurchaseCB&gt;() {
     *     public void query(PurchaseCB subCB) {
     *         subCB.specify().columnPurchaseDatetime();
     *     }
     * }, <span style="color: #FD4747">aliasName</span>);
     * <span style="color: #3F7E5E">// order by [alias-name] desc</span>
     * cb.<span style="color: #FD4747">addSpecifiedDerivedOrderBy_Desc</span>(<span style="color: #FD4747">aliasName</span>);
     * </pre>
     * @param aliasName The alias name specified at (Specify)DerivedReferrer. (NotNull)
     * @return this. (NotNull)
     */
    public BsMemberCQ addSpecifiedDerivedOrderBy_Desc(String aliasName)
    { registerSpecifiedDerivedOrderBy_Desc(aliasName); return this; }

    // ===================================================================================
    //                                                                         Union Query
    //                                                                         ===========
    protected void reflectRelationOnUnionQuery(ConditionQuery baseQueryAsSuper, ConditionQuery unionQueryAsSuper) {
        MemberCQ baseQuery = (MemberCQ)baseQueryAsSuper;
        MemberCQ unionQuery = (MemberCQ)unionQueryAsSuper;
        if (baseQuery.hasConditionQueryMemberSecurityAsOne()) {
            unionQuery.queryMemberSecurityAsOne().reflectRelationOnUnionQuery(baseQuery.queryMemberSecurityAsOne(), unionQuery.queryMemberSecurityAsOne());
        }
    }

    // ===================================================================================
    //                                                                       Foreign Query
    //                                                                       =============
    /**
     * Get the condition-query for relation table. <br />
     * MEMBER_SECURITY by MEMBER_ID, named 'memberSecurityAsOne'.
     * @return The instance of condition-query. (NotNull)
     */
    public MemberSecurityCQ queryMemberSecurityAsOne() {
        return getConditionQueryMemberSecurityAsOne();
    }
    protected MemberSecurityCQ _conditionQueryMemberSecurityAsOne;
    public MemberSecurityCQ getConditionQueryMemberSecurityAsOne() {
        if (_conditionQueryMemberSecurityAsOne == null) {
            _conditionQueryMemberSecurityAsOne = xcreateQueryMemberSecurityAsOne();
            xsetupOuterJoinMemberSecurityAsOne();
        }
        return _conditionQueryMemberSecurityAsOne;
    }
    protected MemberSecurityCQ xcreateQueryMemberSecurityAsOne() {
        String nrp = resolveNextRelationPath("MEMBER", "memberSecurityAsOne");
        String jan = resolveJoinAliasName(nrp, xgetNextNestLevel());
        MemberSecurityCQ cq = new MemberSecurityCQ(this, xgetSqlClause(), jan, xgetNextNestLevel());
        cq.xsetBaseCB(_baseCB);
        cq.xsetForeignPropertyName("memberSecurityAsOne");
        cq.xsetRelationPath(nrp); return cq;
    }
    protected void xsetupOuterJoinMemberSecurityAsOne() {
        MemberSecurityCQ cq = getConditionQueryMemberSecurityAsOne();
        Map<String, String> joinOnMap = newLinkedHashMapSized(4);
        joinOnMap.put("MEMBER_ID", "MEMBER_ID");
        registerOuterJoin(cq, joinOnMap, "memberSecurityAsOne");
    }
    public boolean hasConditionQueryMemberSecurityAsOne() {
        return _conditionQueryMemberSecurityAsOne != null;
    }

    protected Map<String, Object> xfindFixedConditionDynamicParameterMap(String property) {
        return null;
    }

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    protected Map<String, MemberCQ> _scalarConditionMap;
    public Map<String, MemberCQ> getScalarCondition() { return _scalarConditionMap; }
    public String keepScalarCondition(MemberCQ subQuery) {
        if (_scalarConditionMap == null) { _scalarConditionMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_scalarConditionMap.size() + 1);
        _scalarConditionMap.put(key, subQuery); return "scalarCondition." + key;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    protected Map<String, MemberCQ> _specifyMyselfDerivedMap;
    public Map<String, MemberCQ> getSpecifyMyselfDerived() { return _specifyMyselfDerivedMap; }
    public String keepSpecifyMyselfDerived(MemberCQ subQuery) {
        if (_specifyMyselfDerivedMap == null) { _specifyMyselfDerivedMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_specifyMyselfDerivedMap.size() + 1);
        _specifyMyselfDerivedMap.put(key, subQuery); return "specifyMyselfDerived." + key;
    }

    protected Map<String, MemberCQ> _queryMyselfDerivedMap;
    public Map<String, MemberCQ> getQueryMyselfDerived() { return _queryMyselfDerivedMap; }
    public String keepQueryMyselfDerived(MemberCQ subQuery) {
        if (_queryMyselfDerivedMap == null) { _queryMyselfDerivedMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_queryMyselfDerivedMap.size() + 1);
        _queryMyselfDerivedMap.put(key, subQuery); return "queryMyselfDerived." + key;
    }
    protected Map<String, Object> _qyeryMyselfDerivedParameterMap;
    public Map<String, Object> getQueryMyselfDerivedParameter() { return _qyeryMyselfDerivedParameterMap; }
    public String keepQueryMyselfDerivedParameter(Object parameterValue) {
        if (_qyeryMyselfDerivedParameterMap == null) { _qyeryMyselfDerivedParameterMap = newLinkedHashMapSized(4); }
        String key = "subQueryParameterKey" + (_qyeryMyselfDerivedParameterMap.size() + 1);
        _qyeryMyselfDerivedParameterMap.put(key, parameterValue); return "queryMyselfDerivedParameter." + key;
    }

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    protected Map<String, MemberCQ> _myselfExistsMap;
    public Map<String, MemberCQ> getMyselfExists() { return _myselfExistsMap; }
    public String keepMyselfExists(MemberCQ subQuery) {
        if (_myselfExistsMap == null) { _myselfExistsMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_myselfExistsMap.size() + 1);
        _myselfExistsMap.put(key, subQuery); return "myselfExists." + key;
    }

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    protected Map<String, MemberCQ> _myselfInScopeMap;
    public Map<String, MemberCQ> getMyselfInScope() { return _myselfInScopeMap; }
    public String keepMyselfInScope(MemberCQ subQuery) {
        if (_myselfInScopeMap == null) { _myselfInScopeMap = newLinkedHashMapSized(4); }
        String key = "subQueryMapKey" + (_myselfInScopeMap.size() + 1);
        _myselfInScopeMap.put(key, subQuery); return "myselfInScope." + key;
    }

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xCB() { return MemberCB.class.getName(); }
    protected String xCQ() { return MemberCQ.class.getName(); }
    protected String xMap() { return Map.class.getName(); }
}
