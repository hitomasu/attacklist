package jp.attacklist.common.entity.cbean.cq.bs;

import java.util.*;

import org.seasar.dbflute.cbean.*;
import org.seasar.dbflute.cbean.chelper.*;
import org.seasar.dbflute.cbean.ckey.*;
import org.seasar.dbflute.cbean.coption.*;
import org.seasar.dbflute.cbean.cvalue.ConditionValue;
import org.seasar.dbflute.cbean.sqlclause.SqlClause;
import org.seasar.dbflute.dbmeta.DBMetaProvider;
import jp.attacklist.common.entity.allcommon.*;
import jp.attacklist.common.entity.cbean.*;
import jp.attacklist.common.entity.cbean.cq.*;

/**
 * The abstract condition-query of TWEET.
 * @author DBFlute(AutoGenerator)
 */
public abstract class AbstractBsTweetCQ extends AbstractConditionQuery {

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public AbstractBsTweetCQ(ConditionQuery childQuery, SqlClause sqlClause, String aliasName, int nestLevel) {
        super(childQuery, sqlClause, aliasName, nestLevel);
    }

    // ===================================================================================
    //                                                                     DBMeta Provider
    //                                                                     ===============
    @Override
    protected DBMetaProvider xgetDBMetaProvider() {
        return DBMetaInstanceHandler.getProvider();
    }

