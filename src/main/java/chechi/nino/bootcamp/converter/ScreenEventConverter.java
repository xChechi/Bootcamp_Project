package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.event.ScreenEventRequest;
import chechi.nino.bootcamp.dto.event.ScreenEventResponse;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import org.springframework.stereotype.Component;

@Component
public class ScreenEventConverter {

    public ScreenEvent createEvent (ScreenEventRequest request) {

        return ScreenEvent.builder()
                .eventName(request.getEventName())
                .eventTime(request.getEventTime())
                .zoneType(request.getZoneType())
                .build();
    }

    public ScreenEventResponse toEventResponse (ScreenEvent event) {

        return ScreenEventResponse.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .eventTime(event.getEventTime())
                .zoneType(event.getZoneType())
                .build();
    }
}
