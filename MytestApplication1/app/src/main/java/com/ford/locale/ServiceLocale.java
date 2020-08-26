/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.locale;

import com.google.common.base.Optional;

import java.util.Locale;

public enum ServiceLocale {
    CANADA(Locale.CANADA,
            MSDNConstants.US_REGION,
            NgsdnConstants.ENGLISH_US,
            ASDNConstants.ENGLISH,
            ASDNConstants.CANADA,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.CANADA,
            WeatherConstants.ENGLISH_UK),

    CANADA_FRENCH(Locale.CANADA_FRENCH,
            MSDNConstants.CANADA_REGION,
            NgsdnConstants.FRENCH,
            ASDNConstants.FRENCH,
            ASDNConstants.CANADA,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.CANADA,
            WeatherConstants.FRENCH),

    US(Locale.US,
            MSDNConstants.US_REGION,
            NgsdnConstants.ENGLISH_US,
            ASDNConstants.ENGLISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.ENGLISH_US),

    US_SPANISH(LocaleConstants.LOCALE_ES_US,
            MSDNConstants.MEXICO_REGION,
            NgsdnConstants.SPANISH_US,
            ASDNConstants.SPANISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.SPANISH_US),

    MEXICO(LocaleConstants.LOCALE_EN_MX,
            MSDNConstants.US_REGION,
            NgsdnConstants.ENGLISH_US,
            ASDNConstants.ENGLISH,
            ASDNConstants.MEXICO,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.MEXICO,
            WeatherConstants.ENGLISH_US),

    MEXICO_SPANISH(LocaleConstants.LOCALE_ES_MX,
            MSDNConstants.MEXICO_REGION,
            NgsdnConstants.MEXICO_ES,
            ASDNConstants.SPANISH,
            ASDNConstants.MEXICO,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.MEXICO,
            WeatherConstants.MEXICO_ES),

    AP_AUSTRALIA(LocaleConstants.LOCALE_EN_AU,
            MSDNConstants.AUSTRALIA_REGION,
            NgsdnConstants.ENGLISH_AU,
            ASDNConstants.AUSTRALIA,
            ASDNConstants.AUSTRALIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.AUSTRALIA,
            WeatherConstants.ENGLISH_AU),

    AP_NEWZEALAND(LocaleConstants.LOCALE_EN_NZ,
            MSDNConstants.NEWZEALAND_REGION,
            NgsdnConstants.ENGLISH_NZ,
            ASDNConstants.NEWZEALAND,
            ASDNConstants.NEWZEALAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.NEWZEALAND,
            WeatherConstants.ENGLISH_NZ),

    AP_INDIA(LocaleConstants.LOCALE_EN_IN,
            MSDNConstants.INDIA_REGION,
            NgsdnConstants.ENGLISH_IN,
            ASDNConstants.INDIA,
            ASDNConstants.INDIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.INDIA,
            WeatherConstants.ENGLISH_IN),

    EU_ENGLISH(Locale.UK,
            MSDNConstants.UK_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.ENGLISH,
            ASDNConstants.UK,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.UK,
            WeatherConstants.ENGLISH_UK),
    EU_GERMAN(Locale.GERMANY,
            MSDNConstants.GERMANY_REGION,
            NgsdnConstants.GERMAN_DE,
            ASDNConstants.GERMANY,
            ASDNConstants.GERMANY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.GERMANY,
            WeatherConstants.GERMAN_DE),
    EU_FRENCH(Locale.FRANCE,
            MSDNConstants.FRANCE_REGION,
            NgsdnConstants.FRENCH_FR,
            ASDNConstants.FRENCH,
            ASDNConstants.FRENCH,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.FRANCE,
            WeatherConstants.FRENCH_FR),

    EU_ITALY(Locale.ITALY,
            MSDNConstants.ITALY_REGION,
            NgsdnConstants.ITALY_IT,
            ASDNConstants.ITALY,
            ASDNConstants.ITALY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ITALY,
            WeatherConstants.ITALY_IT),

    EU_SPAIN(LocaleConstants.LOCALE_ES,
            MSDNConstants.SPAIN_REGION,
            NgsdnConstants.SPAIN_ES,
            ASDNConstants.SPAIN,
            ASDNConstants.SPAIN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SPAIN,
            WeatherConstants.SPAIN_ES),

    BR_PORTUGUESE(LocaleConstants.LOCALE_BR_PT,
            MSDNConstants.BRAZIL_REGION,
            NgsdnConstants.PORTUGUESE_BR,
            ASDNConstants.ENGLISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.PORTUGUESE_BR),

    BR_ENGLISH(LocaleConstants.LOCALE_BR_EN,
            MSDNConstants.BRAZIL_REGION,
            NgsdnConstants.ENGLISH_US,
            ASDNConstants.ENGLISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.ENGLISH_UK),

    AR_SPANISH(LocaleConstants.LOCALE_AR_ES,
            MSDNConstants.ARGENTINA_REGION,
            NgsdnConstants.SPANISH_AR,
            ASDNConstants.ENGLISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.SPANISH_AR),

    AR_ENGLISH(LocaleConstants.LOCALE_AR_EN,
            MSDNConstants.ARGENTINA_REGION,
            NgsdnConstants.ENGLISH_US,
            ASDNConstants.ENGLISH,
            ASDNConstants.US,
            NgsdnConstants.NA_SCHEDULED_START_FORMAT,
            DealerConstants.US,
            WeatherConstants.ENGLISH_UK),

    EU_AUSTRIA(LocaleConstants.LOCALE_AT,
            MSDNConstants.AUSTRIA_REGION,
            NgsdnConstants.AUSTRIA_DE,
            ASDNConstants.AUSTRIA,
            ASDNConstants.AUSTRIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.AUSTRIA,
            WeatherConstants.AUSTRIA_DE),

    EU_BELGIUM_FR(LocaleConstants.LOCALE_BE_FR,
            MSDNConstants.BELGIUM_REGION,
            NgsdnConstants.BELGIUM_FR,
            ASDNConstants.BELGIUM,
            ASDNConstants.BELGIUM,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.BELGIUM,
            WeatherConstants.BELGIUM_FR),

