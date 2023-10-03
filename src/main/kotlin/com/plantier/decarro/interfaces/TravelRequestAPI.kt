package com.plantier.decarro.interfaces

import com.plantier.decarro.domain.TravelRequest
import com.plantier.decarro.domain.TravelRequestInput
import com.plantier.decarro.domain.TravelService
import com.plantier.decarro.interfaces.mapping.TravelRequestMapper
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping

@Service
@RestController
@RequestMapping(path=["/travelRequests"], produces=[MediaType.APPLICATION_JSON_VALUE])
class TravelRequestAPI(
    val travelService: TravelService,
    val mapper: TravelRequestMapper
) {

    @PostMapping
    fun makeTravelRequest(@RequestBody travelRequestInput: TravelRequestInput) {
        travelService.saveTravelRequest(mapper.map(travelRequestInput))
    }

}