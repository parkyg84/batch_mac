package com.yonggoo.batch_mac.balance.balance_job.batch.processor;

import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import com.yonggoo.batch_mac.balance.balance_job.model.PersonNew;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class BatchJobPersonItemProcessor implements ItemProcessor<PersonNew, PersonNew> {
    @Override
    public PersonNew process(final PersonNew person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final PersonNew transformedPerson = new PersonNew(firstName, lastName);

        log.info("Converting New (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
