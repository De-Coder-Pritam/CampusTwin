package com.campustwin.incident.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Data
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // kis type ka incident hai
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentType type;

    // current status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status = IncidentStatus.REPORTED;

    // short title
    @Column(nullable = false)
    private String title;

    // detail description
    @Column(columnDefinition = "TEXT")
    private String description;

    // campus location — block, building, area
    @Column(nullable = false)
    private String location;

    // kitna urgent hai 1 (low) to 5 (critical)
    @Column(nullable = false)
    private Integer severity;

    // jo report kar raha hai uska universityId — auth-service se aayega
    @Column(nullable = false)
    private String reportedBy;

    // staff ka universityId jisne handle kiya
    private String assignedTo;

    // staff ke notes — kya action liya
    @Column(columnDefinition = "TEXT")
    private String resolutionNotes;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime resolvedAt;
}