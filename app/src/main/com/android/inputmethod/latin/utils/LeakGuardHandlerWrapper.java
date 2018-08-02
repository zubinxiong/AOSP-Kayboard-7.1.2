/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.inputmethod.latin.utils;

import android.os.Handler;
import android.os.Looper;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 给 handler 包装了一层，内部含有一个弱引用，避免持有对象 T 造成内存泄漏
 * 关于 handler 的内存泄漏可以参考 https://github.com/francistao/LearningNotes
 * @param <T>
 */
public class LeakGuardHandlerWrapper<T> extends Handler {
    private final WeakReference<T> mOwnerInstanceRef;

    public LeakGuardHandlerWrapper(@Nonnull final T ownerInstance) {
        this(ownerInstance, Looper.myLooper());
    }

    public LeakGuardHandlerWrapper(@Nonnull final T ownerInstance, final Looper looper) {
        super(looper);
        mOwnerInstanceRef = new WeakReference<>(ownerInstance);
    }

    @Nullable
    public T getOwnerInstance() {
        return mOwnerInstanceRef.get();
    }
}
