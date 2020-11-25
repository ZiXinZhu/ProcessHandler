package com.zzx.transactions.utils;

import com.zzx.transactions.exceptions.CommonException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

public class ExecelUtil {

    private static Logger logger = LoggerFactory.getLogger("info");


    /**
     * 下载execel表模板
     */
    public static void download(HttpServletResponse response, String name, List list, String[] title) {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        HSSFSheet sheet = workbook.createSheet("sheet1");//创建工作表(Sheet)
//设置第一列宽（3766）
        sheet.setColumnWidth(0, 3766);
        HSSFRow row = sheet.createRow(0);// 创建行,从0开始
        for (int i = 0; i < title.length; i++) {
            HSSFCell cells = row.createCell(i);// 设置单元格内容,重载
            styleOne(workbook, cells).setCellValue(title[i]);
        }


        for(int i = 0; i < list.size() ;i++){
            Class cls = list.get(i).getClass();
            //得到所有属性
            Field[] fields = cls.getDeclaredFields();
            HSSFRow row_one = sheet.createRow(i+1);
            styleTwo(workbook, row_one);
            for (int j = 0; j < title.length; j++) {
                //得到属性
                Field field = fields[j];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                Object value = null;
                try {
                    value = field.get(list.get(i));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                row_one.createCell(j).setCellValue(String.valueOf(value));
            }
        }
        OutputStream outputStream = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);//保存Excel文件
            if (outputStream != null) {
                outputStream.close();//关闭文件流
            }
        } catch (Exception e) {
            logger.info("execel流输出时错误,错误详情：{}", e.getMessage());
            throw new CommonException("execel流输出时错误");
        }
        System.out.println("OK!");
    }


    /**
     * 上传并解析execel表
     *
     * @param file
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    public static String upLoadExecel(MultipartFile file) {

        byte[] b = new byte[0];
        try {
            b = file.getBytes();
        } catch (IOException e) {
            logger.info("上传文件出错,错误详情：{}", e.getMessage());
            throw new CommonException("上传文件出错");
        }
        InputStream is = new ByteArrayInputStream(b);
        HSSFWorkbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (IOException e) {
            logger.info("该文件为非execel文件,错误详情：{}", e.getMessage());
            throw new CommonException("该文件为非execel文件");
        }
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                // 循环列Cell
                for (int j = 0; j <= hssfRow.getLastCellNum(); j++) {
                    HSSFCell cell = hssfRow.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    System.out.println(getCellDate(cell));
                }
            }
        }
        return "数据上传成功！";
    }


    /**
     * 遍历获取相应类型值静态工具类
     *
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
                DecimalFormat format = new DecimalFormat("#,##0.000");
                return_string = String.valueOf(format.format(cell.getNumericCellValue()));
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
     *
     * @param file
     * @throws IOException
     */
    public static void outText(MultipartFile file) throws IOException {
        byte[] b = new byte[0];
        try {
            b = file.getBytes();
        } catch (IOException e) {
            logger.info("上传文件出错,错误详情：{}", e.getMessage());
            throw new CommonException("上传文件出错");
        }
        InputStream in = new ByteArrayInputStream(b);
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


    /**
     * 样式1
     *
     * @param workbook
     * @param cell
     * @return
     */
    private static HSSFCell styleOne(HSSFWorkbook workbook, HSSFCell cell) {
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
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * 样式2
     *
     * @param workbook
     * @param cell
     * @return
     */
    private static HSSFRow styleTwo(HSSFWorkbook workbook, HSSFRow cell) {
        //设置单元格数据格式
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("#,##0.000"));
        cell.setRowStyle(textStyle);
        return cell;
    }

}
