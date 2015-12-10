package com.thoughtworks.lhli.lovelive_rock;

import com.thoughtworks.lhli.lovelive_rock.factory.FilterFactory;
import com.thoughtworks.lhli.lovelive_rock.model.CardModel;
import com.thoughtworks.lhli.lovelive_rock.model.EventModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 18)
public class FilterFactoryTest {

    private List<CardModel> cardModelList;
    private FilterFactory filterFactory;
    private HashMap<Integer, String> filterMap;

    @Before
    public void setUp() {
        cardModelList = new ArrayList<>();
        filterFactory = new FilterFactory();
        initialiseFilterMap();
    }

    private void initialiseFilterMap() {
        filterMap = new HashMap<>();
        filterMap.put(R.id.rarity_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.rarity_array)[0]);
        filterMap.put(R.id.attribute_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.attribute_array)[0]);
        filterMap.put(R.id.grade_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.grade_array)[0]);
        filterMap.put(R.id.idol_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.idol_array)[0]);
        filterMap.put(R.id.sub_team_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.sub_team_array)[0]);
        filterMap.put(R.id.skill_type_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.skill_type_array)[0]);
        filterMap.put(R.id.event_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.is_event_card_or_not)[0]);
        filterMap.put(R.id.promo_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.is_promo_or_not)[0]);
        filterMap.put(R.id.collection_spinner, LoveLiveApp.getInstance().getResources().getStringArray(R.array.collection_array)[0]);
    }

    @Test
    public void shouldFilterByRarity() {
        CardModel UR = new CardModel();
        UR.setRarity("UR");
        CardModel SR = new CardModel();
        SR.setRarity("SR");
        CardModel N = new CardModel();
        N.setRarity("N");
        cardModelList.add(UR);
        cardModelList.add(SR);
        cardModelList.add(N);
        filterMap.put(R.id.rarity_spinner, "UR");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(UR);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByAttribute() {
        CardModel Kotori = new CardModel();
        Kotori.setAttribute("Pure");
        CardModel Honoka = new CardModel();
        Honoka.setAttribute("Smile");
        CardModel Umi = new CardModel();
        Umi.setAttribute("Cool");
        cardModelList.add(Kotori);
        cardModelList.add(Honoka);
        cardModelList.add(Umi);
        filterMap.put(R.id.attribute_spinner, "Pure");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(Kotori);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByGrade() {
        CardModel Kotori = new CardModel();
        Kotori.setJapaneseName("南ことり");
        CardModel Hanayo = new CardModel();
        Hanayo.setJapaneseName("小泉花陽");
        CardModel Umi = new CardModel();
        Umi.setJapaneseName("園田海未");
        cardModelList.add(Kotori);
        cardModelList.add(Hanayo);
        cardModelList.add(Umi);
        filterMap.put(R.id.grade_spinner, "First");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(Hanayo);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterBySubTeam() {
        CardModel Kotori = new CardModel();
        Kotori.setJapaneseName("南ことり");
        CardModel Hanayo = new CardModel();
        Hanayo.setJapaneseName("小泉花陽");
        CardModel Umi = new CardModel();
        Umi.setJapaneseName("園田海未");
        cardModelList.add(Kotori);
        cardModelList.add(Hanayo);
        cardModelList.add(Umi);
        filterMap.put(R.id.sub_team_spinner, "Lily White");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(Umi);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByIdol() {
        CardModel Kotori = new CardModel();
        Kotori.setJapaneseName("南ことり");
        CardModel Hanayo = new CardModel();
        Hanayo.setJapaneseName("小泉花陽");
        CardModel Umi = new CardModel();
        Umi.setJapaneseName("園田海未");
        cardModelList.add(Kotori);
        cardModelList.add(Hanayo);
        cardModelList.add(Umi);
        filterMap.put(R.id.idol_spinner, "南ことり");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(Kotori);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterBySkillType() {
        CardModel scoreUp = new CardModel();
        scoreUp.setSkill("Perfect Charm");
        CardModel perfectLock = new CardModel();
        perfectLock.setAttribute("Total Trick");
        CardModel healer = new CardModel();
        healer.setAttribute("Timer Yell");
        cardModelList.add(scoreUp);
        cardModelList.add(perfectLock);
        cardModelList.add(healer);
        filterMap.put(R.id.skill_type_spinner, "Score Up");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(scoreUp);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByIsEventCardOrNot() {
        CardModel eventCard = new CardModel();
        eventCard.setEventModel(new EventModel());
        CardModel Honoka = new CardModel();
        Honoka.setEventModel(null);
        CardModel Umi = new CardModel();
        Umi.setEventModel(null);
        cardModelList.add(eventCard);
        cardModelList.add(Honoka);
        cardModelList.add(Umi);
        filterMap.put(R.id.event_spinner, "Yes");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(eventCard);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByIsPromoOrNot() {
        CardModel promoKotori = new CardModel();
        promoKotori.setIsPromo(true);
        CardModel Honoka = new CardModel();
        Honoka.setIsPromo(false);
        CardModel Umi = new CardModel();
        Umi.setIsPromo(false);
        cardModelList.add(promoKotori);
        cardModelList.add(Honoka);
        cardModelList.add(Umi);
        filterMap.put(R.id.promo_spinner, "Yes");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(promoKotori);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }

    @Test
    public void shouldFilterByCollection() {
        CardModel Kotori = new CardModel();
        Kotori.setJapaneseCollection("手品師編");
        CardModel Honoka = new CardModel();
        Honoka.setJapaneseCollection("バレンタイン編");
        CardModel Umi = new CardModel();
        Umi.setJapaneseCollection("職業編Part2");
        cardModelList.add(Kotori);
        cardModelList.add(Honoka);
        cardModelList.add(Umi);
        filterMap.put(R.id.collection_spinner, "手品師編");
        List<CardModel> expectedList = new ArrayList<>();
        expectedList.add(Kotori);

        assertEquals(expectedList, filterFactory.filterCards(cardModelList, filterMap));
    }
}