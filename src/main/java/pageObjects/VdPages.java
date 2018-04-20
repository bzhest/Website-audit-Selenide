package pageObjects;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.SoftAsserts;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Listeners;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by DWork on 19.04.2018.
 */
@Listeners(SoftAsserts.class)
@Getter
public class VdPages {

    // David's sites

    By byInspectacargezina = By.cssSelector(".minv-img.vehicle_tooltip_container a");
    By byWallworktrucks = By.cssSelector(".vehicle_title.inline.btn-link.h3");
    By byEastcountypreowned = By.cssSelector(".h4.text-primary.vehicle-title");
    By byZidocars = By.cssSelector(".vehicle-img-wrapper");
    By byGenuinemotorcars = By.cssSelector(".vehicle_title.inline.btn-link.h4");
    By byTmx = By.cssSelector(".h4.no-margin-top a");
    By byKenworthnorthwest = By.cssSelector(".h4.text-primary.vehicle-title");
    By byAuctiondemo = By.cssSelector(".h4.text-primary.vehicle-title");
    By byGlobalcarexchange = By.cssSelector(".btn-link.h4.no-margin-top.minvR-name.margin-left-lg-none.margin-left-md-none");
    By byCarkingdirect = By.cssSelector(".minv-img.vehicle_tooltip_container a");
    By byMotortrucks = By.cssSelector(".h4.text-primary.vehicle-title");

    // Artem sites

    By byMotortrader = By.cssSelector(".h4.text-primary.vehicle-title");
    By bySupertiresonline = By.cssSelector(".product-title-link.product_s_desc>a");

    public String getVDurl(String inventoryURL, List<SelenideElement> carLink) {
        open(inventoryURL);
        return carLink.get(0).getAttribute("href");
    }


}
