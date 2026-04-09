package com.solt.formatter;

import com.solt.ds.FormattedReport;
import com.solt.ds.Report;

public interface ReportFormatter {
	FormattedReport format(Report report);
}
