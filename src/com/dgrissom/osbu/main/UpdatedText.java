package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UpdatedText implements Iterable<String> {
    private List<String> textFrames;
    private double fps;
    private int currentFrame;

    public UpdatedText() {
        this(new ArrayList<>());
    }
    public UpdatedText(String... textFrames) {
        this(new ArrayList<>(Arrays.asList(textFrames)));
    }
    public UpdatedText(List<String> textFrames) {
        this(2, textFrames);
    }
    public UpdatedText(double fps, String... textFrames) {
        this(fps, new ArrayList<>(Arrays.asList(textFrames)));
    }
    public UpdatedText(double fps, List<String> textFrames) {
        this.textFrames = textFrames;
        this.fps = fps;
        this.currentFrame = 0;
    }

    public List<String> getTextFrames() {
        return this.textFrames;
    }
    public UpdatedText addTextFrame(String text) {
        this.textFrames.add(text);
        return this;
    }
    public boolean removeTextFrame(int frame) {
        return this.textFrames.size() > frame && frame >= 0 && this.textFrames.remove(frame) != null;
    }
    public double getFramesPerSecond() {
        return this.fps;
    }
    public UpdatedText setFramesPerSecond(int fps) {
        this.fps = fps;
        return this;
    }
    public int getCurrentFrame() {
        return this.currentFrame;
    }
    public UpdatedText setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
        return this;
    }
    public String getCurrentTextFrame() {
        return new StringUtility(this.textFrames.get(this.currentFrame)).format().toString();
    }

    public String update() {
        this.currentFrame++;
        if (this.currentFrame >= this.textFrames.size())
            this.currentFrame = 0;
        return getCurrentTextFrame();
    }

    @Override
    public Iterator<String> iterator() {
        return this.textFrames.iterator();
    }



    /*
    text = Carousel text   (3 spaces after, length = 16)
    frameLength = 5
    frame = 0: Carou b=0,e=4
    frame = 1: arous b=1,e=5
    frame = 2: rouse b=2,e=6
    frame = 3: ousel etc.
    frame = 4: usel
    frame = 5: sel t
    frame = 6: el te
    frame = 7: l tex
    frame = 8:  text
    frame = 9: text
    frame = 10:ext
    frame = 11:xt
    frame = 12:t   C
    frame = 13:   Ca
    frame = 14:  Car
    frame = 15: Caro
    fullCarouselString = Carousel text   Caro(length=20)

    text = Carousel  (2 spaces after, length = 10)
    frameLength = 6
    frame = 0: Carous b=0,e=5
    frame = 1: arouse b=1,e=6
    frame = 2: rousel b=2,e=7
    frame = 3: ousel  etc.
    frame = 4: usel
    frame = 5: sel  C
    frame = 6: el  Ca
    frame = 7: l  Car
    frame = 8:   Caro
    frame = 9:  Carou
    fullCarouselString = Carousel  Carou(length = 15)

    frames + frameLength = fullCarouselString.length()
    frames = fullCarouselString.length() - frameLength
     */
    public static UpdatedText generateCarousel(String text, int frameLength, int fps, boolean addSpaceSeparator) {
        if (text.length() <= frameLength)
            return new UpdatedText(fps, text);
        if (addSpaceSeparator)
            for (int i = 0; i < frameLength; i++)
                text += ' ';
        String fullCarouselString = text + text.substring(0, frameLength + 2);
        int frameCount = fullCarouselString.length() - frameLength;
        List<String> frames = new ArrayList<>(frameCount);
        for (int frame = 0; frame < frameCount; frame++)
            frames.add(fullCarouselString.substring(frame, frame + frameLength));
        return new UpdatedText(fps, frames);
    }
}
