package org.clubrockisen.entrycounter.common;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.clubrockisen.entrycounter.common.Party.PartyColumn;
import org.junit.Before;
import org.junit.Test;

import com.alexrnl.commons.database.structure.Column;
import com.alexrnl.commons.database.structure.EntityColumn;

/**
 * Test suite for the {@link Party} class.
 * @author Alex
 */
public class PartyTest {
	/** The empty party to test */
	private Party	emptyParty;
	/** The full party to test */
	private Party	fullParty;
	/** The parties to test */
	private List<Party> parties;
	
	/**
	 * Set up test attributes.
	 */
	@Before
	public void setUp () {
		emptyParty = new Party();
		fullParty = new Party(8000l);
		
		parties = new LinkedList<>();
		parties.add(emptyParty);
		parties.add(fullParty);
	}
	
	/**
	 * Test method for {@link Party#getEntityName()}.
	 */
	@Test
	public void testGetEntityName () {
		assertEquals("party", emptyParty.getEntityName());
		assertEquals("party", fullParty.getEntityName());
	}
	
	/**
	 * Test method for {@link Party#getEntityColumns()}.
	 */
	@Test
	public void testGetEntityColumns () {
		for (final Party party : parties) {
			final Map<? extends Enum<? extends EntityColumn>, Column> columns = party.getEntityColumns();
			
			assertEquals("idParty", columns.get(PartyColumn.ID).getName());
			assertEquals(Integer.class, columns.get(PartyColumn.ID).getType());
			assertTrue(columns.get(PartyColumn.ID).isID());
			
			assertEquals("date", columns.get(PartyColumn.DATE).getName());
			assertEquals(Date.class, columns.get(PartyColumn.DATE).getType());
			assertFalse(columns.get(PartyColumn.DATE).isID());
			
			assertEquals("entriesTotal", columns.get(PartyColumn.ENTRIES_TOTAL).getName());
			assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_TOTAL).getType());
			assertFalse(columns.get(PartyColumn.ENTRIES_TOTAL).isID());
			
