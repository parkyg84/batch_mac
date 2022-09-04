package com.yonggoo.batch_mac.balance.check_mgt.processor;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ChekMgtItemProcessor implements ItemProcessor<CheckMgtPerson, CheckMgtPerson> {
    @Override
    public CheckMgtPerson process(final CheckMgtPerson person) throws Exception {

        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final CheckMgtPerson transformedPerson = new CheckMgtPerson(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }





//    @Override
//    public CheckMgtPerson process(final CheckMgtPerson person) throws Exception {
//
//        final String firstName = person.getFirstName().toUpperCase();
//        final String lastName = person.getLastName().toUpperCase();
//        final CheckMgtPerson transformedPerson = new CheckMgtPerson(firstName, lastName);
//
//        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
//        return transformedPerson;
//    }






}
