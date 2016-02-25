package com.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class LoveliveDaoGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(2, "com.thoughtworks.lhli.lovelive_rock.data");

        LoveliveDaoGenerator generator = new LoveliveDaoGenerator();
        generator.addAllEntities(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAllEntities(Schema schema) {
        Entity characterVoice = generateCharacterVoiceEntity(schema);
        Entity event = generateEventEntity(schema);
        Entity idol = generateIdolEntity(schema, characterVoice);
        generateCardEntity(schema, event, idol);
        generateSongEntity(schema, event);
    }

    private Entity generateCharacterVoiceEntity(Schema schema) {
        Entity CharacterVoice = schema.addEntity("CharacterVoice");
        CharacterVoice.addIdProperty();
        CharacterVoice.addStringProperty("url");
        CharacterVoice.addStringProperty("twitter");
        CharacterVoice.addStringProperty("nickname");
        CharacterVoice.addStringProperty("name");
        CharacterVoice.addStringProperty("instagram");
        CharacterVoice.implementsSerializable();
        return CharacterVoice;
    }

    private Entity generateEventEntity(Schema schema) {
        Entity Event = schema.addEntity("Event");
        Event.addIdProperty();
        Event.addStringProperty("japaneseName");
        Event.addStringProperty("romajiName");
        Event.addStringProperty("englishName");
        Event.addStringProperty("image");
        Event.addStringProperty("englishImage");
        Event.addStringProperty("beginning");
        Event.addStringProperty("end");
        Event.addStringProperty("englishBeginning");
        Event.addStringProperty("englishEnd");
        Event.addBooleanProperty("japanCurrent");
        Event.addBooleanProperty("worldCurrent");
        Event.addStringProperty("song");
        Event.addLongProperty("japaneseT1Points");
        Event.addLongProperty("japaneseT1Rank");
        Event.addLongProperty("japaneseT2Points");
        Event.addLongProperty("japaneseT2Rank");
        Event.addLongProperty("englishT1Points");
        Event.addLongProperty("englishT1Rank");
        Event.addLongProperty("englishT2Points");
        Event.addLongProperty("englishT2Rank");
        Event.addStringProperty("note");
        Event.implementsSerializable();
        return Event;
    }

    private Entity generateIdolEntity(Schema schema, Entity characterVoice) {
        Entity Idol = schema.addEntity("Idol");
        Idol.addIdProperty();
        Idol.addStringProperty("name");
        Idol.addStringProperty("japaneseName");
        Idol.addBooleanProperty("main");
        Idol.addShortProperty("age");
        Idol.addStringProperty("birthday");
        Idol.addStringProperty("astrologicalSign");
        Idol.addStringProperty("blood");
        Idol.addShortProperty("height");
        Idol.addStringProperty("measurements");
        Idol.addStringProperty("favoriteFood");
        Idol.addStringProperty("leastFavoriteFood");
        Idol.addStringProperty("hobbies");
        Idol.addStringProperty("attribute");
        Idol.addStringProperty("year");
        Idol.addStringProperty("subUnit");
        Property characterVoiceProperty = Idol.addLongProperty("characterVoiceId").notNull().getProperty();
        Idol.addToOne(characterVoice, characterVoiceProperty);
        Idol.addStringProperty("summary");
        Idol.addStringProperty("websiteUrl");
        Idol.addStringProperty("wikiUrl");
        Idol.addStringProperty("wikiaUrl");
        Idol.addStringProperty("officialUrl");
        Idol.addStringProperty("chibi");
        Idol.addStringProperty("chibiSmall");
        Idol.implementsSerializable();
        return Idol;
    }

    private void generateCardEntity(Schema schema, Entity event, Entity idol) {
        Entity Card = schema.addEntity("Card");
        Card.addIdProperty();
        Card.addStringProperty("cardId");
        Card.addStringProperty("name");
        Card.addStringProperty("japaneseName");
        Property idolProperty = Card.addLongProperty("idolId").notNull().getProperty();
        Card.addToOne(idol, idolProperty);
        Card.addStringProperty("japaneseCollection");
        Card.addStringProperty("rarity");
        Card.addStringProperty("attribute");
        Card.addStringProperty("japaneseAttribute");
        Card.addBooleanProperty("isPromo");
        Card.addStringProperty("promoItem");
        Card.addStringProperty("releaseDate");
        Card.addBooleanProperty("japanOnly");
        Property eventProperty = Card.addLongProperty("eventId").notNull().getProperty();
        Card.addToOne(event, eventProperty);
        Card.addBooleanProperty("isSpecial");
        Card.addStringProperty("hp");
        Card.addStringProperty("minimumStatisticsSmile");
        Card.addStringProperty("minimumStatisticsPure");
        Card.addStringProperty("minimumStatisticsCool");
        Card.addStringProperty("nonIdolizedMaximumStatisticsSmile");
        Card.addStringProperty("nonIdolizedMaximumStatisticsPure");
        Card.addStringProperty("nonIdolizedMaximumStatisticsCool");
        Card.addStringProperty("idolizedMaximumStatisticsSmile");
        Card.addStringProperty("idolizedMaximumStatisticsPure");
        Card.addStringProperty("idolizedMaximumStatisticsCool");
        Card.addStringProperty("skill");
        Card.addStringProperty("japaneseSkill");
        Card.addStringProperty("skillDetails");
        Card.addStringProperty("japaneseSkillDetails");
        Card.addStringProperty("centerSkill");
        Card.addStringProperty("japaneseCenterSkill");
        Card.addStringProperty("japaneseCenterSkillDetails");
        Card.addStringProperty("cardImage");
        Card.addStringProperty("cardIdolizedImage");
        Card.addStringProperty("roundCardImage");
        Card.addStringProperty("roundCardIdolizedImage");
        Card.addStringProperty("videoStory");
        Card.addStringProperty("japaneseVideoStory");
        Card.addStringProperty("websiteUrl");
        Card.addStringProperty("nonIdolizedMaxLevel");
        Card.addStringProperty("idolizedMaxLevel");
        Card.addStringProperty("ownedCards");
        Card.addStringProperty("transparentImage");
        Card.addStringProperty("transparentIdolizedImage");
        Card.addStringProperty("transparentUrPair");
        Card.addStringProperty("transparentIdolizedUrPair");
        Card.implementsSerializable();
    }

    private void generateSongEntity(Schema schema, Entity event) {
        Entity Song = schema.addEntity("Song");
        Song.addIdProperty();
        Song.addStringProperty("name");
        Song.addStringProperty("romaji_name");
        Song.addStringProperty("translated_name");
        Song.addStringProperty("attribute");
        Song.addShortProperty("BPM");
        Song.addShortProperty("time");
        Property eventProperty = Song.addLongProperty("eventId").notNull().getProperty();
        Song.addToOne(event, eventProperty);
        Song.addShortProperty("rank");
        Song.addStringProperty("daily_rotation");
        Song.addShortProperty("daily_rotation_position");
        Song.addStringProperty("image");
        Song.addShortProperty("easy_difficulty");
        Song.addShortProperty("easy_notes");
        Song.addShortProperty("normal_difficulty");
        Song.addShortProperty("normal_notes");
        Song.addShortProperty("hard_difficulty");
        Song.addShortProperty("hard_notes");
        Song.addShortProperty("expert_difficulty");
        Song.addShortProperty("expert_random_difficulty");
        Song.addShortProperty("expert_notes");
        Song.addBooleanProperty("available");
        Song.addLongProperty("itunes_id");
        Song.implementsSerializable();
    }
}