package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtility {
    public Object[][] excelManager(File file, String sheetName){
        Object[][] obj = null;
        DataFormatter formatter = new DataFormatter();
        try{
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int lastRow = sheet.getLastRowNum();
            int lastCol = sheet.getRow(0).getLastCellNum();
            obj = new Object[lastRow][lastCol];
            for(int i = 1 ; i < lastRow ; i++){
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;
                for(int j = 0 ; j < lastCol ;j++){
                    XSSFCell cell = row.getCell(j);
                    String val = formatter.formatCellValue(cell);
                    obj[i-1][j] = val;
                }
            }
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
