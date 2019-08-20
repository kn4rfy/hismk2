package com.hisd3.hismk2.repository.ancillary


import com.hisd3.hismk2.domain.ancillary.Service
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ServiceRepository extends JpaRepository<Service, UUID> {

    @Query(value = '''Select s from Service s where
            lower(s.serviceName) like concat('%',:filter,'%') or 
            lower(s.serviceCode) like concat('%',:filter,'%')''')
    List<Service> searchlist(@Param("filter") String filter)
}