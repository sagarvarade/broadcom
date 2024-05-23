package com.util;

import org.junit.Test;

public class TokenTest {
    @Test
    public void main() throws  Exception{
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJlbWFpbCI6ImFAZ21haWwuY29tIiwidXNlcm5hbWUiOiJzYWdhciIsInN1YiI6InNhZ2FyIiwiaWF0IjoxNzE2Mzk5ODAxLCJleHAiOjE3MTY0MDE2MDF9.M41u2rgBtO8amxerNBmVbiB6A2PD3y7HJKgH3IM3v_M";
        Token t=new Token();
        var x=t.getDecompressToken(token);
        System.out.println(x);
    }
}