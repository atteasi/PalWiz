import org.junit.Test;

import com.example.application.views.login.LoginView;
import com.vaadin.testbench.unit.UIUnitTest;

public class LoginTest extends UIUnitTest {
  @Test
  public void setText_clickButton_notificationIsShown() {
    final LoginView loginView = navigate(LoginView.class);

    // TextField and Button are available as package protected in the view
    // So we can use those from there
    /*
     * test(loginView.).setValue("Test");
     * test(helloView.sayHello).click();
     * 
     * // Notification isn't referenced in the view so we need to use the component
     * // query API to find the notification that opened
     * Notification notification = $(Notification.class).first();
     * Assertions.assertEquals("Hello Test", test(notification).getText());
     */
  }
}
