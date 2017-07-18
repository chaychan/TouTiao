package com.chaychan.news.model.entity;

/**
 * @author ChayChan
 * @description: 用户实体类
 * @date 2017/7/9  10:43
 */

public class UserEntity {
    /**
     * verified_content :
     * avatar_url : http://p3.pstatp.com/thumb/216b000e0abb3ee9cb91
     * user_id : 59834611934
     * name : 电竞手游君
     * follower_count : 0
     * follow : false
     * user_auth_info :
     * user_verified : false
     * description : 游戏 资讯 游戏攻略 你要的这里都有，来这里就对了。
     */

    public String verified_content;
    public String avatar_url;
    public long user_id;
    public String name;
    public int follower_count;
    public boolean follow;
    public String user_auth_info;
    public boolean user_verified;
    public String description;
}
