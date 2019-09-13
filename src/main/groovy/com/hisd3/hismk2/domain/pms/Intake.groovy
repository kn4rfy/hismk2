package com.hisd3.hismk2.domain.pms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(schema = "pms", name = "intakes")
class Intake extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "`case`", referencedColumnName = "id")
	Case parentCase
	
	@GraphQLQuery
	@Column(name = "po_intake", columnDefinition = "varchar")
	String poIntake
	
	@GraphQLQuery
	@Column(name = "tube_intake", columnDefinition = "varchar")
	String tubeIntake
	
	@GraphQLQuery
	@Column(name = "ivf_intake", columnDefinition = "varchar")
	String ivfIntake
	
	@GraphQLQuery
	@Column(name = "blood_intake", columnDefinition = "varchar")
	String bloodIntake

	@GraphQLQuery
	@Column(name = "tpn_intake", columnDefinition = "varchar")
	String tpnIntake

	@GraphQLQuery
	@Column(name = "pb_intake", columnDefinition = "varchar")
	String pbIntake

	@GraphQLQuery
	@Column(name = "medication_intake", columnDefinition = "varchar")
	String medicationIntake
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDatetime
}
