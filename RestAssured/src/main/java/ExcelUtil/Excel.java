package ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	static File EXCELPATH=new File("./src/test/resources/testdata.xlsx");
	public static InputStream fs;
	public static XSSFWorkbook workbook;
//	public static Workbook workbook;
	public static XSSFSheet sheet;
//	public static Sheet sheet;
	public static List<String> testCases= new ArrayList<String>();
	public static List<String> runStatus= new ArrayList<String>();
	public static List<String> testDescription= new ArrayList<String>();
	public static List<String> invocationCount= new ArrayList<String>();
	public static List<String> priority= new ArrayList<String>();
	public static HashMap<Integer,String> rowAndTestCaseMap=new HashMap<Integer,String>();

	public static void main(String[] args) throws Exception {
		getRunStatus();
		System.out.println(testCases);
		System.out.println(runStatus);
		System.out.println(testDescription);
		System.out.println(invocationCount);
		System.out.println(priority);
		System.out.println(rowAndTestCaseMap);
	}
	
	/*
	 * Reads the data from the excel sheet and store the values in respective lists which will be used in annotation transformer class
	 */

	public static void getRunStatus() throws Exception {
		try {
			fs=new FileInputStream("./src/test/resources/testdata.xlsx");
			workbook=new XSSFWorkbook(fs);
//			workbook = WorkbookFactory.create(fs);
			sheet=workbook.getSheet("RUNMANAGER");
			for(int i=1;i<=getLastRowNum("RUNMANAGER");i++) {
				testCases.add(getCellContent("RUNMANAGER", i, "TestCaseName"));
				testDescription.add(getCellContent("RUNMANAGER", i, "Test Case Description"));
				runStatus.add(getCellContent("RUNMANAGER", i, "Execute"));
				invocationCount.add(getCellContent("RUNMANAGER", i, "InvocationCount"));
				priority.add(getCellContent("RUNMANAGER", i, "Priority"));
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	public static int getRowNumForRowName(String sheetname,String rowName) {
		int rownum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=1;i<=getLastRowNum(sheetname);i++) {
			if(rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum=i;
				break;
			}
		}

		return rownum;
	}
	

	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}
	
	
	/*
	 * Takes sheetname, row number, column number as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,int colnum) {
		sheet=workbook.getSheet(sheetname);
		int celltype=sheet.getRow(rownum).getCell(colnum).getCellType();
		Cell cell=sheet.getRow(rownum).getCell(colnum);
		String temp="";
		if(celltype==Cell.CELL_TYPE_STRING) {
			temp= cell.getStringCellValue();
		}
		else if(celltype==Cell.CELL_TYPE_NUMERIC || celltype==Cell.CELL_TYPE_FORMULA) {
			temp=String.valueOf(cell.getNumericCellValue());
		}
		else if(celltype==cell.CELL_TYPE_BOOLEAN) {
			temp=String.valueOf(cell.getBooleanCellValue());
		}
		else if(celltype==Cell.CELL_TYPE_ERROR) {
			temp=String.valueOf(cell.getErrorCellValue());
		}
		return temp;
	}
	

	/*
	 * Takes sheetname, row number, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,int rownum,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int colnum=getColumnNumForColumnName(sheetname, columnname);
		return getCellContent(sheetname,rownum,colnum);

	}

	/*
	 * Takes sheetname, row name, column name as parameter
	 * return cell value
	 */
	public static String getCellContent(String sheetname,String rowname,String columnname) {
		sheet=workbook.getSheet(sheetname);
		int rownum=getRowNumForRowName(sheetname, rowname);
		int colnum=getColumnNumForColumnName(sheetname, columnname);

		return getCellContent(sheetname,rownum,colnum);
	}
	
	public static int getColumnNumForColumnName(String sheetname, String columnname) {
		int colnum=0;
		sheet=workbook.getSheet(sheetname);
		for(int i=0;i<getLastColumnNum(sheetname, 0);i++) {
			if(columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum=i;
				break;
			}
		}

		return colnum;
	}
	
	/*
	 * Takes sheetname, row number as parameter
	 * return last cell number of the row
	 */
	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}


	
}
