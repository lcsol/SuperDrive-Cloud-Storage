package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private static final String BASE_URL = "http://localhost:";

	private static final String FIRSTNAME = "John";
	private static final String LASTNAME = "Doe";
	private static final String USERNAME = "test1";
	private static final String PASSWORD = "123";

	private static final String NOTE_TITLE = "note1";
	private static final String NOTE_DESCRIPTION = "new note";

	private static final String NOTE_TITLE_NEW = "new title";
	private static final String NOTE_DESCRIPTION_NEW = "new content";

	private static final String CRED_URL = "credential1.com";
	private static final String CRED_USERNAME = "James";
	private static final String CRED_PASSWORD = "123456";

	private static final String CRED_URL_NEW = "edit.com";
	private static final String CRED_USERNAME_NEW = "David";
	private static final String CRED_PASSWORD_NEW = "56789";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testUnAuthorizedAccess() {
		driver.get("http://localhost:" + port + "/home");
		assertFalse(driver.getTitle() == "Home");

		driver.get("http://localhost:" + port + "/login");
		assertEquals("Login", driver.getTitle());

		driver.get("http://localhost:" + port + "/signup");
		assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void testSignupAndLogin() throws InterruptedException {
		signupAndLogin();

		assertEquals("Home", driver.getTitle());

		HomePage homePage = new HomePage(driver);
		Thread.sleep(1000);
		homePage.logout();

		driver.get("http://localhost:" + port + "/home");
		assertFalse(driver.getTitle() == "Home");
		assertEquals(driver.getTitle(), "Login");
	}

	@Test
	public void testAddNote() throws InterruptedException {
		addNote();
		HomePage homePage = new HomePage(driver);
		String[] note = homePage.getFirstNote(driver);
		assertEquals(note[0], NOTE_TITLE);
		assertEquals(note[1], NOTE_DESCRIPTION);
	}

	@Test
	public void testEditNote() throws InterruptedException {
		addNote();
		HomePage homePage = new HomePage(driver);
		homePage.editNote(NOTE_TITLE_NEW, NOTE_DESCRIPTION_NEW, driver);
		ResultPage resultPage = new ResultPage(driver);
		resultPage.backHome();
		String[] note = homePage.getFirstNote(driver);
		assertEquals(note[0], NOTE_TITLE_NEW);
		assertEquals(note[1], NOTE_DESCRIPTION_NEW);
	}

	@Test
	public void testDeleteNote() throws InterruptedException {
		addNote();
		HomePage homePage = new HomePage(driver);
		homePage.deleteNote(driver);
		ResultPage resultPage = new ResultPage(driver);
		resultPage.backHome();
		assertFalse(homePage.noteExist(driver));
	}

	@Test
	public void testAddCredential() throws InterruptedException {
		addCredential();
		HomePage homePage = new HomePage(driver);
		String[] credential = homePage.getFirstCredential(driver);
		assertEquals(credential[0], CRED_URL);
		assertEquals(credential[1], CRED_USERNAME);
		assertNotEquals(credential[2], CRED_PASSWORD);
	}

	@Test
	public void testEditCredential() throws InterruptedException {
		addCredential();
		HomePage homePage = new HomePage(driver);
		String password = homePage.editCredential(CRED_URL_NEW, CRED_USERNAME_NEW, CRED_PASSWORD_NEW, driver);
		ResultPage resultPage = new ResultPage(driver);
		resultPage.backHome();
		String[] credential = homePage.getFirstCredential(driver);
		assertEquals(password, CRED_PASSWORD);
		assertEquals(credential[0], CRED_URL_NEW);
		assertEquals(credential[1], CRED_USERNAME_NEW);
	}

	@Test
	public void testDeleteCredential() throws InterruptedException {
		addCredential();
		HomePage homePage = new HomePage(driver);
		homePage.deleteCredential(driver);
		assertFalse(homePage.credentialExist(driver));
	}

	private void signupAndLogin() {
		driver.get(BASE_URL + port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(FIRSTNAME, LASTNAME, USERNAME, PASSWORD);

		driver.get(BASE_URL + port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(USERNAME, PASSWORD);
	}

	private void addNote() throws InterruptedException {
		signupAndLogin();

		HomePage homePage = new HomePage(driver);
		homePage.addNewNote(NOTE_TITLE, NOTE_DESCRIPTION, driver);

		ResultPage resultPage = new ResultPage(driver);
		resultPage.backHome();
	}

	private void addCredential() throws InterruptedException {
		signupAndLogin();

		HomePage homePage = new HomePage(driver);
		homePage.addNewCredential(CRED_URL, CRED_USERNAME, CRED_PASSWORD, driver);

		ResultPage resultPage = new ResultPage(driver);
		resultPage.backHome();
	}
}
