package com.plantier.decarro.interfaces

import com.plantier.decarro.domain.Passenger
import com.plantier.decarro.domain.PassengerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping(path = ["/passengers"], produces = [MediaType.APPLICATION_JSON_VALUE])
class PassengerAPI(
    val passengerRepository: PassengerRepository
) {

    @GetMapping
    fun listPassenger() = passengerRepository.findAll()

    @GetMapping("/{id}")
    fun findPassenger(@PathVariable("id") id: Long) =
        passengerRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    @PostMapping
    fun createPassenger(@RequestBody passenger: Passenger) =
        passengerRepository.save(passenger)

    @PutMapping("/{id}")
    fun fullUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: Passenger) : Passenger {
        val foundPassenger = findPassenger(id)
        val copyDriver = foundPassenger.copy(
            name = passenger.name
        )
        return passengerRepository.save(copyDriver)
    }

    @PatchMapping("/{id}")
    fun incrementalUpdatePassenger(@PathVariable("id") id: Long, @RequestBody passenger: Passenger) : Passenger {
        val foundPassenger = findPassenger(id)
        val copyDriver = foundPassenger.copy(
            name = passenger.name ?: foundPassenger.name
        )
        return passengerRepository.save(copyDriver)
    }

    @DeleteMapping("/{id}")
    fun deletePassenger(@PathVariable("id") id: Long) =
        passengerRepository.deleteById(id)
}

data class PatchPassenger(
    val name: String?
)