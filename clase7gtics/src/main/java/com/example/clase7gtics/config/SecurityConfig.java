package com.example.clase7gtics.config;

import com.example.clase7gtics.entity.Usuario;
import com.example.clase7gtics.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class SecurityConfig {

    final DataSource dataSource;
    final UsuarioRepository usuarioRepository;

    public SecurityConfig(DataSource dataSource, UsuarioRepository usuarioRepository) {
        this.dataSource = dataSource;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/processLogin")
                .usernameParameter("email")
                .passwordParameter("contrasenia")
                .successHandler((request, response, authentication) -> {

                    HttpSession session = request.getSession();
                    Usuario usuario = usuarioRepository.findByEmail(authentication.getName());
                    session.setAttribute("usuario", usuario);

                    String rol = "";
                    for (GrantedAuthority role : authentication.getAuthorities()) {
                        rol = role.getAuthority();
                        break;
                    }

                    switch (rol) {
                        case "ADMIN":
                            response.sendRedirect("/admin");
                            break;
                        case "PROFESOR":
                            response.sendRedirect("/profesor");
                            break;
                        case "ALUMNO":
                            response.sendRedirect("/alumno");
                            break;
                        default:
                            response.sendRedirect("/");
                            break;
                    }
                });

        http.authorizeHttpRequests()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/profesor/**").hasAuthority("PROFESOR")
                .requestMatchers("/alumno/**").hasAuthority("ALUMNO")
                .anyRequest().permitAll();

        http.logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);

        String sql1 = "SELECT email, pwd, activo FROM usuario WHERE email = ?";
        String sql2 = "SELECT u.email, r.nombre FROM usuario u INNER JOIN rol r ON u.idrol = r.idrol WHERE u.email = ?";

        jdbc.setUsersByUsernameQuery(sql1);
        jdbc.setAuthoritiesByUsernameQuery(sql2);
        return jdbc;
    }
}
