package lancer;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
//import exerelin.campaign.SectorManager;
import lancer.world.lancergen;

public class lancerplugin extends BaseModPlugin {

    @Override
    public void onNewGame() {

            new lancergen().generate(Global.getSector());

    }
}