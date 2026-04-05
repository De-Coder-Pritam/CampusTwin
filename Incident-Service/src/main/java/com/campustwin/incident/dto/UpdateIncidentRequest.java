package com.campustwin.incident.dto;

import com.campustwin.incident.entity.IncidentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateIncidentRequest {

    @NotNull(message = "Status is required")
    private IncidentStatus status;

    private String resolutionNotes;
}