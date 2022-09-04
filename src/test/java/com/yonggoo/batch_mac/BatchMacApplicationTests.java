package com.yonggoo.batch_mac;


import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilm;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActor;
import com.yonggoo.batch_mac.balance.check_mgt.model.CheckMgtFilmActorVO;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@MybatisTest
//@SpringBatchTest

//@RunWith(SpringRunner.class)
//@ImportAutoConfiguration(DatabaseConfiguration.class)
//@ContextConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@MybatisTest
//@Transactional
///@WebMvcTest(controllers = HelloController.class)

@SpringBootTest
class BatchMacApplicationTests {



//	private final CheckMgtService checkMgtService;
//
//
//
//	public BatchMacApplicationTests(CheckMgtService checkMgtService) {
//		this.checkMgtService = checkMgtService;
//	}

//	@Autowired
//	private JobExplorer jobExplorer;
//
//	@Autowired
//	@Qualifier("checkMgtJob")
//	private Job job;

//	@Autowired
//	private JobLauncherTestUtils jobLauncherTestUtils;

//	@Autowired
//	private StepExecution stepExcecution;
//
//	@Test
//	void contextLoads() {
//	}





	//@ParameterizedTest
//	@Test
//	void testJoin() throws Exception{
//
//
//
//
//		List<CheckMgtActor> lcma = new ArrayList<CheckMgtActor>();
//		List<CheckMgtFilm> lcf = new ArrayList<CheckMgtFilm>();
//		List<CheckMgtFilmActor> lcfa = new ArrayList<CheckMgtFilmActor>();
//		List<CheckMgtFilmActorVO> lcfaVo = new ArrayList<CheckMgtFilmActorVO>();
//
//
//
//
//		for(int i=1; i<11; i++)
//		{
//			CheckMgtActor cma = new CheckMgtActor(i, String.valueOf(i)+"_aaa", String.valueOf(i)+"_bbb", LocalDate.now());
//			lcma.add(cma);
//		}
//
//
//		for(int i=1; i<11; i++)
//		{
//			CheckMgtFilmActor cfa = new CheckMgtFilmActor(i, i, LocalDate.now());
//			lcfa.add(cfa);
//		}
//
//		for(int i=1; i<11; i++)
//		{
//			CheckMgtFilm cf = new CheckMgtFilm(i,Integer.toString(i)+"_title", Integer.toString(i)+"_desc", Integer.toString(LocalDate.now().getDayOfYear()));
//			lcf.add(cf);
//		}
//
//
//
//
//		int la_max =lcma.size();
//		int lf_max =lcf.size();
//		int lfa_max =lcfa.size();
//
//
//
//
//		for(int i = 0; i < la_max; i++)
//		{
//			for(int j=0; j < lf_max; j++)
//			{
//
//				if(lcma.get(i).getActorId() == lcfa.get(j).getActorId()) {
//
//					for (int k = 0; k < lfa_max; k++) {
//
//						if (lcfa.get(j).getFilmId() == lcf.get(k).getFilmId()) {
//
//							//System.out.println(lcma.get(i).getActorId());
//
//							CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(lcma.get(i).getActorId()
//									, lcma.get(i).getFirstName()
//									, lcma.get(i).getLastName()
//									, lcf.get(k).getFilmId()
//									, lcf.get(k).getTitle()
//									, lcf.get(k).getDescription()
//									, lcf.get(k).getReleaseYear());
//
//							lcfaVo.add(cmf);
//
//						}
//					}
//				}
//			}
//		}
//
//
//
//
//		System.out.println("lcfaVo size");
//		System.out.println(lcfaVo.size());
//
//		for( CheckMgtActor  cma  : lcma)
//		{
//			for(CheckMgtFilmActor  cfa : lcfa)
//			{
//				if(cfa.getActorId() == cma.getActorId())
//				{
//
//					for(CheckMgtFilm cf : lcf)
//					{
//						if(cf.getFilmId() == cfa.getFilmId())
//						{
//
//
//							CheckMgtFilmActorVO cmf = new CheckMgtFilmActorVO(cma.getActorId()
//									, cma.getFirstName()
//									, cma.getLastName()
//									, cf.getFilmId()
//									, cf.getTitle()
//									, cf.getDescription()
//									, cf.getRelease_year());
//
//							lcfaVo.add(cmf);
//
//						}
//					}
//				}
//			}
//		}
//
//
//
//
//
//		lcfaVo.forEach(s -> System.out.println(s.getFirstName()));
//		System.out.println("lcfaVo size");
//		System.out.println(lcfaVo.size());
//
//
//
//		//System.out.println(vo.getActorId());
//		//lcfaVo.forEach(s -> System.out.println(s));
//
//
//
//
//
//	}

//	@Test
//	void testjob() throws Exception{
//		JobParameters jobParameters = getNextJobParameters(getJobParameters());
//		//jobLauncherTestUtils.getJobLauncher();
//	}

//
//	@Test
//	void testStep() throws Exception{
//		JobParameters jobParameters = getNextJobParameters(getJobParameters());
//		jobLauncherTestUtils.getJobLauncher();
//
//		//jobParameters.getParameters().
//
//		//jobParameters.
//
////		ApplicationContext context = new AnnotationConfigApplicationContext(TestJobConfiguration.class);
////		JobLauncherTestUtils testUtils = context.getBean(JobLauncherTestUtils.class);
////		JobExecution execution = testUtils.launchStep("step1");
////		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
//
//	}
//
//
//
//	private JobParameters getJobParameters() {
//		JobParametersBuilder jobParameters = new JobParametersBuilder();
//		jobParameters.addLong("time", System.currentTimeMillis());
//		return jobParameters.toJobParameters();
//	}
//
//	private JobParameters getNextJobParameters(JobParameters jobParameters) {
//		String jobIdentifier = jobLauncherTestUtils.getJob().getName();
//		List<JobInstance> lastInstances = jobExplorer.getJobInstances(jobIdentifier, 0, 1);
//		JobParametersIncrementer incrementer = jobLauncherTestUtils.getJob().getJobParametersIncrementer();
//		if (lastInstances.isEmpty()) {
//			return incrementer.getNext(jobParameters);
//		} else {
//			List<JobExecution> lastExecutions = jobExplorer.getJobExecutions(lastInstances.get(0));
//			return incrementer.getNext(lastExecutions.get(0).getJobParameters());
//		}
//	}




	//현재거 다 파악한다음..
	//스탭 추가한다!!!
	//그리고..컨트롤러로..스탭추가!!
	//장애 발생 시 처리가능할듯!!


}






//	@Inject
//	@Qualifier(value = "Job1")
//	private Job job;
//
//	@Inject
//	private JobLauncher jobLauncher;
//
//	@Inject
//	private JobRepository jobRepository;
//
//	private JobLauncherTestUtils jobLauncherTestUtils;
//
//	private void initailizeJobLauncherTestUtils() {
//		this.jobLauncherTestUtils = new JobLauncherTestUtils();
//		this.jobLauncherTestUtils.setJobLauncher(jobLauncher);
//		this.jobLauncherTestUtils.setJobRepository(jobRepository);
//		this.jobLauncherTestUtils.setJob(job);
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		this.initailizeJobLauncherTestUtils();
//	}