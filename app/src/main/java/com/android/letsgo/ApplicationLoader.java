package com.android.letsgo;


import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;


import com.android.letsgo.db.MeasureData;
import com.android.letsgo.presenter.GetAddsForLoaderPresenter;
import com.android.letsgo.utils.NotificationHelper;
import com.android.letsgo.view.AdsForLoaderView;

import java.util.List;


public class ApplicationLoader extends MultiDexApplication implements AdsForLoaderView {
    public static ApplicationLoader instance;
    private GetAddsForLoaderPresenter presenter;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        presenter = new GetAddsForLoaderPresenter();
        presenter.getAdsInfo();
    }

    public static ApplicationLoader getInstance() {
        return instance;
    }


    @Override
    public void getAds(@Nullable List<MeasureData> dataList) {
            for(int i = 0 ; i< dataList.size(); i++) {
                NotificationHelper.Companion.showNotification(instance, "dd", "tt", "rr");
//                    "Будтье внимательней,\n вы находитесь в радиусе пропавшего животного.",
//                    dataList.get(i).getTypeAnimals() + "\n" + dataList.get(i).getName_animals() + "\nДата пропажи:"
//                            + dataList.get(i).getDate_lose() + "\nАдрес пропажи:" + dataList.get(i).getAddress(), dataList.get(i).getImgUrl());
        }
    }
}
