package org.eno.bot.widgets.effects;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;

public interface RepeatOnDelayEffect
        extends Effect {

    DomGlobal.SetIntervalCallbackFn getEffectCallback(HTMLElement component);

    double getDelay();
}
