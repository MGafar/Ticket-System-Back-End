package com.barclays.ticketsystem;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class AutomationTest {
	private WebDriver driver;

	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1936, 1056));
		js = (JavascriptExecutor) driver;
		driver.get("http://localhost:3000/");
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void crudTest() {
		clickCreateButton();
		insertFormDataAndGenerateTicket();
		updateGeneratedTicket();
		deleteTicket();
	}

	@Test 
	public void addSolutionTest() {
		clickCreateButton();
		insertFormDataAndGenerateTicket();
		addAndVerifySolution();
	}
	
	@Test
	public void filterTest() {
		WebElement filterDropdown = driver.findElement(By.name("Filter"));
		filterDropdown.findElement(By.xpath("//option[. = 'Departments']")).click();

		List<WebElement> departments = driver.findElements(By.name("Departments"));
		assert (departments.size() > 0);
		assertThat(departments.get(0).getAttribute("value"), is("All"));

		WebElement departmentDropdown = driver.findElement(By.name("Departments"));
		departmentDropdown.findElement(By.xpath("//option[. = 'FX']")).click();
		assertThat(departmentDropdown.getAttribute("value"), is("1"));

		for (int i = 1; i <= driver.findElements(By.cssSelector(".ticket")).size(); i++) {
			assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + i + ") p:nth-child(5)")).getText(),
					is("Department: FX"));
		}

		departmentDropdown.findElement(By.xpath("//option[. = 'Credit']")).click();
		assertThat(departmentDropdown.getAttribute("value"), is("2"));

		for (int i = 1; i <= driver.findElements(By.cssSelector(".ticket")).size(); i++) {
			assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + i + ") p:nth-child(5)")).getText(),
					is("Department: Credit"));
		}

		filterDropdown.findElement(By.xpath("//option[. = 'Topics']")).click();

		WebElement topicDropdown = driver.findElement(By.name("Topics"));
		topicDropdown.findElement(By.xpath("//option[. = 'Remote Connectivity Issues']")).click();
		assertThat(topicDropdown.getAttribute("value"), is("1"));

		for (int i = 1; i <= driver.findElements(By.cssSelector(".ticket")).size(); i++) {
			assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + i + ") p:nth-child(6)")).getText(),
					is("Topic: Remote Connectivity Issues"));
		}

		topicDropdown.findElement(By.xpath("//option[. = 'Messaging Problems']")).click();
		assertThat(topicDropdown.getAttribute("value"), is("2"));

		for (int i = 1; i <= driver.findElements(By.cssSelector(".ticket")).size(); i++) {
			assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + i + ") p:nth-child(6)")).getText(),
					is("Topic: Messaging Problems"));
		}

		filterDropdown.findElement(By.xpath("//option[. = 'Filter By']")).click();

		assert (driver.findElements(By.name("Department")).size() == 0);
		assert (driver.findElements(By.name("Topic")).size() == 0);
	}
	
	private void clickCreateButton() {
		List<WebElement> createButton = driver.findElements(By.id("btn-create-ticket"));
		assert (createButton.size() > 0);
		driver.findElement(By.id("btn-create-ticket")).click();
		assertThat(driver.findElement(By.cssSelector(".btn-success")).getText(), is("Create"));
	}
	
	private void insertFormDataAndGenerateTicket() {

		driver.findElement(By.name("title")).sendKeys("TitleSelenium");
		driver.findElement(By.cssSelector("p:nth-child(2) > .form-control")).sendKeys("DescriptionSelenium");
		driver.findElement(By.name("author")).sendKeys("AuthorSelenium");

		WebElement departmentDropdown = driver.findElement(By.name("Department"));
		Boolean isEditable = departmentDropdown.isEnabled() && departmentDropdown.getAttribute("readonly") == null;
		assertTrue(isEditable);
		assertThat(departmentDropdown.getAttribute("value"), is("1"));

		WebElement topicDropdown = driver.findElement(By.name("Topic"));
		isEditable = topicDropdown.isEnabled() && topicDropdown.getAttribute("readonly") == null;
		assertTrue(isEditable);
		assertThat(topicDropdown.getAttribute("value"), is("1"));

		driver.findElement(By.cssSelector(".btn-success")).click();
		
		verifyTicketInListComponent();
	}
	
	private void verifyTicketInListComponent() {
		
		int finalChildTicket = driver.findElements(By.cssSelector(".ticket")).size();
		
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") h2")).getText(), 
				is("Title: TitleSelenium"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .ticket-left-column > p")).getText(),
				is("Description: DescriptionSelenium"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(5)")).getText(),
				is("Department: FX"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(6)")).getText(),
				is("Topic: Remote Connectivity Issues"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(7)")).getText(),
				is("Author: AuthorSelenium"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-info")).getText(), 
				is("Update"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-danger")).getText(), 
				is("Delete"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-light")).getText(), 
				is("Start Work"));
	}
	
	private void updateGeneratedTicket() {
		int finalChildTicket = driver.findElements(By.cssSelector(".ticket")).size();

		driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-info")).click();
		driver.findElement(By.name("title")).sendKeys("Updated");
		driver.findElement(By.cssSelector("p:nth-child(2) > .form-control")).sendKeys("Updated");
		driver.findElement(By.name("author")).sendKeys("Updated");

		WebElement departmentDropdown = driver.findElement(By.name("Department"));
		departmentDropdown.findElement(By.xpath("//option[. = 'Credit']")).click();

		WebElement topicDropdown = driver.findElement(By.name("Topic"));
		topicDropdown.findElement(By.xpath("//option[. = 'Messaging Problems']")).click();

		assertThat(driver.findElement(By.cssSelector(".btn-success")).getText(), is("Save"));

		driver.findElement(By.cssSelector(".btn-success")).click();
		
		verifyUpdatedTicketInListComponent();
	}
	
	private void verifyUpdatedTicketInListComponent() {
		int finalChildTicket = driver.findElements(By.cssSelector(".ticket")).size();

		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") h2")).getText(),
				is("Title: TitleSeleniumUpdated"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(2)")).getText(), 
				is("Description: DescriptionSeleniumUpdated"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(5)")).getText(), 
				is("Department: Credit"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(6)")).getText(), 
				is("Topic: Messaging Problems"));
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(7)")).getText(), 
				is("Author: AuthorSeleniumUpdated"));

		List<WebElement> updateButton = driver
				.findElements(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-info"));
		assert (updateButton.size() > 0);

		List<WebElement> deleteButton = driver
				.findElements(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-danger"));
		assert (deleteButton.size() > 0);
	}
	
	private void deleteTicket() {
		int finalChildTicket = driver.findElements(By.cssSelector(".ticket")).size();
		driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-danger")).click();
	}
	
	private void addAndVerifySolution() {
		int finalChildTicket = driver.findElements(By.cssSelector(".ticket")).size();

		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-light")).getText(),
				is("Start Work"));
		driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-light")).click();
		
		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-light")).getText(),
				is("Mark As Done"));
		driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") .btn-light")).click();

		driver.findElement(By.name("solution")).sendKeys("SeleniumSolution");

		WebElement titleInput = driver.findElement(By.name("title"));
		Boolean isEditable = titleInput.isEnabled() && titleInput.getAttribute("readonly") == null;
		assertFalse(isEditable);

		WebElement descriptionInput = driver.findElement(By.cssSelector("p:nth-child(2) > .form-control"));
		isEditable = descriptionInput.isEnabled() && descriptionInput.getAttribute("readonly") == null;
		assertFalse(isEditable);

		WebElement authorInput = driver.findElement(By.name("author"));
		isEditable = authorInput.isEnabled() && authorInput.getAttribute("readonly") == null;
		assertFalse(isEditable);

		assertThat(driver.findElement(By.cssSelector(".btn-success")).getText(), is("Mark as Done"));
		driver.findElement(By.cssSelector(".btn-success")).click();

		assertThat(driver.findElement(By.cssSelector(".ticket:nth-child(" + finalChildTicket + ") p:nth-child(3)")).getText(), 
				is("Solution: SeleniumSolution"));
	}
}