    EU_BELGIUM_NL(LocaleConstants.LOCALE_BE_NL,
            MSDNConstants.BELGIUM_REGION,
            NgsdnConstants.BELGIUM_NL,
            ASDNConstants.BELGIUM,
            ASDNConstants.BELGIUM,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.BELGIUM,
            WeatherConstants.BELGIUM_NL),

    EU_BELGIUM_DE(LocaleConstants.LOCALE_BE_DE,
            MSDNConstants.BELGIUM_REGION,
            NgsdnConstants.BELGIUM_DE,
            ASDNConstants.BELGIUM,
            ASDNConstants.BELGIUM,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.BELGIUM,
            WeatherConstants.BELGIUM_DE),

    EU_CZECH_REPUBLIC(LocaleConstants.LOCALE_CZ,
            MSDNConstants.CZECH_REPUBLIC_REGION,
            NgsdnConstants.CZECH_REPUBLIC_CZ,
            ASDNConstants.CZECH_REPUBLIC,
            ASDNConstants.CZECH_REPUBLIC,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.CZECH_REPUBLIC,
            WeatherConstants.CZECH_REPUBLIC_CZ),

    EU_DENMARK(LocaleConstants.LOCALE_DK,
            MSDNConstants.DENMARK_REGION,
            NgsdnConstants.DENMARK_DN,
            ASDNConstants.DENMARK,
            ASDNConstants.DENMARK,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.DENMARK,
            WeatherConstants.DENMARK_DN),

    EU_FINLAND(LocaleConstants.LOCALE_FI,
            MSDNConstants.FINLAND_REGION,
            NgsdnConstants.FINLAND_FL,
            ASDNConstants.FINLAND,
            ASDNConstants.FINLAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.FINLAND,
            WeatherConstants.FINLAND_FL),

    EU_GREECE(LocaleConstants.LOCALE_GR,
            MSDNConstants.GREECE_REGION,
            NgsdnConstants.GREECE_EL,
            ASDNConstants.GREECE,
            ASDNConstants.GREECE,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.GREECE,
            WeatherConstants.GREECE_EL),

    EU_HUNGARY(LocaleConstants.LOCALE_HU,
            MSDNConstants.HUNGARY_REGION,
            NgsdnConstants.HUNGARY_HU,
            ASDNConstants.HUNGARY,
            ASDNConstants.HUNGARY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.HUNGARY,
            WeatherConstants.HUNGARY_HU),

    EU_IRELAND(LocaleConstants.LOCALE_IE,
            MSDNConstants.IRELAND_REGION,
            NgsdnConstants.IRELAND_EN,
            ASDNConstants.IRELAND,
            ASDNConstants.IRELAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.IRELAND,
            WeatherConstants.IRELAND_EN),

    EU_NETHERLANDS(LocaleConstants.LOCALE_NL,
            MSDNConstants.NETHERLANDS_REGION,
            NgsdnConstants.NETHERLANDS_NL,
            ASDNConstants.NETHERLANDS,
            ASDNConstants.NETHERLANDS,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.NETHERLANDS,
            WeatherConstants.NETHERLANDS_NL),

    EU_NORWAY(LocaleConstants.LOCALE_NO,
            MSDNConstants.NORWAY_REGION,
            NgsdnConstants.NORWAY_NB,
            ASDNConstants.NORWAY,
            ASDNConstants.NORWAY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.NORWAY,
            WeatherConstants.NORWAY_NB),

    EU_POLAND(LocaleConstants.LOCALE_PL,
            MSDNConstants.POLAND_REGION,
            NgsdnConstants.POLAND_PL,
            ASDNConstants.POLAND,
            ASDNConstants.POLAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.POLAND,
            WeatherConstants.POLAND_PL),

    EU_PORTUGAL(LocaleConstants.LOCALE_PT,
            MSDNConstants.PORTUGAL_REGION,
            NgsdnConstants.PORTUGAL_PT,
            ASDNConstants.PORTUGAL,
            ASDNConstants.PORTUGAL,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.PORTUGAL,
            WeatherConstants.PORTUGAL_PT),

    EU_ROMANIA(LocaleConstants.LOCALE_RO,
            MSDNConstants.ROMANIA_REGION,
            NgsdnConstants.ROMANIA_RO,
            ASDNConstants.ROMANIA,
            ASDNConstants.ROMANIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ROMANIA,
            WeatherConstants.ROMANIA_RO),

    EU_SWEDEN(LocaleConstants.LOCALE_SE,
            MSDNConstants.SWEDEN_REGION,
            NgsdnConstants.SWEDEN_SV,
            ASDNConstants.SWEDEN,
            ASDNConstants.SWEDEN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SWEDEN,
            WeatherConstants.SWEDEN_SV),

    EU_SWITZERLAND_FR(LocaleConstants.LOCALE_CH_FR,
            MSDNConstants.SWITZERLAND_REGION,
            NgsdnConstants.SWITZERLAND_FR,
            ASDNConstants.SWITZERLAND,
            ASDNConstants.SWITZERLAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SWITZERLAND,
            WeatherConstants.SWITZERLAND_FR),

    EU_SWITZERLAND_DE(LocaleConstants.LOCALE_CH_DE,
            MSDNConstants.SWITZERLAND_REGION,
            NgsdnConstants.SWITZERLAND_DE,
            ASDNConstants.SWITZERLAND,
            ASDNConstants.SWITZERLAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SWITZERLAND,
            WeatherConstants.SWITZERLAND_DE),

    EU_SWITZERLAND_IT(LocaleConstants.LOCALE_CH_IT,
            MSDNConstants.SWITZERLAND_REGION,
            NgsdnConstants.SWITZERLAND_IT,
            ASDNConstants.SWITZERLAND,
            ASDNConstants.SWITZERLAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SWITZERLAND,
            WeatherConstants.SWITZERLAND_IT),

    EU_LUXEMBOURG_FR(LocaleConstants.LOCALE_LU_FR,
            MSDNConstants.LUXEMBOURG_REGION,
            NgsdnConstants.LUXEMBOURG_FR,
            ASDNConstants.LUXEMBOURG,
            ASDNConstants.LUXEMBOURG,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LUXEMBOURG,
            WeatherConstants.LUXEMBOURG_FR),

