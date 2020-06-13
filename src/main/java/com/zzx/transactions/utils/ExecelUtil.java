package com.zzx.transactions.utils;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class ExecelUtil {



    /**
     * 下载execel表模板
     */
    public static void download(HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        HSSFSheet sheet = workbook.createSheet("sheet1");//创建工作表(Sheet)
//设置第一列宽（3766）
        sheet.setColumnWidth(0, 3766);
//设置单元格数据格式
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("#,##0.000"));

//创建CellStyle或HSSFCellStyle对象
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

//设置单元格字体位置水平方向
        style.setAlignment(HorizontalAlignment.LEFT);
//设置单元格字体位置垂直方向
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//设置边框
        style.setBorderBottom(BorderStyle.THIN);   //底部边框样式
        //通过颜色索引设置底部颜色
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //底部边框颜色

        //同理，设置左边样式
        style.setBorderLeft(BorderStyle.THIN);    //左边边框样式
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());    //左边边框颜色

        //同理，设置右边样式
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREEN.getIndex());
        //最后，设置顶部样式
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BROWN.getIndex());
//设置字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12); // 字体高度
        font.setFontName(" 宋体 "); // 字体
        style.setFont(font);

        HSSFRow row = sheet.createRow(0);// 创建行,从0开始
        HSSFCell one=row.createCell(0);// 设置单元格内容,重载
        one.setCellStyle(style);
        one.setCellValue("第1个参数");
        HSSFCell two=row.createCell(1);
        two.setCellStyle(style);
        two.setCellValue("第2个参数");
        HSSFCell three=row.createCell(2);
        three.setCellStyle(style);
        three.setCellValue("第3个参数");
        HSSFCell four=row.createCell(3);
        four.setCellStyle(style);
        four.setCellValue("第4个参数");
        HSSFCell five=row.createCell(4);
        five.setCellStyle(style);
        five.setCellValue("第5个参数");

        HSSFRow row_one = sheet.createRow(1);

        HSSFCell cell=row_one.createCell(0);
        cell.setCellStyle(textStyle);//设置单元格格式为"文本"
        cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(131231688);
        row_one.createCell(1).setCellValue("【参数2】");
        row_one.createCell(2).setCellValue("【参数3】");
        row_one.createCell(3).setCellValue("【参数4】");
        row_one.createCell(4).setCellValue("【参数5】");


        OutputStream outputStream = null;
        String filename = "模板.xls";
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(outputStream);//保存Excel文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (outputStream != null) {
                outputStream.close();//关闭文件流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OK!");
    }


    /**
     * 遍历获取相应类型值静态工具类
     * @param cell
     * @return
     */
    private static String getCellDate(Cell cell) {
        String return_string = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                return_string = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                return_string = cell.getNumericCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return_string = String.valueOf(cell.getBooleanCellValue());
            default:
                return_string = "";
                break;
        }
        return return_string;
    }


    /**
     * 获取execel表的文本内容
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //通过输入流获取工作簿
        InputStream in = new FileInputStream("C:\\Users\\Husky\\Desktop\\模板.xls");
        //(以下直接使用的是类而不是接口，因为类有实现还有自己的方法，更加强大)
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        ExcelExtractor excelExtractor = new ExcelExtractor(wb);
        //去掉sheet名字
        excelExtractor.setIncludeSheetNames(false);
        //抽取文本输出
        System.out.println(excelExtractor.getText());
        in.close();
    }
}
