package com.hisd3.hismk2.config.graphql.pms

import com.coxautodev.graphql.tools.GraphQLResolver
import com.hisd3.hismk2.domain.Patient
import org.springframework.stereotype.Component

@Component
class PatientResolver implements GraphQLResolver<Patient> {
}
