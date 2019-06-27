package dataStructure;

/**
 * @(#)Arrow.java
 *
 *
 * @author
 * @version 1.00 2018/11/30
 */
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Arrow
{

    private ArrayList<Fragment> arrows;
    private Edge edge;

    private double lastLineEndX, lastLineEndY;
    private boolean readyToDelete;
    private boolean delete = false;
    private boolean isConnected;
    private boolean isItFirst;

    public Arrow()
    {
        readyToDelete = false;
        isItFirst = true;
        isConnected = false;
        edge = new Edge();
        arrows = new ArrayList();
        delete = false;
    }

    public Arrow(double x, double y)
    {
        readyToDelete = false;
        isItFirst = true;
        isConnected = false;
        edge = new Edge();
        arrows = new ArrayList();
        delete = false;
        lastLineEndX = x;
        lastLineEndY = y;
    }

    public double getLastLineEndX()
    {
        return lastLineEndX;
    }

    public void setLineEndX(double lastLineEndX)
    {
        this.lastLineEndX = lastLineEndX;
    }

    public double getLastLineEndY()
    {
        return lastLineEndY;
    }

    public void setLineEndY(double lastLineEndY)
    {
        this.lastLineEndY = lastLineEndY;
    }

    public Joint getEdgeBegin()
    {
        return edge.getBegin();
    }

    public void setEdgeBegin(Joint begin)
    {
        edge.setBegin(begin);
    }
    
    @XmlElement
    public  Edge getEdge()
    {
        return edge;
    }

    public Joint getEdgeEnd()
    {
        return edge.getEnd();
    }

    public void setEdgeEnd(Joint end)
    {
        edge.setEnd(end);
    }
//isConnected accessor methods ---------------------------------------------

    public boolean isConnected()
    {
        return isConnected;
    }

    public void setConnected(boolean bool)
    {
        isConnected = bool;
    }
//isConnected accessor methods end -----------------------------------------

//arrylist accessor methods beginning --------------------------------------
    public void AddArrow(double x, double y)
    {
        if (Math.abs(x - lastLineEndX) > Math.abs(y - lastLineEndY))
        {
            addHorizontalArrow(x, y);
        }
        else
        {
            addVerticalArrow(x, y);
        }
        /*if(isConnected){
            addEndPointLines(x, y);
        }*/
    }

    private void addHorizontalArrow(double x, double y)
    {
        HorizontalArrow htemp = new HorizontalArrow();
        htemp.setOwner(this);
        if (isItFirst)
        {
            lastLineEndX = x;
            lastLineEndY = y;
            isItFirst = false;

        }
        else
        {
            htemp.setHFragment(lastLineEndX, lastLineEndY, x);
            lastLineEndX = htemp.getHFragment().getEndX();
            lastLineEndY = htemp.getHFragment().getEndY();
            arrows.add(htemp);
            if (isConnected)
            {
                if (edge.getEnd().getJointNumber() == 1)
                {
                    EndPointLines eFrag = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX + 10, lastLineEndY - 7);
                    arrows.add(eFrag);
                    EndPointLines eFrag2 = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX + 10, lastLineEndY + 7);
                    arrows.add(eFrag2);
                }
                else if (edge.getEnd().getJointNumber() == 3)
                {
                    EndPointLines eFrag = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX - 10, lastLineEndY - 7);
                    arrows.add(eFrag);
                    EndPointLines eFrag2 = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX - 10, lastLineEndY + 7);
                    arrows.add(eFrag2);
                }

            }
        }
    }

    private void addVerticalArrow(double x, double y)
    {
        VerticalArrow vtemp = new VerticalArrow();
        vtemp.setOwner(this);
        if (isItFirst)
        {
            lastLineEndX = x;
            lastLineEndY = y;
            isItFirst = false;
        }
        else
        {
            vtemp.setVFragment(lastLineEndX, lastLineEndY, y);
            lastLineEndX = vtemp.getVFragment().getEndX();
            lastLineEndY = vtemp.getVFragment().getEndY();
            arrows.add(vtemp);
            if (isConnected)
            {
                if (edge.getEnd().getJointNumber() == 0)
                {
                    EndPointLines eFrag = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX - 7, lastLineEndY - 10);
                    arrows.add(eFrag);
                    EndPointLines eFrag2 = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX + 7, lastLineEndY - 10);
                    arrows.add(eFrag2);
                }
                else if (edge.getEnd().getJointNumber() == 2)
                {
                    EndPointLines eFrag = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX - 7, lastLineEndY + 10);
                    arrows.add(eFrag);
                    EndPointLines eFrag2 = new EndPointLines(lastLineEndX, lastLineEndY, lastLineEndX + 7, lastLineEndY + 10);
                    arrows.add(eFrag2);
                }

            }
        }
    }

    public Shape getArrowFragment(int fragmentIndex)
    {
        return arrows.get(fragmentIndex).getFragmentShape();
    }

    public ArrayList<Shape> getAllArrowFragments()
    {
        ArrayList<Shape> fragments = new ArrayList();
        for (int i = 0; i < arrows.size(); i++)
        {
            fragments.add(arrows.get(i).getFragmentShape());
        }
        return fragments;
    }

    public void deleteArrow()
    {
        readyToDelete = true;
    }

    public boolean getReadyToDelete()
    {
        return readyToDelete;
    }

    @XmlElement
    public ArrayList<Fragment> getArrowAsFragments()
    {
        ArrayList<Fragment> fragments = new ArrayList();
        for (int i = 0; i < arrows.size(); i++)
        {
            fragments.add(arrows.get(i));
        }
        return fragments;
    }

    public void sdelete(Rectangle rekt)
    {
        for (int i = 0; i < arrows.size(); i++)
        {
            arrows.get(i).contains(rekt);
        }
    }

    public String toString()
    {
        String s = "edge begin : " + getEdgeBegin().toString() + ", edge end : " + getEdgeEnd().toString();
        return s;
    }

