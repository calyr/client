package com.calyrsoft.client.datasource

import com.calyrsoft.client.config.ObserveService
import com.calyrsoft.client.mapper.toUserModel
import com.calyrsoft.client.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class IdentityRemoteDataSource: IIdentityRemoteDataSource {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var observeService: ObserveService

    override fun getByCi(ci: String): User {
        val getForObject = restTemplate.getForEntity(
            "http://identity:7004/identity/${ci}",
            UserIdentityModel::class.java
        )
        observeService.getActiveSpan()
        if(getForObject.statusCode.value() == 200 ) {
            return getForObject.body!!.toUserModel()
        } else {
            throw Exception("Error Indentity Api Rest")
        }

    }
}