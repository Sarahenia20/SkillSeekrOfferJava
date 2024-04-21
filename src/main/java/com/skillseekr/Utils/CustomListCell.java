package com.skillseekr.Utils;

import com.skillseekr.Models.Offers.Offer;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CustomListCell extends ListCell<Offer> {
    private final Label idLabel;
    private final Label titleLabel;
    private final Label descriptionLabel;
    private final Label authorLabel;
    private final Label createdAtLabel;
    private final Label motiveLabel;
    private final Label typeLabel;
    private final Label locationLabel;
    private final Label statusLabel;
    private final Label fileNameLabel;

    public CustomListCell() {
        idLabel = new Label();
        titleLabel = new Label();
        descriptionLabel = new Label();
        authorLabel = new Label();
        createdAtLabel = new Label();
        motiveLabel = new Label();
        typeLabel = new Label();
        locationLabel = new Label();
        statusLabel = new Label();
        fileNameLabel = new Label();

        setGraphic(new HBox(
                new VBox(
                        new Label("ID:"),
                        new Label("Title:"),
                        new Label("Description:"),
                        new Label("Author:"),
                        new Label("Created At:"),
                        new Label("Motive:"),
                        new Label("Type:"),
                        new Label("Location:"),
                        new Label("Status:"),
                        new Label("File Name:")
                ),
                new VBox(
                        idLabel,
                        titleLabel,
                        descriptionLabel,
                        authorLabel,
                        createdAtLabel,
                        motiveLabel,
                        typeLabel,
                        locationLabel,
                        statusLabel,
                        fileNameLabel
                )
        ));
    }

    @Override
    protected void updateItem(Offer offer, boolean empty) {
        super.updateItem(offer, empty);

        if (empty || offer == null) {
            setText(null);
            idLabel.setText(null);
            titleLabel.setText(null);
            descriptionLabel.setText(null);
            authorLabel.setText(null);
            createdAtLabel.setText(null);
            motiveLabel.setText(null);
            typeLabel.setText(null);
            locationLabel.setText(null);
            statusLabel.setText(null);
            fileNameLabel.setText(null);
        } else {
            idLabel.setText(offer.getId().toString());
            titleLabel.setText(offer.getTitle());
            descriptionLabel.setText(offer.getDescription());
            authorLabel.setText(offer.getAuthor());
            createdAtLabel.setText(offer.getCreated_at().toString());
            motiveLabel.setText(offer.getMotive().toString());
            typeLabel.setText(offer.getType().toString());
            locationLabel.setText(offer.getLocation().toString());
            statusLabel.setText(offer.getStatus().toString());
            fileNameLabel.setText(offer.getFile_name());
        }
    }
}
