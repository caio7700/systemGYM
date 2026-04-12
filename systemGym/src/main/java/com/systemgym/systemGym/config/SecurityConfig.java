package com.systemgym.systemgym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            // LIBERAÇÃO EXPLÍCITA: Permite acesso total à página de login e recursos estáticos
            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() 
            // TODO O RESTO: Exige que o usuário esteja logado
            .anyRequest().authenticated()
        )
        .formLogin((form) -> form
            .loginPage("/login") 
            .defaultSuccessUrl("/admin/dashboard", true)
            .permitAll() // Garante que a rota de processamento do login também seja pública
        )
        .logout((logout) -> logout
    .logoutUrl("/logout")
    .logoutSuccessUrl("/login?logout") // Adiciona um parâmetro na URL
    .invalidateHttpSession(true)
    .deleteCookies("JSESSIONID")
    .permitAll()
        );

    return http.build();
}

    @Bean
    public UserDetailsService userDetailsService() {
        // Criando um usuário padrão em memória (Usuário: admin / Senha: 123)
        @SuppressWarnings("deprecation")
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user);
    }


}