			assertEquals("entriesMale", columns.get(PartyColumn.ENTRIES_MALE).getName());
			assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_MALE).getType());
			assertFalse(columns.get(PartyColumn.ENTRIES_MALE).isID());
			
			assertEquals("entriesFemale", columns.get(PartyColumn.ENTRIES_FEMALE).getName());
			assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_FEMALE).getType());
			assertFalse(columns.get(PartyColumn.ENTRIES_FEMALE).isID());
		}
	}
	
	/**
	 * Test the field names of the columns.
	 */
	@Test
	public void testColumnsField () {
		assertEquals("IdParty", PartyColumn.ID.getFieldName());
		assertEquals("Date", PartyColumn.DATE.getFieldName());
		assertEquals("EntriesTotal", PartyColumn.ENTRIES_TOTAL.getFieldName());
		assertEquals("EntriesMale", PartyColumn.ENTRIES_MALE.getFieldName());
		assertEquals("EntriesFemale", PartyColumn.ENTRIES_FEMALE.getFieldName());
	}
	
	/**
	 * Test method for {@link Party#getID()}.
	 */
	@Test
	public void testGetID () {
		assertEquals("-1", emptyParty.getID());
		assertEquals("-1", fullParty.getID());
	}
	
	/**
	 * Test method for {@link Party#getColumns()}.
	 */
	@Test
	public void testGetColumns () {
		final Map<? extends Enum<? extends EntityColumn>, Column> columns = Party.getColumns();
		
		assertEquals("idParty", columns.get(PartyColumn.ID).getName());
		assertEquals(Integer.class, columns.get(PartyColumn.ID).getType());
		assertTrue(columns.get(PartyColumn.ID).isID());
		
		assertEquals("date", columns.get(PartyColumn.DATE).getName());
		assertEquals(Date.class, columns.get(PartyColumn.DATE).getType());
		assertFalse(columns.get(PartyColumn.DATE).isID());
		
		assertEquals("entriesTotal", columns.get(PartyColumn.ENTRIES_TOTAL).getName());
		assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_TOTAL).getType());
		assertFalse(columns.get(PartyColumn.ENTRIES_TOTAL).isID());
		
		assertEquals("entriesMale", columns.get(PartyColumn.ENTRIES_MALE).getName());
		assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_MALE).getType());
		assertFalse(columns.get(PartyColumn.ENTRIES_MALE).isID());
		
		assertEquals("entriesFemale", columns.get(PartyColumn.ENTRIES_FEMALE).getName());
		assertEquals(Integer.class, columns.get(PartyColumn.ENTRIES_FEMALE).getType());
		assertFalse(columns.get(PartyColumn.ENTRIES_FEMALE).isID());
	}
	
	/**
	 * Test method for {@link Party#getIdParty()}.
	 */
	@Test
	public void testGetIdParty () {
		assertEquals((long) -1, (long) emptyParty.getIdParty());
		assertEquals((long) -1, (long) fullParty.getIdParty());
	}
	
	/**
	 * Test method for {@link Party#setIdParty(Integer)}.
	 */
	@Test
	public void testSetIdParty () {
		assertEquals((long) -1, (long) emptyParty.getIdParty());
		assertEquals((long) -1, (long) fullParty.getIdParty());
		
		emptyParty.setIdParty(28);
		fullParty.setIdParty(88);
		
		assertEquals((long) 28, (long) emptyParty.getIdParty());
		assertEquals((long) 88, (long) fullParty.getIdParty());
	}
	
	/**
	 * Test method for {@link Party#getDate()}.
	 */
	@Test
	public void testGetDate () {
		assertEquals((long) 0, (long) emptyParty.getDate());
		assertEquals((long) 8000, (long) fullParty.getDate());
	}
	
	/**
	 * Test method for {@link Party#setDate(Long)}.
	 */
	@Test
	public void testSetDate () {
		assertEquals((long) 0, (long) emptyParty.getDate());
		assertEquals((long) 8000, (long) fullParty.getDate());
		
		final long now = System.currentTimeMillis();
		emptyParty.setDate(now);
		fullParty.setDate(888888l);
		
		assertEquals(now, (long) emptyParty.getDate());
		assertEquals((long) 888888, (long) fullParty.getDate());
	}
	
	/**
	 * Test method for {@link Party#getEntriesTotal()}.
	 */
	@Test
	public void testGetEntriesTotal () {
		assertEquals((long) 0, (long) emptyParty.getEntriesTotal());
		assertEquals((long) 0, (long) fullParty.getEntriesTotal());
	}
	
	/**
	 * Test method for {@link Party#setEntriesTotal(Integer)}.
	 */
	@Test
	public void testSetEntriesTotal () {
		assertEquals((long) 0, (long) emptyParty.getEntriesTotal());
		assertEquals((long) 0, (long) fullParty.getEntriesTotal());
		
		emptyParty.setEntriesTotal(128);
		fullParty.setEntriesTotal(48);
		
		assertEquals((long) 128, (long) emptyParty.getEntriesTotal());
		assertEquals((long) 48, (long) fullParty.getEntriesTotal());
	}
	
	/**
	 * Test method for {@link Party#getEntriesMale()}.
	 */
	@Test
	public void testGetEntriesMale () {
		assertEquals((long) 0, (long) emptyParty.getEntriesMale());
		assertEquals((long) 0, (long) fullParty.getEntriesMale());
	}
	
	/**
	 * Test method for {@link Party#setEntriesMale(Integer)}.
	 */
	@Test
	public void testSetEntriesMale () {
		assertEquals((long) 0, (long) emptyParty.getEntriesMale());
		assertEquals((long) 0, (long) fullParty.getEntriesMale());
		
		emptyParty.setEntriesMale(8);
		fullParty.setEntriesMale(6);
		
		assertEquals((long) 8, (long) emptyParty.getEntriesMale());
		assertEquals((long) 6, (long) fullParty.getEntriesMale());
	}
	
	/**
	 * Test method for {@link Party#getEntriesFemale()}.
	 */
	@Test
	public void testGetEntriesFemale () {
		assertEquals((long) 0, (long) emptyParty.getEntriesFemale());
		assertEquals((long) 0, (long) fullParty.getEntriesFemale());
	}
	
	/**
	 * Test method for {@link Party#setEntriesFemale(Integer)}.
	 */
	@Test
	public void testSetEntriesFemale () {
		assertEquals((long) 0, (long) emptyParty.getEntriesFemale());
		assertEquals((long) 0, (long) fullParty.getEntriesFemale());
		
		emptyParty.setEntriesFemale(68);
		fullParty.setEntriesFemale(12);
		
		assertEquals((long) 68, (long) emptyParty.getEntriesFemale());
		assertEquals((long) 12, (long) fullParty.getEntriesFemale());
	}
	
	/**
	 * Test method for {@link Party#hashCode()}.
	 * @throws CloneNotSupportedException
	 *         if the clone operation fails.
	 */
	@Test
	public void testHashCode () throws CloneNotSupportedException {
		for (final Party party : parties) {
			for (final Party otherParty : parties) {
				if (party != otherParty) {
					assertNotEquals(party.hashCode(), otherParty.hashCode());
				} else {
					assertEquals(party.hashCode(), otherParty.hashCode());
				}
			}
			
			final Party clone = party.clone();
			assertNotSame(party, clone);
			assertEquals(party.hashCode(), clone.hashCode());
			clone.setDate(party.getDate() + 1);
			assertNotEquals(party.hashCode(), clone.hashCode());
			clone.setDate(party.getDate());
			clone.setIdParty(party.getIdParty() - 1);
			assertNotEquals(party.hashCode(), clone.hashCode());
			clone.setIdParty(party.getIdParty());
			clone.setEntriesTotal(party.getEntriesTotal() + 2);
			assertNotEquals(party.hashCode(), clone.hashCode());
			clone.setEntriesTotal(party.getEntriesTotal());
			clone.setEntriesMale(party.getEntriesMale() - 2);
			assertNotEquals(party.hashCode(), clone.hashCode());
			clone.setEntriesMale(party.getEntriesMale());
			clone.setEntriesFemale(party.getEntriesFemale() + 8);
			assertNotEquals(party.hashCode(), clone.hashCode());
		}
	}

	/**
	 * Test method for {@link Party#equals(Object)}.
	 * @throws CloneNotSupportedException
	 *         if the clone operation fails.
	 */
	@Test
	public void testEqualsObject () throws CloneNotSupportedException {
		for (final Party party : parties) {
			for (final Party otherParty : parties) {
				if (party != otherParty) {
					assertNotEquals(party, otherParty);
				} else {
					assertEquals(party, otherParty);
				}
			}
			
			final Party clone = party.clone();
			assertNotSame(party, clone);
			assertEquals(party, clone);
			clone.setDate(party.getDate() + 1);
			assertNotEquals(party, clone);
			clone.setDate(party.getDate());
			clone.setIdParty(party.getIdParty() - 1);
			assertNotEquals(party, clone);
			clone.setIdParty(party.getIdParty());
			clone.setEntriesTotal(party.getEntriesTotal() + 2);
			assertNotEquals(party, clone);
			clone.setEntriesTotal(party.getEntriesTotal());
			clone.setEntriesMale(party.getEntriesMale() - 2);
			assertNotEquals(party, clone);
			clone.setEntriesMale(party.getEntriesMale());
			clone.setEntriesFemale(party.getEntriesFemale() + 8);
			assertNotEquals(party, clone);
			
			assertNotEquals(party, null);
			assertNotEquals(party, new Object());
		}
	}
	
	/**
	 * Test method for {@link Party#toString()}.
	 */
	@Test
	public void testToString () {
		assertEquals("Party [date=null, entriesTotal=null]", emptyParty.toString());
		fullParty.setEntriesTotal(280);
		assertEquals("Party [date=8000, entriesTotal=280]", fullParty.toString());
	}
	
	/**
	 * Test method for {@link Party#clone()}.
	 * @throws CloneNotSupportedException
	 *         if the clone operation fails.
	 */
	@Test
	public void testClone () throws CloneNotSupportedException {
		for (final Party party : parties) {
			final Party clone = party.clone();
			assertNotSame(party, clone);
			assertEquals(party.getIdParty(), clone.getIdParty());
			assertEquals(party.getDate(), clone.getDate());
			assertEquals(party.getEntriesTotal(), clone.getEntriesTotal());
			assertEquals(party.getEntriesMale(), clone.getEntriesMale());
			assertEquals(party.getEntriesFemale(), clone.getEntriesFemale());
		}
	}
	
	/**
	 * Test method for {@link Party#compareTo(Party)}.
	 * @throws CloneNotSupportedException
	 *         if the clone operation fails.
	 */
	@Test
	public void testCompareTo () throws CloneNotSupportedException {
		final Party partyBefore = new Party(fullParty.getDate() - 1);
		final Party partyAfter = new Party(fullParty.getDate() + 1);

		assertThat(partyBefore.compareTo(fullParty), lessThan(0));
		assertThat(partyAfter.compareTo(fullParty), greaterThan(0));
		assertEquals(0, fullParty.compareTo(fullParty.clone()));
		assertThat(fullParty.compareTo(null), lessThan(0));
		assertThat(fullParty.compareTo(new Party()), lessThan(0));
	}
}
