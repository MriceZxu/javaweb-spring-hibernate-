package com.hql;

import java.util.ArrayList;
import java.util.List;

import com.bean.Account;
import com.utils.time.TimeInterval;

public class HQLGenerator {

    private static final String AND = "and";
    private static final String EQUAL = "=";
    private static final String SINGLE_QUOTE = "'";
    private static final String BLANK = " ";
    private static final String CLASSNAME_PLACER = "classname";
    private static final String COLUMN_PLACER = "column";
    private static final String VALUE_PLACER = "value";
    private static final String ALL_QUERY = "from " + CLASSNAME_PLACER;
    private static final String SINGLE_QUERY = ALL_QUERY + " obj where obj." + COLUMN_PLACER + BLANK + EQUAL + BLANK
	    + SINGLE_QUOTE + VALUE_PLACER + SINGLE_QUOTE;

    private static final String GET10CONTENT_ACCOUNTS_ID_HQL = "from TalkContent obj where ((obj.srcAccount = '"
	    + VALUE_PLACER + "' and targetAccount = '" + VALUE_PLACER + "')  or (obj.srcAccount = '" + VALUE_PLACER
	    + "' and targetAccount = '" + VALUE_PLACER + "')) and obj.id >= '" + VALUE_PLACER + "' and obj.id <= '"
	    + VALUE_PLACER + "'";

    private static final String GET10CONTENT_ACCOUNTS_ID_TIME_HQL = GET10CONTENT_ACCOUNTS_ID_HQL + " and time >= '"
	    + VALUE_PLACER + "' and time <= '" + VALUE_PLACER + "'";

    /**
     * 生成对单个列的值进行查询的hql语句
     * 
     * @param classname
     *            类名
     * @param column
     *            列名
     * @param value
     *            值
     * @return
     */
    public static String generateSingleEqualQueryHql(String classname, String column, String value) {
	return SINGLE_QUERY.replace(CLASSNAME_PLACER, classname).replace(COLUMN_PLACER, column).replace(VALUE_PLACER,
		value);
    }

    /**
     * 生成对多个列的查询的hql语句
     * 
     * @param classname
     * @param columns
     * @param values
     * @return
     */
    public static String generateMultiEqualQueryHql(String classname, List<String> columns, List<String> values) {
	if (columns.size() != values.size()) {
	    return null;
	}
	String hql = SINGLE_QUERY.replace(CLASSNAME_PLACER, classname).replace(COLUMN_PLACER, columns.get(0))
		.replace(VALUE_PLACER, values.get(0));
	for (int i = 1; i < columns.size(); i++) {
	    hql += BLANK + AND + BLANK + columns.get(i) + BLANK + EQUAL + BLANK + SINGLE_QUOTE + values.get(i)
		    + SINGLE_QUOTE;
	}
	return hql;
    }

    /**
     * 生成获取该类所有存储的对象的hql语句
     * 
     * @param classname
     * @return
     */
    public static String generateAllQuery(String classname) {
	return ALL_QUERY.replace(CLASSNAME_PLACER, classname);
    }

    public static String generateGet10ContentByAccountsAndID(Account account, Account otherAccount, int contentStartID) {
	List<String> values = new ArrayList<String>();
	values.add(String.valueOf(account.getId()));
	values.add(String.valueOf(otherAccount.getId()));
	values.add(String.valueOf(otherAccount.getId()));
	values.add(String.valueOf(account.getId()));
	values.add(String.valueOf(contentStartID));
	values.add(String.valueOf(contentStartID + 10));
	String hql = GET10CONTENT_ACCOUNTS_ID_HQL;
	for (int i = 0; i < values.size(); i++) {
	    hql = hql.replaceFirst(VALUE_PLACER, values.get(i));
	}
	System.out.println(hql);
	return hql;
    }

    public static String generateGet10ContentByAccountAndIDAndTime(Account account, Account otherAccount, int contentStartID, TimeInterval timeInterval){
	List<String> values = new ArrayList<String>();
	values.add(String.valueOf(account.getId()));
	values.add(String.valueOf(otherAccount.getId()));
	values.add(String.valueOf(otherAccount.getId()));
	values.add(String.valueOf(account.getId()));
	values.add(String.valueOf(contentStartID));
	values.add(String.valueOf(contentStartID + 10));
	values.add(timeInterval.start.toString());
	values.add(timeInterval.end.toString());
	String hql = GET10CONTENT_ACCOUNTS_ID_TIME_HQL;
	for (int i = 0; i < values.size(); i++) {
	    hql = hql.replaceFirst(VALUE_PLACER, values.get(i));
	}
	System.out.println(hql);
	return hql;
    }

}
