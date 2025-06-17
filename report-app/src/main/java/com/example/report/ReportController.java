package com.example.report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bbreak.excella.reports.model.CellData;
import org.bbreak.excella.reports.model.CellDataList;
import org.bbreak.excella.reports.processor.ExcelProcessor;
import org.bbreak.excella.reports.processor.ExportParam;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/report")
    public ResponseEntity<ByteArrayResource> downloadReport(HttpServletResponse response) throws IOException {
        // Header data
        CellData header = new CellData("A1", "ヘッダー");

        // Detail data
        List<CellData> details = Arrays.asList(
                new CellData("A2", "明細1"),
                new CellData("A3", "明細2")
        );

        CellDataList dataList = new CellDataList();
        dataList.add(header);
        dataList.addAll(details);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ExcelProcessor processor = new ExcelProcessor();
        processor.setTemplateStream(getClass().getResourceAsStream("/template.xlsx"));
        processor.process(dataList, baos, new ExportParam());

        byte[] bytes = baos.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .contentLength(bytes.length)
                .body(resource);
    }
}
