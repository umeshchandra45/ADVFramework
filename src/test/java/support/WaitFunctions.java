package support;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitFunctions {
	private static final Logger LOG = LoggerFactory.getLogger(WaitFunctions.class);
	private final WebDriverWait wait;
	private WebElement webElement;
	private Duration waitTimeout = Duration.ofSeconds(15);
	private File file;

	public WaitFunctions(WebDriver driver, WebElement webElement) {
		this.wait = new WebDriverWait(driver, waitTimeout);
		setAndCheckWebElement(webElement);
	}

	public WaitFunctions(WebDriver driver) {
		this.wait = new WebDriverWait(driver, waitTimeout);
	}

	public WaitFunctions(WebDriver driver, File file) {
		this.wait = new WebDriverWait(driver, waitTimeout);
		this.file = file;
	}

	private void setAndCheckWebElement(WebElement webElement) {
		if (webElement != null) {
			this.webElement = webElement;
		} else {
			LOG.error("Web element is null");
			throw new IllegalArgumentException("Web element is null.");
		}

	}

	public WaitFunctions withHardWait(int seconds) {
		try {
			Thread.sleep(seconds);

		} catch (InterruptedException interruptedException) {
			LOG.warn("Hard wai was interuppted");
		}
		return this;
	}

	public WaitFunctions withTimeout(int seconds) {
		this.waitTimeout = Duration.ofSeconds(seconds);
		return this;
	}

	public void andThenClick() {
		toBeClickable().click();
	}

	public WebElement toBeDisplayed() {
		LOG.info("Waiting {} second for Element [{}] to be displayed", this.waitTimeout.getSeconds(), this.webElement);
		try {
			untilCondition(driver -> this.webElement.isDisplayed());
		} catch (Exception e) {
			LOG.error("waiting for element [{}] to be clcikable", this.webElement);
		}
		return webElement;
	}

	public WebElement toBeNotDisplayed() {
		LOG.info("Waiting {} second for Element [{}] to be displayed", this.waitTimeout.getSeconds(), this.webElement);
		try {
			untilCondition(driver -> !this.webElement.isDisplayed());
		} catch (Exception e) {
			LOG.error("waiting for element [{}] to be clcikable", this.webElement);
		}
		return webElement;
	}

	public WebElement tobeEnabled() {
		LOG.info("Waiting {} second for Element [{}] to be displayed", this.waitTimeout.getSeconds(), this.webElement);
		try {
			untilCondition(driver -> this.webElement.isEnabled());
		} catch (Exception e) {
			LOG.error("waiting for element [{}] to be clcikable", this.webElement);
		}
		return webElement;
	}

	public WebElement toBeClickable() {
		try {
			untilCondition(driver -> this.webElement.isDisplayed() && this.webElement.isEnabled());
		} catch (Exception e) {
			LOG.error("waiting for element [{}] to be clcikable", this.webElement);
		}
		return webElement;
	}

	public <E> E untilCondition(ExpectedCondition<E> expectedCondition) {
		return wait.until(expectedCondition);
	}

	public boolean elementWaiter(int duration) {
		boolean elementGone = false;
		try {
			int seconds = 0;
			while (seconds < duration) {
				Thread.sleep(1000);
				this.webElement.isDisplayed();
				seconds = seconds + 1000;
			}
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			elementGone = true;
		} catch (InterruptedException e) {
			LOG.warn(e.getMessage());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			LOG.warn(e.getMessage());
		}
		return elementGone;
	}

	
}
