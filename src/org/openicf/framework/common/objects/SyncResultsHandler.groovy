package org.openicf.framework.common.objects

interface SyncResultsHandler {
    boolean handle(SyncDelta delta);
}
