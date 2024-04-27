package siddham.paymentservicedemo.controllers;

import com.razorpay.RazorpayException;
import siddham.paymentservicedemo.dtos.InitiatePaymentRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siddham.paymentservicedemo.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/initiate")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException {
        return paymentService.initiatePayment(requestDto.getOrderId(), requestDto.getEmail());
    }
}
