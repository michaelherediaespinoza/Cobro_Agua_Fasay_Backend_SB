package coac.fasay.cobroagua.securityconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    // Definir el UserDetailsService con una contraseña personalizada
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("fasay")
                .password(passwordEncoder().encode("Root.jjwx1"))  // Codificar la contraseña
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Definir el PasswordEncoder (BCrypt en este caso)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF si no es necesario
                .authorizeHttpRequests(authz -> authz
                        //.requestMatchers("/api/cliente/allclientes").permitAll()
                        //.requestMatchers("/api/cliente/save").permitAll()
                        //.requestMatchers("/api/user/getbyusu").permitAll()
                        .requestMatchers("/api/**").permitAll()  // Permitir acceso sin autenticación a todas las rutas que comienzan con /api/
                        //.requestMatchers("/api/cliente/allclientes").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())  // Configuración de inicio de sesión
                .httpBasic(Customizer.withDefaults());  // Configuración de autenticación básica
               // .cors(Customizer.withDefaults());  // Aplicar configuración CORS desde el bean
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // O especifica los orígenes permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "DELETE", "PUT")); // Permitir métodos específicos
        configuration.setAllowedHeaders(List.of("*")); // Permitir todos los encabezados
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
