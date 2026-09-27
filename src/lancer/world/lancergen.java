package lancer.world;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import lancer.world.systems.my_system;

public class lancergen {
    public static void initFactionRelationships(SectorAPI sector) {
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI league = sector.getFaction(Factions.PERSEAN);
        FactionAPI lancer_union = sector.getFaction("lancer_union");

        lancer_union.setRelationship(path.getId(), RepLevel.HOSTILE);
        lancer_union.setRelationship(hegemony.getId(), RepLevel.FAVORABLE);
        lancer_union.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        lancer_union.setRelationship(tritachyon.getId(), RepLevel.SUSPICIOUS);
        lancer_union.setRelationship(church.getId(), RepLevel.INHOSPITABLE);
        lancer_union.setRelationship(kol.getId(), RepLevel.INHOSPITABLE);
        lancer_union.setRelationship(league.getId(), RepLevel.SUSPICIOUS);

    }

    public void generate(SectorAPI sector) {

        initFactionRelationships(sector);
        new my_system().generate(sector);


    }
}
