package com.yonggoo.batch_mac.balance.balance_check.batch.processor;

import com.yonggoo.batch_mac.balance.balance_check.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@Slf4j
public class PaymentItemProcessor implements ItemProcessor<Payment, Payment> {

    @Bean(name ="myBatisPaymentProcessor")
    @StepScope
    public Payment process(final Payment payment)  throws  Exception{

        final int paymentId = payment.getPaymentId();
        final int customerId = payment.getCustomerId();
        final int staffId = payment.getStaffId();
        final int rentalId = payment.getRentalId();
        final double amount= payment.getAmount();
        final LocalDateTime paymentDate = payment.getPaymentDate();
        final LocalDateTime lastUpdate = payment.getLastUpdate();

        final Payment transformedPayment = new Payment(paymentId, customerId, staffId, rentalId, amount, paymentDate, lastUpdate);

        log.info("Converting (" + payment + ") into (" + transformedPayment + ")");

        return transformedPayment;
    }

}
