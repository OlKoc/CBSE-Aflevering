package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;

public class CreateEnemyShipTest {

    @Mock
    private GameData gameData;

    private EnemyPlugin playPlug = new EnemyPlugin();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gameData.getDisplayWidth()).thenReturn((int) 800f);
        when(gameData.getDisplayHeight()).thenReturn((int) 600f);
    }

    @Test
    void testCreatePlayerShip() {
        Entity enemyShip = playPlug.createEnemyShip(gameData);

        // Verify that the player ship has the correct components
        Assertions.assertNotNull(enemyShip.getPart(MovingPart.class));
        Assertions.assertNotNull(enemyShip.getPart(LifePart.class));

        // Verify that the components have the correct values
        MovingPart movingPart = enemyShip.getPart(MovingPart.class);
        Assertions.assertEquals(10f, movingPart.getDeceleration());
        Assertions.assertEquals(80f, movingPart.getAcceleration());
        Assertions.assertEquals(100f, movingPart.getMaxSpeed());
        Assertions.assertEquals(5f, movingPart.getRotationSpeed());


        LifePart lifePart = enemyShip.getPart(LifePart.class);
        Assertions.assertEquals(1, lifePart.getLife());
    }
}