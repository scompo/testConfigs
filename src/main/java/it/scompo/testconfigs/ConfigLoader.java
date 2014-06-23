/**
 * ConfigLoader.java
 */
package it.scompo.testconfigs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mscomparin
 * 
 */
public class ConfigLoader {

	private static final String CONFIG_NOT_PRESENT_MESSAGE = "Config not present: ";

	public enum EnableValues {

		/**
		 * Enabled (true)
		 */
		ENABLED(true),

		/**
		 * Disabled (false)
		 */
		DISABLED(false);

		private Boolean value = null;

		EnableValues(Boolean value) {
			this.value = value;
		}

		/**
		 * Get the value for the enum.
		 * 
		 * @return the value for the enum.
		 */
		public Boolean getValue() {
			return this.value;
		}
	}

	private static Map<String, Configuration> confs = new HashMap<String, Configuration>();

	private static Map<String, EnableValues> vals = new HashMap<String, EnableValues>();

	/**
	 * Add a configuration.
	 * 
	 * @param name
	 *            the name.
	 * 
	 * @param conf
	 *            the {@link Configuration} to add.
	 */
	public void addConf(String nameConf, Boolean ignore, Configuration conf) {

		Configuration confTemp = null;
		String name = null;
		String data = null;
		EnableValues ev = EnableValues.ENABLED;

		if (ignore != null) {
			ev = ignore ? EnableValues.DISABLED : EnableValues.ENABLED;
		} else {
			ev = EnableValues.ENABLED;
		}

		vals.put(nameConf, ev);

		if (conf != null) {

			name = conf.getName();
			data = conf.getData();

			confTemp = new Configuration(name, data);
			confs.put(nameConf, confTemp);
		}

	}

	/**
	 * Get the configuration.
	 * 
	 * @param name
	 *            the name of the {@link Configuration} to get.
	 * 
	 * @return the {@link Configuration}.
	 * @throws ConfigNotFoundException
	 */
	public Configuration getConf(String name) throws ConfigNotFoundException {
		if (!isPresent(name)) {
			throw new ConfigNotFoundException(CONFIG_NOT_PRESENT_MESSAGE + name);
		}
		return confs.get(name);
	}

	/**
	 * Checks if a configuration it's enabled.
	 * 
	 * @param name
	 *            the name of the configuration to get.
	 * 
	 * @return <code>true</code> if the configuration it's enabled, false if
	 *         not.
	 * @throws ConfigNotFoundException
	 */
	public Boolean enabled(String name) throws ConfigNotFoundException {

		Boolean res;

		if (!isPresent(name) && !isEnabled(name)) {
			throw new ConfigNotFoundException(CONFIG_NOT_PRESENT_MESSAGE + name);
		}

		res = vals.get(name).getValue();

		return res;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private Boolean isEnabled(String name) {

		EnableValues enableValue = null;
		Boolean res = null;

		res = false;

		enableValue = vals.get(name);

		if (enableValue != null) {
			res = enableValue.getValue();
		}

		return res;
	}

	/**
	 * @param name
	 * @param enabled
	 * @throws ConfigNotFoundException
	 */
	public void setEnabled(String name, Boolean enabled)
			throws ConfigNotFoundException {
		if (!isPresentAndConfigured(name)) {
			throw new ConfigNotFoundException(CONFIG_NOT_PRESENT_MESSAGE + name);
		}
		vals.put(name, (enabled ? EnableValues.ENABLED : EnableValues.DISABLED));
	}
	
	/**
	 * Return true if the config is present.
	 * 
	 * @param name
	 * @return
	 */
	private Boolean isPresent(String name) {
		return vals.containsKey(name);
	}
	
	/**
	 * Return ture if the config is configured.
	 * 
	 * @param name
	 * @return
	 */
	private boolean isConfigured(String name) {
		return confs.containsKey(name);
	}
	
	/**
	 * Return true if the config is present and configured.
	 * 
	 * @param name
	 * @return
	 */
	private boolean isPresentAndConfigured(String name) {
		return isPresent(name) && isConfigured(name);
	}

}
