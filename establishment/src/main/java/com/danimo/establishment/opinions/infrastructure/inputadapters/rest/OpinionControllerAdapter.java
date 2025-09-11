package com.danimo.establishment.opinions.infrastructure.inputadapters.rest;

import com.danimo.establishment.common.infrastructure.annotations.WebAdapter;
import com.danimo.establishment.opinions.application.inputports.CreateOpinionInputPort;
import com.danimo.establishment.opinions.application.inputports.FindingOpinionsByLocationIdInputPorts;
import com.danimo.establishment.opinions.application.outputports.persistence.FindingOpinionByLocationIdOutputPort;
import com.danimo.establishment.opinions.application.usecases.createopinion.CreateOpinionDto;
import com.danimo.establishment.opinions.domain.Opinion;
import com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto.OpinionRequestDto;
import com.danimo.establishment.opinions.infrastructure.inputadapters.rest.dto.OpinionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Opinions", description = "Operaciones relacionadas a las comentarios/opiniones de las habitaciones")
@RestController
@RequestMapping("/v1/opinions")
@WebAdapter
public class OpinionControllerAdapter {
    private final CreateOpinionInputPort createOpinionInputPort;
    private final FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort;

    @Autowired
    public OpinionControllerAdapter(CreateOpinionInputPort createOpinionInputPort, FindingOpinionByLocationIdOutputPort findingOpinionByLocationIdOutputPort) {
        this.createOpinionInputPort = createOpinionInputPort;
        this.findingOpinionByLocationIdOutputPort = findingOpinionByLocationIdOutputPort;
    }


    @Operation(
            summary = "Registrar nueva opinion",
            description = "Devuelve la información de la opinion correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opinion creado"),
            @ApiResponse(responseCode = "404", description = "Opinion no creado")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<OpinionResponseDto> createOpinion(@RequestBody OpinionRequestDto dto) {
        CreateOpinionDto objectAdaptedFromRestToDomain = dto.toDomain();

        Opinion opinion = createOpinionInputPort.createOpinion(objectAdaptedFromRestToDomain);

        OpinionResponseDto response = OpinionResponseDto.fromDomain(opinion);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Obtener las opiniones existentes por id de la location",
            description = "Devuelve la información de las opiniones correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opiniones encontradas"),
            @ApiResponse(responseCode = "404", description = "Opiniones no encontradas")
    })
    @GetMapping("/{locationId}")
    @Transactional
    public ResponseEntity<List<OpinionResponseDto>> getOPinionsByLocationId(@PathVariable String locationId) {
        List<OpinionResponseDto> opinions = findingOpinionByLocationIdOutputPort
                .findingOpinionByLocationId(UUID.fromString(locationId))
                .stream()
                .map(OpinionResponseDto::fromDomain)
                .toList();

        return ResponseEntity.ok(opinions);
    }
}