//arraylist accessor methods ending ------------------------------------------
// private classes ---------------------------------------------------------------
    @XmlRootElement
    private class Edge
    {

        private Joint begin, end;

        private Edge()
        {
            begin = null;
            end = null;
        }

        private void setBegin(Joint begin)
        {
            this.begin = begin;
        }

        private void setEnd(Joint end)
        {
            this.end = end;
        }
        
        @XmlElement
        private Joint getBegin()
        {
            return begin;
        }

        @XmlElement
        private Joint getEnd()
        {
            return end;
        }
    }

    private class HorizontalArrow implements Fragment
    {

        private Line hFragment;
        private Arrow owner;

        private HorizontalArrow()
        {
            hFragment = new Line();
            hFragment.setStrokeWidth(3);
        }

        public void contains(Rectangle rekt)
        {
            if (hFragment.intersects(rekt.getBoundsInLocal()))
            {
                deleteArrow();
            }
        }

        public Shape getFragmentShape()
        {
            return (Shape) hFragment;
        }

        public Line getHFragment()
        {
            return hFragment;
        }

        public void setHFragment(double x1, double y, double x2)
        {
            hFragment.setStartX(x1);
            hFragment.setStartY(y);
            hFragment.setEndX(x2);
            hFragment.setEndY(y);
        }

        public void TranslateFragmentLoc(double x, double y)
        {
            hFragment.setTranslateX(x);
            hFragment.setTranslateY(y);
        }

        public void setOwner(Arrow owner)
        {
            this.owner = owner;
        }

        public Arrow getOwner()
        {
            return owner;
        }

        public void shouldIDelete(Circle a)
        {
            if (delete)
            {

            }
            else
            {
                delete = hFragment.intersects(a.getCenterX(), a.getCenterY(), a.getRadius(), a.getRadius());
            }
        }
    }

    private class VerticalArrow implements Fragment
    {

        private Line vFragment;
        private Arrow owner;

        private VerticalArrow()
        {
            vFragment = new Line();
            vFragment.setStrokeWidth(3);
        }

        public void contains(Rectangle rekt)
        {
            if (vFragment.intersects(rekt.getBoundsInLocal()))
            {
                deleteArrow();
            }
        }

        public Shape getFragmentShape()
        {
            return (Shape) vFragment;
        }

        public Line getVFragment()
        {
            return vFragment;
        }

        public void setVFragment(double x, double y1, double y2)
        {
            vFragment.setStartX(x);
            vFragment.setStartY(y1);
            vFragment.setEndX(x);
            vFragment.setEndY(y2);
        }

        public void TranslateFragmentLoc(double x, double y)
        {
            vFragment.setTranslateX(x);
            vFragment.setTranslateY(y);
        }

        public void setOwner(Arrow owner)
        {
            this.owner = owner;
        }

        public Arrow getOwner()
        {
            return owner;
        }

        public void shouldIDelete(Circle a)
        {
            if (delete)
            {

            }
            else
            {
                delete = vFragment.intersects(a.getCenterX(), a.getCenterY(), a.getRadius(), a.getRadius());
            }
        }
    }

    private class EndPointLines implements Fragment
    {

        private Line eFragment;
        private Arrow owner;

        private EndPointLines(double x1, double y1, double x2, double y2)
        {
            eFragment = new Line(x1, y1, x2, y2);
            eFragment.setStrokeWidth(3);
        }

        public void setEFragment(double x, double y)
        {

            eFragment.setStartX(x);
            eFragment.setStartY(y);
            eFragment.setEndX(x + 10);
            eFragment.setEndY(y + 10);
        }

        public void setOwner(Arrow owner)
        {
            this.owner = owner;
        }

        public Arrow getOwner()
        {
            return owner;
        }

        @Override
        public Shape getFragmentShape()
        {
            return (Shape) eFragment;
        }

        @Override
        public void TranslateFragmentLoc(double x, double y)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void contains(Rectangle rekt)
        {
            if (eFragment.intersects(rekt.getBoundsInLocal()))
            {
                deleteArrow();
            }
        }

    }
}
