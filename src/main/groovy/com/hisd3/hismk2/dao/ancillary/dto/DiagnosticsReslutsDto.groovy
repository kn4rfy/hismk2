package com.hisd3.hismk2.dao.ancillary.dto

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import com.hisd3.hismk2.domain.ancillary.Orderslip

class DiagnosticsResultsDto {
	Department department
	ArrayList<OrderSlipItem> diagnosticsList = new ArrayList<Orderslip>()

}
