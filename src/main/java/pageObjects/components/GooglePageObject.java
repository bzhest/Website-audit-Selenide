package pageObjects.components;

import lombok.Getter;

/**
 * Created by DWork on 19.04.2018.
 */
@Getter
public class GooglePageObject {
    private String mobileValue;
    private String desktopValue;
    private String url;

    public GooglePageObject(String mobileValue, String desktopValue, String url) {
        this.mobileValue = mobileValue;
        this.desktopValue = desktopValue;
        this.url = url;
    }
}
