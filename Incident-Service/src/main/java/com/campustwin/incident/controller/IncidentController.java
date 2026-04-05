package com.campustwin.incident.controller;

import com.campustwin.incident.dto.CreateIncidentRequest;
import com.campustwin.incident.dto.IncidentResponse;
import com.campustwin.incident.dto.UpdateIncidentRequest;
import com.campustwin.incident.entity.IncidentStatus;
import com.campustwin.incident.entity.IncidentType;
import com.campustwin.incident.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    // ── STUDENT — naya incident report karo ──────────────────────────────────
    // POST /api/incidents

    @PostMapping
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<IncidentResponse> create(
            @Valid @RequestBody CreateIncidentRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(incidentService.createIncident(request, email));
    }

    // ── STUDENT — apne incidents dekho ───────────────────────────────────────
    // GET /api/incidents/my

    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<List<IncidentResponse>> getMyIncidents(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(incidentService.getMyIncidents(email));
    }

    // ── STAFF — apne department ke incidents dekho ────────────────────────────
    // GET /api/incidents/type/MEDICAL

    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyRole('ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<List<IncidentResponse>> getByType(
            @PathVariable IncidentType type) {
        return ResponseEntity.ok(incidentService.getIncidentsByType(type));
    }

    // ── STAFF — apne assigned incidents dekho ────────────────────────────────
    // GET /api/incidents/assigned

    @GetMapping("/assigned")
    @PreAuthorize("hasAnyRole('ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<List<IncidentResponse>> getAssigned(
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(incidentService.getAssignedIncidents(email));
    }

    // ── STAFF — incident status update karo ──────────────────────────────────
    // PUT /api/incidents/2/status

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<IncidentResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateIncidentRequest request,
            @AuthenticationPrincipal String email) {
        return ResponseEntity.ok(incidentService.updateIncident(id, request, email));
    }

    // ── ADMIN — sab incidents dekho ──────────────────────────────────────────
    // GET /api/incidents/admin/all

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<IncidentResponse>> getAll() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    // ── ADMIN — status se filter karo ────────────────────────────────────────
    // GET /api/incidents/admin/status/REPORTED

    @GetMapping("/admin/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<IncidentResponse>> getByStatus(
            @PathVariable IncidentStatus status) {
        return ResponseEntity.ok(incidentService.getIncidentsByStatus(status));
    }

    // ── KOI BHI — single incident by id ──────────────────────────────────────
    // GET /api/incidents/3

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN','SECURITY','MEDICAL','MAINTENANCE')")
    public ResponseEntity<IncidentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncidentById(id));
    }
}