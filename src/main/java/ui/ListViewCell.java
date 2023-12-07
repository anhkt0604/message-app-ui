package ui;

import javafx.scene.control.ListCell;

public class ListViewCell extends ListCell<Contact> {
    @Override
    public void updateItem(Contact name, boolean empty)
    {
        super.updateItem(name, empty);
        if (name != null) {
            CustomCellView customCellView = new CustomCellView();
            customCellView.setName(name.getName());
            customCellView.setHastag(name.getHastag());
            customCellView.setAvatar(name.getImageFileName());

//                EventHandler addHandler = (EventHandler) (Event event) -> {
//                    System.out.println("Add " + person.getName());
//                };
//                customCellView.setBtnAddAction(addHandler);
//
//                EventHandler removeHandler = (EventHandler) (Event event) -> {
//                    System.out.println("Remove " + person.getName());
//                };

//                customCellView.setBtnRemoveAction(removeHandler);

            setGraphic(customCellView.getHBoxRoot());
        }
    }
}
