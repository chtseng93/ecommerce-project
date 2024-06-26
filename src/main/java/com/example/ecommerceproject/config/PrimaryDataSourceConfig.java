package com.example.ecommerceproject.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;

@Configuration 
@MapperScan(basePackages = "com.example.ecommerceproject.mapper.primary", sqlSessionTemplateRef = "PrimarySessionTemplate")
public class PrimaryDataSourceConfig {

	@Bean("PrimaryDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean(name = "PrimarySessionFactory")
    @Primary
    public SqlSessionFactory primarySessionFactory(@Qualifier("PrimaryDataSource") DataSource dataSource) throws Exception {

		/**
	     * 使用 mybatis plus 配置
	     */
	    MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
	    bean.setDataSource(dataSource);
//	    b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
	    return bean.getObject();
    }

    @Bean(name = "PrimaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("PrimaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "PrimarySessionTemplate")
    @Primary
    public SqlSessionTemplate primarySessionTemplate(@Qualifier("PrimarySessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
       
    	return new SqlSessionTemplate(sqlSessionFactory);
    }
    
}