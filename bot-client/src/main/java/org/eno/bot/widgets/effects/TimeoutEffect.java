package org.eno.bot.widgets.effects;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLElement;

public interface TimeoutEffect extends Effect {

    DomGlobal.SetTimeoutCallbackFn getTimeoutCallback(HTMLElement component);

    double getTimeoutTime();
}
