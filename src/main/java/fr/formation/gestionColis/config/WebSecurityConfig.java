package fr.formation.gestionColis.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// test
	// @Override
	// @Bean
	// protected UserDetailsService userDetailsService() {
	// final InMemoryUserDetailsManager manager = new
	// InMemoryUserDetailsManager();
	// manager.createUser(
	// User.withUsername("greg").password("greg").roles("USER").build());
	// return manager; // super.userDetailsService();
	// }

	private String authoritiesByUserName() {
		return "SELECT username, upper(role.nom) as authority"
				+ " FROM user, role WHERE user.username = ? AND user.roleid = role.id";
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth)
			throws DataSourceLookupFailureException, Exception {
		auth.jdbcAuthentication().rolePrefix("ROLE_").dataSource(this.dataSource())
				.usersByUsernameQuery(this.usersByUserName())
				.authoritiesByUsernameQuery(this.authoritiesByUserName());
	}

	@Bean
	public DataSource dataSource() throws DataSourceLookupFailureException {
		final JndiDataSourceLookup nslookup = new JndiDataSourceLookup();
		nslookup.setResourceRef(true);
		return nslookup.getDataSource("jdbc/gestioncolis");
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(this.dataSource());
		factory.setPersistenceProvider(new HibernatePersistenceProvider());
		factory.setPackagesToScan("fr.formation.gestioncolis.entity");
		return factory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		final JpaTransactionManager transaction = new JpaTransactionManager();
		transaction
				.setEntityManagerFactory(this.entityManagerFactory().getObject());
		return transaction;
	}

	private String usersByUserName() {
		return "SELECT USERNAME as username, PASSWORD as passowrd, true FROM user WHERE username = ?";
	}
}
