package org.clubrockisen.entrycounter;

import org.clubrockisen.entrycounter.common.CommonTests;
import org.clubrockisen.entrycounter.database.DatabaseTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for the EntryCounter software.
 * @author Alex
 */
@RunWith(Suite.class)
@SuiteClasses({ CommonTests.class, DatabaseTests.class })
public class AllTests {
	
}
