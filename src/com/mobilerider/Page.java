package com.mobilerider;

import java.util.Iterator;
import java.util.List;

public class Page<T> implements Iterable<T>
{
    private final List<T> _items;

    private List<T> getItems()
    {
        return _items;
    }

    private final int _pageSize;

    public int getPageSize()
    {
        return _pageSize;
    }

    private final int _index;

    public int getIndex()
    {
        return _index;
    }

    private final int _totalNumberOfItems;

    public int getTotalNumberOfItems()
    {
        return _totalNumberOfItems;
    }

    public int getPageCount()
    {
        return getTotalNumberOfItems() / getPageSize() + (getTotalNumberOfItems() % getPageSize() == 0 ? 0 : 1);
    }

    public boolean isFirst()
    {
        return getIndex() == 0;
    }

    public boolean isLast()
    {
        return getIndex() == (getPageCount() == 0 ? 0 : getPageCount() - 1);
    }

    public boolean isPaginationNecessary()
    {
        return getPageCount() > 1;
    }

    public Page(List<T> items, int pageSize, int index, int totalNumberOfItems)
    {
        if (items == null)
        {
            throw new IllegalArgumentException("items");
        }

        if (pageSize <= 0)
        {
            throw new IllegalArgumentException("pageSize");
        }

        if (items.size() > pageSize)
        {
            throw new IllegalArgumentException("items");
        }

        if (index < 0)
        {
            throw new IllegalArgumentException("index");
        }

        if (totalNumberOfItems < 0)
        {
            throw new IllegalArgumentException("totalNumberOfItems");
        }

        _pageSize = pageSize;
        _index = index;
        _totalNumberOfItems = totalNumberOfItems;

        if (!isLast() && items.size() != pageSize)
        {
            throw new IllegalArgumentException("items");
        }

        _items = items;
    }

   /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator()
    {
        return getItems().iterator();
    }
}