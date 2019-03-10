package com;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    String dbUrl;
    DatabaseConfig(){
            dbUrl = System.getenv("DATABASE_URL");
            if (dbUrl == null) {
                dbUrl = "jdbc:postgresql://ec2-54-197-232-203.compute-1.amazonaws.com:5432/d7ds09u4nk096a?sslmode=require&user=zawptgjwfgsgug&password=808d36f78981bac1fef115f95cdb49ec17e5da9fc3a99ef789484c106d40e7e8";
            }
    }
    @Bean
    public DataSource dataSource(){
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        } catch(Exception e){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");

            dataSource.setUrl(dbUrl);
            dataSource.setUsername("zawptgjwfgsgug");
            dataSource.setPassword("808d36f78981bac1fef115f95cdb49ec17e5da9fc3a99ef789484c106d40e7e8");
            return dataSource;
        }
    }
}
