package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.BarSeatConverter;
import chechi.nino.bootcamp.dto.bar.BarSeatResponse;
import chechi.nino.bootcamp.entity.bar.BarSeat;
import chechi.nino.bootcamp.exception.BarSeatNotFoundException;
import chechi.nino.bootcamp.repository.BarSeatRepository;
import chechi.nino.bootcamp.service.BarSeatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BarSeatServiceImpl implements BarSeatService {

    private final BarSeatRepository barSeatRepository;
    private final BarSeatConverter barSeatConverter;

    @Override
    public List<BarSeatResponse> getAllBarSeats() {

        List<BarSeat> barSeatList = barSeatRepository.findAll();

        return barSeatList.stream().map(barSeatConverter::toBarSeatResponse).toList();
    }

    @Override
    public BarSeatResponse getBarSeatById(Integer id) {

        BarSeat barSeat = barSeatRepository.findById(id).orElseThrow(() -> new BarSeatNotFoundException("Seat not found"));
        return barSeatConverter.toBarSeatResponse(barSeat);
    }
}
