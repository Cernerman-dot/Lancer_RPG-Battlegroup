package data.missions.lancer_mod_showcase;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {
    public void defineMission(MissionDefinitionAPI api) {
        // Set up the fleets
        api.initFleet(FleetSide.PLAYER, "UND", FleetGoal.ATTACK, false, 10);
        api.initFleet(FleetSide.ENEMY, "DNU", FleetGoal.ATTACK, true, 5);

        // Set a blurb for each fleet
        api.setFleetTagline(FleetSide.PLAYER, "UND");

        // These show up as items in the bulleted list under
        // "Tactical Objectives" on the mission detail screen
        api.addBriefingItem("Defeat the enemy forces");
        api.addBriefingItem("Survive, or be wiped.");



        api.addToFleet(FleetSide.PLAYER, "lancer_caspian_sea_default", FleetMemberType.SHIP, "UNS Ontario", true);
        api.addToFleet(FleetSide.PLAYER, "lancer_caspian_sea_default", FleetMemberType.SHIP, "UNS Baikal", true);
        api.addToFleet(FleetSide.PLAYER, "lancer_caspian_sea_default", FleetMemberType.SHIP, "UNS Malawi", true);
        api.addToFleet(FleetSide.PLAYER, "lancer_thoreau_default", FleetMemberType.SHIP, "UNS Lassalle", true);

        // Set up the enemy fleet
        api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, true);
        api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, false);
        api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, false);
        api.addToFleet(FleetSide.ENEMY, "eagle_Assault", FleetMemberType.SHIP, false);
        api.addToFleet(FleetSide.ENEMY, "paragon_Elite", FleetMemberType.SHIP, false);


        // Set up the map.
        float width = 24000f;
        float height = 18000f;
        api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);

        float minX = -width/2;
        float minY = -height/2;

        for (int i = 0; i < 15; i++) {
            float x = (float) Math.random() * width - width/2;
            float y = (float) Math.random() * height - height/2;
            float radius = 100f + (float) Math.random() * 900f;
            api.addNebula(x, y, radius);
        }

        api.addNebula(minX + width * 0.8f - 1000, minY + height * 0.4f, 2000);
        api.addNebula(minX + width * 0.8f - 1000, minY + height * 0.5f, 2000);
        api.addNebula(minX + width * 0.8f - 1000, minY + height * 0.6f, 2000);

        // api.addObjective(minX + width * 0.8f - 1000, minY + height * 0.4f, "nav_buoy");
        // api.addObjective(minX + width * 0.8f - 1000, minY + height * 0.6f, "nav_buoy");
        // api.addObjective(minX + width * 0.3f + 1000, minY + height * 0.3f, "comm_relay");
        // api.addObjective(minX + width * 0.3f + 1000, minY + height * 0.7f, "comm_relay");
        // api.addObjective(minX + width * 0.5f, minY + height * 0.5f, "sensor_array");
        // api.addObjective(minX + width * 0.2f + 1000, minY + height * 0.5f, "sensor_array");

        api.addAsteroidField(minX + width * 0.3f, minY, 90, 3000f,
                20f, 70f, 50);

        api.addPlanet(0, 0, 200f, "irradiated", 350f, true);

    }
}