package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.event.ScreenEventRequest;
import chechi.nino.bootcamp.dto.event.ScreenEventResponse;

import java.util.List;

public interface ScreenEventService {

    List<ScreenEventResponse> getAllEvents ();

    ScreenEventResponse getEventById (Integer id);

    ScreenEventResponse createEvent (ScreenEventRequest request);

    ScreenEventResponse updateEvent (Integer id, ScreenEventRequest request);

    void deleteEventById (Integer id);
}
