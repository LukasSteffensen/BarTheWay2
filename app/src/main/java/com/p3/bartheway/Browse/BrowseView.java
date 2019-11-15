package com.p3.bartheway.Browse;

import com.p3.bartheway.Database.Item;

import java.util.List;

public interface BrowseView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Item> items);
    void onErrorLoading(String message);
}
