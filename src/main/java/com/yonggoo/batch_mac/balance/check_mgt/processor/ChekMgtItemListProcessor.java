package com.yonggoo.batch_mac.balance.check_mgt.processor;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//
//@Configuration
//@Slf4j
//public class ChekMgtItemListProcessor implements ItemProcessor<CheckMgtActor, List<CheckMgtActor>> {
//    @Override
//    public CheckMgtActor process(CheckMgtActor actor) throws Exception {
//
//        int actorId = actor.getActorId();
//        String firstName = actor.getFirstName().toUpperCase();
//        String lastName = actor.getLastName().toUpperCase();
//        LocalDate lastUpdate =actor.getLastUpdate();
//        final CheckMgtActor transformedPerson = new CheckMgtActor(firstName, lastName);
//
////        //log.info("Converting (" + person + ") into (" + transformedPerson + ")");
////
////        List<CheckMgtActor> list = new ArrayList<CheckMgtActor>();
////
////        list.add(new CheckMgtActor( actorId,  firstName,  lastName,  lastUpdate));
////
////        log.info("list size ");
////        log.info(String.valueOf(list.size()));
//
//
//        return transformedPerson;
//
//        //return null;
//    }
//
//
//
//
//
////    @Override
////    public CheckMgtPerson process(final CheckMgtPerson person) throws Exception {
////
////        final String firstName = person.getFirstName().toUpperCase();
////        final String lastName = person.getLastName().toUpperCase();
////        final CheckMgtPerson transformedPerson = new CheckMgtPerson(firstName, lastName);
////
////        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
////        return transformedPerson;
////    }
//
//
//
//
//
//
//}
