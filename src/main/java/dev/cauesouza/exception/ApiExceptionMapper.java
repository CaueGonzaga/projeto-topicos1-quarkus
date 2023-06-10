package dev.cauesouza.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
                if (exception instanceof NotFoundEntityException) {
                        return handleNotFoundException((NotFoundEntityException) exception);
                } else if (exception instanceof ConstraintViolationException) {
                        return handleConflictException((ConstraintViolationException) exception);
                } else if (exception instanceof BadRequestException) {
                        return handleBadRequestException((BadRequestException) exception);
                } else if (exception instanceof JsonProcessingException) {
                        return handleJsonProcessingException((JsonProcessingException) exception);
                }
                return defaultErrorResponse(exception);
        }

        private Response handleNotFoundException(NotFoundEntityException exception) {
                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/not-found",
                                "Recurso não encontrado.",
                                Response.Status.NOT_FOUND.getStatusCode(),
                                exception.getMessage());
                return Response.status(Response.Status.NOT_FOUND)
                                .entity(apiError)
                                .build();
        }

        private Response handleInvalidFormatException(InvalidFormatException exception) {
                String path = exception.getPath().stream()
                                .map(JsonMappingException.Reference::getFieldName)
                                .collect(Collectors.joining("."));

                String detail = String.format(
                                "A campo '%s' recebeu um formato inválido: '%s'. Corrija-o informando um valor compatível com o formato %s.",
                                path, exception.getValue(), exception.getTargetType().getSimpleName());

                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/invalid-format",
                                "Mensagem incompreensível",
                                Response.Status.BAD_REQUEST.getStatusCode(),
                                detail);

                return Response.status(Response.Status.BAD_REQUEST)
                                .entity(apiError)
                                .build();
        }

        private Response handleJsonProcessingException(JsonProcessingException exception) {
                if (exception instanceof InvalidFormatException) {
                        return handleInvalidFormatException((InvalidFormatException) exception);
                }

                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/message-not-readable",
                                "Mensagem incompreensível",
                                Response.Status.BAD_REQUEST.getStatusCode(),
                                "O corpo da requisição está inválido. Verifique o erro de sintaxe.");

                return Response.status(Response.Status.BAD_REQUEST)
                                .entity(apiError)
                                .build();
        }

        private Response handleConflictException(ConstraintViolationException exception) {
                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/conflict",
                                "Conflito de recursos.",
                                Response.Status.CONFLICT.getStatusCode(),
                                exception.getMessage());

                return Response.status(Response.Status.CONFLICT)
                                .entity(apiError)
                                .build();
        }

        private Response handleBadRequestException(BadRequestException exception) {
                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/bad-request",
                                "Requisição inválida.",
                                Response.Status.BAD_REQUEST.getStatusCode(),
                                exception.getMessage());

                return Response.status(Response.Status.BAD_REQUEST)
                                .entity(apiError)
                                .build();
        }

        private Response defaultErrorResponse(Exception exception) {
                MessageApiError apiError = new MessageApiError(
                                "https://localhost:8080/internal-error",
                                exception.getMessage(),
                                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                                "Erro Fatal");

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                                .entity(apiError)
                                .build();
        }
}