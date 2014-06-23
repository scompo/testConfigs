/**
 * Configuration.java
 */
package it.scompo.testconfigs;

/**
 * 
 * @author mscomparin
 */
public class Configuration {

	private String name;
	private String data;
	private Boolean ignore;

	/**
	 * @param name
	 * @param data
	 */
	public Configuration(String name, String data) {
		this(name, data, null);
	}

	/**
	 * @param name
	 * @param data
	 * @param ignore
	 */
	public Configuration(String name, String data, Boolean ignore) {
		this.name = name;
		this.data = data;
		this.ignore = ignore;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the ignore
	 */
	public Boolean getIgnore() {
		return ignore;
	}

	/**
	 * @param ignore
	 *            the ignore to set
	 */
	public void setIgnore(Boolean ignore) {
		this.ignore = ignore;
	}

}
