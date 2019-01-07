package wuchaofei.top.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by cofco on 2019/1/7.
 */

public class ExcelUtil {

    /**
     * 读取excel workbook
     * @see http://blog.csdn.net/shuwei003/article/details/6741649
     * @param path
     * @return
     * @throws IOException
     */
    public static Map<String, Map<String, List<Object>>> readWorkBook(String path) {
        Workbook wb = null;
        boolean isE2007 = false;
        if (path.endsWith(".xlsx")) {
            isE2007 = true;
        }

        Map<String, Map<String, List<Object>>> resultMap = null;
        InputStream is = null;
        try {
            is = new FileInputStream(path);

            if (isE2007) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new HSSFWorkbook(is);
            }
            resultMap = new TreeMap<String, Map<String, List<Object>>>();
            resultMap.put("Sheet1", readSheet(wb.getSheet("Sheet1")));//获得Sheet1
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    private static Map<String, List<Object>> readSheet(Sheet sheet){
        Map<String, List<Object>> colMap = null;
        if(sheet!=null){
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
            colMap = new TreeMap<String, List<Object>>();
            while (rows.hasNext()) {
                Row row = rows.next();  //获得行数据
//                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
                while (cells.hasNext()) {
                    Cell cell = cells.next();
//                    System.out.println("Cell #" + cell.getColumnIndex());

                    List<Object> collist = colMap.get("Cell#" + cell.getColumnIndex());
                    if(null==collist){
                        collist=new ArrayList<Object>();
                        colMap.put("Cell#" + cell.getColumnIndex(), collist);
                    }
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            double value = cell.getNumericCellValue();
                            short format = cell.getCellStyle().getDataFormat();
                            if(format == 14 || format == 31 || format == 57 || format == 58){
                                //日期
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                collist.add(sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                            }else if (format == 20 || format == 32) {
                                //时间
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                collist.add(sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                            }else if(format == 176){
                                //日期
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                collist.add(sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                            }else{
                                collist.add((int)cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            // 自动去两边空格
                            String v = cell.getStringCellValue().trim();
                            if(StringUtils.isNotBlank(v)){
                                collist.add(v);
                            }
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            collist.add(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            collist.add(cell.getCellFormula());
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                            break;
                        default:
                            System.out.println("unsuported sell type="+cell.getCellType());
                            break;
                    }
                }
            }
        }
        return colMap;
    }

    /**
     * 按行读取数据
     * @return
     */
    public static List<Map<String, String>> readData(String path){
        Workbook wb = null;
        boolean isE2007 = false;
        if (path.endsWith(".xlsx")) {
            isE2007 = true;
        }

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        InputStream is = null;

        try {
            is = new FileInputStream(path);

            if (isE2007) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new HSSFWorkbook(is);
            }

            Sheet sheet = wb.getSheetAt(0);

            int count = 0;
            if(sheet!=null){
                Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
                while (rows.hasNext()) {
                    Row row = rows.next();  //获得行数据
//                    System.out.println("Row #" + row.getRowNum());  //获得行号从0开始
                    if(row.getRowNum()==0){
                        continue;
                    }
                    Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
                    Map<String, String> rowMap = new HashMap<String, String>();
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
//                        System.out.println("Cell #" + cell.getColumnIndex());
                        if(cell.getColumnIndex()==0){
                            continue;
                        }
                        switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                double value = cell.getNumericCellValue();
                                short format = cell.getCellStyle().getDataFormat();
                                if(format == 14 || format == 31 || format == 57 || format == 58){
                                    //日期
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    rowMap.put(""+cell.getColumnIndex(),sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                                }else if (format == 20 || format == 32) {
                                    //时间
                                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                    rowMap.put(""+cell.getColumnIndex(),sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                                }else if(format == 176){
                                    //日期
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    rowMap.put(""+cell.getColumnIndex(),sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value)));
                                }else{
                                    String str = "" + cell.getNumericCellValue();
                                    if(str.toLowerCase().indexOf("e")>-1){
                                        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                                        rowMap.put(""+cell.getColumnIndex(), bd.toPlainString());
                                    }else{
                                        rowMap.put(""+cell.getColumnIndex(), str);
                                    }
                                }
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                // 自动去两边空格
                                String v = cell.getStringCellValue().trim();
                                if(StringUtils.isNotBlank(v)){
                                    rowMap.put(""+cell.getColumnIndex(),v);
                                }
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:
                                rowMap.put(""+cell.getColumnIndex(), ""+cell.getBooleanCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                rowMap.put(""+cell.getColumnIndex(), cell.getCellFormula());
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:
                                break;
                            default:
                                System.out.println("unsuported sell type="+cell.getCellType());
                                break;
                        }
                    }
                    System.out.println(rowMap);
                    result.add(rowMap);
                    count++;
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 科学计数法转换
     * @param numberStr
     * @return
     */
    private String convertNumber(String numberStr) {
        BigDecimal resultNumber = new BigDecimal("1");
        // 是否为科学计数法
        if (numberStr.indexOf("E") != -1) {
            int frontLeng = numberStr.indexOf("E");
            String frontNumber = numberStr.substring(0, frontLeng);
            BigDecimal front = new BigDecimal(frontNumber);
            int bankLeng = numberStr.indexOf("+");
            String bankNumber = numberStr.substring(bankLeng + 1, numberStr.length());
            int bankint = Integer.valueOf(bankNumber);
            BigDecimal base = new BigDecimal("10");
            BigDecimal bank = new BigDecimal("1");
            for (int k = 0; k < bankint; k++) {
                bank = bank.multiply(base);
            }
            resultNumber = front.multiply(bank);
        } else {
            resultNumber = new BigDecimal(numberStr);
        }
        // 截取小数点，前面的数字
        String resultStr = String.valueOf(resultNumber);
        int point = resultStr.indexOf(".");
        resultStr = resultStr.substring(0, point);
        return resultStr;
    }
}
