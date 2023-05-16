import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelWriterExample2 {
	private static final int NUM_ROWS = 30000; // Total number of rows
    private static final int CHUNK_SIZE = 1; // Number of rows to write in each chunk
    private static final int NUM_COLUMNS = 40; // Number of columns

    public static void main(String[] args) {
        try {
            // Create a new workbook
            SXSSFWorkbook workbook = new SXSSFWorkbook(100);

            // Create a new sheet
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < NUM_COLUMNS; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue("Column " + (i + 1));
            }

            // Write data in chunks
            FileOutputStream outputStream = new FileOutputStream("large_file1.xlsx");
            BufferedOutputStream buffStream = new BufferedOutputStream(outputStream);
            for (int i = 0; i < NUM_ROWS; i += CHUNK_SIZE) {
                // Create a new row
                Row row = sheet.createRow(i + 1);

                // Write data columns
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue("Data " + (i + 1) + ", " + (j + 1));
                }

                // Flush rows to disk every CHUNK_SIZE rows
                if (i % CHUNK_SIZE == 0) {
                    ((SXSSFSheet) sheet).flushRows(CHUNK_SIZE);
                }
            }

            // Write the workbook to the output stream
            workbook.write(buffStream);

            // Flush and close the output stream
            buffStream.flush();
            buffStream.close();
            outputStream.close();

            // Close the workbook and free memory
            workbook.dispose();
            System.out.println("Excel file written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
