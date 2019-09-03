package com.hisd3.hismk2.domain.bms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Transfer
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.Formula
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*

@Entity
@Table(schema = "philhealth", name = "icd_codes")
class ICDCode implements Serializable{

	@Id
	@GraphQLQuery
	@Column(name = "diagnosis_code", columnDefinition = "varchar")
	String diagnosisCode

	@GraphQLQuery
	@Column(name = "long_name", columnDefinition = "varchar")
	String longName

	@GraphQLQuery
	@Column(name = "primary_amount1", columnDefinition = "numeric")
	BigDecimal primaryAmount1

	@GraphQLQuery
	@Column(name = "primary_hosp_share1", columnDefinition = "numeric")
	BigDecimal primaryHospShare1

	@GraphQLQuery
	@Column(name = "primary_prof_share1", columnDefinition = "numeric")
	BigDecimal primaryProfShare1

}
