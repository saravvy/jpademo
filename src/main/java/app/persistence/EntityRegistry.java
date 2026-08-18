package app.persistence;

import app.persistence.entities.Point;
import org.hibernate.cfg.Configuration;

final class EntityRegistry {

    private EntityRegistry() {}

    static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Point.class);
        // TODO: Add more entities here...
    }
}