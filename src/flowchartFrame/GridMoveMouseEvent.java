/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowchartFrame;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

/**
 *
 * @author Siyovush Kadyrov <siyovush.kadyrov@gmail.com>
 */
public class GridMoveMouseEvent extends MouseEvent
{

    public GridMoveMouseEvent(EventType<? extends MouseEvent> eventType, double x, double y, double screenX, double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown, boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown, boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult)
    {
        super(eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown, primaryButtonDown, middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress, pickResult);
    }

    public double getGridX()
    {
        return super.getX() / 5;
    }

    public double getGridY()
    {
        return super.getY() / 5;
    }
    
    public static void fireEvent(EventTarget eventTarget, Event event)
    {
        
    }
}
