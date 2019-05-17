package com.example.administrator.travel02.bean;

import java.util.List;

public class FollowedBean {

    /**
     * code : 0
     * desc :
     * result : {"page":1,"limit":20,"count":4,"banmi":[{"id":1,"name":"榎本伸也","location":"东京","occupation":"东京著名花艺师","introduction":"大家好，我是老夏，东京人，一个讲汉语带点北京味儿的花艺师。研习花道不仅修心养性，更让我对生活中的美有着独到的见解与嗅觉。旅途之中，我同样善于用一名花艺师的特有的目光，去发现并勾勒那些细微处的美好。","following":3283,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748145275_1313268db35f9fd931c523ddacd9292b.jpg","isFollowed":true},{"id":66,"name":"Sudy","location":"西班牙","occupation":"航海环球旅行规划师，摄影师","introduction":"你好，我是Sudy，一个热爱美食和旅行的摄影师，至今已走过了几十个国家和地区，上百座城市和村镇。\u201c环球旅行\u201d一直是我的梦想，所以两年前，我辞去了工作，用全部的积蓄买了一艘帆船，开始了我的航海环游世界之旅。现在也正和我的船长一起驾着帆船游在世界各地呢。我喜欢用镜头记录不同地方的风景和人文，给你分享我的旅程以及世界各地不同人群的生活方式，也希望在旅行中能够为我们居住的地球尽一份自己微薄的力量。\n\n我陆续会在\u201c伴米旅行\u201d上推出环游世界的各国独家私藏路线，如果你也对这世界好奇，想探索，就跟着我一起上船，航行在这个有趣的星球吧！","following":1191,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524048812294_a9cd79447c501647fffac2420f0ba9af.jpg","isFollowed":true},{"id":68,"name":"一阵清风+墨青原夫妻档","location":"俄罗斯","occupation":"环球旅行家，专业潜水玩家，林业知行者","introduction":"你好，我们是一对携手浪迹天涯近30年，满世界潜水20余年的夫妻。很荣幸如今大规模普及的国际潜水组织PADI、NAUI都是由我们两人邀请进入中国的。现在在伴米，作为深度旅行分享人，我们想以喜爱的昵称登场：我是一阵清风，我的太太是墨青原。\n\n说起俄罗斯，我家世代一直和它有着深厚渊源。祖父在世时既与前苏联文艺界互有唱和；我父亲曾留学苏联学艺术，和母亲一样也都习得俄文，在父母及他们留苏同学前辈影响下，我和太太很小就萌生了俄罗斯情节，可以说对它的了解，完全融入了我们的血液。后来我从北大俄文系毕业，又公派留学苏联多年。那些年，我们一直喜欢行走在俄罗斯的大街小巷，去探寻它背后的故事。我们对这里有着深沉的热爱。 因此，我们夫妻俩会陆续给你分享深度游俄罗斯的精彩旅行线路，以及它背后不为人知的故事，让你认识一个更加生动的俄罗斯。","following":1040,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524541023611_cc77baca8a4be7d77a646fdd5fcb799a.jpg","isFollowed":true},{"id":71,"name":"钟宣宣","location":"上海","occupation":"记者/网站主编/签约摄影师","introduction":"你好，我是钟宣宣，媒体记者/网站主编/图片社签约摄影师。嗯，两条\u201c斜杠\u201d，半个上海人。近15年，一直居住和工作在上海中心城内规模最大、优秀历史建筑数量最多的历史文化风貌区\u2014\u2014衡复历史文化风貌区。从湖南路慢慢走到泰安路，600米，上海市中心最幽静的街区，中间穿过红遍上海、网红店扎堆的武康路，就是我每天\u201c上班-回家\u201d的日常。\n\n晴天，阳光洒在地上幻化成斑斑点点的树荫，浓密的梧桐下掩映着\u201c永不拓宽\u201d的风情小路和一栋栋满载传奇的名人旧居，创意Club、艺术Saloon、咖啡Bar错落其间，旧与新、传统与潮流、怀旧感与现代性交叠融合建筑、历史、文化在这条马路上恰合\u201c三位一体\u201d，你若游得太快，品得太急，如何解得个中纷呈滋味？","following":701,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1527731400615_25670aaa3ade1a0f48f51a47e9feca5b.jpg","isFollowed":true}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * page : 1
         * limit : 20
         * count : 4
         * banmi : [{"id":1,"name":"榎本伸也","location":"东京","occupation":"东京著名花艺师","introduction":"大家好，我是老夏，东京人，一个讲汉语带点北京味儿的花艺师。研习花道不仅修心养性，更让我对生活中的美有着独到的见解与嗅觉。旅途之中，我同样善于用一名花艺师的特有的目光，去发现并勾勒那些细微处的美好。","following":3283,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1511748145275_1313268db35f9fd931c523ddacd9292b.jpg","isFollowed":true},{"id":66,"name":"Sudy","location":"西班牙","occupation":"航海环球旅行规划师，摄影师","introduction":"你好，我是Sudy，一个热爱美食和旅行的摄影师，至今已走过了几十个国家和地区，上百座城市和村镇。\u201c环球旅行\u201d一直是我的梦想，所以两年前，我辞去了工作，用全部的积蓄买了一艘帆船，开始了我的航海环游世界之旅。现在也正和我的船长一起驾着帆船游在世界各地呢。我喜欢用镜头记录不同地方的风景和人文，给你分享我的旅程以及世界各地不同人群的生活方式，也希望在旅行中能够为我们居住的地球尽一份自己微薄的力量。\n\n我陆续会在\u201c伴米旅行\u201d上推出环游世界的各国独家私藏路线，如果你也对这世界好奇，想探索，就跟着我一起上船，航行在这个有趣的星球吧！","following":1191,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524048812294_a9cd79447c501647fffac2420f0ba9af.jpg","isFollowed":true},{"id":68,"name":"一阵清风+墨青原夫妻档","location":"俄罗斯","occupation":"环球旅行家，专业潜水玩家，林业知行者","introduction":"你好，我们是一对携手浪迹天涯近30年，满世界潜水20余年的夫妻。很荣幸如今大规模普及的国际潜水组织PADI、NAUI都是由我们两人邀请进入中国的。现在在伴米，作为深度旅行分享人，我们想以喜爱的昵称登场：我是一阵清风，我的太太是墨青原。\n\n说起俄罗斯，我家世代一直和它有着深厚渊源。祖父在世时既与前苏联文艺界互有唱和；我父亲曾留学苏联学艺术，和母亲一样也都习得俄文，在父母及他们留苏同学前辈影响下，我和太太很小就萌生了俄罗斯情节，可以说对它的了解，完全融入了我们的血液。后来我从北大俄文系毕业，又公派留学苏联多年。那些年，我们一直喜欢行走在俄罗斯的大街小巷，去探寻它背后的故事。我们对这里有着深沉的热爱。 因此，我们夫妻俩会陆续给你分享深度游俄罗斯的精彩旅行线路，以及它背后不为人知的故事，让你认识一个更加生动的俄罗斯。","following":1040,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1524541023611_cc77baca8a4be7d77a646fdd5fcb799a.jpg","isFollowed":true},{"id":71,"name":"钟宣宣","location":"上海","occupation":"记者/网站主编/签约摄影师","introduction":"你好，我是钟宣宣，媒体记者/网站主编/图片社签约摄影师。嗯，两条\u201c斜杠\u201d，半个上海人。近15年，一直居住和工作在上海中心城内规模最大、优秀历史建筑数量最多的历史文化风貌区\u2014\u2014衡复历史文化风貌区。从湖南路慢慢走到泰安路，600米，上海市中心最幽静的街区，中间穿过红遍上海、网红店扎堆的武康路，就是我每天\u201c上班-回家\u201d的日常。\n\n晴天，阳光洒在地上幻化成斑斑点点的树荫，浓密的梧桐下掩映着\u201c永不拓宽\u201d的风情小路和一栋栋满载传奇的名人旧居，创意Club、艺术Saloon、咖啡Bar错落其间，旧与新、传统与潮流、怀旧感与现代性交叠融合建筑、历史、文化在这条马路上恰合\u201c三位一体\u201d，你若游得太快，品得太急，如何解得个中纷呈滋味？","following":701,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1527731400615_25670aaa3ade1a0f48f51a47e9feca5b.jpg","isFollowed":true}]
         */

        private int page;
        private int limit;
        private int count;
        private List<BanmiBean> banmi;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<BanmiBean> getBanmi() {
            return banmi;
        }

        public void setBanmi(List<BanmiBean> banmi) {
            this.banmi = banmi;
        }

        public static class BanmiBean {
            /**
             * id : 1
             * name : 榎本伸也
             * location : 东京
             * occupation : 东京著名花艺师
             * introduction : 大家好，我是老夏，东京人，一个讲汉语带点北京味儿的花艺师。研习花道不仅修心养性，更让我对生活中的美有着独到的见解与嗅觉。旅途之中，我同样善于用一名花艺师的特有的目光，去发现并勾勒那些细微处的美好。
             * following : 3283
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1511748145275_1313268db35f9fd931c523ddacd9292b.jpg
             * isFollowed : true
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
