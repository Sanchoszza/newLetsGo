package com.android.letsgo.utils;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ReadRXFirebaseUtil {

    public static Observable<QuerySnapshot> observeValueEvent(final CollectionReference ref) {
        return Observable.create(new ObservableOnSubscribe<QuerySnapshot>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<QuerySnapshot> emitter) throws Throwable {
                final ListenerRegistration listenerRegistration = ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error!=null){
                            emitter.onError(error);
                        }
                        else {
                            emitter.onNext(value);
                        }
                    }
                });

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {
                        listenerRegistration.remove();
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }

    private void setRxJavaErrorHandler() { RxJavaPlugins.setErrorHandler(Throwable::printStackTrace); }
}
