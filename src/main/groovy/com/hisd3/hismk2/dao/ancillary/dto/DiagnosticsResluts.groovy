package com.hisd3.hismk2.dao.ancillary.dto

import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.ancillary.Orderslip
import com.hisd3.hismk2.domain.ancillary.Service
import com.hisd3.hismk2.domain.pms.Case

class DiagnosticsResults {
      Case parentCase = null
      Department department =null
      List<Orderslip> diagnosticsList = DiagnosticsList
}

class DiagnosticsList {
      Department revenue_center = null
      List<Orderslip> services
}


class RequestedService {
      UUID id = null
      Service service = null
      String status = null
      String category
      String date

}
