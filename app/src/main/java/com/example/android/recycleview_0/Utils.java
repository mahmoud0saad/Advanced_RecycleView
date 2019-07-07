package com.example.android.recycleview_0;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public  class Utils {
    public static List<Integer> getListImageId(Context context){
        List<Integer> imageIdList=new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            imageIdList.add(context
                    .getResources()
                    .getIdentifier("body"+i,"drawable",context.getPackageName())
            );
        }
        
        return imageIdList;
    }
}