    EU_LUXEMBOURG_DE(LocaleConstants.LOCALE_LU_DE,
            MSDNConstants.LUXEMBOURG_REGION,
            NgsdnConstants.LUXEMBOURG_DE,
            ASDNConstants.LUXEMBOURG,
            ASDNConstants.LUXEMBOURG,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LUXEMBOURG,
            WeatherConstants.LUXEMBOURG_DE),

    EU_MALTA_EN(LocaleConstants.LOCALE_MT,
            MSDNConstants.MALTA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.MALTA,
            ASDNConstants.MALTA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.MALTA,
            WeatherConstants.ENGLISH_UK),

    EU_MALTA_IT(LocaleConstants.LOCALE_MT_IT,
            MSDNConstants.MALTA_REGION,
            NgsdnConstants.ITALY_IT,
            ASDNConstants.MALTA,
            ASDNConstants.MALTA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.MALTA,
            WeatherConstants.ITALY_IT),

    EU_BULGARIA_EN(LocaleConstants.LOCALE_BG,
            MSDNConstants.BULGARIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.BULGARIA,
            ASDNConstants.BULGARIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.BULGARIA,
            WeatherConstants.ENGLISH_UK),

    EU_CROATIA_EN(LocaleConstants.LOCALE_HR,
            MSDNConstants.CROATIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.CROATIA,
            ASDNConstants.CROATIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.CROATIA,
            WeatherConstants.ENGLISH_UK),

    EU_ESTONIA_EN(LocaleConstants.LOCALE_EE,
            MSDNConstants.ESTONIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.ESTONIA,
            ASDNConstants.ESTONIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ESTONIA,
            WeatherConstants.ENGLISH_UK),

    EU_LATVIA_EN(LocaleConstants.LOCALE_LV,
            MSDNConstants.LATVIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.LATVIA,
            ASDNConstants.LATVIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LATVIA,
            WeatherConstants.ENGLISH_UK),

    EU_LITHUANIA_EN(LocaleConstants.LOCALE_LT,
            MSDNConstants.LITHUANIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.LITHUANIA,
            ASDNConstants.LITHUANIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LITHUANIA,
            WeatherConstants.ENGLISH_UK),

    EU_SLOVAKIA_EN(LocaleConstants.LOCALE_SK,
            MSDNConstants.SLOVAKIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.SLOVAKIA,
            ASDNConstants.SLOVAKIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SLOVAKIA,
            WeatherConstants.ENGLISH_UK),

    EU_SLOVENIA_EN(LocaleConstants.LOCALE_SI,
            MSDNConstants.SLOVENIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.SLOVENIA,
            ASDNConstants.SLOVENIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SLOVENIA,
            WeatherConstants.ENGLISH_UK),

    EU_SLOVENIA_SL(LocaleConstants.LOCALE_SL_SI,
            MSDNConstants.SLOVENIA_REGION,
            NgsdnConstants.SLOVENIA_SL,
            ASDNConstants.SLOVENIA,
            ASDNConstants.SLOVENIA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SLOVENIA,
            WeatherConstants.ENGLISH_UK),

    EU_ICELAND_EN(LocaleConstants.LOCALE_IS,
            MSDNConstants.ICELAND_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.ICELAND,
            ASDNConstants.ICELAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ICELAND,
            WeatherConstants.ENGLISH_UK),

    EU_ICELAND_DA(LocaleConstants.LOCALE_IS_DA,
            MSDNConstants.ICELAND_REGION,
            NgsdnConstants.DENMARK_DN,
            ASDNConstants.ICELAND,
            ASDNConstants.ICELAND,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ICELAND,
            WeatherConstants.DENMARK_DN),

    EU_CYPRUS_EN(LocaleConstants.LOCALE_CY,
            MSDNConstants.CYPRUS_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.CYPRUS,
            ASDNConstants.CYPRUS,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.CYPRUS,
            WeatherConstants.ENGLISH_UK),

    EU_CYPRUS_EL(LocaleConstants.LOCALE_CY_EL,
            MSDNConstants.CYPRUS_REGION,
            NgsdnConstants.GREECE_EL,
            ASDNConstants.CYPRUS,
            ASDNConstants.CYPRUS,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.CYPRUS,
            WeatherConstants.CYPRUS_EL),

    EU_LIECHTENSTEIN_DE(LocaleConstants.LOCALE_LI,
            MSDNConstants.LIECHTENSTEIN_REGION,
            NgsdnConstants.GERMAN_DE,
            ASDNConstants.LIECHTENSTEIN,
            ASDNConstants.LIECHTENSTEIN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LIECHTENSTEIN,
            WeatherConstants.GERMAN_DE),

    EU_LIECHTENSTEIN_FR(LocaleConstants.LOCALE_LI_FR,
            MSDNConstants.LIECHTENSTEIN_REGION,
            NgsdnConstants.FRENCH_FR,
            ASDNConstants.LIECHTENSTEIN,
            ASDNConstants.LIECHTENSTEIN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LIECHTENSTEIN,
            WeatherConstants.FRENCH_FR),

    EU_LIECHTENSTEIN_IT(LocaleConstants.LOCALE_LI_IT,
            MSDNConstants.LIECHTENSTEIN_REGION,
            NgsdnConstants.ITALY_IT,
            ASDNConstants.LIECHTENSTEIN,
            ASDNConstants.LIECHTENSTEIN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LIECHTENSTEIN,
            WeatherConstants.ITALY_IT),

    EU_SAN_MARINO_IT(LocaleConstants.LOCALE_SM,
            MSDNConstants.SAN_MARINO_REGION,
            NgsdnConstants.GERMAN_DE,
            ASDNConstants.SAN_MARINO,
            ASDNConstants.SAN_MARINO,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SAN_MARINO,
            WeatherConstants.ITALY_IT),

    EU_MONACO_FR(LocaleConstants.LOCALE_MC,
            MSDNConstants.MONACO_REGION,
            NgsdnConstants.FRENCH_FR,
            ASDNConstants.MONACO,
            ASDNConstants.MONACO,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.MONACO,
            WeatherConstants.MONACO_FR),

