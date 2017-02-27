package com.bawei.test.http;

import java.util.ArrayList;


/**
 * Created by cj on 2017/2/16.
 */

public interface CallbackNewsData<T> {
    void success(ArrayList<T> newsContents);
}