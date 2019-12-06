package com.further.run.labzone.recyclerview;

import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.further.run.R;
import com.further.foundation.util.LogUtil;
import com.further.foundation.util.MobileUtil;
import com.further.run.util.ProjectUtil;
import com.further.foundation.adapter.RVAdapter;
import com.further.foundation.adapter.RVHolder;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * Created by Hukuan
 * 2018/9/28.
 * Recyclerview 内嵌 recyclerview
 * 当内嵌的recyclerview高度大于一屏时，刚进入的时候会有个跳动
 */
public class DoubleRecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler_view);

        File file = Environment.getExternalStorageDirectory();
        showFiles(new File("storage"), "File : ");

        List<String> imageList = new ArrayList<>();
        imageList.add("1");
        imageList.add("2");
        int j = imageList.size();
        while (imageList.size() > 1 && imageList.size() < 7) {
            //如果图片的数量很少，执行instantiateItem的时候没有destroy会报错，viewpager的缓存一般是1，正常只要大于3就可以了，这里保险起见设为7
            imageList.addAll(imageList);
            j += j;
        }
        for (int i = 0; i < imageList.size(); i++) {
            System.out.println(imageList.get(i) + "\n");
        }
        System.out.println(imageList);

        final RecyclerView mMainRV = findViewById(R.id.recycler_view);
        mMainRV.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainRV.scrollToPosition(0);
            }
        }, 200);

        mMainRV.setLayoutManager(new LinearLayoutManager(this));
        mMainRV.addItemDecoration(SectionDecoration.Builder.init(new GroupListener() {
            @Override
            public String getGroupName(int pos) {
                return ProjectUtil.getClasses().get(pos).getName();
            }

            @Override
            public View getGroupView(int pos) {
                if (pos > ProjectUtil.getClasses().size()) return null;
//                TextView textView = new TextView(DoubleRecyclerViewActivity.this);
//                textView.setText(pos + "");
//                textView.setBackgroundColor(ContextCompat.getColor(DoubleRecyclerViewActivity.this, R.color.colorPrimary));
                View view = LayoutInflater.from(DoubleRecyclerViewActivity.this).inflate(R.layout.layout, null);
                RecyclerView recyclerView = new RecyclerView(DoubleRecyclerViewActivity.this);
                recyclerView.setBackgroundColor(ContextCompat.getColor(DoubleRecyclerViewActivity.this, R.color.color_ffffff));
                recyclerView.setLayoutManager(new LinearLayoutManager(DoubleRecyclerViewActivity.this, LinearLayoutManager.HORIZONTAL, false));
                List<String> abc = new ArrayList<>();
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                abc.add("跟团游");
                RVAdapter adapter = new RVAdapter<String>(DoubleRecyclerViewActivity.this, abc, R.layout.c_indicator_item) {
                    @Override
                    public void convert(RVHolder holder, String data, int positon) {
                        holder.getItemView().setLayoutParams(new ViewGroup.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT));
                        holder.setText(R.id.item_name, "跟团游");
                        ((TextView) holder.getView(R.id.item_name)).setTextSize(0, 14);
//                holder.setTextColor(R.id.item_name, nowPosition == position ? checkColor : unCheckColor);
                        holder.getView(R.id.item_indicator_bg).setBackgroundColor(ContextCompat.getColor(DoubleRecyclerViewActivity.this, R.color.color_D30775));
                    }
                };
                recyclerView.setAdapter(adapter);
                return recyclerView;
            }
        }).setGroupHeight(MobileUtil.dip2px(40)).build());
        RVAdapter mRvAdapter = new RVAdapter<Class<?>>(this, ProjectUtil.getClasses(), R.layout.child_item_double_activity) {
            @Override
            public int getLayoutResId(Class<?> data) {
                return !data.getName().contains("Design") ? R.layout.child_item_double_activity : R.layout.child_item_double_activity;
            }

            @Override
            public void convert(final RVHolder holder, final Class<?> data, int positon) {

                holder.setText(R.id.content, data.getName());
                TextView text = (TextView) holder.getView(R.id.content);
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.getView(R.id.child_recycler).setVisibility(View.VISIBLE);
                        List<DateVo> dateVos = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            DateVo dateVo = new DateVo();
                            Random random = new Random();
                            dateVo.date = random.nextInt(50) + "";
                            dateVos.add(dateVo);
                        }
                        getSelectDates(dateVos);
                        getSortDates(dateVos);
                        show(dateVos);
                        show(getSelectDates(dateVos));
                        show(getSortDates(dateVos));
//                        LogUtil.e("dates " + show(dateVos));
//                        LogUtil.e("getSelectDates " + getSelectDates(dateVos));
//                        LogUtil.e("getSortDates " + getSortDates(dateVos));
                    }
                });
                RecyclerView childRecycler = (RecyclerView) holder.getView(R.id.child_recycler);
                childRecyler(childRecycler);
            }
        };
        mMainRV.setAdapter(mRvAdapter);

    }

    private long showFiles(File file, String blank) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            long size = 0;
            if (files != null) {
                for (File f : files) {
                    LogUtil.d(blank + f.getAbsolutePath());
                    size += showFiles(f, blank + " ");
                }
            }
            if (size / 1024 > 1024 * 10) {
                LogUtil.d("***" + file.getAbsolutePath() + " : " + size / 1024 / 1024);
            }
            return size;
        } else {
            long fileSize = file.length();

            LogUtil.d(blank + file.getAbsolutePath() + " : " + fileSize);

            return fileSize;
        }
    }

    private void show(List<DateVo> dateVos) {
        for (DateVo dateVo : dateVos) {
            LogUtil.e("dates " + dateVo.date);
        }
        LogUtil.e("==============================");
    }

    protected List<DateVo> getSelectDates(final List<DateVo> dateVos) {
        final List<DateVo> selectedDates = new ArrayList<>();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Observable.from(dateVos)
                .groupBy(new Func1<DateVo, Integer>() {
                    @Override
                    public Integer call(DateVo dateVo) {
                        return dateVos.indexOf(dateVo) / 4;
                    }
                })
                .subscribe(new Action1<GroupedObservable<Integer, DateVo>>() {
                    @Override
                    public void call(GroupedObservable<Integer, DateVo> integerDateVoGroupedObservable) {
                        integerDateVoGroupedObservable
                                .toSortedList(new Func2<DateVo, DateVo, Integer>() {
                                    @Override
                                    public Integer call(DateVo dateVo, DateVo dateVo2) {
                                        int result = 0;
                                        if (dateVo.date != null && dateVo2.date != null) {
                                            int date1 = Integer.parseInt(dateVo.date);
                                            int date2 = Integer.parseInt(dateVo2.date);
                                            if (date1 < date2)
                                                return -1;
                                            else if (date1 > date2)
                                                return 1;
                                            else if (date1 == date2)
                                                return 0;
                                        }
                                        return result;
                                    }
                                })
                                .subscribe(new Action1<List<DateVo>>() {
                                    @Override
                                    public void call(List<DateVo> dateVos) {
                                        selectedDates.addAll(dateVos);
                                    }
                                });
                    }
                });
        return selectedDates;
    }


    protected List<DateVo> getSortDates(final List<DateVo> dateVos) {
        final List<DateVo> selectedDates = new ArrayList<>();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Observable.from(dateVos)
                .toSortedList(new Func2<DateVo, DateVo, Integer>() {
                    @Override
                    public Integer call(DateVo dateVo, DateVo dateVo2) {
                        int result = 0;
                        if (dateVo.date != null && dateVo2.date != null) {
                            int date1 = Integer.parseInt(dateVo.date);
                            int date2 = Integer.parseInt(dateVo2.date);
                            if (date1 < date2)
                                return -1;
                            else if (date1 > date2)
                                return 1;
                            else if (date1 == date2)
                                return 0;
                        }
                        return result;
                    }
                })
                .subscribe(new Action1<List<DateVo>>() {
                    @Override
                    public void call(List<DateVo> dateVos) {
                        selectedDates.addAll(dateVos);
                    }
                });
        return selectedDates;
    }

    private void childRecyler(RecyclerView childRecycler) {
        childRecycler.setLayoutManager(new LinearLayoutManager(this));
        List<Class<?>> classList = new ArrayList<>();
        classList.addAll(ProjectUtil.getClasses());
        classList.addAll(ProjectUtil.getClasses());
        classList.addAll(ProjectUtil.getClasses());
        classList.addAll(ProjectUtil.getClasses());
        RVAdapter mRvAdapter = new RVAdapter<Class<?>>(this, classList, R.layout.item_main_rv) {
            @Override
            public int getLayoutResId(Class<?> data) {
                return !data.getName().contains("Design") ? R.layout.item_main_rv : R.layout.item_main_rv;
            }

            @Override
            public void convert(RVHolder holder, final Class<?> data, int positon) {
                holder.setText(R.id.content, data.getName());
                TextView text = (TextView) holder.getView(R.id.content);
                text.setTextSize(10);
                text.setTextColor(getResources().getColor(R.color.color_333333));
                text.setPadding(MobileUtil.dip2px(15), MobileUtil.dip2px(5), MobileUtil.dip2px(15), MobileUtil.dip2px(5));

            }
        };
        childRecycler.setAdapter(mRvAdapter);
    }
}
