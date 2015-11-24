package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.CardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class CardManager {

    private List<CardModel> cardModelList;
    private static Context context;
    DatabaseManager databaseManager;

    public CardManager(List<CardModel> cardModelList, Context context) {
        this.cardModelList = cardModelList;
        this.context = context;
        this.databaseManager = new DatabaseManager(context);
    }

    public List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public void getAllCards() throws IOException {
        if (isNetworkAvailable(context)) {
            for (int page = 30; page < 32; page++) {
                Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                call.enqueue(getCardListCallback());
            }
        } else {
            System.out.print("Get all cards failed.");
        }
    }

    public void getCardById(String cardId) throws IOException {
        CardModel cardModel = databaseManager.getCardByIdFromCache(cardId);

        if (cardModel != null && cardModel.getCardId() != null) {
            cardModelList.add(cardModel);
            EventBus.getDefault().post(new CardEvent(cardModelList));
        } else if (isNetworkAvailable(context)) {
            Call<CardModel> call = Retrofit.getInstance().getCardService().getCardById(cardId);
            call.enqueue(getCardByIdCallback());
        } else {
            System.out.print("Get cardModel by id failed.");
        }
    }

    protected static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @NonNull
    private Callback<MultipleCards> getCardListCallback() {
        return new Callback<MultipleCards>() {
            @Override
            public void onResponse(Response<MultipleCards> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardModelList.addAll(Arrays.asList(response.body().getResults()));
                    EventBus.getDefault().post(new CardEvent(cardModelList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardListCallback failed.");
            }
        };
    }

    @NonNull
    private Callback<CardModel> getCardByIdCallback() {
        return new Callback<CardModel>() {
            @Override
            public void onResponse(Response<CardModel> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardModelList.add(response.body());
                    databaseManager.cacheCard(response.body());
                    EventBus.getDefault().post(new CardEvent(cardModelList));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardByIdCallback failed.");
            }
        };
    }
}