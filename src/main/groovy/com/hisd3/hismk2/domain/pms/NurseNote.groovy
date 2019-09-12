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
@Table(schema = "pms", name = "nurse_notes")
class NurseNote extends AbstractAuditingEntity {
	
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
	@Column(name = "focus", columnDefinition = "varchar")
	String focus
	
	@GraphQLQuery
	@Column(name = "data", columnDefinition = "varchar")
	String data
	
	@GraphQLQuery
	@Column(name = "action", columnDefinition = "varchar")
	String action
	
	@GraphQLQuery
	@Column(name = "response", columnDefinition = "varchar")
	String response
	
	@GraphQLQuery
	@Column(name = "entry_datetime", columnDefinition = "timestamp")
	LocalDateTime entryDatetime
}
