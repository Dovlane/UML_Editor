package raf.dsw.classycraft.app.model.elements.Connection;

import raf.dsw.classycraft.app.model.compositePattern.ClassyNode;
import raf.dsw.classycraft.app.model.elements.Interclass.Interclass;

public class Generalization extends Connection {

    public Generalization(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Generalization(String name, ClassyNode parent, Interclass from, Interclass to) {
        super(name, parent, from, to);
    }
}