    EU_ANDORRA_ES(LocaleConstants.LOCALE_AD,
            MSDNConstants.ANDORRA_REGION,
            NgsdnConstants.SPAIN_ES,
            ASDNConstants.ANDORRA,
            ASDNConstants.ANDORRA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ANDORRA,
            WeatherConstants.ANDORRA_ES),

    EU_GIBRALTAR_EN(LocaleConstants.LOCALE_GI,
            MSDNConstants.GIBRALTAR_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.GIBRALTAR,
            ASDNConstants.GIBRALTAR,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.GIBRALTAR,
            WeatherConstants.ENGLISH_UK),

    EU_GUERNSEY_EN(LocaleConstants.LOCALE_GG,
            MSDNConstants.GUERNSEY_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.GUERNSEY,
            ASDNConstants.GUERNSEY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.GUERNSEY,
            WeatherConstants.ENGLISH_UK),

    EU_ISLE_OF_MAN_EN(LocaleConstants.LOCALE_IM,
            MSDNConstants.ISLE_OF_MAN_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.ISLE_OF_MAN,
            ASDNConstants.ISLE_OF_MAN,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ISLE_OF_MAN,
            WeatherConstants.ENGLISH_UK),

    EU_JERSEY_EN(LocaleConstants.LOCALE_JE,
            MSDNConstants.JERSEY_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.JERSEY,
            ASDNConstants.JERSEY,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.JERSEY,
            WeatherConstants.ENGLISH_UK),

    EU_FAEROE_ISLANDS_DA(LocaleConstants.LOCALE_FO,
            MSDNConstants.FAEROE_ISLANDS_REGION,
            NgsdnConstants.DENMARK_DN,
            ASDNConstants.FAEROE_ISLANDS,
            ASDNConstants.FAEROE_ISLANDS,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.FAEROE_ISLANDS,
            WeatherConstants.DENMARK_DN),

    ME_BH_EN(LocaleConstants.LOCALE_BH_EN,
            MSDNConstants.BAHRAIN_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.BAHRAIN,
            ASDNConstants.BAHRAIN,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.BAHRAIN,
            WeatherConstants.ENGLISH_UK),

    ME_BH_AR(LocaleConstants.LOCALE_BH_AR,
            MSDNConstants.BAHRAIN_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.BAHRAIN,
            ASDNConstants.BAHRAIN,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.BAHRAIN,
            WeatherConstants.ARABIC_ME),

    ME_KU_EN(LocaleConstants.LOCALE_KW_EN,
            MSDNConstants.KUWAIT_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.KUWAIT,
            ASDNConstants.KUWAIT,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.KUWAIT,
            WeatherConstants.ENGLISH_UK),

    ME_KU_AR(LocaleConstants.LOCALE_KW_AR,
            MSDNConstants.KUWAIT_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.KUWAIT,
            ASDNConstants.KUWAIT,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.KUWAIT,
            WeatherConstants.ARABIC_ME),

    ME_OM_EN(LocaleConstants.LOCALE_OM_EN,
            MSDNConstants.OMAN_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.OMAN,
            ASDNConstants.OMAN,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.OMAN,
            WeatherConstants.ENGLISH_UK),

    ME_OM_AR(LocaleConstants.LOCALE_OM_AR,
            MSDNConstants.OMAN_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.OMAN,
            ASDNConstants.OMAN,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.OMAN,
            WeatherConstants.ARABIC_ME),

    ME_QA_EN(LocaleConstants.LOCALE_QA_EN,
            MSDNConstants.QATAR_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.QATAR,
            ASDNConstants.QATAR,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.QATAR,
            WeatherConstants.ENGLISH_UK),

    ME_QA_AR(LocaleConstants.LOCALE_QA_AR,
            MSDNConstants.QATAR_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.QATAR,
            ASDNConstants.QATAR,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.QATAR,
            WeatherConstants.ARABIC_ME),

    ME_SA_EN(LocaleConstants.LOCALE_SA_EN,
            MSDNConstants.SAUDI_ARABIA_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.SAUDI_ARABIA,
            ASDNConstants.SAUDI_ARABIA,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.SAUDI_ARABIA,
            WeatherConstants.ENGLISH_UK),

    ME_SA_AR(LocaleConstants.LOCALE_SA_AR,
            MSDNConstants.SAUDI_ARABIA_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.SAUDI_ARABIA,
            ASDNConstants.SAUDI_ARABIA,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.SAUDI_ARABIA,
            WeatherConstants.ARABIC_ME),

    ME_UAE_EN(LocaleConstants.LOCALE_UAE_EN,
            MSDNConstants.UAE_REGION,
            NgsdnConstants.ENGLISH_UK,
            ASDNConstants.UAE,
            ASDNConstants.UAE,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.UAE,
            WeatherConstants.ENGLISH_UK),

    ME_UAE_AR(LocaleConstants.LOCALE_UAE_AR,
            MSDNConstants.UAE_REGION,
            NgsdnConstants.ARABIC_ME,
            ASDNConstants.UAE,
            ASDNConstants.UAE,
            NgsdnConstants.ME_SCHEDULED_START_FORMAT,
            DealerConstants.UAE,
            WeatherConstants.ARABIC_ME),

    IM_SOUTH_AFRICA(LocaleConstants.LOCALE_EN_ZA,
            MSDNConstants.ZA_REGION,
            NgsdnConstants.ENGLISH_ZA,
            ASDNConstants.ZAF,
            ASDNConstants.ZAF,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.ZAF,
            WeatherConstants.ENGLISH_ZA),

    IM_NAMIBIA(LocaleConstants.LOCALE_EN_NA,
            MSDNConstants.NA_REGION,
            NgsdnConstants.ENGLISH_ZA,
            ASDNConstants.NAM,
            ASDNConstants.NAM,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.NAM,
            WeatherConstants.ENGLISH_ZA),

    IM_BOTSWANA(LocaleConstants.LOCALE_EN_BW,
            MSDNConstants.BW_REGION,
            NgsdnConstants.ENGLISH_ZA,
            ASDNConstants.BWA,
            ASDNConstants.BWA,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.BWA,
            WeatherConstants.ENGLISH_ZA),

