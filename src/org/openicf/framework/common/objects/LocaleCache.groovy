package org.openicf.framework.common.objects

import java.util.concurrent.atomic.AtomicReference;

class LocaleCache {

    private static final AtomicReference<Locale> INSTANCE = new AtomicReference<Locale>();

    static Locale getInstance() {
        INSTANCE.compareAndSet(null, Locale.getDefault());
        return INSTANCE.get();
    }

    private LocaleCache() {
    }
}
