package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://neerajbhasin-8055.github.io/SeleniumWebpageForPractice/");

        // ------ NORMAL LOCATORS ------
        driver.findElement(By.id("username")).sendKeys("Neeraj Bhasin");
        driver.findElement(By.id("submit-btn")).click();

        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.xpath("//input[@value='Coding']")).click();
        driver.findElement(By.xpath("//input[@value='Reading']")).click();

        WebElement element = driver.findElement(By.id("country-select"));
        Select select = new Select(element);
        select.selectByContainsVisibleText("India");

        // --------- ALERTS ---------
        driver.findElement(By.xpath("//button[text()='Simple Alert']")).click();
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        driver.findElement(By.xpath("//button[text()='Confirm Alert']")).click();
        Thread.sleep(500);
        alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        driver.findElement(By.xpath("//button[text()='Prompt Alert']")).click();
        Thread.sleep(500);
        alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("I am Prompt alert");
        alert.accept();

        // ---- MOUSE HOVER USING ACTION CLASS ----
        WebElement hover = driver.findElement(By.className("tooltip"));
        Actions action = new Actions(driver);
        action.moveToElement(hover).perform();

        // ---- DRAG AND DROP ----
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        action.dragAndDrop(draggable, droppable).perform();

        // ---- CUSTOM DROPDOWN (CHAINED LOCATOR) ----
        WebElement customSelect = driver.findElement(By.id("custom-drop-btn"));
        customSelect.click();

        WebElement customSelectDrop = driver.findElement(By.id("myDropdown"));
        customSelectDrop.findElement(By.xpath(".//li[text()='Mango']")).click();

        // ---- JAVASCRIPT EXECUTOR (NEW TAB) ----
        String parent = driver.getWindowHandle();

        WebElement newTab = driver.findElement(By.id("new-tab-btn"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].setAttribute('target','_blank');", newTab);
        newTab.click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }

        driver.switchTo().window(parent);

        // ---- IFRAME ----
        WebElement iframe = driver.findElement(By.id("practice-iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id("iframe-button")).click();
        driver.switchTo().defaultContent();

        // ----DatePicker---

        // one way
        WebElement datePicker = driver.findElement(By.xpath("//input[@id = 'datepicker-input']"));
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(
//                "arguments[0].removeAttribute('readonly');" +
//                        "arguments[0].value='2026-07-15';" +
//                        "arguments[0].dispatchEvent(new Event('change'));" , datePicker
//
//        );

        // another way
        datePicker.click();
        while(true){
            String month = driver.findElement(By.id("month-year-display")).getText();
            if(month.equals("May 2027") ){
                break;
            }else{
                driver.findElement(By.id("next-month")).click();
            }
        }
        driver.findElement(By.xpath("//div[@id = 'days-grid']//div[text()='8']")).click();


    }
}