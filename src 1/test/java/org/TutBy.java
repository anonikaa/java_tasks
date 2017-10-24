package org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TutBy {
    private WebDriver driver;


    @Parameters ({"nameBrow1"})

    @BeforeTest
    public void setUp(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
            driver = new FirefoxDriver();
        }

    }

    @Test
    public void someBelrusSite() {
        driver.get("https://www.tut.by/");
        WebElement logo = driver.findElement(By.id("pageLogo"));

        driver.get("https://jobs.tut.by/#ua:top_menu_www.tut.by~4075");
        WebElement searchField = driver.findElement(By.xpath("//form[@action='/search/vacancy']//input[@type='text']"));
        searchField.click();
        searchField.sendKeys("специалист по тестированию");
        searchField.submit();


        List<WebElement> links = driver.findElements(By.xpath("//a[@data-qa='vacancy-serp__vacancy-title']"));
        int count = 0;
        for (int i = 0; i < links.size(); i++) {
            String text = links.get(i).getText();

            Pattern pattern = Pattern.compile("(специалист по тестированию)|(специалист по [а-я].* тестированию)"); //есть варик, что регулярка не работает
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) ;
            {
                count++;

            }
        }

        Date date = new Date();
        DateFormat dtfrm = DateFormat.getDateInstance();
        String dateF = dtfrm.format(date);
        String resCount = String.valueOf(count);
        String res = dateF + " | "+ resCount;


        FileWriter writeFile = null;
        try {
            File reportFile = new File("rep.txt");
            writeFile = new FileWriter(reportFile);
            writeFile.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writeFile!=null){
                try {
                    writeFile.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }



    @AfterTest
    void tearDown() {
        driver.quit();

    }
}



