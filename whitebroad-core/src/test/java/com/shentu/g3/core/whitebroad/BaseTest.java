package com.shentu.g3.core.whitebroad;

import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;

@DirtiesContext
@ContextConfiguration({"/whitebroad-core-spring/whitebroad-core-application.xml"}) //加载配置文件
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	@Qualifier("dataSourceWrite")
	protected DataSource dataSource;

	@BeforeClass
	public static void beforeClass() {
	}

	@Override
	@Qualifier("dataSourceWrite")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

}
