package dk.sdu.mmmi.cbse.asteroidsystem;

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

public class CreateAsteroidTest {

    @Mock
    private GameData gameData;

    private AsteroidPlugin playPlug = new AsteroidPlugin();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(gameData.getDisplayWidth()).thenReturn((int) 800f);
        when(gameData.getDisplayHeight()).thenReturn((int) 600f);
    }

    @Test
    void testCreatePlayerShip() {
        Entity asteroid = playPlug.createAsteroid(gameData);

        // Verify that the player ship has the correct components
        Assertions.assertNotNull(asteroid.getPart(MovingPart.class));
        Assertions.assertNotNull(asteroid.getPart(LifePart.class));

        // Verify that the components have the correct values
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        Assertions.assertEquals(0, movingPart.getDeceleration());
        Assertions.assertEquals(50f, movingPart.getAcceleration());
        Assertions.assertEquals(0, movingPart.getRotationSpeed());


        LifePart lifePart = asteroid.getPart(LifePart.class);
        Assertions.assertEquals(3, lifePart.getLife());
    }
}