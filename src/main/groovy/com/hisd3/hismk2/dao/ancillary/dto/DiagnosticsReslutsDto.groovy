package com.hisd3.hismk2.dao.ancillary.dto

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.ancillary.Orderslip

class DiagnosticsResultsDto {
	Department department
	ArrayList<Orderslip> diagnosticsList = new ArrayList<Orderslip>()
	
}
