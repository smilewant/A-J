package com.further.foundation.download;

public interface ProgressListener {
    void update(String url, long bytesRead, long contentLength, String speed);
}