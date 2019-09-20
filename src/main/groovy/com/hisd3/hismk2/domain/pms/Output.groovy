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
@Table(schema = "pms", name = "outputs")
class Output extends AbstractAuditingEntity {
	
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
	@Column(name = "voided_output", columnDefinition = "varchar")
	String voidedOutput
	
	@GraphQLQuery
	@Column(name = "catheter_output", columnDefinition = "varchar")
	String catheterOutput
	
	@GraphQLQuery
	@Column(name = "ng_output", columnDefinition = "varchar")
	String ngOutput
	
	@GraphQLQuery
	@Column(name = "insensible_loss_output", columnDefinition = "varchar")
	String insensibleLossOutput
	
	@GraphQLQuery
	@Column(name = "stool_output", columnDefinition = "varchar")
	String stoolOutput
	
	@GraphQLQuery
	@Column(name = "emesis_output", columnDefinition = "varchar")
	String emesisOutput
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDatetime
}
