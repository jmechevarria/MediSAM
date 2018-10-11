/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medisam.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author jm
 */
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //                .antMatchers("/login").permitAll()
                .antMatchers("/login").anonymous()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/**/favicon.ico").permitAll()
                .antMatchers("/access_denied").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                //                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
//        http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Autowired
    DataSource dataSource;

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("tim").password(passwordEncoder().encode("123")).roles("ADMIN")
//                .and()
//                .withUser("joe").password(passwordEncoder().encode("234")).roles("USER");
        builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled from user where username=?")
                //                .authoritiesByUsernameQuery("select username,role.name "
                //                        + "from user inner join user_role on user.id=user_role.user_id "
                //                        + "inner join role on role.id=user_role.role_id where username=?");
                .authoritiesByUsernameQuery("select role.id, role.name  "
                        + "from role inner join user_role on role.id=user_role.role_id "
                        + "inner join user on user.id=user_role.user_id "
                        + "where username=?");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
//        expressionHandler.setDefaultRolePrefix("");
        web.expressionHandler(expressionHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy("ADMIN > USER");
        return r;
    }

//    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
//        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//        defaultWebSecurityExpressionHandler.setDefaultRolePrefix("");
//        return defaultWebSecurityExpressionHandler;
//    }
}
