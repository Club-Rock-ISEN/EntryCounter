package org.clubrockisen.entrycounter.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.clubrockisen.entrycounter.common.Party;

import com.alexrnl.commons.database.DataBaseConfigurationError;
import com.alexrnl.commons.database.dao.AbstractDAOFactory;
import com.alexrnl.commons.database.dao.DataSourceConfiguration;
import com.alexrnl.commons.database.h2.H2Utils;
import com.alexrnl.commons.error.ExceptionUtils;

/**
 * DAO Factory for a H2 database.
 * @author Alex
 */
public class H2DAOFactory extends AbstractDAOFactory {
	/** Logger */
	private static Logger		lg	= Logger.getLogger(H2DAOFactory.class.getName());
	
	/** The connection to the database. */
	private final Connection	connection;
	
	/**
	 * Constructor #1.<br />
	 * @param dataSourceConfiguration
	 *        the data source configuration.
	 */
	public H2DAOFactory (final DataSourceConfiguration dataSourceConfiguration) {
		super(dataSourceConfiguration);
		H2Utils.initDatabase(dataSourceConfiguration);
		
		try {
			connection = DriverManager.getConnection(dataSourceConfiguration.getUrl(),
					dataSourceConfiguration.getUsername(),
					dataSourceConfiguration.getPassword());
			if (connection.isValid(0)) {
				connection.setAutoCommit(true);
				if (lg.isLoggable(Level.INFO)) {
					lg.info("Connection to H2 database at " + dataSourceConfiguration.getUrl() + " is successfull");
				}
			} else {
				throw new SQLException("Connection to H2 database is not valid");
			}
			addDAO(Party.class, new PartyDAO(connection));
		} catch (final SQLException e) {
			lg.warning("Could not create H2 database connection: " + ExceptionUtils.display(e));
			throw new DataBaseConfigurationError("Error while creating H2 database connection", e);
		}
	}

	@Override
	public void close () throws IOException {
		super.close();
		try {
			connection.close();
		} catch (final SQLException e) {
			lg.warning("Error while closing H2 database connection: " + ExceptionUtils.display(e));
			throw new IOException("Error while closing H2 database", e);
		}
	}
	
}
