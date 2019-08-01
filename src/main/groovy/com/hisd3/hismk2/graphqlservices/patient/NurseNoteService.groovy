package com.hisd3.hismk2.graphqlservices.patient

import com.hisd3.hismk2.dao.patientDao.NurseNoteDao
import com.hisd3.hismk2.domain.patientDom.NurseNote
import io.leangen.graphql.annotations.GraphQLArgument
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@GraphQLApi
class NurseNoteService {

    @Autowired
    NurseNoteDao nurseNoteDao

    //============== All Queries ====================
    @GraphQLQuery(name = "nurseNotes", description = "Get all nurse notes")
    List<NurseNote> getNurseNotes() {
        nurseNoteDao.getNurseNotes()
    }

    @GraphQLQuery(name = "nurseNotesByCase", description = "Get nurse notes by patient case")
    List<NurseNote> getNurseNotesByCase(@GraphQLArgument(name = "patientCase") String patientCase) {
        nurseNoteDao.getNurseNotesByCase(patientCase)
    }
}
