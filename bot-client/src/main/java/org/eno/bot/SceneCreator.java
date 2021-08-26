package org.eno.bot;

import elemental2.dom.CSSProperties;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.eno.bot.scene.Element;
import org.eno.bot.scene.Scene;
import org.eno.bot.scene.Text;

public class SceneCreator {

    private final HTMLDivElement base = (HTMLDivElement) DomGlobal.document.createElement("div");

    public SceneCreator(Scene testScene) {

        updateTransform(base,
                testScene);
        updateStyle(base,
                testScene);

        for (Element element : testScene.getElements()) {

            if (element instanceof Text) {
                final HTMLDivElement uiTextContainer = (HTMLDivElement) DomGlobal.document.createElement("div");
                final elemental2.dom.Text uiText = DomGlobal.document.createTextNode(((Text) element).getContent());
                uiTextContainer.append(uiText);

                updateTransform(uiTextContainer,
                        element);
                updateStyle(uiTextContainer,
                        element);

                base.append(uiTextContainer);
            }


        }

//        base.append(image);

    }

    private static void updateStyle(HTMLElement base,
                                    Element model) {
        if (model.getStyle().getBackgroundColor() != null) {
            base.style.background = model.getStyle().getBackgroundColor();
        }
    }

    private static void updateTransform(HTMLElement base,
                                        Element model) {
        base.style.position = "fixed";
//        base.style.zIndex = CSSProperties.ZIndexUnionType.of("-1");
        base.style.left = model.getTransform().getX() + "px";
        base.style.top = model.getTransform().getY() + "px";
        base.style.height = CSSProperties.HeightUnionType.of(model.getTransform().getHeight() + "px");
        base.style.width = CSSProperties.WidthUnionType.of(model.getTransform().getWidth() + "px");
    }

    public HTMLDivElement create() {
        return base;
    }
}
