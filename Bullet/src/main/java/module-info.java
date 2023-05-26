import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.services.IBulletSystem;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Bullet {
    requires Common;

    provides IBulletSystem with BulletPlugin;
    provides IGamePluginService with BulletPlugin;
    provides IEntityProcessingService with BulletControlSystem;
    exports dk.sdu.mmmi.cbse.bulletsystem;
}