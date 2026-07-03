package com.framework.learning_core.engine; 

import com.framework.learning_core.domain.EnumFlashcardAnswerQuality;
import com.framework.learning_core.domain.BaseFlashcard;
import java.time.LocalDate;

public class SpacingEngine {

    //aplicar o algoritmo SuperMemo 2 (SM2)  que estava em FlashcardService modificando diretamente as propriedades
    //da classe abstrata BaseFlashcard herdada pelas aplicações.
    
    public static void applySM2(BaseFlashcard card, EnumFlashcardAnswerQuality quality) {
        int q = switch (quality) {
            case PERFECT   -> 5;
            case EASY      -> 4;
            case MEDIUM    -> 3;
            case HARD      -> 2;
            case INCORRECT -> 1;
            case BLACKOUT  -> 0;
        };

        double ef = card.getEaseFactor() != null ? card.getEaseFactor() : 2.5;
        int interval = card.getInterval() != null ? card.getInterval() : 0;

        if (q >= 3) {
            interval = switch (interval) {
                case 0 -> 1;
                case 1 -> 6;
                default -> (int) Math.round(interval * ef);
            };
        } else {
            interval = 1;
        }

        ef = ef + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));
        ef = Math.max(1.3, ef); 

        card.setInterval(interval);
        card.setEaseFactor((float) ef);
        card.setLastQuality(quality);
        card.setNextReviewDate(LocalDate.now().plusDays(interval));
    }
}