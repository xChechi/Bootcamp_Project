package chechi.nino.bootcamp.controller.api;

import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import chechi.nino.bootcamp.exception.ReservationNotFoundException;
import chechi.nino.bootcamp.exception.UserNotFoundException;
import chechi.nino.bootcamp.paypal.PayPalService;
import chechi.nino.bootcamp.service.BarReservationService;
import chechi.nino.bootcamp.service.CarReservationService;
import chechi.nino.bootcamp.service.RoomReservationService;
import chechi.nino.bootcamp.service.TableReservationService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static chechi.nino.bootcamp.paypal.PayPalService.getTotalAmount;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/paypal")
public class PayPalController {

    private final PayPalService payPalService;
    private final BarReservationService barReservationService;
    private final CarReservationService carReservationService;
    private final RoomReservationService roomReservationService;
    private final TableReservationService tableReservationService;

    @Secured("USER")
    @PostMapping("/payment")
    public String makePayment (@RequestParam("reservationId") Integer reservationId,
                                               @RequestParam("entityType") String entityType,
                                               @RequestParam("cancelUrl") String cancelUrl,
                                               @RequestParam("successUrl") String successUrl) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //Implement payer name in paypal service later
        System.out.println("Client username is " + username);

        if (authentication.getAuthorities().stream().noneMatch(role -> role.getAuthority().equals("USER"))) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unauthorized: User does not have the required role to make a payment.");
        }

        try {
            Object reservation = null;

            if ("bar".equals(entityType)) {
                reservation = barReservationService.getBarReservationById(reservationId);
            } else if ("car".equals(entityType)) {
                reservation = carReservationService.getCarReservationById(reservationId);
            } else if ("room".equals(entityType)) {
                reservation = roomReservationService.findReservationById(reservationId);
            } else if ("table".equals(entityType)) {
                reservation = tableReservationService.findReservationById(reservationId);
            }

            if (reservation == null) {
                //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReservationNotFoundException("Reservation not found"));
            }

            double totalAmount = getTotalAmount(reservation);
            System.out.println("Total amount in controller " + totalAmount);

            Payment payment = payPalService.createPayment(totalAmount, "USD", "PAYPAL", cancelUrl, successUrl);
            return payment.getLinks().get(1).getHref();
            //return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "redirect:/error";
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed" + e.getMessage());
        }
    }
}
