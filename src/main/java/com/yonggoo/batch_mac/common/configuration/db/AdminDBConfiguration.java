package com.yonggoo.batch_mac.common.configuration.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class AdminDBConfiguration {


    @Bean(name="mySqlAdminDataSource")
    @ConfigurationProperties(prefix = "spring.trade.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlAdminSqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("mySqlAdminDataSource") DataSource db2DataSource
            , ApplicationContext applicationContext) throws Exception {


        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(db2DataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/admin/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "mySqlAdminSqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("mySqlAdminSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }

    @Bean(name = "tradeAdminTX")
    public PlatformTransactionManager ProductTransactionManager(@Qualifier("mySqlAdminDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
