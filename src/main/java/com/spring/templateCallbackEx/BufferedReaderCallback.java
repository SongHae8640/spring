package com.spring.templateCallbackEx;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    Integer doWithReader(BufferedReader reader) throws IOException;
}
