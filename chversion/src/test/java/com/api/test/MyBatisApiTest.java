package com.api.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class MyBatisApiTest {

	@Test
	public void test() {
		DataSource ds = new DriverManagerDataSource("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:XE", "choi", "1234");
		
		SqlSessionFactoryBean sql = new SqlSessionFactoryBean();
		sql.setDataSource(ds);
//		sql.setConfigLocation("classpath:/mybatis-config.xml");
		
		
	}

}
