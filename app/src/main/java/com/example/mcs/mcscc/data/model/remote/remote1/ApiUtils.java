package com.example.mcs.mcscc.data.model.remote.remote1;

public class ApiUtils {
    public static final String BASE_URL = "https://http://de-coding-test.s3.amazonaws.com/books.json";

    public static SOService getSOService(){
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
