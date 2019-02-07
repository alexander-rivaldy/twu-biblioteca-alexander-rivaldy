package com.twu.biblioteca;

/**
 * Created by alexa on 4/02/2019.
 */
public interface LibraryItem {

    boolean available = true;

    public String getFullDetail();

    public void borrowItem(Customer customer) throws ItemCannotBeCheckedOutException;

    public void returnItem() throws ItemNotValidForReturnException;

    public String getTitle();

    public boolean isAvailable();


}
