package leetcode.middle.VerificationSystem;/*
 *
 * @Param
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Leetcode1797 {
    int timeToLive;//tokenId有效时间长度
    Map<String,Integer> map = new HashMap<String,Integer>();
    public Leetcode1797(int timeToLive) {
        this.timeToLive =  timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId,currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        if(map.containsKey(tokenId) && currentTime - map.get(tokenId) < timeToLive){
            map.put(tokenId,currentTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        int num = 0;
        Set<String> tokenIds = map.keySet();
        for(String token:tokenIds){
            if(currentTime - map.get(token) < timeToLive){
                num++;
            }
        }
        return num;
    }
}
