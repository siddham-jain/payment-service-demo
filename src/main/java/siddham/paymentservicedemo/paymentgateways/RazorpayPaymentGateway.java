package siddham.paymentservicedemo.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway{
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    @Override
    public String generatePaymentLink(Long orderId, String email) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount",1000000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 3600000);
        paymentLinkRequest.put("reference_id", "1568739");
        paymentLinkRequest.put("description","Sample payment link creation");
        JSONObject customer = new JSONObject();
        customer.put("name","+916666678210");
        customer.put("contact","Siddham");
        customer.put("email", email);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}