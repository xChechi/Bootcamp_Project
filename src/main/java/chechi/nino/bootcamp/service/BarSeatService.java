package chechi.nino.bootcamp.service;

import chechi.nino.bootcamp.dto.bar.BarSeatResponse;

import java.util.List;

public interface BarSeatService {

    List<BarSeatResponse> getAllBarSeats ();

    BarSeatResponse getBarSeatById (Integer id);
}
