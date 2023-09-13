package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.ScreenEventConverter;
import chechi.nino.bootcamp.dto.event.ScreenEventRequest;
import chechi.nino.bootcamp.dto.event.ScreenEventResponse;
import chechi.nino.bootcamp.entity.bar.ScreenEvent;
import chechi.nino.bootcamp.exception.EventNotFoundException;
import chechi.nino.bootcamp.repository.ScreenEventRepository;
import chechi.nino.bootcamp.service.ScreenEventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreenEventServiceImpl implements ScreenEventService {

    private final ScreenEventRepository screenEventRepository;
    private final ScreenEventConverter screenEventConverter;

    @Override
    public List<ScreenEventResponse> getAllEvents() {

        List<ScreenEvent> events = screenEventRepository.findAll();

        return events.stream()
                .map(screenEventConverter::toEventResponse)
                .toList();
    }

    @Override
    public ScreenEventResponse getEventById(Integer id) {

        ScreenEvent event = screenEventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found"));
        return screenEventConverter.toEventResponse(event);
    }

    @Override
    public ScreenEventResponse createEvent(ScreenEventRequest request) {

        ScreenEvent event = screenEventConverter.createEvent(request);
        ScreenEvent savedEvent = screenEventRepository.save(event);

        return screenEventConverter.toEventResponse(savedEvent);
    }

    @Override
    public ScreenEventResponse updateEvent(Integer id, ScreenEventRequest request) {

        ScreenEvent event = screenEventRepository.findById(id).orElseThrow(() -> new EventNotFoundException("Event not found"));

        event.setEventName(request.getEventName());
        event.setEventTime(request.getEventTime());
        event.setZoneType(request.getZoneType());

        ScreenEvent savedEvent = screenEventRepository.save(event);

        return screenEventConverter.toEventResponse(savedEvent);
    }

    @Override
    public void deleteEventById(Integer id) {
        screenEventRepository.deleteById(id);
    }
}
