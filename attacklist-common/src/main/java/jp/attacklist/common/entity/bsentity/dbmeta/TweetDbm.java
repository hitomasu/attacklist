package jp.attacklist.common.entity.bsentity.dbmeta;

import java.util.List;
import java.util.Map;

import org.seasar.dbflute.DBDef;
import org.seasar.dbflute.Entity;
import org.seasar.dbflute.dbmeta.AbstractDBMeta;
import org.seasar.dbflute.dbmeta.PropertyGateway;
import org.seasar.dbflute.dbmeta.info.*;
import org.seasar.dbflute.dbmeta.name.*;
import jp.attacklist.common.entity.allcommon.*;
import jp.attacklist.common.entity.exentity.*;

/**
 * The DB meta of TWEET. (Singleton)
 * @author DBFlute(AutoGenerator)
 */
public class TweetDbm extends AbstractDBMeta {

    // ===================================================================================
    //                                                                           Singleton
    //                                                                           =========
    private static final TweetDbm _instance = new TweetDbm();
    private TweetDbm() {}
    public static TweetDbm getInstance() { return _instance; }

    // ===================================================================================
    //                                                                       Current DBDef
    //                                                                       =============
    public DBDef getCurrentDBDef() { return DBCurrent.getInstance().currentDBDef(); }

    // ===================================================================================
    //                                                                    Property Gateway
    //                                                                    ================
    protected final Map<String, PropertyGateway> _epgMap = newHashMap();
    {
        setupEpg(_epgMap, new EpgTweetId(), "tweetId");
        setupEpg(_epgMap, new EpgMessage(), "message");
        setupEpg(_epgMap, new EpgMemberId(), "memberId");
    }
    public PropertyGateway findPropertyGateway(String propertyName)
    { return doFindEpg(_epgMap, propertyName); }
    public static class EpgTweetId implements PropertyGateway {
        public Object read(Entity e) { return ((Tweet)e).getTweetId(); }
        public void write(Entity e, Object v) { ((Tweet)e).setTweetId(ctl(v)); }
    }
    public static class EpgMessage implements PropertyGateway {
        public Object read(Entity e) { return ((Tweet)e).getMessage(); }
        public void write(Entity e, Object v) { ((Tweet)e).setMessage((String)v); }
    }
    public static class EpgMemberId implements PropertyGateway {
        public Object read(Entity e) { return ((Tweet)e).getMemberId(); }
        public void write(Entity e, Object v) { ((Tweet)e).setMemberId(ctl(v)); }
    }

    // ===================================================================================
    //                                                                          Table Info
    //                                                                          ==========
    protected final String _tableDbName = "TWEET";
    protected final String _tablePropertyName = "tweet";
    protected final TableSqlName _tableSqlName = new TableSqlName("TWEET", _tableDbName);
    { _tableSqlName.xacceptFilter(DBFluteConfig.getInstance().getTableSqlNameFilter()); }
    public String getTableDbName() { return _tableDbName; }
    public String getTablePropertyName() { return _tablePropertyName; }
    public TableSqlName getTableSqlName() { return _tableSqlName; }

    // ===================================================================================
    //                                                                         Column Info
    //                                                                         ===========
    protected final ColumnInfo _columnTweetId = cci("TWEET_ID", "TWEET_ID", null, null, true, "tweetId", Long.class, true, true, "BIGINT", 19, 0, null, false, null, null, null, null, null);
    protected final ColumnInfo _columnMessage = cci("MESSAGE", "MESSAGE", null, null, true, "message", String.class, false, false, "VARCHAR", 140, 0, null, false, null, null, null, null, null);
    protected final ColumnInfo _columnMemberId = cci("MEMBER_ID", "MEMBER_ID", null, null, true, "memberId", Long.class, false, false, "BIGINT", 19, 0, null, false, null, null, "member", null, null);

    public ColumnInfo columnTweetId() { return _columnTweetId; }
    public ColumnInfo columnMessage() { return _columnMessage; }
    public ColumnInfo columnMemberId() { return _columnMemberId; }

    protected List<ColumnInfo> ccil() {
        List<ColumnInfo> ls = newArrayList();
        ls.add(columnTweetId());
        ls.add(columnMessage());
        ls.add(columnMemberId());
        return ls;
    }

    { initializeInformationResource(); }

    // ===================================================================================
    //                                                                         Unique Info
    //                                                                         ===========
    // -----------------------------------------------------
    //                                       Primary Element
    //                                       ---------------
    protected UniqueInfo cpui() { return hpcpui(columnTweetId()); }
    public boolean hasPrimaryKey() { return true; }
    public boolean hasCompoundPrimaryKey() { return false; }

    // ===================================================================================
    //                                                                       Relation Info
    //                                                                       =============
    // -----------------------------------------------------
    //                                      Foreign Property
    //                                      ----------------
    public ForeignInfo foreignMember() {
        Map<ColumnInfo, ColumnInfo> map = newLinkedHashMap(columnMemberId(), MemberDbm.getInstance().columnMemberId());
        return cfi("tweet_ibfk_1", "member", this, MemberDbm.getInstance(), map, 0, false, false, false, false, null, null, false, "tweetList");
    }

    // -----------------------------------------------------
    //                                     Referrer Property
    //                                     -----------------

    // ===================================================================================
    //                                                                        Various Info
    //                                                                        ============
    public boolean hasIdentity() { return true; }

    // ===================================================================================
    //                                                                           Type Name
    //                                                                           =========
    public String getEntityTypeName() { return "jp.attacklist.common.entity.exentity.Tweet"; }
    public String getConditionBeanTypeName() { return "jp.attacklist.common.entity.cbean.TweetCB"; }
    public String getBehaviorTypeName() { return "jp.attacklist.common.entity.exbhv.TweetBhv"; }

    // ===================================================================================
    //                                                                         Object Type
    //                                                                         ===========
    public Class<Tweet> getEntityType() { return Tweet.class; }

    // ===================================================================================
    //                                                                     Object Instance
    //                                                                     ===============
    public Entity newEntity() { return newMyEntity(); }
    public Tweet newMyEntity() { return new Tweet(); }

    // ===================================================================================
    //                                                                   Map Communication
    //                                                                   =================
    public void acceptPrimaryKeyMap(Entity e, Map<String, ? extends Object> m)
    { doAcceptPrimaryKeyMap((Tweet)e, m); }
    public void acceptAllColumnMap(Entity e, Map<String, ? extends Object> m)
    { doAcceptAllColumnMap((Tweet)e, m); }
    public Map<String, Object> extractPrimaryKeyMap(Entity e) { return doExtractPrimaryKeyMap(e); }
    public Map<String, Object> extractAllColumnMap(Entity e) { return doExtractAllColumnMap(e); }
}
