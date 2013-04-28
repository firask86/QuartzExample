package main;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * @author fkamal
 *
 */
public class SimpleSchedulerExample implements Job {

	public void execute(JobExecutionContext context) {
		// Say Hello to the World and display the date/time
		System.out.println("Hello World! - " + new Date());
	}

	public static void main(String[] args) {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched;
		try {
			sched = sf.getScheduler();
			JobDetail job = JobBuilder.newJob(SimpleSchedulerExample.class)
					.withIdentity("SimpleSchedulerExample").build();
			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("SimpleSchedulerExample")
					.withSchedule(
							SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInSeconds(5).repeatForever())
					.build();
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
