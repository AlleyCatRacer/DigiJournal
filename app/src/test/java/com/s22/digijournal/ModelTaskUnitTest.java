package com.s22.digijournal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModelTaskUnitTest
{
	private ModelTask t1;
	private ModelTask t2;
	private ModelTask t3;
	private ModelTask t4;
	private ModelTask t5;
	private ModelTask t6;
	private ModelTask t7;
	private List<ModelTask> tasks;
	
	@Before public void setUp()
	{
		t1 = new ModelTask("Task 1", "Short description ay", "01/01/2023");
		t2 = new ModelTask("Task 2", "", "N/A");
		t3 = new ModelTask("Task 3", "V.short desc.", "");
		t4 = new ModelTask("Task 4", "Descriptive", "21/05/2022");
		t5 = new ModelTask("Task 5", "Descriptive af", "15/05/2022");
		t6 = new ModelTask("Task 6", "Desky", "14/05/2022");
		t7 = new ModelTask("Task 7", "Desky-desk", "15/06/2022");
		t1.setID(1);
		t2.setID(2);
		t3.setID(3);
		t4.setID(4);
		t5.setID(5);
		t6.setID(6);
		t7.setID(7);
		tasks = new ArrayList<>();
		tasks.add(t1);
		tasks.add(t2);
		tasks.add(t3);
		tasks.add(t4);
		tasks.add(t5);
		tasks.add(t6);
		tasks.add(t7);
	}
	
	@After public void tearDown()
	{
	
	}
	
	@Test public void taskListIsNotEmptyWhenNotEmpty()
	{
		assertFalse(tasks.isEmpty());
	}
	
	@Test public void gettingSetTaskID()
	{
		assertEquals(1, t1.getID());
	}
	
	@Test public void changingTaskNameToTaskNr1()
	{
		t1.setName("Task nr. 1");
		assertEquals("Task nr. 1", t1.getName());
	}
	
	@Test public void gettingEmptyDescription()
	{
		assertEquals("", t2.getDescription());
	}
	
	@Test public void gettingNotEmptyDescription()
	{
		assertEquals("Desky-desk", t7.getDescription());
	}
	
	@Test public void gettingDeadlineLongFromString()
	{
		assertEquals(1672613999L, t1.getDeadline());
	}
	
	@Test public void gettingDeadlineFormattedFromStringWithNoDeadline()
	{
		assertEquals("N/A", t3.getDeadlineFormatted());
	}
	
	@Test public void settingDeadlineLong1672534800()
	{
		t3.setDeadline(1672534800L);
		assertEquals(1672534800, t3.getDeadline());
	}
	
	@Test public void gettingFormattedDeadlineFromExistingDeadline()
	{
		assertEquals("01/01/2023", t1.getDeadlineFormatted());
	}
	
	@Test public void gettingDeadlineLong()
	{
		assertEquals(1672613999, t1.getDeadline());
	}
	
	@Test public void cannotSetDeadlineBeforeNow()
	{
		t3.setDeadline(t3.formatDeadline("13/05/2022"));
		assertEquals(0, t3.getDeadline());
	}
	
	@Test public void canSetDeadlineAfter()
	{
		t3.setDeadline(t3.formatDeadline("01/01/3000"));
		assertEquals(32503762799L, t3.getDeadline());
	}
	
	@Test public void date1505IsBetween1405And1506()
	{
		assertTrue(ModelTask.isBetween(t5.getDeadline(), t6.getDeadline(), t4.getDeadline()));
	}
	
	@Test public void getTasksInUpcomingWeekFrom1405()
	{
		assertEquals(2, ModelTask.getUpcomingTasksWeek(tasks).size());
	}
}