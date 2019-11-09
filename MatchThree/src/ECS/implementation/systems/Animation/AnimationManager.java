package ECS.implementation.systems.Animation;

import ECS.implementation.systems.Animation.base.SAnimation;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationManager {

    HashMap<Integer, ArrayList<SAnimation>> animations;
    private int lastLayer;

    private void initAnimations(){
        lastLayer = 1;
        animations.put(lastLayer, new ArrayList<SAnimation>());
    }

    public AnimationManager() {
        animations = new HashMap<Integer, ArrayList<SAnimation>>();
        initAnimations();
    }

    public void generateNextLayer(){ lastLayer++; }

    public void addAnimation(SAnimation anim){
        animations.get(lastLayer).add(anim);
    }

    public void doAnimations(){
        int currLayer = 0;
        while(currLayer <= lastLayer){
            boolean handled = true;
            ArrayList<SAnimation> anims = animations.get(currLayer);
            for (SAnimation a : anims){
                a.doAnimation();
                if (handled) handled = a.isFinished();
            }

            if (handled) currLayer++;
        }

        animations.clear();
        initAnimations();
    }
}
