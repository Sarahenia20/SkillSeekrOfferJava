package com.skillseekr.Utils;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import com.skillseekr.Models.Offers.Offer;

public class CustomListCellFactory implements Callback<ListView<Offer>, ListCell<Offer>> {
    @Override
    public ListCell<Offer> call(ListView<Offer> listView) {
        return new CustomListCell();
    }
}
