package id.muhajir.kotlinrestfulapi.config

import id.muhajir.kotlinrestfulapi.entity.ApiKey
import id.muhajir.kotlinrestfulapi.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {
    val apiKey = "SECRET"

    override fun run(args: ApplicationArguments?) {
        if(!apiKeyRepository.existsById(apiKey)){
            val entity = ApiKey(apiKey)
            apiKeyRepository.save(entity)
        }
    }
}