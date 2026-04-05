package com.campustwin.incident.dto;

import com.campustwin.incident.entity.IncidentStatus;
import com.campustwin.incident.entity.IncidentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IncidentResponse {

    private Long id;
    private IncidentType type;
    private IncidentStatus status;
    private String title;
    private String description;
    private String location;
    private Integer severity;
    private String reportedBy;
    private String assignedTo;
    private String resolutionNotes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
}