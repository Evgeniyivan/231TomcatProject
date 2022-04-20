package Users.config;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
//@ComponentScan(basePackages = "Users")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class AppConfig {
   private Environment environment;

   @Autowired
   public void setEnvironment(Environment environment) {
      this.environment = environment;
   }


   @Bean
   public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
      dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
      dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
      dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
      return dataSource;
   }

//   @Bean
//   public LocalSessionFactoryBean sessionFactory() {
//      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//      sessionFactory.setDataSource(dataSource());
//      sessionFactory.setPackagesToScan("Users.model");
//      sessionFactory.setHibernateProperties(hibernateProperties());
//      return sessionFactory;
//   }

//   @Bean
//   public HibernateTransactionManager transactionManager() {
//      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//      transactionManager.setSessionFactory(sessionFactory().getObject());
//      return transactionManager;
//   }
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));
      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      em.setJpaProperties(getHibernateProperties());
      return em;
   }
   @Bean
   public Properties getHibernateProperties() {
      Properties properties = new Properties();
      try {
         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
         properties.load(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
      }
      return properties;
   }
   @Bean
   public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory);
      return transactionManager;
   }
}