package lancer.world.systems;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.Arrays;
import org.lazywizard.lazylib.MathUtils;

import java.awt.*;


public class my_system {
    public void generate(SectorAPI sector) {

        float planet1Dist= 4000f; //this is the distance the planet is from the star
        float planet2Dist= 5200f;
        float gate1Dist= 4000f;

        StarSystemAPI system = sector.createStarSystem("Kyrios");
        system.getLocation().set(10000, 20000); //position of the system on the map

        system.setBackgroundTextureFilename("graphics/backgrounds/background1.jpg");
        // create the star and generate the hyperspace anchor for this system
        PlanetAPI my_system_star = system.initStar("Kyrios", // unique id for this star
                "star_yellow", // id in planets.json
                900f, // radius (in pixels at default zoom)
                400); // corona radius, from star edge
        system.setLightColor(new Color(247,255,140)); // light color in entire system, affects all entities

        PlanetAPI planet1 = system.addPlanet("planet1",
                my_system_star, //what it's orbiting
                "Sioa", //name
                "barren", //the planet type (look in planets.json for more)
                900, //angle
                130f, //radius
                planet1Dist, //distance from star
                700f); //how many days to orbit
        planet1.setCustomDescriptionId("planet1"); //for custom descriptions
        PlanetAPI planet2 = system.addPlanet("planet2",
                my_system_star, //what it's orbiting
                "Tenor", //name
                "tundra", //the planet type (look in planets.json for more)
                500, //angle
                160f, //radius
                planet2Dist, //distance from star
                700f); //how many days to orbit
        planet2.setCustomDescriptionId("planet2");
        system.addCustomEntity(
                "gate1",
                "Sioa Gate", //name
                "inactive_gate", //the planet type (look in planets.json for more)
                "neutral" ).setCircularOrbitPointingDown(my_system_star, 870, gate1Dist, 700f); //how many days to orbit

        system.autogenerateHyperspaceJumpPoints(true, true); //generates jump points

        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin(); //these lines clear the hyperspace clouds around the system
        NebulaEditor editor = new NebulaEditor(plugin);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);

        MarketAPI planet1_market = addMarketplace.addMarketplace("lancer_union", planet1,
                null, //connected entities, like stations
                "Sioa",
                5, //size
                new ArrayList<>(Arrays.asList( //these are conditions
                        Conditions.POPULATION_4,
                        Conditions.TECTONIC_ACTIVITY,
                        Conditions.ORE_RICH,
                        Conditions.HOT
                )),
                new ArrayList<>(Arrays.asList( //industries
                        Industries.POPULATION,
                        Industries.SPACEPORT,
                        Industries.STARFORTRESS_HIGH,
                        Industries.MINING,
                        Industries.HIGHCOMMAND,
                        Industries.ORBITALWORKS

                )),
                new ArrayList<>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, //aaand markets
                        Submarkets.GENERIC_MILITARY,
                        Submarkets.SUBMARKET_BLACK,
                        Submarkets.SUBMARKET_OPEN)),
                0.15f
        );

        planet1_market.getIndustry(Industries.ORBITALWORKS).setSpecialItem(new SpecialItemData(Items.PRISTINE_NANOFORGE, null));
        planet1_market.getIndustry(Industries.HIGHCOMMAND).setSpecialItem(new SpecialItemData(Items.CRYOARITHMETIC_ENGINE, null));

        MarketAPI planet2_market = addMarketplace.addMarketplace("lancer_union", planet2,
                null, //connected entities, like stations
                "Tenor",
                5, //size
                new ArrayList<>(Arrays.asList( //these are conditions
                        Conditions.POPULATION_5,
                        Conditions.HABITABLE,
                        Conditions.RUINS_EXTENSIVE
                )),
                new ArrayList<>(Arrays.asList( //industries
                        Industries.POPULATION,
                        Industries.SPACEPORT,
                        Industries.STARFORTRESS_HIGH,
                        Industries.FARMING,
                        Industries.TECHMINING,
                        Industries.MILITARYBASE

                )),
                new ArrayList<>(Arrays.asList(Submarkets.SUBMARKET_STORAGE, //aaand markets
                        Submarkets.GENERIC_MILITARY,
                        Submarkets.SUBMARKET_BLACK,
                        Submarkets.SUBMARKET_OPEN)),
                0.15f
        );
    }
}

