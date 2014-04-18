package com.blog.samples.batch.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/import-accounts-job-context.xml", "/test-context.xml" })
public class ImportAccountsIntegrationTest
{

	@Autowired
	private JobLauncher jobLauncher_i;
	@Autowired
	private Job job_i;
	@Autowired
	private JdbcTemplate jdbcTemplate_i;
	@Value("file:src/test/resources/input/accounts.txt")
	private Resource accountsResource;
	@Value("file:src/test/resources/input/accountsError.txt")
	private Resource accountsErrorResource;
	
	@Before
	public void setUp() throws Exception
	{
		jdbcTemplate_i.update("delete from account");		
	}

	@Test
	public void importAccountDataTest() throws Exception
	{
		int startingCount = jdbcTemplate_i.queryForInt("select count(*) from account");
		jobLauncher_i.run(job_i, new JobParametersBuilder().addString("inputResource", accountsResource.getFile().getAbsolutePath())
															.addLong("timestamp", System.currentTimeMillis())
															.toJobParameters());

		int accountsAdded = 10;
		Assert.assertEquals(startingCount + accountsAdded, jdbcTemplate_i.queryForInt("select count(*) from account"));
	}

	@Test
	public void importAccountDataErrorTest() throws Exception
	{
		int startingCount = jdbcTemplate_i.queryForInt("select count(*) from account");
		jobLauncher_i.run(job_i, new JobParametersBuilder().addString("inputResource", accountsErrorResource.getFile().getAbsolutePath())
															.addLong("timestamp", System.currentTimeMillis())
															.toJobParameters());

		int accountsAdded = 8;
		Assert.assertEquals(startingCount + accountsAdded, jdbcTemplate_i.queryForInt("select count(*) from account"));
	}
}