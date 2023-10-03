package chechi.nino.bootcamp.config;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApiUrlProviderConfig {

    private final Map<String, String> apiUrls = new HashMap<>();

    public ApiUrlProviderConfig() {
        // Initialize API URLs for different dashboards
        apiUrls.put("admin-dashboard", "http://localhost:3000/api/v1/admin-dashboard");
        apiUrls.put("user-dashboard", "http://localhost:3000/api/v1/user-dashboard");
        // Add more dashboards and their URLs as needed
    }

    public String getSecuredApiUrl(String dashboardKey) {
        return apiUrls.get(dashboardKey);
    }
}
