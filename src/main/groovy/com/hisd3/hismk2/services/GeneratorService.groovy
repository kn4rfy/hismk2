package com.hisd3.hismk2.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

enum GeneratorType {
	PATIENT_NO,
	CASE_NO,
	OrderSlip_NO,
	RR_NO,
	BILLING_NO
}

@Service
class GeneratorService {
	
	@Autowired
	JdbcTemplate jdbcTemplate
	
	void initGenerator(GeneratorType type) {
		
		def name = type.name().toLowerCase()
		def count = jdbcTemplate.queryForObject(" SELECT count(*) FROM pg_class where relkind='S' and relname = ? ",
				Long,
				name + "_gen"
		)
		
		if (!count) {
			
			try {
				jdbcTemplate.execute(" CREATE SEQUENCE " + (name + "_gen") + " INCREMENT 1  MINVALUE 1 START 1 ")
			} catch (Exception e) {
				e.printStackTrace()
			}
			
		}
		
	}
	
	Long getCurrentValue(GeneratorType type) {
		initGenerator(type)
		def name = type.name().toLowerCase()
		
		return jdbcTemplate.queryForObject(" SELECT last_value FROM  ${name}_gen", Long) as Long
	}
	
	String getNextValue(GeneratorType type) {
		initGenerator(type)
		
		def name = type.name().toLowerCase()
		def nextVal = jdbcTemplate.queryForObject(" select nextval('" + (name + "_gen") + "')", Long) as Long
		
		return nextVal?.toString() + ""
	}
	
	Long getNextValueLong(GeneratorType type) {
		initGenerator(type)
		
		def name = type.name().toLowerCase()
		def nextVal = jdbcTemplate.queryForObject(" select nextval('" + (name + "_gen") + "')", Long) as Long
		
		return nextVal
	}
	
	String getNextValue(GeneratorType type, Closure closure) {
		
		initGenerator(type)
		
		def name = type.name().toLowerCase()
		def nextVal = jdbcTemplate.queryForObject(" select nextval('" + (name + "_gen") + "')", Long) as Long
		return closure(nextVal)
	}
	
}
