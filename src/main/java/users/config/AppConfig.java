package users.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
//@ComponentScan(basePackages = "Users")
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class AppConfig {

   @Autowired
   private Environment environment;

   @Autowired
   public void setEnvironment(Environment environment) {
      this.environment = environment;
   }


//   @Bean
//   public DataSource dataSource() {
//      BasicDataSource dataSource = new BasicDataSource();
//      dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//      dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//      dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//      dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//      return dataSource;
//   }

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
//   @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//      em.setDataSource(dataSource());
//      em.setPackagesToScan(environment.getRequiredProperty("db.entity.package"));
//      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//      em.setJpaProperties(getHibernateProperties());
//      return em;
//   }
//   @Bean
//   public Properties getHibernateProperties() {
//      Properties properties = new Properties();
//      try {
//         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
//         properties.load(inputStream);
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//      return properties;
//   }
//   @Bean
//   public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(entityManagerFactory);
//      return transactionManager;
//   }
@Bean
public DataSource getDataSource() {
   DriverManagerDataSource dataSource = new DriverManagerDataSource();
   dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));
   dataSource.setUrl(environment.getProperty("db.url"));
   dataSource.setUsername(environment.getProperty("db.username"));
   dataSource.setPassword(environment.getProperty("db.password"));
   return dataSource;
}

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(getDataSource());
      em.setPackagesToScan("users.model");

      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      em.setJpaProperties(additionalProperties());
      return em;
   }

   @Bean
   public PlatformTransactionManager getTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
      return transactionManager;
   }

   Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.dialect.MySQLDialect", environment.getProperty("hibernate.dialect.MySQLDialect"));
      properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
      properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
      return properties;
   }
}