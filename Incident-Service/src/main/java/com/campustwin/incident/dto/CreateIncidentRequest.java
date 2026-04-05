package com.campustwin.incident.dto;

import com.campustwin.incident.entity.IncidentType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateIncidentRequest {

    @NotNull(message = "Incident type is required")
    private IncidentType type;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Severity is required")
    @Min(value = 1, message = "Severity minimum 1")
    @Max(value = 5, message = "Severity maximum 5")
    private Integer severity;
}