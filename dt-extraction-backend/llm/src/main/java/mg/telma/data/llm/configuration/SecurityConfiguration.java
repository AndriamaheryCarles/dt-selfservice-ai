package mg.telma.data.llm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   private static final String[] WHITE_LIST_URL = {
          "/v3/api-docs",
          "/v3/api-docs/**",
          "/swagger-ui/**",
          "/swagger-ui.html",
          "/api/v1/llm/**"
   };


   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
         .csrf(AbstractHttpConfigurer::disable)
             .authorizeHttpRequests(
                    request -> request
                           .requestMatchers(WHITE_LIST_URL).permitAll()
                           .requestMatchers(HttpMethod.OPTIONS).permitAll()
                           .anyRequest()
                           .authenticated()
             ).sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
             .formLogin(AbstractHttpConfigurer::disable);
      return http.build();
   }
}
