package com.thoughtworks.lhli.lovelive_rock.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.activity.MainActivity;
import com.thoughtworks.lhli.lovelive_rock.bus.MainCardEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.MediumCardEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SmallCardEvent;
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

    private final Integer MAX_CARD_NUMBER = 738;
    private final Integer MAX_CARD_PAGE = 74;

    private List<CardModel> cardModelList;
    private Context context;
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
        List<CardModel> cardModelList = databaseManager.queryAllCards();
        if (cardModelList != null && cardModelList.size() == MAX_CARD_NUMBER) {
            EventBus.getDefault().post(new SmallCardEvent(cardModelList));
        } else {
            for (int page = 1; page <= MAX_CARD_PAGE; page++) {
                if (isNetworkAvailable(context)) {
                    Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                    call.enqueue(getCardListCallback());
                } else {
                    System.out.print("Get all cards failed.");
                }
            }
        }
    }

    public void getCardById(String cardId) throws IOException {
        CardModel cardModel = databaseManager.queryCardById(cardId);

        if (cardModel != null && cardModel.getCardId() != null) {
            cardModelList.add(cardModel);
            postEvent();
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
                    for (CardModel cardModel : cardModelList) {
                        CardModel queriedCardModel = databaseManager.queryCardById(cardModel.getCardId());
                        if (queriedCardModel == null) {
                            databaseManager.cacheCard(cardModel);
                        }
                    }
                    String lastCardId = cardModelList.get(cardModelList.size() - 1).getCardId();
                    if (lastCardId.equals(MAX_CARD_NUMBER.toString())) {
                        EventBus.getDefault().post(new SmallCardEvent(cardModelList));
                    }
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
                    postEvent();
                    databaseManager.cacheCard(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardByIdCallback failed.");
            }
        };
    }

    private void postEvent() {
        if (context.getClass().equals(MainActivity.class)) {
            EventBus.getDefault().post(new MainCardEvent(cardModelList));
        } else {
            EventBus.getDefault().post(new MediumCardEvent(cardModelList));
        }
    }
}