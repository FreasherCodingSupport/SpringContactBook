package com.mycompany.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.mycompany.dao","com.mycompany.service"})
public class SpringRootConfig {
  //todo:services; 
    
 @Bean   
    public BasicDataSource getDataSource(){
      BasicDataSource ds = new BasicDataSource();
      ds.setDriverClassName("com.mysql.jdbc.Driver");
      ds.setUrl("jdbc:mysql://localhost:3306/cd_db");
      ds.setUsername("root");
      ds.setPassword("sqldb");
      ds.setMaxTotal(2);
      ds.setInitialSize(2);
      ds.setTestOnBorrow(true);
      ds.setValidationQuery("SELECT 1");
      ds.setDefaultAutoCommit(true);
      return ds;
   
    }
}
