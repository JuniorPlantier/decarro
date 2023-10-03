package com.plantier.decarro.interfaces.mapping

import com.plantier.decarro.domain.PassengerRepository
import com.plantier.decarro.domain.TravelRequest
import com.plantier.decarro.domain.TravelRequestInput
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class TravelRequestMapper(
    val passengerRepository: PassengerRepository
) {
    fun map(input: TravelRequestInput) : TravelRequest {

        val passenger = passengerRepository.findById(input.passengerId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        return TravelRequest(passenger = passenger,
            origin = input.origin,
            destination = input.destination
        )
    }

}