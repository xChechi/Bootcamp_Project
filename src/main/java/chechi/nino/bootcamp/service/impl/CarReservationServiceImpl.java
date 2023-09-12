package chechi.nino.bootcamp.service.impl;

import chechi.nino.bootcamp.converter.CarReservationConverter;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationUpdateRequest;
import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.exception.CarNotFoundException;
import chechi.nino.bootcamp.exception.ReservationNotFoundException;
import chechi.nino.bootcamp.repository.CarRepository;
import chechi.nino.bootcamp.repository.CarReservationRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import chechi.nino.bootcamp.service.CarReservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarReservationServiceImpl implements CarReservationService {

    private final CarRepository carRepository;
    private final CarReservationRepository carReservationRepository;
    private final CarReservationConverter carReservationConverter;

    @Override
    public List<CarReservationResponse> getAllCarReservations() {

        List<CarReservation> reservations = carReservationRepository.findAll();
        List<CarReservationResponse> responses = new ArrayList<>();

        for (CarReservation r : reservations) {
            CarReservationResponse response = carReservationConverter.toCarReservationResponse(r);
            responses.add(response);
        }

        return responses;
    }

    @Override
    public CarReservationResponse getCarReservationById(Integer id) {

        CarReservation carReservation = carReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        return carReservationConverter.toCarReservationResponse(carReservation);
    }

    @Override
    public CarReservationResponse bookCarReservation(Integer userId, CarReservationRequest request) {

        CarReservation carReservation = carReservationConverter.bookCar(userId, request);
        CarReservation savedReservation = carReservationRepository.save(carReservation);

        int passengers = request.getPassengers();
        int carCapacity = request.getCarType().getSeats();

        if (passengers > carCapacity) {
            throw new IllegalArgumentException("Number of passengers exceeds car capacity");
        }

        return carReservationConverter.toCarReservationResponse(savedReservation);
    }

    @Override
    public CarReservationResponse updateReservation(Integer id, CarReservationUpdateRequest request) {

        CarReservation carReservation = carReservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> new CarNotFoundException("Car not found"));

        carReservation.setCar(car);
        carReservation.setCarType(request.getCarType());
        carReservation.setCarModel(request.getCarModel());
        carReservation.setPassengers(request.getPassengers());
        carReservation.setReservationDate(request.getReservationDate());
        carReservation.setDailyCharge(request.getCarType().getDailyCharge());

        CarReservation savedCarReservation = carReservationRepository.save(carReservation);

        int passengers = request.getPassengers();
        int carCapacity = request.getCarType().getSeats();

        if (passengers > carCapacity) {
            throw new IllegalArgumentException("Number of passengers exceeds car capacity");
        }

        return carReservationConverter.toCarReservationResponse(savedCarReservation);
    }

    @Override
    public void deleteCarReservationById(Integer id) {

        carReservationRepository.deleteById(id);

    }

    @Override
    public List<CarReservationResponse> searchByUser(Integer userId) {

        List<CarReservation> reservations = carReservationRepository.findAll();

        return reservations.stream()
                .filter(res -> {
                    Integer reservationUserId = res.getUser().getId();
                    return reservationUserId != null && reservationUserId.equals(userId);
                })
                .map(carReservationConverter::toCarReservationResponse)
                .toList();
    }

    @Override
    public List<CarReservationResponse> searchByCar(Integer carId) {

        List<CarReservation> reservations = carReservationRepository.findAll();

        return reservations.stream()
                .filter(res -> {
                    Integer reservationCarId = res.getCar().getId();
                    return reservationCarId != null && reservationCarId.equals(carId);
                })
                .map(carReservationConverter::toCarReservationResponse)
                .toList();
    }

    @Override
    public List<CarReservationResponse> searchByDate(LocalDate reservationDate) {

        List<CarReservation> reservations = carReservationRepository.searchByDate(reservationDate);

        return reservations.stream()
                .map(carReservationConverter::toCarReservationResponse)
                .toList();
    }
}
