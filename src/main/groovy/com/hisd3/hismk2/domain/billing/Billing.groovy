package com.hisd3.hismk2.domain.billing

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.pms.Case
import com.hisd3.hismk2.domain.pms.Patient
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type
import org.springframework.data.annotation.CreatedDate

import javax.persistence.*
import java.time.Instant

@Entity
@Table(name = "billing", schema = "billing")
class Billing extends AbstractAuditingEntity {
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@GraphQLQuery
	@CreatedDate
	@Column(name = "entry_datetime", nullable = false)
	Instant entryDatetime
	
	@GraphQLQuery
	@Column(name = "billing_no", columnDefinition = "varchar")
	String billingNo
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient", referencedColumnName = "id")
	Patient patient

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_case", referencedColumnName = "id")
	Case patientCase
}
