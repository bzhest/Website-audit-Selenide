package formatWriters;


import org.apache.commons.lang3.StringUtils;
import pageObjects.GooglePages;
import pageObjects.PingdomPages;
import pageObjects.components.GooglePageObject;
import pageObjects.components.PingdomPageObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 13.05.2017.
 */
public class CSVwriter {
    private final static String CSV_SEPARATOR = ";";
    private int counter = 1;

    //Add if p.getUrl contains "vid_" - write siteName + "Vehicle Details"

    public void writePingdomToCSV(List<PingdomPageObject> pages, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (PingdomPageObject p : pages) {
                StringBuffer oneLine = new StringBuffer();
                if (p.getUrl().contains("vid_")) {//String with "vid_" is Vehicle Details Page
                    int indexOfThirdSlash = StringUtils.ordinalIndexOf(p.getUrl(), "/", 3);
                    String cuttedUrl = p.getUrl().substring(0, indexOfThirdSlash);
                    oneLine.append(cuttedUrl + "/Vehicle_Details");
                } else if (p.getUrl().contains("sitemap")) { // special for globalcarexchange - it may not have cars
                    oneLine.append(p.getUrl() + " - Site have no cars");
                } else {
                    oneLine.append(p.getUrl());
                }
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("  " + p.getPerformGradeLetter());
                //oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getPerformGradeNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getMbValue() + " MB");
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(p.getSecValue() + " Sec");
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void writeGoogleToCSV(List<GooglePageObject> googlePages, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            for (GooglePageObject g : googlePages) {
                StringBuffer oneLine = new StringBuffer();

                if (g.getUrl().contains("vid_")) {//String with "vid_" is Vehicle Details Page
                    int indexOfThirdSlash = StringUtils.ordinalIndexOf(g.getUrl(), "/", 3);
                    String cuttedUrl = g.getUrl().substring(0, indexOfThirdSlash);
                    oneLine.append(counter++ + ") " + cuttedUrl + "/Vehicle_Details");
                } else if (g.getUrl().contains("sitemap")) { // special for globalcarexchange - it may not have cars
                    oneLine.append(counter++ + ") " + g.getUrl() + " - Site have no cars");
                } else {
                    oneLine.append(counter++ + ") " + g.getUrl());
                }
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("" + g.getMobileValue());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append("" + g.getDesktopValue());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void testMySiteToCSV(ArrayList<String> sites, ArrayList<String> times, String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
                for (int i = 0; i < times.size(); i++) {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(sites.get(i));
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(times.get(i));
                    bw.write(oneLine.toString());
                    bw.newLine();
               }
            bw.flush();
            bw.close();

        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
