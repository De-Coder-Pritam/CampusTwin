package com.campustwin.incident.service;

import com.campustwin.incident.dto.CreateIncidentRequest;
import com.campustwin.incident.dto.IncidentResponse;
import com.campustwin.incident.dto.UpdateIncidentRequest;
import com.campustwin.incident.entity.Incident;
import com.campustwin.incident.entity.IncidentStatus;
import com.campustwin.incident.entity.IncidentType;
import com.campustwin.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    // ── Student — naya incident report karo ──────────────────────────────────

    public IncidentResponse createIncident(CreateIncidentRequest request, String reportedBy) {
        Incident incident = new Incident();
        incident.setType(request.getType());
        incident.setTitle(request.getTitle());
        incident.setDescription(request.getDescription());
        incident.setLocation(request.getLocation());
        incident.setSeverity(request.getSeverity());
        incident.setReportedBy(reportedBy);
        incident.setStatus(IncidentStatus.REPORTED);
        return toResponse(incidentRepository.save(incident));
    }

    // ── Student — apne incidents dekho ───────────────────────────────────────

    public List<IncidentResponse> getMyIncidents(String reportedBy) {
        return incidentRepository.findByReportedByOrderByCreatedAtDesc(reportedBy)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Staff — apne assigned incidents dekho ────────────────────────────────

    public List<IncidentResponse> getAssignedIncidents(String assignedTo) {
        return incidentRepository.findByAssignedToOrderByCreatedAtDesc(assignedTo)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Staff — type ke basis pe incidents dekho ─────────────────────────────

    public List<IncidentResponse> getIncidentsByType(IncidentType type) {
        return incidentRepository.findByTypeOrderByCreatedAtDesc(type)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Staff — incident ka status update karo ────────────────────────────────

    public IncidentResponse updateIncident(Long id, UpdateIncidentRequest request, String updatedBy) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found with id: " + id));

        // CLOSED incident dobara update nahi ho sakta
        if (incident.getStatus() == IncidentStatus.CLOSED) {
            throw new RuntimeException("Cannot update a closed incident");
        }

        incident.setStatus(request.getStatus());

        if (request.getResolutionNotes() != null) {
            incident.setResolutionNotes(request.getResolutionNotes());
        }

        // jo staff pehli baar update kar raha hai uski email assign ho jaati hai
        // agar already assigned hai toh change nahi hoga
        if (incident.getAssignedTo() == null) {
            incident.setAssignedTo(updatedBy);
        }

        // RESOLVED ya CLOSED hone pe resolvedAt timestamp set karo
        if (request.getStatus() == IncidentStatus.RESOLVED
                || request.getStatus() == IncidentStatus.CLOSED) {
            incident.setResolvedAt(LocalDateTime.now());
        }

        return toResponse(incidentRepository.save(incident));
    }

    // ── Admin — sab incidents dekho ──────────────────────────────────────────

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Admin — status se filter karo ────────────────────────────────────────

    public List<IncidentResponse> getIncidentsByStatus(IncidentStatus status) {
        return incidentRepository.findByStatusOrderByCreatedAtDesc(status)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ── Koi bhi — single incident by id ──────────────────────────────────────

    public IncidentResponse getIncidentById(Long id) {
        return toResponse(incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found with id: " + id)));
    }

    // ── Helper — Incident → IncidentResponse ─────────────────────────────────

    private IncidentResponse toResponse(Incident incident) {
        return IncidentResponse.builder()
                .id(incident.getId())
                .type(incident.getType())
                .status(incident.getStatus())
                .title(incident.getTitle())
                .description(incident.getDescription())
                .location(incident.getLocation())
                .severity(incident.getSeverity())
                .reportedBy(incident.getReportedBy())
                .assignedTo(incident.getAssignedTo())
                .resolutionNotes(incident.getResolutionNotes())
                .createdAt(incident.getCreatedAt())
                .updatedAt(incident.getUpdatedAt())
                .resolvedAt(incident.getResolvedAt())
                .build();
    }
}