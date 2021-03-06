/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.abilities.dynamicvalue.common;

import mage.abilities.Ability;
import mage.abilities.costs.Cost;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.effects.Effect;
import mage.cards.Card;
import mage.cards.SplitCard;
import mage.game.Game;

/**
 * Calculates the converted mana costs of a card that was exiled from hand as
 * cost. If no card was exiled the getManaCostsToPay().getX() will be used as
 * value.
 *
 *
 * @author LevelX2
 */
public class ExileFromHandCostCardConvertedMana implements DynamicValue {

    @Override
    public int calculate(Game game, Ability sourceAbility, Effect effect) {
        for (Cost cost : sourceAbility.getCosts()) {
            if (cost.isPaid() && cost instanceof ExileFromHandCost) {
                int xValue = 0;
                for (Card card : ((ExileFromHandCost) cost).getCards()) {
                    xValue += card.getManaCost().convertedManaCost();
                }
                return xValue;
            }
        }
        return sourceAbility.getManaCostsToPay().getX();
    }

    /**
     * This method does only work to compare the cmc for one (or the first card)
     * exiled as a cost
     *
     * @param game
     * @param sourceAbility
     * @param effect
     * @param amount cmc to compare against
     * @return
     */
    public boolean isConvertedManaCostEqual(Game game, Ability sourceAbility, Effect effect, int amount) {
        for (Cost cost : sourceAbility.getCosts()) {
            if (cost.isPaid() && cost instanceof ExileFromHandCost) {
                for (Card card : ((ExileFromHandCost) cost).getCards()) {
                    if (card instanceof SplitCard) {
                        if (((SplitCard) card).getLeftHalfCard().getManaCost().convertedManaCost() == amount) {
                            return true;
                        }
                        if (((SplitCard) card).getRightHalfCard().getManaCost().convertedManaCost() == amount) {
                            return true;
                        }
                    } else if (card.getManaCost().convertedManaCost() == amount) {
                        return true;
                    }
                    return false;
                }

            }
        }
        return false;
    }

    @Override
    public ExileFromHandCostCardConvertedMana copy() {
        return new ExileFromHandCostCardConvertedMana();
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public String getMessage() {
        return "";
    }
}
