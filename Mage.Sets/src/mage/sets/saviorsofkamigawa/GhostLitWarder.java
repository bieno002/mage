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
package mage.sets.saviorsofkamigawa;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostImpl;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.CounterTargetEffect;
import mage.abilities.effects.common.CounterUnlessPaysEffect;
import mage.abilities.keyword.ChannelAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.target.TargetSpell;

/**
 *
 * @author Plopman
 */
public class GhostLitWarder extends CardImpl {

    public GhostLitWarder(UUID ownerId) {
        super(ownerId, 39, "Ghost-Lit Warder", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{1}{U}");
        this.expansionSetCode = "SOK";
        this.subtype.add("Spirit");

        this.power = new MageInt(1);
        this.toughness = new MageInt(1);

        // {3}{U}, {tap}: Counter target spell unless its controller pays {2}.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new CounterUnlessPaysEffect(new ManaCostsImpl("{2}")), new ManaCostsImpl("{3}{U}"));
        ability.addCost(new TapSourceCost());
        ability.addTarget(new TargetSpell());
        this.addAbility(ability);
        // Channel - {3}{U}, Discard Ghost-Lit Warder: Counter target spell unless its controller pays {4}.
        Ability ability2 = new ChannelAbility("{3}{U}", new CounterUnlessPaysEffect(new ManaCostsImpl("{4}")));
        ability2.addTarget(new TargetSpell());
        this.addAbility(ability2);
        
    }

    public GhostLitWarder(final GhostLitWarder card) {
        super(card);
    }

    @Override
    public GhostLitWarder copy() {
        return new GhostLitWarder(this);
    }
}
