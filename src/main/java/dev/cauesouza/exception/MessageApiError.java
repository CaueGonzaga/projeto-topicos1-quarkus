package dev.cauesouza.exception;

import lombok.Value;

@Value
public class MessageApiError {
    String type;

    String title;

    int status;

    String detail;
}