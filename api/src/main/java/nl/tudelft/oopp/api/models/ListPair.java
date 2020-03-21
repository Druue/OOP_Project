package nl.tudelft.oopp.api.models;

import java.util.List;

public class ListPair<T, E> {

    private List<T> listIds;
    private List<E> listNames;


    public ListPair(List<T> listIds, List<E> listNames) {
        this.listIds = listIds;
        this.listNames = listNames;
    }

    public List<T> getListIds() {
        return listIds;
    }

    public void setListIds(List<T> listIds) {
        this.listIds = listIds;
    }

    public List<E> getListNames() {
        return listNames;
    }

    public void setListNames(List<E> listNames) {
        this.listNames = listNames;
    }
}
