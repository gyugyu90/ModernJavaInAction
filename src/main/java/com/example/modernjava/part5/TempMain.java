package com.example.modernjava.part5;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TempMain {

    public static void main(String[] args) {
        Observable<TempInfo> observable = getCelsiusTemperatures("New York", "Chicago", "San Francisco");
        observable.blockingSubscribe(new TempObserver());
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        return getTemperature(town)
                .map(tempInfo -> new TempInfo(tempInfo.getTown(), (tempInfo.getTemp() - 32) * 5 / 9 ));
    }

    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(TempMain::getCelsiusTemperature)
                .collect(Collectors.toList()));
    }

    private static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create(emitter -> {
            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe(i -> {
                        if (!emitter.isDisposed()) {
                            if (i >= 5) {
                                emitter.onComplete();
                            } else {
                                try {
                                    emitter.onNext(TempInfo.fetch(town));
                                } catch (Exception ex) {
                                    emitter.onError(ex);
                                }
                            }
                        }
                    });
        });
    }

}
