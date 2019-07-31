import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelWrite {
    public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public String ColName="CarModel";
    public int col_num;
    @Test
    public void WriteResultInExcel()
    {

        String str="hello";

       // String[] CarModel = new String[]{"abc","rty","tyu","tyy","yut","tyu"};
        try
        {
        for(int i=1;i<=5;i++)
        {
            WriteResult(str,i);

        }}
        catch (Exception e)
        {
            System.out.println("At this point...");
        }
    }



    public void WriteResult(String Ress, int DR) throws Exception
    {
        FileInputStream file_input_stream= new FileInputStream("/home/shijon/Documents/CarModel.xlsx");
        workbook=new XSSFWorkbook(file_input_stream);
        worksheet=workbook.getSheet("excelwrite");
        XSSFRow Row=worksheet.getRow(0);

        int sheetIndex=workbook.getSheetIndex("excelwrite");
        DataFormatter formatter = new DataFormatter();
        if(sheetIndex==1)
        {
            System.out.println("No such sheet in file exists");
        } else      {
            col_num=1;
            for(int i=0;i<Row.getLastCellNum();i++)
            {
                XSSFCell cols=Row.getCell(i);
                String colsval=formatter.formatCellValue(cols);
                if(colsval.trim().equalsIgnoreCase(ColName.trim()))
                {
                    col_num=i;
                    break;
                }
            }
//
            Row= worksheet.getRow(DR);
            try
            {
                //get my Row which is equal to Data  Result and that colNum
               // System.out.println("********** "+col_num+1);
                XSSFCell cell=worksheet.getRow(DR).getCell(col_num);
                System.out.println("********** "+cell.toString());
                // if no cell found then it create cell
                if(cell==null) {
                    cell=Row.createCell(col_num);
                }
                //Set Result is pass in that cell number
                cell.setCellValue(Ress);
                //System.out.println("********** "+Ress);


            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("********** "+Ress);
            }

        }
        FileOutputStream file_output_stream=new FileOutputStream("/home/shijon/Documents/CarModel.xlsx");
        workbook.write(file_output_stream);
        file_output_stream.close();
        if(col_num==-1) {
            System.out.println("Column you are searching for does not exist");
        }
    }
}



