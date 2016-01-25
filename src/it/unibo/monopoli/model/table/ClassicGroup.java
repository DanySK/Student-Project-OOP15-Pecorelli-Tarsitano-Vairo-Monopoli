package it.unibo.monopoli.model.table;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ClassicGroup implements Group {

    private final List<Ownership> members;
    private  final String name;

    public ClassicGroup(final String name, final Ownership... members) {
        this.members = new LinkedList<>();
        this.members.addAll(Arrays.asList(members));
        this.name = name;
    }

    @Override
    public List<Ownership> getMembers() {
        return Collections.unmodifiableList(this.members);
    }

    @Override
    public String getName() {
        return this.name;
    }

}
