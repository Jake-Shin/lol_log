package com.example.system.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_info")
public class SystemInfoVo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private String id;
    @Column
    private String rId;
    @Column
    private String accountId;
    @Column
    private String puuid;
    @Column
    private String name;
    @Column
    private String profileIconId;
    @Column
    private String revisionDate;
    @Column
    private String summonerLevel;
}
