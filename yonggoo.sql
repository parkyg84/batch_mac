  queryProvider.setSelectClause("id, first_name, last_name");
        queryProvider.setFromClause("from monkey.people2");
        
       
 

select *
from monkey.people3
;

delete table monkey.batch_job_instance 
;


select * from monkey.batch_job_execution 
;

truncate table monkey.batch_job_execution 
;
truncate table monkey.batch_job_execution_context 
;
truncate table monkey.batch_job_execution_params 
;
truncate table monkey.batch_job_execution_seq 
;
truncate table monkey.batch_job_instance 
;
truncate table monkey.batch_job_seq 
;
truncate table monkey.batch_step_execution 
;

truncate table monkey.batch_step_execution_context 
;
truncate table monkey.batch_step_execution_seq
;




 


commit
;


select *
from monkey.people2
;
insert into monkey.people2(firstName, lastName)
values('abcd','efg')
;


select *
from monkey.people2
;

select *
from monkey.people3

;
 
truncate table monkey.people2
;
truncate table monkey.people3
;

create table monkey.people2 (
id INT auto_increment primary key
,firstName varchar(25)
, lastName varchar(25)
);

create table monkey.people3 (
id INT auto_increment primary key
,firstName varchar(25)
, lastName varchar(25)
);
select version()
;
--10.7.3-MariaDB


------------------------------------------

delete from monkey.BATCH_JOB_EXECUTION_CONTEXT;

delete from monkey.BATCH_JOB_EXECUTION_PARAMS;

delete from monkey.BATCH_JOB_EXECUTION_SEQ;

delete from monkey.BATCH_JOB_SEQ;

delete from monkey.BATCH_STEP_EXECUTION_CONTEXT;

delete from monkey.BATCH_STEP_EXECUTION_SEQ;

delete from monkey.BATCH_STEP_EXECUTION;

delete from monkey.BATCH_JOB_EXECUTION;

delete from BATCH_JOB_INSTANCE;

 

INSERT INTO BATCH_STEP_EXECUTION_SEQ values(0, '0');

INSERT INTO BATCH_JOB_EXECUTION_SEQ values(0, '0');

INSERT INTO BATCH_JOB_SEQ values(0, '0');