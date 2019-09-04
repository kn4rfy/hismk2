package com.hisd3.hismk2.repository.philhealth

import com.hisd3.hismk2.domain.bms.ICDCode
import com.hisd3.hismk2.domain.philhealth.RVSCode
import groovy.transform.TypeChecked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@TypeChecked
interface RVSCodesRepository extends JpaRepository<RVSCode, UUID> {
	
	@Query(value = "Select r from RVSCode r order by rvsCode",
			countQuery = "Select count(r) from RVSCode r order by rvsCode"
	)
	List<RVSCode> getRVSCodes()

	@Query(value = '''Select r from RVSCode r where 
            lower(r.rvsCode) like concat('%',:filter,'%') or 
            lower(r.longName) like concat('%',:filter,'%')''')
	List<RVSCode> searchRVSCodes(@Param("filter") String filter)
}
