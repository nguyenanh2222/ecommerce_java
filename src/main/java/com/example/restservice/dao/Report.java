package com.example.restservice.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "REPORT")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 26)
    @Column(name = "usersId", length = 26)
    private String usersId;

    @NotNull
    @Column(name = "participantsId", nullable = false)
    private Long participantsId;

    @Size(max = 45)
    @NotNull
    @Column(name = "reportType", nullable = false, length = 45)
    private String reportType;

    @NotNull
    @Lob
    @Column(name = "notes", nullable = false)
    private String notes;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "createdAt", nullable = false)
    private Instant createdAt;

    @Column(name = "groupId")
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public Long getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(Long participantsId) {
        this.participantsId = participantsId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}