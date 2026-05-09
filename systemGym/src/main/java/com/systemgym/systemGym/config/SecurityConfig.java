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
            .requestMatchers("/login", "/css/**", "/js/**").permitAll()
            
            // ADMIN: Controle total
            .requestMatchers("/admin/funcionario/**", "/admin/financeiro/**").hasRole("ADMIN")
            
            // INSTRUTOR: Foco nos treinos e alunos
            .requestMatchers("/admin/treino/**", "/admin/exercicio/**").hasAnyRole("ADMIN", "INSTRUTOR")
            
            // RECEPCIONISTA: Cadastros e pagamentos, mas sem deletar
            .requestMatchers("/admin/aluno/novo", "/admin/aluno/salvar").hasAnyRole("ADMIN", "RECEPCIONISTA")
            .requestMatchers("/admin/aluno/excluir/**").hasRole("ADMIN") // Só admin deleta
            
            .anyRequest().authenticated()
        )
        .formLogin((form) -> form
            .loginPage("/login")
            .defaultSuccessUrl("/admin/dashboard", true)
            .permitAll()
        )
        .logout((logout) -> logout.permitAll());

    return http.build();
}

@Bean
public UserDetailsService userDetailsService() {
    // Criando usuários de teste para cada perfil
    @SuppressWarnings("deprecation")
    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("123")
        .roles("ADMIN")
        .build();

    @SuppressWarnings("deprecation")
    UserDetails instrutor = User.withDefaultPasswordEncoder()
        .username("instrutor")
        .password("123")
        .roles("INSTRUTOR")
        .build();

    @SuppressWarnings("deprecation")
    UserDetails recepcao = User.withDefaultPasswordEncoder()
        .username("recepcao")
        .password("123")
        .roles("RECEPCIONISTA")
        .build();

    return new InMemoryUserDetailsManager(admin, instrutor, recepcao);
}


}
