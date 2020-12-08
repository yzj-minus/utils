package com.yzjdev.utils;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class v7Util {

    /**
     * v7列表线性布局
     * @param context context
     * @param recyclerView rv
     * @param direct 布局方向 横向 0 竖直 1
     */
    public static void linear(Context context, RecyclerView recyclerView, int direct){
        recyclerView.setLayoutManager(new LinearLayoutManager(context, direct, false));
    }


    /**
     * v7列表网格布局
     * @param context context
     * @param recyclerView rv
     * @param col 列数
     */
    public static void grid(Context context, RecyclerView recyclerView, int col){
        recyclerView.setLayoutManager(new GridLayoutManager(context, col));
    }


    /**
     * v7列表瀑布流
     * @param recyclerView rv
     * @param col 列数
     */
    public static void staggeredGrid(RecyclerView recyclerView, int col){
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(col, StaggeredGridLayoutManager.VERTICAL));
    }
}
