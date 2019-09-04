package com.further.run.util;

import com.further.run.Setting.FragmentManagerActivity;
import com.further.run.Setting.SettingManagerActivity;
import com.further.algorithm.AlgorithmActivity;
import com.further.algorithm.recursion.HanotaActivity;
import com.further.run.anno.AnnoActivity;
import com.further.run.concurrent.ConcurrentTestActivity;
import com.further.run.customview.SuspendViewService;
import com.further.run.designpatterns.DesignPatternsActivity;
import com.further.run.labzone.aidltest.AidlActivity;
import com.further.run.labzone.eventdispatch.EventDispatchActivity;
import com.further.run.labzone.glide.GlideShowActivity;
import com.further.run.labzone.handlerthread.HandlerThreadTestActivity;
import com.further.run.labzone.hugeimagetest.HugeImageActivity;
import com.further.run.labzone.hugeimagetest.ImageThumbActivity;
import com.further.run.labzone.launchmode.ScrollingActivity;
import com.further.run.labzone.launchmode.SingleInstanceTestActivity;
import com.further.run.labzone.optimize.OptimizeActivity;
import com.further.run.labzone.recyclerview.DoubleRecyclerViewActivity;
import com.further.run.media.SurfaceActivity;
import com.further.run.media.TextureViewActivity;
import com.further.run.media.VideoViewActivity;
import com.further.run.rn.MyReactActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 2018/5/15.
 */
public class ProjectUtil {
    public static List<Class<?>> getClasses() {
        /*
            1.直接从manifest获取所有的activity
            2.通过注解获得所有的activity
         */
        List<Class<?>> classList = new ArrayList<>();
        classList.add(AlgorithmActivity.class);
        classList.add(DesignPatternsActivity.class);
        classList.add(ConcurrentTestActivity.class);
        classList.add(EventDispatchActivity.class);
        classList.add(AnnoActivity.class);
        classList.add(AidlActivity.class);
        classList.add(HugeImageActivity.class);
        classList.add(SingleInstanceTestActivity.class);
        classList.add(SuspendViewService.class);
        classList.add(GlideShowActivity.class);
        classList.add(OptimizeActivity.class);
        classList.add(HandlerThreadTestActivity.class);
        classList.add(DoubleRecyclerViewActivity.class);
        classList.add(ScrollingActivity.class);
        classList.add(SurfaceActivity.class);
        classList.add(VideoViewActivity.class);
        classList.add(TextureViewActivity.class);
        classList.add(SettingManagerActivity.class);
        classList.add(HanotaActivity.class);
        classList.add(ImageThumbActivity.class);
        classList.add(FragmentManagerActivity.class);
        classList.add(MyReactActivity.class);
        return classList;
    }
}
