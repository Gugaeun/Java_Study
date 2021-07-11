package config;


import java.beans.PropertyVetoException;

//import org.apache.tomcat.jdbc.pool.DataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import springmvc.dao.MemberDao;
import springmvc.service.AuthService;
import springmvc.service.MemberRegisterService;

@Configuration
public class MemberConfig {
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		} catch(PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springdb1?characterEncoding=utf8&serverTimezone=Asia/Seoul");
		ds.setUser("root");
		ds.setPassword("1234");
		
		return ds;
	}
	
//	@Bean
//	public DataSource dataSource() {
//		DataSource ds = new DataSource();
//		ds.setDriverClassName("com.mysql.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://127.0.0.1:3306/springdb1?characterEncoding=utf8&serverTimezone=Asia/Seoul");
//		ds.setUsername("root");
//		ds.setPassword("1234");
////		ds.setInitialSize(2);
////		ds.setMaxActive(10);
////		ds.setTestWhileIdle(true);
////		ds.setMinEvictableIdleTimeMillis(60000 * 3);
////		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
//		
//		return ds;
//	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public AuthService authService() {
		AuthService authService = new AuthService();
		authService.setMemberDao(memberDao());
		
		return authService;
	}
	
}
