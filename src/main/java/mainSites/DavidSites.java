package mainSites;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.PingdomPages;
import pageObjects.VdPages;
import pageObjects.components.GooglePageObject;
import pageObjects.components.PingdomPageObject;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Configuration.AssertionMode.SOFT;
import static com.codeborne.selenide.Configuration.AssertionMode.STRICT;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by DWork on 19.04.2018.
 */
@Listeners(SoftAsserts.class)
public class DavidSites {

    public static List<String> sitsToDavid = new ArrayList<>();
    public static List<PingdomPageObject> davidPagesPingdom = new ArrayList<>();
    public static List<GooglePageObject> davidPagesGooglePages = new ArrayList<>();
    //private static String workbookCsv = "csvDavidPingdom";
    private static String workbookCsv = "csvDavidPingdom";
    private static final String PINGDOM = "https://tools.pingdom.com/#!/";
    private static final String TESTMYSITE = "https://testmysite.withgoogle.com/intl/en-gb";
    private static final String GOOGLEINSIGHTS = "https://developers.google.com/speed/pagespeed/insights/";
    /*private static String workbookExcel = "WebSiteAudit.xls";
    private static String sheetDavidPingdom = "DavidPingdom";
    private static String sheetDavidTestMySite = "DavidTestMySite";
    private static String sheetDavidGoogle = "DavidGoogle";*/

    @Test
    public static void testSites() {
        Configuration.browser = "chrome";
        Configuration.timeout = 50000;
        Configuration.pageLoadStrategy = "normal";
        Configuration.browserSize = "1920x1080";
        Configuration.assertionMode = SOFT;
        VdPages vdp = new VdPages();
        PingdomPages pingdomPages = new PingdomPages();

        //String theeuropeanmastersVDurl = vdp.getVDurl("http://www.theeuropeanmasters.com/cars-for-sale.html", vdp.getTheeuropeanmasters());

        String inspectacargezinaVDurl = vdp.getVDurl( "http://www.inspectacargezina.co.za/cars-for-sale.html", $$(vdp.getByInspectacargezina()));
        String wallworktrucksVDurl = vdp.getVDurl("http://www.wallworktrucks.com/trucks-for-sale-inventory.html", $$(vdp.getByWallworktrucks()));
        String eastcountypreownedVDurl = vdp.getVDurl("http://www.eastcountypreowned.com/cars-for-sale.html", $$(vdp.getByEastcountypreowned()));
        String zidocarsVDurl = vdp.getVDurl("http://www.zidocars.co.za/cars-for-sale.html", $$(vdp.getByZidocars()));
        String genuinemotorcarsVDurl = vdp.getVDurl("http://www.genuinemotorcars.com/cars-for-sale.html", $$(vdp.getByGenuinemotorcars()));

        //String tmxwholesaleVDurl = vdp.getVDurl("https://www.tmxwholesale.com/cars-for-sale.html", vdp.getTMX());

        String kenworthnorthwestVDurl = vdp.getVDurl("http://www.kenworthnorthwest.com/trucks-for-sale_condition_2.html", $$(vdp.getByKenworthnorthwest()));
        String auctiondemoVDurl = vdp.getVDurl("http://www.auctiondemo.ixloo.com/presaleinventory", $$(vdp.getByAuctiondemo()));
        String globalcarexchangeVDurl = vdp.getVDurl("https://www.globalcarexchange.com/cars-for-sale.html", $$(vdp.getByGlobalcarexchange()));
        String motortrucksVDurl = vdp.getVDurl("http://www.motortrucks.com/trucks-for-sale_condition_2.html", $$(vdp.getByMotortrucks()));


        /*sitsToDavid.add("http://www.theeuropeanmasters.com/");
        sitsToDavid.add("http://www.theeuropeanmasters.com/cars-for-sale.html");
        sitsToDavid.add(theeuropeanmastersVDurl);*/

        sitsToDavid.add("http://www.inspectacargezina.co.za/");
        sitsToDavid.add("http://www.inspectacargezina.co.za/cars-for-sale.html");
        sitsToDavid.add(inspectacargezinaVDurl);
        sitsToDavid.add("http://www.wallworktrucks.com/");
        sitsToDavid.add("https://www.wallworktrucks.com/trucks-for-sale.html");
        sitsToDavid.add(wallworktrucksVDurl);
        sitsToDavid.add("http://www.eastcountypreowned.com/");
        sitsToDavid.add("http://www.eastcountypreowned.com/cars-for-sale.html");
        sitsToDavid.add(eastcountypreownedVDurl);
        sitsToDavid.add("http://www.zidocars.co.za/");
        sitsToDavid.add("http://www.zidocars.co.za/cars-for-sale.html");
        sitsToDavid.add(zidocarsVDurl);
        sitsToDavid.add("http://www.genuinemotorcars.com/");
        sitsToDavid.add("http://www.genuinemotorcars.com/cars-for-sale.html");
        sitsToDavid.add(genuinemotorcarsVDurl);
        sitsToDavid.add("https://www.tmxwholesale.com/");
        sitsToDavid.add("https://www.tmxwholesale.com/cars-for-sale.html");
        //sitsToDavid.add(tmxwholesaleVDurl);
        sitsToDavid.add("http://www.kenworthnorthwest.com/");
        sitsToDavid.add("http://www.kenworthnorthwest.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(kenworthnorthwestVDurl);
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/");
        sitsToDavid.add("http://www.auctiondemo.ixloo.com/presaleinventory");
        sitsToDavid.add(auctiondemoVDurl);
        sitsToDavid.add("http://www.globalcarexchange.com/");
        sitsToDavid.add("https://www.globalcarexchange.com/cars-for-sale.html");
        sitsToDavid.add(globalcarexchangeVDurl);
        sitsToDavid.add("http://www.motortrucks.com");
        sitsToDavid.add("http://www.motortrucks.com/trucks-for-sale_condition_2.html");
        sitsToDavid.add(motortrucksVDurl);
        sitsToDavid.add("https://advancedregenerativeorthopedics.com/");


        //--------------------PINGDOM--------------
        open(PINGDOM);
        pingdomPages.getPingdomSitesParameters(sitsToDavid, davidPagesPingdom, workbookCsv); // for csv file
        //ppm.getPingdomSitesParametersWriteExcel(sitsToDavid, davidPagesPingdom, pp, driver, workbookExcel, sheetDavidPingdom, wait);


        //------------------GOOGLE Test My Site--------
        //open(TESTMYSITE);
        //testMySitePages.getLoadingTime(sitsToDavid,driver,"csvDavidTestMySite"); // for csv file
        //ppm.makeGtmetrixScreenshots(sitsToDavid);
        //testMySitePages.getLoadingTimeToExcel(sitsToDavid,driver,workbookExcel,sheetDavidTestMySite);

        //-------------------GOOGLE Insights----------
        //open(GOOGLEINSIGHTS);
        //gm.getGoogleSitesParameters(sitsToDavid, davidPagesGooglePages, g, "csvDavidGoogle", pp, wait); // for csv file
        //gm.getGoogleSitesParameterstoExcel(sitsToDavid, davidPagesGooglePages, g, workbookExcel, sheetDavidGoogle, pp, wait);
    }


}
