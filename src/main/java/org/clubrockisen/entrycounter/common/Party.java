package org.clubrockisen.entrycounter.common;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

import com.alexrnl.commons.database.structure.Column;
import com.alexrnl.commons.database.structure.Entity;
import com.alexrnl.commons.database.structure.EntityColumn;
import com.alexrnl.commons.utils.object.AutoCompare;
import com.alexrnl.commons.utils.object.AutoHashCode;
import com.alexrnl.commons.utils.object.Field;

/**
 * Class representing a party.<br />
 * @author Alex
 */
public class Party extends Entity implements Cloneable, Comparable<Party> {
	/** Logger */
	private static Logger					lg					= Logger.getLogger(Party.class.getName());
	
	/** Serial version UID */
	private static final long				serialVersionUID	= 6015728894702729632L;
	
	/** Map between the enumeration and the actual columns in the database */
	private static Map<PartyColumn, Column>	columns;
	/** Lock for the columns */
	private static Object					lock				= new Object();
	/** Name of the entity */
	private static String					entityName			= "party";
	
	/** The id of the party */
	private Integer							idParty;
	/** The date of the party */
	private Long							date;
	/** The total entries of the party */
	private Integer							entriesTotal;
	/** The number of male during the party */
	private Integer							entriesMale;
	/** The number of female during the party */
	private Integer							entriesFemale;
	
	@Override
	public String getEntityName () {
		return entityName;
	}
	
	/**
	 * The enumeration for the party columns.
	 * @author Alex
	 */
	public enum PartyColumn implements EntityColumn {
		/** The party's id */
		ID ("IdParty"),
		/** The party's date */
		DATE ("Date"),
		/** The party's total entries */
		ENTRIES_TOTAL ("EntriesTotal"),
		/** The party's male entries */
		ENTRIES_MALE ("EntriesMale"),
		/** The party's female entries */
		ENTRIES_FEMALE ("EntriesFemale");
		
		/** The name of the property in the class */
		private final String fieldName;
		
		/**
		 * Constructor #1.<br />
		 * @param fieldName
		 *        the name of the field.
		 */
		private PartyColumn (final String fieldName) {
			this.fieldName = fieldName;
		}

		@Override
		public String getFieldName () {
			return fieldName;
		}
		
	}
	
	@Override
	protected void setEntityColumns () {
		synchronized (lock) {
			if (columns != null) {
				return;
			}
			columns = new EnumMap<>(PartyColumn.class);
			columns.put(PartyColumn.ID, new Column(Integer.class, "idParty", true));
			columns.put(PartyColumn.DATE, new Column(java.sql.Date.class, "date"));
			columns.put(PartyColumn.ENTRIES_TOTAL, new Column(Integer.class, "entriesTotal"));
			columns.put(PartyColumn.ENTRIES_MALE, new Column(Integer.class, "entriesMale"));
			columns.put(PartyColumn.ENTRIES_FEMALE, new Column(Integer.class, "entriesFemale"));
		}
	}
	
	@Override
	public Map<? extends Enum<? extends EntityColumn>, Column> getEntityColumns () {
		return columns;
	}
	
	/**
	 * Statically returns the entity columns.
	 * @return the entity columns.
	 */
	public static Map<? extends Enum<? extends EntityColumn>, Column> getColumns () {
		return columns;
	}
	
	@Override
	public String getID () {
		return getIdParty().toString();
	}
	
	/**
	 * Constructor #1.<br />
	 * @param date
	 *        the date of the party.
	 */
	public Party (final Long date) {
		super();
		this.date = date;
	}
	
	/**
	 * Constructor #2.<br />
	 * Default constructor.
	 */
	public Party () {
		this(null);
	}
	
	/**
	 * Return the attribute idParty.
	 * @return the attribute idParty.
	 */
	@Field
	public Integer getIdParty () {
		return idParty == null ? Integer.valueOf(-1) : idParty;
	}

	/**
	 * Set the attribute idParty.
	 * @param idParty the attribute idParty.
	 */
	public void setIdParty (final Integer idParty) {
		this.idParty = idParty;
	}

	/**
	 * Return the attribute date.
	 * @return the attribute date.
	 */
	@Field
	public Long getDate () {
		return date == null ? Long.valueOf(0) : date;
	}

	/**
	 * Set the attribute date.
	 * @param date the attribute date.
	 */
	public void setDate (final Long date) {
		this.date = date;
	}

	/**
	 * Return the attribute entriesTotal.
	 * @return the attribute entriesTotal.
	 */
	@Field
	public Integer getEntriesTotal () {
		return entriesTotal == null ? Integer.valueOf(0) : entriesTotal;
	}

	/**
	 * Set the attribute entriesTotal.
	 * @param entriesTotal the attribute entriesTotal.
	 */
	public void setEntriesTotal (final Integer entriesTotal) {
		this.entriesTotal = entriesTotal;
	}

	/**
	 * Return the attribute entriesMale.
	 * @return the attribute entriesMale.
	 */
	@Field
	public Integer getEntriesMale () {
		return entriesMale == null ? Integer.valueOf(0) : entriesMale;
	}

	/**
	 * Set the attribute entriesMale.
	 * @param entriesMale the attribute entriesMale.
	 */
	public void setEntriesMale (final Integer entriesMale) {
		this.entriesMale = entriesMale;
	}

	/**
	 * Return the attribute entriesFemale.
	 * @return the attribute entriesFemale.
	 */
	@Field
	public Integer getEntriesFemale () {
		return entriesFemale == null ? Integer.valueOf(0) : entriesFemale;
	}

	/**
	 * Set the attribute entriesFemale.
	 * @param entriesFemale the attribute entriesFemale.
	 */
	public void setEntriesFemale (final Integer entriesFemale) {
		this.entriesFemale = entriesFemale;
	}
	
	@Override
	public int hashCode () {
		return AutoHashCode.getInstance().hashCode(this);
	}
	
	@Override
	public boolean equals (final Object obj) {
		if (!(obj instanceof Party)) {
			return false;
		}
		return AutoCompare.getInstance().compare(this, (Party) obj);
	}
	
	@Override
	public String toString () {
		return "Party [date=" + date + ", entriesTotal=" + entriesTotal + "]";
	}

	@Override
	public Party clone () throws CloneNotSupportedException {
		final Party clone = new Party();
		clone.idParty = idParty;
		clone.date = date;
		clone.entriesTotal = entriesTotal;
		clone.entriesMale = entriesMale;
		clone.entriesFemale = entriesFemale;
		return clone;
	}
	
	@Override
	public int compareTo (final Party o) {
		if (o == null || o.date == null) {
			return -1;
		}
		return (int) (date - o.date);
	}
	
}
