package com.hisd3.hismk2.rest.dto

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.ancillary.OrderSlipItem
import com.hisd3.hismk2.domain.ancillary.Orderslip

class DiagnosticsResultsDto {
	String orderNo = ""
	ArrayList<OrderSlipItem> diagnosticsList = new ArrayList<Orderslip>()
	
}
