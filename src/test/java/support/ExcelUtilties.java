package support;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtilties {

    public static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;
    static int sheetCount;
    static XSSFCellStyle cellStyle;
    static File src;

    public static Object[][] getTestDataMap(File filePath, String SheetName) {
        Object[][] obj = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int lastRowNum = ExcelWSheet.getLastRowNum();
            int lastCellNum = ExcelWSheet.getRow(0).getLastCellNum();
            obj = new Object[lastRowNum][1];


            for (int i = 0; i < lastRowNum; i++) {
                Map<Object, Object> datamap = new HashMap<>();
                for (int j = 0; j < lastCellNum; j++) {
                    datamap.put(ExcelWSheet.getRow(0).getCell(j).toString(), ExcelWSheet.getRow(i + 1).getCell(j).toString());
                }
                obj[i][0] = datamap;
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
