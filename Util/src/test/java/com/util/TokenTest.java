package com.util;

import org.junit.Test;

public class TokenTest {
    @Test
    public void testToken() throws  Exception{
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJlbWFpbCI6ImFAZ21haWwuY29tIiwidXNlcm5hbWUiOiJzYWdhciIsInN1YiI6InNhZ2FyIiwiaWF0IjoxNzIyMTUxNTcxLCJleHAiOjE3MjIxNTMzNzF9.Wi1t5o9eFuibhJycW6ac8WEt_ONqeXQ0TqsXd1mUhiM";

        var tokenParts= Token.getDecompressToken(token);
        System.out.println(tokenParts);

        System.out.println("Userid : "+String.valueOf(tokenParts.get("sub")));
        System.out.println("exp   :" +String.valueOf(tokenParts.get("exp")));
        System.out.println("iat   :" +String.valueOf(tokenParts.get("iat")));
        System.out.println("roles :" +String.valueOf(tokenParts.get("roles")));
    }
}