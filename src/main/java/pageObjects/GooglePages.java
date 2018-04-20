package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DWork on 19.04.2018.
 */
public class GooglePages {

    By byNumberMobileAndDesctopList = By.cssSelector(".speed-report-card-score span");
    By byNumberMobileList = By.cssSelector(".speed-report-card-score span");
    By byTabMobile_DesktopList = By.cssSelector(".ranking");
    By byBlocksOfGradesList = By.cssSelector(".speed-report-card");
    By byUrlInput = By.cssSelector(".url");
    By byCaptchaList = By.cssSelector(".captcha-faq-link");
    By byButtonAnalize = By.cssSelector(".button.button-red.analyze");

    public String getMobileOptimizationGrade(){
        return $$(byBlocksOfGradesList).get(1).$(".speed-report-card-score span").getText();
    }

    public String getDesctopOptimizationGrade(){
        return $$(byBlocksOfGradesList).get(3).$(".speed-report-card-score span").getText();
    }

    public SelenideElement getMobileTab(){return $$(byTabMobile_DesktopList).get(0);}
    public SelenideElement getDesktopTab(){return $$(byTabMobile_DesktopList).get(1);}











}
