package pageObjects.components;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

/**
 * Created by DWork on 18.04.2018.
 */
@Getter
public class PingdomPageObject {
    private String performGradeLetter;
    private Integer performGradeNumber;
    private String mbValue;
    private String secValue;
    private String url;

    public PingdomPageObject(String performGradeLetter, Integer performGradeNumber, String mbValue, String secValue, String url) {
        this.performGradeLetter = performGradeLetter;
        this.performGradeNumber = performGradeNumber;
        this.mbValue = mbValue;
        this.secValue = secValue;
        this.url = url;
    }
}
