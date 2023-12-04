package raf.dsw.classycraft.app.model.ClassyRepository;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.compositePattern.ClassyNode;
import raf.dsw.classycraft.app.model.compositePattern.ClassyNodeLeaf;
import raf.dsw.classycraft.app.model.observerPattern.IListener;
import raf.dsw.classycraft.app.model.observerPattern.IPublisher;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNodeLeaf implements IPublisher {

    List<IListener> listeners = new ArrayList<>();

    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
        ApplicationFramework.getInstance().getClassyRepository().addDiagramView(this);

        // If the diagram within the currently displayed package
        // is removed, it should notify the PackageView about it.
        if (parent == Package.getDisplayedPackage()) {

            // null is equivalent to updatePackageView
            Package.getDisplayedPackage().notifyAllSubscribers(null);

        }
    }

    @Override
    public void addListener(IListener listener) {
        if (!listeners.contains(listener))
            listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) {
        if (listeners.contains(listener))
            listeners.remove(listener);
    }

    @Override
    public void notifyAllSubscribers(Object notification) {
        for (IListener listener: listeners)
                listener.update(notification);
    }
}
