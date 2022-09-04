package com.yonggoo.batch_mac.balance.check_mgt;

import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtPerson;
import com.yonggoo.batch_mac.common.configuration.db.TradeDBConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;



//https://stackoverflow.com/questions/43245742/spring-batch-read-step-running-in-loop
@Configuration
@Slf4j
public class CheckMgtItemReader {

    private final TradeDBConfiguration tradeDBConfiguration;

    public CheckMgtItemReader(TradeDBConfiguration tradeDBConfiguration) {
        this.tradeDBConfiguration = tradeDBConfiguration;
    }

    @Bean(name ="myBatisPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<CheckMgtPerson> tradeDbRead() throws Exception {
        MyBatisPagingItemReader<CheckMgtPerson> myBatisPagingItemReader = new MyBatisPagingItemReader<CheckMgtPerson>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectCheckMgt");
        myBatisPagingItemReader.setSqlSessionFactory(tradeDBConfiguration.db2SqlSessionFactory());
        myBatisPagingItemReader.setParameterValues(parameters);
        return myBatisPagingItemReader;
    }

//    abstract protected void doReadPage(); //페이징해서 결과 만들어냄.abstract protected void doJumpToPage(int itemIndex); //혹시 모를 중복을 건너 뛰는 페이징 함수.
//    출처: https://sundries-in-myidea.tistory.com/141 [얇고 넓은 개발 블로그:티스토리]


    @Bean(name ="myBatisPagingItemCheckMgtActorReader")
    @StepScope
    public MyBatisPagingItemReader<CheckMgtActor> tradeDbCheckMgtActorRead() throws Exception {


//        final MyBatisPagingItemReader<CheckMgtActor> firstNameReader = null;
//        final MyBatisPagingItemReader<CheckMgtActor> lastNameReader = null;




        //firstNameReader
                //lastNameReader
        //데이터 읽어서..이슈 없으면면


       final MyBatisPagingItemReader<CheckMgtActor> first = firstNameReader();

        //first.

        final MyBatisPagingItemReader<CheckMgtActor> last = lastNameReader();

         mergeFirstLast(first, last);


        //boolean a = first.


//        System.out.println("aaaaaaaaaaa");
//        System.out.println(a);
        //first.read().


        MyBatisPagingItemReader<CheckMgtActor> myBatisPagingItemReader = new MyBatisPagingItemReader<CheckMgtActor>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectCheckMgtActor");
        myBatisPagingItemReader.setSqlSessionFactory(tradeDBConfiguration.db2SqlSessionFactory());
        myBatisPagingItemReader.setParameterValues(parameters);

//https://overpassion.tistory.com/64

        //myBatisPagingItemReader.read().

        //머지한 데이터를 아래에 담고!!
        //myBatisPagingItemReader

        //String b = myBatisPagingItemReader.read().getFirstName();
        //System.out.println("bbbbbbbbbbb");
        //System.out.println(b);
        //리턴해주면!!
        return myBatisPagingItemReader;




        //여기서 데이터 추출해와서...정리..
        //테스트방법!!! 테이블 조인법 해결하기!!
        // 요기서 많이 읽어서 넘겨야 하는건가??
        //실제 매핑되어야할 테이블로 간주..
        //여기서 데이터 추출해와서...정리..
        //테스트방법!!! 테이블 조인법 해결하기!!
        // 요기서 많이 읽어서 넘겨야 하는건가??
        //실제 매핑되어야할 테이블로 간주..


    }


        public MyBatisPagingItemReader<CheckMgtActor> firstNameReader() throws Exception {


            MyBatisPagingItemReader<CheckMgtActor> myBatisPagingItemReader = new MyBatisPagingItemReader<CheckMgtActor>();
            Map<String, Object> parameters = new HashMap<>();
            myBatisPagingItemReader.setQueryId("selectCheckMgtActor");
            myBatisPagingItemReader.setSqlSessionFactory(tradeDBConfiguration.db2SqlSessionFactory());
            myBatisPagingItemReader.setParameterValues(parameters);

//        FlatFileItemReader<CheckMgtActor> reader = new FlatFileItemReader<CheckMgtActor>();
//        reader.setResource(new ClassPathResource("firstname.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Person>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "firstName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                setTargetType(Person.class);
//            }});
//        }});
//

        return myBatisPagingItemReader;

    }


    public MyBatisPagingItemReader<CheckMgtActor> lastNameReader() throws Exception {


        MyBatisPagingItemReader<CheckMgtActor> myBatisPagingItemReader = new MyBatisPagingItemReader<CheckMgtActor>();
        Map<String, Object> parameters = new HashMap<>();
        myBatisPagingItemReader.setQueryId("selectCheckMgtActor");
        myBatisPagingItemReader.setSqlSessionFactory(tradeDBConfiguration.db2SqlSessionFactory());
        myBatisPagingItemReader.setParameterValues(parameters);

//        FlatFileItemReader<CheckMgtActor> reader = new FlatFileItemReader<CheckMgtActor>();
//        reader.setResource(new ClassPathResource("firstname.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Person>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "firstName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                setTargetType(Person.class);
//            }});
//        }});
//

        return myBatisPagingItemReader;

    }



//    @Override
//    protected void doReadPage() {
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        if (parameterValues != null) {
//            parameters.putAll(parameterValues);
//        }
//        parameters.put("_page", getPage());
//        parameters.put("_pagesize", getPageSize());
//        parameters.put("_skiprows", getPage() * getPageSize());
//        if (results == null) {
//            results = new CopyOnWriteArrayList<T>();
//        } else {
//            results.clear();
//        }
//        results.addAll(sqlSessionTemplate.<T>selectList(queryId, parameters));
//    }



    //  머지 후 리턴
    public MyBatisPagingItemReader<CheckMgtActor> mergeFirstLast(MyBatisPagingItemReader<CheckMgtActor> first, MyBatisPagingItemReader<CheckMgtActor> last) {
//        // do merge logic here


        //first.
        //first.

        return first;
    }

//
//    public MyBatisPagingItemReader<CheckMgtActor> firstNameReader() {
//
//        FlatFileItemReader<CheckMgtActor> reader = new FlatFileItemReader<CheckMgtActor>();
//        reader.setResource(new ClassPathResource("firstname.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Person>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "firstName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                setTargetType(Person.class);
//            }});
//        }});
//        return reader;
//
//    }
//
//    public MyBatisPagingItemReader<CheckMgtActor> lastNameReader() {
//
//        FlatFileItemReader<CheckMgtActor> reader = new FlatFileItemReader<Person>();
//        reader.setResource(new ClassPathResource("lastname.csv"));
//        reader.setLineMapper(new DefaultLineMapper<Person>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[] { "lastName" });
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
//                setTargetType(Person.class);
//            }});
//        }});
//        return reader;
//
//    }


}
