package chechi.nino.bootcamp.config;

import chechi.nino.bootcamp.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    /*
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/", "/api/v1/home", "/api/v1/register", "/api/v1/login", "/api/v1/forgotten-password", "/api/v1/demo", "/css/**",
                                                  "/img/**", "/lib/**", "/js/**").permitAll()
                        //.requestMatchers("/api/v1/user/**").hasRole("USER")
                        //.requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                //.formLogin(form -> form
                //        .loginPage("/login")
                //        .defaultSuccessUrl("/demo").permitAll())
                //.logout(LogoutConfigurer::permitAll)
                //.exceptionHandling(e -> e.accessDeniedPage("/access-denied"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable) // (csrf -> csrf.disable()) // Commented after
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**", "/", "/api/v1/home", "/api/v1/login", "/api/v1/register").permitAll()
                        .requestMatchers("/css/**", "/img/**", "/lib/**", "/js/**").permitAll()
                        .requestMatchers("/api/v1/demo", "/api/**", "/**").permitAll() //.hasAnyRole("USER", "ADMIN")   // Added after
                        //.requestMatchers("/swagger-ui/**", "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                //.formLogin(form -> form
                //        .loginPage("/api/v1/login")
                //        .loginProcessingUrl("/api/v1/login")
                //        .defaultSuccessUrl("/api/v1/demo")
                //        .failureUrl("/api/v1/login?error=true")
                //        .permitAll()
                //)
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // AuthFilter is mine, UsernameFilter is by default

        return http.build();
    }
}