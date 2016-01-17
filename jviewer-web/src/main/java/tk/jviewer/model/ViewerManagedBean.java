package tk.jviewer.model;

import tk.jviewer.entity.RoomEntity;

/**
 * Contains state of the Viewer dialog.
 */
public class ViewerManagedBean {

    private RoomEntity currentRoom;

    public RoomEntity getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(RoomEntity currentRoom) {
        this.currentRoom = currentRoom;
    }

}