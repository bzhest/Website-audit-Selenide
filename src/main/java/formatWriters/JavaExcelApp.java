package formatWriters;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import pageObjects.components.GooglePageObject;
import pageObjects.components.PingdomPageObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by DWork on 10.04.2018.
 */
public class JavaExcelApp {

    private final static String CSV_SEPARATOR = ";";
    private Sheet davidPingdomSheet = null;
    private Sheet davidGoogleSheet = null;
    private Sheet davidTestMySiteSheet = null;
    private Workbook wb;



    public void createWorkBook(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            wb = new HSSFWorkbook();
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSheet(Sheet sheet, String sheetName){

        if(wb.getSheet(sheetName) == null){
            sheet = wb.createSheet(sheetName);
        }else{
            sheet = wb.getSheet(sheetName);
        }
        //sheet = wb.createSheet(sheetName);

        Iterator<Row> rowIte =  sheet.iterator();
        while(rowIte.hasNext()){
            rowIte.next();
            rowIte.remove();
        }
    }

    public void createDavidPingdomSheet(String sheetName){
        if(wb.getSheet(sheetName) == null){
            davidPingdomSheet = wb.createSheet(sheetName);
        }else{
            davidPingdomSheet = wb.getSheet(sheetName);
        }
        //sheet = wb.createSheet(sheetName);

        Iterator<Row> rowIte =  davidPingdomSheet.iterator();
        while(rowIte.hasNext()){
            rowIte.next();
            rowIte.remove();
        }
    }

    public void createDavidGoogleSheet(String sheetName){
        if(wb.getSheet(sheetName) == null){
            davidGoogleSheet = wb.createSheet(sheetName);
        }else{
            davidGoogleSheet = wb.getSheet(sheetName);
        }
        //sheet = wb.createSheet(sheetName);

        Iterator<Row> rowIte =  davidGoogleSheet.iterator();
        while(rowIte.hasNext()){
            rowIte.next();
            rowIte.remove();
        }
    }

    public void createDavidTestMySiteSheet(String sheetName){
        if(wb.getSheet(sheetName) == null){
            davidTestMySiteSheet = wb.createSheet(sheetName);
        }else{
            davidTestMySiteSheet = wb.getSheet(sheetName);
        }
        //sheet = wb.createSheet(sheetName);

        Iterator<Row> rowIte =  davidTestMySiteSheet.iterator();
        while(rowIte.hasNext()){
            rowIte.next();
            rowIte.remove();
        }
    }


    public void writePingdomToExcel(List<PingdomPageObject> pages, String workBook, String sheet) {
        int row = 0;
        createWorkBook(workBook);
        createDavidPingdomSheet(sheet);
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

            Row r = davidPingdomSheet.createRow(row);
            Cell c0 = r.createCell(0);
            c0.setCellValue(oneLine.toString());

            Cell c1 = r.createCell(1);
            c1.setCellValue("  " + p.getPerformGradeLetter() + p.getPerformGradeNumber());

            Cell c2 = r.createCell(2);
            c2.setCellValue(p.getMbValue() + " MB");

            Cell c3 = r.createCell(3);
            c3.setCellValue(p.getSecValue() + " Sec");
            row++;

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(workBook);
                wb.write(fos);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}

    public void writeGoogleToExcel(List<GooglePageObject> googlePages, String workBook, String sheet) {
        int row = 0;
        createWorkBook(workBook);
        createDavidGoogleSheet(sheet);

            for (GooglePageObject g : googlePages) {
                StringBuffer oneLine = new StringBuffer();

                if (g.getUrl().contains("vid_")) {//String with "vid_" is Vehicle Details Page
                    int indexOfThirdSlash = StringUtils.ordinalIndexOf(g.getUrl(), "/", 3);
                    String cuttedUrl = g.getUrl().substring(0, indexOfThirdSlash);
                    oneLine.append(cuttedUrl + "/Vehicle_Details");
                } else if (g.getUrl().contains("sitemap")) { // special for globalcarexchange - it may not have cars
                    oneLine.append(g.getUrl() + " - Site have no cars");
                } else {
                    oneLine.append(g.getUrl());
                }

                Row r = davidGoogleSheet.createRow(row);
                Cell c0 = r.createCell(0);
                c0.setCellValue(oneLine.toString());

                Cell c1 = r.createCell(1);
                c1.setCellValue("" + g.getMobileValue());

                Cell c2 = r.createCell(2);
                c2.setCellValue("" + g.getDesktopValue());

                row++;

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(workBook);
                    wb.write(fos);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void testMySiteToExcel(ArrayList<String> sites, ArrayList<String> times, String workBook, String sheetTitle) {

        createWorkBook(workBook);
        createDavidTestMySiteSheet(sheetTitle);
            for (int i = 0; i < times.size(); i++) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(sites.get(i));

                Row r = davidTestMySiteSheet.createRow(i);
                Cell c0 = r.createCell(0);
                c0.setCellValue(oneLine.toString());

                Cell c1 = r.createCell(1);
                c1.setCellValue(times.get(i));
            }

                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(workBook);
                    wb.write(fos);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

}