    IM_SWAZILAND(LocaleConstants.LOCALE_EN_SW,
            MSDNConstants.SZ_REGION,
            NgsdnConstants.ENGLISH_ZA,
            ASDNConstants.SWZ,
            ASDNConstants.SWZ,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.SWZ,
            WeatherConstants.ENGLISH_ZA),

    IM_LESOTHO(LocaleConstants.LOCALE_EN_LS,
            MSDNConstants.LS_REGION,
            NgsdnConstants.ENGLISH_ZA,
            ASDNConstants.LSO,
            ASDNConstants.LSO,
            NgsdnConstants.EU_SCHEDULED_START_FORMAT,
            DealerConstants.LSO,
            WeatherConstants.ENGLISH_ZA);

    private final Locale regionLocale;
    private final String ngsdnLanguage;
    private final String MSDNRegion;
    private final String ASDNLanguage;
    private final String ASDNCountry;
    private final String ScheduledStartDateFormat;
    private final String dealerSearchDatabase;
    private final String weatherLanguage;

    private Optional<Locale> deviceLocale = Optional.absent();

    ServiceLocale(Locale regionLocale, String msdnRegion, String ngsdnLanguage, String asdnLanguage, String asdnCountry, String scheduledStartDateFormat, String dealerSearchDatabase, String weatherLanguage) {
        this.regionLocale = regionLocale;
        this.ngsdnLanguage = ngsdnLanguage;
        this.dealerSearchDatabase = dealerSearchDatabase;
        this.weatherLanguage = weatherLanguage;
        MSDNRegion = msdnRegion;
        ASDNLanguage = asdnLanguage;
        ASDNCountry = asdnCountry;
        ScheduledStartDateFormat = scheduledStartDateFormat;
    }

    public static ServiceLocale findByAndroidLocale(Locale locale) {
        for (ServiceLocale serviceLocale : ServiceLocale.values()) {
            if (serviceLocale.regionLocale.equals(locale)) {
                return serviceLocale;
            }
        }

        return null;
    }

    public static String getISO3CountryCode(final String countryName) {
        for (ServiceLocale serviceLocale : ServiceLocale.values()) {
            if (serviceLocale.regionLocale.getDisplayCountry(Locale.US).equalsIgnoreCase(countryName)) {
                return serviceLocale.getISO3Country();
            }
        }
        return null;
    }

    public void setDeviceLocale(Locale deviceLocale) {
        this.deviceLocale = Optional.of(deviceLocale);
    }

    public String getMSDNLanguage() {
        return regionLocale.getLanguage().toUpperCase();
    }

    public String getMSDNIso3Language() {
        return regionLocale.getISO3Language();
    }

    public String getMSDNRegion() {
        return MSDNRegion;
    }

    public String getISO2Country() {
        return regionLocale.getCountry();
    }

    public String getISO3Country() {
        return regionLocale.getISO3Country();
    }

    public String getOsbISO3Country() {
        return getISO3Country().equals("ROU") ? "ROM" : getISO3Country();
    }

    public String getNgsdnLanguage() {
        if (deviceLocale.isPresent()) {
            return deviceLocale.get().getLanguage() + "-" + deviceLocale.get().getCountry().toLowerCase();
        }
        return ngsdnLanguage;
    }

    public String getCsdnLanguage() {
        if (deviceLocale.isPresent()) {
            return deviceLocale.get().getLanguage() + "-" + deviceLocale.get().getCountry().toUpperCase();
        }
        return ngsdnLanguage;
    }

    public String getASDNLanguage() {
        return ASDNLanguage;
    }

    public String getASDNCountry() {
        return ASDNCountry;
    }

    public String getScheduledStartDateFormat() {
        return ScheduledStartDateFormat;
    }

    public String getDealerSearchCountryCode() {
        return dealerSearchDatabase;
    }

    public String getWeatherLanguage() {
        return weatherLanguage;
    }

    //================================================================================
    // Classes
    //================================================================================

    private static class MSDNConstants {
        public static final String UK_REGION = "UK";
        public static final String GERMANY_REGION = "DE";
        public static final String FRANCE_REGION = "FR";
        public static final String ITALY_REGION = "IT";
        public static final String SPAIN_REGION = "ES";
        public static final String BRAZIL_REGION = "BR";
        public static final String ARGENTINA_REGION = "AR";
        private static final String US_REGION = "US";
        private static final String CANADA_REGION = "CA";
        private static final String AUSTRIA_REGION = "AT";
        private static final String BELGIUM_REGION = "BE";
        private static final String CZECH_REPUBLIC_REGION = "CZ";
        private static final String DENMARK_REGION = "DK";
        private static final String FINLAND_REGION = "FI";
        private static final String GREECE_REGION = "GR";
        private static final String HUNGARY_REGION = "HU";
        private static final String IRELAND_REGION = "IE";
        private static final String NETHERLANDS_REGION = "NL";
        private static final String NORWAY_REGION = "NO";
        private static final String POLAND_REGION = "PL";
        private static final String PORTUGAL_REGION = "PT";
        private static final String ROMANIA_REGION = "RO";
        private static final String SWEDEN_REGION = "SE";
        private static final String SWITZERLAND_REGION = "CH";
        private static final String LUXEMBOURG_REGION = "LU";
        private static final String MEXICO_REGION = "MX";
        private static final String AUSTRALIA_REGION = "AU";
        private static final String NEWZEALAND_REGION = "NZ";
        private static final String INDIA_REGION = "IN";
        private static final String MALTA_REGION = "MT";
        private static final String BULGARIA_REGION = "BG";
        private static final String CROATIA_REGION = "HR";
        private static final String ESTONIA_REGION = "EE";
        private static final String LATVIA_REGION = "LV";
        private static final String LITHUANIA_REGION = "LT";
        private static final String SLOVAKIA_REGION = "SK";
        private static final String SLOVENIA_REGION = "SI";
        private static final String ICELAND_REGION = "IS";
        private static final String CYPRUS_REGION = "CY";
        private static final String LIECHTENSTEIN_REGION = "LI";
        private static final String SAN_MARINO_REGION = "SM";
        private static final String MONACO_REGION = "MC";
        private static final String ANDORRA_REGION = "AD";
        private static final String GIBRALTAR_REGION = "GI";
        private static final String GUERNSEY_REGION = "GG";
        private static final String ISLE_OF_MAN_REGION = "IM";
        private static final String JERSEY_REGION = "JE";
        private static final String FAEROE_ISLANDS_REGION = "FO";
        private static final String BAHRAIN_REGION = "BH";
        private static final String KUWAIT_REGION = "KW";
        private static final String OMAN_REGION = "OM";
        private static final String QATAR_REGION = "QA";
        private static final String SAUDI_ARABIA_REGION = "SA";
        private static final String UAE_REGION = "AE";
        private static final String ZA_REGION = "ZA";
        private static final String NA_REGION = "NA";
        private static final String BW_REGION = "BW";
        private static final String SZ_REGION = "SZ";
        private static final String LS_REGION = "LS";
    }

