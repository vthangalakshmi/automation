package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	XSSFCell cell;
	File src;
	public ReadExcel(String excelpath){
		try{
			src=new File(excelpath);
			FileInputStream fis=new FileInputStream(src);
			wb=new XSSFWorkbook(fis);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public String getData(int sheetNumber,int row,int column){
		sheet1=wb.getSheetAt(sheetNumber);
		String data=sheet1.getRow(row).getCell(column).getStringCellValue()+"".toString();
		return data;
	}
	public int getRowCount(int sheetIndex){
		int rows=wb.getSheetAt(sheetIndex).getLastRowNum()+1;
		return rows;
	}
	public int getColumnCount(int sheetIndex){
		int columns=wb.getSheetAt(sheetIndex).getRow(0).getLastCellNum();
		return columns;
	}
	public void setCellData(String value, int sheetNumber,int RowNum, int ColNum) {
        try {
            XSSFRow row = wb.getSheetAt(sheetNumber).getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(src);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	}

}
