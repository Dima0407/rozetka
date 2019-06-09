package excel;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Dmytro Torlop
 * on 05.06.19
 */
public class ExcelManager {

    Logger logger = Logger.getLogger(ExcelManager.class.getName());

    public HSSFWorkbook createWorkbook(int sheetCount) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (int i = 0; i < sheetCount; i++) {
            workbook.createSheet("Page " + (i + 1));
        }
        return workbook;
    }

    public void addHeadersToCells(HSSFWorkbook workbook, int sheetIndex, ArrayList<String> headers) {
        Sheet s = workbook.getSheetAt(sheetIndex);
        Row r = s.createRow(0);
        int counter = 0;
        for (String header : headers) {
            Cell c = r.createCell(counter, CellType.STRING);
            c.setCellValue(header);
            counter++;
        }
    }

    public void addValuesToSheet(HSSFWorkbook workbook, int sheetIndex, HashMap<String, Long> values) {
        Sheet s = workbook.getSheetAt(sheetIndex);
        int counter = 1;
        for (String key : values.keySet()) {
            Row r = s.createRow(counter);
            Cell c0 = r.createCell(0);
            Cell c1 = r.createCell(1);
            c0.setCellValue(key);
            c1.setCellValue(values.get(key));
            counter++;
        }
    }

    public void exportWorkbook(HSSFWorkbook workbook, String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();

        try {
            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            logger.info("Results are exported [" + file.getAbsolutePath() + "]");
        } catch (IOException e) {
            logger.error("Error exporting results [" + file.getAbsolutePath() + "]");
            e.printStackTrace();
        }
    }
}
