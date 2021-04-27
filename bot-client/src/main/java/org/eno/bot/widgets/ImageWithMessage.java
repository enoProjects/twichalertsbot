package org.eno.bot.widgets;

import elemental2.dom.*;

public class ImageWithMessage
        extends EnoItem {

    protected final Image image = new Image();
    protected final HTMLDivElement imageContainer;
    protected final HTMLDivElement textContainer;
    protected final HTMLDivElement containerContainer;

    private final int imageWidth = 400;

    public ImageWithMessage(final String alt,
                            final String pathToImage,
                            final String text) {
        final int i = (DomGlobal.window.innerWidth - imageWidth) / 2;
        containerContainer = (HTMLDivElement) DomGlobal.document.createElement("div");
        containerContainer.style.width = CSSProperties.WidthUnionType.of(imageWidth + "px");
        containerContainer.style.paddingLeft = CSSProperties.PaddingLeftUnionType.of(i + "px");

        image.alt = alt;
        image.src = pathToImage;
        imageContainer = (HTMLDivElement) DomGlobal.document.createElement("div");
        imageContainer.append(image);

        textContainer = (HTMLDivElement) DomGlobal.document.createElement("div");
        textContainer.className = "textNode";
        textContainer.style.textAlign = "center";
        textContainer.style.fontSize = CSSProperties.FontSizeUnionType.of("24px");
        final Text textNode = DomGlobal.document.createTextNode(text);

        textContainer.append(textNode);

        containerContainer.append(imageContainer);
        containerContainer.append(textContainer);
    }

    @Override
    public HTMLElement getElement() {
        return containerContainer;
    }
}
