package org.clubrockisen.entrycounter.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.clubrockisen.entrycounter.common.Party;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite for the {@link PartyDAO} test.
 * @author Alex
 */
public class PartyDAOTest {
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
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#create(com.alexrnl.commons.database.structure.Entity)}.
	 */
	@Test
	public void testCreate () {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#find(int)}.
	 */
	@Test
	public void testFind () {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#update(com.alexrnl.commons.database.structure.Entity)}.
	 */
	@Test
	public void testUpdate () {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#delete(com.alexrnl.commons.database.structure.Entity)}.
	 */
	@Test
	public void testDelete () {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#retrieveAll()}.
	 */
	@Test
	public void testRetrieveAll () {
		fail("Not yet implemented"); // TODO
	}
	
	/**
	 * Test method for {@link com.alexrnl.commons.database.sql.SQLDAO#search(com.alexrnl.commons.database.structure.Column, java.lang.String)}.
	 */
	@Test
	public void testSearch () {
		fail("Not yet implemented"); // TODO
	}
}
