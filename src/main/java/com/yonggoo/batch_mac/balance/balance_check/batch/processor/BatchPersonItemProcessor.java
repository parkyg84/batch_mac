package com.yonggoo.batch_mac.balance.balance_check.batch.processor;

import com.yonggoo.batch_mac.balance.balance_check.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BatchPersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(final Person person) throws Exception {

        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }
}
