package com.example.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.system.vo.SummonerDetailInfoVo;
import com.example.system.vo.SystemInfoVo;

import jakarta.transaction.Transactional;

@Repository
public interface SystemInfoRepository extends JpaRepository<SystemInfoVo, String> {
    
    @Query(value = "SELECT ID, R_ID, ACCOUNT_ID, PUUID, NAME, PROFILE_ICON_ID, REVISION_DATE, SUMMONER_LEVEL FROM user_info", nativeQuery=true)
    List<SystemInfoVo> selectAll();

    @Query(value = "SELECT ID, QUEUE_TYPE, TIER, RANK, LEAGUEPOINTS, WINS, LOSSES, VETERAN, INACTIVE, FRESHBLOOD, HOTSTREAK FROM user_league", nativeQuery=true)
    List<SummonerDetailInfoVo> selectSummonerDetailAll();

    @Query(value = "INSERT INTO user_league (QUEUE_TYPE, TIER, `RANK`, LEAGUEPOINTS, WINS, LOSSES, VETERAN, INACTIVE, FRESH_BLOOD, HOT_STREAK, SUMMONER_ID)" + 
                    " VALUES(:#{#userLeague.queueType}, :#{#userLeague.tier}, :#{#userLeague.rank}, :#{#userLeague.leaguePoints}, :#{#userLeague.wins}, :#{#userLeague.losses}, :#{#userLeague.veteran}, :#{#userLeague.inactive}, :#{#userLeague.freshBlood}, :#{#userLeague.hotStreak}, :#{#userLeague.summonerId})", nativeQuery=true)
    @Modifying
    @Transactional
    void insertUserLeague(@Param(value="userLeague") SummonerDetailInfoVo info);
}
