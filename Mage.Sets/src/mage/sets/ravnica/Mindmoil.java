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
package mage.sets.ravnica;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.game.Game;
import mage.players.Player;

/**
 *
 * @author jeffwadsworth
 */
public class Mindmoil extends CardImpl {

    public Mindmoil(UUID ownerId) {
        super(ownerId, 135, "Mindmoil", Rarity.RARE, new CardType[]{CardType.ENCHANTMENT}, "{4}{R}");
        this.expansionSetCode = "RAV";


        // Whenever you cast a spell, put the cards in your hand on the bottom of your library in any order, then draw that many cards.
        this.addAbility(new SpellCastControllerTriggeredAbility(new MindmoilEffect(), false));
    }

    public Mindmoil(final Mindmoil card) {
        super(card);
    }

    @Override
    public Mindmoil copy() {
        return new Mindmoil(this);
    }
}

class MindmoilEffect extends OneShotEffect {

    public MindmoilEffect() {
        super(Outcome.Neutral);
        staticText = "put the cards in your hand on the bottom of your library in any order, then draw that many cards";
    }

    public MindmoilEffect(final MindmoilEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player you = game.getPlayer(source.getControllerId());
        if (you != null) {
            int count = you.getHand().size();
            you.putCardsOnBottomOfLibrary(you.getHand(), game, source, true);
            you.drawCards(count, game);
        }
        return true;
    }

    @Override
    public MindmoilEffect copy() {
        return new MindmoilEffect(this);
    }
}