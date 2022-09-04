package com.yonggoo.batch_mac.common.configuration.db;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
public class TradeDBConfiguration {

    private final ApplicationContext applicationContext;

    public TradeDBConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean(name="mySqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.trade")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mySqlSqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(db2DataSource());
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/trade/*.xml"));
        return factoryBean.getObject();
    }


    @Bean(name = "mySqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("mySqlSqlSessionFactory") SqlSessionFactory db2SqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }

    @Primary
    @Bean(name = "tradeTX")
    public PlatformTransactionManager productTransactionManager(@Qualifier("mySqlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}




//    private final DataSource dataSource;
//    private final ApplicationContext applicationContext;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mappers/*.xml"));
//
//        return sqlSessionFactoryBean.getObject();
//    }
//


//https://blog.jiniworld.me/69  //설정!!

//    private final DataSource dataSource;
//    private final ApplicationContext applicationContext;
//
//    public TradeDBConfiguration(DataSource dataSource,
//                                ApplicationContext applicationContext) {
//        this.dataSource = dataSource;
//        this.applicationContext = applicationContext;
//    }


//    @Primary
//    @Bean(name = "mySqlSqlSessionFactory")
//    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("mySqlDataSource") DataSource db2DataSource
//            , ApplicationContext applicationContext) throws Exception {
//
//
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(db2DataSource);
//        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
//        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/trade/*.xml"));
//        return factoryBean.getObject();
//    }


//sakira db
//https://eboong.tistory.com/85
//https://blog.sqlauthority.com/2020/02/15/mysql-download-sample-database-sakila-world-employee/
//mysql>Source c:/temp/sakila-db/sakila-schema.sql
//mysql>Source c:/temp/sakila-db/sakila-data.sql
//    https://www.fun-coding.org/mysql_pratices1.html