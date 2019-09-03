package com.hisd3.hismk2.repository.ancillary

import com.hisd3.hismk2.domain.ancillary.DiagnosticResult
import org.springframework.data.jpa.repository.JpaRepository

interface DiagnosticsResultRepository extends JpaRepository<DiagnosticResult, UUID> {

}
