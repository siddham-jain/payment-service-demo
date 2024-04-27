package siddham.paymentservicedemo.services;

import com.razorpay.RazorpayException;
import siddham.paymentservicedemo.paymentgateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentGateway paymentGateway;

    PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(Long orderId, String email) throws RazorpayException {
        return paymentGateway.generatePaymentLink(orderId, email);
    }
}