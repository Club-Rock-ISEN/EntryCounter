package org.clubrockisen.entrycounter.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.clubrockisen.entrycounter.common.Party;
import org.clubrockisen.entrycounter.common.Party.PartyColumn;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.alexrnl.commons.database.sql.SQLDAO;
import com.alexrnl.commons.database.structure.Column;
import com.alexrnl.commons.database.structure.Entity;
import com.alexrnl.commons.time.TimeConverter;
import com.alexrnl.commons.time.TimeConverter.Unit;

/**
 * Test suite for the {@link PartyDAO} test.
 * @author Alex
 */
public class PartyDAOTest {
	
	/** One day in milliseconds */
	private final static Long	ONE_DAY	= TimeConverter.convert(1, Unit.DAYS, Unit.MILLISECONDS);
	
	/** The test database connection */
	private static Connection	connection;
	
	/** The party DAO to use */
	private PartyDAO			partyDao;
	
	/**
	 * Create the test database connection, and create the connection to it.
	 * @throws SQLException
	 *         if the database could not be created.
	 */
	@BeforeClass
	public static void setUpBeforeClass () throws SQLException {
		connection = DriverManager.getConnection("jdbc:h2:mem:");
		final PreparedStatement createTable = connection.prepareStatement("CREATE TABLE party ("
				+ "idParty			INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "date				DATE NOT NULL UNIQUE,"
				+ "entriesTotal		INT(4) UNSIGNED NOT NULL,"
				+ "entriesMale		INT(4) UNSIGNED,"
				+ "entriesFemale	INT(4) UNSIGNED);");
		createTable.executeUpdate();
	}
	
	/**
	 * Close the connection with the database (thus deleting it).
	 * @throws SQLException
	 *         if the database could not be closed.
	 */
	@AfterClass
	public static void tearDownAfterClass () throws SQLException {
		connection.close();
	}
	
	/**
	 * Set up test attributes.
	 * @throws SQLException
	 *         if there was an issue while creating the DAO.
	 */
	@Before
	public void setUp () throws SQLException {
		partyDao = new PartyDAO(connection);
	}
	
	/**
	 * Tear down test attributes.
	 * @throws IOException
	 *         if the DAO could not be closed properly.
	 */
	@After
	public void tearDown () throws IOException {
		for (final Party party : partyDao.retrieveAll()) {
			assertTrue(partyDao.delete(party));
		}
		partyDao.close();
	}
	
	/**
	 * Compute the time stamp of the current day.
	 * @return the time stamp of the day.
	 */
	private static Long getTimeStampOfDay () {
		return getTimeStampOfDay(0);
	}
	
	/**
	 * Compute the time stamp of the day <code>x</code> days before the current day.
	 * @param deltaDays
	 *        the number of day before today to return.
	 * @return the timestamp for the requested day.
	 */
	private static Long getTimeStampOfDay (final int deltaDays) {
		Long dayTimeStamp = System.currentTimeMillis();
		dayTimeStamp -= dayTimeStamp % ONE_DAY;
		return dayTimeStamp - deltaDays * ONE_DAY;
	}
	
	/**
	 * Test method for {@link SQLDAO#create(Entity)}.
	 */
	@Test
	public void testCreate () {
		assertNull(partyDao.create(null));
		final Party party = new Party(getTimeStampOfDay());
		party.setEntriesFemale(28);
		party.setEntriesMale(8);
		party.setEntriesTotal(36);
		final Party createdParty = partyDao.create(party);
		assertNotSame(party, createdParty);
		assertEquals(party.getDate(), createdParty.getDate());
		assertEquals(party.getEntriesTotal(), createdParty.getEntriesTotal());
		assertEquals(party.getEntriesMale(), createdParty.getEntriesMale());
		assertEquals(party.getEntriesFemale(), createdParty.getEntriesFemale());
	}
	
	/**
	 * Test method for {@link SQLDAO#find(int)}.
	 */
	@Test
	public void testFind () {
		Party party1 = new Party(getTimeStampOfDay(1));
		Party party2 = new Party(getTimeStampOfDay(2));
		party1.setEntriesMale(18);
		party1.setEntriesFemale(18);
		party2.setEntriesMale(28);
		party2.setEntriesFemale(28);
		party1 = partyDao.create(party1);
		party2 = partyDao.create(party2);

		assertEquals(party1, partyDao.find(party1.getIdParty()));
		assertEquals(party2, partyDao.find(party2.getIdParty()));
	}
	
	/**
	 * Test method for {@link SQLDAO#update(Entity)}.
	 */
	@Test
	public void testUpdate () {
		assertFalse(partyDao.update(null));
		final Party party = partyDao.create(new Party(getTimeStampOfDay()));
		assertNotNull(party);
		party.setEntriesFemale(88);
		party.setEntriesMale(74);
		party.setEntriesTotal(162);
		assertTrue(partyDao.update(party));
		
		assertEquals(party, partyDao.find(party.getIdParty()));
	}
	
	/**
	 * Test method for {@link SQLDAO#delete(Entity)}.
	 */
	@Test
	public void testDelete () {
		final Party party1 = partyDao.create(new Party(getTimeStampOfDay()));
		final Party party2 = partyDao.create(new Party(getTimeStampOfDay(1)));
		final Party party3 = partyDao.create(new Party(getTimeStampOfDay(2)));
		
		assertEquals(3, partyDao.retrieveAll().size());
		assertTrue(partyDao.delete(party1));
		final Set<Party> parties = partyDao.retrieveAll();
		assertEquals(2, parties.size());
		for (final Party party : parties) {
			if (party.getIdParty() == party2.getIdParty()) {
				assertEquals(getTimeStampOfDay(1), party.getDate());
			} else if (party.getIdParty() == party3.getIdParty()) {
				assertEquals(getTimeStampOfDay(2), party.getDate());
			} else {
				fail("Party found whitch matches neither party2 or party3");
			}
		}
		
	}
	
	/**
	 * Test method for {@link SQLDAO#retrieveAll()}.
	 */
	@Test
	public void testRetrieveAll () {
		assertEquals(0, partyDao.retrieveAll().size());
		final Party party1 = partyDao.create(new Party(getTimeStampOfDay()));
		final Party party2 = partyDao.create(new Party(getTimeStampOfDay(1)));
		final Party party3 = partyDao.create(new Party(getTimeStampOfDay(2)));
		
		final Set<Party> parties = partyDao.retrieveAll();
		assertEquals(3, parties.size());
		for (final Party party : parties) {
			if (party.getIdParty() == party1.getIdParty()) {
				assertEquals(getTimeStampOfDay(), party.getDate());
			} else if (party.getIdParty() == party2.getIdParty()) {
				assertEquals(getTimeStampOfDay(1), party.getDate());
			} else if (party.getIdParty() == party3.getIdParty()) {
				assertEquals(getTimeStampOfDay(2), party.getDate());
			} else {
				fail("Party found whitch matches neither party1, party2 or party3");
			}
		}
		
	}
	
	/**
	 * Test method for {@link SQLDAO#search(Column, String)}.
	 */
	@Test
	public void testSearch () {
		partyDao.create(new Party(getTimeStampOfDay()));
		final Party party2 = partyDao.create(new Party(getTimeStampOfDay(1)));
		partyDao.create(new Party(getTimeStampOfDay(2)));
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		final Set<Party> parties = partyDao.search(Party.getColumns().get(PartyColumn.DATE), sdf.format(getTimeStampOfDay(1)));
		assertEquals(1, parties.size());
		assertEquals(party2, parties.iterator().next());
	}
}
