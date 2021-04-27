package org.eno.bot.widgets;

import elemental2.dom.DomGlobal;
import org.eno.bot.widgets.effects.Effect;
import org.eno.bot.widgets.effects.ExecuteAsapEffect;
import org.eno.bot.widgets.effects.RepeatOnDelayEffect;
import org.eno.bot.widgets.effects.TimeoutEffect;

public abstract class EnoItem
        implements EnoElement {

    public void addEffect(final Effect effect) {
        if (effect instanceof RepeatOnDelayEffect) {
            final RepeatOnDelayEffect de = (RepeatOnDelayEffect) effect;
            DomGlobal.setInterval(de.getEffectCallback(getElement()),
                    de.getDelay());
        } else if (effect instanceof TimeoutEffect) {
            final TimeoutEffect te = (TimeoutEffect) effect;
            DomGlobal.setTimeout(te.getTimeoutCallback(getElement()),
                    te.getTimeoutTime());
        } else if (effect instanceof ExecuteAsapEffect) {
            ((ExecuteAsapEffect) effect).doIt(getElement());
        }

    }

}
