package com.campustwin.incident.entity;

public enum IncidentStatus {
    REPORTED,       // student ne abhi report kiya
    ACKNOWLEDGED,   // concerned staff ne dekh liya
    IN_PROGRESS,    // kaam chal raha hai
    RESOLVED,       // solve ho gaya
    CLOSED          // officially band
}