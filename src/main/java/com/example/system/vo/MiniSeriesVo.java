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
@Entity(name = "mini_series")
public class MiniSeriesVo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private String id;
    @Column
    private String target;
    @Column
    private String wins;
    @Column
    private String losses;
    @Column
    private String progress;    
}
