import com.microsoft.playwright.*;

import java.util.Arrays;

public class NEW_ADDED {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize((null)));
        Page page = context.newPage();
        System.out.println("new Anand line added");
        System.out.println("new Anand line added");
        page.navigate("https://testautomationpractice.blogspot.com/");

        System.out.println("afafaf line added");
        System.out.println("new fsfsg added");
        System.out.println("sgsg");
        System.out.println("new line added");
        System.out.println("new line added");
        System.out.println("new line added");
    }
}
