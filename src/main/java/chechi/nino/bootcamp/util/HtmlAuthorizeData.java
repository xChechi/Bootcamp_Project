package chechi.nino.bootcamp.util;

import chechi.nino.bootcamp.config.ApiUrlProviderConfig;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class HtmlAuthorizeData {

    private final ApiUrlProviderConfig apiUrlProviderConfig;
    public String authorizeData(HttpServletRequest httpServletRequest) {
        String jwtToken = (String) httpServletRequest.getSession().getAttribute("jwtToken");
        System.out.println("Retrieved JWT Token: " + jwtToken);

        if (jwtToken != null) {
            String authorizationHeader = "Bearer " + jwtToken;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);

            String apiUrl = apiUrlProviderConfig.getSecuredApiUrl("admin-dashboard");

            return restTemplate.getForObject(apiUrl, String.class, headers);
        }
        return null;
    }
}
