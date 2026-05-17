# Window Handling Guide for Playwright QA

## Overview
This guide explains how to handle multiple windows, popups, and tabs in your Playwright QA automation tests.

## Files Created

### 1. WindowHandler.java (Utility Class)
A helper class that provides methods to manage multiple windows/popups/tabs in Playwright tests.

#### Key Methods:

**`waitForPopup(Runnable action)`**
- Opens a new window/popup and returns the Page object
- Usage: `Page popup = windowHandler.waitForPopup(() -> page.click("button"));`

**`getAllPages()`**
- Returns a list of all open pages in the current browser context
- Usage: `List<Page> allPages = windowHandler.getAllPages();`

**`switchToPage(int index)`**
- Switches to a specific page by its index (0 = first page)
- Usage: `Page targetPage = windowHandler.switchToPage(1);`

**`switchToPageByTitle(String title)`**
- Finds and switches to a page with a specific title
- Usage: `Page targetPage = windowHandler.switchToPageByTitle("Google");`

**`switchToPageByUrl(String url)`**
- Finds and switches to a page with a URL containing the specified text
- Usage: `Page targetPage = windowHandler.switchToPageByUrl("example.com");`

**`closePage(Page page)`**
- Closes a specific page/window
- Usage: `windowHandler.closePage(popup);`

**`closeAllPopups()`**
- Closes all popup windows except the main page
- Usage: `windowHandler.closeAllPopups();`

**`printAllPageDetails()`**
- Prints details (title, URL) of all open pages
- Usage: `windowHandler.printAllPageDetails();`

**`getPageCount()`**
- Returns the total number of open pages
- Usage: `int count = windowHandler.getPageCount();`

**`getMainPage()`**
- Returns the main/parent page object
- Usage: `Page main = windowHandler.getMainPage();`

---

## Example Usage Scenarios

### Scenario 1: Simple Popup Handling
```java
// Initialize
WindowHandler windowHandler = new WindowHandler(context, mainPage);

// Open popup
Page popup = windowHandler.waitForPopup(() -> {
    mainPage.click("text=Open Link");
});

// Do something on popup
System.out.println(popup.title());

// Close popup
windowHandler.closePage(popup);
```

### Scenario 2: Multiple Windows
```java
// Open multiple popups
Page popup1 = windowHandler.waitForPopup(() -> mainPage.click("button1"));
Page popup2 = windowHandler.waitForPopup(() -> mainPage.click("button2"));

// Get total window count
System.out.println("Total windows: " + windowHandler.getPageCount()); // Output: 3

// Switch between windows
Page targetPage = windowHandler.switchToPage(2);
targetPage.fill("input", "data");

// View all open windows
windowHandler.printAllPageDetails();

// Close all popups
windowHandler.closeAllPopups();
```

### Scenario 3: Switch by URL or Title
```java
// Open a popup
Page popup = windowHandler.waitForPopup(() -> {
    mainPage.click("text=Google");
});

// Switch to the popup by URL
Page googlePage = windowHandler.switchToPageByUrl("google.com");
googlePage.fill("input[name='q']", "Playwright");

// Or switch by title
Page searchPage = windowHandler.switchToPageByTitle("Google Search");
searchPage.press("Enter");
```

---

## Best Practices

1. **Always Initialize WindowHandler**: Create the handler immediately after creating your main page
   ```java
   WindowHandler windowHandler = new WindowHandler(context, mainPage);
   ```

2. **Check for Null**: Always verify that a page exists before using it
   ```java
   Page targetPage = windowHandler.switchToPageByTitle("Title");
   if (targetPage != null) {
       // Use the page
   }
   ```

3. **Print Details for Debugging**: Use `printAllPageDetails()` to debug window handling
   ```java
   windowHandler.printAllPageDetails();
   ```

4. **Clean Up Resources**: Always close pages and context properly
   ```java
   windowHandler.closeAllPopups();
   mainPage.close();
   context.close();
   browser.close();
   playwright.close();
   ```

5. **Handle Popups Immediately**: Wait for popups as soon as they occur
   ```java
   Page popup = windowHandler.waitForPopup(() -> {
       mainPage.click("button"); // This triggers the popup
   });
   // Handle popup immediately after
   ```

---

## Common Window Handling Patterns

### Pattern 1: Handle Child Window
```java
Page childWindow = windowHandler.waitForPopup(() -> {
    mainPage.click("text=Open in New Window");
});

// Perform actions on child window
childWindow.fill("#input", "value");
childWindow.click("button");

// Switch back to main window if needed
mainPage.focus();

// Close child window
windowHandler.closePage(childWindow);
```

### Pattern 2: Handle Multiple Serial Operations
```java
// Open first print
Page print1 = windowHandler.waitForPopup(() -> mainPage.click("text=Print"));
windowHandler.closePage(print1);

// Open second popup
Page popup2 = windowHandler.waitForPopup(() -> mainPage.click("text=Download"));
popup2.pdf("file.pdf");
windowHandler.closePage(popup2);
```

### Pattern 3: Parallel Window Operations
```java
// Open both windows
Page popup1 = windowHandler.waitForPopup(() -> mainPage.click("button1"));
Page popup2 = windowHandler.waitForPopup(() -> mainPage.click("button2"));

// Get all pages
List<Page> pages = windowHandler.getAllPages();

// Perform actions on all pages
for (Page page : pages) {
    System.out.println("Page: " + page.title());
}

// Close all non-main pages
windowHandler.closeAllPopups();
```

---

## Files Created

1. **WindowHandler.java** - Utility class with window handling methods
2. **test_multipe_windows.java** - Complete example showing different scenarios
3. **test_02.java** (Updated) - Your original test updated to use WindowHandler

---

## Running the Tests

```bash
# Compile
mvn clean compile

# Run specific test
mvn test -Dtest=test_02
mvn test -Dtest=test_multipe_windows

# Run all tests
mvn test
```

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| `waitForPopup` times out | Ensure the action actually triggers a popup |
| `NullPointerException` when switching | Check if the page exists before using it |
| Page not closed properly | Verify the page is not already closed with `!page.isClosed()` |
| Multiple pages with same title | Use `switchToPageByUrl()` instead for more specificity |

---

## Additional Resources

- [Playwright Java Documentation](https://playwright.dev/java/)
- [Playwright Window Handling Guide](https://playwright.dev/java/docs/pages#how-to-wait-for-new-page)

