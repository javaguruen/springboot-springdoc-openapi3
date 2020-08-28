package no.hamre.springboot.openapi3.rest.controller

/*
import org.springframework.hateoas.Link
import org.springframework.hateoas.Link.REL_SELF
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
*/
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

//@Api(tags = "Document")
@Validated
@RestController
@RequestMapping("/v1/documents")
class DocumentController {

    @GetMapping(value = ["/{uid}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        description = "Get document identified by the uniqueIdentifier provided by EPS during document creation",
        parameters = [
            Parameter(name = "Authorization", description = "Bearer token", schema = Schema(type = "string"), required = true, `in` = ParameterIn.HEADER )
        ]
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "OK"),
        ApiResponse(responseCode = "400", description = "Bad Request"), //response = ErrorResponse::class),
        ApiResponse(responseCode = "401", description = "Unauthorized access"), //, response = ErrorResponse::class),
        ApiResponse(responseCode = "403", description = "Access not allowed"), //, response = ErrorResponse::class),
        ApiResponse(responseCode = "404", description = "Document not found"), //, response = ErrorResponse::class),
        ApiResponse(responseCode = "500", description = "Server error")]) //, response = ErrorResponse::class)])
    fun getDocument(
        @Parameter(description = "Document uniqueIdentifier", required = true, `in` = ParameterIn.PATH)
        @PathVariable("uid")
        uid: @NotBlank String?
    ): ResponseEntity<DocumentResource> {
        return ResponseEntity.ok(DocumentResource())
    }
/*

    @GetMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Get all pending (document status is PENDING) documents for given department or user logged in", nickname = "getListOfDocuments")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun getListOfDocuments(
        @ApiParam(value = "Document status") @RequestParam(name = "status") status: String?
    ): ResponseEntity<List<DocumentResource>> {
        return ResponseEntity.ok(listOf(DocumentResource()))
    }

    @GetMapping(value = ["/{uid}$EPS_DOCUMENTS_PATH_ATTACHMENTS"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Get all attachments for document identified by the uniqueIdentifier provided by EPS during document creation, and by the attachment type (optional) ", nickname = "getAllAttachments")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 404, message = "Document not found", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun getAllAttachments(
        @ApiParam(value = "Document uniqueIdentifier", required = true) @PathVariable("uid") uid: @NotBlank String?,
        @ApiParam(value = "Attachment type", allowableValues = "GRUNNBOKSUTSKRIFT, PREREQUISITE") @RequestParam(name = "attachmenttype", required = false) attachmentType: AttachmentType?
    ): ResponseEntity<List<AttachmentResource>> {
        return ResponseEntity.ok(listOf(AttachmentResource()))
    }

    @PostMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Save document ", nickname = "saveDocument", notes = "Save document using b64 encoding")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun saveDocument(
        @ApiParam(value = "Settlement to add document to ") @RequestParam(name = "settlementuid", required = false) settlementUid: String?,
        @ApiParam(name = "Body", value = "Document data field encoded with b64 and metadata", required = true) @RequestBody documentRequest: @Valid DocumentRequest?
    ): ResponseEntity<DocumentResource> {
        return ResponseEntity.created(URI.create("https://ambita.com")).body<DocumentResource>(DocumentResource())
    }

    @PutMapping(value = ["/{uid}$EPS_DOCUMENTS_PATH_STATUS"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Change document status", nickname = "changeDocumentStatus")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun acceptDocument(
        @ApiParam(value = "Document unique id", required = true) @PathVariable("uid") uid: @NotBlank String?,
        @ApiParam(name = "Body", value = "Datafield for the new status", required = true) @RequestBody documentStatusChangeAction: @Valid DocumentStatusChangeAction?
    ): ResponseEntity<*> {
        return ResponseEntity.ok().build<Any>()
    }

    @GetMapping(value = ["/{uid}/details"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Get document details by document unique id ", nickname = "getDocumentDetails")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 404, message = "Document details not found", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun getDocumentDetails(
        @ApiParam(value = "Document unique id", required = true) @PathVariable("uid") uid: @NotBlank String?
    ): ResponseEntity<DocumentMetaDto> {
        return ResponseEntity.ok<DocumentMetaDto>(DocumentMetaDto())
    }

    @GetMapping(value = ["/{uid}/info"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Get all document information (does not include the byte[]) by document unique id.", nickname = "getDocumentInfo")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 404, message = "Document not found", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun getDocumentInfo(
        @ApiParam(value = "Document unique id", required = true) @PathVariable("uid") uid: @NotBlank String?
    ): ResponseEntity<DocumentDto> {
        return ResponseEntity.ok(DocumentDto())
    }

    @PostMapping(value = ["/{uid}/details"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiImplicitParam(name = "Authorization", value = "Bearer token", dataType = "string", paramType = "header", required = true)
    @ApiOperation(value = "Save document details", nickname = "saveDocumentDetails")
    @ApiResponses(value = [ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class), ApiResponse(code = 401, message = "Unauthorized access", response = ErrorResponse::class), ApiResponse(code = 403, message = "Access not allowed", response = ErrorResponse::class), ApiResponse(code = 404, message = "Document not found", response = ErrorResponse::class), ApiResponse(code = 500, message = "Server error", response = ErrorResponse::class)])
    fun saveDocumentDetails(
        @ApiParam(value = "Document unique id", required = true) @PathVariable("uid") uid: @NotBlank String?,
        @ApiParam(value = "Datafield for details") @RequestBody documentDetailRequest: @Valid DocumentDetailRequest?
    ): ResponseEntity<DocumentDetailRequest> {
        return ResponseEntity.ok(DocumentDetailRequest())
    }
*/

}

class DocumentDetailRequest {

}

data class DocumentResource(
    val id: Long = 1,
    val name: String = "Name"
)

class ErrorResponse {

}

class AttachmentResource {

}

class DocumentDto {

}

class DocumentStatusChangeAction{

}
class DocumentMetaDto{

}