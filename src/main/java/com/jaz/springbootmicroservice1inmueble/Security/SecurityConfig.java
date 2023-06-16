package com.jaz.springbootmicroservice1inmueble.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${service.security.secure-key-username}")
    private String SECURE_KEY_USERNAME;

    @Value("${service.security.secure-key-password}")
    private String SECURE_KEY_PASSWORD;

    @Value("${service.security.secure-key-username-2}")
    private String SECURE_KEY_USERNAME_2;

    @Value("${service.security.secure-key-password-2}")
    private String SECURE_KEY_PASSWORD_2;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
          AuthenticationManagerBuilder.class);
        /**
         * Para este método se obtienen los usuarios desde un archivo
         */
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("SECURE_KEY_USERNAME")
                .password(new BCryptPasswordEncoder().encode("SECURE_KEY_PASSWORD"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
                .and()
                .withUser("SECURE_KEY_USERNAME_2")
                .password(new BCryptPasswordEncoder().encode("SECURE_KEY_PASSWORD_2"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"))
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());

        /**
         * Todos los point estaran protegidos por eso el "/**" por ende
         * todos los usuarios deberán iniciar sesión y ser de tipo admin, el tipo de autenticación es basico y encaso de tener
         * un problema será redireccionados a una pagina, el csrf esta desabilidado ya que no tenemos sesiones, sin embargo en caso
         * de tener sesiones debería estar activado, el redireccion es adicional por lo mismo
         */
        return http.antMatcher("/**")
                .authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, Exception) -> {
                    response.sendRedirect("https//alejo-zam.com");
                } )
                .and()
                .build();

    }
        /**
         * Metodo para autenticar los usuarios que van a ingresar a las apliación, para este caso
         * se queman los valores correspondientes en modo de ejemplo
         */
       /* authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
                .and()
                .withUser("dev")
                .password(new BCryptPasswordEncoder().encode("dev"))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DEV"))
                .and()
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/


}
