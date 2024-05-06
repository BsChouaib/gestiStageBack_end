package com.MCBS.GestiStage.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long presentationId;
    private Date presentationDate;
    private Date presentationStartTime;
    private Date presentationEndTime;
    private String location;
    private String presentationTitle;
    private boolean external;

}
