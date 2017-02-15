package test;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.Account;
import com.bean.TalkContent;
import com.dao.IAccountDao;
import com.dao.ITalkContentDao;
import com.dao.impl.AccountDao;
import com.dao.impl.TalkContentDao;
import com.utils.time.TimeInterval;

public class Test {

    public static void main(String[] args) {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
		"classpath:/com/config/spring-hibernate.xml");
	SessionFactory sessionFactory = context.getBean(SessionFactory.class);
	IAccountDao accountDao = new AccountDao();
	accountDao.setSessionFactory(sessionFactory);
	ITalkContentDao talkContentDao = new TalkContentDao();
	talkContentDao.setSessionFactory(sessionFactory);
	
	Account account = new Account();
	account.setName("cat");
	account.setPassword("123");
//	accountDao.save(account);
	account = accountDao.getByID(1);
	
	Account otherAccount = new Account();
	otherAccount.setName("dog");
	otherAccount.setPassword("123");
//	accountDao.save(otherAccount);
	otherAccount = accountDao.getByID(2);
	
	TalkContent talkContent = new TalkContent();
	talkContent.setTalk("the second content");
	talkContent.setSrcAccount(account);
	talkContent.setTargetAccount(otherAccount);
//	talkContentDao.save(talkContent);
	
	for(TalkContent temp : talkContentDao.getTenContentByAccounts(account, otherAccount, 0)){
	    System.out.println(temp);
	}
	
	
//	Timestamp start = new Timestamp(117, 1, 1, 1, 1, 1, 0);
//	Timestamp end = new Timestamp(119, 1, 1, 1, 1, 1, 0);
//	TimeInterval timeInterval = new TimeInterval(start, end);
//	
//	talkContent = null;
//	talkContent = talkContentDao.getTenContentByAccounts(account, otherAccount, timeInterval, 0).get(0);
//	System.out.println(talkContent);
    }

}