    private static class NgsdnConstants {
        public static final String ENGLISH_UK = "en-gb";
        public static final String GERMAN_DE = "de-de";
        public static final String FRENCH_FR = "fr-fr";
        public static final String ITALY_IT = "it-it";
        public static final String SPAIN_ES = "es-es";
        public static final String PORTUGUESE_BR = "pt-br";
        public static final String SPANISH_AR = "es-ar";
        public static final String EU_SCHEDULED_START_FORMAT = "M-dd-yyyy h:mm:ss a";
        private static final String ENGLISH_US = "en-us";
        private static final String FRENCH = "fr-ca";
        private static final String AUSTRIA_DE = "de-at";
        private static final String BELGIUM_FR = "fr-be";
        private static final String BELGIUM_NL = "nl-be";
        private static final String BELGIUM_DE = "de-be";
        private static final String CZECH_REPUBLIC_CZ = "cs-cz";
        private static final String DENMARK_DN = "da-DK";
        private static final String FINLAND_FL = "fi-FI";
        private static final String GREECE_EL = "el-gr";
        private static final String HUNGARY_HU = "hu-hu";
        private static final String IRELAND_EN = "en-ie";
        private static final String NETHERLANDS_NL = "nl-nl";
        private static final String NORWAY_NB = "nb-no";
        private static final String POLAND_PL = "pl-pl";
        private static final String PORTUGAL_PT = "pt-pt";
        private static final String ROMANIA_RO = "ro-ro";
        private static final String SWEDEN_SV = "sv-se";
        private static final String SLOVENIA_SL = "sl-si";
        private static final String SWITZERLAND_FR = "fr-ch";
        private static final String SWITZERLAND_DE = "de-ch";
        private static final String SWITZERLAND_IT = "it-ch";
        private static final String LUXEMBOURG_FR = "fr-lu";
        private static final String LUXEMBOURG_DE = "de-lu";
        private static final String MEXICO_ES = "es-mx";
        private static final String ENGLISH_AU = "en-au";
        private static final String ENGLISH_NZ = "en-nz";
        private static final String ENGLISH_IN = "en-in";
        private static final String SPANISH_US = "es-us";
        private static final String ARABIC_ME = "ar-ae";
        private static final String NA_SCHEDULED_START_FORMAT = "M-dd-yyyy h:mm:ss a";
        private static final String AP_SCHEDULED_START_FORMAT = "M-dd-yyyy a h:mm:ss";
        private static final String ME_SCHEDULED_START_FORMAT = "M-dd-yyyy a h:mm:ss";
        private static final String ENGLISH_ZA = "en-za";
    }

    private static class WeatherConstants {
        private static final String ENGLISH_US = "en-us";
        private static final String FRENCH = "fr-ca";
        private static final String ENGLISH_UK = "en-gb";
        private static final String GERMAN_DE = "de-de";
        private static final String FRENCH_FR = "fr-fr";
        private static final String ITALY_IT = "it-it";
        private static final String SPAIN_ES = "es-es";
        private static final String PORTUGUESE_BR = "pt-br";
        private static final String SPANISH_AR = "es-ar";
        private static final String AUSTRIA_DE = "de-at";
        private static final String BELGIUM_FR = "fr-be";
        private static final String BELGIUM_NL = "nl-be";
        private static final String BELGIUM_DE = "de-be";
        private static final String CZECH_REPUBLIC_CZ = "cs-cz";
        private static final String DENMARK_DN = "da-DK";
        private static final String FINLAND_FL = "fi-FI";
        private static final String GREECE_EL = "el-gr";
        private static final String HUNGARY_HU = "hu-hu";
        private static final String IRELAND_EN = "en-ie";
        private static final String NETHERLANDS_NL = "nl-nl";
        private static final String NORWAY_NB = "nb-no";
        private static final String POLAND_PL = "pl-pl";
        private static final String PORTUGAL_PT = "pt-pt";
        private static final String ROMANIA_RO = "ro-ro";
        private static final String SWEDEN_SV = "sv-se";
        private static final String SWITZERLAND_FR = "fr-ch";
        private static final String SWITZERLAND_DE = "de-ch";
        private static final String SWITZERLAND_IT = "it-ch";
        private static final String LUXEMBOURG_FR = "fr-lu";
        private static final String LUXEMBOURG_DE = "de-lu";
        private static final String MEXICO_ES = "es-mx";
        private static final String ENGLISH_AU = "en-au";
        private static final String ENGLISH_NZ = "en-nz";
        private static final String ENGLISH_IN = "en-in";
        private static final String SPANISH_US = "es-us";
        private static final String CYPRUS_EL = "el-el";
        private static final String MONACO_FR = "fr-mc";
        private static final String ANDORRA_ES = "es-ad";
        private static final String ARABIC_ME = "ar-ae";
        private static final String ENGLISH_ZA = "en-za";
    }

