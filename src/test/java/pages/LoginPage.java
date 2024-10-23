package pages;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

	@FindBy(id = "userid")
	private WebElement input_user;

	@FindBy(id = "password")
	private WebElement input_password;

	@FindBy(id = "btnActive")
	private WebElement button_signIn;

	@FindBy(xpath = "//a[text()='You have a new home page!']")
	private WebElement text_homePage;
	
	@FindBy(xpath="//div[@id='pt1:_UISpgl52u']")
	private WebElement homeButton;

	public LoginPage(Map<Object, Object> objectMap) {
		try {
			PageFactory.initElements(driver, this);
			waitFor(input_user).elementWaiter(2);
			waitFor(input_password).elementWaiter(2);
			setLoginID(objectMap);
			setPassword(objectMap);
			clickSignInButton();
			clickHomeButton();

		} catch (Exception e) {

		}

	}

	private void clickHomeButton() {
		waitFor(homeButton).elementWaiter(3);
		waitFor(homeButton).toBeClickable().click();
		
	}

	public void setLoginID(Map<Object, Object> userData) {
		if (userData != null) {
			String loginData = (String) userData.get("username");
			input_user.sendKeys(loginData);
		}

	}

	public void setPassword(Map<Object, Object> userData) {
		if (userData != null) {
			String loginData = (String) userData.get("password");
			input_password.sendKeys(loginData);
		}

	}

	public void clickSignInButton() {
		button_signIn.click();

	}

}
