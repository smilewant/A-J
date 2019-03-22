package com.further.run.media;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.further.run.R;
import com.further.run.main.MainActivity;
import com.further.run.media.BootAnimResponse.BootAnimModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 2019/1/11.
 */
public class SplashActivity extends AppCompatActivity {
    SplashMediaView splashMediaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
        setContentView(R.layout.activity_splash);
        splashMediaView = findViewById(R.id.splash_media_view);

        BootAnimResponse response = new BootAnimResponse();
        BootAnimModel modelt = response.new BootAnimModel();
        modelt.videoUrl = Constant.getVideoInfo().get(0).getFilePath();
        modelt.name = "splash";
        modelt.endTime= "2019-03-01 00:00:00";

        List<BootAnimModel> models = new ArrayList<>();
        models.add(modelt);
        SplashMediaService.runSplashMediaService(this, models);
        for (BootAnimModel model : models) {
            if (!TextUtils.isEmpty(model.videoUrl)) {
//                String mediaName = model.name.concat("_").concat(model.endTime.substring(0, 10)).concat(".mp4");
                String mediaName = "-02-01.mp4";
                String pathName = SplashMediaService.getPatchDir(this).getAbsolutePath() + "/"+  mediaName;
                File file = new File(pathName);
                if (file.exists()) {

                    splashMediaView.setUrl(pathName);
                    splashMediaView.setOnMediaCompleteListener(new SplashMediaView.MediaComplete() {
                        @Override
                        public void error() {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            SplashActivity.this.finish();
                        }
                    });
                    return;
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashMediaView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashMediaView.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        splashMediaView.release();
        super.onDestroy();
    }

}
