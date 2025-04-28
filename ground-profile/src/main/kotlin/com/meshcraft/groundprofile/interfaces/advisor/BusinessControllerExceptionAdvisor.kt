package com.meshcraft.groundprofile.interfaces.advisor

import jakarta.validation.ConstraintViolationException
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.LocalDateTime

@RestControllerAdvice
class BusinessControllerExceptionAdvisor(
    private val messageSource: MessageSource
) {
    data class ErrorResponse(
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val status: Int,
        val error: String,
        val message: String,
        val path: String,
        val errors: List<FieldError> = emptyList()
    )

    data class FieldError(
        val field: String,
        val message: String,
        val rejectedValue: Any?
    )

    private fun getMessage(code: String, args: Array<Any>? = null, defaultMessage: String): String {
        val locale = LocaleContextHolder.getLocale()
        return messageSource.getMessage(code, args, defaultMessage, locale) ?: defaultMessage
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.map {
            val messageCode = "profile.validation.${it.field}"
            FieldError(
                field = it.field,
                message = getMessage(messageCode, null, it.defaultMessage ?: "validation error"),
                rejectedValue = it.rejectedValue
            )
        }

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = getMessage("error.badrequest", null, "Validation failed for request parameters"),
            path = request.getDescription(false).substringAfter("uri="),
            errors = errors
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(
        ex: ConstraintViolationException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errors = ex.constraintViolations.map {
            val propertyPath = it.propertyPath.toString()
            val fieldName = propertyPath.substring(propertyPath.lastIndexOf('.') + 1)
            val messageCode = "profile.validation.${fieldName}"

            FieldError(
                field = fieldName,
                message = getMessage(messageCode, null, it.message),
                rejectedValue = it.invalidValue
            )
        }

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = getMessage("error.badrequest", null, "Constraint violation"),
            path = request.getDescription(false).substringAfter("uri="),
            errors = errors
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = getMessage("error.badrequest.malformedjson", null, "Malformed JSON request"),
            path = request.getDescription(false).substringAfter("uri=")
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
        ex: MethodArgumentTypeMismatchException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val paramName = ex.name
        val messageArgs: Array<Any> = arrayOf(paramName ?: "unknown parameter")

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = getMessage("error.badrequest.typemismatch", messageArgs, "Type conversion failed: ${ex.message}"),
            path = request.getDescription(false).substringAfter("uri=")
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val messageCode = when {
            ex.message?.contains("Profile not found") == true -> "profile.notfound"
            else -> "error.badrequest"
        }

        val message = getMessage(messageCode, null, ex.message ?: "Bad request")

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.reasonPhrase,
            message = message,
            path = request.getDescription(false).substringAfter("uri=")
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            message = getMessage("error.internal", null, "An unexpected error occurred"),
            path = request.getDescription(false).substringAfter("uri=")
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}