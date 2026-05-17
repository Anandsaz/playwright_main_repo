import com.microsoft.playwright.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class test_01 {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = context.newPage();
        System.out.println("Browser Launched Successfully");
        page.navigate("https://testautomationpractice.blogspot.com/");
        page.locator("#name").fill("Selenium");
        page.locator("#email").fill("abcd@gmail.com");

        //drop down
        page.locator("#country").click();
        //page.locator("#country").selectOption("India");
        List<String> options = page.locator("#country option").allTextContents();
        for (String e : options) {
            if (e.trim().contains("India")) {
                page.locator("#country").selectOption("India");
                System.out.println("Selected option: " + e.trim());
                break;
            }
        }

        page.locator("#country").click();
        List<String> drp = page.locator("#country option").allTextContents();
        for (int i = 0; i < drp.size(); i++) {
            String text = drp.get(i).trim();
            if (text.equalsIgnoreCase("China")) {
                page.locator("#country").selectOption("China");
                System.out.println("Selected option: " + text);
                break;

            }

        }

        page.locator("#sunday").click();
        System.out.println("Sunday is checked");
        page.isChecked("#sunday");
        Boolean fal = page.isChecked("#monday");
        if (!fal) {
            System.out.println("Monday is not checked");
        }

        page.getByText("Blue").first().click();
        boolean status = page.isChecked("option[value='blue']");
        System.out.println("Is Blue selected");

        //page.close();


    }
}
