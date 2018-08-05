package org.teomant.securityTest.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource( "classpath:jpa.properties" )
@EnableTransactionManagement
@EnableJpaRepositories( "org.teomant.securityTest" )
public class JpaConfig{

//    @Value( "${jndi.name}" ) private         String jndiName;
//    @Value( "${db.dialect}" ) private        String dialect;
//    @Value( "${db.default.schema}" ) private String schema;
//    @Value( "${db.show_sql}" ) private       String showSql;
//
//    @Bean
//    public DataSource dataSource() throws NamingException{
//        return ( DataSource ) new JndiTemplate().lookup( jndiName );
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory( DataSource dataSource ){
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
//                new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource( dataSource );
//
//        String entities   = ClassUtils.getPackageName( "org.teomant.securityTest" );
//        String converters = ClassUtils.getPackageName( Jsr310JpaConverters.class );
//        entityManagerFactoryBean.setPackagesToScan( entities , converters );
//
//        entityManagerFactoryBean.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put( Environment.DIALECT , dialect );
//        jpaProperties.put( Environment.SHOW_SQL , showSql );
//        jpaProperties.put( Environment.DEFAULT_SCHEMA , schema );
//        entityManagerFactoryBean.setJpaProperties( jpaProperties );
//
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            EntityManagerFactory entityManagerFactory ){
//        return new JpaTransactionManager( entityManagerFactory );
//    }

    //
    // If don`t want to store info about db on server
    //

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory ){
        return new JpaTransactionManager( entityManagerFactory );
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/securityTest");
        dataSource.setUsername( "postgres" );
        dataSource.setPassword( "tempus" );
        dataSource.setSchema( "public" );
        return dataSource;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(  ){
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "org.teomant.securityTest" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

}

