package core.event;

import core.entity.field.Cell;
import core.entity.field.Knuckle;

public interface KnuckleListener {
    /**
     * Оповещение слушателей о перемещении костяшки
     * @param knuckle костяшка, изменившая позицию на поле
     */
    void knuckleMoved(Knuckle knuckle, Cell newKnucklePosition);
}
