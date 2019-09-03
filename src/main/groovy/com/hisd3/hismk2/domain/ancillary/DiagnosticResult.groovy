package com.hisd3.hismk2.domain.ancillary

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import groovy.transform.TypeChecked
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.Type

import javax.persistence.*
import java.time.Instant

@TypeChecked
@Entity
@Table(schema = "ancillary", name = "diagnostic_results")
class DiagnosticResult extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderslip", referencedColumnName = "id")
	Orderslip orderslip
	
	@Column(name = "service", columnDefinition = "uuid")
	UUID service
	
	@GraphQLQuery
	@Column(name = "name", columnDefinition = "varchar")
	String name
	
	@GraphQLQuery
	@Column(name = "type", columnDefinition = "varchar")
	String type
	
	@GraphQLQuery
	@Column(name = "data", columnDefinition = "varchar")
	String data
	
	@GraphQLQuery
	@Column(name = "mimetype", columnDefinition = "varchar")
	String mimetype
	
	@GraphQLQuery
	@Column(name = "file_name", columnDefinition = "varchar")
	String file_name
	
	@GraphQLQuery
	@Column(name = "url_path", columnDefinition = "varchar")
	String url_path
	
	@GraphQLQuery
	@Column(name = "accession", columnDefinition = "varchar")
	String accession
	
	@GraphQLQuery
	@Column(name = "parsed", columnDefinition = "varchar")
	String parsed
	
	@GraphQLQuery
	@Column(name = "deleted", columnDefinition = "bool")
	Boolean deleted
	
	@Transient
	Instant getCreated() {
		return createdDate
	}
}
