package com.hisd3.hismk2.config

import com.hisd3.hismk2.domain.Patient
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer

@Configuration
class SpringDataRestConfig implements   RepositoryRestConfigurer {

    @Override
    void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.exposeIdsFor(Patient)
    }
}
