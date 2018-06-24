package org.openicf.framework.common.objects

class SearchResult {

    final String pagedResultsCookie;
    final int remainingPagedResults;

    SearchResult() {
        this(null, -1);
    }

    SearchResult(final String pagedResultsCookie, final int remainingPagedResults) {
        this.pagedResultsCookie = pagedResultsCookie;
        this.remainingPagedResults = remainingPagedResults;
    }
}
