package com.zzx.transactions.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
//���õ�Ԫ������λ��
        style.setAlignment(HorizontalAlignment.LEFT);
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


}
