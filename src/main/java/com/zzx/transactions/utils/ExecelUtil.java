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
     * ����execel��ģ��
     */
    public static void download(HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();//����Excel�ļ�(Workbook)
        HSSFSheet sheet = workbook.createSheet("sheet1");//����������(Sheet)
//���õ�һ�п�3766��
        sheet.setColumnWidth(0, 3766);
//���õ�Ԫ�����ݸ�ʽ
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("#,##0.000"));

//����CellStyle��HSSFCellStyle����
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

//���õ�Ԫ������λ��ˮƽ����
        style.setAlignment(HorizontalAlignment.LEFT);
//���õ�Ԫ������λ�ô�ֱ����
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//���ñ߿�
        style.setBorderBottom(BorderStyle.THIN);   //�ײ��߿���ʽ
        //ͨ����ɫ�������õײ���ɫ
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); //�ײ��߿���ɫ

        //ͬ�����������ʽ
        style.setBorderLeft(BorderStyle.THIN);    //��߱߿���ʽ
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());    //��߱߿���ɫ

        //ͬ�������ұ���ʽ
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.GREEN.getIndex());
        //������ö�����ʽ
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BROWN.getIndex());
//��������
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12); // ����߶�
        font.setFontName(" ���� "); // ����
        style.setFont(font);

        HSSFRow row = sheet.createRow(0);// ������,��0��ʼ
        HSSFCell one=row.createCell(0);// ���õ�Ԫ������,����
        one.setCellStyle(style);
        one.setCellValue("��1������");
        HSSFCell two=row.createCell(1);
        two.setCellStyle(style);
        two.setCellValue("��2������");
        HSSFCell three=row.createCell(2);
        three.setCellStyle(style);
        three.setCellValue("��3������");
        HSSFCell four=row.createCell(3);
        four.setCellStyle(style);
        four.setCellValue("��4������");
        HSSFCell five=row.createCell(4);
        five.setCellStyle(style);
        five.setCellValue("��5������");

        HSSFRow row_one = sheet.createRow(1);

        HSSFCell cell=row_one.createCell(0);
        cell.setCellStyle(textStyle);//���õ�Ԫ���ʽΪ"�ı�"
        cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(131231688);
        row_one.createCell(1).setCellValue("������2��");
        row_one.createCell(2).setCellValue("������3��");
        row_one.createCell(3).setCellValue("������4��");
        row_one.createCell(4).setCellValue("������5��");


        OutputStream outputStream = null;
        String filename = "ģ��.xls";
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
            workbook.write(outputStream);//����Excel�ļ�
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (outputStream != null) {
                outputStream.close();//�ر��ļ���
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("OK!");
    }


    /**
     * ������ȡ��Ӧ����ֵ��̬������
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
     * ��ȡexecel����ı�����
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //ͨ����������ȡ������
        InputStream in = new FileInputStream("C:\\Users\\Husky\\Desktop\\ģ��.xls");
        //(����ֱ��ʹ�õ���������ǽӿڣ���Ϊ����ʵ�ֻ����Լ��ķ���������ǿ��)
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        ExcelExtractor excelExtractor = new ExcelExtractor(wb);
        //ȥ��sheet����
        excelExtractor.setIncludeSheetNames(false);
        //��ȡ�ı����
        System.out.println(excelExtractor.getText());
        in.close();
    }
}
