package chechi.nino.bootcamp.converter;

import chechi.nino.bootcamp.dto.reservation_car.CarReservationRequest;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.entity.car.Car;
import chechi.nino.bootcamp.entity.reservation.CarReservation;
import chechi.nino.bootcamp.entity.user.User;
import chechi.nino.bootcamp.exception.CarNotFoundException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.repository.CarRepository;
import chechi.nino.bootcamp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CarReservationConverter {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final UserConverter userConverter;
    private final CarConverter carConverter;

    public CarReservation bookCar (Integer userId, CarReservationRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> new CarNotFoundException("Car not found"));

        return CarReservation.builder()
                .user(user)
                .car(car)
                .passengers(request.getPassengers())
                .carType(request.getCarType())
                .carModel(request.getCarModel())
                .reservationDate(request.getReservationDate())
                .dailyCharge(request.getCarType().getDailyCharge())
                .build();
    }

    public CarReservationResponse toCarReservationResponse (CarReservation carReservation) {

        return CarReservationResponse.builder()
                .user(userConverter.toUserResponse(carReservation.getUser()))
                .car(carConverter.toCarResponse(carReservation.getCar()))
                .passengers(carReservation.getPassengers())
                .carType(carReservation.getCarType())
                .carModel(carReservation.getCarModel())
                .reservationDate(carReservation.getReservationDate())
                .dailyCharge(carReservation.getDailyCharge())
                .build();
    }
}
