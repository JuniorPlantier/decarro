package com.plantier.decarro.domain

import org.springframework.stereotype.Component

@Component
class TravelService(
    val travelRequestRepository: TravelRequestRepository
) {

    fun saveTravelRequest(travelRequest: TravelRequest) =
        travelRequestRepository.save(travelRequest)
}