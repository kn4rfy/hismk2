package com.hisd3.hismk2.repository.billing

import com.hisd3.hismk2.domain.billing.Billing
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BillingRepository extends JpaRepository<Billing, UUID> {
    @Query("select b from Billing b where b.patient.id = :patientid")
    List<Billing> getByPatientId(@Param("patientid") UUID patientid)


    @Query("select b from Billing b where b.patient.id = :patientId and b.status = 'ACTIVE' order by b.entryDatetime ASC")
    List<Billing> getBillingByPatient(@Param("patientId") UUID patientId)
}