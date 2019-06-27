package dataStructure;

/**
 * @(#)Process.java
 *
 *
 * @author
 * @version 1.00 2018/11/27
 */
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Surec
{

    private static int processID = 0;
    private HashMap<Integer, Component> hmap;
    private ArrayList<Arrow> arrowList;

    private int objectID;
    private int currentSelected;
    private boolean ArrowMode;
    private boolean deleteMode;
    private StartEvent startingComponent;

    public Surec()
    {
        startingComponent = new StartEvent("", 0, 0, objectID, this);
        currentSelected = -1;
        processID++;
        objectID = 0;
        hmap = new HashMap<Integer, Component>();
        hmap.put(objectID, startingComponent);
        arrowList = new ArrayList();
        ArrowMode = false;
        deleteMode = false;
    }

    public void setComponentTypeWork(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new Work("", x, y, objectID, this));
    }

    public void setComponentTypeDecision(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new Decision("", x, y, objectID, this));
    }

    public void setComponentTypeDocument(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new Document("", x, y, objectID, this));
    }

    public void setComponentTypeNarrowedProcess(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new NarrowedProcess("", x, y, objectID, this));
    }

    public void setComponentTypeOffPageRef(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new OffPageRef("", x, y, objectID, this));
    }

    public void setComponentTypeStartEvent(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new StartEvent("", x, y, objectID, this));
    }

    public void setComponentTypeFinishEvent(double x, double y)
    {
        objectID++;
        hmap.put(objectID, new FinishEvent("", x, y, objectID, this));
    }

    public Component getIndexedComponent(int index)
    {
        return hmap.get(index);
    }

    @XmlElement
    public ArrayList<Arrow> getArrows()
    {
        return arrowList;
    }

    /*public void AccessComponentSelected(int index,boolean bool){
    	hmap.get(index).setSelected(bool);
    }*/
    //later write it better//
    @XmlElement
    public ArrayList<Component> getProcessComponents()
    {
        ArrayList<Component> no_woman_no_cry = new ArrayList();
        for (int i = 0; i < hmap.size(); i++)
        {
            no_woman_no_cry.add(hmap.get(i));
        }
        return no_woman_no_cry;
    }

    @XmlElement
    public static int getProcessID()
    {
        return processID;
    }

    public boolean AccessObjectJointContains(int index, int x, int y)
    {
        int jointNumber = hmap.get(index).getNumberOfJoints();
        for (int i = 0; i < jointNumber; i++)
        {
            if (hmap.get(index).getJoint(i).Contains(x, y))
            {
                return true;
            }
        }
        return false;
    }

    public int AccessObjectContainingJoint(int index, int x, int y)
    {
        int jointNumber = hmap.get(index).getNumberOfJoints();
        for (int i = 0; i < jointNumber; i++)
        {
            if (hmap.get(index).getJoint(i).Contains(x, y))
            {
                return i;
            }
        }
        return -1;
    }

    public int getCurrentSelected()
    {
        return currentSelected;
    }

    public void setCurrentSelected(int selection)
    {
        currentSelected = selection;
    }

    public void continueDrawing(double x, double y)
    {
        arrowList.get(arrowList.size() - 1).AddArrow(x, y);
    }

    public void createArrow(double x, double y)
    {
        arrowList.add(new Arrow(x, y));
    }

    public void setArrowLastPoint(double x, double y)
    {
        arrowList.get(arrowList.size() - 1).setLineEndX(x);
        arrowList.get(arrowList.size() - 1).setLineEndY(y);
    }

    public void setIndexedComponentJointVisibilty(int index, boolean vis)
    {
        (hmap.get(index)).setJointVisibility(vis);
    }

    public int getLastObjectID()
    {
        if (hmap.size() == 1)
        {
            return -1;
        }
        return hmap.size() - 1;
    }

    public int getObjectID()
    {
        return objectID;
    }

    public HashMap getCompMap()
    {
        return hmap;
    }

    public void setArrowMode(boolean bool)
    {
        ArrowMode = bool;
        if (bool)
        {
            for (int i = 1; i < hmap.size(); i++)
            {
                setIndexedComponentJointVisibilty(i, true);
            }
        }
        else
        {
            for (int i = 1; i < hmap.size(); i++)
            {
                setIndexedComponentJointVisibilty(i, false);
            }
        }
    }

    public boolean getArrowMode()
    {
        return ArrowMode;
    }

    public void drawArrows(double x, double y)
    {
        if (ArrowMode)
        {
            continueDrawing(x, y);
        }
    }

    public boolean isDeleteMode()
    {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode)
    {
        this.deleteMode = deleteMode;
    }

    //arraylist döndürüyo
    public void deleteAllSelectedComponentFromDS()
    {
        for (int i = 0; i < hmap.size(); i++)
        {
            if (hmap.get(i).getReadyToDelete())
            {
                for (int j = i; j < hmap.size() - 1; j++)
                {
                    hmap.replace(j, hmap.get(j + 1));
                    hmap.get(j).decrementObjectID();
                }
                hmap.remove(hmap.size() - 1);
                objectID = objectID - 1;
            }
        }
    }

    public ArrayList<Integer> deleteAllSelectedComponents()
    {
        ArrayList<Integer> temp = new ArrayList();
        for (int i = 0; i < hmap.size(); i++)
        {
            if (hmap.get(i).getReadyToDelete())
            {
                temp.add(i);
            }
        }
        return temp;
    }

    public ArrayList<Integer> deleteAllSelectedArrows()
    {
        ArrayList<Integer> temp = new ArrayList();
        for (int i = 0; i < arrowList.size(); i++)
        {
            if (arrowList.get(i).getReadyToDelete())
            {
                temp.add(i);
            }
        }
        return temp;
    }

    public void deleteAllSelectedArrowsFromDS()
    {
        for (int i = 0; i < arrowList.size(); i++)
        {
            if (arrowList.get(i).getReadyToDelete())
            {
                arrowList.remove(i);
            }
        }
    }

    public String getConnectionMap()
    {
        String s = "";
        System.out.println(arrowList.size());
        for (int i = 0; i < arrowList.size(); i++)
        {
            s = s + "\narrow no :" + i + " = " + arrowList.get(i).toString();
        }
        return s;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < hmap.size(); i++)
        {
            s = s + hmap.get(i).toString();
        }
        return s;
    }

}
