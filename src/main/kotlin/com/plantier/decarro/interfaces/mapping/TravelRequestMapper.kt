package com.plantier.decarro.interfaces.mapping

import com.plantier.decarro.domain.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
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

    fun map(travelRequest: TravelRequest) : TravelRequestOutput {
        return TravelRequestOutput(
            id = travelRequest.id!!,
            origin = travelRequest.origin,
            destination = travelRequest.destination,
            status = travelRequest.status,
            creationDate = travelRequest.creationDate
        )
    }

    /*  Trabalha com links de única entidade.
        linkTo > os links recebem a atualização automaticamente, passando a classe da API como parâmetro.
        withRel > construção de um atributo do link que indica o que está sendo representado, neste caso um passageiro.
        withTitle >  mensagem explicativa do link
    * */
    fun buildOutputModel(travelRequest: TravelRequest,
                         output: TravelRequestOutput) : EntityModel<TravelRequestOutput> {
        val passengerLink = WebMvcLinkBuilder
            .linkTo(Passenger::class.java)
            .withRel("passenger")
            .withTitle(travelRequest.passenger.name)

        return EntityModel.of(output, passengerLink)
    }
}