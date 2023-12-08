package raf.dsw.classycraft.app.model.StatePattern.concrete;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.ClassyRepository.Diagram;
import raf.dsw.classycraft.app.model.MessageGenerator.MessageType;
import raf.dsw.classycraft.app.model.StatePattern.State;
import raf.dsw.classycraft.app.model.elements.Connection.Aggregation;
import raf.dsw.classycraft.app.model.elements.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Interclass.Interclass;
import raf.dsw.classycraft.app.model.elements.LineElement;

import java.awt.*;

public class AddConnectionState implements State {
    DiagramElement selectedDiagramElementFrom;
    LineElement lineElement;

    public void mousePressed(Point location, DiagramView diagramView) {
        selectedDiagramElementFrom = diagramView.getElementAt(location);
        Diagram currentDiagram = diagramView.getDiagram();

        if (selectedDiagramElementFrom != null && selectedDiagramElementFrom instanceof Interclass) {
            lineElement = new LineElement("temporary line", currentDiagram, (Interclass) selectedDiagramElementFrom);
            ClassyTreeItem classyTreeDiagram =
                    MainFrame.getInstance().getClassyTree().getRoot().getTreeItemFromClassyNode(currentDiagram);
            if (classyTreeDiagram == null) {
                MainFrame.getInstance().getMessageGenerator().generateMessage(
                        "Diagram cannot be found in ClassyTree.", MessageType.ERROR);
                return;
            }
            MainFrame.getInstance().getClassyTree().attachChild(classyTreeDiagram, lineElement);

        }
        System.out.println("Adding Connection!");
    }


    @Override
    public void mouseReleased(Point location, DiagramView diagramView) {
        DiagramElement selectedDiagramElementTo = diagramView.getElementAt(location);
        Diagram currentDiagram = diagramView.getDiagram();
        if (selectedDiagramElementTo != null && selectedDiagramElementTo instanceof Interclass) {
            Aggregation aggregation = new Aggregation("aggregation", currentDiagram, (Interclass) selectedDiagramElementFrom, (Interclass)selectedDiagramElementTo);
            ClassyTreeItem classyTreeDiagram =
                    MainFrame.getInstance().getClassyTree().getRoot().getTreeItemFromClassyNode(currentDiagram);
            if (classyTreeDiagram == null) {
                MainFrame.getInstance().getMessageGenerator().generateMessage(
                        "Diagram cannot be found in ClassyTree.", MessageType.ERROR);
                return;
            }
            MainFrame.getInstance().getClassyTree().attachChild(classyTreeDiagram, aggregation);
        }

        // Remove the clicked one from painters
        ClassyTreeItem treeItemLineElement =
                MainFrame.getInstance().getClassyTree().getRoot().getTreeItemFromClassyNode(lineElement);
       MainFrame.getInstance().getClassyTree().removeItem(treeItemLineElement);
    }

    @Override
    public void mouseDragged(Point startLocation, Point currentLocation, DiagramView diagramView) {
        lineElement.setCurrentPoint(currentLocation);
    }

}