    // ===================================================================================
    //                                                                          Table Name
    //                                                                          ==========
    public String getTableDbName() {
        return "TWEET";
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as equal. (NullAllowed: if null, no condition)
     */
    public void setTweetId_Equal(Long tweetId) {
        doSetTweetId_Equal(tweetId);
    }

    protected void doSetTweetId_Equal(Long tweetId) {
        regTweetId(CK_EQ, tweetId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as notEqual. (NullAllowed: if null, no condition)
     */
    public void setTweetId_NotEqual(Long tweetId) {
        doSetTweetId_NotEqual(tweetId);
    }

    protected void doSetTweetId_NotEqual(Long tweetId) {
        regTweetId(CK_NES, tweetId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as greaterThan. (NullAllowed: if null, no condition)
     */
    public void setTweetId_GreaterThan(Long tweetId) {
        regTweetId(CK_GT, tweetId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as lessThan. (NullAllowed: if null, no condition)
     */
    public void setTweetId_LessThan(Long tweetId) {
        regTweetId(CK_LT, tweetId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as greaterEqual. (NullAllowed: if null, no condition)
     */
    public void setTweetId_GreaterEqual(Long tweetId) {
        regTweetId(CK_GE, tweetId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetId The value of tweetId as lessEqual. (NullAllowed: if null, no condition)
     */
    public void setTweetId_LessEqual(Long tweetId) {
        regTweetId(CK_LE, tweetId);
    }

    /**
     * RangeOf with various options. (versatile) <br />
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br />
     * And NullIgnored, OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param minNumber The min number of tweetId. (NullAllowed: if null, no from-condition)
     * @param maxNumber The max number of tweetId. (NullAllowed: if null, no to-condition)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setTweetId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, getCValueTweetId(), "TWEET_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetIdList The collection of tweetId as inScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setTweetId_InScope(Collection<Long> tweetIdList) {
        doSetTweetId_InScope(tweetIdList);
    }

    protected void doSetTweetId_InScope(Collection<Long> tweetIdList) {
        regINS(CK_INS, cTL(tweetIdList), getCValueTweetId(), "TWEET_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     * @param tweetIdList The collection of tweetId as notInScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setTweetId_NotInScope(Collection<Long> tweetIdList) {
        doSetTweetId_NotInScope(tweetIdList);
    }

    protected void doSetTweetId_NotInScope(Collection<Long> tweetIdList) {
        regINS(CK_NINS, cTL(tweetIdList), getCValueTweetId(), "TWEET_ID");
    }

    /**
     * IsNull {is null}. And OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setTweetId_IsNull() { regTweetId(CK_ISN, DOBJ); }

    /**
     * IsNotNull {is not null}. And OnlyOnceRegistered. <br />
     * TWEET_ID: {PK, ID, NotNull, BIGINT(19)}
     */
    public void setTweetId_IsNotNull() { regTweetId(CK_ISNN, DOBJ); }

    protected void regTweetId(ConditionKey k, Object v) { regQ(k, v, getCValueTweetId(), "TWEET_ID"); }
    abstract protected ConditionValue getCValueTweetId();

    /**
     * Equal(=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as equal. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_Equal(String message) {
        doSetMessage_Equal(fRES(message));
    }

    protected void doSetMessage_Equal(String message) {
        regMessage(CK_EQ, message);
    }

    /**
     * NotEqual(&lt;&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as notEqual. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_NotEqual(String message) {
        doSetMessage_NotEqual(fRES(message));
    }

    protected void doSetMessage_NotEqual(String message) {
        regMessage(CK_NES, message);
    }

    /**
     * GreaterThan(&gt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as greaterThan. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_GreaterThan(String message) {
        regMessage(CK_GT, fRES(message));
    }

    /**
     * LessThan(&lt;). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as lessThan. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_LessThan(String message) {
        regMessage(CK_LT, fRES(message));
    }

    /**
     * GreaterEqual(&gt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as greaterEqual. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_GreaterEqual(String message) {
        regMessage(CK_GE, fRES(message));
    }

    /**
     * LessEqual(&lt;=). And NullOrEmptyIgnored, OnlyOnceRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as lessEqual. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_LessEqual(String message) {
        regMessage(CK_LE, fRES(message));
    }

    /**
     * InScope {in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param messageList The collection of message as inScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_InScope(Collection<String> messageList) {
        doSetMessage_InScope(messageList);
    }

    public void doSetMessage_InScope(Collection<String> messageList) {
        regINS(CK_INS, cTL(messageList), getCValueMessage(), "MESSAGE");
    }

    /**
     * NotInScope {not in ('a', 'b')}. And NullOrEmptyIgnored, NullOrEmptyElementIgnored, SeveralRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param messageList The collection of message as notInScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_NotInScope(Collection<String> messageList) {
        doSetMessage_NotInScope(messageList);
    }

    public void doSetMessage_NotInScope(Collection<String> messageList) {
        regINS(CK_NINS, cTL(messageList), getCValueMessage(), "MESSAGE");
    }

    /**
     * PrefixSearch {like 'xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as prefixSearch. (NullAllowed: if null (or empty), no condition)
     */
    public void setMessage_PrefixSearch(String message) {
        setMessage_LikeSearch(message, cLSOP());
    }

    /**
     * LikeSearch with various options. (versatile) {like '%xxx%' escape ...}. And NullOrEmptyIgnored, SeveralRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)} <br />
     * <pre>e.g. setMessage_LikeSearch("xxx", new <span style="color: #FD4747">LikeSearchOption</span>().likeContain());</pre>
     * @param message The value of message as likeSearch. (NullAllowed: if null (or empty), no condition)
     * @param likeSearchOption The option of like-search. (NotNull)
     */
    public void setMessage_LikeSearch(String message, LikeSearchOption likeSearchOption) {
        regLSQ(CK_LS, fRES(message), getCValueMessage(), "MESSAGE", likeSearchOption);
    }

    /**
     * NotLikeSearch with various options. (versatile) {not like 'xxx%' escape ...} <br />
     * And NullOrEmptyIgnored, SeveralRegistered. <br />
     * MESSAGE: {NotNull, VARCHAR(140)}
     * @param message The value of message as notLikeSearch. (NullAllowed: if null (or empty), no condition)
     * @param likeSearchOption The option of not-like-search. (NotNull)
     */
    public void setMessage_NotLikeSearch(String message, LikeSearchOption likeSearchOption) {
        regLSQ(CK_NLS, fRES(message), getCValueMessage(), "MESSAGE", likeSearchOption);
    }

    protected void regMessage(ConditionKey k, Object v) { regQ(k, v, getCValueMessage(), "MESSAGE"); }
    abstract protected ConditionValue getCValueMessage();
    
    /**
     * Equal(=). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as equal. (NullAllowed: if null, no condition)
     */
    public void setMemberId_Equal(Long memberId) {
        doSetMemberId_Equal(memberId);
    }

    protected void doSetMemberId_Equal(Long memberId) {
        regMemberId(CK_EQ, memberId);
    }

    /**
     * NotEqual(&lt;&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as notEqual. (NullAllowed: if null, no condition)
     */
    public void setMemberId_NotEqual(Long memberId) {
        doSetMemberId_NotEqual(memberId);
    }

    protected void doSetMemberId_NotEqual(Long memberId) {
        regMemberId(CK_NES, memberId);
    }

    /**
     * GreaterThan(&gt;). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as greaterThan. (NullAllowed: if null, no condition)
     */
    public void setMemberId_GreaterThan(Long memberId) {
        regMemberId(CK_GT, memberId);
    }

    /**
     * LessThan(&lt;). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as lessThan. (NullAllowed: if null, no condition)
     */
    public void setMemberId_LessThan(Long memberId) {
        regMemberId(CK_LT, memberId);
    }

    /**
     * GreaterEqual(&gt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as greaterEqual. (NullAllowed: if null, no condition)
     */
    public void setMemberId_GreaterEqual(Long memberId) {
        regMemberId(CK_GE, memberId);
    }

    /**
     * LessEqual(&lt;=). And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberId The value of memberId as lessEqual. (NullAllowed: if null, no condition)
     */
    public void setMemberId_LessEqual(Long memberId) {
        regMemberId(CK_LE, memberId);
    }

    /**
     * RangeOf with various options. (versatile) <br />
     * {(default) minNumber &lt;= column &lt;= maxNumber} <br />
     * And NullIgnored, OnlyOnceRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param minNumber The min number of memberId. (NullAllowed: if null, no from-condition)
     * @param maxNumber The max number of memberId. (NullAllowed: if null, no to-condition)
     * @param rangeOfOption The option of range-of. (NotNull)
     */
    public void setMemberId_RangeOf(Long minNumber, Long maxNumber, RangeOfOption rangeOfOption) {
        regROO(minNumber, maxNumber, getCValueMemberId(), "MEMBER_ID", rangeOfOption);
    }

    /**
     * InScope {in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberIdList The collection of memberId as inScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setMemberId_InScope(Collection<Long> memberIdList) {
        doSetMemberId_InScope(memberIdList);
    }

    protected void doSetMemberId_InScope(Collection<Long> memberIdList) {
        regINS(CK_INS, cTL(memberIdList), getCValueMemberId(), "MEMBER_ID");
    }

    /**
     * NotInScope {not in (1, 2)}. And NullIgnored, NullElementIgnored, SeveralRegistered. <br />
     * MEMBER_ID: {IX, NotNull, BIGINT(19), FK to MEMBER}
     * @param memberIdList The collection of memberId as notInScope. (NullAllowed: if null (or empty), no condition)
     */
    public void setMemberId_NotInScope(Collection<Long> memberIdList) {
        doSetMemberId_NotInScope(memberIdList);
    }

    protected void doSetMemberId_NotInScope(Collection<Long> memberIdList) {
        regINS(CK_NINS, cTL(memberIdList), getCValueMemberId(), "MEMBER_ID");
    }

    /**
     * Set up InScopeRelation (sub-query). <br />
     * {in (select MEMBER_ID from MEMBER where ...)} <br />
     * MEMBER by my MEMBER_ID, named 'member'.
     * @param subQuery The sub-query of Member for 'in-scope'. (NotNull)
     */
    public void inScopeMember(SubQuery<MemberCB> subQuery) {
        assertObjectNotNull("subQuery<MemberCB>", subQuery);
        MemberCB cb = new MemberCB(); cb.xsetupForInScopeRelation(this); subQuery.query(cb);
        String subQueryPropertyName = keepMemberId_InScopeRelation_Member(cb.query()); // for saving query-value.
        registerInScopeRelation(cb.query(), "MEMBER_ID", "MEMBER_ID", subQueryPropertyName, "member");
    }
    public abstract String keepMemberId_InScopeRelation_Member(MemberCQ subQuery);

    /**
     * Set up NotInScopeRelation (sub-query). <br />
     * {not in (select MEMBER_ID from MEMBER where ...)} <br />
     * MEMBER by my MEMBER_ID, named 'member'.
     * @param subQuery The sub-query of Member for 'not in-scope'. (NotNull)
     */
    public void notInScopeMember(SubQuery<MemberCB> subQuery) {
        assertObjectNotNull("subQuery<MemberCB>", subQuery);
        MemberCB cb = new MemberCB(); cb.xsetupForInScopeRelation(this); subQuery.query(cb);
        String subQueryPropertyName = keepMemberId_NotInScopeRelation_Member(cb.query()); // for saving query-value.
        registerNotInScopeRelation(cb.query(), "MEMBER_ID", "MEMBER_ID", subQueryPropertyName, "member");
    }
    public abstract String keepMemberId_NotInScopeRelation_Member(MemberCQ subQuery);

    protected void regMemberId(ConditionKey k, Object v) { regQ(k, v, getCValueMemberId(), "MEMBER_ID"); }
    abstract protected ConditionValue getCValueMemberId();

    // ===================================================================================
    //                                                                     ScalarCondition
    //                                                                     ===============
    /**
     * Prepare ScalarCondition as equal. <br />
     * {where FOO = (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_Equal()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setXxx... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setYyy...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_Equal() {
        return xcreateSSQFunction(CK_EQ.getOperand());
    }

    /**
     * Prepare ScalarCondition as equal. <br />
     * {where FOO &lt;&gt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_NotEqual()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setXxx... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setYyy...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_NotEqual() {
        return xcreateSSQFunction(CK_NES.getOperand());
    }

    /**
     * Prepare ScalarCondition as greaterThan. <br />
     * {where FOO &gt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_GreaterThan()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_GreaterThan() {
        return xcreateSSQFunction(CK_GT.getOperand());
    }

    /**
     * Prepare ScalarCondition as lessThan. <br />
     * {where FOO &lt; (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_LessThan()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_LessThan() {
        return xcreateSSQFunction(CK_LT.getOperand());
    }

    /**
     * Prepare ScalarCondition as greaterEqual. <br />
     * {where FOO &gt;= (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_GreaterEqual()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_GreaterEqual() {
        return xcreateSSQFunction(CK_GE.getOperand());
    }

    /**
     * Prepare ScalarCondition as lessEqual. <br />
     * {where FOO &lt;= (select max(BAR) from ...)
     * <pre>
     * cb.query().<span style="color: #FD4747">scalar_LessEqual()</span>.max(new SubQuery&lt;TweetCB&gt;() {
     *     public void query(TweetCB subCB) {
     *         subCB.specify().setFoo... <span style="color: #3F7E5E">// derived column for function</span>
     *         subCB.query().setBar...
     *     }
     * });
     * </pre>
     * @return The object to set up a function. (NotNull)
     */
    public HpSSQFunction<TweetCB> scalar_LessEqual() {
        return xcreateSSQFunction(CK_LE.getOperand());
    }

    protected HpSSQFunction<TweetCB> xcreateSSQFunction(final String operand) {
        return new HpSSQFunction<TweetCB>(new HpSSQSetupper<TweetCB>() {
            public void setup(String function, SubQuery<TweetCB> subQuery, HpSSQOption<TweetCB> option) {
                xscalarCondition(function, subQuery, operand, option);
            }
        });
    }

    protected void xscalarCondition(String function, SubQuery<TweetCB> subQuery, String operand, HpSSQOption<TweetCB> option) {
        assertObjectNotNull("subQuery<TweetCB>", subQuery);
        TweetCB cb = xcreateScalarConditionCB(); subQuery.query(cb);
        String subQueryPropertyName = keepScalarCondition(cb.query()); // for saving query-value
        option.setPartitionByCBean(xcreateScalarConditionPartitionByCB()); // for using partition-by
        registerScalarCondition(function, cb.query(), subQueryPropertyName, operand, option);
    }
    public abstract String keepScalarCondition(TweetCQ subQuery);

    protected TweetCB xcreateScalarConditionCB() {
        TweetCB cb = new TweetCB();
        cb.xsetupForScalarCondition(this);
        return cb;
    }

    protected TweetCB xcreateScalarConditionPartitionByCB() {
        TweetCB cb = new TweetCB();
        cb.xsetupForScalarConditionPartitionBy(this);
        return cb;
    }

    // ===================================================================================
    //                                                                       MyselfDerived
    //                                                                       =============
    public void xsmyselfDerive(String function, SubQuery<TweetCB> subQuery, String aliasName, DerivedReferrerOption option) {
        assertObjectNotNull("subQuery<TweetCB>", subQuery);
        TweetCB cb = new TweetCB(); cb.xsetupForDerivedReferrer(this); subQuery.query(cb);
        String subQueryPropertyName = keepSpecifyMyselfDerived(cb.query()); // for saving query-value.
        registerSpecifyMyselfDerived(function, cb.query(), "TWEET_ID", "TWEET_ID", subQueryPropertyName, "myselfDerived", aliasName, option);
    }
    public abstract String keepSpecifyMyselfDerived(TweetCQ subQuery);

    /**
     * Prepare for (Query)MyselfDerived (SubQuery).
     * @return The object to set up a function for myself table. (NotNull)
     */
    public HpQDRFunction<TweetCB> myselfDerived() {
        return xcreateQDRFunctionMyselfDerived();
    }
    protected HpQDRFunction<TweetCB> xcreateQDRFunctionMyselfDerived() {
        return new HpQDRFunction<TweetCB>(new HpQDRSetupper<TweetCB>() {
            public void setup(String function, SubQuery<TweetCB> subQuery, String operand, Object value, DerivedReferrerOption option) {
                xqderiveMyselfDerived(function, subQuery, operand, value, option);
            }
        });
    }
    public void xqderiveMyselfDerived(String function, SubQuery<TweetCB> subQuery, String operand, Object value, DerivedReferrerOption option) {
        assertObjectNotNull("subQuery<TweetCB>", subQuery);
        TweetCB cb = new TweetCB(); cb.xsetupForDerivedReferrer(this); subQuery.query(cb);
        String subQueryPropertyName = keepQueryMyselfDerived(cb.query()); // for saving query-value.
        String parameterPropertyName = keepQueryMyselfDerivedParameter(value);
        registerQueryMyselfDerived(function, cb.query(), "TWEET_ID", "TWEET_ID", subQueryPropertyName, "myselfDerived", operand, value, parameterPropertyName, option);
    }
    public abstract String keepQueryMyselfDerived(TweetCQ subQuery);
    public abstract String keepQueryMyselfDerivedParameter(Object parameterValue);

    // ===================================================================================
    //                                                                        MyselfExists
    //                                                                        ============
    /**
     * Prepare for MyselfExists (SubQuery).
     * @param subQuery The implementation of sub query. (NotNull)
     */
    public void myselfExists(SubQuery<TweetCB> subQuery) {
        assertObjectNotNull("subQuery<TweetCB>", subQuery);
        TweetCB cb = new TweetCB(); cb.xsetupForMyselfExists(this); subQuery.query(cb);
        String subQueryPropertyName = keepMyselfExists(cb.query()); // for saving query-value.
        registerMyselfExists(cb.query(), subQueryPropertyName);
    }
    public abstract String keepMyselfExists(TweetCQ subQuery);

    // ===================================================================================
    //                                                                       MyselfInScope
    //                                                                       =============
    /**
     * Prepare for MyselfInScope (SubQuery).
     * @param subQuery The implementation of sub query. (NotNull)
     */
    public void myselfInScope(SubQuery<TweetCB> subQuery) {
        assertObjectNotNull("subQuery<TweetCB>", subQuery);
        TweetCB cb = new TweetCB(); cb.xsetupForMyselfInScope(this); subQuery.query(cb);
        String subQueryPropertyName = keepMyselfInScope(cb.query()); // for saving query-value.
        registerMyselfInScope(cb.query(), subQueryPropertyName);
    }
    public abstract String keepMyselfInScope(TweetCQ subQuery);

    // ===================================================================================
    //                                                                       Very Internal
    //                                                                       =============
    // very internal (for suppressing warn about 'Not Use Import')
    protected String xabCB() { return TweetCB.class.getName(); }
    protected String xabCQ() { return TweetCQ.class.getName(); }
    protected String xabLSO() { return LikeSearchOption.class.getName(); }
    protected String xabSSQS() { return HpSSQSetupper.class.getName(); }
}
