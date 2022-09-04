package com.yonggoo.batch_mac.common.configuration.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//mybatis transaction manager
//https://m.blog.naver.com/2feelus/220616977338
import javax.sql.DataSource;

@Configuration
//@MapperScan(value="com.yonggoo.batch_mac.balance.check_mgt.mapper.admin")
@MapperScan(value = "com.yonggoo.batch_mac.balance.check_mgt.mapper.admin", sqlSessionFactoryRef = "mySqlAdminSqlSessionFactory")
public class AdminDBConfiguration {

    private final ApplicationContext applicationContext;

    public AdminDBConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean(name="mySqlAdminDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.admin")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlAdminSqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory() throws Exception {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(db2DataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));

        //factoryBean.setTypeAliasesPackage("com.yonggoo.batch_mac.balance.check_mgt.mapper");
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/admin/*.xml"));
        return factoryBean.getObject();

    }

    @Bean(name = "mySqlAdminSqlSessionTemplate")
    //@Primary
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("mySqlAdminSqlSessionFactory") SqlSessionFactory mySqlAdminSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(mySqlAdminSqlSessionFactory);
    }

    @Bean(name = "tradeAdminTX")
    //@Primary
    public PlatformTransactionManager productTransactionManager(@Qualifier("mySqlAdminDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
