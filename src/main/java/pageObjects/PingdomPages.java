package pageObjects;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.SoftAsserts;
import formatWriters.CSVwriter;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import pageObjects.components.PingdomPageObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by DWork on 19.04.2018.
 */
@Listeners(SoftAsserts.class)
@Getter
public class PingdomPages {
    By byUrlInput = By.cssSelector("#urlinput");
    By byLinkTryAgainList = By.linkText("try again");
    By byPerformanceGrade = By.cssSelector(".rbc-summary-perfgrade .rbc-summary-info-value");
    By byMb = By.cssSelector(".rbc-summary-item.rbc-summary-pagesize .rbc-summary-info-value");
    By byStartTest = By.cssSelector(".button-starttest");
    By bySecList = By.cssSelector(".timelineBars");
    By byLocationDropdownDivList = By.cssSelector("div.select");
    By byLocationDropDownSelect = By.cssSelector(".select.select-custom.select-location.select-hidden");
    By byLocationUsaList = By.cssSelector("li[data-country = 'USA']");

    Integer performanceGrade;
    String performanceLetter;
    Double pageSize;
    Double seconds;


    public PingdomPages clickOnLocationUSA() {
        for (SelenideElement locations : $$(byLocationUsaList)) {
            try {
                if (locations.getText().contains("California")) {
                    locations.click();
                }
            } catch (Exception ex) {
                $$(byLocationUsaList).get(0).click();
            }
        }
        return new PingdomPages();
    }

    private void clickStartTest(String site) {
        try {
            $(byStartTest).click();
            $(byPerformanceGrade).waitUntil(visible, 60_000);
        }catch (Exception ex){
            tryToSelectAllPingdomPagesFields(site);
        }
    }

    protected String currentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    //for each site repeat test 3 time and select best result
    public void getPingdomSitesParameters(List<String> sites, List<PingdomPageObject> pingdomPageObjects, String csvFileName) {

        for (String site : sites) {
            List<Integer> performanceGradeList = new ArrayList<>();
            List<Double> pageSizeList = new ArrayList<>();
            List<Double> loadTimeList = new ArrayList<>();
            for(int i = 0; i <3; i++) {
                Configuration.reportsFolder = "Screenshots/" + currentTime() + "/Pingdom/errors";
                $(byLocationDropdownDivList).waitUntil(visible, 10000);
                inputSiteSelectCountry(site);
                tryToSelectAllPingdomPagesFields(site);

                performanceGradeList.add(calculatePerformanceGrade());
                pageSizeList.add(calculatePageSize());
                loadTimeList.add(calculateLoadTime());
                makePingdomScreenshot1(site);
                //makePingdomScreenshot2(site);
            }
            Integer maxPerformanceGrade = Collections.max(performanceGradeList);
            System.out.print("Best grades result: " + maxPerformanceGrade + " ");
            String maxPerformanceLetter = calculatePerformanceLetter(maxPerformanceGrade);
            //System.out.println("Result " + maxPerformanceLetter);
            Double maxPageSize = Collections.max(pageSizeList);
            System.out.println("Best weight result:  " + maxPageSize + " MB");
            Double minPageSeconds = Collections.min(loadTimeList);
            System.out.println("Best time result: " + minPageSeconds + " sec");
            System.out.println("***********************************************");

            pingdomPageObjects.add(new PingdomPageObject(maxPerformanceLetter, maxPerformanceGrade, String.valueOf(maxPageSize), String.valueOf(minPageSeconds), site));
            writeToCSV(pingdomPageObjects, csvFileName);
        }
    }

    private Integer selectHigherGradeFromList(List<Integer> performanceGradeList){
        return Collections.max(performanceGradeList);
    }

    public void inputSiteSelectCountry(String site) {
        $(byUrlInput).setValue(site);
        System.out.println(site);
        $(byLocationDropdownDivList).click();
        clickOnLocationUSA();
    }

    public void clickStartTest() {
        $(byStartTest).click();
    }

