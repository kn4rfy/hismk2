package com.hisd3.hismk2.domain.bms

import com.hisd3.hismk2.domain.AbstractAuditingEntity
import com.hisd3.hismk2.domain.Department
import com.hisd3.hismk2.domain.pms.Transfer
import io.leangen.graphql.annotations.GraphQLQuery
import org.hibernate.annotations.*

import javax.persistence.*

@javax.persistence.Entity
@javax.persistence.Table(schema = "bms", name = "rooms")
class Room extends AbstractAuditingEntity {
	
	@GraphQLQuery
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "uuid")
	@Type(type = "pg-uuid")
	UUID id
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department", referencedColumnName = "id")
	Department department
	
	@GraphQLQuery
	@Column(name = "room_no", columnDefinition = "varchar")
	String roomNo
	
	@GraphQLQuery
	@Column(name = "bed_no", columnDefinition = "varchar")
	String bedNo
	
	@GraphQLQuery
	@Column(name = "description", columnDefinition = "varchar")
	String description
	
	@GraphQLQuery
	@Column(name = "price", columnDefinition = "numeric")
	BigDecimal price
	
	@GraphQLQuery
	@Column(name = "status", columnDefinition = "varchar")
	String status
	
	@GraphQLQuery
	@Column(name = "type", columnDefinition = "varchar")
	String type
	
	@GraphQLQuery
	@Formula("concat(room_no, coalesce('-' || nullif(bed_no,''), ''), coalesce('-' || nullif(status,'') , ''))")
	String roomName
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	Set<Transfer> roomTransfers = [] as Set
}
