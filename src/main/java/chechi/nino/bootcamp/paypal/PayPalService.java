package chechi.nino.bootcamp.paypal;
import chechi.nino.bootcamp.dto.reservation_bar.BarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_car.CarReservationResponse;
import chechi.nino.bootcamp.dto.reservation_room.RoomReservationResponse;
import chechi.nino.bootcamp.dto.reservation_table.TableReservationResponse;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PayPalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    public Payment createPayment(double total, String currency, String method, String cancelUrl, String successUrl) throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

        String formattedTotal = String.format(Locale.US, "%.2f", total);

        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(formattedTotal);
        System.out.println("Amount is " + amount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public static double getTotalAmount(Object reservation) {

        double totalAmount = 0.00;

        if (reservation instanceof BarReservationResponse) {
            totalAmount = ((BarReservationResponse) reservation).getTotalCharge();
        } else if (reservation instanceof CarReservationResponse) {
            totalAmount = ((CarReservationResponse) reservation).getDailyCharge();
        } else if (reservation instanceof RoomReservationResponse) {
            totalAmount = ((RoomReservationResponse) reservation).getTotalCharge();
        } else {
            totalAmount = ((TableReservationResponse) reservation).getTotalCharge();
        }

        return totalAmount;
    }
}

