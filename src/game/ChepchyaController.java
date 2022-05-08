package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class ChepchyaController implements StepListener {
    private NPC npc;
    private int x1;
    private int x2;

    public ChepchyaController(NPC npc, int x1, int x2){
        this.npc = npc;
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if(npc.getPosition().x > x2){
            npc.updateImage(npc.getIdleLeftImage());
            npc.startWalking(-npc.getWalking_speed());


        }
        else if (npc.getPosition().x < x1){

            npc.updateImage(npc.getIdleRightImage());
            npc.startWalking(npc.getWalking_speed());


        }
    }
}
