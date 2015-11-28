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

    private Integer maxCardNumber;
    private Integer maxCardPage;

    private List<CardModel> cardModelList;
    DatabaseManager databaseManager;

    public CardManager(List<CardModel> cardModelList) {
        this.cardModelList = cardModelList;
        this.databaseManager = new DatabaseManager();
        maxCardNumber = Integer.parseInt(LoveLiveApp.getInstance().getMaxCardNumber());
        maxCardPage = calculateMaxCardPage(maxCardNumber);
    }

    private Integer calculateMaxCardPage(Integer maxCardNumber) {
        return maxCardNumber == 0 ? 0 : Integer.parseInt(new DecimalFormat("0").format(maxCardNumber / 10.0 + 1));
    }

    public void getAllCards() throws IOException {
        List<CardModel> cardModelList = databaseManager.queryAllCards();
        if (cardModelList != null && cardModelList.size() == maxCardNumber) {
            EventBus.getDefault().post(new SmallCardEvent(cardModelList));
            EventBus.getDefault().post(new FetchProcessEvent("100"));
        } else {
            for (int page = 1; page <= maxCardPage; page++) {
                if (LoveLiveApp.getInstance().isNetworkAvailable()) {
                    Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                    Response<MultipleCards> cardsResponse = call.execute();
                    saveCardsToDatabase(cardsResponse);
                } else {
                    System.out.print("Get all cards failed.");
                }
            }
        }
    }

    private void saveCardsToDatabase(Response<MultipleCards> response) {
        if (response.isSuccess()) {
            cardModelList.addAll(Arrays.asList(response.body().getResults()));
            for (CardModel cardModel : cardModelList) {
                CardModel queriedCardModel = databaseManager.queryCardById(cardModel.getCardId());
                if (queriedCardModel == null) {
                    databaseManager.cacheCard(cardModel);
                }
            }
            sendFetchProcessEvent(cardModelList.size());
            if (cardModelList.size() == maxCardNumber) {
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

    public void getLatestCardNumber() throws IOException {
        if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            Call<Integer[]> call = Retrofit.getInstance().getCardService().getCardId();
            Response<Integer[]> cardIdResponse = call.execute();
            List<Integer> cardIdList = Arrays.asList(cardIdResponse.body());
            String lastCardId = cardIdList.get(cardIdList.size() - 1).toString();
            LoveLiveApp.getInstance().setMaxCardNumber(lastCardId);
        } else {
            System.out.print("Get latest card number failed.");
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
        Double percentage = 100 * gottenCardNumber / Double.parseDouble(maxCardNumber.toString());
        String percentageMsg = new DecimalFormat("0").format(percentage);
        EventBus.getDefault().post(new FetchProcessEvent(percentageMsg));
    }
}