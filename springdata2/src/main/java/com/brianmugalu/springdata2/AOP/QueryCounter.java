package com.brianmugalu.springdata2.AOP;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class QueryCounter {
    private int queryCount;

    public void incrementQueryCount() {
        queryCount++;
    }

    public int getQueryCount(String methodName) {
        return queryCount;
    }

}
