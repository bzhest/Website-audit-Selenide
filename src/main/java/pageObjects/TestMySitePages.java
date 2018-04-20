package pageObjects;

import formatWriters.CSVwriter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by DWork on 19.04.2018.
 */
public class TestMySitePages {
    private List<String> times = new ArrayList<>();
    private final String TEST_MY_SITE = "https://testmysite.withgoogle.com/intl/en-gb";

    By byEnterUrlField = By.cssSelector(".url-entry__input");
    By byArrowSubmitButton = By.cssSelector(".url-entry__submit");
    By byLoadingTime = By.cssSelector(".results__speed-number-numeral");
    By byTestAnotherUrlLink = By.cssSelector("a.change-button");


    public void getLoadingTime(ArrayList<String> sites,ArrayList<String> times, String csvFileName) {
        for (String site : sites) {
            restartTestIfFailed(site);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String time = $(byLoadingTime).getText().trim();
            times.add(time);
            System.out.println("Mobile time is " + time + " on " + site);
            writeToCSV(sites, times, csvFileName);
            clickAnotherLink();
        }
    }

    /*public void getLoadingTimeToExcel(ArrayList<String> sites, String workbook, String sheet) {
        for (String site : sites) {
            restartTestIfFailed(site);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String time = $(byLoadingTime).getText().trim();
            times.add(time);
            System.out.println("Mobile time is " + time + " on " + site);
            writeToExcel(sites, times, workbook,sheet);
            clickAnotherLink();
        }
    }*/

    public void restartTestIfFailed(String site){
        try{
            $(byEnterUrlField).waitUntil(visible, 140000);
            $(byEnterUrlField).setValue(site);
            $(byArrowSubmitButton).click();
            $(byLoadingTime).waitUntil(visible, 140000);
            sleep(2);
        }catch(Exception ex){
            open(TEST_MY_SITE);
            restartTestIfFailed(site);
        }
    }

    public void clickAnotherLink(){
        try {
            $(byTestAnotherUrlLink).waitUntil(visible, 140000);

            $(byTestAnotherUrlLink).click();
        }catch (Exception ex){
            try{
                sleep(2);
                $(byTestAnotherUrlLink).waitUntil(visible, 140000);
                //wait.until(ExpectedConditions.elementToBeClickable(testAnotherUrlLink));
                $(byTestAnotherUrlLink).click();
            }catch (Exception ex1){
                open(TEST_MY_SITE);
            }


        }
    }


    public void writeToCSV(ArrayList<String> sites, ArrayList <String> times, String fileName) {
        CSVwriter writer = new CSVwriter();
        writer.testMySiteToCSV(sites, times, fileName);
    }

    /*public void writeToExcel(ArrayList<String> sites, ArrayList <String> times, String workBook, String sheet) {
        JavaExcelApp excel = new JavaExcelApp();
        excel.testMySiteToExcel( sites, times,workBook,sheet);
    }*/


    public void sleep(int sec){
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
