package com.campustwin.incident.repository;

import com.campustwin.incident.entity.Incident;
import com.campustwin.incident.entity.IncidentStatus;
import com.campustwin.incident.entity.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // student apne incidents dekhe
    List<Incident> findByReportedByOrderByCreatedAtDesc(String reportedBy);

    // staff apne assigned incidents dekhe
    List<Incident> findByAssignedToOrderByCreatedAtDesc(String assignedTo);

    // type se filter (MEDICAL, SECURITY, etc.)
    List<Incident> findByTypeOrderByCreatedAtDesc(IncidentType type);

    // status se filter
    List<Incident> findByStatusOrderByCreatedAtDesc(IncidentStatus status);

    // type + status dono se filter
    List<Incident> findByTypeAndStatusOrderByCreatedAtDesc(IncidentType type, IncidentStatus status);

    // admin — sab incidents latest pehle
    List<Incident> findAllByOrderByCreatedAtDesc();
}