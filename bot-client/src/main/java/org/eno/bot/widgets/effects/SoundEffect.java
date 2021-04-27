package org.eno.bot.widgets.effects;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLAudioElement;
import elemental2.dom.HTMLElement;

public class SoundEffect implements ExecuteAsapEffect {

    private final HTMLAudioElement sound = (HTMLAudioElement) DomGlobal.document.createElement("audio");

    private final String addressToSound;

    public SoundEffect(final String addressToSound) {
        this.addressToSound = addressToSound;
    }

    @Override
    public void doIt(final HTMLElement component) {
        sound.src = addressToSound;
        component.append(sound);
        sound.play();
    }
}
