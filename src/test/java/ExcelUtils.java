
import java.io.FileInputStream;

import java.io.FileNotFoundException;



import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtils {
    static FileInputStream file=null;
    static Workbook book;
    static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {

        try {
            file = new FileInputStream("/home/shijon/Documents/CarModel.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        // System.out.println(sheet.getLastRowNum() + "--------" +
        // sheet.getRow(0).getLastCellNum());
        String temp;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                temp = sheet.getRow(i + 1).getCell(k).toString();
               temp= temp.replace(".0","");
                data[i][k] = temp;//sheet.getRow(i + 1).getCell(k).toString();

            }
        }
        return data;
    }
    }
