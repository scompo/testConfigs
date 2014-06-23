package it.scompo.testconfigs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfLoaderTest {

	private static final boolean DISABLED = false;
	private static final boolean ENABLED = true;
	
	private static final String NAME_NOT_PRESENT = "notPresent";
	private static final String NAME_NO_IGNORE_DATA = "no_ing_data";
	private static final String NAME_IGNORE_DATA = "ign_data";
	private static final String NAME_IGNORE_NO_DATA = "ign_no_data";
	private static final String NAME_MISSING_IGNORE_DATA = "miss_ign_data";

	private static final Configuration CONF_NO_IGNORE_DATA = new Configuration(
			NAME_NO_IGNORE_DATA, NAME_NO_IGNORE_DATA, false);

	private static final Configuration CONF_MISSING_IGNORE_DATA = new Configuration(
			NAME_MISSING_IGNORE_DATA, NAME_MISSING_IGNORE_DATA, null);

	private static final Configuration CONF_IGNORE_DATA = new Configuration(
			NAME_IGNORE_DATA, NAME_IGNORE_DATA, ENABLED);

	private ConfigLoader configLoader;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		configLoader = new ConfigLoader();

		configLoader.addConf(NAME_IGNORE_DATA, ENABLED, CONF_IGNORE_DATA);

		configLoader.addConf(NAME_IGNORE_NO_DATA, ENABLED, null);

		configLoader.addConf(NAME_NO_IGNORE_DATA, false, CONF_NO_IGNORE_DATA);

		configLoader.addConf(NAME_MISSING_IGNORE_DATA, null,
				CONF_MISSING_IGNORE_DATA);

	}

	@After
	public void tearDown() throws Exception {
		configLoader = null;
	}

	@Test
	public void testEnabledIgnoreData() {

		Boolean res = null;

		try {
			res = configLoader.enabled(NAME_IGNORE_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(DISABLED, res);
	}

	@Test
	public void testEnabledIgnoreNoData() {

		Boolean res = null;

		try {
			res = configLoader.enabled(NAME_IGNORE_NO_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(false, res);
	}

	@Test
	public void testEnabledNoIgnoreData() {

		Boolean res = null;

		try {
			res = configLoader.enabled(NAME_NO_IGNORE_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(ENABLED, res);
	}

	@Test
	public void testEnabledMissingIgnoreData() {

		Boolean res = null;

		try {
			res = configLoader.enabled(NAME_MISSING_IGNORE_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(ENABLED, res);
	}

	@Test
	public void testMissingConfig() {

		try {
			configLoader.enabled(NAME_NOT_PRESENT);
			fail("Exception not thrown!");
		} catch (ConfigNotFoundException e) {
			assertEquals(ConfigNotFoundException.class, e.getClass());
		}
	}

	@Test
	public void testMissingConfigGet() {

		try {
			configLoader.getConf(NAME_NOT_PRESENT);
			fail("Exception not thrown!");
		} catch (ConfigNotFoundException e) {
			assertEquals(ConfigNotFoundException.class, e.getClass());
		}
	}

	@Test
	public void testChangeEnableRealTime() {

		Boolean res = null;

		try {
			configLoader.setEnabled(NAME_IGNORE_DATA, ENABLED);
			res = configLoader.enabled(NAME_IGNORE_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(ENABLED, res);
	}
	
	@Test
	public void testChangeEnableRealTimeMissingIgnore() {

		Boolean res = null;

		try {
			configLoader.setEnabled(NAME_MISSING_IGNORE_DATA, ENABLED);
			res = configLoader.enabled(NAME_MISSING_IGNORE_DATA);
		} catch (ConfigNotFoundException e) {
			fail(e.getMessage());
		}

		assertEquals(ENABLED, res);
	}

	@Test
	public void testChangeEnableRealTimeNotPresent() {

		try {
			configLoader.setEnabled(NAME_NOT_PRESENT, ENABLED);
			fail("exception not thrown");
		} catch (ConfigNotFoundException e) {
			assertEquals(ConfigNotFoundException.class, e.getClass());
		}
	}

	@Test
	public void testChangeEnableRealTimeNotConfigured() {

		try {
			configLoader.setEnabled(NAME_IGNORE_NO_DATA, ENABLED);
			fail("exception not thrown");
		} catch (ConfigNotFoundException e) {
			assertEquals(ConfigNotFoundException.class, e.getClass());
		}
	}

}
