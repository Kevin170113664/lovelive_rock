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
    private Integer minCardPage;
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

    private Integer calculateMinCardPage(List<CardModel> cardModelListFromDB) {
        return cardModelListFromDB.size() == 0 ? 1 : Integer.parseInt(new DecimalFormat("0").format(cardModelListFromDB.size() / 10.0));
    }

    public void getAllCards() throws IOException {
        List<CardModel> cardModelListFromDB = databaseManager.queryAllCards();
        if (cardModelListFromDB.size() >= maxCardNumber) {
            EventBus.getDefault().post(new SmallCardEvent(cardModelListFromDB));
            EventBus.getDefault().post(new FetchProcessEvent("100"));
        } else {
            minCardPage = calculateMinCardPage(cardModelListFromDB);
            cardModelList.addAll(cardModelListFromDB);
            getCardByPages();
        }
    }

    private void getCardByPages() throws IOException {
        for (int page = minCardPage; page <= maxCardPage; page++) {
            if (LoveLiveApp.getInstance().isNetworkAvailable()) {
                Call<MultipleCards> call = Retrofit.getInstance().getCardService().getCardList(page);
                Response<MultipleCards> cardsResponse = call.execute();
                saveCardsAndSendEvents(cardsResponse);
            } else {
                System.out.print("Get cards from " + page + " pages failed.");
            }
        }
    }

    private void saveCardsAndSendEvents(Response<MultipleCards> response) {
        if (response.isSuccess()) {
            cardModelList.addAll(Arrays.asList(response.body().getResults()));
            cacheOnePageCards();
            sendFetchProcessEvent(cardModelList.size());
            sendSmallCardEvent();
        } else {
            System.out.print("getCardByIdCallback failed.");
        }
    }

    private void cacheOnePageCards() {
        for (CardModel cardModel : cardModelList) {
            CardModel queriedCardModel = databaseManager.queryCardById(cardModel.getCardId());
            if (queriedCardModel == null) {
                databaseManager.cacheCard(cardModel);
            }
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

    public void getMaxCardNumber() throws IOException {
        if (LoveLiveApp.getInstance().isNetworkAvailable()) {
            Call<Integer[]> call = Retrofit.getInstance().getCardService().getCardId();
            setMaxCardNumber(call.execute());
        } else {
            System.out.print("Get latest card number failed.");
        }
    }

    protected void setMaxCardNumber(Response<Integer[]> cardIdResponse) {
        List<Integer> cardIdList = Arrays.asList(cardIdResponse.body());
        if (cardIdList.size() > 0) {
            String lastCardId = cardIdList.get(cardIdList.size() - 1).toString();
            LoveLiveApp.getInstance().setMaxCardNumber(lastCardId);
        }
    }

    public void updateLatest20Cards() throws IOException {
        if (maxCardNumber >= 20 && LoveLiveApp.getInstance().isNetworkAvailable()) {
            for (int cardId = maxCardNumber; cardId >= maxCardNumber - 19; cardId--) {
                Call<CardModel> call = Retrofit.getInstance().getCardService().getCardById(String.format("%s", cardId));
                Response<CardModel> response = call.execute();
                CardModel cardModel = response.body();
                updateCard(cardModel);
            }
        } else {
            System.out.print("Update latest cards failed.");
        }
    }

    protected void updateCard(CardModel cardModel) {
        databaseManager.deleteCard(cardModel.getCardId());
        databaseManager.cacheCard(cardModel);
    }

    public void getCardBySkill(String skill) {
        List<CardModel> cardModelList = databaseManager.queryCardsBySkill(skill);

        if (cardModelList != null && cardModelList.size() != 0) {
            EventBus.getDefault().post(new SmallCardEvent(cardModelList));
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
        String percentageMsg = new DecimalFormat("0").format((Double) (percentage > 100.0 ? 100.0 : percentage));
        EventBus.getDefault().post(new FetchProcessEvent(percentageMsg));
    }

    private void sendSmallCardEvent() {
        if (cardModelList.size() >= maxCardNumber) {
            EventBus.getDefault().post(new SmallCardEvent(cardModelList));
        }
    }
}