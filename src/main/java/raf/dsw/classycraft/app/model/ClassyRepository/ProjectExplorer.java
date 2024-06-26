package raf.dsw.classycraft.app.model.ClassyRepository;

import raf.dsw.classycraft.app.model.compositePattern.ClassyNode;
import raf.dsw.classycraft.app.model.compositePattern.ClassyNodeComposite;
import raf.dsw.classycraft.app.model.observerPattern.IPublisher;

public class ProjectExplorer extends ClassyNodeComposite implements IPublisher {

    private int nmbOfCreatedProjects;

    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public boolean addChild(ClassyNode child) {
        if (child instanceof Project) {
            if (getChildByName(child.getName()) == null) {
                getChildren().add(child);
                Notification notification =
                        new Notification(child, NotificationType.ADD);
                notifyAllSubscribers(notification);
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeOccurred() {
        setChanged(true);
    }

    public void increaseCounter() {
        nmbOfCreatedProjects += 1;
    }

    public int getNmbOfCreatedProjects() {
        return nmbOfCreatedProjects;
    }

}
