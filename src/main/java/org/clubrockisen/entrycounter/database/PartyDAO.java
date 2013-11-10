package org.clubrockisen.entrycounter.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TimeZone;

import org.clubrockisen.entrycounter.common.Party;
import org.clubrockisen.entrycounter.common.Party.PartyColumn;

import com.alexrnl.commons.database.sql.SQLDAO;
import com.alexrnl.commons.database.structure.Column;

/**
 * DAO for the {@link Party} entity.<br />
 * @author Alex
 */
public class PartyDAO extends SQLDAO<Party> {
	/** Map between the columns enumeration and their name in the database */
	private final Map<? extends Enum<?>, Column>	columns;
	/** Sample to be used by the super class */
	private Party									partySample;
	
	/**
	 * Constructor #1.<br />
	 * @param connection
	 *        the connection to the database.
	 * @throws SQLException
	 *         if there is an error while building the DAO.
	 */
	public PartyDAO (final Connection connection) throws SQLException {
		super(connection);
		
		columns = new Party().getEntityColumns();
	}
	
	@Override
	protected Party getEntitySample () {
		if (partySample == null) {
			partySample = new Party();
		}
		return partySample;
	}

	@Override
	protected Party createEntityFromResult (final ResultSet result) throws SQLException {
		final Party party = new Party();
		
		party.setIdParty(result.getInt(columns.get(PartyColumn.ID).getName()));
		// Get the GMT date as saved in the database
		final long gmtDate = result.getDate(columns.get(PartyColumn.DATE).getName()).getTime();
		// Convert to the current time zone
		party.setDate(gmtDate + TimeZone.getDefault().getOffset(gmtDate));
		party.setEntriesTotal(result.getInt(columns.get(PartyColumn.ENTRIES_TOTAL).getName()));
		party.setEntriesMale(result.getInt(columns.get(PartyColumn.ENTRIES_MALE).getName()));
		party.setEntriesFemale(result.getInt(columns.get(PartyColumn.ENTRIES_FEMALE).getName()));
		
		return party;
	}

	@Override
	protected void fillInsertStatement (final PreparedStatement statement, final Party obj) throws SQLException {
		int index = 1;
		statement.setDate(index++, new Date(obj.getDate()));
		statement.setInt(index++, obj.getEntriesTotal());
		statement.setInt(index++, obj.getEntriesMale());
		statement.setInt(index++, obj.getEntriesFemale());
	}

	@Override
	protected void fillUpdateStatement (final PreparedStatement statement, final Party obj) throws SQLException {
		fillInsertStatement(statement, obj);
		statement.setInt(columns.size(), obj.getIdParty());
	}
}
