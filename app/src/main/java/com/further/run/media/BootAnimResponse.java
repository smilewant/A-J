package com.further.run.media;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hukuan
 * 2019/1/11.
 */
public class BootAnimResponse implements Serializable {
    public List<BootAnimModel> datas;

    public class BootAnimModel implements Serializable {
        public String name;// "测试图片名称",
        public String size;// "640x960",
        public String imgUrl;// "http://192.168.0.121/pics/super/2014/06/29462_480x800.jpg",
        public int timeOut;// "3",
        public String firstChannel;// "iphone",
        public int sorts;// "2",
        public String clickUrl;// "http://m.lvmama.com",
        public boolean display;// true
        // added at v7.9.2
        public String beginTime;
        public String endTime;

        public String videoUrl;
        public String type;
        public boolean isFourthG;
        public int showTimes;
    }
}