    private static class ASDNConstants {
        public static final String UK = "W_COUNTRY_UK";
        public static final String GERMANY = "W_COUNTRY_GERMANY";
        public static final String ITALY = "W_COUNTRY_ITALY";
        public static final String SPAIN = "W_COUNTRY_SPAIN";
        private static final String ENGLISH = "W_LANG_EN";
        private static final String FRENCH = "W_LANG_FR";
        private static final String SPANISH = "W_LANG_ES";
        private static final String US = "W_COUNTRY_US";
        private static final String CANADA = "W_COUNTRY_CA";
        private static final String AUSTRIA = "W_COUNTRY_AUSTRIA";
        private static final String BELGIUM = "W_COUNTRY_BELGIUM";
        private static final String CZECH_REPUBLIC = "W_COUNTRY_CZECH_REPUBLIC";
        private static final String DENMARK = "W_COUNTRY_DENMARK";
        private static final String FINLAND = "W_COUNTRY_FINLAND";
        private static final String GREECE = "W_COUNTRY_GREECE";
        private static final String HUNGARY = "W_COUNTRY_HUNGARY";
        private static final String IRELAND = "W_COUNTRY_IRELAND";
        private static final String NETHERLANDS = "W_COUNTRY_NETHERLANDS";
        private static final String NORWAY = "W_COUNTRY_NORWAY";
        private static final String POLAND = "W_COUNTRY_POLAND";
        private static final String PORTUGAL = "W_COUNTRY_PORTUGAL";
        private static final String ROMANIA = "W_COUNTRY_ROMANIA";
        private static final String SWEDEN = "W_COUNTRY_SWEDEN";
        private static final String SWITZERLAND = "W_COUNTRY_SWITZERLAND";
        private static final String LUXEMBOURG = "W_COUNTRY_LUXEMBOURG";
        private static final String MEXICO = "W_COUNTRY_MEXICO";
        private static final String AUSTRALIA = "W_COUNTRY_AUSTRALIA";
        private static final String NEWZEALAND = "W_COUNTRY_NEWZEALAND";
        private static final String MALTA = "W_COUNTRY_MALTA";
        private static final String BULGARIA = "W_COUNTRY_BULGARIA";
        private static final String CROATIA = "W_COUNTRY_CROATIA";
        private static final String ESTONIA = "W_COUNTRY_ESTONIA";
        private static final String LATVIA = "W_COUNTRY_LATVIA";
        private static final String LITHUANIA = "W_COUNTRY_LITHUANIA";
        private static final String SLOVAKIA = "W_COUNTRY_SLOVAKIA";
        private static final String SLOVENIA = "W_COUNTRY_SLOVENIA";
        private static final String ICELAND = "W_COUNTRY_ICELAND";
        private static final String INDIA = "W_COUNTRY_INDIA";
        private static final String CYPRUS = "W_COUNTRY_CYPRUS";
        private static final String LIECHTENSTEIN = "W_COUNTRY_LIECHTENSTEIN";
        private static final String SAN_MARINO = "W_COUNTRY_SAN_MARINO";
        private static final String MONACO = "W_COUNTRY_MONACO";
        private static final String ANDORRA = "W_COUNTRY_ANDORRA";
        private static final String GIBRALTAR = "W_COUNTRY_GIBRALTAR";
        private static final String GUERNSEY = "W_COUNTRY_GUERNSEY";
        private static final String ISLE_OF_MAN = "W_COUNTRY_ISLE_OF_MAN";
        private static final String JERSEY = "W_COUNTRY_JERSEY";
        private static final String FAEROE_ISLANDS = "W_COUNTRY_FAEROE_ISLANDS";
        private static final String BAHRAIN = "W_COUNTRY_BAHRAIN";
        private static final String KUWAIT = "KW_COUNTRY_KUWAIT";
        private static final String OMAN = "W_COUNTRY_OMAN";
        private static final String QATAR = "W_COUNTRY_QATAR";
        private static final String SAUDI_ARABIA = "W_COUNTRY_SAUDI_ARABIA";
        private static final String UAE = "W_COUNTRY_UAE";
        private static final String ZAF = "W_COUNTRY_ZAF";
        private static final String NAM = "W_COUNTRY_NAM";
        private static final String BWA = "W_COUNTRY_BWA";
        private static final String SWZ = "W_COUNTRY_SWZ";
        private static final String LSO = "W_COUNTRY_LSO";

    }

    private static class DealerConstants {
        private static final String US = "US";
        private static final String CANADA = "CAN";
        private static final String UK = "UK";
        private static final String GERMANY = "DE";
        private static final String FRANCE = "FR";
        private static final String ITALY = "IT";
        private static final String SPAIN = "ES";
        private static final String AUSTRIA = "AT";
        private static final String BELGIUM = "BE";
        private static final String CZECH_REPUBLIC = "CZ";
        private static final String DENMARK = "DK";
        private static final String FINLAND = "FI";
        private static final String GREECE = "GR";
        private static final String HUNGARY = "HU";
        private static final String IRELAND = "IE";
        private static final String NETHERLANDS = "NL";
        private static final String NORWAY = "NO";
        private static final String POLAND = "PL";
        private static final String PORTUGAL = "PT";
        private static final String ROMANIA = "RO";
        private static final String SWEDEN = "SE";
        private static final String SWITZERLAND = "CH";
        private static final String LUXEMBOURG = "LU";
        private static final String MEXICO = "MX";
        private static final String AUSTRALIA = "AU";
        private static final String NEWZEALAND = "NZ";
        private static final String MALTA = "MT";
        private static final String BULGARIA = "BG";
        private static final String CROATIA = "HR";
        private static final String ESTONIA = "EE";
        private static final String LATVIA = "LV";
        private static final String LITHUANIA = "LT";
        private static final String SLOVAKIA = "SK";
        private static final String SLOVENIA = "SI";
        private static final String ICELAND = "IS";
        private static final String INDIA = "IN";
        private static final String CYPRUS = "CY";
        private static final String LIECHTENSTEIN = "LI";
        private static final String SAN_MARINO = "SM";
        private static final String MONACO = "MC";
        private static final String ANDORRA = "AD";
        private static final String GIBRALTAR = "GI";
        private static final String GUERNSEY = "GG";
        private static final String ISLE_OF_MAN = "IM";
        private static final String JERSEY = "JE";
        private static final String FAEROE_ISLANDS = "FO";
        private static final String BAHRAIN = "BH";
        private static final String KUWAIT = "KW";
        private static final String OMAN = "OM";
        private static final String QATAR = "QA";
        private static final String SAUDI_ARABIA = "SA";
        private static final String UAE = "AE";
        private static final String ZAF = "ZA";
        private static final String NAM = "NA";
        private static final String BWA = "BW";
        private static final String LSO = "LS";
        private static final String SWZ = "SZ";
    }

