package com.example.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.system.repository.SystemInfoRepository;
import com.example.system.vo.SummonerDetailInfoVo;
import com.example.system.vo.SystemInfoVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
public class SystemInfoController {

    @Autowired
	private Environment environment;
    
    private SystemInfoRepository systemInfoRepository;

    @Autowired
    public SystemInfoController(SystemInfoRepository systemInfoRepository) {
        this.systemInfoRepository = systemInfoRepository;
    }

    @RequestMapping("/system/info")
    @ResponseBody
    public Map<String, Object> systemInfo() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        okhttp3.ResponseBody respBody = null;
        // OkHttpClient client = new OkHttpClient().newBuilder()
        // .build();
        // MediaType mediaType = MediaType.parse("text/plain");
        // RequestBody body = RequestBody.create(mediaType, "");
        // Request request = new Request.Builder()
        // .url("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/fXQw8qmUIIwucur8Pd2AG5wyNsDKhJWHEL7PWLnp8xbZUQ")
        // //.method("GET", body)
        // .addHeader("X-Riot-Token", environment.getProperty("key.riot.key"))
        // .build();
        // Response response = client.newCall(request).execute();
        // if(response.isSuccessful()) {
        //     okhttp3.ResponseBody respBody = response.body();
        //     //System.out.println(respBody.string());
        //     map.put("data", mapper.readTree(respBody.string()));
        // } else {
        //     //nothing to do.
        // }





        String data = apiRiot("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/", "VTVZ2YR4Z-dcbym7lG__RS7aGln7aCKCmqZAxVltKJ89AA");
        List<SummonerDetailInfoVo> detail = mapper.readValue(data, new TypeReference<List<SummonerDetailInfoVo>>() {});
        for (SummonerDetailInfoVo vo : detail) {
            System.out.println(vo.getQueueType());
        }
        map.put("data", mapper.readTree(data));

        
        
        return map;
    }

    @RequestMapping("/system/select")
    public void test() {
        List<SystemInfoVo> a = systemInfoRepository.selectAll();
        for (SystemInfoVo object : a) {
            System.out.println(object.getName());
        }
    }

    // @RequestMapping("/user/testInsert")
    // public void testInsert() throws Exception {
    //     ObjectMapper mapper = new ObjectMapper();
    //     SummonerDetailInfoVo sdVo = new SummonerDetailInfoVo();
    //     sdVo.setSummonerId("fXQw8qmUIIwucur8Pd2AG5wyNsDKhJWHEL7PWLnp8xbZUQ");
    //     String data = apiRiot("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/", "fXQw8qmUIIwucur8Pd2AG5wyNsDKhJWHEL7PWLnp8xbZUQ");
    //     List<SummonerDetailInfoVo> detail = mapper.readValue(data, new TypeReference<List<SummonerDetailInfoVo>>() {});
    //     for (SummonerDetailInfoVo vo : detail) {
    //         sdVo.setQueueType(vo.getQueueType());
    //         sdVo.setTier(vo.getTier());
    //         sdVo.setRank(vo.getRank());
    //         sdVo.setLeaguePoints(vo.getLeaguePoints());
    //         sdVo.setWins(vo.getWins());
    //         sdVo.setLosses(vo.getLosses());
    //         sdVo.setVeteran(vo.getVeteran());
    //         sdVo.setInactive(vo.getInactive());
    //         sdVo.setFreshBlood(vo.getFreshBlood());
    //         sdVo.setHotStreak(vo.getHotStreak());
    //         systemInfoRepository.insertUserLeague(sdVo);
    //     }
    // }

    @RequestMapping("/user/insertSummonerInfo")
    @Scheduled(cron = "0 0 3 * * *")
    public void insertSummonerInfo() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<SystemInfoVo> a = systemInfoRepository.selectAll();
        SummonerDetailInfoVo sdVo = new SummonerDetailInfoVo();
        for (SystemInfoVo object : a) {
            sdVo.setSummonerId(object.getRId());
            //System.out.println(object.getRId());
            String data = apiRiot("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/", object.getRId());
            List<SummonerDetailInfoVo> detail = mapper.readValue(data, new TypeReference<List<SummonerDetailInfoVo>>() {});
            for (SummonerDetailInfoVo vo : detail) {
                sdVo.setQueueType(vo.getQueueType());
                sdVo.setTier(vo.getTier());
                sdVo.setRank(vo.getRank());
                sdVo.setLeaguePoints(vo.getLeaguePoints());
                sdVo.setWins(vo.getWins());
                sdVo.setLosses(vo.getLosses());
                sdVo.setVeteran(vo.getVeteran());
                sdVo.setInactive(vo.getInactive());
                sdVo.setFreshBlood(vo.getFreshBlood());
                sdVo.setHotStreak(vo.getHotStreak());
                systemInfoRepository.insertUserLeague(sdVo);
            }
        }
    }

    public String apiRiot(String apiUrl, String value) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
        //.url("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/fXQw8qmUIIwucur8Pd2AG5wyNsDKhJWHEL7PWLnp8xbZUQ")
        .url(apiUrl + value)
        //.method("GET", body)
        .addHeader("X-Riot-Token", environment.getProperty("key.riot.key"))
        .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()) {
            okhttp3.ResponseBody respBody = response.body();
            //System.out.println(respBody.string());
            //return mapper.readTree(respBody.string());
            return respBody.string();
        } else {
            return "false";
        }
    }
}
