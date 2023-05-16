import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterExample {
	
	private static final int NUM_ROWS = 30000; // Total number of rows
    private static final int CHUNK_SIZE = 1; // Number of rows to write in each chunk
    private static final int NUM_CHUNKS = NUM_ROWS / CHUNK_SIZE; // Number of chunks to write
    private static final int NUM_COLUMNS = 40; // Number of columns

    public static void main(String[] args) {
        try {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a new sheet
            Sheet sheet = workbook.createSheet("Sheet1");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < NUM_COLUMNS; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue("Column " + (i + 1));
            }

            // Write data in chunks
            FileOutputStream outputStream = new FileOutputStream("large_file.xlsx");
            BufferedOutputStream buffStream = new BufferedOutputStream(outputStream);
            for (int i = 0; i < NUM_CHUNKS; i++) {
                // Determine the start and end rows for this chunk
                int startRow = i * CHUNK_SIZE + 1;
                int endRow = (i + 1) * CHUNK_SIZE;

                // Create a new sheet for this chunk
                Sheet chunkSheet = workbook.createSheet("Chunk " + (i + 1));

                // Copy the header row to the chunk sheet
                Row chunkHeaderRow = chunkSheet.createRow(0);
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell chunkCell = chunkHeaderRow.createCell(j);
                    chunkCell.setCellValue(headerCell.getStringCellValue());
                }

                // Write data rows to the chunk sheet
                int rowIndex = 1;
                for (int j = startRow; j <= endRow; j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    Row chunkRow = chunkSheet.createRow(rowIndex);
                    for (int k = 0; k < NUM_COLUMNS; k++) {
                        Cell cell = row.getCell(k);
                        if (cell == null) {
                            continue;
                        }
                        Cell chunkCell = chunkRow.createCell(k);
                        chunkCell.setCellValue("hi");
                    }
                    rowIndex++;
                }

                // Write the chunk sheet to the output stream
//                chunkSheet.write(buffStream);
                workbook.write(buffStream);
                workbook.removeSheetAt(workbook.getSheetIndex(chunkSheet));
            }

            // Flush and close the output stream
            buffStream.flush();
            buffStream.close();
            outputStream.close();

            // Close the workbook
            workbook.close();

            System.out.println("Excel file written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
