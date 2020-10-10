package com.bothsavage.service;

import java.util.Map;

/**
 * 报告操作接口
 */
public interface ReportService {
    Map<String, Object> getBusinessReportData() throws Exception;
}