    private void tryToSelectAllPingdomPagesFields(String site) {
        try {
            clickStartTest();
            $(byPerformanceGrade).waitUntil(visible, 60_000);
        } catch (NoSuchElementException ex) {
            if ($$(byLinkTryAgainList).size() !=0) {
                clickLinkTryAgain(site);
            } else if ($$(byStartTest).size()==0) {
                refreshPage_StartTestAgain(site);
            } else {
                clickStartTest(site);
            }
        }
    }

    private void clickLinkTryAgain(String site) {
        try {
            $$(byLinkTryAgainList).get(0).click();
            $(byLocationDropdownDivList).waitUntil(visible, 60_000);
            inputSiteSelectCountryClickStart(site);
            $(byPerformanceGrade).waitUntil(visible, 60_000);
        } catch (Exception ex1) {
            tryToSelectAllPingdomPagesFields(site);
        }
    }

    public void inputSiteSelectCountryClickStart(String site) {
        inputSiteSelectCountry(site);
        clickStartTest(site);
    }

    private void refreshPage_StartTestAgain(String site) {
        try {
            open(WebDriverRunner.getWebDriver().getCurrentUrl());
            $(byLocationDropdownDivList).waitUntil(visible,50_000);
            inputSiteSelectCountryClickStart(site);
            $(byPerformanceGrade).waitUntil(visible,50_000);
        } catch (Exception ex1) {
            tryToSelectAllPingdomPagesFields(site);
        }
    }
    private Integer calculatePerformanceGrade() {
        String grade = $(byPerformanceGrade).getAttribute("textContent").replaceAll("\\D+", "");
        performanceGrade = Integer.parseInt(grade);//("innerHTML");
        System.out.print(performanceGrade + " ");
        return performanceGrade;
    }

    private void makePingdomScreenshot1(String site){
        Configuration.reportsFolder = "Screenshots/" + currentTime() + "/Pingdom/sites";
        $(".rbc-summary-group").scrollTo();
        String siteWithoutSlash = site.replaceAll("[./:]","_");
        String siteName = siteWithoutSlash.substring(siteWithoutSlash.indexOf("www_")+4);
        screenshot(siteName);
    }

    public void makePingdomScreenshot2(String site){
        Configuration.reportsFolder = "Screenshots/" + currentTime() + "/Pingdom/sites";
        $(".table-general.table-perfinsights").scrollTo();
        String siteWithoutSlash = site.replaceAll("[./:]","_");
        String siteName = siteWithoutSlash.substring(siteWithoutSlash.indexOf("www_")+4);

        screenshot(siteName + "2");
    }

    public String calculatePerformanceLetter(Integer performanceGrade) {
        performanceLetter = "";
        if (performanceGrade >= 90) {
            performanceLetter += "A";
        } else if (performanceGrade >= 80 && performanceGrade < 90) {
            performanceLetter += "B";
        } else if (performanceGrade >= 60 && performanceGrade < 80) {
            performanceLetter += "C";
        } else {
            performanceLetter += "D";
        }
        System.out.println(performanceLetter);
        return performanceLetter;
    }

    private Double calculatePageSize() {
        String size = $(byMb).getAttribute("textContent").replaceAll("[^0-9.]", "");
        Double pageSize = Double.parseDouble(size);
        System.out.println(pageSize + " MB");
        return pageSize;
    }

    private Double calculateLoadTime() {
        List<SelenideElement> partLoadings = $$(bySecList).get(0).$$(By.xpath(".//*"));
        int totalLoadingTime = 0;
        for (SelenideElement w : partLoadings) {
            int loadingTime = Integer.parseInt(w.getAttribute("textContent").replaceAll("\\D+", ""));
            totalLoadingTime += loadingTime;
        }
        seconds = Math.round(totalLoadingTime) / 1000.0d;
        seconds = round(seconds, 1);
        System.out.println(seconds + " sec");
        System.out.println("-----------------------------------------------");
        return seconds;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void writeToCSV(List<PingdomPageObject> pingdomPageObject, String fileName) {
        CSVwriter writer = new CSVwriter();
        writer.writePingdomToCSV(pingdomPageObject, fileName);
    }

    /*public void writeToExcel(ArrayList<PingdomPages> pingdomPage, String workBook, String sheet) {
        JavaExcelApp excel = new JavaExcelApp();
        excel.writePingdomToExcel(pingdomPage,workBook,sheet);
    }*/

}
