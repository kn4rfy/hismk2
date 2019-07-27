package com.hisd3.hismk2.resthandler.pms

import com.hisd3.hismk2.domain.Patient
import com.hisd3.hismk2.services.GeneratorService
import com.hisd3.hismk2.services.GeneratorType
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler

@RepositoryEventHandler
class PatientHandler {

    @Autowired
    GeneratorService generatorService

    @HandleBeforeCreate
    void beforeCreatePatient(Patient patient){

        patient.patientNo = generatorService.getNextValue(GeneratorType.PATIENT_NO){ Long no ->

            StringUtils.leftPad(no.toString(),5,"0")

        }
    }
}
