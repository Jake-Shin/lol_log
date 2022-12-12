package com.example.system.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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
@Entity(name = "user_league")
public class SummonerDetailInfoVo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int ID;
    @Column
    private String queueType;
    @Column
    private String tier;
    @Column
    private String rank;
    @Column
    private String leaguePoints;
    @Column
    private String wins;
    @Column
    private String losses;
    @Column
    private String veteran;
    @Column
    private String inactive;
    @Column
    private String freshBlood;
    @Column
    private String hotStreak;
    @Column
    private String summonerId;

    private String leagueId;
    private String summonerName;

    @Transient
    private MiniSeriesVo miniSeries;

    //@ConstructorProperties({"miniSeries", "target", "wins", "losses", "progress"})
    //public SummonerDetailInfoVo(){}
    
}
