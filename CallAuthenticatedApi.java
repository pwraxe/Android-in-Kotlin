
public class RetrofitInitializer {


    public static Retrofit getRetrofitReference(){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseRequest.BASE_URL)
                .build();
    }


    public static Retrofit getClientWithAuth(final String token) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if(!TextUtils.isEmpty(token)) {
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });
        }
        else {

            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });
        }

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