    public static class LocaleConstants {
        public static final Locale LOCALE_ES = new Locale("es", "ES");
        public static final Locale LOCALE_BR_EN = new Locale("en", "BR");
        public static final Locale LOCALE_BR_PT = new Locale("pt", "BR");
        public static final Locale LOCALE_AR_ES = new Locale("es", "AR");
        public static final Locale LOCALE_AR_EN = new Locale("en", "AR");
        public static final Locale LOCALE_AT = new Locale("de", "AT");
        public static final Locale LOCALE_BE = new Locale("en", "BE");
        public static final Locale LOCALE_BE_FR = new Locale("fr", "BE");
        public static final Locale LOCALE_BE_NL = new Locale("nl", "BE");
        public static final Locale LOCALE_BE_DE = new Locale("de", "BE");
        public static final Locale LOCALE_CZ = new Locale("cs", "CZ");
        public static final Locale LOCALE_DK = new Locale("da", "dk");
        public static final Locale LOCALE_FI = new Locale("fi", "FI");
        public static final Locale LOCALE_GR = new Locale("el", "GR");
        public static final Locale LOCALE_HU = new Locale("hu", "HU");
        public static final Locale LOCALE_IE = new Locale("en", "IE");
        public static final Locale LOCALE_NL = new Locale("nl", "NL");
        public static final Locale LOCALE_NO = new Locale("no", "NO");
        public static final Locale LOCALE_PL = new Locale("pl", "PL");
        public static final Locale LOCALE_PT = new Locale("pt", "PT");
        public static final Locale LOCALE_RO = new Locale("ro", "RO");
        public static final Locale LOCALE_SE = new Locale("sv", "SE");
        public static final Locale LOCALE_CH = new Locale("en", "CH");
        public static final Locale LOCALE_CH_FR = new Locale("fr", "CH");
        public static final Locale LOCALE_CH_DE = new Locale("de", "CH");
        public static final Locale LOCALE_CH_IT = new Locale("it", "CH");
        public static final Locale LOCALE_LU = new Locale("en", "LU");
        public static final Locale LOCALE_LU_FR = new Locale("fr", "LU");
        public static final Locale LOCALE_LU_DE = new Locale("de", "LU");
        public static final Locale LOCALE_ES_MX = new Locale("es", "MX");
        public static final Locale LOCALE_EN_MX = new Locale("en", "MX");
        public static final Locale LOCALE_EN_AU = new Locale("en", "AU");
        public static final Locale LOCALE_EN_NZ = new Locale("en", "NZ");
        public static final Locale LOCALE_EN_IN = new Locale("en", "IN");
        public static final Locale LOCALE_ES_US = new Locale("es", "US");
        public static final Locale LOCALE_MT = new Locale("en", "MT");
        public static final Locale LOCALE_MT_IT = new Locale("it", "MT");
        public static final Locale LOCALE_BG = new Locale("en", "BG");
        public static final Locale LOCALE_BG_BG = new Locale("bg", "BG");
        public static final Locale LOCALE_HR = new Locale("en", "HR");
        public static final Locale LOCALE_EE = new Locale("en", "EE");
        public static final Locale LOCALE_LV = new Locale("en", "LV");
        public static final Locale LOCALE_LT = new Locale("en", "LT");
        public static final Locale LOCALE_SK = new Locale("en", "SK");
        public static final Locale LOCALE_SK_SK = new Locale("sk", "SK");
        public static final Locale LOCALE_SI = new Locale("en", "SI");
        public static final Locale LOCALE_SL_SI = new Locale("sl", "SI");
        public static final Locale LOCALE_IS = new Locale("en", "IS");
        public static final Locale LOCALE_IS_DA = new Locale("da", "IS");
        public static final Locale LOCALE_CY = new Locale("en", "CY");
        public static final Locale LOCALE_CY_EL = new Locale("el", "CY");
        public static final Locale LOCALE_LI = new Locale("de", "LI");
        public static final Locale LOCALE_LI_FR = new Locale("fr", "LI");
        public static final Locale LOCALE_LI_IT = new Locale("it", "LI");
        public static final Locale LOCALE_SM = new Locale("it", "SM");
        public static final Locale LOCALE_MC = new Locale("fr", "MC");
        public static final Locale LOCALE_AD = new Locale("es", "AD");
        public static final Locale LOCALE_GI = new Locale("en", "GI");
        public static final Locale LOCALE_GG = new Locale("en", "GG");
        public static final Locale LOCALE_IM = new Locale("en", "IM");
        public static final Locale LOCALE_JE = new Locale("en", "JE");
        public static final Locale LOCALE_FO = new Locale("da", "FO");
        public static final Locale LOCALE_BH_AR = new Locale("ar", "BH");
        public static final Locale LOCALE_BH_EN = new Locale("en", "BH");
        public static final Locale LOCALE_KW_AR = new Locale("ar", "KW");
        public static final Locale LOCALE_KW_EN = new Locale("en", "KW");
        public static final Locale LOCALE_OM_AR = new Locale("ar", "OM");
        public static final Locale LOCALE_OM_EN = new Locale("en", "OM");
        public static final Locale LOCALE_QA_AR = new Locale("ar", "QA");
        public static final Locale LOCALE_QA_EN = new Locale("en", "QA");
        public static final Locale LOCALE_SA_AR = new Locale("ar", "SA");
        public static final Locale LOCALE_SA_EN = new Locale("en", "SA");
        public static final Locale LOCALE_UAE_AR = new Locale("ar", "AE");
        public static final Locale LOCALE_UAE_EN = new Locale("en", "AE");
        public static final Locale LOCALE_EN_ZA = new Locale("en", "ZA");
        public static final Locale LOCALE_EN_NA = new Locale("en", "NA");
        public static final Locale LOCALE_EN_BW = new Locale("en", "BW");
        public static final Locale LOCALE_EN_SW = new Locale("en", "SZ");
        public static final Locale LOCALE_EN_LS = new Locale("en", "LS");
    }
}