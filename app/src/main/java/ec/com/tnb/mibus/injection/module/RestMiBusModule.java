package ec.com.tnb.mibus.injection.module;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ec.com.tnb.mibus.BuildConfig;
import ec.com.tnb.mibus.data.model.busstation.RealmDouble;
import ec.com.tnb.mibus.data.remote.RestMiBusService;
import io.realm.RealmList;
import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by f3r10 on 5/6/16.
 */

@Module
public class RestMiBusModule {

    Type token = new TypeToken<RealmList<RealmDouble>>(){}.getType();

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmDouble>>() {

                    @Override
                    public void write(JsonWriter out, RealmList<RealmDouble> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmDouble> read(JsonReader in) throws IOException {
                        RealmList<RealmDouble> list = new RealmList<RealmDouble>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new RealmDouble(in.nextDouble()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    RestMiBusService provideRestMiBusService(Gson gson, OkHttpClient okHttpClient){
        OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        httpClientBuilder.addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .build().create(RestMiBusService.class);


    }
}
