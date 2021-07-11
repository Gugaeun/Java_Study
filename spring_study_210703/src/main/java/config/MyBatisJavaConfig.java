package config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import springmvc.dao.BoardMapperDao;
import springmvc.dao.BoardMapperDaoImpl;
import springmvc.service.BoardService;
import springmvc.service.BoardServiceImpl;

@Configuration
public class MyBatisJavaConfig {
	
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
	
	@Bean
//	(name = "mainSqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(
//			@Qualifier("main") DataSource dataSource
	) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		
		// snake케이스 형태를 camel케이스 형태로 변환해서 get하도록 설정
//		SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
//		org.apache.ibatis.session.Configuration configuration =  sqlSessionFactory.getConfiguration();
//        configuration.setMapUnderscoreToCamelCase(true);
//		factoryBean.setTypeAliasesPackage("main.dto");
//      factoryBean.setConfiguration(configuration);

//		factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		Resource configLocations = new ClassPathResource("/mybatis-config.xml");	// '/'생략가능
		factoryBean.setConfigLocation(configLocations);
		
//		factoryBean.setMapperLocations(applicationContext.getResources("classpath:main_mapper/**/*.xml"));
		Resource[] mapperLocations = new Resource[1];
		mapperLocations[0] = new ClassPathResource("/mybatis-mappers/boardMapperDao.xml");	// '/'생략가능
		factoryBean.setMapperLocations(mapperLocations);
		
		return factoryBean;
	}
	
	// 참고만 할것(전체로 mapper지정 방법)
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//	    sessionFactory.setDataSource(dataSource);
//	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//	    sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));
//	 
//	    Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
//	    sessionFactory.setConfigLocation(myBatisConfig);
//	 
//	    return sessionFactory.getObject();
//	}
	
	@Bean
	public BoardMapperDao boardMapperDao() throws Exception {
		BoardMapperDaoImpl boardDao = new BoardMapperDaoImpl();
		boardDao.setSqlSesstion(sqlSessionTemplate());
		
		return boardDao;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory().getObject());
	}
	
	@Bean
	public BoardService boardService() throws Exception {
		BoardServiceImpl svc = new BoardServiceImpl();
		svc.setBoardMapperDao(boardMapperDao());
		
		return svc;
	}
}
