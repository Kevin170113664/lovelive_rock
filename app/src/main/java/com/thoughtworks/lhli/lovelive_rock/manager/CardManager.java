package com.thoughtworks.lhli.lovelive_rock.manager;

import android.support.annotation.NonNull;

import com.thoughtworks.lhli.lovelive_rock.LoveLiveApp;
import com.thoughtworks.lhli.lovelive_rock.Retrofit;
import com.thoughtworks.lhli.lovelive_rock.bus.FetchProcessEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.MainCardEvent;
import com.thoughtworks.lhli.lovelive_rock.bus.SmallCardEvent;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.MultipleCards;

import java.io.IOException;
import java.text.DecimalFormat;
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
    DatabaseManager databaseManager;

    public CardManager(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
        this.databaseManager = new DatabaseManager();
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
                if (LoveLiveApp.getInstance().isNetworkAvailable()) {
                    Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                    Response<MultipleCards> cardsResponse = call.execute();
                    saveCardsToDB(cardsResponse);
                } else {
                    System.out.print("Get all cards failed.");
                }
            }
        }
    }

    private void saveCardsToDB(Response<MultipleCards> response) {
        if (response.isSuccess()) {
            cardModelList.addAll(Arrays.asList(response.body().getResults()));
            for (CardModel cardModel : cardModelList) {
                CardModel queriedCardModel = databaseManager.queryCardById(cardModel.getCardId());
                if (queriedCardModel == null) {
                    databaseManager.cacheCard(cardModel);
                }
            }
            sendFetchProcessEvent(cardModelList.size());
            if (cardModelList.size() == MAX_CARD_NUMBER) {
                EventBus.getDefault().post(new SmallCardEvent(cardModelList));
            }
        } else {
            System.out.print("getCardByIdCallback failed.");
        }
    }

    public void getCardById(String cardId) throws IOException {
        CardModel cardModel = databaseManager.queryCardById(cardId);

        if (cardModel != null && cardModel.getCardId() != null) {
            cardModelList.add(cardModel);
            EventBus.getDefault().post(new MainCardEvent(cardModelList));
        } else if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            Call<CardModel> call = Retrofit.getInstance().getCardService().getCardById(cardId);
            call.enqueue(getCardByIdCallback());
        } else {
            System.out.print("Get cardModel by id failed.");
        }
    }

    @NonNull
    private Callback<CardModel> getCardByIdCallback() {
        return new Callback<CardModel>() {
            @Override
            public void onResponse(Response<CardModel> response, retrofit.Retrofit retrofit) {
                if (response.isSuccess()) {
                    cardModelList.add(response.body());
                    EventBus.getDefault().post(new MainCardEvent(cardModelList));
                    databaseManager.cacheCard(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.print("getCardByIdCallback failed.");
            }
        };
    }

    private void sendFetchProcessEvent(int gottenCardNumber) {
        Double percentage = 100 * gottenCardNumber / Double.parseDouble(MAX_CARD_NUMBER.toString());
        String percentageMsg = new DecimalFormat("0").format(percentage) + "%";
        EventBus.getDefault().post(new FetchProcessEvent(percentageMsg));
    }
}