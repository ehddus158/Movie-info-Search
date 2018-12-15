package com.example.isbee.moviesearch.util;

import androidx.databinding.BindingAdapter;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapterUtil {

    @BindingAdapter({ "loadUrl" })
    public static void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }

    @BindingAdapter({"srcUrl"})
    public static void setImageUrl(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }
}
