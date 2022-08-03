//package com.vnexpress.springbootproject.file;
//
//
//import com.vnexpress.springbootproject.dto.ContentDto;
////import org.apache.poi.ooxml.POIXMLDocumentPart;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeMap;
//
//public class WriteFileExcel {
//
//    XSSFWorkbook workbook = new XSSFWorkbook();
////    Sheet sheet=workbook.createSheet("Content");
//
//
//    // Blank workbook
//    private List<ContentDto> contentDtoList;
//
//    public WriteFileExcel(List<ContentDto> contentDtoList) {
//        this.contentDtoList = contentDtoList;
//
//    }
//
//
//    private void writeHeaderLine() {
//        Sheet sheet=workbook.createSheet("Content");
//        Row row = sheet.createRow(0);
//
//        CellStyle style = workbook.createCellStyle();
//        Font font = workbook.createFont();
//        font.setBold(true);
////        font.setFontHeight(16);
//        style.setFont(font);
//
//        createCell(row, 0, "ID", style);
//        createCell(row, 1, "Title", style);
//        createCell(row, 2, "Main", style);
//        createCell(row, 3, "Time", style);
//        createCell(row, 4, "Image", style);
//
//    }
//
//    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
//        Sheet sheet=workbook.createSheet("Content");
//        sheet.autoSizeColumn(columnCount);
//        Cell cell = row.createCell(columnCount);
//        if (value instanceof Integer) {
//            cell.setCellValue((Integer) value);
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        }else {
//            cell.setCellValue((String) value);
//        }
//        cell.setCellStyle(style);
//    }
//
//    private void writeDataLines() {
//        int rowCount = 1;
//
//        CellStyle style = workbook.createCellStyle();
//        Font font = workbook.createFont();
////        font.setFontHeight(14);
//        style.setFont(font);
//
//        for (ContentDto contentDto : contentDtoList) {
//            Sheet sheet=workbook.createSheet("Content");
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//
//            createCell(row, columnCount++, contentDto.getIdContent(), style);
//            createCell(row, columnCount++, contentDto.getTitleContent(), style);
//            createCell(row, columnCount++, contentDto.getMainContent(), style);
//            createCell(row, columnCount++, contentDto.getTimeContent().toString(), style);
//            createCell(row, columnCount++, contentDto.getImageContent(), style);
//
//        }
//    }
//
//    public void export(HttpServletResponse response) throws IOException {
//        writeHeaderLine();
//        writeDataLines();
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        outputStream.close();
//
//    }
//}
