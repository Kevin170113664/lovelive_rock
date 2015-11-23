package com.thoughtworks.lhli.lovelive_rock;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.model.Card;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class CardManager {

    private List<Card> cardList;
    private Context context;

    public CardManager(List<Card> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void getAllCards() throws IOException {
        if (isNetworkAvailable()) {
            for (int page = 70; page < 73; page++) {
                Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                call.enqueue(getCardListCallback());
            }
        } else {
            System.out.print("Get all cards failed.");
        }
    }

    public void getCardById(String Id) throws IOException {
        if (isNetworkAvailable()) {
            Call<Card> call = Retrofit.getInstance().getCardService().getCardById(Id);
            call.enqueue(getCardByIdCallback());
        } else {
            System.out.print("Get card by id failed.");
        }
    }

    protected Boolean isNetworkAvailable() {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @NonNull
    private Callback<MultipleCards> getCardListCallback() {
        return new Callback<MultipleCards>() {
            @Override
            public void onResponse(Response<MultipleCards> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardList.addAll(Arrays.asList(response.body().getResults()));
                    EventBus.getDefault().post(new CardEvent(cardList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardListCallback failed.");
            }
        };
    }

    @NonNull
    private Callback<Card> getCardByIdCallback() {
        return new Callback<Card>() {
            @Override
            public void onResponse(Response<Card> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardList.add(response.body());
                    EventBus.getDefault().post(new CardEvent(cardList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardByIdCallback failed.");
            }
        };

    }
}