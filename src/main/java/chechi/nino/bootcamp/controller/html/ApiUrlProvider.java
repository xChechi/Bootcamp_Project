package chechi.nino.bootcamp.controller.html;

import jakarta.servlet.http.HttpServletRequest;

public interface ApiUrlProvider {

    String getSecuredApiUrl ();

    String authorizeData (HttpServletRequest httpServletRequest);
}

