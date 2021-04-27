package org.eno.bot.widgets;

import org.eno.bot.widgets.effects.Effect;

public class EnoBossImage extends ImageWithMessage {

    public EnoBossImage(final String text,
                        final Effect... effect) {
        super("enoPog",
                "images/eno_boss.png",
                text);
        for (Effect e : effect) {
            addEffect(e);
        }

    }

}
