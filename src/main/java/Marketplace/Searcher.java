package Marketplace;

import Marketplace.Items.types.Item;

import java.util.ArrayList;
import java.util.Stack;

public class Searcher {
    private Stack<Filter> filters;
    private Sorter sorter = null;

    /**
     * executes a search using the filters specified in filters and the sorter, if it has been given a value.
     *
     * @return an arraylist of the resulting items
     */
    public ArrayList<Item> execute() {
        ArrayList<Item> items = Database.GetLst();
        while (filters.size() > 0){
            Filter filter = filters.pop();
            items = filter.apply(items);
        }
        if (!(this.sorter == null)){
            sorter.sort(items);
        }
       return items;
    }

    /**
     * adds a new filter to filters
     * @param filter   the filter to be added
     */
    public void addFilter(Filter filter){
        filters.push(filter);
    }

    /**
     * changes this searcher's sorter
     * @param sorter the sorter to be used
     */
    public void addSorter(Sorter sorter){
        this.sorter = sorter;
    }

}